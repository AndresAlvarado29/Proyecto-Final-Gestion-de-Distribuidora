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
public class Cliente {
    /**
    * Atributos que seran utilizados en la gestion de la 
    * informacion.
    */
    
    private String NombreDelCliente;
    private String RUC;
    private String Direccion;
    private String Telefono;
    private String Correo;
    private String CodigoDeCliente;
    
    // Constructor sin parametros.
    public Cliente() {
    }
     
    // Constructor con parametros.
    public Cliente(String NombreDelCliente, String RUC, String Direccion, String Telefono, String Correo, String CodigoDeCliente) {
        this.NombreDelCliente = NombreDelCliente;
        this.RUC = RUC;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Correo = Correo;
        this.CodigoDeCliente = CodigoDeCliente;
    }
    
    // Getters y Setters
    public String getNombreDelCliente() {
        return NombreDelCliente;
    }

    public void setNombreDelCliente(String NombreDelCliente) {
        this.NombreDelCliente = NombreDelCliente;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getCodigoDeCliente() {
        return CodigoDeCliente;
    }

    public void setCodigoDeCliente(String CodigoDeCliente) {
        this.CodigoDeCliente = CodigoDeCliente;
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
    
    // Equals Y HashCode
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.CodigoDeCliente);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.CodigoDeCliente, other.CodigoDeCliente)) {
            return false;
        }
        return true;
    } 
}
