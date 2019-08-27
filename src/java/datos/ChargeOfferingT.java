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
public class ChargeOfferingT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="Default";
    private String timeRange="";
    private String productSpecName="";
    private String customerSpecName="";
    private String offerType="";
    private String priority="";
    private boolean partial=false;
    private String purchaseMax="";
    private String ownMax="";
    private String taxSupplier="";
    private ArrayList<ChargeEventMapT> chargeEvents= new ArrayList<>();
    
    public ChargeOfferingT(){
        
    }
    
    public ChargeOfferingT(int id){
        this.id=id;
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

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getProductSpecName() {
        return productSpecName;
    }

    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    public String getCustomerSpecName() {
        return customerSpecName;
    }

    public void setCustomerSpecName(String customerSpecName) {
        this.customerSpecName = customerSpecName;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isPartial() {
        return partial;
    }

    public void setPartial(boolean partial) {
        this.partial = partial;
    }

    public String getPurchaseMax() {
        return purchaseMax;
    }

    public void setPurchaseMax(String purchaseMax) {
        this.purchaseMax = purchaseMax;
    }

    public String getOwnMax() {
        return ownMax;
    }

    public void setOwnMax(String ownMax) {
        this.ownMax = ownMax;
    }

    public ArrayList<ChargeEventMapT> getChargeEvents() {
        return chargeEvents;
    }

    public void setChargeEvents(ArrayList<ChargeEventMapT> chargeEvents) {
        this.chargeEvents = chargeEvents;
    }

    public String getTaxSupplier() {
        return taxSupplier;
    }

    public void setTaxSupplier(String taxSupplier) {
        this.taxSupplier = taxSupplier;
    }
    
    
    
    @Override
    public String toString() {
        String chargeEvents="";
        for(int i=0;i<this.chargeEvents.size();i++){
            chargeEvents+=this.chargeEvents.get(i).toString()+"\n";
        }
        return "<chargeOffering externalID=\"Bono de 12 meses Plan Ultra 3 4GLTE\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" +
            "    <name>"+name+"</name>\n" +
            "    <description>"+description+"</description>\n" +
            "    <internalId>"+internalId+"</internalId>\n" +
            "    <pricingProfileName>"+pricingProfileName+"</pricingProfileName>\n" +
            "    <priceListName>"+priceListName+"</priceListName>\n" +
            "    <timeRange>"+timeRange+"</timeRange>\n" +
            ((productSpecName.equals(""))?"    <customerSpecName>"+customerSpecName+"</customerSpecName>\n":
                "    <productSpecName>"+productSpecName+"</productSpecName>\n") +
            "    <offerType>"+offerType+"</offerType>\n" +
            "    <priority>"+priority+"</priority>\n" +
            "    <partial>"+partial+"</partial>\n" +
            "    <purchaseMin>-1.0</purchaseMin>\n" +
            "    <purchaseMax>"+purchaseMax+"</purchaseMax>\n" +
            "    <ownMin>-1.0</ownMin>\n" +
            "    <ownMax>"+ownMax+"</ownMax>\n" +
            "    <taxSupplier>"+taxSupplier+"</taxSupplier>\n" +
            "    <applicableQuantity>REMAINING</applicableQuantity>\n"+chargeEvents+"</chargeOffering>";
    }

    @Override
    public int procesar(ArrayList<String> chargeOfferings2, int index) {
        ArrayList<String> chargeOfferings= (ArrayList<String>)chargeOfferings2.clone();
        int itemCount = 0;
        for(int i=index; i<chargeOfferings.size();i++) {
            
            if(chargeOfferings.get(i).matches("(?s)name: (.*)")) this.name= chargeOfferings.get(i).substring(6);
            else if(chargeOfferings.get(i).matches("(?s)description: (.*)")) this.description= chargeOfferings.get(i).substring(13);
            else if(chargeOfferings.get(i).matches("(?s)internalId: (.*)")) this.internalId= chargeOfferings.get(i).substring(12);
            else if(chargeOfferings.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= chargeOfferings.get(i).substring(15);
            else if(chargeOfferings.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= chargeOfferings.get(i).substring(20);
            else if(chargeOfferings.get(i).matches("(?s)timeRange: (.*)")) this.timeRange= chargeOfferings.get(i).substring(11);
            else if(chargeOfferings.get(i).matches("(?s)customerSpecName: (.*)")) this.customerSpecName= chargeOfferings.get(i).substring(18);
            else if(chargeOfferings.get(i).matches("(?s)productSpecName: (.*)")) this.productSpecName= chargeOfferings.get(i).substring(17);
            else if(chargeOfferings.get(i).matches("(?s)specName: (.*)")){ 
                        if(chargeOfferings.get(i).substring(10).equals("Account"))this.customerSpecName= chargeOfferings.get(i).substring(10);
                        else this.productSpecName= chargeOfferings.get(i).substring(10);}
            else if(chargeOfferings.get(i).matches("(?s)offerType: (.*)")) this.offerType= chargeOfferings.get(i).substring(11);
            else if(chargeOfferings.get(i).matches("(?s)priority: (.*)")) this.priority= chargeOfferings.get(i).substring(10);
            else if(chargeOfferings.get(i).matches("(?s)partial: (.*)")) this.partial= Boolean.valueOf(chargeOfferings.get(i).substring(9));
            else if(chargeOfferings.get(i).matches("(?s)purchaseMax: (.*)")) this.purchaseMax= chargeOfferings.get(i).substring(13);
            else if(chargeOfferings.get(i).matches("(?s)taxSupplier: (.*)")) this.taxSupplier= chargeOfferings.get(i).substring(13);
            else if(chargeOfferings.get(i).matches("(?s)ownMax: (.*)")) this.ownMax= chargeOfferings.get(i).substring(8);
            else if(chargeOfferings.get(i).matches("(?s)applicableQuantity: (.*)") || chargeOfferings.get(i).matches("(?s)purchaseMin: (.*)") || chargeOfferings.get(i).matches("(?s)ownMin: (.*)")){}
            else if(chargeOfferings.get(i).matches("(?s)chargeEventMap")){ 
                
                ChargeEventMapT chargeEvent = new ChargeEventMapT(itemCount);
                itemCount++;
                i= chargeEvent.procesar(chargeOfferings, i+1);
                i--;
                this.chargeEvents.add(chargeEvent);
            }else return i;
        }
        return chargeOfferings.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.chargeEvents.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(ChargeEventMapT item: this.chargeEvents){
            item.buscar(buscar);
        }
        if((name+"/"+taxSupplier+"/"+description+"/"+internalId+"/"+priceListName+"/"+timeRange+"/"+customerSpecName+"/"+offerType+"/"+priority+"/"+partial+"/"+purchaseMax+"/"+ownMax).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.chargeEvents.add((ChargeEventMapT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.chargeEvents.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.chargeEvents.size();i++){
            this.chargeEvents.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.chargeEvents.get(i).merge(nodoI);
        }
    }
   
    
    
}
