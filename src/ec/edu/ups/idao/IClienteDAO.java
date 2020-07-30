/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.idao;

import ec.edu.ups.modelo.Cliente;

/**
 *
 * @author HI andres
 */
public interface IClienteDAO {
    //Metodos CRUD
    public void create(Cliente cliente);
    public Cliente read(String RUC);
    public void update(Cliente cliente);
    public void delete(Cliente cliente);
}
