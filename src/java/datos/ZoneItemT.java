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
public class ZoneItemT extends Nodo{
    private String productName="";
    private String originPrefix="";
    private String destinationPrefix="";
    private String validFrom="";
    private String validTo="";
    private ZoneResult zoneResult= new ZoneResult();

    public ZoneItemT(int id, String productName, String originPrefix, String destinationPrefix, String validFrom, String validTo, String zoneName) {
        this.id = id;
        this.productName = productName;
        this.originPrefix = originPrefix;
        this.destinationPrefix = destinationPrefix;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.zoneResult = new ZoneResult(zoneName);
    }

    public ZoneItemT(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOriginPrefix() {
        return originPrefix;
    }

    public void setOriginPrefix(String originPrefix) {
        this.originPrefix = originPrefix;
    }

    public String getDestinationPrefix() {
        return destinationPrefix;
    }

    public void setDestinationPrefix(String destinationPrefix) {
        this.destinationPrefix = destinationPrefix;
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

    public ZoneResult getZoneResult() {
        return zoneResult;
    }

    public void setZoneResult(ZoneResult zoneResult) {
        this.zoneResult = zoneResult;
    }

    @Override
    public String toString(String s) {
        return s+"<zoneItem>\n"+ 
               s+"\t<productName>" + productName +"</productName>\n" +
               s+"\t<originPrefix>" + originPrefix +"</originPrefix>\n" +
               s+"\t<destinationPrefix>" + destinationPrefix +"</destinationPrefix>\n" +
               s+"\t<validFrom>" + validFrom +"</validFrom>\n"+
               s+"\t<validTo>" + validTo +"</validTo>\n"+
               s+zoneResult.toString(s+"\t")+"\n"+
               s+"</zoneItem>";
    }
    
    public int procesar(ArrayList<String> zoneModels, int index) {
        
        
        for(int i=index; i<zoneModels.size();i++) {
            
            if(zoneModels.get(i).matches("(?s)destinationPrefix: (.*)")) this.destinationPrefix= zoneModels.get(i).substring(19);
            else if(zoneModels.get(i).matches("(?s)originPrefix: (.*)")) this.originPrefix= zoneModels.get(i).substring(14);
            else if(zoneModels.get(i).matches("(?s)productName: (.*)")) this.productName= zoneModels.get(i).substring(13);
            else if(zoneModels.get(i).matches("(?s)validFrom: (.*)")) this.validFrom= zoneModels.get(i).substring(11);
            else if(zoneModels.get(i).matches("(?s)validTo: (.*)")) this.validTo= zoneModels.get(i).substring(9);
            else if(zoneModels.get(i).matches("(?s)zoneResult")){ 
                ZoneResult zoneResult = new ZoneResult();
                i= zoneResult.procesar(zoneModels, i+1);
                i--;
                this.zoneResult = zoneResult;
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
        if((destinationPrefix+"/"+originPrefix+"/"+productName+"/"+validFrom+"/"+validTo).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || zoneResult.buscar(buscar)){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    @Override
    public void merge(Nodo nodo) {
        ZoneItemT zoneItemT= (ZoneItemT) nodo;
        
    }
    
    
    public class ZoneResult extends Nodo{
        private String ZoneName="";

        public ZoneResult(String ZoneName) {
            this.ZoneName = ZoneName;
        }

        private ZoneResult() {
            
        }

        public String getZoneName() {
            return ZoneName;
        }

        public void setZoneName(String ZoneName) {
            this.ZoneName = ZoneName;
        }

        @Override
        public String toString(String s) {
            return s+"<zoneResult>\n" +
                    s+"\t<zoneName>" + ZoneName + "</zoneName>\n"+
                    s+"</zoneResult>";
        }
        
        public int procesar(ArrayList<String> zoneModels, int index) {
            
            for(int i=index; i<zoneModels.size();i++) {
                
                if(zoneModels.get(i).matches("(?s)zoneName: (.*)")) this.ZoneName= zoneModels.get(i).substring(10);
                else return i;
            }
            return zoneModels.size();
        }
        
        
        @Override
        public boolean buscar(String buscar){
             if(ZoneName.replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
                   this.visibilidad=true;
                   return true;
             }else{
                  this.visibilidad=false;
                  return false;
             }
        }
        
        
        
    }
    
}
