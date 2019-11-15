/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import control.FirstPDF;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ChargeEventMapT extends Nodo{
    private String eventName="";
    private int valid=0;
    private String timezoneMode="EVENT";
    private String targetEngine="RRE";
    private String chargeRatePlanName="";
    private String ratePlanIID="";
    private String prorateFirst="PRORATE_CHARGE";
    private String prorateLast="PRORATE_CHARGE";
    private String rum="";
    private int minQuantity= 1;
    private String rolloverRatePlanName= "";
    
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

    public String getRum() {
        return rum;
    }

    public void setRum(String rum) {
        this.rum = rum;
    }

    public String getRolloverRatePlanName() {
        return rolloverRatePlanName;
    }

    public void setRolloverRatePlanName(String rolloverRatePlanName) {
        this.rolloverRatePlanName = rolloverRatePlanName;
    }
    
    
    
    
    
    @Override
    public String toString(String s) {
        return 
           s+"<chargeEventMap>\n" +
        s+"\t<eventName>"+eventName+"</eventName>\n" +
        s+"\t<validIfCancelled>"+((valid<=2)?"true":"false")+"</validIfCancelled>\n" +
        s+"\t<validIfInactive>"+((valid%2!=0)?"true":"false")+"</validIfInactive>\n" +
        s+"\t<rum>"+rum+"</rum>\n"+
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
        s+"\t<rolloverRatePlanName>"+rolloverRatePlanName+"</rolloverRatePlanName>\n" +
        s+"\t<ratePlanIID>"+ratePlanIID+"</ratePlanIID>\n" +
        s+"</chargeEventMap>";
    }
    
    @Override
    public int procesar(ArrayList<String> charges, int index, String user) {
        
        
        for(int i=index; i<charges.size();i++) {
            
            if(charges.get(i).matches("(?s)eventName: (.*)")) this.eventName= charges.get(i).substring(11);
            else if(charges.get(i).matches("(?s)validIfCancelled: (.*)")){
                        this.valid= ((charges.get(i).substring(18).equals("true"))?1:3);
                        i++;
                        this.valid= ((charges.get(i).substring(17).equals("false"))?this.valid+1:this.valid);
            }
            else if(charges.get(i).matches("(?s)valid: (.*)")) this.valid= Integer.parseInt(charges.get(i).substring(7));
            else if(charges.get(i).matches("(?s)rum: (.*)")) this.rum= charges.get(i).substring(5);
            else if(charges.get(i).matches("(?s)timezoneMode: (.*)")) this.timezoneMode= charges.get(i).substring(14);
            else if(charges.get(i).matches("(?s)chargeRatePlanName: (.*)")) this.chargeRatePlanName= charges.get(i).substring(20);
            else if(charges.get(i).matches("(?s)rolloverRatePlanName: (.*)")) this.rolloverRatePlanName= charges.get(i).substring(22);
            else if(charges.get(i).matches("(?s)ratePlanIID: (.*)")) this.ratePlanIID= charges.get(i).substring(13);
            else if(charges.get(i).matches("(?s)prorateFirst: (.*)")) this.prorateFirst= charges.get(i).substring(14);
            else if(charges.get(i).matches("(?s)prorateLast: (.*)")) this.prorateLast= charges.get(i).substring(13);
            else if(charges.get(i).matches("(?s)minQuantity: (.*)")) this.minQuantity= Integer.parseInt(charges.get(i).substring(13));
            else if(charges.get(i).matches("(?s)chargeRatePlanInfo")){i++; this.targetEngine= charges.get(i).substring(14);}
            else if(charges.get(i).matches("(?s)incrementQuantity(.*)") || charges.get(i).matches("(?s)roundingMode(.*)") || charges.get(i).matches("(?s)minQuantityUnit(.*)")){}
            else{ validaRollover(); return i;}
        }
        validaRollover();
        return charges.size();
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
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
    
    public void getPDF(Element element){
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.eventName));
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.chargeRatePlanName));
    }

    private void validaRollover() {
        if(this.eventName.equals("EventBillingCycleRolloverMonthly") && !this.chargeRatePlanName.equals("")){
            this.rolloverRatePlanName= this.chargeRatePlanName;
            this.chargeRatePlanName="";
            this.rum="Occurrence";
        }
    }
    
    
    
}
