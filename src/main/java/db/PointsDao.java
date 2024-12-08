package db;

import model.Points;
import javax.persistence.*;
import java.util.List;

public class PointsDao {

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    private final EntityManager em = factory.createEntityManager();

    public void createPoint(Points point) {
        em.getTransaction().begin();
        em.persist(point);
        em.getTransaction().commit();
    }

    public List<Points> getAll(Long userId) {
        Query query = em.createQuery("from Points p where p.user_id = :user_id");
        query.setParameter("user_id", userId);
        return query.getResultList();
    }

    public List<Points> getLastPoints(Long userId, int first, int rows) {
        TypedQuery<Points> query = em.createQuery("SELECT p FROM Points p where p.user_id = :user_id ORDER BY p.id DESC", Points.class);
        query.setParameter("user_id", userId);
        query.setFirstResult(first);
        query.setMaxResults(rows);
        return query.getResultList();
    }

    public Long getCount(Long userId) {
        Query query = em.createQuery("SELECT COUNT(p) FROM Points p where p.user_id = :user_id");
        query.setParameter("user_id", userId);
        return (Long) query.getSingleResult();
    }
}
