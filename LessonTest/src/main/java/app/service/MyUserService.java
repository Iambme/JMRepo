package app.service;

import app.model.MyUser;

import java.util.List;

public interface MyUserService {
    MyUser getMyUser(int id);

    List<MyUser> getMyUserList();

    void saveMyUser(MyUser myUser);

    void updateMyUser(int id, String name, String login, String password);
    void updateMyUser(MyUser myUser);

    void deleteMyUser(int id);
}
