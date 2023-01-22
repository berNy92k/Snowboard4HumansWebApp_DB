package pl.snowboard4humans.controller.admin.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Category;
import pl.snowboard4humans.service.admin.CategoryAdminService;

@Controller
@RequestMapping(value = "/admin/category")
public class CategoryAdminController extends SuperController {

  private final CategoryAdminService categoryAdminService;

  @Autowired
  public CategoryAdminController(final CategoryAdminService categoryAdminService) {
    this.categoryAdminService = categoryAdminService;
  }

  @GetMapping
  public String getCategories(final Model model) {
    final MsgAndListDto<Category> categories = categoryAdminService.getCategories();

    return getRequestDispatcherWithDefaultMessage(model,
        categories.getMessage(),
        ConstantsAdminENG.CATEGORY_LIST_OBJECT,
        categories.getListOfElements(),
        ConstantsAdminENG.CATEGORY_LIST_URL);
  }

  @PostMapping
  public String addCategory(final Model model,
                            @ModelAttribute final Category category) {
    final MsgAndListDto<Category> categories = categoryAdminService.addOrUpdateCategory(category);

    return getRequestDispatcherWithDefaultMessage(model,
        categories.getMessage(),
        ConstantsAdminENG.CATEGORY_LIST_OBJECT,
        categories.getListOfElements(),
        ConstantsAdminENG.CATEGORY_LIST_URL);
  }

  @GetMapping(value = "deleteCategory")
  public String deleteCategory(@RequestParam(name = "id") final int categoryId,
                               final Model model) {
    final MsgAndListDto<Category> categories = categoryAdminService.deleteCategory(categoryId);

    return getRequestDispatcherWithDefaultMessage(model,
        categories.getMessage(),
        ConstantsAdminENG.CATEGORY_LIST_OBJECT,
        categories.getListOfElements(),
        ConstantsAdminENG.CATEGORY_LIST_URL);
  }

  @GetMapping(value = "category_create")
  public String addNewCategoryScreen(final Model model) {

    return getRequestDispatcherWithDefaultMessage(model,
        ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_CATEGORY_ADMIN,
        ConstantsAdminENG.CATEGORY_OBJECT,
        new Category(),
        ConstantsAdminENG.CATEGORY_CREATE_OR_UPDATE_URL);
  }

  @GetMapping(value = "editCategory")
  public String editCategoryScreen(final Model model,
                                   @RequestParam(value = "id") final int categoryId) {
    final MsgAndListDto<Category> categories = categoryAdminService.getEditCategoryScreen(categoryId);

    return getRequestDispatcherWithDefaultMessage(model,
        categories.getMessage(),
        ConstantsAdminENG.CATEGORY_OBJECT,
        categories.getListOfElements().get(0),
        ConstantsAdminENG.CATEGORY_CREATE_OR_UPDATE_URL);
  }

}
