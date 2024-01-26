/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoorm.pantallas;

import entidades.Cine;
import entidades.Pase;
import entidades.Pelicula;
import entidades.Tarifa;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

/**
 *
 * @author emils
 */
public class Principal extends javax.swing.JFrame {

    final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
    final SessionFactory sessionFactory = new MetadataSources(registro).buildMetadata().buildSessionFactory();

    //Session sesion = sessionFactory.openSession();
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registro).buildMetadata().buildSessionFactory();
        cargarDatosEnTabla();
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        botonInsertarPelicula.setEnabled(false);

        Titulo.getDocument().addDocumentListener(new CambioCampoListener());
        Director.getDocument().addDocumentListener(new CambioCampoListener());
        Genero.getDocument().addDocumentListener(new CambioCampoListener());
        clasificacion.addItemListener(e -> validarCampos());
        listaHorarios.addListSelectionListener(e -> validarCampos());
        listaCines.addListSelectionListener(e -> validarCampos());
        textoTitulo.setEnabled(false);
        textoGenero.setEnabled(false);
        textoDirector.setEnabled(false);
        textoProta1.setEnabled(false);
        textoProta2.setEnabled(false);
        textoProta3.setEnabled(false);
        comboActu.setEnabled(false);

        botonEdit.setEnabled(false);
        textoTitulo.getDocument().addDocumentListener(new CambioCampoEditListener());
        textoGenero.getDocument().addDocumentListener(new CambioCampoEditListener());
        textoDirector.getDocument().addDocumentListener(new CambioCampoEditListener());
        comboActu.addItemListener(e -> validarCampos());
    }

    private void cargarDatosBase() {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();

        try {

            sesion.save(new Pelicula("La luna Mágica", "Bon Jovi", "Animacion", "PG-13", "Rafael Mutilado", "Rafael Mutilado 2", ""));
            sesion.save(new Pelicula("El durazno", "Martin Scorsese", "Animacion", "PG-13", "Beyonce", "", ""));
            sesion.save(new Pelicula("Cristiano Ronaldo", "Cristiano Ronaldo", "Accion", "NC-17", "Cristiano Ronaldo", "", ""));
            sesion.save(new Pelicula("La magica noche", "Bon Jovi", "Ciencia Ficcion", "R", "", "", ""));
            sesion.save(new Pelicula("Cristiano Ronaldo", "Cristiano Ronaldo", "Accion", "PG", "Rafa Mora", "", ""));
            sesion.save(new Pelicula("La noche estrellada", "Rafita", "Amor", "G", "Gundogan", "", ""));
            sesion.save(new Cine("Multicines Tenerife", "Las Palmitas", 1, "564345432"));
            sesion.save(new Cine("Yelmo Meridiano", "Ofra", 2, "234345456"));
            sesion.save(new Cine("Yelmo La Villa", "San Isidro", 3, "846353221"));
            sesion.save(new Cine("X-Sur Cine", "Roque del Conde", 4, "867546543"));
            sesion.save(new Cine("Cine Zentral Center", "Fañabe", 5, "660122340"));
            sesion.save(new Cine("Cine Price Prime", "La Caleta", 6, "660122544"));
            sesion.getTransaction().commit();
            List<Cine> cines = sesion.createQuery("FROM Cine", Cine.class).list();

            cines.forEach(ciner -> {
                asignarTarifasACine(ciner);
            });

            List<Pelicula> peliculas = sesion.createQuery("FROM Pelicula", Pelicula.class).list();
            cines.forEach(ciner -> {
                peliculas.forEach(pelicular -> {
                    generarPasesParaCineYPelicula(ciner, pelicular);
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
            sesion.getTransaction().rollback();
        } finally {
            sesion.close();
        }

    }

    private void generarPasesParaCineYPelicula(Cine cine, Pelicula pelicula) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        try {
            sesion.save(new Pase("12:30", cine, pelicula));
            sesion.save(new Pase("13:30", cine, pelicula));
            sesion.save(new Pase("15:30", cine, pelicula));
            sesion.save(new Pase("17:30", cine, pelicula));
            sesion.save(new Pase("20:30", cine, pelicula));
            sesion.save(new Pase("22:30", cine, pelicula));
            sesion.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            sesion.getTransaction().rollback();
        } finally {
            sesion.close();
        }
    }

    private void asignarTarifasACine(Cine cine) {
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        try {
            sesion.save(new Tarifa("Normal", 7.50, cine));
            sesion.save(new Tarifa("Miercoles", 5.50, cine));
            sesion.save(new Tarifa("Sabado", 5.50, cine));
            sesion.save(new Tarifa("Domingo y Festivos", 6.50, cine));
            sesion.save(new Tarifa("Jubilados/Ancianos", 4.50, cine));
            sesion.save(new Tarifa("Niños", 4.50, cine));
            sesion.getTransaction().commit();
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
            List<Pelicula> peliculas = sesion.createQuery("from Pelicula", Pelicula.class).getResultList();

            Object[][] peliculaData = new Object[peliculas.size()][(8)];
            for (int i = 0; i < peliculas.size(); i++) {
                Pelicula pelicula = peliculas.get(i);
                peliculaData[i][0] = pelicula.getId();
                peliculaData[i][1] = pelicula.getTitulo().toString();
                peliculaData[i][2] = pelicula.getDirector().toString();
                peliculaData[i][3] = pelicula.getClasificacion().toString();
                peliculaData[i][4] = pelicula.getGenero().toString();
                peliculaData[i][5] = pelicula.getProtagonista1().toString();
                peliculaData[i][6] = pelicula.getProtagonista2().toString();
                peliculaData[i][7] = pelicula.getProtagonista3().toString();
            }

            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    peliculaData,
                    new String[]{
                        "Id", "Pelicula", "Director", "Clasificación", "Género", "Protagonista", "Protagonista 2", "Protagonista 3"
                    }
            ) {
                Class[] types = new Class[]{
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesion.close();
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
                    paseData[i][1] = pase.getHora();
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
                Object[][] peliculaData = new Object[peliculas.size()][8];

                for (int i = 0; i < peliculas.size(); i++) {
                    Pelicula pelicula = peliculas.get(i);
                    peliculaData[i][0] = pelicula.getId();
                    peliculaData[i][1] = pelicula.getTitulo();
                    peliculaData[i][2] = pelicula.getDirector();
                    peliculaData[i][3] = pelicula.getClasificacion();
                    peliculaData[i][4] = pelicula.getGenero();
                    peliculaData[i][5] = pelicula.getProtagonista1();
                    peliculaData[i][6] = pelicula.getProtagonista2();
                    peliculaData[i][7] = pelicula.getProtagonista3();
                }

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        peliculaData,
                        new String[]{
                            "Id", "Pelicula", "Director", "Clasificación", "Género", "Protagonista", "Protagonista 2", "Protagonista 3"
                        }
                ) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false, false, false
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
                Object[][] tarifaData = new Object[tarifas.size()][3];

                for (int i = 0; i < tarifas.size(); i++) {
                    Tarifa tarifa = tarifas.get(i);
                    tarifaData[i][0] = tarifa.getId();
                    tarifaData[i][1] = tarifa.getDia();
                    tarifaData[i][2] = tarifa.getPrecio();
                }

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        tarifaData,
                        new String[]{
                            "Id", "Día", "Precio"
                        }
                ) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
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

    private void cargarPeliculasPorGenero(String tipoGenero) {
        Session sesion = sessionFactory.openSession();

        try {
            String hql = "FROM Pelicula p WHERE p.genero = :tipoGenero";
            Query<Pelicula> query = sesion.createQuery(hql, Pelicula.class);
            query.setParameter("tipoGenero", tipoGenero);

            List<Pelicula> peliculas = query.list();
            sesion.close();

            if (!peliculas.isEmpty()) {
                Object[][] peliculaData = new Object[peliculas.size()][8];

                for (int i = 0; i < peliculas.size(); i++) {
                    Pelicula pelicula = peliculas.get(i);
                    peliculaData[i][0] = pelicula.getId();
                    peliculaData[i][1] = pelicula.getTitulo();
                    peliculaData[i][2] = pelicula.getDirector();
                    peliculaData[i][3] = pelicula.getClasificacion();
                    peliculaData[i][4] = pelicula.getGenero();
                    peliculaData[i][5] = pelicula.getProtagonista1();
                    peliculaData[i][6] = pelicula.getProtagonista2();
                    peliculaData[i][7] = pelicula.getProtagonista3();
                }

                jTable2.setModel(new javax.swing.table.DefaultTableModel(
                        peliculaData,
                        new String[]{
                            "Id", "Pelicula", "Director", "Clasificación", "Género", "Protagonista", "Protagonista 2", "Protagonista 3"
                        }
                ) {
                    Class[] types = new Class[]{
                        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                    };
                    boolean[] canEdit = new boolean[]{
                        false, false, false, false, false, false, false, false
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        botonActualizar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        textoId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botonRecogerID = new javax.swing.JButton();
        textoGenero = new javax.swing.JTextField();
        textoTitulo = new javax.swing.JTextField();
        comboActu = new javax.swing.JComboBox<>();
        textoDirector = new javax.swing.JTextField();
        textoProta1 = new javax.swing.JTextField();
        textoProta3 = new javax.swing.JTextField();
        textoProta2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        botonEdit = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        eliminarPelicula = new javax.swing.JButton();
        textoIdE = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botonInsertarPelicula = new javax.swing.JButton();
        Director = new javax.swing.JTextField();
        prota1 = new javax.swing.JTextField();
        Titulo = new javax.swing.JTextField();
        clasificacion = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Genero = new javax.swing.JTextField();
        prota3 = new javax.swing.JTextField();
        prota2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaCines = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaHorarios = new javax.swing.JList<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        comboConsultas = new javax.swing.JComboBox<>();
        textoCambiante = new javax.swing.JLabel();
        textoConsultas = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        botonBusqueda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        insertarDatosBase = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cinematografía Pura");
        setMaximumSize(new java.awt.Dimension(597, 823));
        setMinimumSize(new java.awt.Dimension(597, 823));
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(585, 780));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(585, 780));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(585, 780));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jScrollPane1.setBackground(new java.awt.Color(51, 102, 0));

        jTable1.setBackground(new java.awt.Color(51, 102, 0));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        botonActualizar.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Editar");

        textoId.setColumns(5);
        textoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoIdActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel3.setText("ID:");

        botonRecogerID.setText("Buscar");
        botonRecogerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRecogerIDActionPerformed(evt);
            }
        });

        textoGenero.setColumns(20);
        textoGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoGeneroActionPerformed(evt);
            }
        });

        textoTitulo.setColumns(20);
        textoTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoTituloActionPerformed(evt);
            }
        });

        comboActu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "G", "PG", "PG-13", "R", "NC-17" }));

        textoDirector.setColumns(20);
        textoDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoDirectorActionPerformed(evt);
            }
        });

        textoProta1.setColumns(20);
        textoProta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoProta1ActionPerformed(evt);
            }
        });

        textoProta3.setColumns(20);
        textoProta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoProta3ActionPerformed(evt);
            }
        });

        textoProta2.setColumns(20);
        textoProta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoProta2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Titulo:");

        jLabel7.setText("Director:");

        jLabel8.setText("Clasificación:");

        jLabel9.setText("Género:");

        jLabel10.setText("Prota 1:");

        jLabel11.setText("Prota 2:");

        jLabel12.setText("Prota 3:");

        botonEdit.setText("Editar");
        botonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoProta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoProta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoProta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboActu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonRecogerID)
                        .addGap(18, 18, 18)
                        .addComponent(botonEdit))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(botonRecogerID)
                    .addComponent(botonEdit))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboActu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoProta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoProta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoProta3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Eliminar");

        eliminarPelicula.setText("Eliminar");
        eliminarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPeliculaActionPerformed(evt);
            }
        });

        textoIdE.setColumns(5);

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setText("ID:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(eliminarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textoIdE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(72, 72, 72))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoIdE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(45, 45, 45)
                .addComponent(eliminarPelicula)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(botonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(botonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32))))
        );

        jTabbedPane1.addTab("Peliculas", jPanel1);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        botonInsertarPelicula.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonInsertarPelicula.setText("Insertar");
        botonInsertarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertarPeliculaActionPerformed(evt);
            }
        });

        Director.setColumns(20);

        prota1.setColumns(20);

        Titulo.setColumns(20);

        clasificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "G", "PG", "PG-13", "R", "NC-17" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Si la película no tiene protagonistas");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Deje los siguientes campos vacios");

        Genero.setColumns(20);
        Genero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeneroActionPerformed(evt);
            }
        });

        prota3.setColumns(20);

        prota2.setColumns(20);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel15.setText("Inserta los datos de la Pelicula");

        listaCines.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Multicines Tenerife", "Yelmo Meridiano", "Yelmo  La Villa", "X-Sur Cine", "Cine Zentral Center", "Cine Price Prime" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaCines.setToolTipText("");
        jScrollPane2.setViewportView(listaCines);

        listaHorarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "12:30", "13:30", "15:30", "17:30", "20:30", "22:30" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listaHorarios);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Selecciona los cines en los que esta disponible ");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("y el horario en el que esta disponible");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Titulo:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Director:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Clasificación:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Género:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Prota 1:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Prota 2:");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Prota 3:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Director, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel14))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 64, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel15)))
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))
                        .addGap(421, 421, 421))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(prota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel23)
                                .addComponent(jLabel24)
                                .addComponent(jLabel25))
                            .addGap(421, 421, 421)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(botonInsertarPelicula)
                        .addGap(222, 222, 222))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel15)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Director, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prota1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(24, 24, 24)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonInsertarPelicula)
                .addGap(57, 57, 57))
        );

        jTabbedPane1.addTab("Insertar", jPanel2);

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Elige tu consulta:");

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

        jTable2.setBackground(new java.awt.Color(51, 102, 0));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable2);

        botonBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonBusqueda.setText("Buscar");
        botonBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel18)
                                .addGap(45, 45, 45)
                                .addComponent(comboConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(textoConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(textoCambiante)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(botonBusqueda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(comboConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(textoCambiante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(botonBusqueda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jTabbedPane1.addTab("Consultar", jPanel3);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel1.setText("Proyecto Cines");

        insertarDatosBase.setText("No tienes datos?");
        insertarDatosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarDatosBaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(66, 66, 66)
                        .addComponent(insertarDatosBase)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(insertarDatosBase)
                        .addGap(25, 25, 25))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonInsertarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInsertarPeliculaActionPerformed
        String campo1 = Titulo.getText();
        String campo2 = Director.getText();
        String campo3 = clasificacion.getSelectedItem().toString();
        String campo4 = Genero.getText();
        List<String> campo5 = listaHorarios.getSelectedValuesList();
        List<String> campo6 = listaCines.getSelectedValuesList();
        String campo7 = prota1.getText();
        String campo8 = prota2.getText();
        String campo9 = prota3.getText();

        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();

        try {

            Pelicula pelicula = new Pelicula(campo1, campo2, campo4, campo3, campo7, campo8, campo9);
            sesion.save(pelicula);

            for (String nombreCine : campo6) {
                for (String horario : campo5) {

                    Cine cine = (Cine) sesion.createQuery("FROM Cine WHERE nombre = :nombreCine")
                            .setParameter("nombreCine", nombreCine)
                            .uniqueResult();

                    Pase pase = new Pase(horario, cine, pelicula);
                    sesion.save(pase);
                }
            }
            sesion.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            sesion.getTransaction().rollback();
        } finally {
            Titulo.setText("");
            Director.setText("");
            clasificacion.setSelectedIndex(0);
            Genero.setText("");
            listaHorarios.clearSelection();
            listaCines.clearSelection();
            prota1.setText("");
            prota2.setText("");
            prota3.setText("");
            sesion.close();
        }

    }//GEN-LAST:event_botonInsertarPeliculaActionPerformed
    private class CambioCampoListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            validarCampos();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validarCampos();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validarCampos();
        }
    }

    private class CambioCampoEditListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            validarCamposEdit();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validarCamposEdit();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validarCamposEdit();
        }
    }

    private void validarCampos() {
        String campo1 = Titulo.getText();
        String campo2 = Director.getText();
        String campo3 = clasificacion.getSelectedItem().toString();
        String campo4 = Genero.getText();
        List<String> campo5 = listaHorarios.getSelectedValuesList();
        List<String> campo6 = listaCines.getSelectedValuesList();

        if (!campo1.isEmpty() && !campo2.isEmpty() && !campo3.isEmpty() && !campo4.isEmpty() && !campo5.isEmpty() && !campo6.isEmpty()) {
            botonInsertarPelicula.setEnabled(true);
        } else {
            botonInsertarPelicula.setEnabled(false);
        }
    }

    private void validarCamposEdit() {
        String campo1 = textoTitulo.getText();
        String campo2 = textoDirector.getText();
        String campo3 = comboActu.getSelectedItem().toString();
        String campo4 = textoGenero.getText();

        if (!campo1.isEmpty() && !campo2.isEmpty() && !campo3.isEmpty() && !campo4.isEmpty()) {
            botonEdit.setEnabled(true);
        } else {
            botonEdit.setEnabled(false);
        }
    }

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        cargarDatosEnTabla();
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void textoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoIdActionPerformed

    private void textoTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoTituloActionPerformed

    private void textoGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoGeneroActionPerformed

    private void textoDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoDirectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoDirectorActionPerformed

    private void textoProta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoProta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoProta1ActionPerformed

    private void textoProta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoProta3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoProta3ActionPerformed

    private void textoProta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoProta2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoProta2ActionPerformed

    private void GeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GeneroActionPerformed

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

    private void comboConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboConsultasActionPerformed
        int indice = comboConsultas.getSelectedIndex();
        switch (indice) {
            case 0:
                botonInsertarPelicula.setEnabled(true);
                textoCambiante.setText("Ingresa el nombre del Cine:");
                break;
            case 1:
                botonInsertarPelicula.setEnabled(true);
                textoCambiante.setText("Ingresa el nombre de la Pelicula:");
                break;
            case 2:
                botonInsertarPelicula.setEnabled(true);
                textoCambiante.setText("Ingresa el nombre del Director:");
                break;
            case 3:
                botonInsertarPelicula.setEnabled(true);
                textoCambiante.setText("Ingresa el nombre del Genero:");
                break;
            default:
                botonInsertarPelicula.setEnabled(false);
                textoCambiante.setText("Ingresa el nombre del Cine:");
        }
    }//GEN-LAST:event_comboConsultasActionPerformed

    private void insertarDatosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarDatosBaseActionPerformed
        cargarDatosBase();
    }//GEN-LAST:event_insertarDatosBaseActionPerformed

    private void eliminarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPeliculaActionPerformed
        int idElim = Integer.parseInt(textoIdE.getText());
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        try {
            Pelicula peliculaSeleccionada = sesion.get(Pelicula.class, idElim);
            Query<Pase> query = sesion.createQuery("DELETE FROM Pase p WHERE p.pelicula = :peliculaSeleccionada");
            query.setParameter("peliculaSeleccionada", peliculaSeleccionada);
            query.executeUpdate();
            sesion.delete(peliculaSeleccionada);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            sesion.getTransaction().rollback();
        } finally {
            textoIdE.setText("");
            cargarDatosEnTabla();
            sesion.close();
        }
    }//GEN-LAST:event_eliminarPeliculaActionPerformed

    private void botonRecogerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRecogerIDActionPerformed
        int idElim = Integer.parseInt(textoId.getText());
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        try {
            Pelicula peliculaSeleccionada = sesion.get(Pelicula.class, idElim);
            if (peliculaSeleccionada != null) {
                textoTitulo.setEnabled(true);
                textoGenero.setEnabled(true);
                textoDirector.setEnabled(true);
                textoProta1.setEnabled(true);
                textoProta2.setEnabled(true);
                textoProta3.setEnabled(true);
                comboActu.setEnabled(true);

                textoId.setEnabled(false);
                textoTitulo.setText(peliculaSeleccionada.getTitulo());
                textoGenero.setText(peliculaSeleccionada.getGenero());
                textoDirector.setText(peliculaSeleccionada.getDirector());
                textoProta1.setText(peliculaSeleccionada.getProtagonista1());
                textoProta2.setText(peliculaSeleccionada.getProtagonista2());
                textoProta3.setText(peliculaSeleccionada.getProtagonista3());
                comboActu.setSelectedItem(peliculaSeleccionada.getClasificacion());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna película con ID: " + idElim, "Película no encontrada", JOptionPane.INFORMATION_MESSAGE);
                textoTitulo.setEnabled(false);
                textoGenero.setEnabled(false);
                textoDirector.setEnabled(false);
                textoProta1.setEnabled(false);
                textoProta2.setEnabled(false);
                textoProta3.setEnabled(false);
                comboActu.setEnabled(false);
            }
            sesion.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            sesion.getTransaction().rollback();
        } finally {
            sesion.close();
        }
    }//GEN-LAST:event_botonRecogerIDActionPerformed

    private void botonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditActionPerformed
        String campo1 = textoTitulo.getText();
        String campo2 = textoGenero.getText();
        String campo4 = textoDirector.getText();
        String campo5 = textoProta1.getText();
        String campo6 = textoProta2.getText();
        String campo7 = textoProta3.getText();
        String campo3 = comboActu.getSelectedItem().toString();
        Session sesion = sessionFactory.openSession();
        sesion.beginTransaction();
        try {
            Pelicula peliculaSeleccionada = sesion.get(Pelicula.class, Integer.parseInt(textoId.getText()));
            peliculaSeleccionada.setTitulo(campo1);
            peliculaSeleccionada.setGenero(campo2);
            peliculaSeleccionada.setDirector(campo4);
            peliculaSeleccionada.setProtagonista1(campo5);
            peliculaSeleccionada.setProtagonista2(campo6);
            peliculaSeleccionada.setProtagonista3(campo7);
            peliculaSeleccionada.setClasificacion(campo3);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            sesion.getTransaction().rollback();
        } finally {
            textoTitulo.setEnabled(false);
            textoGenero.setEnabled(false);
            textoDirector.setEnabled(false);
            textoProta1.setEnabled(false);
            textoProta2.setEnabled(false);
            textoProta3.setEnabled(false);
            comboActu.setEnabled(false);
            textoId.setEnabled(true);
            textoId.setText("");
            textoTitulo.setText("");
            textoGenero.setText("");
            textoDirector.setText("");
            textoProta1.setText("");
            textoProta2.setText("");
            textoProta3.setText("");
            comboActu.setSelectedIndex(0);
            cargarDatosEnTabla();
            sesion.close();
        }

    }//GEN-LAST:event_botonEditActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Director;
    private javax.swing.JTextField Genero;
    private javax.swing.JTextField Titulo;
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonBusqueda;
    private javax.swing.JButton botonEdit;
    private javax.swing.JButton botonInsertarPelicula;
    private javax.swing.JButton botonRecogerID;
    private javax.swing.JComboBox<String> clasificacion;
    private javax.swing.JComboBox<String> comboActu;
    private javax.swing.JComboBox<String> comboConsultas;
    private javax.swing.JButton eliminarPelicula;
    private javax.swing.JButton insertarDatosBase;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JList<String> listaCines;
    private javax.swing.JList<String> listaHorarios;
    private javax.swing.JTextField prota1;
    private javax.swing.JTextField prota2;
    private javax.swing.JTextField prota3;
    private javax.swing.JLabel textoCambiante;
    private javax.swing.JTextField textoConsultas;
    private javax.swing.JTextField textoDirector;
    private javax.swing.JTextField textoGenero;
    private javax.swing.JTextField textoId;
    private javax.swing.JTextField textoIdE;
    private javax.swing.JTextField textoProta1;
    private javax.swing.JTextField textoProta2;
    private javax.swing.JTextField textoProta3;
    private javax.swing.JTextField textoTitulo;
    // End of variables declaration//GEN-END:variables
}
