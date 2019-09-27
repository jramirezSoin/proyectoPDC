/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.Nodo;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class CrpCompositePopModelT extends Nodo implements ResultI {

    private String name = "pricing";
    private String popModelType = "recurringPopModel";
    private String lowerBound = "NO_MIN";
    private String rumTierExpression = "";
    private String balanceElementNumCode = "";
    private String balanceElementName = "";
    private String rumName = "";
    private String enforceCreditLimit = "false";
    private String distributionMethod = "FROM_BAL_IMPACT";
    private String currencyCode = "";
    private String applicableQuantity = "";
    private ArrayList<PriceTierRangeT> priceTierRanges = new ArrayList<>();
    private ArrayList<PriceTierValidityPeriodT> priceTierValidityPeriods = new ArrayList<>();

    public CrpCompositePopModelT() {
    }

    public CrpCompositePopModelT(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString(String s) {
        String priceTierRangess = "";
        if (this.priceTierValidityPeriods.size() == 0) {
            for (PriceTierRangeT range : priceTierRanges) {
                priceTierRangess += range.toString(s + "\t\t\t") + "\n";
            }
        } else {
            for (PriceTierValidityPeriodT period : priceTierValidityPeriods) {
                String aux = "";
                for (PriceTierRangeT range : priceTierRanges) {
                    if (range.getPriceTierValidityPeriod() == period.getId()) {
                        aux += range.toString(s + "\t\t\t\t") + "\n";
                    }
                }
                priceTierRangess += period.toString(s + "\t\t\t", aux) + "\n";
            }
        }
        return s + "<crpCompositePopModel>\n"
                + s + "\t<name>" + name + "</name>\n"
                + s + "\t<" + popModelType + ">\n"
                + s + "\t\t<priceTier>\n"
                + ((lowerBound.equals("")) ? s + "\t\t\t<distributionMethod>" + distributionMethod + "</distributionMethod>\n" : s + "\t\t\t<lowerBound>" + lowerBound + "</lowerBound>\n")
                + s + "\t\t\t<tierBasis>\n"
                + ((balanceElementNumCode.equals("")) ? s + "\t\t\t\t<rumTierExpression/>\n" : s + "\t\t\t\t<balanceTierExpression>\n" + s + "\t\t\t\t\t<balanceElementNumCode>" + balanceElementNumCode + "</balanceElementNumCode>\n" + s + "\t\t\t\t</balanceTierExpression>\n")
                + s + "\t\t\t</tierBasis>\n"
                + s + "\t\t\t<rumName>" + rumName + "</rumName>\n"
                + s + "\t\t\t<enforceCreditLimit>" + enforceCreditLimit + "</enforceCreditLimit>\n"
                + ((currencyCode.equals("")) ? "" : s + "\t\t\t<currencyCode>" + currencyCode + "</currencyCode>\n")
                + priceTierRangess
                + ((applicableQuantity.equals("")) ? "" : s + "\t\t\t<applicableQuantity>" + applicableQuantity + "</applicableQuantity>\n")
                + s + "\t\t</priceTier>\n"
                + s + "\t</" + popModelType + ">\n"
                + s + "</crpCompositePopModel>";

    }

    @Override
    public int procesar(ArrayList<String> crp, int index) {
        int itemCount = 0;
        boolean popModel = false;
        boolean priceTier = false;
        boolean tierBasis = false;
        boolean balanceTierExpression = false;
        for (int i = index; i < crp.size(); i++) {

            if (crp.get(i).matches("(?s)name: (.*)")) {
                this.name = crp.get(i).substring(6);
            } else if (("usageChargePopModel oneTimePopModel recurringPopModel").contains(crp.get(i))) {
                this.popModelType = crp.get(i);
                popModel = true;
            } else if (("priceTier").contains(crp.get(i)) && popModel) {
                priceTier = true;
            } else if (("tierBasis").contains(crp.get(i)) && priceTier) {
                tierBasis = true;
            } else if (("balanceTierExpression").contains(crp.get(i)) && tierBasis) {
                balanceTierExpression = true;
            } else if (crp.get(i).matches("(?s)lowerBound: (.*)")) {
                this.lowerBound = crp.get(i).substring(12);
            } else if (crp.get(i).matches("(?s)rumTierExpression: (.*)") && tierBasis) {
                this.rumTierExpression = crp.get(i).substring(19);
            } else if (crp.get(i).matches("(?s)balanceElementNumCode: (.*)") && balanceTierExpression) {
                this.balanceElementNumCode = crp.get(i).substring(23);
                this.balanceElementName = ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("numericCode", crp.get(i).substring(23)), "name");
            } else if (crp.get(i).matches("(?s)balanceElementName: (.*)")) {
                this.balanceElementName = crp.get(i).substring(20);
                this.balanceElementNumCode = ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name", crp.get(i).substring(20)), "numericCode");
            } else if (crp.get(i).matches("(?s)rumName: (.*)") && priceTier) {
                this.rumName = crp.get(i).substring(9);
            } else if (crp.get(i).matches("(?s)enforceCreditLimit: (.*)") && priceTier) {
                this.enforceCreditLimit = crp.get(i).substring(20);
            } else if (crp.get(i).matches("(?s)distributionMethod: (.*)") && priceTier) {
                this.distributionMethod = crp.get(i).substring(20);
            } else if (crp.get(i).matches("(?s)currencyCode: (.*)") && priceTier) {
                this.currencyCode = crp.get(i).substring(14);
            } else if (crp.get(i).matches("(?s)applicableQuantity: (.*)") && priceTier) {
                this.applicableQuantity = crp.get(i).substring(20);
            } else if (("priceTierRange tierRange").contains(crp.get(i)) && priceTier) {
                PriceTierRangeT resul;
                if (priceTierValidityPeriods.size() == 0) {
                    resul = new PriceTierRangeT(priceTierRanges.size());
                } else {
                    resul = new PriceTierRangeT(priceTierRanges.size(), priceTierValidityPeriods.size() - 1);
                }
                resul.setTipo(crp.get(i));
                itemCount++;
                i = resul.procesar(crp, i + 1);
                i--;
                this.priceTierRanges.add(resul);
            } else if (crp.get(i).matches("(?s)priceTierValidityPeriod") && priceTier) {
                PriceTierValidityPeriodT resul = new PriceTierValidityPeriodT(itemCount);
                itemCount++;
                i = resul.procesar(crp, i + 1);
                i--;
                this.priceTierValidityPeriods.add(resul);
            } else if (crp.get(i).matches("(?s)priceTierPeriods")) {
                i++;
                for (int j = 0; i < crp.size(); i++) {
                    if (crp.get(i).matches("(?s)startDate_n(.*)")) {
                        PriceTierValidityPeriodT period = this.getPriceTierValidityPeriods().get(this.getPriceTierValidityPeriods().size() - 1);
                        try {
                            this.getPriceTierValidityPeriods().add(new PriceTierValidityPeriodT(period.getId() + 1, period.getLowerBound(), ControlFunctions.getParseString(crp.get(i).split(": ")[1])));
                        } catch (ParseException ex) {
                            Logger.getLogger(CrpCompositePopModelT.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (crp.get(i).matches("(?s)startDate_(.*)")) {
                        try {
                            this.getPriceTierValidityPeriods().get(j).setValidFrom(ControlFunctions.getParseString(crp.get(i).split(": ")[1]));
                        } catch (ParseException ex) {
                            Logger.getLogger(CrpCompositePopModelT.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (crp.get(i).matches("(?s)upperBound_(.*)")) {
                        System.out.println("J= "+j);
                        for (int l = 0; i < crp.size(); i++) {
                            if (crp.get(i).matches("(?s)upperBound_n: (.*)")){
                                this.getPriceTierRanges().add(new PriceTierRangeT(this.getPriceTierRanges().size(),j,crp.get(i).split(": ")[1],"TierRange"));
                                if(this.getPriceTierRanges().size()!=1){
                                    this.priceTierRanges.get(this.getPriceTierRanges().size()-1).setCharges((ArrayList<ChargeT>)this.priceTierRanges.get(this.getPriceTierRanges().size()-2).getCharges().clone());
                                }
                            }else if(crp.get(i).matches("(?s)upperBound_(.*)")){
                                this.getPriceTierRanges().get(Integer.parseInt(crp.get(i).split("_")[1].split(":")[0])).setUpperBound(crp.get(i).split(": ")[1]);
                            }
                            else{i--; break;}
                        }
                        j++;
                    } else {
                        i--;
                        break;
                    }
                }
            } else {
                return i;
            }
        }
        return crp.size();
    }

    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if (indexs.size() == 0) {
            index = this.procesar(lista, index);
        } else {
            int i = indexs.get(0);
            indexs.remove(0);
            if (indexs.get(0) == 1) {
                this.priceTierValidityPeriods.get(i).procesarI(lista, index, indexs);
            } else {
                this.priceTierRanges.get(i).procesarI(lista, index, indexs);
            }
        }
        return index;
    }

    @Override
    public boolean buscar(String buscar) {
        for (PriceTierValidityPeriodT item : this.priceTierValidityPeriods) {
            item.buscar(buscar);
        }
        for (PriceTierRangeT item : this.priceTierRanges) {
            item.buscar(buscar);
        }
        if ((name + "/" + popModelType + "/" + lowerBound + "/" + rumTierExpression + "/" + balanceElementNumCode + "/" + balanceElementName + "/" + rumName + "/" + enforceCreditLimit + "/" + distributionMethod + "/" + currencyCode + "/" + applicableQuantity).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())) {
            this.visibilidad = true;
            return true;
        } else {
            this.visibilidad = false;
            return false;
        }
    }

    @Override
    public void getRumCurrency(String rum, String currency) {
        this.rumName = rum;
        this.currencyCode = currency;
        for(PriceTierRangeT tier: this.priceTierRanges){
            for(ChargeT charge: tier.getCharges()){
                charge.getRumCurrency(rum,currency);
            }
        }
    }

}
