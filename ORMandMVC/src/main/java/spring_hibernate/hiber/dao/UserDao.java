package spring_hibernate.hiber.dao;




import spring_hibernate.hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   User getByCar(String name,int series);
}
