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
public class BundledT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="Default";
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
    
    @Override
    public void clean(){
        bundledItems.clear();
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
            bundleItems+=this.bundledItems.get(i).toString("\t")+"\n";
        }
        return "<bundledProductOffering xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+
                "\t<name>" + name + "</name>\n"+
                "\t<description>" + description + "</description>\n"+
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<pricingProfileName>" + pricingProfileName + "</pricingProfileName>\n"+
                "\t<priceListName>" + priceListName + "</priceListName>\n"+
                "\t<timeRange>" + timeRange + "</timeRange>\n"+
                ((customerSpecName.equals(""))?"\t<productSpecName>" + productSpecName + "</productSpecName>\n":"\t<customerSpecName>" + customerSpecName + "</customerSpecName>\n")+
                "\t<billOnPurchase>" + billOnPurchase + "</billOnPurchase>\n"+
                "\t<customize>" + customize + "</customize>\n"+
                "\t<groupBalanceElements>" + groupBalanceElements + "</groupBalanceElements>\n"+ 
                bundleItems +
                "</bundledProductOffering>";
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
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+timeRange+"/"+productSpecName+"/"+customerSpecName+"/"+billOnPurchase+"/"+customize+"/"+groupBalanceElements).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())||estado){
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
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Lote: "+this.name, FirstPDF.titleFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Nombre: "+name,FirstPDF.normalFont));
            preface.add(new Paragraph("Descripción: "+description,FirstPDF.normalFont));
            preface.add(new Paragraph("ID: "+internalId,FirstPDF.normalFont));
            preface.add(new Paragraph("Nombre de lista de precio: "+priceListName,FirstPDF.normalFont));
            preface.add(new Paragraph("Rango: "+timeRange,FirstPDF.normalFont));
            if(!productSpecName.equals(""))
            preface.add(new Paragraph("Producto: "+productSpecName,FirstPDF.normalFont));
            else
            preface.add(new Paragraph("Cliente: "+customerSpecName,FirstPDF.normalFont));
            preface.add(new Paragraph("Factura por compra: "+billOnPurchase,FirstPDF.normalFont));
            preface.add(new Paragraph("Personalizar: "+customize,FirstPDF.normalFont));
            preface.add(new Paragraph("Elementos de grupo de saldo: "+groupBalanceElements,FirstPDF.normalFont));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Ofertas",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Oferta"));
            table.addCell(FirstPDF.createTableHeader("Tipo"));
            for(BundledItemT item : this.bundledItems){
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
