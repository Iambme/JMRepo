package app.dao;

import app.model.MyUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class MyUserDaoImpl implements MyUserDao {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory)
    {
        this.entityManagerFactory = entityManagerFactory;
        entityManager=entityManagerFactory.createEntityManager();
    }


//    private SessionFactory sessionFactory;
//
//
//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public MyUser getMyUser(int id) {
        MyUser myUser = (MyUser)entityManager.find(MyUser.class, id);
        return myUser;
//        Query query = sessionFactory.getCurrentSession().createQuery("from MyUser where id =: id");
//
//        query.setParameter("id", id);
//
//        return (MyUser) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MyUser> getMyUserList() {
//        TypedQuery<MyUser> query = sessionFactory.getCurrentSession().createQuery("from MyUser");
//        return query.getResultList();
        return entityManager.createQuery("from MyUser").getResultList();
    }

    @Override
    public void saveMyUser(MyUser myUser) {
        //sessionFactory.getCurrentSession().save(myUser);
        entityManager.getTransaction().begin();
        entityManager.persist(myUser);
        entityManager.getTransaction().commit();

    }

    @Override
    public void updateMyUser(int id, String name, String login, String password) {
//        Query query = sessionFactory.getCurrentSession().createQuery("update MyUser set name = :nameParam, login = :loginParam" +
//                ", password = :passwordParam" + " where id = : id");

        MyUser myUser = getMyUser(id);
        myUser.setName(name);
        myUser.setLogin(login);
        myUser.setPassword(password);

        entityManager.getTransaction().begin();
        entityManager.persist(myUser);
        entityManager.getTransaction().commit();



    }

    @Override
    public void updateMyUser(MyUser myUser) {
//        Query query = sessionFactory.getCurrentSession().createQuery("update MyUser set name = :nameParam, login = :loginParam" +
//                ", password = :passwordParam" + " where id = : id");


        entityManager.getTransaction().begin();
        entityManager.merge(myUser);
        entityManager.getTransaction().commit();



    }

    @Override
    public void deleteMyUser(int id) {
//        Query query = sessionFactory.getCurrentSession().createQuery("delete from MyUser where id =: id");
//        query.setParameter("id", id);
//        query.executeUpdate();
        MyUser myUser = getMyUser(id);
        entityManager.getTransaction().begin();
        entityManager.remove(myUser);
        entityManager.getTransaction().commit();
    }
}
