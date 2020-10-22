package app.dao;

import app.model.User;
import app.model.Role;


import java.util.List;

public interface MyUserDao {
    User getMyUser(int id);

    List<User> getMyUserList();

    void saveMyUser(User user, Role role);

    boolean isNotReg(String email);

    void updateMyUser(User user, Role role);

    void deleteMyUser(int id);

    User getUserByName(String s);
}
