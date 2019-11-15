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
public class AlterationEventMapT extends Nodo{
    private String eventName="";
    private int valid=0;
    private String targetEngine="RRE";
    private String alterationRatePlanName="";
    private String ratePlanIID="";
    private String validAtStartNotValidAtEnd="PRORATE_DISCOUNT";
    private String notValidAtStartValidAtEnd="PRORATE_DISCOUNT";
    private String notValidAtStartNotValidAtEnd= "PRORATE_DISCOUNT";
    private boolean snowball= false;
    private String alterationRatePlanSelectorName= "";
    
    public AlterationEventMapT(){
        
    }
    
    public AlterationEventMapT(int id){
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

    public String getValidAtStartNotValidAtEnd() {
        return validAtStartNotValidAtEnd;
    }

    public void setValidAtStartNotValidAtEnd(String validAtStartNotValidAtEnd) {
        this.validAtStartNotValidAtEnd = validAtStartNotValidAtEnd;
    }

    public String getTargetEngine() {
        return targetEngine;
    }

    public void setTargetEngine(String targetEngine) {
        this.targetEngine = targetEngine;
    }

    public String getNotValidAtStartValidAtEnd() {
        return notValidAtStartValidAtEnd;
    }

    public void setNotValidAtStartValidAtEnd(String notValidAtStartValidAtEnd) {
        this.notValidAtStartValidAtEnd = notValidAtStartValidAtEnd;
    }

    public String getRatePlanIID() {
        return ratePlanIID;
    }

    public void setRatePlanIID(String ratePlanIID) {
        this.ratePlanIID = ratePlanIID;
    }

    public String notValidAtStartNotValidAtEnd() {
        return notValidAtStartNotValidAtEnd;
    }

    public void setProrateFirst(String notValidAtStartNotValidAtEnd) {
        this.notValidAtStartNotValidAtEnd = notValidAtStartNotValidAtEnd;
    }

    public String getAlterationRatePlanSelectorName() {
        return alterationRatePlanSelectorName;
    }

    public void setAlterationRatePlanSelectorName(String alterationRatePlanSelectorName) {
        this.alterationRatePlanSelectorName = alterationRatePlanSelectorName;
    }

    public String getAlterationRatePlanName() {
        return alterationRatePlanName;
    }

    public void setAlterationRatePlanName(String alterationRatePlanName) {
        this.alterationRatePlanName = alterationRatePlanName;
    }

    public boolean isSnowball() {
        return snowball;
    }

    public void setSnowball(boolean snowball) {
        this.snowball = snowball;
    }

    public String getNotValidAtStartNotValidAtEnd() {
        return notValidAtStartNotValidAtEnd;
    }

    public void setNotValidAtStartNotValidAtEnd(String notValidAtStartNotValidAtEnd) {
        this.notValidAtStartNotValidAtEnd = notValidAtStartNotValidAtEnd;
    }
    
    
    
    @Override
    public String toString(String s) {
        String sel="Sel", selector="Selector";
        if(!alterationRatePlanName.equals("")){
            sel=""; selector="";
        }
        return 
        s+"<alterationEventMap>\n" +
        s+"\t<eventName>"+eventName+"</eventName>\n" +
        s+"\t<validIfCancelled>"+((valid<=2)?"true":"false")+"</validIfCancelled>\n" +
        s+"\t<validIfInactive>"+((valid%2!=0)?"true":"false")+"</validIfInactive>\n" +
        s+"\t<snowball>"+snowball+"</snowball>\n"+
        s+"\t<validAtStartNotValidAtEnd>"+validAtStartNotValidAtEnd+"</validAtStartNotValidAtEnd>\n"+
        s+"\t<notValidAtStartValidAtEnd>"+notValidAtStartValidAtEnd+"</notValidAtStartValidAtEnd>\n"+
        s+"\t<notValidAtStartNotValidAtEnd>"+notValidAtStartNotValidAtEnd+"</notValidAtStartNotValidAtEnd>\n"+
        s+"\t<alterationRatePlan"+selector+"Info>\n" +
        s+"\t\t<targetEngine>"+targetEngine+"</targetEngine>\n" +
        s+"\t</alterationRatePlan"+selector+"Info>\n" +
        s+"\t<alterationRatePlanName>"+alterationRatePlanName+"</alterationRatePlanName>\n" +
        s+"\t<alterationRatePlanSelectorName>"+alterationRatePlanSelectorName+"</alterationRatePlanSelectorName>\n" +
        s+"\t<ratePlan"+sel+"IID>"+ratePlanIID+"</ratePlan"+sel+"IID>\n" +
        s+"</alterationEventMap>";
    }
    
    @Override
    public int procesar(ArrayList<String> alterations, int index, String user) {
        
        
        for(int i=index; i<alterations.size();i++) {
            
            if(alterations.get(i).matches("(?s)eventName: (.*)")) this.eventName= alterations.get(i).substring(11);
            else if(alterations.get(i).matches("(?s)validIfCancelled: (.*)")){
                        this.valid= ((alterations.get(i).substring(18).equals("true"))?1:3);
                        i++;
                        this.valid= ((alterations.get(i).substring(17).equals("false"))?this.valid+1:this.valid);
            }
            else if(alterations.get(i).matches("(?s)valid: (.*)")) this.valid= Integer.parseInt(alterations.get(i).substring(7));
            else if(alterations.get(i).matches("(?s)validAtStartNotValidAtEnd: (.*)")) this.validAtStartNotValidAtEnd= alterations.get(i).substring(27);
            else if(alterations.get(i).matches("(?s)notValidAtStartValidAtEnd: (.*)")) this.notValidAtStartValidAtEnd= alterations.get(i).substring(27);
            else if(alterations.get(i).matches("(?s)alterationRatePlanName: (.*)")) this.alterationRatePlanName= alterations.get(i).substring(24);
            else if(alterations.get(i).matches("(?s)alterationRatePlanSelectorName: (.*)")) this.alterationRatePlanSelectorName= alterations.get(i).substring(32);
            else if(alterations.get(i).matches("(?s)ratePlanIID: (.*)")) this.ratePlanIID= alterations.get(i).substring(13);
            else if(alterations.get(i).matches("(?s)ratePlanSelIID: (.*)")) this.ratePlanIID= alterations.get(i).substring(16);
            else if(alterations.get(i).matches("(?s)notValidAtStartNotValidAtEnd: (.*)")) this.notValidAtStartNotValidAtEnd= alterations.get(i).substring(30);
            else if(alterations.get(i).matches("(?s)snowball: (.*)")) this.snowball= Boolean.valueOf(alterations.get(i).substring(10));
            else if(alterations.get(i).matches("(?s)alterationRatePlanInfo")){i++; this.targetEngine= alterations.get(i).substring(14);}
            else if(alterations.get(i).matches("(?s)alterationRatePlanSelectorInfo")){i++; this.targetEngine= alterations.get(i).substring(32);}
            else{ validaRollover(); return i;}
        }
        validaRollover();
        return alterations.size();
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((eventName+"/"+valid+"/"+snowball+"/"+validAtStartNotValidAtEnd+"/"+targetEngine+"/"+notValidAtStartNotValidAtEnd+"/"+ratePlanIID+"/"+alterationRatePlanSelectorName+"/"+alterationRatePlanName+"/"+notValidAtStartValidAtEnd).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    @Override
    public void merge(Nodo nodo) {
        AlterationEventMapT alterationEventT= (AlterationEventMapT) nodo;
        
    }
    
    public void getPDF(Element element){
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.eventName));
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.alterationRatePlanName+this.alterationRatePlanSelectorName));
    }

    private void validaRollover() {
        if(this.eventName.equals("EventBillingCycleRolloverMonthly") && !this.alterationRatePlanName.equals("")){
            this.alterationRatePlanSelectorName= this.alterationRatePlanName;
            this.alterationRatePlanName="";
        }
    }
    
    
        
}
