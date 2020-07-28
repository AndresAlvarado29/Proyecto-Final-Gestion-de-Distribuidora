/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IBodegaDAO;
import ec.edu.ups.modelo.Bodega;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author HI andres
 */
public class BodegaDAO implements IBodegaDAO {

    //archivo binario
    private RandomAccessFile archivo;
    private int tamañoRegistro;
    private Bodega bodega;
    
      //Constructor
    public BodegaDAO() {
        
        tamañoRegistro = 83;
        try {
            archivo = new RandomAccessFile("Datos/Bodegass.dat", "rw");
            tamañoRegistro = 83;
        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }
    
    
    @Override
    public void create(Bodega bodega) {
        try {
            archivo.seek(archivo.length());
            archivo.writeUTF(bodega.getNombre());
            archivo.writeUTF(bodega.getDireccion());
            archivo.writeUTF(bodega.getTelefono());
            
        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:UsuarioDao)");
            e.printStackTrace();

        }
        
    }

    @Override
    public Bodega read(String codigo) {
        return null;

    }

    @Override
    public void update(Bodega bodega) {
        try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String BodegaArchivo = archivo.readUTF();
                
                if (bodega.getNombre().equals(BodegaArchivo)) {
                    archivo.writeUTF(bodega.getDireccion());
                    archivo.writeUTF(bodega.getNombre());
                    archivo.writeUTF(bodega.getTelefono());
                    break;
                    
                }
                salto += 128;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (update: UsuarioDAO)");
            e.printStackTrace();

      }
    }

    @Override
    public void delete(Bodega bodega) {

    }
    
}
