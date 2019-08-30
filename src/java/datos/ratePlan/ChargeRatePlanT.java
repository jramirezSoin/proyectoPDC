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
public class ChargeRatePlanT extends Nodo{
    
    private String name="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="";
    private String taxCode="";
    private String applicableRums="";
    private String applicableQuantity="";
    private String taxTime="";
    private String todMode="";
    private String applicableQtyTreatment="";
    private String permittedName="";
    private String permittedType="";
    private String eventName="";
    private String cycleFeeFlag="";
    private String billOffset="";
    private String description="";
    private SubscriberCurrencyT subscriberCurrency= new SubscriberCurrencyT();
    
    public ChargeRatePlanT(){}
    public ChargeRatePlanT(int id){this.id=id;}

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

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getApplicableRums() {
        return applicableRums;
    }

    public void setApplicableRums(String applicableRums) {
        this.applicableRums = applicableRums;
    }

    public String getApplicableQuantity() {
        return applicableQuantity;
    }

    public void setApplicableQuantity(String applicableQuantity) {
        this.applicableQuantity = applicableQuantity;
    }

    public String getTaxTime() {
        return taxTime;
    }

    public void setTaxTime(String taxTime) {
        this.taxTime = taxTime;
    }

    public String getTodMode() {
        return todMode;
    }

    public void setTodMode(String todMode) {
        this.todMode = todMode;
    }

    public String getApplicableQtyTreatment() {
        return applicableQtyTreatment;
    }

    public void setApplicableQtyTreatment(String applicableQtyTreatment) {
        this.applicableQtyTreatment = applicableQtyTreatment;
    }

    public String getPermittedName() {
        return permittedName;
    }

    public void setPermittedName(String permittedName) {
        this.permittedName = permittedName;
    }

    public String getPermittedType() {
        return permittedType;
    }

    public void setPermittedType(String permittedType) {
        this.permittedType = permittedType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCycleFeeFlag() {
        return cycleFeeFlag;
    }

    public void setCycleFeeFlag(String cycleFeeFlag) {
        this.cycleFeeFlag = cycleFeeFlag;
    }

    public String getBillOffset() {
        return billOffset;
    }

    public void setBillOffset(String billOffset) {
        this.billOffset = billOffset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubscriberCurrencyT getSubscriberCurrency() {
        return subscriberCurrency;
    }

    public void setSubscriberCurrency(SubscriberCurrencyT subscriberCurrency) {
        this.subscriberCurrency = subscriberCurrency;
    }
    
    @Override
    public String toString(String s) {
        return 
        s+"<chargeRatePlan xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+
        s+"\t<name>"+name+"</name>\n"+
        s+"\t<internalId>"+internalId+"</internalId>\n"+
        s+"\t<pricingProfileName>"+pricingProfileName+"</pricingProfileName>\n"+
        s+"\t<priceListName>"+priceListName+"</priceListName>\n"+
        s+"\t<taxCode>"+taxCode+"</taxCode>\n"+
        s+"\t<applicableRums>"+applicableRums+"</applicableRums>\n"+
        s+"\t<applicableQuantity>"+applicableQuantity+"</applicableQuantity>\n"+
        s+"\t<taxTime>"+taxTime+"</taxTime>\n"+
        s+"\t<todMode>"+todMode+"</todMode>\n"+
        s+"\t<applicableQtyTreatment>"+applicableQtyTreatment+"</applicableQtyTreatment>\n"+
        s+"\t<permittedName>"+permittedName+"</permittedName>\n"+
        s+"\t<permittedType>"+permittedType+"</permittedType>\n"+
        s+"\t<eventName>"+eventName+"</eventName>\n"+
        s+"\t<cycleFeeFlag>"+cycleFeeFlag+"</cycleFeeFlag>\n"+
        s+"\t<billOffset>"+billOffset+"</billOffset>\n"+
        s+"\t"+subscriberCurrency.toString(s+"\t")+"\n"+
        s+"</chargeRatePlan>";
    }

    @Override
    public int procesar(ArrayList<String> chargeRates2, int index) {
        ArrayList<String> chargeRates=  (ArrayList<String>)chargeRates2.clone();
        for(int i=index; i<chargeRates.size();i++) {
            
            if(chargeRates.get(i).matches("(?s)name: (.*)")) this.name= chargeRates.get(i).substring(6);
            else if(chargeRates.get(i).matches("(?s)internalId: (.*)")) this.internalId= chargeRates.get(i).substring(12);
            else if(chargeRates.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= chargeRates.get(i).substring(20);
            else if(chargeRates.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= chargeRates.get(i).substring(15);
            else if(chargeRates.get(i).matches("(?s)taxCode: (.*)")) this.taxCode= chargeRates.get(i).substring(9);
            else if(chargeRates.get(i).matches("(?s)applicableRums: (.*)")) this.applicableRums= chargeRates.get(i).substring(16);
            else if(chargeRates.get(i).matches("(?s)applicableQuantity: (.*)")) this.applicableQuantity= chargeRates.get(i).substring(20);
            else if(chargeRates.get(i).matches("(?s)taxTime: (.*)")) this.taxTime= chargeRates.get(i).substring(9);
            else if(chargeRates.get(i).matches("(?s)todMode: (.*)")) this.todMode= chargeRates.get(i).substring(9);
            else if(chargeRates.get(i).matches("(?s)applicableQtyTreatment: (.*)")) this.applicableQtyTreatment= chargeRates.get(i).substring(24);
            else if(chargeRates.get(i).matches("(?s)permittedName: (.*)")) this.permittedName= chargeRates.get(i).substring(15);
            else if(chargeRates.get(i).matches("(?s)permittedType: (.*)")) this.permittedType= chargeRates.get(i).substring(15);
            else if(chargeRates.get(i).matches("(?s)eventName: (.*)")) this.eventName= chargeRates.get(i).substring(11);
            else if(chargeRates.get(i).matches("(?s)cycleFeeFlag: (.*)")) this.cycleFeeFlag= chargeRates.get(i).substring(14);
            else if(chargeRates.get(i).matches("(?s)billOffset: (.*)")) this.billOffset= chargeRates.get(i).substring(12);
            else if(chargeRates.get(i).matches("(?s)description: (.*)")) this.description= chargeRates.get(i).substring(13);
            else if(chargeRates.get(i).matches("(?s)subscriberCurrency")){
                
                SubscriberCurrencyT subscriberCurrency = new SubscriberCurrencyT();
                i= subscriberCurrency.procesar(chargeRates, i+1);
                i--;
                this.setSubscriberCurrency(subscriberCurrency);
            }else return i;
        }
        return chargeRates.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            this.subscriberCurrency.procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((name+"/"+internalId+"/"+pricingProfileName+"/"+priceListName+"/"+taxCode+"/"+applicableRums+"/"+applicableQuantity+"/"+taxTime+"/"+todMode+"/"+applicableQtyTreatment+"/"+permittedName+"/"+permittedType+"/"+eventName+"/"+cycleFeeFlag+"/"+billOffset+"/"+description).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || subscriberCurrency.buscar(buscar)){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    
}
