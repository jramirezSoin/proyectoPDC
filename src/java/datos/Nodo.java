/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.Document;
import java.util.ArrayList;
import com.itextpdf.text.Element;

/**
 *
 * @author Joseph Ramírez
 */
public class Nodo implements Cloneable{
    public boolean visibilidad= true;
    public int id;
    public boolean masivo=false;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }

    public boolean buscar(String buscar) {     
        return true;
    }
    
    public int procesar(ArrayList<String> zoneModels, int index) {
        return index;
    }

    public int procesarI(ArrayList<String> lista, int i, ArrayList<Integer> indexs) {
        return 0;
    }

    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        
    }

    public void eliminar(ArrayList<Integer> index) {
        
    }
    
    public void masivo(){
        this.masivo=true;
    }

    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        
    }
    
    public void merge(Nodo nodo) {
        
    }
    
    public String toString(String s){
        return "";
    }
    
    public String toString(String s, String aux){
        return "";
    }
    
    public Object clone(){
        Nodo obj=null;
        try{
            obj=(Nodo)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
    
    public void clean(){
        
    }

    public void getPDF(Document document) {
        
    }
    
    public void getPDF(Element element) {
        
    }
    
}
