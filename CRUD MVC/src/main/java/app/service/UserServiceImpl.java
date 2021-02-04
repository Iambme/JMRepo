package app.service;

import app.dao.MyUserDao;
import app.model.User;
import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final MyUserDao myUserDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(MyUserDao myUserDao, PasswordEncoder passwordEncoder) {
        this.myUserDao = myUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public User getMyUser(int id) {
        return myUserDao.getMyUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getMyUserList() {
        return myUserDao.getMyUserList();
    }

    @Transactional
    @Override
    public boolean saveMyUser(User user, Role role) {
        if (user.getUsername().trim().length() == 0 || user.getPassword().trim().length() == 0 || myUserDao.isNotReg(user.getEmail()) ||
                user.getEmail().trim().length() == 0 || user.getLastName().trim().length() == 0) {
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            myUserDao.saveMyUser(user, role);
            return true;
        }
    }

    @Transactional
    @Override
    public boolean updateMyUser(User user, Role role) {
        if (user.getUsername().trim().length() == 0 || user.getPassword().trim().length() == 0 ||
                user.getEmail().trim().length() == 0 || user.getLastName().trim().length() == 0) {
            return false;
        } else {
            myUserDao.updateMyUser(user, role);
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
