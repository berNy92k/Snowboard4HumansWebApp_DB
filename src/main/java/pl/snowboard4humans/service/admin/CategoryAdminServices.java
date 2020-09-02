package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.model.Category;
import pl.snowboard4humans.repository.CategoryRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryAdminServices extends SuperService {
    private CategoryRepo categoryRepo;

    @Autowired
    public CategoryAdminServices(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public String getCategories(Model model) {
        String message = ConstantsAdminENG.LIST_OF_CATEGORY_ADMIN;

        List<Category> categoryList = categoryRepo.findAll();

        // if categories list is empty then message change
        if (Utils.isEmpty(categoryList)) {
            message = ConstantsAdminENG.LACK_OF_CATEGORY_IN_DB;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.CATEGORY_LIST_OBJECT,
                categoryList,
                ConstantsAdminENG.CATEGORY_LIST_URL);
    }

    public String getAddNewCategoryScreen(Model model) {
        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_CATEGORY_ADMIN,
                ConstantsAdminENG.CATEGORY_OBJECT,
                new Category(),
                ConstantsAdminENG.CATEGORY_CREATE_OR_UPDATE_URL);
    }

    public String addOrUpdateCategory(Model model,
                                      Category category) {
        String message;

        Integer categoryId = category.getId();
        Category categorySaved = categoryRepo.save(category);

        List<Category> categoryList = categoryRepo.findAll();
        if (categoryId == null && categorySaved != null && categorySaved.getId() != null) {
            message = ConstantsAdminENG.NEW_CATEGORY_WAS_CREATED;
        } else if (categoryId != null && categorySaved != null && categorySaved.getId() != null) {
            message = ConstantsAdminENG.CATEGORY_WAS_UPDATED;
        } else if (categoryId == null && categorySaved == null) {
            message = ConstantsAdminENG.NEW_CATEGORY_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.CATEGORY_WAS_NOT_UPDATED;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.CATEGORY_LIST_OBJECT,
                categoryList,
                ConstantsAdminENG.CATEGORY_LIST_URL);
    }

    public String getEditCategoryScreen(Model model,
                                        int categoryId) {
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        Category category = categoryOptional.orElseGet(Category::new);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_CATEGORY_ADMIN,
                ConstantsAdminENG.CATEGORY_OBJECT,
                category,
                ConstantsAdminENG.CATEGORY_CREATE_OR_UPDATE_URL);
    }

    public String deleteCategory(Model model,
                                 int categoryId) {
        String message;
        Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        if (categoryOptional.isPresent()) {
            categoryRepo.deleteById(categoryId);
            message = ConstantsAdminENG.CATEGORY_WAS_DELETED;
        } else {
            message = ConstantsAdminENG.COULD_NOT_FIND_CATEGORY_BY_ID + ConstantsAdminENG.DELETED_BY_ANOTHER_CATEGORY_ADMIN;
        }

        List<Category> categoryList = categoryRepo.findAll();

        // if categories list is empty then message change
        if (Utils.isEmpty(categoryList)) {
            message = ConstantsAdminENG.LACK_OF_CATEGORY_IN_DB + message;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.CATEGORY_LIST_OBJECT,
                categoryList,
                ConstantsAdminENG.CATEGORY_LIST_URL);
    }
}
