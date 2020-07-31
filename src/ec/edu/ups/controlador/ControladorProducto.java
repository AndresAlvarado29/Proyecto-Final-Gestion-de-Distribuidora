/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;
import ec.edu.ups.dao.BodegaDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.idao.IProductoDAO;
import ec.edu.ups.modelo.Bodega;
import ec.edu.ups.modelo.Producto;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class ControladorProducto {
    //Objeto Producto
        private Bodega bodega;
        private Producto producto;
        private BodegaDAO bodegaDao;
        private ProductoDAO productoDao;

    public ControladorProducto (BodegaDAO bodegaDao, ProductoDAO productoDao) {
        this.bodegaDao = bodegaDao;
        this.productoDao = productoDao;
    }
    
    public void crearProducto (int codigoProducto,String descripcion, String nombreDelProducto, int Stock, double precio, String fechaDeElaboracion, String fechaDeCaducidad, String marca, int codigoBodega){
        this.producto= new Producto(codigoProducto,descripcion, nombreDelProducto,  Stock, precio,fechaDeElaboracion, fechaDeCaducidad, marca);
        bodega=this.bodegaDao.read(codigoBodega);
        producto.asignarBodega(bodega);
        productoDao.create(producto);
        
    }
    
    public void eliminarProducto(Producto producto){
        productoDao.delete(producto);
    }
    public Producto buscarProducto (int codigo){
        Producto prod = productoDao.read(codigo);
    return prod;
    }
    public void actualizarProducto(int codigoProducto,String descripcion, String nombreDelProducto, int Stock, double precio, String fechaDeElaboracion, String fechaDeCaducidad, String marca, int codigoBodega){
         this.producto= new Producto(codigoProducto,descripcion, nombreDelProducto,  Stock, precio,fechaDeElaboracion, fechaDeCaducidad, marca);
        bodega=this.bodegaDao.read(codigoBodega);
        producto.asignarBodega(bodega);
        productoDao.update(producto);
    }
    
    public List<Producto> listarProductos(){
        return productoDao.listarTodosProductos();
    
    }
    public List<Producto> listarProductosBodega(int codigoBodega){
        return productoDao.listarPorBodega(codigoBodega);
    
    }
      public int obtenerSiguienteCodigo() {
        int codigo =  productoDao.obtenerUltimoCodigo();

        return codigo;
    }
}
