package app.dao;

import app.model.MyUser;
import app.model.Role;
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
    public void saveMyUser(MyUser myUser, Role role) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT c FROM Role c WHERE c.role = :role", Role.class);
        Role manegedRole = query.setParameter("role", role.getRole()).getSingleResult();
        myUser.addRole(manegedRole);
        entityManager.persist(myUser);
    }


    @Override
    public void updateMyUser(MyUser myUser) {
        entityManager.merge(myUser);
    }

    public void updateMyUser(MyUser myUser, Role role) {
        TypedQuery<Role> query = entityManager.createQuery("SELECT c FROM Role c WHERE c.role = :role", Role.class);
        Role manegedRole = query.setParameter("role", role.getRole()).getSingleResult();
        myUser.addRole(manegedRole);
        entityManager.merge(myUser);
    }

    @Override
    public void deleteMyUser(int id) {
        MyUser myUser = getMyUser(id);
        entityManager.remove(myUser);
    }

    @Override
    public MyUser getUserByName(String login) {
        TypedQuery<MyUser> query = entityManager.createQuery("SELECT c FROM MyUser c WHERE c.login = :login", MyUser.class);
        MyUser myUser = query.setParameter("login", login).getSingleResult();
        System.out.println(myUser);
        return myUser;
    }
}
