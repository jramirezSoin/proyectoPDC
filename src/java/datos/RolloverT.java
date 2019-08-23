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
public class RolloverT extends Nodo{
    private String name="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="Default";
    private String startDate="";
    private String endDate="";
    private String glid="";
    private String unitOfMeasure="";
    private String balanceElementNumCode="";
    private String balanceElementName="";
    private String rolloverUnits="";
    private String rolloverMaxUnits="";
    private String rolloverCount="";

    public RolloverT() {
    }
    
    public RolloverT(int id){
        this.id=id;
    }

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getBalanceElementNumCode() {
        return balanceElementNumCode;
    }

    public void setBalanceElementNumCode(String balanceElementNumCode) {
        this.balanceElementNumCode = balanceElementNumCode;
    }

    public String getRolloverUnits() {
        return rolloverUnits;
    }

    public void setRolloverUnits(String rolloverUnits) {
        this.rolloverUnits = rolloverUnits;
    }

    public String getRolloverMaxUnits() {
        return rolloverMaxUnits;
    }

    public void setRolloverMaxUnits(String rolloverMaxUnits) {
        this.rolloverMaxUnits = rolloverMaxUnits;
    }

    public String getRolloverCount() {
        return rolloverCount;
    }

    public void setRolloverCount(String rolloverCount) {
        this.rolloverCount = rolloverCount;
    }

    public String getBalanceElementName() {
        return balanceElementName;
    }

    public void setBalanceElementName(String balanceElementName) {
        this.balanceElementName = balanceElementName;
    }
    
    

    @Override
    public String toString() {
        return "<rolloverRatePlan xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">"
                + "    <name>" + name + "</name>\n    <internalId>" + internalId + "</internalId>\n    <pricingProfileName>" 
                + pricingProfileName + "</pricingProfileName>\n    <priceListName>" + priceListName + "</priceListName>    <dateRange>\n"
                +"        <startDate>" + startDate + "</startDate>\n        <endDate>" + endDate +"</endDate>\n        <rolloverPopModel>\n            <rolloverCharge>\n"
                + "                <glid>" + glid + "</glid>\n                <unitOfMeasure>" + unitOfMeasure 
                + "</unitOfMeasure>\n                <balanceElementNumCode>" + balanceElementNumCode 
                + "</balanceElementNumCode>\n                <rolloverUnits>" + rolloverUnits 
                + "</rolloverUnits>\n                <rolloverMaxUnits>" + rolloverMaxUnits 
                + "</rolloverMaxUnits>\n                <rolloverCount>" + rolloverCount + "</rolloverCount>\n"
                + "            </rolloverCharge>\n        </rolloverPopModel>\n    </dateRange>\n</rolloverRatePlan>";
    }
    
    
    
    @Override
    public int procesar(ArrayList<String> rollovers, int index) {
        ArrayList<String> rollovers2= (ArrayList<String>)rollovers.clone();
        for(int i=index; i<rollovers2.size();i++) {
           System.out.println(rollovers2.get(i)); 
           if(rollovers2.get(i).matches("(?s)name: (.*)")) this.name= rollovers2.get(i).substring(6);
           else if(rollovers2.get(i).matches("(?s)internalId: (.*)")) this.internalId= rollovers2.get(i).substring(12);
           else if(rollovers2.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= rollovers2.get(i).substring(15);
           else if(rollovers2.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= rollovers2.get(i).substring(20);
           else if(rollovers2.get(i).matches("(?s)startDate: (.*)")) this.startDate= rollovers2.get(i).substring(11);
           else if(rollovers2.get(i).matches("(?s)endDate: (.*)")) this.endDate= rollovers2.get(i).substring(9);
           else if(rollovers2.get(i).matches("(?s)glid: (.*)")) this.glid= rollovers2.get(i).substring(6);
           else if(rollovers2.get(i).matches("(?s)unitOfMeasure: (.*)")) this.unitOfMeasure= rollovers2.get(i).substring(15);
           else if(rollovers2.get(i).matches("(?s)rolloverUnits: (.*)")) this.rolloverUnits= rollovers2.get(i).substring(15);
           else if(rollovers2.get(i).matches("(?s)rolloverMaxUnits: (.*)")) this.rolloverMaxUnits= rollovers2.get(i).substring(18);
           else if(rollovers2.get(i).matches("(?s)rolloverCount: (.*)")) this.rolloverCount= rollovers2.get(i).substring(15);
           else if(rollovers2.get(i).matches("(?s)balanceElementNumCode: (.*)")){ this.balanceElementNumCode= rollovers2.get(i).substring(23); this.balanceElementName=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("numericCode",rollovers2.get(i).substring(23)),"name");}
           else if(rollovers2.get(i).matches("(?s)balanceElementName: (.*)")){ this.balanceElementName= rollovers2.get(i).substring(20); this.balanceElementNumCode=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name",rollovers2.get(i).substring(20)),"numericCode");}
           else if(("dateRange rolloverPopModel rolloverCharge").contains(rollovers2.get(i))){
                
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
        if((name+"/"+startDate+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+endDate+"/"+glid+"/"+unitOfMeasure+"/"+balanceElementNumCode+"/"+rolloverUnits+"/"+rolloverMaxUnits+"/"+rolloverCount).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
}
