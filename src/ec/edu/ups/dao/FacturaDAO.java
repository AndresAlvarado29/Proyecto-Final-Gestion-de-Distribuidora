/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IFacturaDAO;
import ec.edu.ups.modelo.Factura;
import java.io.RandomAccessFile;

/**
 *
 * @author HI andres
 */
public class FacturaDAO implements IFacturaDAO {

     /**
     * Tamaño del archivo:
     *
     * fechaDeSalida 12 caracteres
     * RUC 13 caracteres
     * DireccionAdministracion 25 cracteres
     * TelefonoAdministracion 15 caracteres
     * EstadoDeFactura 12  caracteres
     *
     * Total  82 bytes + 10 bytes extras = 92 bytes por registro
     *
     */
     
    //archivo binario
    private RandomAccessFile archivo;
    
    
    @Override
    public void create(Factura factura) {

    }

    @Override
    public Factura read(String numero) {
        return null;

    }

    @Override
    public void update(Factura factura) {

    }

    @Override
    public void delete(Factura factura) {

    }
    
}
