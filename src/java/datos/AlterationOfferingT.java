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
public class AlterationOfferingT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="Product Offering";
    private String priceListName="Default";
    private String timeRange="";
    private String productSpecName="";
    private String customerSpecName="";
    private String offerType="";
    private String priority="";
    private boolean partial=false;
    private String purchaseMax="";
    private String ownMax="";
    private ArrayList<AlterationEventMapT> alterationEvents= new ArrayList<>();
    
    public AlterationOfferingT(){
        
    }
    
    @Override
    public void clean(){
        alterationEvents.clear();
    }
    
    public AlterationOfferingT(int id){
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

    public ArrayList<AlterationEventMapT> getAlterationEvents() {
        return alterationEvents;
    }

    public void setAlterationEvents(ArrayList<AlterationEventMapT> alterationEvents) {
        this.alterationEvents = alterationEvents;
    }
    
    
    
    @Override
    public String toString() {
        String alterationEvents="";
        for(int i=0;i<this.alterationEvents.size();i++){
            alterationEvents+=this.alterationEvents.get(i).toString("\t")+"\n";
        }
        return "<alterationOffering externalID=\""+name+"\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" +
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
            "\t<applicableChargeAndQuantity>REMAINING</applicableChargeAndQuantity>\n"+
            alterationEvents+
            "</alterationOffering>";
    }

    @Override
    public int procesar(ArrayList<String> alterationOfferings2, int index, String user) {
        ArrayList<String> alterationOfferings= (ArrayList<String>)alterationOfferings2.clone();
        int itemCount = 0;
        for(int i=index; i<alterationOfferings.size();i++) {
            
            if(alterationOfferings.get(i).matches("(?s)iceUpdater: (.*)")) this.iceUpdaterCount = alterationOfferings.get(i).substring(12).equals("new")?1:Integer.parseInt(alterationOfferings.get(i).substring(12));
            else if(alterationOfferings.get(i).matches("(?s)name: (.*)")) this.name= alterationOfferings.get(i).substring(6);
            else if(alterationOfferings.get(i).matches("(?s)description: (.*)")) this.description= alterationOfferings.get(i).substring(13);
            else if(alterationOfferings.get(i).matches("(?s)internalId: (.*)")) this.internalId= alterationOfferings.get(i).substring(12);
            else if(alterationOfferings.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= alterationOfferings.get(i).substring(15);
            else if(alterationOfferings.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= alterationOfferings.get(i).substring(20);
            else if(alterationOfferings.get(i).matches("(?s)timeRange: (.*)")) this.timeRange= alterationOfferings.get(i).substring(11);
            else if(alterationOfferings.get(i).matches("(?s)customerSpecName: (.*)")) this.customerSpecName= alterationOfferings.get(i).substring(18);
            else if(alterationOfferings.get(i).matches("(?s)productSpecName: (.*)")) this.productSpecName= alterationOfferings.get(i).substring(17);
            else if(alterationOfferings.get(i).matches("(?s)specName: (.*)")){ 
                        if(alterationOfferings.get(i).substring(10).equals("Account"))this.customerSpecName= alterationOfferings.get(i).substring(10);
                        else this.productSpecName= alterationOfferings.get(i).substring(10);}
            else if(alterationOfferings.get(i).matches("(?s)offerType: (.*)")) this.offerType= alterationOfferings.get(i).substring(11);
            else if(alterationOfferings.get(i).matches("(?s)priority: (.*)")) this.priority= alterationOfferings.get(i).substring(10);
            else if(alterationOfferings.get(i).matches("(?s)partial: (.*)")) this.partial= Boolean.valueOf(alterationOfferings.get(i).substring(9));
            else if(alterationOfferings.get(i).matches("(?s)purchaseMax: (.*)")) this.purchaseMax= alterationOfferings.get(i).substring(13);
            else if(alterationOfferings.get(i).matches("(?s)ownMax: (.*)")) this.ownMax= alterationOfferings.get(i).substring(8);
            else if(alterationOfferings.get(i).matches("(?s)applicableChargeAndQuantity: (.*)") || alterationOfferings.get(i).matches("(?s)purchaseMin: (.*)") || alterationOfferings.get(i).matches("(?s)ownMin: (.*)")){}
            else if(alterationOfferings.get(i).matches("(?s)alterationEventMap")){ 
                
                AlterationEventMapT alterationEvent = new AlterationEventMapT(itemCount);
                itemCount++;
                i= alterationEvent.procesar(alterationOfferings, i+1, user);
                i--;
                this.alterationEvents.add(alterationEvent);
            }else return i;
        }
        return alterationOfferings.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.alterationEvents.get(i).procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(AlterationEventMapT item: this.alterationEvents){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+timeRange+"/"+customerSpecName+"/"+offerType+"/"+priority+"/"+partial+"/"+purchaseMax+"/"+ownMax).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.alterationEvents.add((AlterationEventMapT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.alterationEvents.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.alterationEvents.size();i++){
            this.alterationEvents.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.alterationEvents.get(i).merge(nodoI);
        }
    }
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Oferta de descuento: "+this.name, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("Nombre: ",name));
            preface.add(FirstPDF.createDescription("Descripción: ",description));
            preface.add(FirstPDF.createDescription("ID: ",internalId));
            preface.add(FirstPDF.createDescription("Nombre de lista de precio: ",priceListName));
            preface.add(FirstPDF.createDescription("Rango: ",timeRange));
            if(!productSpecName.equals(""))
            preface.add(FirstPDF.createDescription("Producto: ",productSpecName));
            else
            preface.add(FirstPDF.createDescription("Cliente: ",customerSpecName));
            preface.add(FirstPDF.createDescription("Tipo de oferta: ",offerType));
            preface.add(FirstPDF.createDescription("Prioridad: ",priority));
            preface.add(FirstPDF.createDescription("Parcial: ",partial+""));
            preface.add(FirstPDF.createDescription("Máximo de compra: ",purchaseMax));
            preface.add(new Paragraph("Mínimo de compra: -1.0",FirstPDF.p));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Eventos",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(2);
            float[] columnWidths = new float[]{10f, 10f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Evento"));
            table.addCell(FirstPDF.createTableHeader("Plan de Tarifa"));
            for(AlterationEventMapT item : this.alterationEvents){
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
