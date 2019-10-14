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
    private String fecha="";
    private String archivo="";   

    public Cambio() {
    }

    public Cambio(String cambio, String fecha, String archivo) {
        this.cambio=cambio;
        this.archivo=archivo;
        this.fecha=fecha;
    }
    
    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    
}
