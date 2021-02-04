package spring_hibernate.hiber.dao;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_hibernate.hiber.model.Car;
import spring_hibernate.hiber.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getByCar(String name, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Car where name=:name and series=:series");
        query.setParameter("name", name);
        query.setParameter("series", series);
        Car car = (Car) query.uniqueResult();
        query = sessionFactory.getCurrentSession().createQuery("from User where car=:car");
        query.setParameter("car", car);
        User user = (User) query.uniqueResult();
        return user;
    }

}
