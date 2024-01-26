/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author emils
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "CINE")
public class Cine {
    @Id
    private String nombre;
    private String calle;
    private int numero;
    private String telefono;

    @OneToMany(mappedBy = "cine")
    private Set<Tarifa> tarifas;

    @OneToMany(mappedBy = "cine")
    private Set<Pase> pases;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(Set<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public Set<Pase> getPases() {
        return pases;
    }

    public void setPases(Set<Pase> pases) {
        this.pases = pases;
    }

    public Cine(String nombre, String calle, int numero, String telefono) {
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.telefono = telefono;
    }
    
    public Cine(){}

    
}