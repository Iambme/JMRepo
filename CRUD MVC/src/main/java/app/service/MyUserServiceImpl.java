package app.service;

import app.dao.MyUserDao;
import app.model.MyUser;
import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    private MyUserDao myUserDao;

    @Autowired
    public void setMyUserDao(MyUserDao myUserDao) {
        this.myUserDao = myUserDao;
    }

    @Transactional(readOnly = true)
    @Override
    public MyUser getMyUser(int id) {
        return myUserDao.getMyUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MyUser> getMyUserList() {
        return myUserDao.getMyUserList();
    }


    @Transactional
    @Override
    public void saveMyUser(MyUser myUser, Role role) {
        myUserDao.saveMyUser(myUser,role);
    }

    @Transactional
    @Override
    public void updateMyUser(MyUser myUser) {
        myUserDao.updateMyUser(myUser);
    }

    @Transactional
    @Override
    public void updateMyUser(MyUser myUser, Role role) {
        myUserDao.updateMyUser(myUser,role);
    }

    @Transactional
    @Override
    public void deleteMyUser(int id) {
        myUserDao.deleteMyUser(id);
    }
}
