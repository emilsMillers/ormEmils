/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

/**
 *
 * @author emils
 */
import entidades.Cine;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OpCines {

    private final SessionFactory sessionFactory;

    public OpCines(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Cine> obtenerTodosLosCines() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Cine";
            Query<Cine> query = session.createQuery(hql, Cine.class);
            return query.list();
        }
    }
    
    public void guardarCine(Cine cine) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(cine);
            transaction.commit();
        }
    }

    public Cine obtenerCinePorId(int cineId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cine.class, cineId);
        }
    }

    public void actualizarCine(Cine cine) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(cine);
            transaction.commit();
        }
    }

    public void eliminarCine(int cineId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Cine cine = session.get(Cine.class, cineId);

            if (cine != null) {
                session.delete(cine);
                transaction.commit();
            }
        }
    }
}