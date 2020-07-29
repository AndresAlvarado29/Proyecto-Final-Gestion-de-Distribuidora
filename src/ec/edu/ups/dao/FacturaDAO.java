/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.dao;

import ec.edu.ups.idao.IFacturaDAO;
import ec.edu.ups.modelo.Factura;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HI andres
 */
public class FacturaDAO implements IFacturaDAO {

     /**
     * Tamaño del archivo:
     *
     * codigoFactura 5 caracteres
     * fechaDeSalida 12 caracteres
     * RUC 13 caracteres
     * DireccionAdministracion 25 cracteres
     * TelefonoAdministracion 10 caracteres
     * EstadoDeFactura 12  caracteres
     *
     * Total  82 bytes + 10 bytes extras = 92 bytes por registro
     *
     */
     
    //archivo binario
    private RandomAccessFile archivo;
    private int tamañoRegistro;
    
      //Constructor
    public FacturaDAO() {
        tamañoRegistro = 87;
        try {
            archivo = new RandomAccessFile("Datos/Facturas.dat", "rw");
            tamañoRegistro = 87;

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura");
            e.printStackTrace();

        }
    }
    
    
    @Override
    public void create(Factura factura) {
        
        try {
            archivo.seek(archivo.length());
            archivo.writeUTF(factura.getCodigoFactura());
            archivo.writeUTF(factura.getDireccionAdministracion());
            archivo.writeUTF(factura.getFechaDeSalida());
            archivo.writeUTF(factura.getTelefonoAdministracion());
            archivo.writeUTF(factura.getRUC());
            

        } catch (IOException e) {
            System.out.println("Error de  lectura y escritura(create:UsuarioDao)");
            e.printStackTrace();

        }

    }

    @Override
    public Factura read(String numero) {
        return null;

    }

    @Override
    public void update(Factura factura) {
        
         try {
            int salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                String facturaArchivo = archivo.readUTF();
                
                if (factura.getRUC().equals(facturaArchivo)) {
                    archivo.writeUTF(factura.getDireccionAdministracion());
                    archivo.writeUTF(factura.getFechaDeSalida());
                    archivo.writeUTF(factura.getTelefonoAdministracion());
                    archivo.writeUTF(factura.getRUC());
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
    public List<Factura> mostrarFacturas() {

         List<Factura> lista = new ArrayList<Factura>();    
        try {
            long salto = 0;
            
            while (salto < archivo.length()) {
                archivo.seek(salto);
                
                String codigoFactura = archivo.readUTF();
                String fechaDeSalida = archivo.readUTF();
                String RUC = archivo.readUTF();
                String DireccionAdministracion = archivo.readUTF();
                String TelefonoAdministracion = archivo.readUTF();
                String estado = archivo.readUTF();
                 
                 
                    Factura f = new Factura();
                    
                    lista.add(f);
                    
                                    
                    
                
                salto = salto + 92;
            }
        } catch (IOException e) {
            System.out.println("Error login");
            e.printStackTrace();
        }
        
        return lista;
    }
    
}
