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
public class PriceTierValidityPeriodT extends Nodo{
    private String lowerBound="";
    private String validFrom="";
    
    public PriceTierValidityPeriodT(){}
    public PriceTierValidityPeriodT(int id){this.id=id;}

    public String getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(String lowerBound) {
        this.lowerBound = lowerBound;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }
    
    
}
