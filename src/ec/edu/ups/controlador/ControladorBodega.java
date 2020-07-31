/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;

import ec.edu.ups.dao.BodegaDAO;
import ec.edu.ups.dao.ProductoDAO;
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
    
        private ProductoDAO productoDao;
        private Bodega bodega;
        private BodegaDAO bodegaDao;
  
  public ControladorBodega( BodegaDAO bodegaDao , ProductoDAO productoDao) {
        this.bodegaDao = bodegaDao;
        this.productoDao=productoDao;
    }
  
  public void crearBodega(String nombre, String direccion, String telefono,int codigoBodega){
  this.bodega= new Bodega (nombre,direccion,telefono, codigoBodega);
  bodegaDao.create(bodega);
  }
  
  public void eliminarBodega(int codigo){
  Bodega bode= bodegaDao.read(codigo);
  bodegaDao.delete(bode);
  }
  
  public void actualizarBodega(String nombre, String direccion, String telefono,int codigoBodega){
   this.bodega= new Bodega (nombre,direccion,telefono, codigoBodega);
  bodegaDao.update(bodega);
  }
  public int obtenerStockBodega(String nombreBodega){
      Bodega b = bodegaDao.buscarPorNombre(nombreBodega);
      return productoDao.obtenerStockBodega(b.getCodigo());
  
  }
    
    public Bodega buscarPorNombre (String nombre){
      return bodegaDao.buscarPorNombre(nombre);
 
        }
  
    public void actualizarStock(String nombre){
      
    bodegaDao.updateStock(obtenerStockBodega(nombre), nombre);
    }
  
  
    public List<Bodega> listarBodegas(){
     return bodegaDao.listarTodasBodegas();
  
        }
    
    
      public int obtenerSiguienteCodigo() {
        int codigo =  bodegaDao.obtenerUltimoCodigo();

        return codigo;
    }
}
