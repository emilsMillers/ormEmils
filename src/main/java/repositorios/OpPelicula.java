/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import entidades.Pelicula;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author emils
 */
public class OpPelicula {
    private final SessionFactory sessionFactory;

    public OpPelicula(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Pelicula> obtenerTodasLasPeliculas() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Pelicula";
            //String join = "FROM Pase p JOIN p.cine c where c.nombre = 'Meridiano' ";
            //Query<Pase> queryP = session.createQuery(hql, Pase.class).list().forEach(it -> it.getCine());
            Query<Pelicula> query = session.createQuery(hql, Pelicula.class);
            return query.list();
        }
    }

    public void guardarPelicula(Pelicula pelicula) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(pelicula);
            transaction.commit();
        }
    }

    public Pelicula obtenerPeliculaPorId(int peliculaId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Pelicula.class, peliculaId);
        }
    }

    public void actualizarPelicula(Pelicula pelicula) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(pelicula);
            transaction.commit();
        }
    }

    public void eliminarPelicula(int peliculaId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Pelicula pelicula = session.get(Pelicula.class, peliculaId);

            if (pelicula != null) {
                session.delete(pelicula);
                transaction.commit();
            }
        }
    }
}
