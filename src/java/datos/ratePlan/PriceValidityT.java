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
public class PriceValidityT extends Nodo {
    private String startValidityMode="";
    private String endValidityMode="";
    private String validityRange="0/inf";
    private String relativeStartOffset="";
    private String relativeEndOffset="";
    private String relativeEndOffsetUnit="";
    
    public PriceValidityT(){}
    
   public PriceValidityT(int id){this.id=id;}

    public String getStartValidityMode() {
        return startValidityMode;
    }

    public void setStartValidityMode(String startValidityMode) {
        this.startValidityMode = startValidityMode;
    }

    public String getEndValidityMode() {
        return endValidityMode;
    }

    public void setEndValidityMode(String endValidityMode) {
        this.endValidityMode = endValidityMode;
    }

    public String getValidityRange() {
        return validityRange;
    }

    public void setValidityRange(String validityRange) {
        this.validityRange = validityRange;
    }

    public String getRelativeStartOffset() {
        return relativeStartOffset;
    }

    public void setRelativeStartOffset(String relativeStartOffset) {
        this.relativeStartOffset = relativeStartOffset;
    }

    public String getRelativeEndOffset() {
        return relativeEndOffset;
    }

    public void setRelativeEndOffset(String relativeEndOffset) {
        this.relativeEndOffset = relativeEndOffset;
    }

    public String getRelativeEndOffsetUnit() {
        return relativeEndOffsetUnit;
    }

    public void setRelativeEndOffsetUnit(String relativeEndOffsetUnit) {
        this.relativeEndOffsetUnit = relativeEndOffsetUnit;
    }
    
    @Override
    public String toString(String s) {
        return 
        s+"<priceValidity>\n" +
        s+"\t<startValidityMode>"+startValidityMode+"</startValidityMode>\n" +
        s+"\t<endValidityMode>"+endValidityMode+"</endValidityMode>\n" +
        s+"\t<validityRange>"+validityRange+"</validityRange>\n" +
        s+"\t<relativeStartOffset>"+relativeStartOffset+"</relativeStartOffset>\n" +
        s+"\t<relativeEndOffset>"+relativeEndOffset+"</relativeEndOffset>\n" +
        ((relativeEndOffsetUnit.equals(""))?"":s+"\t<relativeEndOffsetUnit>"+relativeEndOffsetUnit+"</relativeEndOffsetUnit>\n")+
        s+"</priceValidity>";
                
    }

    @Override
    public int procesar(ArrayList<String> validity, int index) {
        for(int i=index; i<validity.size();i++) {
            if(validity.get(i).matches("(?s)startValidityMode: (.*)")) this.startValidityMode= validity.get(i).substring(19);
            else if(validity.get(i).matches("(?s)endValidityMode: (.*)")) this.endValidityMode= validity.get(i).substring(17);
            else if(validity.get(i).matches("(?s)validityRange: (.*)")) this.validityRange= validity.get(i).substring(15);
            else if(validity.get(i).matches("(?s)relativeStartOffset: (.*)")) this.relativeStartOffset= validity.get(i).substring(21);
            else if(validity.get(i).matches("(?s)relativeEndOffset: (.*)")) this.relativeEndOffset= validity.get(i).substring(19);
            else if(validity.get(i).matches("(?s)relativeEndOffsetUnit: (.*)")) this.relativeEndOffsetUnit= validity.get(i).substring(23);
            else{validar(); return i;}
        }
        validar();
        return validity.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((startValidityMode+"/"+endValidityMode+"/"+validityRange+"/"+relativeStartOffset+"/"+relativeEndOffset+"/"+relativeEndOffsetUnit).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    private void validar(){
        if(this.endValidityMode.equals("NEVER")){ this.relativeEndOffset="-1"; this.relativeEndOffsetUnit="";}
        else if(this.endValidityMode.equals("RELATIVE_TO_START")){ this.relativeEndOffset="0"; this.relativeEndOffsetUnit="BILLING_CYCLE";}
    }
}
