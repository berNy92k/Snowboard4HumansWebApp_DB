package pl.snowboard4humans.controller.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.model.User;
import pl.snowboard4humans.service.admin.UserAdminServices;

@Controller
@RequestMapping(value = "/admin/user")
public class UserAdminController {

    private UserAdminServices userAdminServices;

    @Autowired
    public UserAdminController(UserAdminServices userAdminServices) {
        this.userAdminServices = userAdminServices;
    }

    @GetMapping
    public String getUsers(Model model) {
        return userAdminServices.getUsers(model);
    }

    @PostMapping
    public String addUser(Model model,
                          @ModelAttribute User user) {
        return userAdminServices.addOrUpdateUser(model, user);
    }

    @GetMapping(value = "user_create")
    public String addNewUserScreen(Model model) {
        return userAdminServices.getAddNewUserScreen(model);
    }

    @GetMapping(value = "editUser")
    public String editUserScreen(Model model,
                                 @RequestParam(value = "id") int userId) {
        return userAdminServices.getEditUserScreen(model, userId);
    }

}
