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
public class ChargeEventMapT extends Nodo{
    private String eventName="";
    private int valid=0;
    private String timezoneMode="";
    private String targetEngine="";
    private String chargeRatePlanName="";
    private String ratePlanIID="";
    private String prorateFirst="";
    private String prorateLast="";
    private int minQuantity= 0;
    
    public ChargeEventMapT(){
        
    }
    
    public ChargeEventMapT(int id){
        this.id=id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getTimezoneMode() {
        return timezoneMode;
    }

    public void setTimezoneMode(String timezoneMode) {
        this.timezoneMode = timezoneMode;
    }

    public String getTargetEngine() {
        return targetEngine;
    }

    public void setTargetEngine(String targetEngine) {
        this.targetEngine = targetEngine;
    }

    public String getChargeRatePlanName() {
        return chargeRatePlanName;
    }

    public void setChargeRatePlanName(String chargeRatePlanName) {
        this.chargeRatePlanName = chargeRatePlanName;
    }

    public String getRatePlanIID() {
        return ratePlanIID;
    }

    public void setRatePlanIID(String ratePlanIID) {
        this.ratePlanIID = ratePlanIID;
    }

    public String getProrateFirst() {
        return prorateFirst;
    }

    public void setProrateFirst(String prorateFirst) {
        this.prorateFirst = prorateFirst;
    }

    public String getProrateLast() {
        return prorateLast;
    }

    public void setProrateLast(String prorateLast) {
        this.prorateLast = prorateLast;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }
    
    @Override
    public String toString(String s) {
        return 
           s+"<chargeEventMap>\n" +
        s+"\t<eventName>"+eventName+"</eventName>\n" +
        s+"\t<validIfCancelled>"+((valid<=2)?"true":"false")+"</validIfCancelled>\n" +
        s+"\t<validIfInactive>"+((valid%2!=0)?"true":"false")+"</validIfInactive>\n" +
        s+"\t<timezoneMode>"+timezoneMode+"</timezoneMode>\n" +
        s+"\t<minQuantity>"+minQuantity+"</minQuantity>\n" +
        s+"\t<minQuantityUnit>NONE</minQuantityUnit>\n" +
        s+"\t<incrementQuantity>1</incrementQuantity>\n" +
        s+"\t<incrementQuantityUnit>NONE</incrementQuantityUnit>\n" +
        s+"\t<roundingMode>NEAREST</roundingMode>\n" +
        s+"\t<prorateFirst>"+prorateFirst+"</prorateFirst>\n" +
        s+"\t<prorateLast>"+prorateLast+"</prorateLast>\n" +
        s+"\t<chargeRatePlanInfo>\n" +
        s+"\t\t<targetEngine>"+targetEngine+"</targetEngine>\n" +
        s+"\t</chargeRatePlanInfo>\n" +
        s+"\t<chargeRatePlanName>"+chargeRatePlanName+"</chargeRatePlanName>\n" +
        s+"\t<ratePlanIID>"+ratePlanIID+"</ratePlanIID>\n" +
        s+"</chargeEventMap>";
    }
    
    public int procesar(ArrayList<String> charges, int index) {
        
        
        for(int i=index; i<charges.size();i++) {
            
            if(charges.get(i).matches("(?s)eventName: (.*)")) this.eventName= charges.get(i).substring(11);
            else if(charges.get(i).matches("(?s)validIfCancelled: (.*)")){
                        this.valid= ((charges.get(i).substring(18).equals("true"))?1:3);
                        i++;
                        this.valid= ((charges.get(i).substring(17).equals("false"))?this.valid+1:this.valid);
            }
            else if(charges.get(i).matches("(?s)valid: (.*)")) this.valid= Integer.parseInt(charges.get(i).substring(7));
            else if(charges.get(i).matches("(?s)timezoneMode: (.*)")) this.timezoneMode= charges.get(i).substring(14);
            else if(charges.get(i).matches("(?s)chargeRatePlanName: (.*)")) this.chargeRatePlanName= charges.get(i).substring(20);
            else if(charges.get(i).matches("(?s)ratePlanIID: (.*)")) this.ratePlanIID= charges.get(i).substring(13);
            else if(charges.get(i).matches("(?s)prorateFirst: (.*)")) this.prorateFirst= charges.get(i).substring(14);
            else if(charges.get(i).matches("(?s)prorateLast: (.*)")) this.prorateLast= charges.get(i).substring(13);
            else if(charges.get(i).matches("(?s)minQuantity: (.*)")) this.minQuantity= Integer.parseInt(charges.get(i).substring(13));
            else if(charges.get(i).matches("(?s)chargeRatePlanInfo")){i++; this.targetEngine= charges.get(i).substring(14);}
            else if(charges.get(i).matches("(?s)incrementQuantity(.*)") || charges.get(i).matches("(?s)roundingMode(.*)") || charges.get(i).matches("(?s)minQuantityUnit(.*)")){}
            else return i;
        }
        return charges.size();
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((eventName+"/"+valid+"/"+timezoneMode+"/"+targetEngine+"/"+chargeRatePlanName+"/"+ratePlanIID+"/"+prorateFirst+"/"+prorateLast+"/"+minQuantity).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    @Override
    public void merge(Nodo nodo) {
        ChargeEventMapT chargeEventT= (ChargeEventMapT) nodo;
        
    }
    
    
    
}
