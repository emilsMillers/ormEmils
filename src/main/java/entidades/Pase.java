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
@Table(name = "PASE")
public class Pase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hora;

    @ManyToOne
    @JoinColumn(name = "cine_nombre")
    private Cine cine;

    @ManyToOne
    @JoinColumn(name = "pelicula_titulo")
    private Pelicula pelicula;

    public Pase(Long id, String hora, Cine cine, Pelicula pelicula) {
        this.id = id;
        this.hora = hora;
        this.cine = cine;
        this.pelicula = pelicula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    
}
