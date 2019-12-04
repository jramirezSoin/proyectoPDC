/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Joseph Ramírez
 */
public class Cambio implements java.io.Serializable{
    private String cambio="";
    private int cantidad=0;
    private String archivo="";   

    public Cambio() {
    }

    public Cambio(String cambio, int cantidad, String archivo) {
        this.cambio=cambio;
        this.archivo=archivo;
        this.cantidad=cantidad;
    }
    
    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    
}
