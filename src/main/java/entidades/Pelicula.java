/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.*;
import java.util.Set;

/**
 *
 * @author emils
 */

@Entity
@Table(name = "PELICULA")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String director;
    private String genero;
    private String clasificacion;
    private String protagonista1;
    private String protagonista2;
    private String protagonista3;

    @OneToMany(mappedBy = "pelicula")
    private Set<Pase> pases;

    public Pelicula(String titulo, String director, String genero, String clasificacion, String protagonista1, String protagonista2, String protagonista3) {
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.protagonista1 = protagonista1;
        this.protagonista2 = protagonista2;
        this.protagonista3 = protagonista3;
    }
    public Pelicula(){}
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

    public Set<Pase> getPases() {
        return pases;
    }

    public void setPases(Set<Pase> pases) {
        this.pases = pases;
    }
    
    public String getProtagonista1() {
        return protagonista1;
    }

    public void setProtagonista1(String protagonista1) {
        this.protagonista1 = protagonista1;
    }

    public String getProtagonista2() {
        return protagonista2;
    }

    public void setProtagonista2(String protagonista2) {
        this.protagonista2 = protagonista2;
    }

    public String getProtagonista3() {
        return protagonista3;
    }

    public void setProtagonista3(String protagonista3) {
        this.protagonista3 = protagonista3;
    }


    
}
