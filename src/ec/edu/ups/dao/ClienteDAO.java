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
        
    }

    @Override
    public Cliente read(String ruc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
