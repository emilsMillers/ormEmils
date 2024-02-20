/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.*;

/**
 *
 * @author emils
 */
@Entity
@Table(name = "protagonista")
public class Protagonista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    public Protagonista() {
    }

    public Protagonista(int id, String nombre, Pelicula pelicula) {
        this.id = id;
        this.nombre = nombre;
        this.pelicula = pelicula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

}
