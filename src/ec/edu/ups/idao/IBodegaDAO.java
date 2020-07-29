/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.idao;

import ec.edu.ups.modelo.Bodega;
import java.util.List;

/**
 *
 * @author HI andres
 */
public interface IBodegaDAO {
    //Metodos CRUD
    public void create(Bodega bodeja);
    public Bodega read(String codigo);
    public void update(Bodega bodega);
    public void delete(Bodega bodega);
    public List<String> findAll();
    public List<Bodega> listarBodegas();
    
}
