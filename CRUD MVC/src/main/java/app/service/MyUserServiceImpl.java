package app.service;

import app.dao.MyUserDao;
import app.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {
    private MyUserDao myUserDao;

    @Autowired
    public void setMyUserDao(MyUserDao myUserDao) {
        this.myUserDao = myUserDao;
    }

    @Override
    public MyUser getMyUser(int id) {
        return myUserDao.getMyUser(id);
    }

    @Override
    public List<MyUser> getMyUserList() {
        return myUserDao.getMyUserList();
    }

    @Override
    public void saveMyUser(MyUser myUser) {
        myUserDao.saveMyUser(myUser);
    }

    @Override
    public void updateMyUser(int id, String name, String login, String password) {
        myUserDao.updateMyUser(id, name, login, password);
    }
    @Override
    public void updateMyUser(MyUser myUser) {
        myUserDao.updateMyUser(myUser);
    }

    @Override
    public void deleteMyUser(int id) {
        myUserDao.deleteMyUser(id);
    }
}
