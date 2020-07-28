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
import java.util.Collection;

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
         try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String cedulaArchivo = archivo.readUTF();
                
                if (cedula.equals(cedulaArchivo)) {
                    return new Usuario(cedula, archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF(), false);
                    
                }
                salto += 128;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (read: UsuarioDAO)");
            e.printStackTrace();

        }
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
        
    }
    
      @Override
    public Collection<Usuario> findAll() {
        return null;
    }
    
    @Override
    public Usuario login(String cedula, String contraseña) {
        try {
            int salto = 66;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String correoArchivo = archivo.readUTF();
                String contraseñaArchivo = archivo.readUTF();
                
                if (correoArchivo.trim().equals(cedula) && contraseñaArchivo.equals(contraseña)) {
                    archivo.seek(salto - 66);
                    return new Usuario(archivo.readUTF(), archivo.readUTF().trim(), archivo.readUTF().trim(), cedula, contraseña, false);
                    
                }
                salto += 128;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (login: UsuarioDAO)");
            e.printStackTrace();

        }
        return null;
    }
    
}
