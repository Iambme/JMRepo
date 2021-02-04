package app.dao;

import app.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class MyUserDaoImpl implements MyUserDao {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public MyUser getMyUser(int id) {
        MyUser myUser = (MyUser) entityManager.find(MyUser.class, id);
        return myUser;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MyUser> getMyUserList() {
        return entityManager.createQuery("from MyUser").getResultList();
    }

    @Override
    public void saveMyUser(MyUser myUser) {
        entityManager.getTransaction().begin();
        entityManager.persist(myUser);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateMyUser(int id, String name, String login, String password) {
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
        entityManager.getTransaction().begin();
        entityManager.merge(myUser);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteMyUser(int id) {
        MyUser myUser = getMyUser(id);
        entityManager.getTransaction().begin();
        entityManager.remove(myUser);
        entityManager.getTransaction().commit();
    }
}
