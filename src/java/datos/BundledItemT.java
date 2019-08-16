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
public class BundledItemT extends Nodo{
    
    public ListaT purchaseStart= new ListaT();
    public ListaT purchaseEnd= new ListaT();
    public ListaT usageStart= new ListaT();
    public ListaT usageEnd= new ListaT();
    public ListaT cycleStart= new ListaT();
    public ListaT cycleEnd= new ListaT();
    public String status="";
    public String statusCode="";
    public String quantity="";
    public String purchaseChargeAdjustment="";
    public String usageChargeAdjustment="";
    public String cycleChargeAdjustment="";
    public String chargeOfferingName="";
    public String alterationOfferingName="";
    
    public BundledItemT(int id) {
        this.id=id;
    }

    public ListaT getPurchaseStart() {
        return purchaseStart;
    }

    public void setPurchaseStart(ListaT purchaseStart) {
        this.purchaseStart = purchaseStart;
    }

    public ListaT getPurchaseEnd() {
        return purchaseEnd;
    }

    public void setPurchaseEnd(ListaT purchaseEnd) {
        this.purchaseEnd = purchaseEnd;
    }

    public ListaT getUsageStart() {
        return usageStart;
    }

    public void setUsageStart(ListaT usageStart) {
        this.usageStart = usageStart;
    }

    public ListaT getUsageEnd() {
        return usageEnd;
    }

    public void setUsageEnd(ListaT usageEnd) {
        this.usageEnd = usageEnd;
    }

    public ListaT getCycleStart() {
        return cycleStart;
    }

    public void setCycleStart(ListaT cycleStart) {
        this.cycleStart = cycleStart;
    }

    public ListaT getCycleEnd() {
        return cycleEnd;
    }

