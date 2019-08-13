/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class BalanceElementT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String priceListName="";
    private boolean obsolete=false;
    private String code="";
    private String numCode="";
    private String symbol="";
    private boolean transientElement=false;
    private boolean foldable=false;
    private boolean counter=false;
    private ArrayList<RoundingRuleT> roundingRules;
    private String consumptionRule="";
    
    public BalanceElementT(int id){
        this.id= id;
        this.roundingRules = new ArrayList<>();
    }

    public BalanceElementT(int id,String code,String consumptionRule, String numCode, String symbol, boolean transientElement, boolean foldable, boolean counter, String name, String description, String internalId, String priceListName, boolean obsolete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.internalId = internalId;
        this.priceListName = priceListName;
        this.obsolete = obsolete;
        this.code=code;
        this.numCode=numCode;
        this.symbol=symbol;
        this.transientElement=transientElement;
        this.foldable=foldable;
        this.counter=counter;
        this.roundingRules = new ArrayList<>();
        this.consumptionRule= consumptionRule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean getTransientElement() {
        return transientElement;
    }

    public void setTransientElement(boolean transientElement) {
        this.transientElement = transientElement;
    }

    public boolean isFoldable() {
        return foldable;
    }

    public void setFoldable(boolean foldable) {
        this.foldable = foldable;
    }

    public boolean isCounter() {
        return counter;
    }

    public void setCounter(boolean counter) {
        this.counter = counter;
    }

    public ArrayList<RoundingRuleT> getRoundingRules() {
        return roundingRules;
    }

    public void setRoundingRules(ArrayList<RoundingRuleT> roundingRules) {
        this.roundingRules = roundingRules;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getPriceListName() {
        return priceListName;
    }

    public void setPriceListName(String priceListName) {
        this.priceListName = priceListName;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    public ArrayList<RoundingRuleT> getZoneItems() {
        return roundingRules;
    }

    public void setZoneItems(ArrayList<RoundingRuleT> roundingRules) {
        this.roundingRules = roundingRules;
    }

    public String getConsumptionRule() {
        return consumptionRule;
    }

    public void setConsumptionRule(String consumptionRule) {
        this.consumptionRule = consumptionRule;
    }
    
    

    @Override
    public String toString() {
        String roundingRules="";
        for(int i=0;i<this.roundingRules.size();i++){
            roundingRules+=this.roundingRules.get(i).toString()+"\n";
        }
        return "<balanceElements xmlns:mtd=\"http://xmlns.oracle.com/communications/platform/model/Metadata\" xmlns:cim=\"http://xmlns.oracle.com/communications/platform/model/Config\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" 
                + "    <name>" + name + "</name>\n    <description>" + description + "</description>\n    <internalId>"
                + internalId + "</internalId>\n    <priceListName>" + priceListName + "</priceListName>\n    <obsolete>" 
                + obsolete + "</obsolete>\n    <code>"+code+"</code>\n    <numericCode>"+numCode+"</numericCode>\n    <symbol>"
                + symbol +"</symbol>\n    <transientElement>"+transientElement+"</transientElement>\n    <foldable>"
                + foldable +"</foldable>\n    <counter>"+counter+"</counter>\n" + roundingRules + "    <consumptionRule>"
                + consumptionRule +"</consumptionRule>\n</balanceElements>";
    }

    
    @Override
    public int procesar(ArrayList<String> balances, int index) {
        int itemCount = 0;
        for(int i=index; i<balances.size();i++) {
            
            if(balances.get(i).matches("name: (.*)")) this.name= balances.get(i).substring(6);
            else if(balances.get(i).matches("description: (.*)")) this.description= balances.get(i).substring(13);
            else if(balances.get(i).matches("internalId: (.*)")) this.internalId= balances.get(i).substring(12);
            else if(balances.get(i).matches("priceListName: (.*)")) this.priceListName= balances.get(i).substring(15);
            else if(balances.get(i).matches("numericCode: (.*)")) this.numCode= balances.get(i).substring(13);
            else if(balances.get(i).matches("code: (.*)")) this.code= balances.get(i).substring(6);
            else if(balances.get(i).matches("symbol: (.*)")) this.symbol= balances.get(i).substring(8);
            else if(balances.get(i).matches("transientElement: (.*)")) this.transientElement= Boolean.valueOf(balances.get(i).substring(18));
            else if(balances.get(i).matches("consumptionRule: (.*)")) this.consumptionRule= balances.get(i).substring(17);
            else if(balances.get(i).matches("obsolete: (.*)")) this.obsolete= Boolean.valueOf(balances.get(i).substring(10));
            else if(balances.get(i).matches("foldable: (.*)")) this.foldable= Boolean.valueOf(balances.get(i).substring(10));
            else if(balances.get(i).matches("counter: (.*)")) this.counter= Boolean.valueOf(balances.get(i).substring(9));
            else if(balances.get(i).matches("roundingRules")){ 
                
                RoundingRuleT roundingRule = new RoundingRuleT(itemCount);
                itemCount++;
                i= roundingRule.procesar(balances, i+1);
                i--;
                this.roundingRules.add(roundingRule);
            }else return i;
        }
        return balances.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.roundingRules.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(RoundingRuleT item: this.roundingRules){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+obsolete+"/"+code+"/"+numCode+"/"+symbol+"/"+transientElement+"/"+foldable+"/"+counter+"/"+consumptionRule).toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.roundingRules.add((RoundingRuleT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.roundingRules.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.roundingRules.size();i++){
            this.roundingRules.get(i).id--;
        }
    }
    
}