package app.service;

import app.model.MyUser;
import app.model.Role;

import java.util.List;

public interface MyUserService {
    MyUser getMyUser(int id);

    List<MyUser> getMyUserList();

    boolean saveMyUser(MyUser myUser,Role role);

    boolean updateMyUser(MyUser myUser,Role role);

    void deleteMyUser(int id);

    String ifPasswordNull(int id, String password);
}
