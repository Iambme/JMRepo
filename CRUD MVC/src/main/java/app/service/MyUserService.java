package app.service;

import app.model.MyUser;
import app.model.Role;
import java.util.List;

public interface MyUserService {
    MyUser getMyUser(int id);

    List<MyUser> getMyUserList();


    void saveMyUser(MyUser myUser, Role role);

    void updateMyUser(MyUser myUser);
    void updateMyUser(MyUser myUser, Role role);


    void deleteMyUser(int id);
}
