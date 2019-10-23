/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.ControlFunctions;
import control.FirstPDF;
import datos.Nodo;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class ChargeRatePlanT extends Nodo{
    
    private String name="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="Default";
    private String taxCode="";
    private String applicableRums="";
    private String applicableQuantity="ORIGINAL";
    private String taxTime="";
    private String todMode="";
    private String applicableQtyTreatment="CONTINOUS";
    private String permittedName="";
    private String permittedType="";
    private String eventName="";
    private String cycleFeeFlag="0";
    private String billOffset="0";
    private String description="";
    private SubscriberCurrencyT subscriberCurrency= new SubscriberCurrencyT();
    private String dir="";
    
    public ChargeRatePlanT(){}
    public ChargeRatePlanT(int id){this.id=id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getApplicableRums() {
        return applicableRums;
    }

    public void setApplicableRums(String applicableRums) {
        this.applicableRums = applicableRums;
    }

    public String getApplicableQuantity() {
        return applicableQuantity;
    }

    public void setApplicableQuantity(String applicableQuantity) {
        this.applicableQuantity = applicableQuantity;
    }

    public String getTaxTime() {
        return taxTime;
    }

    public void setTaxTime(String taxTime) {
        this.taxTime = taxTime;
    }

    public String getTodMode() {
        return todMode;
    }

    public void setTodMode(String todMode) {
        this.todMode = todMode;
    }

    public String getApplicableQtyTreatment() {
        return applicableQtyTreatment;
    }

    public void setApplicableQtyTreatment(String applicableQtyTreatment) {
        this.applicableQtyTreatment = applicableQtyTreatment;
    }

    public String getPermittedName() {
        return permittedName;
    }

    public void setPermittedName(String permittedName) {
        this.permittedName = permittedName;
    }

    public String getPermittedType() {
        return permittedType;
    }

    public void setPermittedType(String permittedType) {
        this.permittedType = permittedType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCycleFeeFlag() {
        return cycleFeeFlag;
    }

    public void setCycleFeeFlag(String cycleFeeFlag) {
        this.cycleFeeFlag = cycleFeeFlag;
    }

    public String getBillOffset() {
        return billOffset;
    }

    public void setBillOffset(String billOffset) {
        this.billOffset = billOffset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubscriberCurrencyT getSubscriberCurrency() {
        return subscriberCurrency;
    }

    public void setSubscriberCurrency(SubscriberCurrencyT subscriberCurrency) {
        this.subscriberCurrency = subscriberCurrency;
    }
    
    @Override
    public String toString() {
        this.revisar();
        if(todMode.equals("TIMED")){
            this.subscriberCurrency.getApplicableRum().setIncrementQuantity("0.0");
            this.subscriberCurrency.getApplicableRum().setMinQuantity("0.0");}
        return 
        "<chargeRatePlan xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+
        "\t<name>"+name+"</name>\n"+
        "\t<internalId>"+internalId+"</internalId>\n"+
        "\t<pricingProfileName>"+pricingProfileName+"</pricingProfileName>\n"+
        "\t<priceListName>"+priceListName+"</priceListName>\n"+
        ((taxTime.equals("NONE"))?"":"\t<taxCode>"+taxCode+"</taxCode>\n")+
        "\t<applicableRums>"+applicableRums+"</applicableRums>\n"+
        "\t<applicableQuantity>"+applicableQuantity+"</applicableQuantity>\n"+
        "\t<taxTime>"+taxTime+"</taxTime>\n"+
        "\t<todMode>"+todMode+"</todMode>\n"+
        "\t<applicableQtyTreatment>"+applicableQtyTreatment+"</applicableQtyTreatment>\n"+
        "\t<permittedName>"+permittedName+"</permittedName>\n"+
        "\t<permittedType>"+permittedType+"</permittedType>\n"+
        "\t<eventName>"+eventName+"</eventName>\n"+
        "\t<cycleFeeFlag>"+cycleFeeFlag+"</cycleFeeFlag>\n"+
        "\t<billOffset>"+billOffset+"</billOffset>\n"+
        subscriberCurrency.toString("\t")+"\n"+
        ((description.equals(""))?"":"\t<description>"+description+"</description>\n")+        
        "</chargeRatePlan>";
    }

    @Override
    public int procesar(ArrayList<String> chargeRates2, int index) {
        ArrayList<String> chargeRates=  (ArrayList<String>)chargeRates2.clone();
        for(int i=index; i<chargeRates.size();i++) {
            
            if(chargeRates.get(i).matches("(?s)name: (.*)")) this.name= chargeRates.get(i).substring(6);
            else if(chargeRates.get(i).matches("(?s)internalId: (.*)")) this.internalId= chargeRates.get(i).substring(12);
            else if(chargeRates.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= chargeRates.get(i).substring(20);
            else if(chargeRates.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= chargeRates.get(i).substring(15);
            else if(chargeRates.get(i).matches("(?s)taxCode: (.*)")) this.taxCode= chargeRates.get(i).substring(9);
            else if(chargeRates.get(i).matches("(?s)applicableRums: (.*)")) this.applicableRums= chargeRates.get(i).substring(16);
            else if(chargeRates.get(i).matches("(?s)applicableQuantity: (.*)")) this.applicableQuantity= chargeRates.get(i).substring(20);
            else if(chargeRates.get(i).matches("(?s)taxTime: (.*)")) this.taxTime= chargeRates.get(i).substring(9);
            else if(chargeRates.get(i).matches("(?s)todMode: (.*)")) this.todMode= chargeRates.get(i).substring(9);
            else if(chargeRates.get(i).matches("(?s)applicableQtyTreatment: (.*)")) this.applicableQtyTreatment= chargeRates.get(i).substring(24);
            else if(chargeRates.get(i).matches("(?s)permittedName: (.*)")) this.permittedName= chargeRates.get(i).substring(15);
            else if(chargeRates.get(i).matches("(?s)permittedType: (.*)")) this.permittedType= chargeRates.get(i).substring(15);
            else if(chargeRates.get(i).matches("(?s)eventName: (.*)")) this.eventName= chargeRates.get(i).substring(11);
            else if(chargeRates.get(i).matches("(?s)cycleFeeFlag: (.*)")) this.cycleFeeFlag= chargeRates.get(i).substring(14);
            else if(chargeRates.get(i).matches("(?s)billOffset: (.*)")) this.billOffset= chargeRates.get(i).substring(12);
            else if(chargeRates.get(i).matches("(?s)description: (.*)")) this.description= chargeRates.get(i).substring(13);
            else if(chargeRates.get(i).matches("(?s)subscriberCurrency")){
                
                this.setSubscriberCurrency(new SubscriberCurrencyT(0));
                i= this.getSubscriberCurrency().procesar(chargeRates, i+1);
                i--;
            }else if(chargeRates.get(i).matches("(?s)crpDelRanges")){
                i++;
                while(i<chargeRates.size()){
                    try {
                        String valor = chargeRates.get(i).split(":")[1];
                        String tipo = chargeRates.get(i).split(":")[0].split("_")[0];
                        String ident = chargeRates.get(i).split(":")[0].split("_")[1];
                        if(ControlFunctions.isNumeric(ident)){
                        if(tipo.equals("startDate"))
                            this.subscriberCurrency.getCrpRelDateRanges().get(Integer.parseInt(ident)).setStartDate(ControlFunctions.getParseString(valor.substring(1)));
                        else if(tipo.equals("endDate"))
                            this.subscriberCurrency.getCrpRelDateRanges().get(Integer.parseInt(ident)).setEndDate(ControlFunctions.getParseString(valor.substring(1)));
                        }else{
                            CrpRelDateRangeT crp= new CrpRelDateRangeT(this.subscriberCurrency.getCrpRelDateRanges().size());
                            crp.setStartDate(ControlFunctions.getParseString(valor.substring(1)));
                            i++;
                            valor = chargeRates.get(i).split(":")[1];
                            crp.setEndDate(ControlFunctions.getParseString(valor.substring(1)));
                            if(this.subscriberCurrency.getCrpRelDateRanges().size()>0){
                            CrpRelDateRangeT last = this.subscriberCurrency.getCrpRelDateRanges().get(this.subscriberCurrency.getCrpRelDateRanges().size()-1);
                            if(last.getZoneModel()==null)
                                crp.setCrpCompositePopModel(last.getCrpCompositePopModel());
                            else
                                crp.setZoneModel(last.getZoneModel());}
                            else{
                                crp.getDefaultComposite(this.getApplicableRums(),this.subscriberCurrency.getCurrencyName());
                            }
                            this.subscriberCurrency.getCrpRelDateRanges().add(crp);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ChargeRatePlanT.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    i++;
                }
            }
            else return i;
        }
        this.revisar();
        return chargeRates.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            if(indexs.get(0)==0 && indexs.get(1)<0){
                if(indexs.get(1)==-1)
                    index= this.procesar(lista, index);
                else if(indexs.get(1)==-2){
                    try{
                        index= this.getSubscriberCurrency().getCrpRelDateRanges().get(indexs.get(2)).getZoneModel().procesar(lista, index);}
                    catch(NullPointerException e){
                        this.getSubscriberCurrency().getCrpRelDateRanges().get(indexs.get(2)).setZoneModel(new ZoneModelT(0));
                        this.getSubscriberCurrency().getCrpRelDateRanges().get(indexs.get(2)).setCrpCompositePopModel(null);
                        index= this.getSubscriberCurrency().getCrpRelDateRanges().get(indexs.get(2)).getZoneModel().procesar(lista, index);
                    }
                }
                else if(indexs.get(1)==-3)
                    index= (this.buscaPop(dir)).procesar(lista, index);
                else if(indexs.get(1)==-4)
                    index= (this.buscaPop(dir)).procesar(lista, index);
                else if(indexs.get(1)==-5)
                    index= (this.buscaPop(dir)).getPriceTierRanges().get(indexs.get(2)).getCharges().get(indexs.get(3)).procesar(lista, index);
            }
            else
            this.subscriberCurrency.procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((name+"/"+internalId+"/"+pricingProfileName+"/"+priceListName+"/"+taxCode+"/"+applicableRums+"/"+applicableQuantity+"/"+taxTime+"/"+todMode+"/"+applicableQtyTreatment+"/"+permittedName+"/"+permittedType+"/"+eventName+"/"+cycleFeeFlag+"/"+billOffset+"/"+description).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || subscriberCurrency.buscar(buscar)){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    public CrpCompositePopModelT buscaPop(String dir) {
        this.dir=dir;
        ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = dir.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            int key=index.get(0);
            index.remove(0);
            if(index.size()==0) return this.subscriberCurrency.getCrpRelDateRanges().get(key).getCrpCompositePopModel();
            else return this.subscriberCurrency.getCrpRelDateRanges().get(key).getZoneModel().buscaPop(index);
    }
    
    public void revisar(){
        this.permittedType= ((this.permittedName.equals("Account"))?"CUSTOMER":"PRODUCT");
        if(this.applicableRums.equals("Occurrence")){ this.subscriberCurrency.setApplicableRum(null);
                this.pricingProfileName="Subscription";
                }
        else{
            this.pricingProfileName="Convergent Usage";
            if(this.subscriberCurrency.getApplicableRum()!=null)this.subscriberCurrency.getApplicableRum().setApplicableRumName(this.getApplicableRums());
            else{
                this.subscriberCurrency.setApplicableRum(new ApplicableRumT(this.applicableRums,((todMode.equals("Timed"))?"0.0":"1.0")));
            }
        }
        for(CrpRelDateRangeT rel: this.getSubscriberCurrency().getCrpRelDateRanges()){
            if(rel.getCrpCompositePopModel()!=null){ 
                rel.getCrpCompositePopModel().getRumCurrency(this.getApplicableRums(), this.getSubscriberCurrency().getCurrencyCode());
            }else{
                for(ResultsT res: rel.getZoneModel().getResults()){
                    res.getResult().getRumCurrency(this.getApplicableRums(), this.getSubscriberCurrency().getCurrencyCode());
                }
            }
        }
    }
    
        @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Plan de Tarifa de Cargos: "+this.name, FirstPDF.titleFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Nombre: "+name,FirstPDF.normalFont));
            preface.add(new Paragraph("Descripción: "+description,FirstPDF.normalFont));
            preface.add(new Paragraph("ID: "+internalId,FirstPDF.normalFont));
            preface.add(new Paragraph("Nombre de lista de precio: "+priceListName,FirstPDF.normalFont));
            preface.add(new Paragraph("Código de impuesto: "+taxCode,FirstPDF.normalFont));
            preface.add(new Paragraph("Tiempo de impuesto: "+taxTime,FirstPDF.normalFont));
            preface.add(new Paragraph("RUMs aplicables: "+applicableRums,FirstPDF.normalFont));
            preface.add(new Paragraph("Nombre permitido: "+permittedName,FirstPDF.normalFont));
            preface.add(new Paragraph("Tipo permitido: "+permittedType,FirstPDF.normalFont));
            preface.add(new Paragraph("Evento: "+eventName,FirstPDF.normalFont));
            this.subscriberCurrency.getPDF(preface);
            document.add(preface);
            
        } catch (DocumentException ex) {
            
        }
    }
    
    
    
}
