package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getUsers() {
        Query query = em.createQuery("from User ");
        return query.getResultList();
    }

    @Override
    public User showId(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
       em.persist(user);
    }

    @Override
    public void removeUser(long id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public void editUser(User editedUser) {
        em.merge(editedUser);
    }
}
