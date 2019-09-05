/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.ListaT;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import xml.XmlParser;
import java.text.SimpleDateFormat;  
import java.util.Date;  

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
    
    public static ArrayList<ListaT> getListaBalance(String tipo){
        String[] controls = getPathPointer(ControlPath.balanceElementClick);
        ArrayList<ListaT> nodos = XmlParser.LeerBalance(new File(controls[0]) , controls[1]);
        for(int i=0; i<nodos.size(); i++){
            ListaT nodo= nodos.get(i);
            if(Integer.parseInt(nodo.unit)>=1000 && tipo.equals("currency")){ nodos.remove(i);
            i--;
            }
            else if((Integer.parseInt(nodo.unit)<1000 || Integer.parseInt(nodo.unit)>2000000) && tipo.equals("no_currency")){ nodos.remove(i);
            i--;
            }
        }
        return nodos;
    }
    
    public static ArrayList<ListaT> getListaFiltro(String tipo, ArrayList<ListaT> buscar){
        String[] controls = getPathPointer(tipo);
        ArrayList<String> nodos = XmlParser.Buscar(new File(controls[0]) , controls[1],buscar);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getListaFiltro(String tipo, ArrayList<ListaT> buscar, ListaT s){
        String[] controls = getPathPointer(tipo);
        ArrayList<String> nodos = XmlParser.Buscar(new File(controls[0]) , controls[1],buscar, s);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getListaFiltroDeep(String tipo, ArrayList<ListaT> buscar, String s){
        String[] controls = getPathPointer(tipo);
        ArrayList<String> nodos = XmlParser.BuscarDeep(new File(controls[0]) , controls[1],buscar, s);
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
        else if(tipo.equals(ControlPath.eventAttributeSpecClick)){
                controls[0]= ControlPath.eventAttributeSpecPath;
                controls[1]= ControlPath.eventAttributeSpecPointer;            
        }
        else if(tipo.equals(ControlPath.chargeRateClick)){
                controls[0]= ControlPath.chargeRatePath;
                controls[1]= ControlPath.chargeRatePointer;            
        }
        else if(tipo.equals(ControlPath.glidClick)){
                controls[0]= ControlPath.glidPath;
                controls[1]= ControlPath.glidPointer;            
        }
        else if(tipo.equals(ControlPath.uscMapClick)){
                controls[0]= ControlPath.uscMapPath;
                controls[1]= ControlPath.uscMapPointer;            
        }
        return controls;
    }

    public static String Buscar(String tipo, ListaT buscar, String retorna) {
        String[] controls = getPathPointer(tipo);
        return XmlParser.BuscarUno(new File(controls[0]) , controls[1],buscar,retorna);
    }
    
    public static String getParseDate(String s) throws ParseException{
        SimpleDateFormat day= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat day2= new SimpleDateFormat("dd MMM yyyy");
        if(s.equals("inf")) return "Never ends";
        else if(s.equals("0")) return "Now";
        else{
            s= s.substring(0, 4)+"-"+s.substring(4, 6)+"-"+s.substring(6, 8);
            return day2.format(day.parse(s));}
    }
    
    public static String getParseString(String s) throws ParseException{
        SimpleDateFormat day= new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat day2= new SimpleDateFormat("dd MMM yyyy");
        if(s.equals("Never ends")) return "inf";
        else if(s.equals("Now")) return "0";
        else{
            return (day.format(day2.parse(s))).replaceAll("-", "")+"T000000";}
    }
    
    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }
    
}
