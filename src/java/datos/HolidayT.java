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
public class HolidayT extends Nodo {
    private String name="";
    private String description="";
    private String internalId="";
    private String priceListName="Default";
    private boolean obsolete=false;
    private ArrayList<HolidayItemT> holidayItems;
    

    public HolidayT(int id) {
        this.id=id;
        this.holidayItems= new ArrayList<>();
    }
    
    @Override
    public void clean(){
        holidayItems.clear();
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

    public ArrayList<HolidayItemT> getHolidayItems() {
        return holidayItems;
    }

    public void setHolidayItems(ArrayList<HolidayItemT> holidayItems) {
        this.holidayItems = holidayItems;
    }
    
    
    
    @Override
    public String toString() {
        String holidays="";
        for(int i=0;i<this.holidayItems.size();i++){
            holidays+=this.holidayItems.get(i).toString()+"\n";
        }
        return "<holidayCalendar xmlns:mtd=\"http://xmlns.oracle.com/communications/platform/model/Metadata\" xmlns:cim=\"http://xmlns.oracle.com/communications/platform/model/Config\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" 
                +"    <name>" + name + "</name>\n    <description>" + description + "</description>\n    <internalId>" 
                + internalId + "</internalId>\n    <priceListName>" + priceListName + "</priceListName>\n    <obsolete>" 
                + obsolete + "</obsolete>\n"+holidays+"</holidayCalendar>";
    }
    

    @Override
    public int procesar(ArrayList<String> impactCategories, int index) {
        int itemCount = 0;
        for(int i=index; i<impactCategories.size();i++) {
            
            if(impactCategories.get(i).matches("(?s)name: (.*)")) this.name= impactCategories.get(i).substring(6);
            else if(impactCategories.get(i).matches("(?s)description: (.*)")) this.description= impactCategories.get(i).substring(13);
            else if(impactCategories.get(i).matches("(?s)internalId: (.*)")) this.internalId= impactCategories.get(i).substring(12);
            else if(impactCategories.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= impactCategories.get(i).substring(15);
            else if(impactCategories.get(i).matches("(?s)obsolete: (.*)")) this.obsolete= Boolean.valueOf(impactCategories.get(i).substring(10));
            else if(impactCategories.get(i).matches("(?s)holiday")){ 
                
                HolidayItemT holidayItem = new HolidayItemT(itemCount);
                itemCount++;
                i= holidayItem.procesar(impactCategories, i+1);
                i--;
                this.holidayItems.add(holidayItem);
            }else return i;
        }
        return impactCategories.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.holidayItems.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(HolidayItemT item: this.holidayItems){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+obsolete).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.holidayItems.add((HolidayItemT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.holidayItems.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.holidayItems.size();i++){
            this.holidayItems.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.holidayItems.get(i).merge(nodoI);
        }
    }
    
    
}
