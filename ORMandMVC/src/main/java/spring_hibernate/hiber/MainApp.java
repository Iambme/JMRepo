package spring_hibernate.hiber;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_hibernate.hiber.config.AppConfig;
import spring_hibernate.hiber.model.Car;
import spring_hibernate.hiber.model.User;
import spring_hibernate.hiber.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);




        //Создание 3х Юзеров и Добавление каждому из них по машине
/*
        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");

        Car car1 = new Car("Car1", 82);
        Car car2 = new Car("Car2", 84);
        Car car3 = new Car("Car3", 87);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
*/

        //Вывод информации о юзерах и их машинах
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar().toString());
            System.out.println();
        }
        //получение юзера по названию и серии машины
//
        User user = userService.getUserByCar("Car2",84);
        System.out.println(user.toString());
        context.close();


    }
}
