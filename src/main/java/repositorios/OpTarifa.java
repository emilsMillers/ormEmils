/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

/**
 *
 * @author emils
 */
import entidades.Tarifa;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OpTarifa {

    private final SessionFactory sessionFactory;

    public OpTarifa(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Tarifa> obtenerTodasLasTarifas() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Tarifa";
            Query<Tarifa> query = session.createQuery(hql, Tarifa.class);
            return query.list();
        }
    }
    
    public void guardarTarifa(Tarifa tarifa) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(tarifa);
            transaction.commit();
        }
    }

    public Tarifa obtenerTarifaPorId(int tarifaId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Tarifa.class, tarifaId);
        }
    }

    public void actualizarTarifa(Tarifa tarifa) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(tarifa);
            transaction.commit();
        }
    }

    public void eliminarTarifa(int tarifaId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            
            Tarifa tarifa = session.get(Tarifa.class, tarifaId);

            if (tarifa != null) {
                session.delete(tarifa);
                transaction.commit();
            }
        }
    }
}
