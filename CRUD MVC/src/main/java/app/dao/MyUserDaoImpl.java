package app.dao;

import app.model.MyUser;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MyUserDaoImpl implements MyUserDao {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MyUser getMyUser(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from MyUser where id =: id");
        query.setParameter("id", id);

        return (MyUser) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MyUser> getMyUserList() {
        TypedQuery<MyUser> query = sessionFactory.getCurrentSession().createQuery("from MyUser");
        return query.getResultList();
    }

    @Override
    public void saveMyUser(MyUser myUser) {
        sessionFactory.getCurrentSession().save(myUser);
    }

    @Override
    public void updateMyUser(int id, String name, String login, String password) {
        Query query = sessionFactory.getCurrentSession().createQuery("update MyUser set name = :nameParam, login = :loginParam" +
                ", password = :passwordParam" + " where id = : id");

        query.setParameter("id", id);
        query.setParameter("nameParam", name);
        query.setParameter("loginParam", login);
        query.setParameter("passwordParam", password);

        query.executeUpdate();
    }

    @Override
    public void deleteMyUser(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from MyUser where id =: id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