    public void setCycleEnd(ListaT cycleEnd) {
        this.cycleEnd = cycleEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchaseChargeAdjustment() {
        return purchaseChargeAdjustment;
    }

    public void setPurchaseChargeAdjustment(String purchaseChargeAdjustment) {
        this.purchaseChargeAdjustment = purchaseChargeAdjustment;
    }

    public String getUsageChargeAdjustment() {
        return usageChargeAdjustment;
    }

    public void setUsageChargeAdjustment(String usageChargeAdjustment) {
        this.usageChargeAdjustment = usageChargeAdjustment;
    }

    public String getCycleChargeAdjustment() {
        return cycleChargeAdjustment;
    }

    public void setCycleChargeAdjustment(String cycleChargeAdjustment) {
        this.cycleChargeAdjustment = cycleChargeAdjustment;
    }

    public String getChargeOfferingName() {
        return chargeOfferingName;
    }

    public void setChargeOfferingName(String chargeOfferingName) {
        this.chargeOfferingName = chargeOfferingName;
    }

    public String getAlterationOfferingName() {
        return alterationOfferingName;
    }

    public void setAlterationOfferingName(String alterationOfferingName) {
        this.alterationOfferingName = alterationOfferingName;
    }
    
    public int procesar(ArrayList<String> zoneModels, int index) {
        
        
        for(int i=index; i<zoneModels.size();i++) {
            
            if(zoneModels.get(i).matches("(?s)status: (.*)")) this.status= zoneModels.get(i).substring(8);
            else if(zoneModels.get(i).matches("(?s)statusCode: (.*)")) this.statusCode= zoneModels.get(i).substring(12);
            else if(zoneModels.get(i).matches("(?s)quantity: (.*)")) this.quantity= zoneModels.get(i).substring(10);
            else if(zoneModels.get(i).matches("(?s)purchaseChargeAdjustment: (.*)")) this.purchaseChargeAdjustment= zoneModels.get(i).substring(26);
            else if(zoneModels.get(i).matches("(?s)usageChargeAdjustment: (.*)")) this.usageChargeAdjustment= zoneModels.get(i).substring(23);
            else if(zoneModels.get(i).matches("(?s)cycleChargeAdjustment: (.*)")) this.cycleChargeAdjustment= zoneModels.get(i).substring(23);
            else if(zoneModels.get(i).matches("(?s)chargeOfferingName: (.*)")) this.chargeOfferingName= zoneModels.get(i).substring(20);
            else if(zoneModels.get(i).matches("(?s)alterationOfferingName: (.*)")) this.alterationOfferingName= zoneModels.get(i).substring(24);
            else if(zoneModels.get(i).matches("(?s)purchaseStart")){
                i++;
                if(zoneModels.get(i).matches("(?s)offset: (.*)")){
                    this.purchaseStart.id= Integer.parseInt(zoneModels.get(i).substring(8));
                    i++;
                    if(zoneModels.get(i).matches("(?s)mode: (.*)"))
                    this.purchaseStart.valor= zoneModels.get(i).substring(8);
                    else return i-=2;
                        }else{ return i--; }
            }
            else if(zoneModels.get(i).matches("(?s)purchaseEnd")){
                i++;
                if(zoneModels.get(i).matches("(?s)offset: (.*)")){
                    this.purchaseEnd.id= Integer.parseInt(zoneModels.get(i).substring(8));
                    i++;
                    if(zoneModels.get(i).matches("(?s)mode: (.*)"))
                    this.purchaseEnd.valor= zoneModels.get(i).substring(8);
                    else return i-=2;
                        }else{ return i--; }
            }
            else if(zoneModels.get(i).matches("(?s)usageStart")){
                i++;
                if(zoneModels.get(i).matches("(?s)offset: (.*)")){
                    this.usageStart.id= Integer.parseInt(zoneModels.get(i).substring(8));
                    i++;
                    if(zoneModels.get(i).matches("(?s)mode: (.*)"))
                    this.usageStart.valor= zoneModels.get(i).substring(8);
                    else return i-=2;
                        }else{ return i--; }
            }
            else if(zoneModels.get(i).matches("(?s)usageEnd")){
                i++;
                if(zoneModels.get(i).matches("(?s)offset: (.*)")){
                    this.usageEnd.id= Integer.parseInt(zoneModels.get(i).substring(8));
                    i++;
                    if(zoneModels.get(i).matches("(?s)mode: (.*)"))
                    this.usageEnd.valor= zoneModels.get(i).substring(8);
                    else return i-=2;
                        }else{ return i--; }
            }
            else if(zoneModels.get(i).matches("(?s)cycleStart")){
                i++;
                if(zoneModels.get(i).matches("(?s)offset: (.*)")){
                    this.cycleStart.id= Integer.parseInt(zoneModels.get(i).substring(8));
                    i++;
                    if(zoneModels.get(i).matches("(?s)mode: (.*)"))
                    this.cycleStart.valor= zoneModels.get(i).substring(8);
                    else return i-=2;
                        }else{ return i--; }
            }
            else if(zoneModels.get(i).matches("(?s)cycleEnd")){
                i++;
                if(zoneModels.get(i).matches("(?s)offset: (.*)")){
                    this.cycleEnd.id= Integer.parseInt(zoneModels.get(i).substring(8));
                    i++;
                    if(zoneModels.get(i).matches("(?s)mode: (.*)"))
                    this.cycleEnd.valor= zoneModels.get(i).substring(8);
                    else return i-=2;
                        }else{ return i--; }
            }
            else return i;
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
        if((purchaseStart.valor+"/"+purchaseEnd.valor+"/"+usageStart.valor+"/"+
                usageEnd.valor+"/"+cycleStart.valor+"/"+cycleEnd.valor+"/"+
                status+"/"+statusCode+"/"+quantity+"/"+purchaseChargeAdjustment+"/"+
                usageChargeAdjustment+"/"+cycleChargeAdjustment+"/"+chargeOfferingName+"/"+
                alterationOfferingName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    
}
