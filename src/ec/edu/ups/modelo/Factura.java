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
public class Factura {
    
    /**
    * Atributos que se utilizaran en los diferentes metodos de gestion de
    * informacion.
    */
    private String fechaDeSalida;
    private String RUC;
    private String DireccionAdministracion;
    private String TelefonoAdministracion;
    private boolean EstadoDeFactura;
    
    //Constructor sin parametros
    public Factura() {    
        
    }
    
    //Constructor con parametros
    public Factura(String fechaDeSalida, String RUC, String DireccionAdministracion, String TelefonoAdministracion, boolean EstadoDeFactura) {
        this.fechaDeSalida = fechaDeSalida;
        this.RUC = RUC;
        this.DireccionAdministracion = DireccionAdministracion;
        this.TelefonoAdministracion = TelefonoAdministracion;
        this.EstadoDeFactura = EstadoDeFactura;
    }
    

    //Getters y setters
    public String getFechaDeSalida() {
        return fechaDeSalida;
    }

    
    public void setFechaDeSalida(String fechaDeSalida) {
        this.fechaDeSalida = this.validarEspacios(fechaDeSalida, 12);
    }

    
    public String getRUC() {
        return RUC;
    }

    
    public void setRUC(String RUC) {
        this.RUC = this.validarEspacios(RUC, 13);
    }

    public String getDireccionAdministracion() {
        return DireccionAdministracion;
    }

    
    public void setDireccionAdministracion(String DireccionAdministracion) {
        this.DireccionAdministracion = this.validarEspacios(DireccionAdministracion, 25);
    }

    
    public String getTelefonoAdministracion() {
        return TelefonoAdministracion;
    }

    
    public void setTelefonoAdministracion(String TelefonoAdministracion) {
        this.TelefonoAdministracion = this.validarEspacios(TelefonoAdministracion, 10);
    }

    public boolean isEstadoDeFactura() {
        return EstadoDeFactura;
    }

    
    public void setEstadoDeFactura(boolean EstadoDeFactura) {
       String Estado = Boolean.toString(EstadoDeFactura);
       Estado = validarEspacios(Estado, 12);
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
    
    //Hashcode y Equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.RUC);
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
        final Factura other = (Factura) obj;
        if (!Objects.equals(this.RUC, other.RUC)) {
            return false;
        }
        return true;
    }
    
    //toString
    @Override
    public String toString() {
        return "Factura{" + "fechaDeSalida=" + fechaDeSalida + ", RUC=" + RUC + ", DireccionAdministracion=" + DireccionAdministracion + ", TelefonoAdministracion=" + TelefonoAdministracion + ", EstadoDeFactura=" + EstadoDeFactura + '}';
    }
    
}
