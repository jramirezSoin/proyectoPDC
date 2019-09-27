/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.Nodo;
import java.util.ArrayList;
/**
 *
 * @author Joseph Ramírez
 */
public class ChargeT extends Nodo{
    private String tipo="recurringCharge";
    private String price="0.0";
    private String unitOfMeasure="";
    private String balanceElementNumCode="";
    private String balanceElementName="";
    private boolean discountable=false;
    private boolean proratable=false;
    private String priceType="CONSUMPTION";
    private String glid="0";
    private String glidName="";
    private PriceValidityT priceValidity;
    private String incrementStep="";
    private String incrementRounding="";
    private String taxTime="";
    private String taxCode="";
    
    public ChargeT(){
    }
    
    public ChargeT(int id){
        this.id=id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getBalanceElementName() {
        return balanceElementName;
    }

    public void setBalanceElementName(String balanceElementName) {
        this.balanceElementName = balanceElementName;
    }

    public boolean isDiscountable() {
        return discountable;
    }

    public void setDiscountable(boolean discountable) {
        this.discountable = discountable;
    }

    public boolean isProratable() {
        return proratable;
    }

    public void setProratable(boolean proratable) {
        this.proratable = proratable;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public PriceValidityT getPriceValidity() {
        return priceValidity;
    }

    public void setPriceValidity(PriceValidityT priceValidity) {
        this.priceValidity = priceValidity;
    }

    public String getIncrementStep() {
        return incrementStep;
    }

    public void setIncrementStep(String incrementStep) {
        this.incrementStep = incrementStep;
    }

    public String getIncrementRounding() {
        return incrementRounding;
    }

    public void setIncrementRounding(String incrementRounding) {
        this.incrementRounding = incrementRounding;
    }

    public String getTaxTime() {
        return taxTime;
    }

    public void setTaxTime(String taxTime) {
        this.taxTime = taxTime;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getGlidName() {
        return glidName;
    }

    public void setGlidName(String glidName) {
        this.glidName = glidName;
    }
    
    
    
    @Override
    public String toString(String s) {
        return s+"<"+tipo+">\n" +
            s+"\t<price>"+price+"</price>\n" +
            s+"\t<unitOfMeasure>"+unitOfMeasure+"</unitOfMeasure>\n" +
            s+"\t<balanceElementNumCode>"+balanceElementNumCode+"</balanceElementNumCode>\n" +
            s+"\t<discountable>"+discountable+"</discountable>\n" +
            s+"\t<priceType>"+priceType+"</priceType>\n" +
            ((taxTime.equals(""))?"":s+"\t<taxTime>"+taxTime+"</taxTime>\n")+
            ((taxCode.equals(""))?"":s+"\t<taxCode>"+taxCode+"</taxCode>\n")+    
            s+"\t<glid>"+glid+"</glid>\n"+
            (!(tipo.equals("scaledCharge"))?"":s+"\t<incrementStep>"+incrementStep+"</incrementStep>\n")+    
            (!(tipo.equals("scaledCharge"))?"":s+"\t<incrementRounding>"+incrementRounding+"</incrementRounding>\n")+
            ((priceValidity==null)?"":priceValidity.toString(s+"\t")+"\n")+
            (!(tipo.equals("recurringCharge"))?"":s+"\t<proratable>"+proratable+"</proratable>\n")+            
            s+"</"+tipo+">";    
    }

    @Override
    public int procesar(ArrayList<String> charge, int index) {
        int itemCount = 0;
        for(int i=index; i<charge.size();i++) {
            if(charge.get(i).matches("(?s)price: (.*)")) this.price= charge.get(i).substring(7);
            else if(charge.get(i).matches("(?s)unitOfMeasure: (.*)")) this.unitOfMeasure= charge.get(i).substring(15);
            else if(charge.get(i).matches("(?s)balanceElementNumCode: (.*)")){ this.balanceElementNumCode= charge.get(i).substring(23); this.balanceElementName=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("numericCode",charge.get(i).substring(23)),"name");}
            else if(charge.get(i).matches("(?s)balanceElementName: (.*)")){ this.balanceElementName= charge.get(i).substring(20); this.balanceElementNumCode=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name",charge.get(i).substring(20)),"numericCode");}
            else if(charge.get(i).matches("(?s)discountable: (.*)")) this.discountable= Boolean.valueOf(charge.get(i).substring(14));
            else if(charge.get(i).matches("(?s)proratable: (.*)")) this.proratable= Boolean.valueOf(charge.get(i).substring(12));
            else if(charge.get(i).matches("(?s)priceType: (.*)")) this.priceType= charge.get(i).substring(11);
            else if(charge.get(i).matches("(?s)glid: (.*)")){ this.glid= charge.get(i).substring(6); this.glidName=ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("code",charge.get(i).substring(6)),"name");}
            else if(charge.get(i).matches("(?s)glidName: (.*)")){ this.glidName= charge.get(i).substring(10); this.glid=ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("name",charge.get(i).substring(10)),"code");}
            
            else if(charge.get(i).matches("(?s)incrementStep: (.*)")) this.incrementStep= charge.get(i).substring(15);
            else if(charge.get(i).matches("(?s)incrementRounding: (.*)")) this.incrementRounding= charge.get(i).substring(19);
            else if(charge.get(i).matches("(?s)taxTime: (.*)")) this.taxTime= charge.get(i).substring(9);
            else if(charge.get(i).matches("(?s)taxCode: (.*)")) this.taxCode= charge.get(i).substring(9);
            
            else if(charge.get(i).matches("(?s)scaled: (.*)")) this.tipo= ((charge.get(i).substring(8).equals("true"))?"scaledCharge":"fixedCharge");
            else if(charge.get(i).matches("(?s)priceValidity")){ 
                
                PriceValidityT resul = new PriceValidityT(itemCount);
                itemCount++;
                i= resul.procesar(charge, i+1);
                i--;
                this.setPriceValidity(resul);
            }else{validar(); return i;}
        }
        validar();
        return charge.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((price+"/"+unitOfMeasure+"/"+balanceElementNumCode+"/"+balanceElementName+"/"+discountable+"/"+proratable+"/"+priceType+"/"+glid+"/"+incrementStep+"/"+incrementRounding+"/"+taxTime+"/"+taxCode).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || priceValidity.buscar(buscar)){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    private void validar(){
        if(!this.tipo.equals("scaledCharge")){this.incrementRounding=this.incrementStep=this.taxCode=this.taxTime="";}
        if(this.priceType.equals("CONSUMPTION")){this.priceValidity=null;}
    }

    void getRumCurrency(String rum, String currency) {
        System.out.println("LLEGA "+currency+" "+this.balanceElementNumCode);
        if(this.balanceElementNumCode.equals("") || this.balanceElementNumCode.equals("null") || Integer.parseInt(this.balanceElementNumCode)<1000){
            this.balanceElementNumCode = ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("code", currency), "numericCode");
            this.balanceElementName = ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("code", currency), "name");
        }
    }
    
}
