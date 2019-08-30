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
public class CrpCompositePopModelT extends Nodo implements ResultI{
    private String crpCompositePopModelName="";
    private String popModelType="";
    private String lowerBound="";
    private String rumTierExpression="";
    private String balanceElementNumCode="";
    private String balanceElementName="";
    private String rumName="";
    private String enforceCreditLimit="";
    private String distributionMethod="";
    private String currencyCode="";
    private String applicableQuantity="";
    private ArrayList<PriceTierRangeT> priceTierRanges= new ArrayList<>();
    private ArrayList<PriceTierValidityPeriodT> priceTierValidityPeriods= new ArrayList<>();
    
    public CrpCompositePopModelT(){}
    public CrpCompositePopModelT(int id){this.id=id;}

    public String getCrpCompositePopModelName() {
        return crpCompositePopModelName;
    }

    public void setCrpCompositePopModelName(String crpCompositePopModelName) {
        this.crpCompositePopModelName = crpCompositePopModelName;
    }

    public String getPopModelType() {
        return popModelType;
    }

    public void setPopModelType(String popModelType) {
        this.popModelType = popModelType;
    }

    public String getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(String lowerBound) {
        this.lowerBound = lowerBound;
    }

    public String getRumTierExpression() {
        return rumTierExpression;
    }

    public void setRumTierExpression(String rumTierExpression) {
        this.rumTierExpression = rumTierExpression;
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

    public String getRumName() {
        return rumName;
    }

    public void setRumName(String rumName) {
        this.rumName = rumName;
    }

    public String getEnforceCreditLimit() {
        return enforceCreditLimit;
    }

    public void setEnforceCreditLimit(String enforceCreditLimit) {
        this.enforceCreditLimit = enforceCreditLimit;
    }

    public String getDistributionMethod() {
        return distributionMethod;
    }

    public void setDistributionMethod(String distributionMethod) {
        this.distributionMethod = distributionMethod;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getApplicableQuantity() {
        return applicableQuantity;
    }

    public void setApplicableQuantity(String applicableQuantity) {
        this.applicableQuantity = applicableQuantity;
    }

    public ArrayList<PriceTierRangeT> getPriceTierRanges() {
        return priceTierRanges;
    }

    public void setPriceTierRanges(ArrayList<PriceTierRangeT> priceTierRanges) {
        this.priceTierRanges = priceTierRanges;
    }

    public ArrayList<PriceTierValidityPeriodT> getPriceTierValidityPeriods() {
        return priceTierValidityPeriods;
    }

    public void setPriceTierValidityPeriods(ArrayList<PriceTierValidityPeriodT> priceTierValidityPeriods) {
        this.priceTierValidityPeriods = priceTierValidityPeriods;
    }
    
    
    
    
    
}
