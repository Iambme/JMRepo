package app.service;

import app.model.User;
import app.model.Role;

import java.util.List;

public interface UserService {
    User getMyUser(int id);

    List<User> getMyUserList();

    boolean saveMyUser(User user, String roleName);

    boolean updateMyUser(User user, String roleName);

    void deleteMyUser(int id);

    String ifPasswordNull(int id, String password);

    boolean isNotReg(String email);

    boolean saveMyUser(User user);

}
