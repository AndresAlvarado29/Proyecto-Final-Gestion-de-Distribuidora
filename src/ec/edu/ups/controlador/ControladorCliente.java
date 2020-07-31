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
   //Objeto Cliente
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

    //Crea un Cliente atraves de la vista y lo agrega al archivo creado en el DAO
    public Cliente crear(Cliente cliente) {
        clientesDAO.create(cliente);
        return cliente;
    }
    public void registrar(String nombre,String RUC, String direccion, String telefono,String correo, String codigoDeCliente) {
        cliente = new Cliente(nombre, RUC,direccion, telefono,correo, codigoDeCliente);
        clientesDAO.create(cliente);
    }
    
    //Llama al DAO para obtener el cliente y lo muestra en pantalla atraves de la vista
   
    
    //Llama al DAO para actualizar un Cliente
    public void actualizar(Cliente cliente) {
        clientesDAO.update(cliente);
    }
    
    //Llama al DAO para eliminar un Cliente
    public void eliminar(Cliente cliente) {
        clientesDAO.delete(cliente);
    } 
}
