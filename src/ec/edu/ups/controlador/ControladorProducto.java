/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;
import ec.edu.ups.idao.IProductoDAO;
import ec.edu.ups.modelo.Producto;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class ControladorProducto {
    //Objeto Producto
    private Producto producto;
    //Objetos DAO
    private IProductoDAO productosDAO ;

    //Constructor sin parametros
    public ControladorProducto() {
        
    }

    //Constructor con parametros
    public ControladorProducto(IProductoDAO productosDAO) {
        this.productosDAO = productosDAO;
    }

    //Crea un Producto atraves de la vista y lo agrega al archivo creado en el DAO
    public Producto crear(Producto producto) {
        productosDAO.create(producto);
        return producto;
    }
    
    //Llama al DAO para obtener Producto y lo muestra en pantalla atraves de la vista
    
    
    //Llama al DAO para actualizar un Producto
    public void actualizar(Producto producto) {
        productosDAO.update(producto);
    }
    
    //Llama al DAO para eliminar un Producto
    public void eliminar(String codigoProducto) {
        productosDAO.delete(codigoProducto);
    }
}
