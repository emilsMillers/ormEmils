/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoorm;

import com.mycompany.proyectoorm.pantallas.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author emils
 */
public class ProyectoORM {

    public static void main(String[] args) {
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory sessionFactory = new MetadataSources(registro).buildMetadata().buildSessionFactory();

        System.out.println("Abriendo conexión a la base de datos");
        final Session sesion = sessionFactory.openSession();
        
        
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Principal pantalla;
                try {
                    pantalla = new Principal(sesion);
                    pantalla.setVisible(true);
                    pantalla.setLocationRelativeTo(null);
                    pantalla.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                            sesion.close();
                            System.out.println("Conexión cerrada");
                            StandardServiceRegistryBuilder.destroy(registro);
                        }
                    });
                } catch (Exception ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
