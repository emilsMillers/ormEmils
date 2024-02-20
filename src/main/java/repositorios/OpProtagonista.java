/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

/**
 *
 * @author emils
 */
import entidades.Protagonista;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OpProtagonista {

    private final SessionFactory sessionFactory;

    public OpProtagonista(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Protagonista> obtenerTodosLosProtagonistas() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Protagonista";
            Query<Protagonista> query = session.createQuery(hql, Protagonista.class);
            return query.list();
        }
    }

    public void guardarProtagonista(Protagonista protagonista) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(protagonista);
            transaction.commit();
        }
    }

    public Protagonista obtenerProtagonistaPorId(int protagonistaId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Protagonista.class, protagonistaId);
        }
    }

    public void actualizarProtagonista(Protagonista protagonista) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(protagonista);
            transaction.commit();
        }
    }

    public void eliminarProtagonista(int protagonistaId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Protagonista protagonista = session.get(Protagonista.class, protagonistaId);

            if (protagonista != null) {
                session.delete(protagonista);
                transaction.commit();
            }
        }
    }
}
