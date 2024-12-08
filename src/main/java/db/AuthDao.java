package db;

import model.Users;
import utils.Hash;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AuthDao {

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    private final EntityManager em = factory.createEntityManager();

    public Long hasInTable(String login, String password) {
        Query query = em.createQuery("SELECT id FROM Users u WHERE u.login = :login AND u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", Hash.SHA(password));
        List<Long> ids = query.getResultList();
        if (ids.isEmpty()) {
            return null;
        } else {
            return ids.get(0);
        }
    }

    public void createUser(Users user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
