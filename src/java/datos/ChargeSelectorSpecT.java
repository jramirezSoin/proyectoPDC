/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import control.ControlFunctions;
import control.ControlPath;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ChargeSelectorSpecT extends Nodo {

    private String name = "";
    private String internalId = "";
    private String pricingProfileName = "";
    private String priceListName = "Default";
    private String glid = "";
    private String glidName = "";
    private String validFrom = "";
    private String validTo = "";
    private String balanceElementNumCode = "";
    private String balanceElementName = "";
    private String pricingName = "";
    private String zoneResult = "";
    private String timeModelTagName = "";
    private ListaT eventConditions = new ListaT();
    private String description = "";

    public ChargeSelectorSpecT() {
    }

    public ChargeSelectorSpecT(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public String getGlidName() {
        return glidName;
    }

    public void setGlidName(String glidName) {
        this.glidName = glidName;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
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

    public String getPricingName() {
        return pricingName;
    }

    public void setPricingName(String pricingName) {
        this.pricingName = pricingName;
    }

    public String getZoneResult() {
        return zoneResult;
    }

    public void setZoneResult(String zoneResult) {
        this.zoneResult = zoneResult;
    }

    public String getTimeModelTagName() {
        return timeModelTagName;
    }

    public void setTimeModelTagName(String timeModelTagName) {
        this.timeModelTagName = timeModelTagName;
    }

    public ListaT getEventConditions() {
        return eventConditions;
    }

    public void setEventConditions(ListaT eventConditions) {
        this.eventConditions = eventConditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return   "<chargeSelectorSpec xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+
                 "\t<name>" + name + "</name>\n"+
                 ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                 "\t<pricingProfileName>" + pricingProfileName + "</pricingProfileName>\n"+
                 "\t<priceListName>" + priceListName + "</priceListName>\n"+
                 "\t<validFrom>" + validFrom + "</validFrom>\n"+
                 "\t<validTo>" + validTo + "</validTo>\n"+
                 "\t<timeModelTagName>" + timeModelTagName + "</timeModelTagName>\n"+
                 "\t<zoneResult>" + zoneResult + "</zoneResult>\n"+
                 "\t<balanceElementNumCode>" + balanceElementNumCode + "</balanceElementNumCode>\n"+
                 "\t<pricingName>" + pricingName + "</pricingName>\n"+
                 "\t<glid>" + glid + "</glid>\n"+
                 "\t<eventConditions>\n"+
                 "\t\t<eventField>" + eventConditions.unit + "</eventField>\n"+
                 "\t\t<eventValue>" + eventConditions.valor + "</eventValue>\n"+
                 "\t</eventConditions>\n"+
                 "</chargeSelectorSpec>";
    }

    @Override
    public int procesar(ArrayList<String> chargeSelectors2, int index) {
        ArrayList<String> chargeSelectors = (ArrayList<String>) chargeSelectors2.clone();
        for (int i = index; i < chargeSelectors.size(); i++) {
            if (chargeSelectors.get(i).matches("(?s)name: (.*)")) {
                this.name = chargeSelectors.get(i).substring(6);
            } else if (chargeSelectors.get(i).matches("(?s)internalId: (.*)")) {
                this.internalId = chargeSelectors.get(i).substring(12);
            } else if (chargeSelectors.get(i).matches("(?s)priceListName: (.*)")) {
                this.priceListName = chargeSelectors.get(i).substring(15);
            } else if (chargeSelectors.get(i).matches("(?s)pricingProfileName: (.*)")) {
                this.pricingProfileName = chargeSelectors.get(i).substring(20);
            } else if (chargeSelectors.get(i).matches("(?s)validFrom: (.*)")) {
                this.validFrom = chargeSelectors.get(i).substring(11);
            } else if (chargeSelectors.get(i).matches("(?s)validTo: (.*)")) {
                this.validTo = chargeSelectors.get(i).substring(9);
            }  else if (chargeSelectors.get(i).matches("(?s)glid: (.*)")) {
                this.glid = chargeSelectors.get(i).substring(6);
                String[] balances= glid.split("\\|");
                if(balances.length==1 || !glid.contains("|"))
                    this.glidName = ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("code", chargeSelectors.get(i).substring(6)), "name");
                else{
                    this.glidName="";
                    for(String balance: balances){ this.glidName+="|"+ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("code", balance), "name");}
                    glidName = this.glidName.substring(1);
                    }
            } else if (chargeSelectors.get(i).matches("(?s)glidName: (.*)")) {
                this.glidName = chargeSelectors.get(i).substring(10);
                String[] balances= glidName.split("\\|");
                if(balances.length==1 || !glidName.contains("|"))
                    this.glid = ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("name", chargeSelectors.get(i).substring(10)), "code");
                else{
                    this.glid="";
                    for(String balance: balances){ this.glid+="|"+ControlFunctions.Buscar(ControlPath.glidClick, new ListaT("name", balance), "code");}
                    glid = this.glid.substring(1);
                    }
            } else if (chargeSelectors.get(i).matches("(?s)pricingName: (.*)")) {
                this.pricingName = chargeSelectors.get(i).substring(13);
            } else if (chargeSelectors.get(i).matches("(?s)description: (.*)")) {
                this.description = chargeSelectors.get(i).substring(13);
            } else if (chargeSelectors.get(i).matches("(?s)eventField: (.*)")) {
                this.eventConditions.unit = chargeSelectors.get(i).substring(12);
            } else if (chargeSelectors.get(i).matches("(?s)eventValue: (.*)")) {
                this.eventConditions.valor = chargeSelectors.get(i).substring(12);
            } else if (chargeSelectors.get(i).matches("(?s)timeModelTagName: (.*)")) {
                this.timeModelTagName = chargeSelectors.get(i).substring(18);
            } else if (chargeSelectors.get(i).matches("(?s)zoneResult: (.*)")) {
                this.zoneResult = chargeSelectors.get(i).substring(12);
            } else if (chargeSelectors.get(i).matches("(?s)balanceElementNumCode: (.*)")) {
                this.balanceElementNumCode = chargeSelectors.get(i).substring(23);
                String[] balances= balanceElementNumCode.split("\\|");
                if(balances.length==1 || !balanceElementNumCode.contains("|"))
                    this.balanceElementName = ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("numericCode", chargeSelectors.get(i).substring(23)), "name");
                else{
                    this.balanceElementName="";
                    for(String balance: balances){this.balanceElementName+="|"+ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("numericCode", balance), "name");}
                    balanceElementName = this.balanceElementName.substring(1);
                    }
            } else if (chargeSelectors.get(i).matches("(?s)balanceElementName: (.*)")) {
                this.balanceElementName = chargeSelectors.get(i).substring(20);
                String[] balances= balanceElementName.split("\\|");
                if(balances.length==1 || !balanceElementName.contains("|"))
                    this.balanceElementNumCode = ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name", chargeSelectors.get(i).substring(20)), "numericCode");
                else{
                    this.balanceElementNumCode="";
                    for(String balance: balances){ this.balanceElementNumCode+="|"+ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name", balance), "numericCode");}
                    balanceElementNumCode = this.balanceElementNumCode.substring(1);
                    }
            } else if (("eventConditions").contains(chargeSelectors.get(i))) {
                eventConditions = new ListaT();
            } else if (("Nglids Nbalances Ntimes").contains(chargeSelectors.get(i))) {
                String s="";
                int aux=i;
                i++;
                for(int j=i;i < chargeSelectors.size(); i++){
                    if (chargeSelectors.get(i).matches("nt: (.*)")){ s+="|"+chargeSelectors.get(i).substring(4);}
                    else{ break;}
                }
                i--;
                if(!s.equals("")){
                    s=s.substring(1);
                switch(chargeSelectors.get(aux)){
                    case "Nglids": chargeSelectors.set(i,"glidName: "+s); break;
                    case "Nbalances": chargeSelectors.set(i,"balanceElementName: "+s); break;
                    case "Ntimes": chargeSelectors.set(i,"timeModelTagName: "+s); break;
                }
                i--;
                }

            }else {
                return i;
            }
        }
        return chargeSelectors.size();
    }

    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if (indexs.size() == 0) {
            index = this.procesar(lista, index);
        }
        return index;
    }

    @Override
    public boolean buscar(String buscar) {
        if ((name + "/" + validFrom + "/" + internalId + "/" + priceListName + "/" + pricingProfileName + "/" + validTo + "/" + glidName + "/" + timeModelTagName + "/" + balanceElementName + "/" + zoneResult + "/" + description + "/" + pricingName + "/" + eventConditions.valor + "/" + eventConditions.unit).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())) {
            this.visibilidad = true;
            return true;
        } else {
            this.visibilidad = false;
            return false;
        }
    }

}
