/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.idao.IBodegaDAO;
import ec.edu.ups.modelo.Bodega;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class ControladorBodega {
    
     //Objeto Telefono
    private Bodega bodega;
    //Objetos DAO
    private IBodegaDAO bodegasDAO ;

    //Constructor sin parametros
    public ControladorBodega() {
        
    }

    //Constructor con parametros
    public ControladorBodega(IBodegaDAO bodegasDAO) {
        this.bodegasDAO = bodegasDAO;
    }
    
    //Crea un Telefono atraves de la vista y lo agrega al archivo creado en el DAO
    public Bodega crear(Bodega bodega) {
        bodegasDAO.create(bodega);
        return bodega;
    }
    
    public void registrar(String codigo, String nombre, String direccion, String telefono) {
        bodega = new Bodega(codigo, nombre, direccion, telefono);
        bodegasDAO.create(bodega);
    }
    
    //Llama al DAO para obtener Telefono y lo muestra en pantalla atraves de la vista
    
    
    //Llama al DAO para actualizar un Telefono
    public void actualizar(Bodega bodega) {
        bodegasDAO.update(bodega);
    }
    
    //Llama al DAO para eliminar un Telefono
    public void eliminar(Bodega bodega) {
        bodegasDAO.delete(bodega);
    }
    /*public String obtenerSiguienteCodigo(){
   /* int codigo = bodegasDAO.obtenerUltimoCodigo();
    return++codigo+"";
    }*/
     
    
}
