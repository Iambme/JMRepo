package app.dao;

import app.model.User;
import app.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class MyUserDaoImpl implements MyUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getMyUser(int id) {
        User user = (User) entityManager.find(User.class, id);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getMyUserList() {
        return entityManager.createQuery("from User").getResultList();
    }


    @Override
    public void saveMyUser(User user, Role role) {
        if (role.getRole() != null) {
            role = getRole(role);
            user.addRole(role);
        }
        entityManager.persist(user);
    }

    @Override
    public boolean isNotReg(String email) {
        return getMyUserList()
                .stream()
                .anyMatch((e) -> e.getEmail().hashCode() == email.hashCode());
    }

    @Override
    public void updateMyUser(User user, Role role) {
        if (role.getRole() != null) {
            user = getMyUser(user.getId());
            role = getRole(role);
            user.addRole(role);
        }
        entityManager.merge(user);
    }

    @Override
    public void deleteMyUser(int id) {
        User user = getMyUser(id);
        entityManager.remove(user);
    }

    @Override
    public User getUserByName(String email) {
        TypedQuery<User> query = entityManager.createQuery("SELECT c FROM User c WHERE c.email = :email", User.class);
        return query.setParameter("email", email).getSingleResult();
    }
    private Role getRole(Role role){
        TypedQuery<Role> query2 = entityManager.createQuery("SELECT c FROM Role c WHERE c.role = :role", Role.class);
        return query2.setParameter("role", role.getRole()).getSingleResult();
    }
}
