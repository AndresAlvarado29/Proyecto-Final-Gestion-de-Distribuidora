/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.idao;

import ec.edu.ups.modelo.Factura;

/**
 *
 * @author HI andres
 */
public interface IFacturaDAO {
    //Metodos CRUD
    public void create(Factura factura);
    public Factura read(String numero);
    public void update(Factura factura);
    public void delete(Factura factura);
}
