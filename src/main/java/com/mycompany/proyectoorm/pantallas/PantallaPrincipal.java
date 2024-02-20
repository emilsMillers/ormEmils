package com.mycompany.proyectoorm.pantallas;

import entidades.Cine;
import entidades.Horario;
import entidades.Pase;
import entidades.Pelicula;
import entidades.Protagonista;
import entidades.Tarifa;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import repositorios.OpCines;
import repositorios.OpHorario;
import repositorios.OpPases;
import repositorios.OpPelicula;
import repositorios.OpProtagonista;
import repositorios.OpTarifa;

/**
 *
 * @author 2damb
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
    final SessionFactory sessionFactory = new MetadataSources(registro).buildMetadata().buildSessionFactory();
    OpPelicula rPelicula = new OpPelicula(sessionFactory);
    OpCines rCine = new OpCines(sessionFactory);
    OpPases rPase = new OpPases(sessionFactory);
    OpTarifa rTarifa = new OpTarifa(sessionFactory);
    OpProtagonista rProtagonista = new OpProtagonista(sessionFactory);
    OpHorario rHorario = new OpHorario(sessionFactory);

    /**
     * Creates new form PantallaPrincipal
     */
    public PantallaPrincipal() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    private void cargarDatosBase() {
    Session sesion = sessionFactory.openSession();
    sesion.beginTransaction();

    try {
       Pelicula pelicula1 = new Pelicula(0, "La luna Mágica", "Bon Jovi", null, null, "Animacion", "PG-13");
        Pelicula pelicula2 = new Pelicula(0, "El durazno", "Martin Scorsese", null, null, "Animacion", "PG-13");
        Pelicula pelicula3 = new Pelicula(0, "Cristiano Ronaldo", "Cristiano Ronaldo", null, null, "Accion", "NC-17");
        Pelicula pelicula4 = new Pelicula(0, "La magica noche", "Bon Jovi", null, null, "Ciencia Ficcion", "R");
        Pelicula pelicula5 = new Pelicula(0, "Cristiano Ronaldo", "Cristiano Ronaldo", null, null, "Accion", "PG");
        Pelicula pelicula6 = new Pelicula(0, "La noche estrellada", "Rafita", null, null, "Amor", "G");

        sesion.save(pelicula1);
        sesion.save(pelicula2);
        sesion.save(pelicula3);
        sesion.save(pelicula4);
        sesion.save(pelicula5);
        sesion.save(pelicula6);

        Cine cine1 = new Cine(1,"Multicines Tenerife", "Las Palmitas", "564345432", null, null);
        Cine cine2 = new Cine(2,"Yelmo Meridiano", "Ofra", "234345456", null, null);
        Cine cine3 = new Cine(3,"Yelmo La Villa", "San Isidro", "846353221", null, null);
        Cine cine4 = new Cine(4,"X-Sur Cine", "Roque del Conde", "867546543", null, null);
        Cine cine5 = new Cine(5,"Cine Zentral Center", "Fañabe", "660122340", null, null);
        Cine cine6 = new Cine(6,"Cine Price Prime", "La Caleta", "660122544", null, null);

        sesion.save(cine1);
        sesion.save(cine2);
        sesion.save(cine3);
        sesion.save(cine4);
        sesion.save(cine5);
        sesion.save(cine6);
        
        Pase pase1 = new Pase(0, cine1, pelicula1, null);
        Pase pase2 = new Pase(0, cine2, pelicula2, null);
        Pase pase3 = new Pase(0, cine3, pelicula3, null);
        Pase pase4 = new Pase(0, cine4, pelicula4, null);
        Pase pase5 = new Pase(0, cine5, pelicula5, null);
        Pase pase6 = new Pase(0, cine6, pelicula6, null);

        sesion.save(pase1);
        sesion.save(pase2);
        sesion.save(pase3);
        sesion.save(pase4);
        sesion.save(pase5);
        sesion.save(pase6);

        Horario horario1 = new Horario(1, pase2, LocalTime.of(10, 0));
        Horario horario2 = new Horario(2, pase2, LocalTime.of(13, 0));
        Horario horario3 = new Horario(3, pase3, LocalTime.of(16, 0));
        Horario horario4 = new Horario(4, pase1, LocalTime.of(19, 0));

        sesion.save(horario1);
        sesion.save(horario2);
        sesion.save(horario3);
        sesion.save(horario4);

        Protagonista protagonista1 = new Protagonista(1, "Rafael Mutilado", pelicula1);
        Protagonista protagonista2 = new Protagonista(2, "Beyonce", pelicula2);
        Protagonista protagonista3 = new Protagonista(3, "Cristiano Ronaldo", pelicula3);
        Protagonista protagonista4 = new Protagonista(4, "Rafa Mora", pelicula5);
        Protagonista protagonista5 = new Protagonista(5, "Gundogan", pelicula6);

        sesion.save(protagonista1);
        sesion.save(protagonista2);
        sesion.save(protagonista3);
        sesion.save(protagonista4);
        sesion.save(protagonista5);

        Tarifa tarifa1 = new Tarifa(1, cine1, 10.0, 8.0, 12.0, 6.0);
        Tarifa tarifa2 = new Tarifa(2, cine2, 9.0, 7.0, 11.0, 5.0);
        Tarifa tarifa3 = new Tarifa(3, cine3, 11.0, 9.0, 13.0, 7.0);
        Tarifa tarifa4 = new Tarifa(4, cine4, 12.0, 10.0, 14.0, 8.0);
        Tarifa tarifa5 = new Tarifa(5, cine5, 10.0, 8.0, 12.0, 6.0);
        Tarifa tarifa6 = new Tarifa(6, cine6, 9.0, 7.0, 11.0, 5.0);

        sesion.save(tarifa1);
        sesion.save(tarifa2);
        sesion.save(tarifa3);
        sesion.save(tarifa4);
        sesion.save(tarifa5);
        sesion.save(tarifa6);
        
        sesion.getTransaction().commit();
        cargarDatosEnTabla();
    } catch (Exception e) {
        e.printStackTrace();
        sesion.getTransaction().rollback();
    } finally {
        sesion.close();
    }
}
    private void cargarDatosEnTabla() {
    Session sesion = sessionFactory.openSession();
    try {
        List<Pelicula> peliculas = sesion.createQuery("FROM Pelicula", Pelicula.class).getResultList();
        DefaultTableModel modeloPelicula = new DefaultTableModel();
        modeloPelicula.addColumn("ID");
        modeloPelicula.addColumn("Titulo");
        modeloPelicula.addColumn("Director");
        modeloPelicula.addColumn("Genero");
        modeloPelicula.addColumn("Clasificación");

        for (Pelicula pelicula : peliculas) {
            modeloPelicula.addRow(new Object[]{
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getDirector(),
                pelicula.getGenero(),
                pelicula.getClasificacion()
            });
        }
        jTable1.setModel(modeloPelicula);
        List<Cine> cines = sesion.createQuery("FROM Cine", Cine.class).getResultList();
        DefaultTableModel modeloCine = new DefaultTableModel();
        modeloCine.addColumn("ID");
        modeloCine.addColumn("Nombre");
        modeloCine.addColumn("Dirección");
        modeloCine.addColumn("Teléfono");

        for (Cine cine : cines) {
            modeloCine.addRow(new Object[]{
                cine.getId(),
                cine.getNombre(),
                cine.getDireccion(),
                cine.getTelefono()
            });
        }
        jTable2.setModel(modeloCine);

        // Cargar datos de pases
        List<Pase> pases = sesion.createQuery("FROM Pase", Pase.class).getResultList();
        DefaultTableModel modeloPase = new DefaultTableModel();
        modeloPase.addColumn("ID");
        modeloPase.addColumn("Cine");
        modeloPase.addColumn("Pelicula");

        for (Pase pase : pases) {
            modeloPase.addRow(new Object[]{
                pase.getId(),
                pase.getCine(),
                pase.getPelicula()
            });
        }
        jTable3.setModel(modeloPase);

        List<Tarifa> tarifas = sesion.createQuery("FROM Tarifa", Tarifa.class).getResultList();
        DefaultTableModel modeloTarifa = new DefaultTableModel();
        modeloTarifa.addColumn("ID");
        modeloTarifa.addColumn("Cine");
        modeloTarifa.addColumn("Miercoles / Sabado");
        modeloTarifa.addColumn("Jubilados");
        modeloTarifa.addColumn("Festivos");
        modeloTarifa.addColumn("Estudiantes");

        for (Tarifa tarifa : tarifas) {
            modeloTarifa.addRow(new Object[]{
                tarifa.getId(),
                tarifa.getCine(),
                tarifa.getmierSabado(),
                tarifa.getjubilados(),
                tarifa.getFestivos(),
                tarifa.getEstudiante()
            });
        }
        jTable4.setModel(modeloTarifa);
        
        List<Protagonista> protagonistas = sesion.createQuery("FROM Protagonista", Protagonista.class).getResultList();
        DefaultTableModel modeloProtagonista = new DefaultTableModel();
        modeloProtagonista.addColumn("ID");
        modeloProtagonista.addColumn("Nombre");
        modeloProtagonista.addColumn("Pelicula");

        for (Protagonista protagonista : protagonistas) {
            modeloProtagonista.addRow(new Object[]{
                protagonista.getId(),
                protagonista.getNombre(),
                protagonista.getPelicula()
            });
        }
        jTable5.setModel(modeloProtagonista);
        
        List<Horario> horarios = sesion.createQuery("FROM Horario", Horario.class).getResultList();
        DefaultTableModel modeloHorario = new DefaultTableModel();
        modeloHorario.addColumn("ID");
        modeloHorario.addColumn("Pase");
        modeloHorario.addColumn("Hora");

        for (Horario horario : horarios) {
            modeloHorario.addRow(new Object[]{
                horario.getId(),
                horario.getPase(),
                horario.getHora()
            });
        }
        jTable6.setModel(modeloHorario);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error cargando datos de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        sesion.close();
    }
}


    
    private void insertarPelicula() {
        String titulo = jTextField1.getText();
        String director = jTextField2.getText();
        String genero = jTextField3.getText();
        String clasificacion = jTextField4.getText();

        if (titulo.isEmpty() || director.isEmpty() || genero.isEmpty() || clasificacion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de insertar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Pelicula nuevaPelicula = new Pelicula();
            nuevaPelicula.setTitulo(titulo);
            nuevaPelicula.setDirector(director);
            nuevaPelicula.setGenero(genero);
            nuevaPelicula.setClasificacion(clasificacion);

            rPelicula.guardarPelicula(nuevaPelicula);

            cargarDatosEnTabla();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar la película en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertarCine() {
        String nombre = jTextField11.getText();
        String direccion = jTextField12.getText();
        String telefono = jTextField13.getText();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de insertar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Cine nuevoCine = new Cine();
            nuevoCine.setNombre(nombre);
            nuevoCine.setDireccion(direccion);
            nuevoCine.setTelefono(telefono);

            rCine.guardarCine(nuevoCine);

            cargarDatosEnTabla();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar el cine en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertarPase() {
        String idCineStr = jTextField14.getText();
        String idPeliculaStr = jTextField20.getText();

        if (idCineStr.isEmpty() || idPeliculaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de insertar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idCine = Integer.parseInt(idCineStr);
            int idPelicula = Integer.parseInt(idPeliculaStr);

            Pase nuevoPase = new Pase();

            Cine cine = new Cine();
            cine.setId(idCine);

            Pelicula pelicula = new Pelicula();
            pelicula.setId(idPelicula);

            nuevoPase.setCine(cine);
            nuevoPase.setPelicula(pelicula);

            rPase.guardarPase(nuevoPase);

            cargarDatosEnTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para las ID.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar el pase en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertarTarifa() {
        String idCineStr = jTextField21.getText();
        String mierSabadoStr = jTextField26.getText();
        String jubiladosStr = jTextField31.getText();
        String festivosStr = jTextField32.getText();
        String estudiantesStr = jTextField33.getText();

        if (idCineStr.isEmpty() || mierSabadoStr.isEmpty() || jubiladosStr.isEmpty() || festivosStr.isEmpty() || estudiantesStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de insertar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idCine = Integer.parseInt(idCineStr);
            double mierSabado = Double.parseDouble(mierSabadoStr);
            double jubilados = Double.parseDouble(jubiladosStr);
            double festivos = Double.parseDouble(festivosStr);
            double estudiantes = Double.parseDouble(estudiantesStr);

            Tarifa nuevaTarifa = new Tarifa();

            Cine cine = new Cine();
            cine.setId(idCine);

            nuevaTarifa.setCine(cine);
            nuevaTarifa.setmierSabado(mierSabado);
            nuevaTarifa.setjubilados(jubilados);
            nuevaTarifa.setFestivos(festivos);
            nuevaTarifa.setEstudiante(estudiantes);

            rTarifa.guardarTarifa(nuevaTarifa);

            cargarDatosEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para la ID y los precios.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar la película en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertarProtagonista() {
        String nombre = jTextField30.getText();
        String idPeliculaStr = jTextField38.getText();

        if (nombre.isEmpty() || idPeliculaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de insertar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idPelicula = Integer.parseInt(idPeliculaStr);

            Pelicula pelicula = new Pelicula();
            pelicula.setId(idPelicula);

            Protagonista nuevoProtagonista = new Protagonista();
            nuevoProtagonista.setNombre(nombre);
            nuevoProtagonista.setPelicula(pelicula);

            rProtagonista.guardarProtagonista(nuevoProtagonista);

            cargarDatosEnTabla();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar el protagonista en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertarHorario() {
        String idPaseStr = jTextField39.getText();
        String horaStr = jTextField44.getText();

        if (idPaseStr.isEmpty() || horaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos antes de insertar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idPase = Integer.parseInt(idPaseStr);
            LocalTime hora = LocalTime.parse(horaStr);
            
            Pase pase = new Pase();
            pase.setId(idPase);

            Horario nuevoHorario = new Horario();
            nuevoHorario.setPase(pase);
            nuevoHorario.setHora(hora);

            rHorario.guardarHorario(nuevoHorario);

            cargarDatosEnTabla();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar el horario en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPelicula() {
        String idTexto = jTextField5.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            rPelicula.eliminarPelicula(id);

            cargarDatosEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la película de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarCine() {
        String idTexto = jTextField15.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            rCine.eliminarCine(id);

            cargarDatosEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la película de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPase() {
        String idTexto = jTextField22.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            rPase.eliminarPase(id);

            cargarDatosEnTabla();


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el pase de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarTarifa() {
        String idTexto = jTextField27.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            rTarifa.eliminarTarifa(id);

            cargarDatosEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar la tarifa de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProtagonista() {
        String idTexto = jTextField40.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            rProtagonista.eliminarProtagonista(id);

            cargarDatosEnTabla();

         
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el protagonista de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarHorario() {
        String idTexto = jTextField46.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            rHorario.eliminarHorario(id);

            cargarDatosEnTabla();

      
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el horario de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarPelicula() {
        String idTexto = jTextField6.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            Pelicula peliculaExistente = rPelicula.obtenerPeliculaPorId(id);

            if (peliculaExistente == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna película con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevoTitulo = jTextField7.getText();
            String nuevoDirector = jTextField8.getText();
            String nuevoGenero = jTextField9.getText();
            String nuevaClasificacion = jTextField10.getText();

            if (!nuevoTitulo.isEmpty() && !nuevoTitulo.equals("Nuevo Título")) {
                peliculaExistente.setTitulo(nuevoTitulo);
            }
            if (!nuevoDirector.isEmpty() && !nuevoDirector.equals("Nuevo Director")) {
                peliculaExistente.setDirector(nuevoDirector);
            }
            if (!nuevoGenero.isEmpty() && !nuevoGenero.equals("Nuevo Genero")) {
                peliculaExistente.setGenero(nuevoGenero);
            }
            if (!nuevaClasificacion.isEmpty() && !nuevaClasificacion.equals("Nueva Clasificación")) {
                peliculaExistente.setClasificacion(nuevaClasificacion);
            }

            rPelicula.actualizarPelicula(peliculaExistente);

            cargarDatosEnTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la película en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCine() {
        String idTexto = jTextField16.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            Cine cineExistente = rCine.obtenerCinePorId(id);

            if (cineExistente == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún cine con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevoNombre = jTextField17.getText();
            String nuevaDireccion = jTextField18.getText();
            String nuevoTelefono = jTextField19.getText();

            if (!nuevoNombre.isEmpty() && !nuevoNombre.equals("Nuevo Nombre")) {
                cineExistente.setNombre(nuevoNombre);
            }
            if (!nuevaDireccion.isEmpty() && !nuevaDireccion.equals("Nueva Dirección")) {
                cineExistente.setDireccion(nuevaDireccion);
            }
            if (!nuevoTelefono.isEmpty() && !nuevoTelefono.equals("Nuevo Teléfono")) {
                cineExistente.setTelefono(nuevoTelefono);
            }

            rCine.actualizarCine(cineExistente);

            cargarDatosEnTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el cine en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarPase() {
        String idTexto = jTextField23.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            Pase paseExistente = rPase.obtenerPasePorId(id);

            if (paseExistente == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ningún pase con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevoIDCIneStr = jTextField24.getText();
            String nuevoIDPeliculaStr = jTextField25.getText();

            int nuevoIDCine = Integer.parseInt(nuevoIDCIneStr);
            int nuevoIDPelicula = Integer.parseInt(nuevoIDPeliculaStr);

            Cine cine = new Cine();
            cine.setId(nuevoIDCine);

            Pelicula pelicula = new Pelicula();
            pelicula.setId(nuevoIDPelicula);

            if (!nuevoIDCIneStr.isEmpty() && !nuevoIDCIneStr.equals("Nuevo IDCine")) {
                paseExistente.setCine(cine);
            }
            if (!nuevoIDPeliculaStr.isEmpty() && !nuevoIDPeliculaStr.equals("Nuevo IDPelicula")) {
                paseExistente.setPelicula(pelicula);
            }

            rPase.actualizarPase(paseExistente);

            cargarDatosEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el pase en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTarifa() {
        String idTexto = jTextField28.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            Tarifa tarifaExistente = rTarifa.obtenerTarifaPorId(id);

            if (tarifaExistente == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna tarifa con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String idCineStr = jTextField29.getText();
            String mierSabadoStr = jTextField34.getText();
            String jubiladosStr = jTextField35.getText();
            String festivosStr = jTextField36.getText();
            String estudiantesStr = jTextField37.getText();

            int idCine = Integer.parseInt(idCineStr);
            double mierSabado = Double.parseDouble(mierSabadoStr);
            double jubilados = Double.parseDouble(jubiladosStr);
            double festivos = Double.parseDouble(festivosStr);
            double estudiantes = Double.parseDouble(estudiantesStr);

            Cine cine = new Cine();
            cine.setId(idCine);

            if (!idCineStr.isEmpty() && !idCineStr.equals("Nuevo IDCine")) {
                tarifaExistente.setCine(cine);
            }
            if (!mierSabadoStr.isEmpty() && !mierSabadoStr.equals("Nuevo Precio Día Espectador")) {
                tarifaExistente.setmierSabado(mierSabado);
            }
            if (!jubiladosStr.isEmpty() && !jubiladosStr.equals("Nuevo Precio Día Jubilado")) {
                tarifaExistente.setjubilados(jubilados);
            }
            if (!festivosStr.isEmpty() && !festivosStr.equals("Nuevo Precio Festivos")) {
                tarifaExistente.setFestivos(festivos);
            }
            if (!estudiantesStr.isEmpty() && !estudiantesStr.equals("Nueva Precio Estudiantes")) {
                tarifaExistente.setEstudiante(estudiantes);
            }

            rTarifa.actualizarTarifa(tarifaExistente);

            cargarDatosEnTabla();

   
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID y precios válidos para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la tarifa en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarProtagonista() {
        String idTexto = jTextField41.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            Protagonista protagonistaExistente = rProtagonista.obtenerProtagonistaPorId(id);

            if (protagonistaExistente == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna película con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevoNombre = jTextField42.getText();
            String nuevoIDPeliculaStr = jTextField43.getText();

            int nuevoIDPelicula = Integer.parseInt(nuevoIDPeliculaStr);

            Pelicula pelicula = new Pelicula();
            pelicula.setId(nuevoIDPelicula);

            if (!nuevoNombre.isEmpty() && !nuevoNombre.equals("Nuevo Nombre")) {
                protagonistaExistente.setNombre(nuevoNombre);
            }
            if (!nuevoIDPeliculaStr.isEmpty() && !nuevoIDPeliculaStr.equals("Nuevo IDPelicula")) {
                protagonistaExistente.setPelicula(pelicula);
            }

            rProtagonista.actualizarProtagonista(protagonistaExistente);

            cargarDatosEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la película en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarHorario() {
        String idTexto = jTextField47.getText();

        if (idTexto.isEmpty() || idTexto.equals("ID")) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            Horario horarioExistente = rHorario.obtenerHorarioPorId(id);

            if (horarioExistente == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna película con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nuevoIDPaseStr = jTextField48.getText();
            String nuevaHoraStr = jTextField49.getText();

            int nuevoIDPase = Integer.parseInt(nuevoIDPaseStr);
            LocalTime nuevaHora = LocalTime.parse(nuevaHoraStr);
            
            Pase pase = new Pase();
            pase.setId(nuevoIDPase);
            
            if (!nuevoIDPaseStr.isEmpty() && !nuevoIDPaseStr.equals("Nuevo IDPase")) {
                horarioExistente.setPase(pase);
            }
            if (!nuevaHoraStr.isEmpty() && !nuevaHoraStr.equals("Nueva Hora")) {
                horarioExistente.setHora(nuevaHora);
            }

            rHorario.actualizarHorario(horarioExistente);

            cargarDatosEnTabla();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el horario en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        comboConsultas = new javax.swing.JComboBox<>();
        textoCambiante = new javax.swing.JLabel();
        textoConsultas = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        botonBusqueda = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Cines");

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(100, 160));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Dirección", "Teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setHeaderValue("Teléfono");
        }

        jPanel7.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Crear");

        jTextField11.setToolTipText("Nombre");
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField11FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField11FocusLost(evt);
            }
        });

        jTextField12.setToolTipText("Dirección");
        jTextField12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField12FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField12FocusLost(evt);
            }
        });

        jTextField13.setToolTipText("Teléfono");
        jTextField13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField13FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField13FocusLost(evt);
            }
        });

        jButton4.setText("Insertar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addContainerGap())
        );

        jPanel9.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Eliminar");

        jTextField15.setToolTipText("Id");
        jTextField15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField15FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField15FocusLost(evt);
            }
        });

        jButton5.setText("Eliminar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );

        jPanel10.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Actualizar");

        jTextField16.setToolTipText("Id");
        jTextField16.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField16FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField16FocusLost(evt);
            }
        });

        jTextField17.setToolTipText("Nombre");
        jTextField17.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField17FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField17FocusLost(evt);
            }
        });

        jTextField18.setToolTipText("Dirección");
        jTextField18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField18FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField18FocusLost(evt);
            }
        });

        jTextField19.setToolTipText("Teléfono");
        jTextField19.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField19FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField19FocusLost(evt);
            }
        });

        jButton6.setText("Actualizar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField16)
                    .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField18)
                    .addComponent(jTextField19)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cines", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Título", "Director", "Genero", "Clasificación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setHeaderValue("Clasificación");
        }

        jPanel5.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crear");

        jTextField1.setToolTipText("Título");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jTextField2.setToolTipText("Director");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        jTextField3.setToolTipText("Género");
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        jTextField4.setToolTipText("Clasificación");
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });

        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel6.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Eliminar");

        jTextField5.setToolTipText("Id");
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jPanel8.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Actualizar");

        jTextField6.setToolTipText("Id");
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });

        jTextField7.setToolTipText("Titulo");
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });

        jTextField8.setToolTipText("Director");
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });

        jTextField9.setToolTipText("Género");
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });

        jTextField10.setToolTipText("Clasificación");
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField10FocusLost(evt);
            }
        });

        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField8)
                    .addComponent(jTextField9)
                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Peliculas", jPanel2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Cine", "Pelicula"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel11.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Crear");

        jTextField14.setToolTipText("IdCine");
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField14FocusLost(evt);
            }
        });

        jTextField20.setToolTipText("IdPelícula");
        jTextField20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField20FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField20FocusLost(evt);
            }
        });

        jButton7.setText("Insertar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );

        jPanel12.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Eliminar");

        jTextField22.setToolTipText("Id");
        jTextField22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField22FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField22FocusLost(evt);
            }
        });

        jButton8.setText("Eliminar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField22))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addContainerGap())
        );

        jPanel13.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Actualizar");

        jTextField23.setToolTipText("Id");
        jTextField23.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField23FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField23FocusLost(evt);
            }
        });

        jTextField24.setToolTipText("IdCine");
        jTextField24.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField24FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField24FocusLost(evt);
            }
        });

        jTextField25.setToolTipText("IdPelícula");
        jTextField25.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField25FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField25FocusLost(evt);
            }
        });

        jButton9.setText("Actualizar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField23)
                    .addComponent(jTextField24, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField25)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jButton9)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pase", jPanel3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Cine", "Día Espectador", "Día Jubilado", "Festivos", "Estudiantes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel16.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Crear");

        jTextField21.setToolTipText("IdCine");
        jTextField21.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField21FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField21FocusLost(evt);
            }
        });

        jTextField26.setToolTipText("Precio Miercoles / Sabado");
        jTextField26.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField26FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField26FocusLost(evt);
            }
        });

        jButton10.setText("Insertar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTextField31.setToolTipText("Precio Jubilados");
        jTextField31.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField31FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField31FocusLost(evt);
            }
        });

        jTextField32.setToolTipText("Festivos");
        jTextField32.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField32FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField32FocusLost(evt);
            }
        });

        jTextField33.setToolTipText("Estudiantes");
        jTextField33.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField33FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField33FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField33, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField32, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField31, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField21, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addContainerGap())
        );

        jPanel17.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Eliminar");

        jTextField27.setToolTipText("Id");
        jTextField27.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField27FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField27FocusLost(evt);
            }
        });

        jButton11.setText("Eliminar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField27))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11)
                .addContainerGap())
        );

        jPanel18.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Actualizar");

        jTextField28.setToolTipText("Id");
        jTextField28.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField28FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField28FocusLost(evt);
            }
        });

        jTextField29.setToolTipText("IdCine");
        jTextField29.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField29FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField29FocusLost(evt);
            }
        });

        jButton12.setText("Actualizar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jTextField34.setToolTipText("Precio Miercoles / Sabado");
        jTextField34.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField34FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField34FocusLost(evt);
            }
        });

        jTextField35.setToolTipText("Precio Jubilados");
        jTextField35.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField35FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField35FocusLost(evt);
            }
        });

        jTextField36.setToolTipText("Precio Festivos");
        jTextField36.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField36FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField36FocusLost(evt);
            }
        });

        jTextField37.setToolTipText("Precio Estudiantes");
        jTextField37.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField37FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField37FocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField28)
                    .addComponent(jTextField29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField34)
                    .addComponent(jTextField35)
                    .addComponent(jTextField36)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField37))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tarifa", jPanel4);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Dirección", "Teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(3).setHeaderValue("Teléfono");
        }

        jPanel19.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Crear");

        jTextField30.setToolTipText("Nombre");
        jTextField30.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField30FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField30FocusLost(evt);
            }
        });

        jTextField38.setToolTipText("IdPelícula");
        jTextField38.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField38FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField38FocusLost(evt);
            }
        });

        jButton13.setText("Insertar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jTextField38, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton13)
                .addContainerGap())
        );

        jPanel20.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Eliminar");

        jTextField40.setToolTipText("Id");
        jTextField40.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField40FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField40FocusLost(evt);
            }
        });

        jButton14.setText("Eliminar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField40, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addContainerGap())
        );

        jPanel21.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Actualizar");

        jTextField41.setToolTipText("Id");
        jTextField41.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField41FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField41FocusLost(evt);
            }
        });

        jTextField42.setToolTipText("Nombre");
        jTextField42.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField42FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField42FocusLost(evt);
            }
        });

        jTextField43.setToolTipText("IdPelicula");
        jTextField43.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField43FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField43FocusLost(evt);
            }
        });

        jButton15.setText("Actualizar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField41)
                    .addComponent(jTextField42, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField43)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Protagonista", jPanel14);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Pase", "Hora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jPanel22.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Crear");

        jTextField39.setToolTipText("IdPase");
        jTextField39.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField39FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField39FocusLost(evt);
            }
        });

        jTextField44.setToolTipText("Hora");
        jTextField44.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField44FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField44FocusLost(evt);
            }
        });

        jButton16.setText("Insertar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jTextField44, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField39, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton16)
                .addContainerGap())
        );

        jPanel23.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Eliminar");

        jTextField46.setToolTipText("Id");
        jTextField46.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField46FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField46FocusLost(evt);
            }
        });

        jButton17.setText("Eliminar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField46, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addContainerGap())
        );

        jPanel24.setPreferredSize(new java.awt.Dimension(220, 220));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Actualizar");

        jTextField47.setToolTipText("Id");
        jTextField47.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField47FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField47FocusLost(evt);
            }
        });

        jTextField48.setToolTipText("IdPase");
        jTextField48.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField48FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField48FocusLost(evt);
            }
        });

        jTextField49.setToolTipText("Hora");
        jTextField49.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField49FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField49FocusLost(evt);
            }
        });

        jButton18.setText("Actualizar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addComponent(jTextField47)
                    .addComponent(jTextField48, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField49)
                    .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jButton18)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Horario", jPanel15);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Elige tu consulta:");

        comboConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver las Tarifas de un cine", "Ver los Pases de una Pelicula", "Buscar Pelicula por Director", "Buscar Pelicula Por Genero" }));
        comboConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboConsultasActionPerformed(evt);
            }
        });

        textoCambiante.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        textoCambiante.setText("Ingresa el nombre del Cine:");

        textoConsultas.setColumns(15);
        textoConsultas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTable7.setBackground(new java.awt.Color(51, 102, 0));
        jTable7.setForeground(new java.awt.Color(255, 255, 255));
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(jTable7);

        botonBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonBusqueda.setText("Buscar");
        botonBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel20)
                                .addGap(45, 45, 45)
                                .addComponent(comboConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(textoConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(textoCambiante)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(botonBusqueda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(comboConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(textoCambiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(botonBusqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Busqueda", jPanel25);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setText("Proyecto Cines");

        jButton19.setText("AgregarDatos");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton21.setText("Actualizar Datos");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(82, 82, 82)
                .addComponent(jButton19)
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jButton21))
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField3FocusGained

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField4FocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        insertarPelicula();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        eliminarPelicula();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField5FocusGained

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField6FocusGained

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField7FocusGained

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField8FocusGained

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextField10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField10FocusGained

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField10FocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        actualizarPelicula();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField11FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField11FocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField11FocusGained

    private void jTextField11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField11FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField11FocusLost

    private void jTextField12FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField12FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField12FocusGained

    private void jTextField12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField12FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField12FocusLost

    private void jTextField13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField13FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField13FocusGained

    private void jTextField13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField13FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField13FocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        insertarCine();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField15FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField15FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField15FocusGained

    private void jTextField15FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField15FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField15FocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        eliminarCine();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField16FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField16FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField16FocusGained

    private void jTextField16FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField16FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField16FocusLost

    private void jTextField17FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField17FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField17FocusGained

    private void jTextField17FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField17FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField17FocusLost

    private void jTextField18FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField18FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField18FocusGained

    private void jTextField18FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField18FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField18FocusLost

    private void jTextField19FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField19FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField19FocusGained

    private void jTextField19FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField19FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField19FocusLost

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        actualizarCine();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField14FocusGained

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField14FocusLost

    private void jTextField20FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField20FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField20FocusGained

    private void jTextField20FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField20FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField20FocusLost

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        insertarPase();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextField22FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField22FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField22FocusGained

    private void jTextField22FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField22FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField22FocusLost

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        eliminarPase();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField23FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField23FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField23FocusGained

    private void jTextField23FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField23FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField23FocusLost

    private void jTextField24FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField24FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField24FocusGained

    private void jTextField24FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField24FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField24FocusLost

    private void jTextField25FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField25FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField25FocusGained

    private void jTextField25FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField25FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField25FocusLost

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        actualizarPase();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField21FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField21FocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField21FocusGained

    private void jTextField21FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField21FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField21FocusLost

    private void jTextField26FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField26FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField26FocusGained

    private void jTextField26FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField26FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField26FocusLost

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        insertarTarifa();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField27FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField27FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField27FocusGained

    private void jTextField27FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField27FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField27FocusLost

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        eliminarTarifa();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField28FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField28FocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField28FocusGained

    private void jTextField28FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField28FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField28FocusLost

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        actualizarTarifa();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField31FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField31FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField31FocusGained

    private void jTextField31FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField31FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField31FocusLost

    private void jTextField32FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField32FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField32FocusGained

    private void jTextField32FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField32FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField32FocusLost

    private void jTextField33FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField33FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField33FocusGained

    private void jTextField33FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField33FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField33FocusLost

    private void jTextField29FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField29FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField29FocusLost

    private void jTextField29FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField29FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField29FocusGained

    private void jTextField34FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField34FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField34FocusGained

    private void jTextField34FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField34FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField34FocusLost

    private void jTextField35FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField35FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField35FocusGained

    private void jTextField35FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField35FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField35FocusLost

    private void jTextField36FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField36FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField36FocusGained

    private void jTextField36FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField36FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField36FocusLost

    private void jTextField37FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField37FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField37FocusGained

    private void jTextField37FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField37FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField37FocusLost

    private void jTextField30FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField30FocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField30FocusGained

    private void jTextField30FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField30FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField30FocusLost

    private void jTextField38FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField38FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField38FocusGained

    private void jTextField38FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField38FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField38FocusLost

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        insertarProtagonista();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField40FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField40FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField40FocusGained

    private void jTextField40FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField40FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField40FocusLost

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        eliminarProtagonista();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField41FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField41FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField41FocusGained

    private void jTextField41FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField41FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField41FocusLost

    private void jTextField42FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField42FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField42FocusGained

    private void jTextField42FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField42FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField42FocusLost

    private void jTextField43FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField43FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField43FocusGained

    private void jTextField43FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField43FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField43FocusLost

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        actualizarProtagonista();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField39FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField39FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField39FocusGained

    private void jTextField39FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField39FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField39FocusLost

    private void jTextField44FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField44FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField44FocusGained

    private void jTextField44FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField44FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField44FocusLost

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        insertarHorario();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jTextField46FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField46FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField46FocusGained

    private void jTextField46FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField46FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField46FocusLost

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        eliminarHorario();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextField47FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField47FocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField47FocusGained

    private void jTextField47FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField47FocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTextField47FocusLost

    private void jTextField48FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField48FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField48FocusGained

    private void jTextField48FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField48FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField48FocusLost

    private void jTextField49FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField49FocusGained
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField49FocusGained

    private void jTextField49FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField49FocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField49FocusLost

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        actualizarHorario();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void comboConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboConsultasActionPerformed
        int indice = comboConsultas.getSelectedIndex();
        switch (indice) {
            case 0:
            botonBusqueda.setEnabled(true);
            textoCambiante.setText("Ingresa el nombre del Cine:");
            break;
            case 1:
            botonBusqueda.setEnabled(true);
            textoCambiante.setText("Ingresa el nombre de la Pelicula:");
            break;
            case 2:
            botonBusqueda.setEnabled(true);
            textoCambiante.setText("Ingresa el nombre del Director:");
            break;
            case 3:
            botonBusqueda.setEnabled(true);
            textoCambiante.setText("Ingresa el nombre del Genero:");
            break;
            default:
            botonBusqueda.setEnabled(false);
            textoCambiante.setText("Ingresa el nombre del Cine:");
        }
    }//GEN-LAST:event_comboConsultasActionPerformed
    private void cargarPeliculasPorGenero(String tipoGenero) {
        Session sesion = sessionFactory.openSession();

        try {
            String hql = "FROM Pelicula p WHERE p.genero = :tipoGenero";
            Query<Pelicula> query = sesion.createQuery(hql, Pelicula.class);
            query.setParameter("tipoGenero", tipoGenero);

            List<Pelicula> peliculas = query.list();
            sesion.close();

            if (!peliculas.isEmpty()) {
                Object[][] peliculaData = new Object[peliculas.size()][5];

                for (int i = 0; i < peliculas.size(); i++) {
                    Pelicula pelicula = peliculas.get(i);
                    peliculaData[i][0] = pelicula.getId();
                    peliculaData[i][1] = pelicula.getTitulo();
                    peliculaData[i][2] = pelicula.getDirector();
                    peliculaData[i][3] = pelicula.getClasificacion();
                    peliculaData[i][4] = pelicula.getGenero();
                   
                }

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        peliculaData,
                        new String[]{
                            "Id", "Pelicula", "Director", "Clasificación", "Género"
                        }
                ) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                        java.lang.String.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false
                    };

                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }
     private void cargarPasesPorPelicula(String nombrePelicula) {
    Session sesion = sessionFactory.openSession();
    try {
        String hql = "FROM Pase p WHERE p.pelicula.titulo = :nombrePelicula";
        Query<Pase> query = sesion.createQuery(hql, Pase.class);
        query.setParameter("nombrePelicula", nombrePelicula);

        List<Pase> pases = query.list();

        if (!pases.isEmpty()) {
            Object[][] paseData = new Object[pases.size()][3];

            for (int i = 0; i < pases.size(); i++) {
                Pase pase = pases.get(i);
                paseData[i][0] = pase.getId();
                // Aquí debes agregar el campo correspondiente de los horarios, por ejemplo pase.getHorarios().get(0).getHora()
                paseData[i][1] = pase.getHorarios().get(0).getHora();
                paseData[i][2] = pase.getCine().getNombre();
            }

            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                paseData,
                new String[]{
                    "Id", "Hora", "Nombre del Cine"
                }
            ) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (sesion != null && sesion.isOpen()) {
            sesion.close();
        }
    }
}

    private void cargarPeliculasPorDirector(String nombreDirector) {
        Session sesion = sessionFactory.openSession();

        try {
            String hql = "FROM Pelicula p WHERE p.director = :nombreDirector";
            Query<Pelicula> query = sesion.createQuery(hql, Pelicula.class);
            query.setParameter("nombreDirector", nombreDirector);

            List<Pelicula> peliculas = query.list();

            if (!peliculas.isEmpty()) {
                Object[][] peliculaData = new Object[peliculas.size()][5];

                for (int i = 0; i < peliculas.size(); i++) {
                    Pelicula pelicula = peliculas.get(i);
                    peliculaData[i][0] = pelicula.getId();
                    peliculaData[i][1] = pelicula.getTitulo();
                    peliculaData[i][2] = pelicula.getDirector();
                    peliculaData[i][3] = pelicula.getClasificacion();
                    peliculaData[i][4] = pelicula.getGenero();
                }

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        peliculaData,
                        new String[]{
                            "Id", "Pelicula", "Director", "Clasificación", "Género"
                        }
                ) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                        java.lang.String.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false
                    };

                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close();
            }
        }
    }

    private void cargarTarifasPorCine(String nombreCine) {
    Session sesion = sessionFactory.openSession();

    try {
        String hql = "FROM Tarifa t WHERE t.cine.nombre = :nombreCine";
        Query<Tarifa> query = sesion.createQuery(hql, Tarifa.class);
        query.setParameter("nombreCine", nombreCine);

        List<Tarifa> tarifas = query.list();

        if (!tarifas.isEmpty()) {
            Object[][] tarifaData = new Object[tarifas.size()][5];

            for (int i = 0; i < tarifas.size(); i++) {
                Tarifa tarifa = tarifas.get(i);
                tarifaData[i][0] = tarifa.getId();
                tarifaData[i][1] = tarifa.getmierSabado();
                tarifaData[i][2] = tarifa.getjubilados();
                tarifaData[i][3] = tarifa.getFestivos();
                tarifaData[i][4] = tarifa.getEstudiante();
            }

            jTable2.setModel(new javax.swing.table.DefaultTableModel(
                tarifaData,
                new String[]{
                    "Id", "Miércoles/Sábado", "Jubilados", "Festivos", "Estudiante"
                }
            ) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class,
                    java.lang.Double.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (sesion != null && sesion.isOpen()) {
            sesion.close();
        }
    }
}
    private void botonBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBusquedaActionPerformed
        int indice = comboConsultas.getSelectedIndex();
        String consulta = textoConsultas.getText();
        switch (indice) {
            case 0:
            cargarTarifasPorCine(consulta);
            break;
            case 1:
            cargarPasesPorPelicula(consulta);
            break;
            case 2:
            cargarPeliculasPorDirector(consulta);
            break;
            case 3:
            cargarPeliculasPorGenero(consulta);
            break;
            default:

        }
    }//GEN-LAST:event_botonBusquedaActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        cargarDatosBase();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        cargarDatosEnTabla();
    }//GEN-LAST:event_jButton21ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBusqueda;
    private javax.swing.JComboBox<String> comboConsultas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel textoCambiante;
    private javax.swing.JTextField textoConsultas;
    // End of variables declaration//GEN-END:variables
}
