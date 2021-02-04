package app.dao;

import app.model.MyUser;

import java.util.List;

public interface MyUserDao {
    MyUser getMyUser(int id);

    List<MyUser> getMyUserList();

    void saveMyUser(MyUser myUser);

    void updateMyUser(MyUser myUser);

    void deleteMyUser(int id);
}
