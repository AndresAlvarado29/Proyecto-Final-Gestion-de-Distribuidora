/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;
import ec.edu.ups.idao.IClienteDAO;
import ec.edu.ups.modelo.Cliente;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class ControladorCliente {
   //Objeto Telefono
    private Cliente cliente;
    //Objetos DAO
    private IClienteDAO clientesDAO ;

    //Constructor sin parametros
    public ControladorCliente() {
        
    }

    //Constructor con parametros
    public ControladorCliente(IClienteDAO clientesDAO) {
        this.clientesDAO = clientesDAO;
    }

    //Crea un Telefono atraves de la vista y lo agrega al archivo creado en el DAO
    public Cliente crear(Cliente cliente) {
        clientesDAO.create(cliente);
        return cliente;
    }
    
    //Llama al DAO para obtener Telefono y lo muestra en pantalla atraves de la vista
   
    
    //Llama al DAO para actualizar un Telefono
    public void actualizar(Cliente cliente) {
        clientesDAO.update(cliente);
    }
    
    //Llama al DAO para eliminar un Telefono
    public void eliminar(Cliente cliente) {
        clientesDAO.delete(cliente);
    } 
}
