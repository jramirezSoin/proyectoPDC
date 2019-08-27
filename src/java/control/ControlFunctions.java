/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.ListaT;
import java.io.File;
import java.util.ArrayList;
import xml.XmlParser;

/**
 *
 * @author Joseph Ramírez
 */
public class ControlFunctions {
    
    public static ArrayList<ListaT> ListS2ListT(ArrayList<String> lista){
        ArrayList<ListaT> resultado = new ArrayList<>();
        for(int i=0; i<lista.size(); i++)
            resultado.add(new ListaT(lista.get(i),i));
        return resultado;
    }
    
    public static ArrayList<String> ListT2ListS(ArrayList<ListaT> lista){
        ArrayList<String> resultado = new ArrayList<>();
        for(ListaT i : lista)
            resultado.add(i.valor);
        return resultado;
    }
    
    public static ArrayList<ListaT> getLista(String tipo){
        String[] controls = getPathPointer(tipo);
        ArrayList<String> nodos = XmlParser.Leer2(new File(controls[0]) , controls[1]);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getListaFiltro(String tipo, ArrayList<ListaT> buscar){
        String[] controls = getPathPointer(tipo);
        ArrayList<String> nodos = XmlParser.Buscar(new File(controls[0]) , controls[1],buscar);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static String[] getPathPointer(String tipo){
        String[] controls= new String[2];
        if(tipo.equals(ControlPath.zoneModelsClick)){
                controls[0]= ControlPath.zoneModelsPath;
                controls[1]= ControlPath.zoneModelsPointer;
        }
        else if(tipo.equals(ControlPath.impactCategoriesClick)){
                controls[0]= ControlPath.impactCategoriesPath;
                controls[1]= ControlPath.impactCategoriesPointer;            
        }
        else if(tipo.equals(ControlPath.attributeSpecMapClick)){
                controls[0]= ControlPath.attributeSpecMapPath;
                controls[1]= ControlPath.attributeSpecMapPointer;            
        }
        else if(tipo.equals(ControlPath.chargeOfferingClick)){
                controls[0]= ControlPath.chargeOfferingPath;
                controls[1]= ControlPath.chargeOfferingPointer;            
        }
        else if(tipo.equals(ControlPath.alterationOfferingClick)){
                controls[0]= ControlPath.alterationOfferingPath;
                controls[1]= ControlPath.alterationOfferingPointer;            
        }
        else if(tipo.equals(ControlPath.balanceElementClick)){
                controls[0]= ControlPath.balanceElementPath;
                controls[1]= ControlPath.balanceElementPointer;            
        }
        else if(tipo.equals(ControlPath.bundledClick)){
                controls[0]= ControlPath.bundledPath;
                controls[1]= ControlPath.bundledPointer;            
        }
        return controls;
    }

    public static String Buscar(String tipo, ListaT buscar, String retorna) {
        String[] controls = getPathPointer(tipo);
        return XmlParser.BuscarUno(new File(controls[0]) , controls[1],buscar,retorna);
    }
    
}
