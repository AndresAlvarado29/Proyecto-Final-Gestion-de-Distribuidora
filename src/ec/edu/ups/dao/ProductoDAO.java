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
     *
     * Total  119 bytes + 10 bytes extras = 129 bytes por registro
     *
     */
    
    
    //archivo binario
    private RandomAccessFile archivo;
    private int tamañoRegistro;
    
      //Constructor
    public ProductoDAO() {
        
        tamañoRegistro = 129;
        try {
            archivo = new RandomAccessFile("Datos/Productos.dat", "rw");
            tamañoRegistro = 129;

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }
    
    
    
    
    @Override
    public void create(Producto producto) {
         try {
            archivo.seek(archivo.length());
            archivo.writeUTF(producto.getNombreDelProducto());
            archivo.writeUTF(producto.getDescripcion());
            archivo.writeUTF(producto.getMarca());
            archivo.writeUTF(producto.getFechaDeElaboracion());
            archivo.writeUTF(producto.getFechaDeCaducidad());

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:UsuarioDao)");
            e.printStackTrace();

        }
    }

    
    
    @Override
    public Producto read(String codigoDelProducto) {
                try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String codigoProductoArchivo = archivo.readUTF();
                
                if (codigoDelProducto.equals(codigoProductoArchivo)) {
                    return new Producto(codigoDelProducto, archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readInt(), archivo.readDouble(), archivo.readUTF(), archivo.readUTF(), archivo.readUTF());
                    
                }
                salto += 129;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (read: UsuarioDAO)");
            e.printStackTrace();

        }
        return null;

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
    public void delete(Producto producto) {

    }
    
}
