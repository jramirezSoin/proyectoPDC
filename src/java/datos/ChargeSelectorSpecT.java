/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import control.ControlFunctions;
import control.ControlPath;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ChargeSelectorSpecT extends Nodo{
    private String name="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="Default";
    private String glid="";
    private String glidName="";
    private String validFrom="";
    private String validTo="";
    private String balanceElementNumCode="";
    private String balanceElementName="";
    private String pricingName="";
    private String zoneResult="";
    private String timeModelTagName="";
    private ListaT eventConditions= new ListaT();
    private String description="";
    
    public ChargeSelectorSpecT(){}
    public ChargeSelectorSpecT(int id){this.id=id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getPricingProfileName() {
        return pricingProfileName;
    }

    public void setPricingProfileName(String pricingProfileName) {
        this.pricingProfileName = pricingProfileName;
    }

    public String getPriceListName() {
        return priceListName;
    }

    public void setPriceListName(String priceListName) {
        this.priceListName = priceListName;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public String getGlidName() {
        return glidName;
    }

    public void setGlidName(String glidName) {
        this.glidName = glidName;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getBalanceElementNumCode() {
        return balanceElementNumCode;
    }

    public void setBalanceElementNumCode(String balanceElementNumCode) {
        this.balanceElementNumCode = balanceElementNumCode;
    }

    public String getBalanceElementName() {
        return balanceElementName;
    }

    public void setBalanceElementName(String balanceElementName) {
        this.balanceElementName = balanceElementName;
    }

    public String getPricingName() {
        return pricingName;
    }

    public void setPricingName(String pricingName) {
        this.pricingName = pricingName;
    }

    public String getZoneResult() {
        return zoneResult;
    }

    public void setZoneResult(String zoneResult) {
        this.zoneResult = zoneResult;
    }

    public String getTimeModelTagName() {
        return timeModelTagName;
    }

    public void setTimeModelTagName(String timeModelTagName) {
        this.timeModelTagName = timeModelTagName;
    }

    public ListaT getEventConditions() {
        return eventConditions;
    }

    public void setEventConditions(ListaT eventConditions) {
        this.eventConditions = eventConditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "<chargeSelectorSpec xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" +
                "    <name>"+name+"</name>\n" +
                "    <internalId>"+internalId+"</internalId>\n" +
                "    <pricingProfileName>"+pricingProfileName+"</pricingProfileName>\n" +
                "    <priceListName>"+priceListName+"</priceListName>\n" +
                "    <validFrom>"+validFrom+"</validFrom>\n" +
                "    <validTo>"+validTo+"</validTo>\n" +
                "    <timeModelTagName>"+timeModelTagName+"</timeModelTagName>"+
                "    <zoneResult>"+zoneResult+"</zoneResult>"+
                "    <balanceElementNumCode>"+balanceElementNumCode+"</balanceElementNumCode>\n" +
                "    <pricingName>"+pricingName+"</pricingName>"+
                "    <glid>"+glid+"</glid>"+
                "    <eventConditions>\n" +
                "        <eventField>"+eventConditions.unit+"</eventField>\n" +
                "        <eventValue>"+eventConditions.valor+"</eventValue>\n" +
                "    </eventConditions>\n" +
                "</chargeSelectorSpec>";
    }
    
    
    
    @Override
    public int procesar(ArrayList<String> rollovers, int index) {
        ArrayList<String> rollovers2= (ArrayList<String>)rollovers.clone();
        for(int i=index; i<rollovers2.size();i++) { 
           if(rollovers2.get(i).matches("(?s)name: (.*)")) this.name= rollovers2.get(i).substring(6);
           else if(rollovers2.get(i).matches("(?s)internalId: (.*)")) this.internalId= rollovers2.get(i).substring(12);
           else if(rollovers2.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= rollovers2.get(i).substring(15);
           else if(rollovers2.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= rollovers2.get(i).substring(20);
           else if(rollovers2.get(i).matches("(?s)validFrom: (.*)")) this.validFrom= rollovers2.get(i).substring(12);
           else if(rollovers2.get(i).matches("(?s)validTo: (.*)")) this.validTo= rollovers2.get(i).substring(9);
           else if(rollovers2.get(i).matches("(?s)glid: (.*)")){ this.glid= rollovers2.get(i).substring(6); this.glidName=ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("code",rollovers2.get(i).substring(6)),"name");}
           else if(rollovers2.get(i).matches("(?s)glidName: (.*)")){ this.glidName= rollovers2.get(i).substring(10); this.glid=ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("name",rollovers2.get(i).substring(10)),"code");}
           else if(rollovers2.get(i).matches("(?s)pricingName: (.*)")) this.pricingName= rollovers2.get(i).substring(13);
           else if(rollovers2.get(i).matches("(?s)description: (.*)")) this.description= rollovers2.get(i).substring(13);
           else if(rollovers2.get(i).matches("(?s)eventField: (.*)")) this.eventConditions.unit= rollovers2.get(i).substring(12);
           else if(rollovers2.get(i).matches("(?s)eventValue: (.*)")) this.eventConditions.valor= rollovers2.get(i).substring(12);
           else if(rollovers2.get(i).matches("(?s)timeModelTagName: (.*)")) this.timeModelTagName= rollovers2.get(i).substring(18);
           else if(rollovers2.get(i).matches("(?s)zoneResult: (.*)")) this.zoneResult= rollovers2.get(i).substring(12);
           else if(rollovers2.get(i).matches("(?s)balanceElementNumCode: (.*)")){ this.balanceElementNumCode= rollovers2.get(i).substring(23); this.balanceElementName=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("numericCode",rollovers2.get(i).substring(23)),"name");}
           else if(rollovers2.get(i).matches("(?s)balanceElementName: (.*)")){ this.balanceElementName= rollovers2.get(i).substring(20); this.balanceElementNumCode=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name",rollovers2.get(i).substring(20)),"numericCode");}
           else if(("eventConditions").contains(rollovers2.get(i))){
               eventConditions=new ListaT();
           }
           else return i;
        }
        return rollovers2.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((name+"/"+validFrom+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+validTo+"/"+glidName+"/"+timeModelTagName+"/"+balanceElementName+"/"+zoneResult+"/"+description+"/"+pricingName+"/"+eventConditions.valor+"/"+eventConditions.unit).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
}
