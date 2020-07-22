/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

/**
 *
 * @author HI andres
 */
public class Bodega {
    /**
    * Atributos que seran utilizados en la gestion de la 
    * informacion.
    */
    
    private String nombre;
    private String direccion;
    private String telefono;
    private int numeroDeEmpleados;
    private int muellesDeDesenbarque;
    private int transportes;

    // Constructor sin parametros.
    public Bodega() {
    }

    // Constructor con parametros. 
    public Bodega(String nombre, String direccion, String telefono, int numeroDeEmpleados, int muellesDeDesenbarque, int transportes) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numeroDeEmpleados = numeroDeEmpleados;
        this.muellesDeDesenbarque = muellesDeDesenbarque;
        this.transportes = transportes;
    }
    
    // Getters y Setters
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

    public int getNumeroDeEmpleados() {
        return numeroDeEmpleados;
    }

    public void setNumeroDeEmpleados(int numeroDeEmpleados) {
        this.numeroDeEmpleados = numeroDeEmpleados;
    }

    public int getMuellesDeDesenbarque() {
        return muellesDeDesenbarque;
    }

    public void setMuellesDeDesenbarque(int muellesDeDesenbarque) {
        this.muellesDeDesenbarque = muellesDeDesenbarque;
    }

    public int getTransportes() {
        return transportes;
    }

    public void setTransportes(int transportes) {
        this.transportes = transportes;
    }
}
