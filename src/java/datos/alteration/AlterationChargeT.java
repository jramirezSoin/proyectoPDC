/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import datos.ListaT;
import datos.Nodo;
import datos.ratePlan.PriceValidityT;

/**
 *
 * @author Joseph Ramírez
 */
public class AlterationChargeT extends Nodo{
    
    private String price = "";
    private String unitOfMeasure = "";
    private String balanceElementNumCode = "";
    private String alterationAppliesTo = "";
    private ListaT alterationBasedOn = new ListaT("","");
    private String priceType = "";
    private String incrementStep = "";
    private String prorateLastIncrementStep = "";
    private String glid = "";
    private PriceValidityT priceValidity;
    
    public AlterationChargeT() {
    }
    
    public AlterationChargeT(int id) {
        this.id= id;
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

    public String getAlterationAppliesTo() {
        return alterationAppliesTo;
    }

    public void setAlterationAppliesTo(String alterationAppliesTo) {
        this.alterationAppliesTo = alterationAppliesTo;
    }

    public ListaT getAlterationBasedOn() {
        return alterationBasedOn;
    }

    public void setAlterationBasedOn(ListaT alterationBasedOn) {
        this.alterationBasedOn = alterationBasedOn;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getIncrementStep() {
        return incrementStep;
    }

    public void setIncrementStep(String incrementStep) {
        this.incrementStep = incrementStep;
    }

    public String getProrateLastIncrementStep() {
        return prorateLastIncrementStep;
    }

    public void setProrateLastIncrementStep(String prorateLastIncrementStep) {
        this.prorateLastIncrementStep = prorateLastIncrementStep;
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
    
    
    
    
}
