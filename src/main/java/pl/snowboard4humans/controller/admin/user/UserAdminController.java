package pl.snowboard4humans.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.User;
import pl.snowboard4humans.service.admin.UserAdminService;

@Controller
@RequestMapping(value = "/admin/user")
public class UserAdminController extends SuperController {

    private final UserAdminService userAdminService;

    @Autowired
    public UserAdminController(final UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @GetMapping
    public String getUsers(final Model model) {
        final MsgAndListDto<User> users = userAdminService.getUsers();

        return getRequestDispatcherWithDefaultMessage(model,
                users.getMessage(),
                ConstantsAdminENG.USER_LIST_OBJECT,
                users.getListOfElements(),
                ConstantsAdminENG.USER_LIST_URL);
    }

    @PostMapping
    public String addUser(final Model model,
                          @ModelAttribute final User user) {
        final MsgAndListDto<User> users = userAdminService.addOrUpdateUser(user);

        return getRequestDispatcherWithDefaultMessage(model,
                users.getMessage(),
                ConstantsAdminENG.USER_LIST_OBJECT,
                users.getListOfElements(),
                ConstantsAdminENG.USER_LIST_URL);
    }

    @GetMapping(value = "user_create")
    public String addNewUserScreen(final Model model) {

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_USER_ADMIN,
                ConstantsAdminENG.USER_OBJECT,
                new User(),
                ConstantsAdminENG.USER_CREATE_URL);
    }

    @GetMapping(value = "editUser")
    public String editUserScreen(final Model model,
                                 @RequestParam(value = "id") final int userId) {
        final MsgAndListDto<User> users = userAdminService.getEditUserScreen(userId);

        return getRequestDispatcherWithDefaultMessage(model,
                users.getMessage(),
                ConstantsAdminENG.USER_OBJECT,
                users.getListOfElements().get(0),
                ConstantsAdminENG.USER_CREATE_URL);
    }

    @GetMapping(value = "deleteUser")
    public String deleteUser(final Model model,
                             @RequestParam(name = "id") final int userId) {
        final MsgAndListDto<User> users = userAdminService.deleteUser(userId);

        return getRequestDispatcherWithDefaultMessage(model,
                users.getMessage(),
                ConstantsAdminENG.USER_LIST_OBJECT,
                users.getListOfElements(),
                ConstantsAdminENG.USER_LIST_URL);
    }

}
