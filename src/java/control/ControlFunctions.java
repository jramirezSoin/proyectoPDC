/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.Cambio;
import datos.ListaT;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import xml.XmlParser;
import java.text.SimpleDateFormat;  

/**
 *
 * @author Joseph Ramírez
 */
public class ControlFunctions {
    
    public static ArrayList<ListaT> ListS2ListT(ArrayList<String> lista){
        ArrayList<ListaT> resultado = new ArrayList<>();
        if(lista!=null){
        for(int i=0; i<lista.size(); i++)
            resultado.add(new ListaT(lista.get(i),i));
        }
        return resultado;
    }
    
    public static ArrayList<String> ListT2ListS(ArrayList<ListaT> lista){
        ArrayList<String> resultado = new ArrayList<>();
        for(ListaT i : lista)
            resultado.add(i.valor);
        return resultado;
    }
    
    public static ArrayList<ListaT> getLista(String tipo, String user){
        String[] controls = getPathPointer(tipo,user);
        ArrayList<String> nodos = XmlParser.Leer2(new File(controls[0]) , controls[1]);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getLista(String tipo, String user, String indicador){
        String[] controls = getPathPointer(tipo,user);
        ArrayList<String> nodos = XmlParser.Leer2(new File(controls[0]) , indicador);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getLista(String tipo, String user, String indicador, String valor){
        String[] controls = getPathPointer(tipo,user);
        if(indicador.equals("")) indicador= controls[1];
        ArrayList<String> nodos = XmlParser.Leer2(new File(controls[0]) , indicador, valor);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getLista(String tipo, String user, String indicador, String valor,boolean conjunto){
        String[] controls = getPathPointer(tipo,user);
        if(indicador.equals("")) indicador= controls[1];
        ArrayList<String> nodos = XmlParser.Leer2(new File(controls[0]) , indicador, valor,conjunto);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getListaBalance(String tipo, String user){
        String[] controls = getPathPointer(ControlPath.balanceElementClick,user);
        ArrayList<ListaT> nodos = XmlParser.LeerBalance(new File(controls[0]) , controls[1]);
        for(int i=0; i<nodos.size(); i++){
            ListaT nodo= nodos.get(i);
            if(Integer.parseInt(nodo.unit)>=1000 && tipo.equals("currency")){ nodos.remove(i);
            i--;
            }
            else if((Integer.parseInt(nodo.unit)<1000) && tipo.equals("no_currency")){ nodos.remove(i);
            i--;
            }
            else if(tipo.equals("all"))break;
        }
        return nodos;
    }
    
    public static ArrayList<ListaT> getListaEvent(String user){
        String[] controls = getPathPointer(ControlPath.eventAttributeSpecClick,user);
        ArrayList<ListaT> nodos = XmlParser.LeerEvent(new File(controls[0]) , controls[1],"EventDelayedSessionTelcoGprs");
        return nodos;
    }
    
    public static ArrayList<ListaT> getListaFiltro(String tipo, String user, ArrayList<ListaT> buscar){
        String[] controls = getPathPointer(tipo,user);
        ArrayList<String> nodos = XmlParser.Buscar(new File(controls[0]) , controls[1],buscar);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getListaFiltro(String tipo, String user, ArrayList<ListaT> buscar, ListaT s){
        String[] controls = getPathPointer(tipo,user);
        ArrayList<String> nodos = XmlParser.Buscar(new File(controls[0]) , controls[1],buscar, s);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static ArrayList<ListaT> getListaFiltroDeep(String tipo, String user, ArrayList<ListaT> buscar, String s){
        return getListaFiltroDeep(tipo,user,buscar,s,false);
    }
    
    public static ArrayList<ListaT> getListaFiltroDeep(String tipo, String user, ArrayList<ListaT> buscar, String s,Boolean conjunto){
        String[] controls = getPathPointer(tipo,user);
        ArrayList<String> nodos = XmlParser.BuscarDeep(new File(controls[0]) , controls[1],buscar, s,conjunto);
        ArrayList<ListaT> nodosId = ListS2ListT(nodos);
        return nodosId;
    }
    
    public static String[] getPathPointer(String tipo, String user){
        String[] controls= new String[2];
        String pathUser = ControlPath.path+user+"/";
        if(tipo.equals(ControlPath.zoneModelsClick)){
                controls[0]= pathUser+ControlPath.zoneModelsPath;
                controls[1]= ControlPath.zoneModelsPointer;
        }
        else if(tipo.equals(ControlPath.impactCategoriesClick)){
                controls[0]= pathUser+ControlPath.impactCategoriesPath;
                controls[1]= ControlPath.impactCategoriesPointer;            
        }
        else if(tipo.equals(ControlPath.attributeSpecMapClick)){
                controls[0]= pathUser+ControlPath.attributeSpecMapPath;
                controls[1]= ControlPath.attributeSpecMapPointer;            
        }
        else if(tipo.equals(ControlPath.chargeOfferingClick)){
                controls[0]= pathUser+ControlPath.chargeOfferingPath;
                controls[1]= ControlPath.chargeOfferingPointer;            
        }
        else if(tipo.equals(ControlPath.balanceElementClick)){
                controls[0]= pathUser+ControlPath.balanceElementPath;
                controls[1]= ControlPath.balanceElementPointer;            
        }
        else if(tipo.equals(ControlPath.bundledClick)){
                controls[0]= pathUser+ControlPath.bundledPath;
                controls[1]= ControlPath.bundledPointer;            
        }
        else if(tipo.equals(ControlPath.eventAttributeSpecClick)){
                controls[0]= pathUser+ControlPath.eventAttributeSpecPath;
                controls[1]= ControlPath.eventAttributeSpecPointer;            
        }
        else if(tipo.equals(ControlPath.chargeRateClick)){
                controls[0]= pathUser+ControlPath.chargeRatePath;
                controls[1]= ControlPath.chargeRatePointer;            
        }
        else if(tipo.equals(ControlPath.glidClick)){
                controls[0]= pathUser+ControlPath.glidPath;
                controls[1]= ControlPath.glidPointer;            
        }
        else if(tipo.equals(ControlPath.uscMapClick)){
                controls[0]= pathUser+ControlPath.uscMapPath;
                controls[1]= ControlPath.uscMapPointer;            
        }
        else if(tipo.equals(ControlPath.timeModelsClick)){
                controls[0]= pathUser+ControlPath.timeModelsPath;
                controls[1]= ControlPath.timeModelsPointer;            
        }
        else if(tipo.equals(ControlPath.rolloverClick)){
                controls[0]= pathUser+ControlPath.rolloverPath;
                controls[1]= ControlPath.rolloverPointer;            
        }
        else if(tipo.equals(ControlPath.genericSelectorClick)){
                controls[0]= pathUser+ControlPath.genericSelectorPath;
                controls[1]= ControlPath.genericSelectorPointer;            
        }
        else if(tipo.equals(ControlPath.customRuleClick)){
                controls[0]= pathUser+ControlPath.customRulePath;
                controls[1]= ControlPath.customRulePointer;            
        }
        else if(tipo.equals(ControlPath.alterationOfferingClick)){
                controls[0]= pathUser+ControlPath.alterationOfferingPath;
                controls[1]= ControlPath.alterationOfferingPointer;            
        }
        else if(tipo.equals(ControlPath.alterationRateClick)){
                controls[0]= pathUser+ControlPath.alterationRatePath;
                controls[1]= ControlPath.alterationRatePointer;            
        }
        else if(tipo.equals(ControlPath.triggerSpecClick)){
                controls[0]= pathUser+ControlPath.triggerSpecPath;
                controls[1]= ControlPath.triggerSpecPointer;            
        }
        else if(tipo.equals(ControlPath.chargeSelectorSpecClick)){
                controls[0]= pathUser+ControlPath.chargeSelectorSpecPath;
                controls[1]= ControlPath.chargeSelectorSpecPointer;            
        }
        else if(tipo.equals(ControlPath.holidayClick)){
                controls[0]= pathUser+ControlPath.holidayPath;
                controls[1]= ControlPath.holidayPointer;            
        }
        else if(tipo.equals(ControlPath.alterationSelectorClick)){
                controls[0]= pathUser+ControlPath.alterationSelectorPath;
                controls[1]= ControlPath.alterationSelectorPointer;            
        }
        return controls;
    }

    public static String Buscar(String tipo, String user, ListaT buscar, String retorna) {
        String[] controls = getPathPointer(tipo,user);
        return XmlParser.BuscarUno(new File(controls[0]) , controls[1],buscar,retorna);
    }
    
    public static String getParseDate(String s) throws ParseException{
        SimpleDateFormat day= new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat day2= new SimpleDateFormat("dd MMM yyyy");
        if(s.equalsIgnoreCase("inf")) return "Never ends";
        else if(s.equals("0")) return "Now";
        else{
            s= s.replace("T000000","");
            return day2.format(day.parse(s));}
    }
    
    public static String getParseHour(String s) throws ParseException{
        String[] ss= s.split("/");
        SimpleDateFormat day= new SimpleDateFormat("HHmmss");
        SimpleDateFormat day2= new SimpleDateFormat("HH:mm:ss");
        ss[0]= ss[0].replace("T","");
        ss[1]= ss[1].replace("T","");
        return day2.format(day.parse(ss[0]))+"/"+day2.format(day.parse(ss[1]));
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
    
    public static ArrayList<ListaT> LeerConstante(String constante){
        return XmlParser.LeerConstante(constante);
    }
    
    public static boolean CambioContiene(ArrayList<Cambio> cambios, String valor){
        for(Cambio cambio: cambios){
            if(cambio.getArchivo().matches("(.*)"+valor+"(.*)")){
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<Cambio> CambioContieneElimina(ArrayList<Cambio> cambios, String valor){
        if(cambios!=null){
        for(int i=0; i<cambios.size(); i++){
            Cambio cambio= cambios.get(i);
            if(cambio.getArchivo().matches("(.*)"+valor+"(.*)")){
                cambios.remove(i);
                i--;
            }
        }
        }
        return cambios;
    }
}
