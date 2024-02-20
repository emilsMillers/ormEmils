/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

/**
 *
 * @author emils
 */
import entidades.Pase;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

public class OpPases {

    private final SessionFactory sessionFactory;

    public OpPases(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Pase> obtenerTodosLosPases() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Pase";
            Query<Pase> query = session.createQuery(hql, Pase.class);
            return query.list();
        }
    }

    public void guardarPase(Pase pase) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(pase);
            transaction.commit();
        }
    }

    public Pase obtenerPasePorId(int paseId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Pase.class, paseId);
        }
    }

    public void actualizarPase(Pase pase) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(pase);
            transaction.commit();
        }
    }

    public void eliminarPase(int paseId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            
            Pase pase = session.get(Pase.class, paseId);

            if (pase != null) {
                session.delete(pase);
                transaction.commit();
            }
        }
    }
}
