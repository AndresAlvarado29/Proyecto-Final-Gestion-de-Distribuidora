/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.idao;

import ec.edu.ups.modelo.Factura;
import java.util.List;

/**
 *
 * @author HI andres
 */
public interface IFacturaDAO {
    //Metodos CRUD
    public void create(Factura factura);
    public Factura read(String codigoFactura);
    public void update(Factura factura);
    public boolean cambiarEstado(String codigoFactura);
    public List<Factura> mostrarFacturas();
}
