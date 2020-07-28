/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.controlador;
import ec.edu.ups.idao.IUsuarioDAO;
import ec.edu.ups.modelo.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class ControladorUsuario {
    
    //Objetos modelo
    private Usuario usuario;
    
    //Objetos DAO
    private IUsuarioDAO usuarioDAO;
    
     //Constructor sin parametros
    public ControladorUsuario() {

    }
    
    //Constructor con parametros
    public ControladorUsuario(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        
    }
    
    //Crea un Usuario con los datos de la vista y lo agrega al archivo creado en el DAO
    public void registrar(String cedula, String nombre, String apellido, String correo, String contraseña) {
        usuario = new Usuario(cedula, nombre, apellido, correo, contraseña, true);
        usuarioDAO.create(usuario);
    }

    //Retorna el usuario logeado
    public Usuario verUsuario() {
        return usuario;
    }
    
     //Genera un Usuario con los datos ingresada atraves de la vista y lo remplaza atraves del DAO
    public void actualizar(String cedula, String nombre, String apellido, String correo, String contraseña) {
        usuario = new Usuario(cedula, nombre, apellido, correo, contraseña, true);
        usuarioDAO.update(usuario);
    }
    
    //Genera un Usuario con la clave ingresada atraves de la vista y lo elimina atraves del DAO
    public void eliminar(String cedula) {
        usuarioDAO.delete(usuario);
    }    
    
    //Genera un Usuario atraves de la vista y compara con cada Usuario existente en el DAO
    public Usuario Autentificar(Usuario usuario) {
        Collection<Usuario> usuarios = usuarioDAO.findAll();
        for (Usuario usuario1 : usuarios) {
            if (usuario.equals(usuario1)) {
                return usuario1;
            }
        }
        return null;
    }

    //Muestra en pantalla todos los usuarios existentes en el DAO atraves de la vista
    public Collection<Usuario>  verUsuarios() {
        Collection<Usuario> usuarios;
        usuarios = usuarioDAO.findAll();
        if (!usuarios.isEmpty()) {
            return usuarios;
        } else {
            return null;
        }
    }
    
    //inicio de secion
    public boolean validarUsuario(String correo, String contraseña) {
        usuario = usuarioDAO.login(correo, contraseña);
        if (usuario != null) {
            return true;
        } else {
            return false;
        }
    }
}
