package spring_hibernate.hiber.service;




import spring_hibernate.hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserByCar(String name,int series);
}
