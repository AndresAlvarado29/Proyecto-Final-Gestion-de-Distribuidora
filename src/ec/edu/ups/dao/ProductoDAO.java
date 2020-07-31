/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.idao.IUsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class ProductoDAO implements IProductoDAO{
    
    /**
     * Tamaño del archivo:
     *
     * stock  10 caracteres 
     * nombre  25 caracteres 
     * codigo 5 caracteres 
     * precio  5 caracteres 
     * marca  10 caracteres
     * Fecha de Elaboracion 12 caracteres
     * fecha de caducidad 12 caracteres
     * descripcion 40 caracteres
     * codigoBodega 5 caracteres
     *
     * Total  124 bytes + 10 bytes extras = 134 bytes por registro
     *
     */
    
    
    //archivo binario
    private RandomAccessFile archivo;
    private int tamañoRegistro;
    
      //Constructor
    public ProductoDAO() {
        
        tamañoRegistro = 134;
        try {
            archivo = new RandomAccessFile("Datos/Productos.dat", "rw");
            tamañoRegistro = 134;

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }
    
    
    
    
    @Override
    public void create(Producto producto) {
         try {
            archivo.seek(archivo.length());
            archivo.writeInt(producto.getStock()); 
            archivo.writeUTF(producto.getDescripcion());
            archivo.writeUTF(producto.getNombreDelProducto());
            archivo.writeUTF(producto.getCodigoDelProducto());
            archivo.writeDouble(producto.getPrecio());
            archivo.writeUTF(producto.getFechaDeElaboracion());
            archivo.writeUTF(producto.getFechaDeCaducidad());
            archivo.writeUTF(producto.getMarca());

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:UsuarioDao)");
            e.printStackTrace();

        }
    }

    

    @Override
    public void update(Producto producto) {
           try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String productoArchivo = archivo.readUTF();
                
                if (producto.getCodigoDelProducto().equals(productoArchivo)) {
                    archivo.writeUTF(producto.getDescripcion());
                    archivo.writeUTF(producto.getMarca());
                    archivo.writeUTF(producto.getNombreDelProducto());
                    archivo.writeUTF(producto.getFechaDeCaducidad());
                    archivo.writeUTF(producto.getFechaDeElaboracion());
                    break;
                    
                }
                salto += 129;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (update: UsuarioDAO)");
            e.printStackTrace();

        }

    }

    @Override
    public void delete(String codigoProducto) {

        try {
            Producto p = new Producto(" ", " ", " ", 0, 0, " ", " ", "", "");
            long salto = 0;
            
            while (salto < archivo.length()) {
                
                archivo.seek(salto);
                
                String codigoArchivo = archivo.readUTF();
                System.out.println(codigoArchivo);
                
                archivo.seek(archivo.getFilePointer() - 4);
                
                System.out.println(archivo.getFilePointer());
                
                if (codigoProducto.equals(codigoArchivo.trim())) {
                    
                   
                  
                    archivo.writeUTF(p.getCodigoDelProducto());
                    System.out.println(p.getCodigoDelProducto());
                    archivo.writeUTF(p.getNombreDelProducto());
                    archivo.writeUTF(p.getDescripcion());
                    archivo.writeUTF(p.getFechaDeCaducidad());
                    archivo.writeUTF(p.getFechaDeElaboracion());
                    archivo.writeUTF(p.getMarca());
                    archivo.writeUTF(p.getNombreDelProducto());
            
                    archivo.writeInt(p.getStock());
            
                    archivo.writeDouble(p.getPrecio());

                                      
                  
                    salto=archivo.length()+1;
                    
                }
                salto = salto + 134;
            }
        } catch (IOException e) {
            System.out.println("Error login");
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listarProductos() {
       List<Producto> modelo = new ArrayList<Producto>();
        
        
        try {
        int salto = 0;
        while (salto < archivo.length()) {
            archivo.seek(salto);
                    
                    String codigoProducto = archivo.readUTF();
                    String descripcion = archivo.readUTF();
                    String nombreDelProducto = archivo.readUTF();

                    int Stock = archivo.readInt();
                    
                    double precio=archivo.readDouble();
                    
                    String fechaDeElaboracion = archivo.readUTF();
                    String fechaDeCaducidad = archivo.readUTF();
                    String marca = archivo.readUTF();
                    String codigoBodega = archivo.readUTF();
                    
                    Producto producto = new Producto(codigoProducto, descripcion, nombreDelProducto, Stock, precio, fechaDeElaboracion, fechaDeCaducidad, marca, codigoBodega);
                    if(codigoProducto.trim().equalsIgnoreCase("")||codigoProducto.trim().contains("f")==true){
                        
                    }else{
                       modelo.add(producto);
                    }
                    
                    salto = salto + 134;
                    
        }
        return modelo;
        } catch (IOException e) {
            System.out.println("Error listar producto");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> findAll() {

        List<String> Lista = new ArrayList<String>();
        
        try {
            int salto = 0;
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String codigoProducto = archivo.readUTF();
                
                Lista.add(codigoProducto.trim());
                
               
                salto = salto + 134;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Lista;
        
    }

    @Override
    public List<Producto> listarProductosPorBodega(String codigoBo) {

        List<Producto> modelo = new ArrayList<Producto>();
        
        
        
        try {
        int salto = 0;
        while (salto < archivo.length()) {
            archivo.seek(salto);
                    
                    String codigoProducto = archivo.readUTF();
                    String descripcion = archivo.readUTF();
                    String nombreDelProducto = archivo.readUTF();

                    int Stock = archivo.readInt();
                    
                    double precio=archivo.readDouble();
                    
                    String fechaDeElaboracion = archivo.readUTF();
                    String fechaDeCaducidad = archivo.readUTF();
                    String marca = archivo.readUTF();
                    String codigoBodega = archivo.readUTF();
                    
                    
                    Producto producto=new Producto(codigoProducto, descripcion, nombreDelProducto, Stock, precio, fechaDeElaboracion, fechaDeCaducidad, marca, codigoBodega);
                    System.out.println(codigoProducto.trim().contains("f"));
                    if(codigoProducto.trim().equalsIgnoreCase("")||codigoProducto.trim().contains("f")==true){
                        
                    }else{
                        if(codigoBodega.trim().equalsIgnoreCase(codigoBo)){
                            modelo.add(producto);
                        }
                       
                    }
                    
                    salto = salto + 134;
                    
        }
        return modelo;
        } catch (IOException e) {
            System.out.println("Error listar producto");
            e.printStackTrace();
        }
        return null;
    }

  

    @Override
    public List<Producto> read(String CodigoProducto) {
        
        List<Producto> modelo = new ArrayList<Producto>();

        try {
        int salto = 0;
        while (salto < archivo.length()) {
            archivo.seek(salto);
                    
                    String codigoProducto = archivo.readUTF();
                    String descripcion = archivo.readUTF();
                    String nombreDelProducto = archivo.readUTF();

                    int Stock = archivo.readInt();
                    
                    double precio=archivo.readDouble();
                    
                    String fechaDeElaboracion = archivo.readUTF();
                    String fechaDeCaducidad = archivo.readUTF();
                    String marca = archivo.readUTF();
                    String codigoBodega = archivo.readUTF();
                    
                    
                    Producto producto = new Producto(codigoProducto, descripcion, nombreDelProducto, Stock, precio, fechaDeElaboracion, fechaDeCaducidad, marca, codigoBodega);
                    if(codigoProducto.trim().equalsIgnoreCase(CodigoProducto)){
                         modelo.add(producto);
                    }else{
                      
                    }
                
                    salto=salto + 134;
                    
        }
        return modelo;
        } catch (IOException e) {
            System.out.println("Error listar producto");
            e.printStackTrace();
        }
        return null;
    }

   
}
