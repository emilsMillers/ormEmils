/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

/**
 *
 * @author emils
 */
import entidades.Horario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OpHorario {

    private final SessionFactory sessionFactory;

    public OpHorario(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Horario> obtenerTodosLosHorarios() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Horario";
            Query<Horario> query = session.createQuery(hql, Horario.class);
            return query.list();
        }
    }
    
    public void guardarHorario(Horario horario) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(horario);
            transaction.commit();
        }
    }

    public Horario obtenerHorarioPorId(int horarioId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Horario.class, horarioId);
        }
    }

    public void actualizarHorario(Horario horario) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(horario);
            transaction.commit();
        }
    }

    public void eliminarHorario(int horarioId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            
            Horario horario = session.get(Horario.class, horarioId);

            if (horario != null) {
                session.delete(horario);
                transaction.commit();
            }
        }
    }
}
