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

        return getRequestDispatcher(model,
                message,
                ConstantsAdminENG.CATEGORY_LIST_OBJECT,
                categoryList,
                ConstantsAdminENG.CATEGORY_LIST_URL);
    }

    public String getAddNewCategoryScreen(Model model) {
        return getRequestDispatcher(model,
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

        List<Category> userList = categoryRepo.findAll();
        if (categoryId == null && categorySaved != null && categorySaved.getId() != null) {
            message = ConstantsAdminENG.NEW_CATEGORY_WAS_CREATED;
        } else if (categoryId != null && categorySaved != null && categorySaved.getId() != null) {
            message = ConstantsAdminENG.CATEGORY_WAS_UPDATED;
        } else if (categoryId == null && categorySaved == null) {
            message = ConstantsAdminENG.NEW_CATEGORY_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.CATEGORY_WAS_NOT_UPDATED;
        }

        return getRequestDispatcher(model,
                message,
                ConstantsAdminENG.CATEGORY_LIST_OBJECT,
                userList,
                ConstantsAdminENG.CATEGORY_LIST_URL);
    }

    public String getEditCategoryScreen(Model model,
                                        int categoryId) {
        Optional<Category> userOptional = categoryRepo.findById(categoryId);
        Category category = userOptional.orElseGet(Category::new);

        return getRequestDispatcher(model,
                ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_CATEGORY_ADMIN,
                ConstantsAdminENG.CATEGORY_OBJECT,
                category,
                ConstantsAdminENG.CATEGORY_CREATE_OR_UPDATE_URL);
    }
}
