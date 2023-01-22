package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.User;
import pl.snowboard4humans.repository.UserRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserAdminService extends SuperService {

  private final UserRepo userRepo;

  @Autowired
  public UserAdminService(final UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public MsgAndListDto<User> getUsers() {
    final List<User> userList = userRepo.findAll();

    final String message;
    if (Utils.isEmpty(userList)) {
      message = ConstantsAdminENG.LACK_OF_USERS_IN_DB;
    } else {
      message = ConstantsAdminENG.LIST_OF_USERS_ADMIN;
    }

    return new MsgAndListDto<>(message, userList);
  }

  public MsgAndListDto<User> addOrUpdateUser(final User user) {
    final Integer userId = user.getId();

    // replace with old password when new password was not filled
    if (Objects.equals(user.getPassword(), ConstantsPL.NULL)
        || user.getPassword().equals(ConstantsPL.EMPTY_MESSAGE)) {

      final Optional<User> userByIdOptional = userRepo.findById(userId);
      final User userById = userByIdOptional.orElse(null);
      final String password = userById != null ? userById.getPassword() : null;
      user.setPassword(password);
    }

    final User userSaved = userRepo.save(user);
    final String message;
    if (userId == null && userSaved != null && userSaved.getId() != null) {
      message = ConstantsAdminENG.NEW_USER_WAS_CREATED;
    } else if (userId != null && userSaved != null && userSaved.getId() != null) {
      message = ConstantsAdminENG.USER_WAS_UPDATED;
    } else if (userId == null && userSaved == null) {
      message = ConstantsAdminENG.NEW_USER_WAS_NOT_CREATED;
    } else {
      message = ConstantsAdminENG.USER_WAS_NOT_UPDATED;
    }

    return new MsgAndListDto<>(message, userRepo.findAll());
  }

  public MsgAndListDto<User> getEditUserScreen(final int userId) {
    final Optional<User> userOptional = userRepo.findById(userId);
    final User user = userOptional.orElseGet(User::new);

    return new MsgAndListDto<>(ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_USER_ADMIN, Collections.singletonList(user));
  }

  public MsgAndListDto<User> deleteUser(final int userId) {
    final Optional<User> userOptional = userRepo.findById(userId);

    String message;
    if (userOptional.isPresent()) {
      userRepo.deleteById(userId);
      message = ConstantsAdminENG.USER_WAS_DELETED;
    } else {
      message = ConstantsAdminENG.COULD_NOT_FIND_USER_BY_ID + ConstantsAdminENG.DELETED_BY_ANOTHER_USER_ADMIN;
    }

    final List<User> userList = userRepo.findAll();

    // if manufacturers list is empty then message change
    if (Utils.isEmpty(userList)) {
      message = ConstantsAdminENG.LACK_OF_USERS_IN_DB;
    }

    return new MsgAndListDto<>(message, userList);
  }
}
