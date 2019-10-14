/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import datos.Nodo;

/**
 *
 * @author Joseph Ramírez
 */
public class AlterationRatePlanT extends Nodo{
    private String name = "";
    private String description = "";
    private String internalId = "";
    private String pricingProfileName = "";
    private String priceListName = "";
    private String taxCode="";
    private ArpDateRangeT arpDateRange = new ArpDateRangeT();

    public AlterationRatePlanT() {
    }
    
    public AlterationRatePlanT(int id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ArpDateRangeT getArpDateRange() {
        return arpDateRange;
    }

    public void setArpDateRange(ArpDateRangeT arpDateRange) {
        this.arpDateRange = arpDateRange;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    
    
    
    
    
    
}
