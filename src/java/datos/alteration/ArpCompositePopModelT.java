/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import datos.ListaT;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ArpCompositePopModelT extends Nodo{
    
    private String name="";
    private String distributionMethod = "";
    private ListaT tierBasis= new ListaT("","");
    private BoundT lowerBound= new BoundT();
    private ArrayList<TierRangeT> tierRange= new ArrayList<>();

    public ArpCompositePopModelT() {
    }
    
    public ArpCompositePopModelT(int id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistributionMethod() {
        return distributionMethod;
    }

    public void setDistributionMethod(String distributionMethod) {
        this.distributionMethod = distributionMethod;
    }

    public ListaT getTierBasis() {
        return tierBasis;
    }

    public void setTierBasis(ListaT tierBasis) {
        this.tierBasis = tierBasis;
    }

    public BoundT getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BoundT lowerBound) {
        this.lowerBound = lowerBound;
    }

    public ArrayList<TierRangeT> getTierRange() {
        return tierRange;
    }

    public void setTierRange(ArrayList<TierRangeT> tierRange) {
        this.tierRange = tierRange;
    }
    
    
    
}
