package app.dao;

import app.model.MyUser;

import java.util.List;

public interface MyUserDao {
    MyUser getMyUser(int id);

    List<MyUser> getMyUserList();

    void saveMyUser(MyUser myUser);

    void updateMyUser(int id, String name, String login, String password);

    void deleteMyUser(int id);
}
