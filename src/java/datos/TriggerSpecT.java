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
public class TriggerSpecT extends Nodo{
    
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="";
    private ArrayList<ExpressionT> expressions;
    
    public TriggerSpecT(int id) {
        this.id= id;
        this.expressions = new ArrayList<>();
    }
    
    public TriggerSpecT(int id, String name, String description, String internalId, String pricingProfileName, String priceListName){
        this.id = id;
        this.name = name;
        this.description = description;
        this.internalId = internalId;
        this.priceListName = priceListName;
        this.pricingProfileName = pricingProfileName;
        this.expressions = new ArrayList<>();        
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

    public void setDescription(String description) {
        this.description = description;
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

    public ArrayList<ExpressionT> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<ExpressionT> expressions) {
        this.expressions = expressions;
    }
    
        @Override
    public int procesar(ArrayList<String> triggerSpecs, int index) {
        int itemCount = 0;
        for(int i=index; i<triggerSpecs.size();i++) {
            
            if(triggerSpecs.get(i).matches("(?s)name: (.*)")) this.name= triggerSpecs.get(i).substring(6);
            else if(triggerSpecs.get(i).matches("(?s)description: (.*)")) this.description= triggerSpecs.get(i).substring(13);
            else if(triggerSpecs.get(i).matches("(?s)internalId: (.*)")) this.internalId= triggerSpecs.get(i).substring(12);
            else if(triggerSpecs.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= triggerSpecs.get(i).substring(15);
            else if(triggerSpecs.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= triggerSpecs.get(i).substring(20);
            else if(("chargeTriggerExpression complexTriggerExpression quantityTriggerExpression balanceTriggerExpression").contains(triggerSpecs.get(i))){     
                ExpressionT expression = new ExpressionT(itemCount,triggerSpecs.get(i));
                itemCount++;
                i= expression.procesar(triggerSpecs, i+1);
                i--;
                this.expressions.add(expression);
            }else return i;
        }
        return triggerSpecs.size();
    }
    
    @Override
    public String toString() {
        String zoneItems="";
        for(int i=0;i<this.expressions.size();i++){
            zoneItems+=this.expressions.get(i).toString()+"\n";
        }
        return "<triggerSpec xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" 
                + "    <name>" + name + "</name>\n    <description>" + description + "</description>\n    <internalId>"
                + internalId + "</internalId>\n    <pricingProfileName>" + pricingProfileName + "</pricingProfileName>\n    <priceListName>" 
                + priceListName + "</priceListName>\n" + zoneItems + "</triggerSpec>";
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.expressions.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(ExpressionT item: this.expressions){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.expressions.add((ExpressionT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.expressions.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.expressions.size();i++){
            this.expressions.get(i).id--;
        }
    }
    
}
