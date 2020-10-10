package app.service;

import app.dao.MyUserDao;
import app.model.MyUser;
import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    private MyUserDao myUserDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MyUserServiceImpl(MyUserDao myUserDao, PasswordEncoder passwordEncoder) {
        this.myUserDao = myUserDao;
        this.passwordEncoder = passwordEncoder;
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
    public boolean saveMyUser(MyUser myUser, Role role) {
        if (myUser.getUsername().trim().length() == 0 || myUser.getPassword().trim().length() == 0 || myUserDao.isNotReg(myUser.getEmail()) ||
                myUser.getEmail().trim().length() == 0 || myUser.getLastName().trim().length() == 0) {
            return false;
        } else {
            myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
            myUserDao.saveMyUser(myUser, role);
            return true;
        }
    }

    @Transactional
    @Override
    public boolean updateMyUser(MyUser myUser, Role role) {
        if (myUser.getUsername().trim().length() == 0 || myUser.getPassword().trim().length() == 0 ||
                myUser.getEmail().trim().length() == 0 || myUser.getLastName().trim().length() == 0) {
            return false;
        } else {
            myUserDao.updateMyUser(myUser, role);
            return true;
        }
    }

    @Transactional
    @Override
    public void deleteMyUser(int id) {
        myUserDao.deleteMyUser(id);
    }

    @Override
    public String ifPasswordNull(int id, String password) {
        if (password.trim().length() == 0) {
            return myUserDao.getMyUser(id).getPassword();
        } else {
            return passwordEncoder.encode(password);
        }
    }

}
