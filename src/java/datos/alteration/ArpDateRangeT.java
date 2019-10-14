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
public class ArpDateRangeT extends Nodo{
    private String startDate = "";
    private String endDate = "";
    private ArrayList<AlterationConfigurationT> alterationConfigurations = new ArrayList<>();

    public ArpDateRangeT() {
    }
    
    public ArpDateRangeT(int id) {
        this.id=id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<AlterationConfigurationT> getAlterationConfigurations() {
        return alterationConfigurations;
    }

    public void setAlterationConfigurations(ArrayList<AlterationConfigurationT> alterationConfigurations) {
        this.alterationConfigurations = alterationConfigurations;
    }
    
    
}
