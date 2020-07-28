/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.modelo;

import java.util.Objects;

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
    
     public String validarEspacios(String cadena, int longitud) {
        if (cadena.length() == longitud) {
            return cadena;

        } else {
            if (cadena.length() < longitud) {
                return llenarEspacios(cadena, longitud);

            } else {
                return cortarEspacios(cadena, longitud);

            }
        }
    }

    public String llenarEspacios(String cadena, int longitud) {
        return String.format("%-" + longitud + "s", cadena);

    }

    public String cortarEspacios(String cadena, int longitud) {
        return cadena.substring(0, longitud);

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bodega other = (Bodega) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bodega{" + "nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", numeroDeEmpleados=" + numeroDeEmpleados + ", muellesDeDesenbarque=" + muellesDeDesenbarque + ", transportes=" + transportes + '}';
    }
    
    
}
