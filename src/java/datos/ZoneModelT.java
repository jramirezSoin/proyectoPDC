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
public class ZoneModelT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String priceListName="";
    private boolean obsolete=false;
    private ArrayList<ZoneItemT> zoneItems;
    
    public ZoneModelT(int id){
        this.id= id;
        this.zoneItems = new ArrayList<>();
    }

    public ZoneModelT(int id, String name, String description, String internalId, String priceListName, boolean obsolete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.internalId = internalId;
        this.priceListName = priceListName;
        this.obsolete = obsolete;
        this.zoneItems = new ArrayList<>();
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

    public ArrayList<ZoneItemT> getZoneItems() {
        return zoneItems;
    }

    public void setZoneItems(ArrayList<ZoneItemT> zoneItems) {
        this.zoneItems = zoneItems;
    }

    @Override
    public String toString() {
        String zoneItems="";
        for(int i=0;i<this.zoneItems.size();i++){
            zoneItems+=this.zoneItems.get(i).toString()+"\n";
        }
        return "<standardZoneModel xmlns:mtd=\"http://xmlns.oracle.com/communications/platform/model/Metadata\" xmlns:cim=\"http://xmlns.oracle.com/communications/platform/model/Config\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" 
                + "    <name>" + name + "</name>\n    <description>" + description + "</description>\n    <internalId>"
                + internalId + "</internalId>\n    <priceListName>" + priceListName + "</priceListName>\n    <obsolete>" 
                + obsolete + "</obsolete>\n" + zoneItems + "</standardZoneModel>";
    }

    @Override
    public int procesar(ArrayList<String> zoneModels, int index) {
        int itemCount = 0;
        for(int i=index; i<zoneModels.size();i++) {
            
            if(zoneModels.get(i).matches("name: (.*)")) this.name= zoneModels.get(i).substring(6);
            else if(zoneModels.get(i).matches("description: (.*)")) this.description= zoneModels.get(i).substring(13);
            else if(zoneModels.get(i).matches("internalId: (.*)")) this.internalId= zoneModels.get(i).substring(12);
            else if(zoneModels.get(i).matches("priceListName: (.*)")) this.priceListName= zoneModels.get(i).substring(15);
            else if(zoneModels.get(i).matches("obsolete: (.*)")) this.obsolete= Boolean.valueOf(zoneModels.get(i).substring(10));
            else if(zoneModels.get(i).matches("zoneItem")){ 
                
                ZoneItemT zoneItem = new ZoneItemT(itemCount);
                itemCount++;
                i= zoneItem.procesar(zoneModels, i+1);
                i--;
                this.zoneItems.add(zoneItem);
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
            this.zoneItems.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(ZoneItemT item: this.zoneItems){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+obsolete).toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.zoneItems.add((ZoneItemT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.zoneItems.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.zoneItems.size();i++){
            this.zoneItems.get(i).id--;
        }
    }
    
    
    
    
}
