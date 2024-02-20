/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.*;
import java.time.LocalTime;

/**
 *
 * @author emils
 */
@Entity
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pase_id")
    private Pase pase;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    public Horario() {
    }

    public Horario(int id, Pase pase, LocalTime hora) {
        this.id = id;
        this.pase = pase;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pase getPase() {
        return pase;
    }

    public void setPase(Pase pase) {
        this.pase = pase;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

}
