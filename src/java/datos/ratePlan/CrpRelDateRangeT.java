/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class CrpRelDateRangeT extends Nodo{
    private String startDate="";
    private String endDate="";
    private ZoneModelT zoneModel;
    private CrpCompositePopModelT crpCompositePopModel;
    
    public CrpRelDateRangeT(){}
    public CrpRelDateRangeT(int id){this.id=id;}

    public ZoneModelT getZoneModel() {
        return zoneModel;
    }

    public void setZoneModel(ZoneModelT zoneModel) {
        this.zoneModel = zoneModel;
    }

    public CrpCompositePopModelT getCrpCompositePopModel() {
        return crpCompositePopModel;
    }

    public void setCrpCompositePopModel(CrpCompositePopModelT crpCompositePopModel) {
        this.crpCompositePopModel = crpCompositePopModel;
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
    
    @Override
    public String toString(String s) {
        return s+"<crpRelDateRange>\n" +
            s+"\t<absoluteDateRange>\n" +
            s+"\t\t<startDate>"+startDate+"</startDate>\n" +
            s+"\t\t<endDate>"+endDate+"</endDate>\n" +
            s+"\t</absoluteDateRange>\n" +
                ((zoneModel!=null)?zoneModel.toString(s+"\t"):crpCompositePopModel.toString(s+"\t"))+"\n"+
            s+"</crpRelDateRange>";
    }

    @Override
    public int procesar(ArrayList<String> zoneModels, int index) {
        for(int i=index; i<zoneModels.size();i++) {
            
            if(zoneModels.get(i).matches("(?s)absoluteDateRange(.*)")){
                i++;
                if(zoneModels.get(i).matches("(?s)startDate: (.*)")) this.startDate= zoneModels.get(i).substring(11);
                i++;
                if(zoneModels.get(i).matches("(?s)endDate: (.*)")) this.endDate= zoneModels.get(i).substring(9);
            } 
            else if(("zoneModel enhancedZoneModel").contains(zoneModels.get(i))){ 
                ZoneModelT zoneItem = new ZoneModelT(zoneModels.get(i));
                i= zoneItem.procesar(zoneModels, i+1);
                i--;
                this.setZoneModel(zoneItem);
            }else if(zoneModels.get(i).matches("(?s)crpCompositePopModel")){ 
                CrpCompositePopModelT crpCompositePopModel = new CrpCompositePopModelT();
                i= crpCompositePopModel.procesar(zoneModels, i+1);
                i--;
                this.setCrpCompositePopModel(crpCompositePopModel);
            }else return i;
        }
        return zoneModels.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if(zoneModel!=null) zoneModel.buscar(buscar);
        if(crpCompositePopModel!=null) crpCompositePopModel.buscar(buscar);
        if((startDate+"/"+endDate).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    public void getDefaultComposite(String rum, String currencyCode) {
        ChargeT charge= new ChargeT(0);
        PriceTierRangeT tierRange = new PriceTierRangeT(0);
        CrpCompositePopModelT popModel = new CrpCompositePopModelT(0);
        tierRange.getCharges().add(charge);
        popModel.getPriceTierRanges().add(tierRange);
        popModel.getRumCurrency(rum, currencyCode);
        this.crpCompositePopModel=popModel;
    }
    
    
    
    
    
    
}
