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
    private String codigoBodega;
    

    // Constructor sin parametros.
    public Bodega() {
    }

    // Constructor con parametros. 
    public Bodega(String nombre, String direccion, String telefono,String codigoBodega) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.codigoBodega = codigoBodega;
        
    }
    
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = this.validarEspacios(nombre, 25);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = this.validarEspacios(direccion, 50);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = this.validarEspacios(telefono, 10);
    }

    public String getCodigo() {
        return codigoBodega;
    }

    public void setCodigo(String codigoBodega) {
        this.codigoBodega = this.validarEspacios(codigoBodega, 5);
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
        hash = 89 * hash + Objects.hashCode(this.codigoBodega);
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
        if (!Objects.equals(this.codigoBodega, other.codigoBodega)) {
            return false;
        }
        return true;
    }

    
 
}
