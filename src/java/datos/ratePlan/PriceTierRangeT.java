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
public class PriceTierRangeT extends Nodo{
    private String tipo="";
    private String upperBound="";
    private ArrayList<ChargeT> charges= new ArrayList<>();
    private int priceTierValidityPeriod = -1;
    
    public PriceTierRangeT(){}
    public PriceTierRangeT(int id){this.id=id;}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(String upperBound) {
        this.upperBound = upperBound;
    }

    public ArrayList<ChargeT> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<ChargeT> charges) {
        this.charges = charges;
    }

    public int getPriceTierValidityPeriod() {
        return priceTierValidityPeriod;
    }

    public void setPriceTierValidityPeriod(int priceTierValidityPeriod) {
        this.priceTierValidityPeriod = priceTierValidityPeriod;
    }
    
    
    
    
}
