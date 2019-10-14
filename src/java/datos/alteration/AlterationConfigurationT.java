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
public class AlterationConfigurationT extends Nodo{
    private String applicableChargeAndQuantity = "";
    private String triggerSpecName = "";
    private String chargeSelectorSpecName = "";
    private ArpCompositePopModelT arpCompositePopModel = new ArpCompositePopModelT();

    public AlterationConfigurationT() {
    }
    
    public AlterationConfigurationT(int id) {
        this.id=id;
    }

    public String getApplicableChargeAndQuantity() {
        return applicableChargeAndQuantity;
    }

    public void setApplicableChargeAndQuantity(String applicableChargeAndQuantity) {
        this.applicableChargeAndQuantity = applicableChargeAndQuantity;
    }

    public String getTriggerSpecName() {
        return triggerSpecName;
    }

    public void setTriggerSpecName(String triggerSpecName) {
        this.triggerSpecName = triggerSpecName;
    }

    public String getChargeSelectorSpecName() {
        return chargeSelectorSpecName;
    }

    public void setChargeSelectorSpecName(String chargeSelectorSpecName) {
        this.chargeSelectorSpecName = chargeSelectorSpecName;
    }

    public ArpCompositePopModelT getArpCompositePopModel() {
        return arpCompositePopModel;
    }

    public void setArpCompositePopModel(ArpCompositePopModelT arpCompositePopModel) {
        this.arpCompositePopModel = arpCompositePopModel;
    }
    
    
}
