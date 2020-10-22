package app.service;

import app.model.User;
import app.model.Role;

import java.util.List;

public interface UserService {
    User getMyUser(int id);

    List<User> getMyUserList();

    boolean saveMyUser(User user, Role role);

    boolean updateMyUser(User user, Role role);

    void deleteMyUser(int id);

    String ifPasswordNull(int id, String password);
}
