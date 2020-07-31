/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.idao.IUsuarioDAO;
import ec.edu.ups.modelo.Bodega;
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
    
     private int codigo;
     private BodegaDAO bodegaDao;
    
      //Constructor
    public ProductoDAO() {
        
        codigo = 0;
        bodegaDao = new BodegaDAO();
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
            archivo.writeInt(producto.getCodigoProducto());
            archivo.writeUTF(producto.getNombreDelProducto());
            archivo.writeDouble(producto.getPrecio());
            archivo.writeInt(producto.getStock());
            archivo.writeUTF(producto.getDescripcion());
            archivo.writeInt(producto.getBodega().getCodigo());

        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura create: ProductoDao");
        }
    }

    @Override
    public Producto read(int codigo) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == codigo) {
                    Producto producto = new Producto(codigoA, archivo.readUTF().trim(), archivo.readUTF().trim(),archivo.readInt(), archivo.readDouble(),archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF().trim());
                    Bodega bodega = bodegaDao.read(archivo.readInt());
                    producto.asignarBodega(bodega);
                    return producto;
                }

                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura read:ProductoDao");
        }
        return null;

    }

    @Override
    public void update(Producto producto) {
        int salto = 0;
        int codi = producto.getCodigoProducto();
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == codi) {

                    archivo.writeUTF(producto.getNombreDelProducto());
                    archivo.writeDouble(producto.getPrecio());
                    archivo.writeInt(producto.getStock());
                    archivo.writeUTF(producto.getDescripcion());

                    break;
                }
                salto += tamañoRegistro;
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura o escritura(Update: ProductoDao)");
        }
    }

    @Override
    public void delete(Producto producto) {
        try {
            int cod = producto.getCodigoProducto();
            int salto = 0;
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == cod) {
                    archivo.seek(salto);
                    archivo.writeInt(0);
                    archivo.writeUTF(llenarEspacios(25));
                    archivo.writeDouble(0);
                    archivo.writeInt(0);
                    archivo.writeUTF(llenarEspacios(80));
                    archivo.writeInt(0);
                    break;
                }
                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error  Delete : ProductoDao");
        }
    }

    public String llenarEspacios(int espacios) {
        String aux = "";
        return String.format("%-" + espacios + "s", aux);
    }

    @Override
    public List<Producto> listarTodosProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            int salto = 0;

            while (salto < archivo.length()) {
                archivo.seek(salto);

                int valor = archivo.readInt();
                if (valor > 0) {
                    Producto producto = new Producto(valor, archivo.readUTF().trim(), archivo.readUTF().trim(),archivo.readInt(), archivo.readDouble(),archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF().trim());
                    Bodega bodega = bodegaDao.read(archivo.readInt());
                    producto.asignarBodega(bodega);
                    listaProductos.add(producto);

                }

                salto += tamañoRegistro;
            }
            return listaProductos;
        } catch (IOException ex) {
            System.out.println("error listarTodosProductos : ProductoDao");
        }
        return listaProductos;

    }

    @Override
    public int obtenerUltimoCodigo() {
 
    boolean f=false;
        try {
             long pos= archivo.length() - tamañoRegistro;
             if (archivo.length()==tamañoRegistro){
             archivo.seek(pos);
             codigo=archivo.readInt();
             }else if (archivo.length() > tamañoRegistro) {
              
              
            while(f==false){
                archivo.seek(pos);
               
               if (archivo.readInt()!=0){
                   break;
               }
                pos-=tamañoRegistro;
            }
                archivo.seek(pos);
                codigo=archivo.readInt();
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura");
        }

        return codigo;
    
    }

    @Override
    public List<Producto> listarPorBodega(int codigo) {
        List<Producto> listar = new ArrayList();
        int salto = 125;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == codigo) {
                    archivo.seek(salto);
                    archivo.seek(salto - 125);
                    int c = archivo.readInt();
                    if (c != 0) {
                        Producto producto = new Producto(codigoA, archivo.readUTF().trim(), archivo.readUTF().trim(),archivo.readInt(), archivo.readDouble(),archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF().trim());
                        Bodega bodega = bodegaDao.read(archivo.readInt());
                        producto.asignarBodega(bodega);
                        listar.add(producto);
                    }
                }

                salto += tamañoRegistro;
            }
            return listar;
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura listarPorBodega :ProductoDao");
        }
        return listar;

    }

    @Override
    public int obtenerStockBodega(int codigoBodega) {
        int stock=0;
         Bodega bodega=bodegaDao.read(codigo);
         int codi=bodega.getCodigo();
        int salto = 134;

        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == codi) {
                    stock++;
                }
                salto += tamañoRegistro;
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura o escritura(obtenerStockBodega: ProductoDao)");
        }
        return stock;
    }
}
