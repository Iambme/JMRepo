package app.service;


import app.model.User;
import app.model.Role;
import app.repository.RoleRepo;
import app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepo, RoleRepo roleRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public User getMyUser(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getMyUserList() {
        return (List<User>) userRepo.findAll();
    }

    @Transactional
    @Override
    public boolean saveMyUser(User user, String roleName) {
        if (user.getUsername().trim().length() == 0 || user.getPassword().trim().length() == 0 || isNotReg(user.getEmail()) ||
                user.getEmail().trim().length() == 0 || user.getLastName().trim().length() == 0) {
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (roleName != null) {
                Role role = roleRepo.findByName(roleName);
                user.addRole(role);
            }
            userRepo.save(user);
            return true;
        }
    }

    @Transactional
    @Override
    public boolean updateMyUser(User user, String roleName) {
        if (user.getUsername().trim().length() == 0 || user.getPassword().trim().length() == 0 ||
                user.getEmail().trim().length() == 0 || user.getLastName().trim().length() == 0) {
            return false;
        } else {
            if (roleName != null) {
                User oldUser = getMyUser(user.getId());
                Role oldRole = oldUser.getRoles().stream().findFirst().get();
                Role role = roleRepo.findByName(roleName);
                user.setRoles(new HashSet<Role>());
                user.addRole(oldRole);
                user.addRole(role);
            }
            userRepo.save(user);
            return true;
        }
    }


    @Transactional
    @Override
    public void deleteMyUser(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public String ifPasswordNull(int id, String password) {
        if (password.trim().length() == 0) {
            return userRepo.findById(id).get().getPassword();
        } else {
            return passwordEncoder.encode(password);
        }
    }

    @Override
    public boolean isNotReg(String email) {
        return getMyUserList()
                .stream()
                .anyMatch((e) -> e.getEmail().hashCode() == email.hashCode());
    }

    @Transactional
    @Override
    public boolean saveMyUser(User user) {
        if (user.getUsername().trim().length() == 0 || user.getPassword().trim().length() == 0 || isNotReg(user.getEmail()) ||
                user.getEmail().trim().length() == 0 || user.getLastName().trim().length() == 0) {
            return false;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            String roleName = user.getRoles().stream().findFirst().get().getName();
            Role role = roleRepo.findByName(roleName);
            user.setRoles(new HashSet<Role>());
            user.addRole(role);
            userRepo.save(user);
            return true;
        }
    }

}
