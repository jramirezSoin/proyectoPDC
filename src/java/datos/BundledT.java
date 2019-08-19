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
public class BundledT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="";
    private String timeRange="";
    private String productSpecName="";
    private String customerSpecName="";
    private boolean billOnPurchase=false;
    private String customize="";
    private boolean groupBalanceElements=false;
    private ArrayList<BundledItemT> bundledItems;
    
    public BundledT(int id) {
        bundledItems= new ArrayList<>();
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

    public boolean getBillOnPurchase() {
        return billOnPurchase;
    }

    public void setBillOnPurchase(boolean billOnPurchase) {
        this.billOnPurchase = billOnPurchase;
    }

    public String getCustomize() {
        return customize;
    }

    public void setCustomize(String customize) {
        this.customize = customize;
    }

    public boolean getGroupBalanceElements() {
        return groupBalanceElements;
    }

    public void setGroupBalanceElements(boolean groupBalanceElements) {
        this.groupBalanceElements = groupBalanceElements;
    }

    public ArrayList<BundledItemT> getBundledItems() {
        return bundledItems;
    }

    public void setBundledItems(ArrayList<BundledItemT> bundledItems) {
        this.bundledItems = bundledItems;
    }

    @Override
    public String toString() {
        String bundleItems="";
        for(int i=0;i<this.bundledItems.size();i++){
            bundleItems+=this.bundledItems.get(i).toString()+"\n";
        }
        return "<bundledProductOffering xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"
                +"    <name>" + name + "</name>\n    <description>" + description 
                + "</description>\n    <internalId>" + internalId + "</internalId>\n    <pricingProfileName>" 
                + pricingProfileName + "</pricingProfileName>\n    <priceListName>" + priceListName + "</priceListName>\n    <timeRange>" 
                + timeRange + "</timeRange>\n    "
                +((customerSpecName.equals(""))?"<productSpecName>" + productSpecName + "</productSpecName>":"<customerSpecName>" + customerSpecName + "</customerSpecName>")
                +"\n    <billOnPurchase>" + billOnPurchase + "</billOnPurchase>\n    <customize>" + customize + "</customize>\n    <groupBalanceElements>" 
                + groupBalanceElements + "</groupBalanceElements>\n"+ bundleItems +"</bundledProductOffering>";
    }
    
    
    
    @Override
    public int procesar(ArrayList<String> zoneModels, int index) {
        int itemCount = 0;
        for(int i=index; i<zoneModels.size();i++) {
            
            if(zoneModels.get(i).matches("(?s)name: (.*)")) this.name= zoneModels.get(i).substring(6);
            else if(zoneModels.get(i).matches("(?s)description: (.*)")) this.description= zoneModels.get(i).substring(13);
            else if(zoneModels.get(i).matches("(?s)internalId: (.*)")) this.internalId= zoneModels.get(i).substring(12);
            else if(zoneModels.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= zoneModels.get(i).substring(15);
            else if(zoneModels.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= zoneModels.get(i).substring(20);
            else if(zoneModels.get(i).matches("(?s)timeRange: (.*)")) this.timeRange= zoneModels.get(i).substring(11);
            else if(zoneModels.get(i).matches("(?s)productSpecName: (.*)")) this.productSpecName= zoneModels.get(i).substring(17);
            else if(zoneModels.get(i).matches("(?s)customerSpecName: (.*)")) this.customerSpecName= zoneModels.get(i).substring(18);
            else if(zoneModels.get(i).matches("(?s)billOnPurchase: (.*)")) this.billOnPurchase= Boolean.valueOf(zoneModels.get(i).substring(16));
            else if(zoneModels.get(i).matches("(?s)customize: (.*)")) this.customize= zoneModels.get(i).substring(11);
            else if(zoneModels.get(i).matches("(?s)groupBalanceElements: (.*)")) this.groupBalanceElements= Boolean.valueOf(zoneModels.get(i).substring(22));
            else if(zoneModels.get(i).matches("(?s)aplicable: (.*)")){
                if(zoneModels.get(i).substring(11).equals("Account")){this.customerSpecName="Account"; this.productSpecName="";}
                else{this.productSpecName=zoneModels.get(i).substring(11); this.customerSpecName="";}
            }
            else if(zoneModels.get(i).matches("(?s)bundledProductOfferingItem")){ 
                
                BundledItemT zoneItem = new BundledItemT(itemCount);
                itemCount++;
                i= zoneItem.procesar(zoneModels, i+1);
                i--;
                this.bundledItems.add(zoneItem);
            }else return i;
        }
        return zoneModels.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.bundledItems.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        boolean estado=false;
        boolean aux=false;
        for(BundledItemT item: this.bundledItems){
            aux=item.buscar(buscar);
            if(!estado)estado=aux;
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+timeRange+"/"+productSpecName+"/"+customerSpecName+"/"+billOnPurchase+"/"+customize+"/"+groupBalanceElements).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.bundledItems.add((BundledItemT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.bundledItems.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.bundledItems.size();i++){
            this.bundledItems.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.bundledItems.get(i).merge(nodoI);
        }
    }
    
    
}
