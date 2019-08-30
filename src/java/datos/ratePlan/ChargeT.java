/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import datos.Nodo;
/**
 *
 * @author Joseph Ramírez
 */
public class ChargeT extends Nodo{
    private String tipo="";
    private String price="";
    private String unitOfMeasure="";
    private String balanceElementNumCode="";
    private String balanceElementName="";
    private boolean discountable=false;
    private boolean proratable=false;
    private String priceType="";
    private String glid="";
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
    
    
}
