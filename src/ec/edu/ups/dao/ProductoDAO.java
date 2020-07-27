/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.idao.IUsuarioDAO;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class ProductoDAO implements IProductoDAO{
    
    /**
     * Tamaño del archivo:
     *
     * stock  10 caracteres 
     * nombre  25 caracteres 
     * codigo 10 caracteres 
     * precio  50 caracteres 
     * marca  10 caracteres
     * Fecha de Elaboracion 12 caracteres
     * fecha de caducidad 12 caracteres
     * descripcion 40 caracteres
     *
     * Total  169 bytes + 10 bytes extras = 179 bytes por registro
     *
     */
    
    
    //archivo binario
    private RandomAccessFile archivo;
    
    
    @Override
    public void create(Producto producto) {
        
    }

    @Override
    public Producto read(String codigo) {
        return null;

    }

    @Override
    public void update(Producto producto) {

    }

    @Override
    public void delete(Producto producto) {

    }
    
}
