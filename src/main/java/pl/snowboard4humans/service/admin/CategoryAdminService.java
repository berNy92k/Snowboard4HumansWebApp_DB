package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Category;
import pl.snowboard4humans.repository.CategoryRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryAdminService extends SuperService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryAdminService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public MsgAndListDto<Category> getCategories() {
        final List<Category> categoryList = categoryRepo.findAll();

        final String message;
        if (Utils.isEmpty(categoryList)) {
            message = ConstantsAdminENG.LACK_OF_CATEGORY_IN_DB;
        } else {
            message = ConstantsAdminENG.LIST_OF_CATEGORY_ADMIN;
        }

        return new MsgAndListDto<>(message, categoryList);
    }

    public MsgAndListDto<Category> addOrUpdateCategory(final Category category) {
        final Integer categoryId = category.getId();
        final Category categorySaved = categoryRepo.save(category);
        final List<Category> categoryList = categoryRepo.findAll();

        final String message;
        if (categoryId == null && categorySaved != null && categorySaved.getId() != null) {
            message = ConstantsAdminENG.NEW_CATEGORY_WAS_CREATED;
        } else if (categoryId != null && categorySaved != null && categorySaved.getId() != null) {
            message = ConstantsAdminENG.CATEGORY_WAS_UPDATED;
        } else if (categoryId == null && categorySaved == null) {
            message = ConstantsAdminENG.NEW_CATEGORY_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.CATEGORY_WAS_NOT_UPDATED;
        }

        return new MsgAndListDto<>(message, categoryList);
    }

    public MsgAndListDto<Category> getEditCategoryScreen(final int categoryId) {
        final Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
        final Category category = categoryOptional.orElseGet(Category::new);

        return new MsgAndListDto<>(ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_CATEGORY_ADMIN, Collections.singletonList(category));
    }

    public MsgAndListDto<Category> deleteCategory(final int categoryId) {
        final Optional<Category> categoryOptional = categoryRepo.findById(categoryId);

        String message;
        if (categoryOptional.isPresent()) {
            categoryRepo.deleteById(categoryId);
            message = ConstantsAdminENG.CATEGORY_WAS_DELETED;
        } else {
            message = ConstantsAdminENG.COULD_NOT_FIND_CATEGORY_BY_ID + ConstantsAdminENG.DELETED_BY_ANOTHER_CATEGORY_ADMIN;
        }

        final List<Category> categoryList = categoryRepo.findAll();
        // if categories list is empty then message change
        if (Utils.isEmpty(categoryList)) {
            message = ConstantsAdminENG.LACK_OF_CATEGORY_IN_DB + message;
        }

        return new MsgAndListDto<>(message, categoryList);
    }
}
