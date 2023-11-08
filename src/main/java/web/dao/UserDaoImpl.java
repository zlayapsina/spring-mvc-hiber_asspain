package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceUnit
    private EntityManagerFactory emf;


    @Override
    public List<User> getUsers() {
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        cq.select(cq.from(User.class));
        TypedQuery<User> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public User showId(int id) {
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("id"), id));
        TypedQuery<User> query = em.createQuery(cq);
        System.out.println(query.getSingleResult());
        return query.getSingleResult();
    }

    @Override
    public void saveUser(String name, String surname, int age) {
        EntityManager em = emf.createEntityManager();

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void removeUser(int id) {
        EntityManager em = emf.createEntityManager();

        User deletedUser = em.find(User.class, id);
        em.getTransaction().begin();
        em.remove(deletedUser);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void editUser(int id, User editedUser) {
        EntityManager em = emf.createEntityManager();

        User managedUser = em.find(User.class, id);
        managedUser.setName(editedUser.getName());
        managedUser.setSurname(editedUser.getSurname());
        managedUser.setAge(editedUser.getAge());

        em.getTransaction().begin();
        em.merge(managedUser);
        em.getTransaction().commit();

        em.close();
    }
}
