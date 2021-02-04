package app.dao;

import app.model.MyUser;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class MyUserDaoImpl implements MyUserDao {

    @PersistenceContext
    private EntityManager entityManager;
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
        entityManager.persist(myUser);
    }



    @Override
    public void updateMyUser(MyUser myUser) {
        entityManager.merge(myUser);
    }

    @Override
    public void deleteMyUser(int id) {
        MyUser myUser = getMyUser(id);
        entityManager.remove(myUser);
    }
}
