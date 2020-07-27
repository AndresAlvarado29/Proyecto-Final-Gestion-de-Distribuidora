/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IUsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author HI andres
 */
public class UsuarioDAO implements IUsuarioDAO {
    
    /**
     * Tamaño del archivo:
     *
     * cedula  10 caracteres 
     * nombre  25 caracteres 
     * apellido 25 caracteres 
     * correo  50 caracteres 
     * contraseña  8 caracteres
     *
     * Total  118 bytes + 10 bytes extras = 128 bytes por registro
     *
     */
    
    
     //archivo binario
    private RandomAccessFile archivo;

    //Constructor
    public UsuarioDAO() {
        try {
            archivo = new RandomAccessFile("Datos/Usuarios.dat", "rw");

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }

    @Override
    public void create(Usuario usuario) {
         try {
            archivo.seek(archivo.length());
            archivo.writeUTF(usuario.getCedula());
            archivo.writeUTF(usuario.getNombre());
            archivo.writeUTF(usuario.getApellido());
            archivo.writeUTF(usuario.getCorreo());
            archivo.writeUTF(usuario.getContraseña());

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:UsuarioDao)");
            e.printStackTrace();

        }

    }

    @Override
    public Usuario read(String cedula) {
        return null;
    }

    @Override
    public void update(Usuario usuario) {
         try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String cedulaArchivo = archivo.readUTF();
                
                if (usuario.getCedula().equals(cedulaArchivo)) {
                    archivo.writeUTF(usuario.getNombre());
                    archivo.writeUTF(usuario.getApellido());
                    archivo.writeUTF(usuario.getCorreo());
                    archivo.writeUTF(usuario.getContraseña());
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
    public void delete(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
