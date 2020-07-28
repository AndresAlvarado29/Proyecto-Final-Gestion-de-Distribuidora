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
     * telefono  15 caracteres 
     * correo  50 caracteres
     * codigo 
     * 
     * Total  123 bytes + 10 bytes extras = 133 bytes por registro
     *
     */
    private RandomAccessFile archivo;
    private int codigo;
    private IUsuarioDAO usuarioDAO;

    //Constructor
    public ClienteDAO(IUsuarioDAO usuarioDAO) {

        try {
            archivo = new RandomAccessFile("Datos/Clientes.dat", "rw");
            codigo = (int) (archivo.length()/97);
            this.usuarioDAO = usuarioDAO;

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
    public Cliente read(String ruc) {
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
                salto += 128;

            }

        } catch (IOException e) {
            System.out.println("Error de lectura (update: UsuarioDAO)");
            e.printStackTrace();

        }

    }

    @Override
    public void delete(Cliente cliente) {

    }
    
}
