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
public class RolloverT extends Nodo{
    private String name="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="";
    private String startDate="";
    private String endDate="";
    private String glid="";
    private String unitOfMeasure="";
    private String balanceElementNumCode="";
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
        for(int i=index; i<rollovers.size();i++) {
            
           if(rollovers.get(i).matches("name: (.*)")) this.name= rollovers.get(i).substring(6);
           else if(rollovers.get(i).matches("internalId: (.*)")) this.internalId= rollovers.get(i).substring(12);
           else if(rollovers.get(i).matches("priceListName: (.*)")) this.priceListName= rollovers.get(i).substring(15);
           else if(rollovers.get(i).matches("pricingProfileName: (.*)")) this.pricingProfileName= rollovers.get(i).substring(20);
           else if(rollovers.get(i).matches("startDate: (.*)")) this.startDate= rollovers.get(i).substring(11);
           else if(rollovers.get(i).matches("endDate: (.*)")) this.endDate= rollovers.get(i).substring(9);
           else if(rollovers.get(i).matches("glid: (.*)")) this.glid= rollovers.get(i).substring(6);
           else if(rollovers.get(i).matches("unitOfMeasure: (.*)")) this.unitOfMeasure= rollovers.get(i).substring(15);
           else if(rollovers.get(i).matches("balanceElementNumCode: (.*)")) this.balanceElementNumCode= rollovers.get(i).substring(23);
           else if(rollovers.get(i).matches("rolloverUnits: (.*)")) this.rolloverUnits= rollovers.get(i).substring(15);
           else if(rollovers.get(i).matches("rolloverMaxUnits: (.*)")) this.rolloverMaxUnits= rollovers.get(i).substring(18);
           else if(rollovers.get(i).matches("rolloverCount: (.*)")) this.rolloverCount= rollovers.get(i).substring(15);
           else if(("dateRange rolloverPopModel rolloverCharge").contains(rollovers.get(i))){
                
            }
            else return i;
        }
        return rollovers.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((name+"/"+startDate+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+endDate+"/"+glid+"/"+unitOfMeasure+"/"+balanceElementNumCode+"/"+rolloverUnits+"/"+rolloverMaxUnits+"/"+rolloverCount).toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
}
