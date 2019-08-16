/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class RoundingRuleT extends Nodo{
    private String precision="";
    private String toleranceMin="";
    private String toleranceMax="";
    private String tolerancePercentage="";
    private String roundingMode="";
    private String processingStage="";
    
    public RoundingRuleT(int id) {
        this.id=id;
    }

    public RoundingRuleT(int id, String precision, String toleranceMin, String toleranceMax, String tolerancePercentage, String roundingMode, String processingStage) {
        this.id= id;
        this.precision = precision;
        this.toleranceMin = toleranceMin;
        this.toleranceMax = toleranceMax;
        this.tolerancePercentage = tolerancePercentage;
        this.roundingMode = roundingMode;
        this.processingStage = processingStage;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getToleranceMin() {
        return toleranceMin;
    }

    public void setToleranceMin(String toleranceMin) {
        this.toleranceMin = toleranceMin;
    }

    public String getToleranceMax() {
        return toleranceMax;
    }

    public void setToleranceMax(String toleranceMax) {
        this.toleranceMax = toleranceMax;
    }

    public String getTolerancePercentage() {
        return tolerancePercentage;
    }

    public void setTolerancePercentage(String tolerancePercentage) {
        this.tolerancePercentage = tolerancePercentage;
    }

    public String getRoundingMode() {
        return roundingMode;
    }

    public void setRoundingMode(String roundingMode) {
        this.roundingMode = roundingMode;
    }

    public String getProcessingStage() {
        return processingStage;
    }

    public void setProcessingStage(String processingStage) {
        this.processingStage = processingStage;
    }
    
    public int procesar(ArrayList<String> zoneModels, int index) {
        
        
        for(int i=index; i<zoneModels.size();i++) {
            
            if(zoneModels.get(i).matches("(?s)precision: (.*)")) this.precision= zoneModels.get(i).substring(11);
            else if(zoneModels.get(i).matches("(?s)toleranceMin: (.*)")) this.toleranceMin= zoneModels.get(i).substring(14);
            else if(zoneModels.get(i).matches("(?s)toleranceMax: (.*)")) this.toleranceMax= zoneModels.get(i).substring(14);
            else if(zoneModels.get(i).matches("(?s)tolerancePercentage: (.*)")) this.tolerancePercentage= zoneModels.get(i).substring(21);
            else if(zoneModels.get(i).matches("(?s)roundingMode: (.*)")) this.roundingMode= zoneModels.get(i).substring(14);
            else if(zoneModels.get(i).matches("(?s)processingStage: (.*)")) this.processingStage= zoneModels.get(i).substring(17);
            else return i;
        }
        return zoneModels.size();
    }

    @Override
    public String toString() {
        return "    <roundingRules>\n        <precision>" + precision + "</precision>\n        <toleranceMin>" 
                + toleranceMin + "</toleranceMin>\n        <toleranceMax>" + toleranceMax + "</toleranceMax>\n"
                +"        <tolerancePercentage>" + tolerancePercentage + "</tolerancePercentage>\n        <roundingMode>" 
                + roundingMode + "</roundingMode>\n        <processingStage>" + processingStage + "</processingStage>\n"
                +"</roundingRules>";
    }
    
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((precision+"/"+toleranceMin+"/"+toleranceMax+"/"+tolerancePercentage+"/"+roundingMode+"/"+processingStage).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
}
