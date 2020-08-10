package pl.snowboard4humans.controller.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.model.Category;
import pl.snowboard4humans.service.admin.CategoryAdminServices;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryAdminController {

    private CategoryAdminServices categoryAdminServices;

    @Autowired
    public CategoryAdminController(CategoryAdminServices categoryAdminServices) {
        this.categoryAdminServices = categoryAdminServices;
    }

    @GetMapping
    public String getCategories(Model model) {
        return categoryAdminServices.getCategories(model);
    }

    @PostMapping
    public String addCategory(Model model,
                              @ModelAttribute Category category) {
        return categoryAdminServices.addOrUpdateCategory(model, category);
    }

    @GetMapping(value = "category_create")
    public String addNewCategoryScreen(Model model) {
        return categoryAdminServices.getAddNewCategoryScreen(model);
    }

    @GetMapping(value = "editCategory")
    public String editCategoryScreen(Model model,
                                     @RequestParam(value = "id") int categoryId) {
        return categoryAdminServices.getEditCategoryScreen(model, categoryId);
    }

}
