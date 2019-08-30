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
public class PriceValidityT extends Nodo {
    private String startValidityMode="";
    private String endValidityMode="";
    private String validityRange="";
    private String relativeStartOffset="";
    private String relativeEndOffset="";
    private String relativeEndOffsetUnit="";
    
    public PriceValidityT(){}
    
   public PriceValidityT(int id){this.id=id;}

    public String getStartValidityMode() {
        return startValidityMode;
    }

    public void setStartValidityMode(String startValidityMode) {
        this.startValidityMode = startValidityMode;
    }

    public String getEndValidityMode() {
        return endValidityMode;
    }

    public void setEndValidityMode(String endValidityMode) {
        this.endValidityMode = endValidityMode;
    }

    public String getValidityRange() {
        return validityRange;
    }

    public void setValidityRange(String validityRange) {
        this.validityRange = validityRange;
    }

    public String getRelativeStartOffset() {
        return relativeStartOffset;
    }

    public void setRelativeStartOffset(String relativeStartOffset) {
        this.relativeStartOffset = relativeStartOffset;
    }

    public String getRelativeEndOffset() {
        return relativeEndOffset;
    }

    public void setRelativeEndOffset(String relativeEndOffset) {
        this.relativeEndOffset = relativeEndOffset;
    }

    public String getRelativeEndOffsetUnit() {
        return relativeEndOffsetUnit;
    }

    public void setRelativeEndOffsetUnit(String relativeEndOffsetUnit) {
        this.relativeEndOffsetUnit = relativeEndOffsetUnit;
    }
    
    
}
