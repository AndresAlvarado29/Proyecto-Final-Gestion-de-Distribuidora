/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.idao;

import ec.edu.ups.modelo.Producto;
import java.util.List;

/**
 *
 * @author HI andres
 */
public interface IProductoDAO {
    //Metodos CRUD
    public void create(Producto producto);
    public List<Producto> read(String CodigoProducto);
    public void update(Producto producto);
    public void delete(String codigoProducto);
    
    public List<Producto> listarProductos();
    public List<String> findAll();
    
    public List<Producto> listarProductosPorBodega(String codigoBo);
    
}
