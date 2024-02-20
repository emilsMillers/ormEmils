/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author emils
 */
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cine")
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL)
    private List<Pase> pases;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL)
    private List<Tarifa> tarifas;

    public Cine() {
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Cine(int id, String nombre, String direccion, String telefono, List<Pase> pases, List<Tarifa> tarifas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.pases = pases;
        this.tarifas = tarifas;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Pase> getPases() {
        return pases;
    }

    public void setPases(List<Pase> pases) {
        this.pases = pases;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

}