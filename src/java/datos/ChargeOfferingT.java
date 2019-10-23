/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.FirstPDF;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    @Override
    public void clean(){
        chargeEvents.clear();
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
            chargeEvents+=this.chargeEvents.get(i).toString("\t")+"\n";
        }
        return "<chargeOffering externalID=\""+name+"\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" +
            "\t<name>"+name+"</name>\n" +
            "\t<description>"+description+"</description>\n" +
            ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
            "\t<pricingProfileName>"+pricingProfileName+"</pricingProfileName>\n" +
            "\t<priceListName>"+priceListName+"</priceListName>\n" +
            "\t<timeRange>"+timeRange+"</timeRange>\n" +
            ((productSpecName.equals(""))?"\t<customerSpecName>"+customerSpecName+"</customerSpecName>\n":"\t<productSpecName>"+productSpecName+"</productSpecName>\n") +
            "\t<offerType>"+offerType+"</offerType>\n" +
            "\t<priority>"+priority+"</priority>\n" +
            "\t<partial>"+partial+"</partial>\n" +
            "\t<purchaseMin>-1.0</purchaseMin>\n" +
            "\t<purchaseMax>"+purchaseMax+"</purchaseMax>\n" +
            "\t<ownMin>-1.0</ownMin>\n" +
            "\t<ownMax>"+ownMax+"</ownMax>\n" +
            "\t<taxSupplier>"+taxSupplier+"</taxSupplier>\n" +
            "\t<applicableQuantity>REMAINING</applicableQuantity>\n"+
            chargeEvents+
            "</chargeOffering>";
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
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Zone Model: "+this.name, FirstPDF.titleFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("nombre: "+name,FirstPDF.normalFont));
            preface.add(new Paragraph("Descripción: "+description,FirstPDF.normalFont));
            preface.add(new Paragraph("ID: "+internalId,FirstPDF.normalFont));
            preface.add(new Paragraph("Nombre de lista de precio: "+priceListName,FirstPDF.normalFont));
            preface.add(new Paragraph("Rango: "+timeRange,FirstPDF.normalFont));
            if(!productSpecName.equals(""))
            preface.add(new Paragraph("Producto: "+productSpecName,FirstPDF.normalFont));
            else
            preface.add(new Paragraph("Cliente: "+customerSpecName,FirstPDF.normalFont));
            preface.add(new Paragraph("Tipo de oferta: "+offerType,FirstPDF.normalFont));
            preface.add(new Paragraph("Prioridad: "+priority,FirstPDF.normalFont));
            preface.add(new Paragraph("Parcial: "+partial,FirstPDF.normalFont));
            preface.add(new Paragraph("Máximo de compra: "+purchaseMax,FirstPDF.normalFont));
            preface.add(new Paragraph("Mínimo de compra: -1.0",FirstPDF.normalFont));
            preface.add(new Paragraph("Proveedor de impuestos: "+taxSupplier,FirstPDF.normalFont));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Eventos",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(2);
            float[] columnWidths = new float[]{10f, 10f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Evento"));
            table.addCell(FirstPDF.createTableHeader("Plan de Tarifa"));
            for(ChargeEventMapT item : this.chargeEvents){
                if(item.visibilidad){
                    item.getPDF(table);
                }
            }
            preface.add(table);
            document.add(preface);
            
        } catch (DocumentException ex) {
            Logger.getLogger(ZoneModelT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    
}
