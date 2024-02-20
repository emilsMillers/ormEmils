
package entidades;

import jakarta.persistence.*;

/**
 *
 * @author emils
 */
@Entity
@Table(name = "tarifa")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "cine_id")
    private Cine cine;

    @Column(name = "Miércoles/Sabado")
    private double mierSabado;
    
    @Column(name = "festivos")
    private double festivos;

    @Column(name = "estudiante")
    private double estudiante;
    
    @Column(name = "Jubilados")
    private double jubilados;

    

    public Tarifa() {
    }

    public Tarifa(int id, Cine cine, double mierSabado, double jubilados, double festivos, double estudiante) {
        this.id = id;
        this.cine = cine;
        this.mierSabado = mierSabado;
        this.jubilados = jubilados;
        this.festivos = festivos;
        this.estudiante = estudiante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public double getmierSabado() {
        return mierSabado;
    }

    public void setmierSabado(double mierSabado) {
        this.mierSabado = mierSabado;
    }

    public double getjubilados() {
        return jubilados;
    }

    public void setjubilados(double jubilados) {
        this.jubilados = jubilados;
    }

    public double getFestivos() {
        return festivos;
    }

    public void setFestivos(double festivos) {
        this.festivos = festivos;
    }

    public double getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(double estudiante) {
        this.estudiante = estudiante;
    }

}
