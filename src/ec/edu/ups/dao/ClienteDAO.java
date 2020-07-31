/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IClienteDAO;
import ec.edu.ups.modelo.Cliente;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import ec.edu.ups.idao.IUsuarioDAO;
import ec.edu.ups.modelo.Usuario;

/**
 *
 * @author HI andres
 */
public class ClienteDAO implements IClienteDAO{
    
    /**
     * Tamaño del archivo:
     *
     * nombre  25 caracteres 
     * RUC  13 caracteres 
     * direccion 30 caracteres 
     * telefono  10 caracteres 
     * correo  50 caracteres
     * codigo 5 caracteres
     * 
     * Total  138 bytes + 10 bytes extras = 148 bytes por registro
     *
     */
    private RandomAccessFile archivo;
    private int tamañoRegistro;
    private IUsuarioDAO usuarioDAO;

    //Constructor
    public ClienteDAO(IUsuarioDAO usuarioDAO) {
         tamañoRegistro = 148;
        try {
            archivo = new RandomAccessFile("Datos/Clientes.dat", "rw");
            tamañoRegistro = 148;

        } catch (IOException e) {
            System.out.println("Error de lectura y escritura");
            e.printStackTrace();
        }
    }

    @Override
    public void create(Cliente cliente) {
        try {
            archivo.seek(archivo.length());
            archivo.writeUTF(cliente.getCorreo());
            archivo.writeUTF(cliente.getDireccion());
            archivo.writeUTF(cliente.getNombreDelCliente());
            archivo.writeUTF(cliente.getRUC());
            archivo.writeUTF(cliente.getTelefono());

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:UsuarioDao)");
            e.printStackTrace();

        }
    }

    @Override
    public Cliente read(String RUC) {
        
        try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String RUCArchivo = archivo.readUTF();
                
                if (RUC.equals(RUCArchivo)) {
                    return new Cliente(RUC, archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF().trim(), archivo.readUTF(),archivo.readUTF());
                    
                }
                salto += 148;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (read: UsuarioDAO)");
            e.printStackTrace();

        }
        return null;
    }


    @Override
    public void update(Cliente cliente) {
         try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String clienteArchivo = archivo.readUTF();
                
                if (cliente.getCodigoDeCliente().equals(clienteArchivo)) {
                    archivo.writeUTF(cliente.getCorreo());
                    archivo.writeUTF(cliente.getDireccion());
                    archivo.writeUTF(cliente.getNombreDelCliente());
                    archivo.writeUTF(cliente.getRUC());
                    archivo.writeUTF(cliente.getTelefono());
                    break;
                    
                }
                salto += 148;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (update: UsuarioDAO)");
            e.printStackTrace();

        }

    }

    @Override
    public void delete(Cliente cliente) {

         try {
            String codigoCliente = cliente.getCodigoDeCliente();
            int salto = 0;
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String codigoClienteArchivo = archivo.readUTF();
                if (codigoCliente.trim().equals(codigoClienteArchivo.trim())) {
                    archivo.writeUTF(llenarEspacios(10));
                    archivo.writeUTF(llenarEspacios(25));
                    archivo.writeUTF(llenarEspacios(25));
                    archivo.writeUTF(llenarEspacios(50));

                    break;
                }
                salto += tamañoRegistro;
            }

        } catch (IOException ex) {
            System.out.println("Error delete usuario");
        }
    }
    
    
     public String llenarEspacios(int espacios) {
        String aux = "";
        return String.format("%-" + espacios + "s", aux);
    }
}
