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
public class ListaT extends Nodo{
    public String valor="";
    public String unit="";

    public ListaT(String valor, int id) {
        this.valor = valor;
        this.id = id;
    }

    public ListaT() {
    }
    
    public ListaT(String unit, String valor){
        this.valor = valor;
        this.unit = unit;
    }
    
    @Override
    public boolean buscar(String buscar) {   
        if((this.valor+"/"+this.unit).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }
        else{
            this.visibilidad=false;
            return false;
        }
    }
    
}
