/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author emils
 */

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "titulo", length = 50, nullable = false)
    private String titulo;

    @Column(name = "director", length = 50, nullable = false)
    private String director;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pase> pases;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    private List<Protagonista> protagonistas;

    @Column(name = "genero", length = 20, nullable = false)
    private String genero;

    @Column(name = "clasificacion", length = 20, nullable = false)
    private String clasificacion;

    @Override
    public String toString() {
        return titulo;
    }

    public Pelicula() {
    }

    public Pelicula(int id, String titulo, String director, List<Pase> pases, List<Protagonista> protagonistas, String genero, String clasificacion) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.pases = pases;
        this.protagonistas = protagonistas;
        this.genero = genero;
        this.clasificacion = clasificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<Pase> getPases() {
        return pases;
    }

    public void setPases(List<Pase> pases) {
        this.pases = pases;
    }

    public List<Protagonista> getProtagonistas() {
        return protagonistas;
    }

    public void setProtagonistas(List<Protagonista> protagonistas) {
        this.protagonistas = protagonistas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

}

