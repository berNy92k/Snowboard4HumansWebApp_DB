package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.model.User;
import pl.snowboard4humans.repository.UserRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminServices extends SuperService {
    private UserRepo userRepo;

    @Autowired
    public UserAdminServices(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String getUsers(Model model) {
        String message = ConstantsAdminENG.LIST_OF_USERS_ADMIN;

        List<User> userList = userRepo.findAll();

        // if manufacturers list is empty then message change
        if (Utils.isEmpty(userList)) {
            message = ConstantsAdminENG.LACK_OF_USERS_IN_DB;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.USER_LIST_OBJECT,
                userList,
                ConstantsAdminENG.USER_LIST_URL);
    }

    public String getAddNewUserScreen(Model model) {
        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_USER_ADMIN,
                ConstantsAdminENG.USER_OBJECT,
                new User(),
                ConstantsAdminENG.USER_CREATE_URL);
    }

    public String addOrUpdateUser(Model model,
                                  User user) {
        String message;

        Integer userId = user.getId();

        // replace with old password when new password was not filled
        if (user.getPassword() == ConstantsPL.NULL
                || user.getPassword().equals(ConstantsPL.EMPTY_MESSAGE)) {

            Optional<User> userByIdOptional = userRepo.findById(userId);
            User userById = userByIdOptional.orElse(null);
            String password = userById != null ? userById.getPassword() : null;
            user.setPassword(password);
        }

        User userSaved = userRepo.save(user);

        List<User> userList = userRepo.findAll();
        if (userId == null && userSaved != null && userSaved.getId() != null) {
            message = ConstantsAdminENG.NEW_USER_WAS_CREATED;
        } else if (userId != null && userSaved != null && userSaved.getId() != null) {
            message = ConstantsAdminENG.USER_WAS_UPDATED;
        } else if (userId == null && userSaved == null) {
            message = ConstantsAdminENG.NEW_USER_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.USER_WAS_NOT_UPDATED;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.USER_LIST_OBJECT,
                userList,
                ConstantsAdminENG.USER_LIST_URL);
    }

    public String getEditUserScreen(Model model,
                                    int userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        User user = userOptional.orElseGet(User::new);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_USER_ADMIN,
                ConstantsAdminENG.USER_OBJECT,
                user,
                ConstantsAdminENG.USER_CREATE_URL);
    }
}
