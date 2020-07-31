/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IBodegaDAO;
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
public class BodegaDAO implements IBodegaDAO {

    //archivo binario
    private int codigo;
    private RandomAccessFile archivo;
    private int tamañoRegistro;
    private Bodega bodega;
    
      //Constructor
    public BodegaDAO() {
        
     /**
     * Tamaño del archivo:
     *
     * nombre  25 caracteres 
     * direccion  50 caracteres 
     * telefono 10 caracteres 
     * codigo 5 caracteres
     * numero de empleados  4 caracteres 
     * muelles de desembarque  4 caracteres
     * transportes 4 caracteres
     *
     * Total  97 bytes + 10 bytes extras = 107 bytes por registro
     *
     */
        codigo = 0;
        tamañoRegistro = 107;
        try {
            archivo = new RandomAccessFile("Datos/Bodegass.dat", "rw");
            tamañoRegistro = 107;
        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }
    
    
     @Override
    public void create(Bodega bodega) {
        try {
            archivo.seek(archivo.length());
            archivo.writeInt(bodega.getCodigo());
            archivo.writeUTF(bodega.getNombre());
            archivo.writeUTF(bodega.getDireccion());
            archivo.writeUTF(bodega.getTelefono());
            archivo.writeInt (bodega.getStock());

        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura create: BodegaDao");
        }
    }

    @Override
    public Bodega read(int codigo) {
        int salto = 0;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == codigo) {
                    Bodega bodega = new Bodega(archivo.readUTF().trim(), archivo.readUTF().trim(),
                            archivo.readUTF().trim(), codigoA);

                    return bodega;
                }

                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura read:BodegaDao");
        }
        return null;

    }

    @Override
    public void update(Bodega bodega) {
        int salto = 0;
        int codi = bodega.getCodigo();
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == codi) {

                    archivo.writeUTF(bodega.getNombre());
                    archivo.writeUTF(bodega.getDireccion());
                    archivo.writeUTF(bodega.getTelefono());
                    archivo.writeInt (bodega.getStock());

                    break;
                }
                salto += tamañoRegistro;
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura o escritura Update :BodegaDao");
        }
    }

    @Override
    public void delete(Bodega bodega) {
        try {
            int codi = bodega.getCodigo();
            int salto = 0;
            while (salto < archivo.length()) {
                archivo.seek(salto);
                int codigoA = archivo.readInt();
                if (codigoA == codi) {
                    archivo.seek(salto);
                    archivo.writeInt(0);
                    archivo.writeUTF(llenarEspacios(25));
                    archivo.writeUTF(llenarEspacios(60));
                    archivo.writeUTF(llenarEspacios(25));
                    archivo.writeInt(0);
                    break;
                }
                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error  Delete : BodegaDao");
        }
    }

    public String llenarEspacios(int espacios) {
        String aux = "";
        return String.format("%-" + espacios + "s", aux);
    }

    @Override
    public List<Bodega> listarTodasBodegas() {
        List<Bodega> listaBodegas = new ArrayList<>();
        try {
            int salto = 0;

            while (salto < archivo.length()) {
                archivo.seek(salto);

                int valor = archivo.readInt();
                if (valor != 0) {

                    Bodega bodega = new Bodega(archivo.readUTF().trim(), archivo.readUTF().trim(),
                            archivo.readUTF().trim(), valor);
                    bodega.setStock(archivo.readInt());
                    listaBodegas.add(bodega);

                }

                salto += tamañoRegistro;
            }
            return listaBodegas;
        } catch (IOException ex) {
            System.out.println("error listarTodosProductos : ProductoDao");
        }
      return listaBodegas;

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
    public Bodega buscarPorNombre(String nombre) {
        
        int salto = 4;
        try {
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String nomA = archivo.readUTF().trim();
                if (nomA.equals(nombre)) {
                    archivo.seek(salto-4);
                    Bodega bodega = new Bodega(archivo.readUTF().trim(), archivo.readUTF().trim(),
                            archivo.readUTF().trim(), archivo.readInt());

                    return bodega;
                }

                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura read:BodegaDao");
        }
        return null;
      
    }

    @Override
    public void updateStock(int cantidad, String nombre) {
     int salto=4;
        try {
             
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String nombreBodega =archivo.readUTF().trim();
                if (nombreBodega.equals(nombre)) {
                     archivo.seek((salto-4)+120);
                    archivo.writeInt(cantidad);
                    break;
                }
                salto += tamañoRegistro;
            }
        } catch (IOException ex) {
            System.out.println("Error de lectura o escritura UpdateStock :BodegaDao");
        }
    }

    
}
