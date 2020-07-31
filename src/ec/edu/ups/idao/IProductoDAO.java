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

    public Producto read(int codigoProducto);

    public void update(Producto producto);

    public void delete(Producto producto);

    public int obtenerUltimoCodigo();
    
    public int obtenerStockBodega(int codigoBodega);

    public List<Producto> listarTodosProductos();

    public List<Producto> listarPorBodega(int codigoBodega);
    
}
