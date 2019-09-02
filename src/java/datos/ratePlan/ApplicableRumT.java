/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ApplicableRumT extends Nodo{
    private String applicableRumName="";
    private String minQuantity="";
    private String minQuantityUnit="NONE";
    private String incrementQuantity="";
    private String incrementQuantityUnit="NONE";
    private String roundingMode="NEAREST";
    
    public ApplicableRumT(){}
    public ApplicableRumT(int id){this.id=id;}

    public String getApplicableRumName() {
        return applicableRumName;
    }

    public void setApplicableRumName(String applicableRumName) {
        this.applicableRumName = applicableRumName;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getMinQuantityUnit() {
        return minQuantityUnit;
    }

    public void setMinQuantityUnit(String minQuantityUnit) {
        this.minQuantityUnit = minQuantityUnit;
    }

    public String getIncrementQuantity() {
        return incrementQuantity;
    }

    public void setIncrementQuantity(String incrementQuantity) {
        this.incrementQuantity = incrementQuantity;
    }

    public String getIncrementQuantityUnit() {
        return incrementQuantityUnit;
    }

    public void setIncrementQuantityUnit(String incrementQuantityUnit) {
        this.incrementQuantityUnit = incrementQuantityUnit;
    }

    public String getRoundingMode() {
        return roundingMode;
    }

    public void setRoundingMode(String roundingMode) {
        this.roundingMode = roundingMode;
    }
    
    @Override
    public String toString(String s, String aux) {
        return 
        s+"<applicableRum>\n" +
            s+"\t<applicableRumName>"+applicableRumName+"</applicableRumName>\n" +
            s+"\t<minQuantity>"+minQuantity+"</minQuantity>\n" +
            s+"\t<minQuantityUnit>"+minQuantityUnit+"</minQuantityUnit>\n" +
            s+"\t<incrementQuantity>"+incrementQuantity+"</incrementQuantity>\n" +
            s+"\t<incrementQuantityUnit>"+incrementQuantityUnit+"</incrementQuantityUnit>\n" +
            s+"\t<roundingMode>"+roundingMode+"</roundingMode>\n"+
                aux+s+"</applicableRum>";
                
    }

    @Override
    public int procesar(ArrayList<String> applicable, int index) {
        for(int i=index; i<applicable.size();i++) {
            if(applicable.get(i).matches("(?s)applicableRumName: (.*)")) this.applicableRumName= applicable.get(i).substring(19);
            else if(applicable.get(i).matches("(?s)minQuantity: (.*)")) this.minQuantity= applicable.get(i).substring(13);
            else if(applicable.get(i).matches("(?s)minQuantityUnit: (.*)")) this.minQuantityUnit= applicable.get(i).substring(17);
            else if(applicable.get(i).matches("(?s)incrementQuantity: (.*)")) this.incrementQuantity= applicable.get(i).substring(19);
            else if(applicable.get(i).matches("(?s)incrementQuantityUnit: (.*)")) this.incrementQuantityUnit= applicable.get(i).substring(23);
            else if(applicable.get(i).matches("(?s)roundingMode: (.*)")) this.roundingMode= applicable.get(i).substring(14);
            else return i;
        }
        return applicable.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((applicableRumName+"/"+minQuantity+"/"+minQuantityUnit+"/"+incrementQuantity+"/"+incrementQuantityUnit+"/"+roundingMode).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    
}
