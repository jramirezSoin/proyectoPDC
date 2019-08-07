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
    public String valor;

    public ListaT(String valor, int id) {
        this.valor = valor;
        this.id = id;
    }
    
    @Override
    public boolean buscar(String buscar) {   
        if(this.valor.toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }
        else{
            this.visibilidad=false;
            return false;
        }
    }
    
}
