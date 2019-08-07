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
        ArrayList<String> nodos = XmlParser.Leer(new File(controls[0]) , controls[1]);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static String[] getPathPointer(String tipo){
        String[] controls= new String[2];
        if(tipo.equals(ControlPath.zoneModelsClick)){
                controls[0]= ControlPath.zoneModelsPath;
                controls[1]= ControlPath.zoneModelsPointer;}
        else if(tipo.equals(ControlPath.impactCategoriesClick)){
                controls[0]= ControlPath.impactCategoriesPath;
                controls[1]= ControlPath.impactCategoriesPointer;            
        }
        return controls;
    }
    
}
