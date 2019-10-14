/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class TierRangeT extends Nodo{
    private BoundT upperBound = new BoundT();
    private ArrayList<AlterationChargeT> charges = new ArrayList<>();

    public TierRangeT() {
    }
    
    public TierRangeT(int id) {
        this.id=id;
    }

    public BoundT getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BoundT upperBound) {
        this.upperBound = upperBound;
    }

    public ArrayList<AlterationChargeT> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<AlterationChargeT> charges) {
        this.charges = charges;
    }
    
    
    
}
