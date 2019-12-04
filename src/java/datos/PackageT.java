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
public class PackageT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="Subscription";
    private String priceListName="Default";
    private boolean billOnPurchase=false;
    private ArrayList<PackageItemT> packageItems;
    private ArrayList<BalanceSpecT> balances;
    
    public PackageT(int id){
        this.id= id;
        packageItems = new ArrayList<>();
        balances = new ArrayList<>();
    }
    
    @Override
    public void clean(){
        packageItems.clear();
        balances.clear();
    }
    
    public PackageT(){
        packageItems = new ArrayList<>();
        balances = new ArrayList<>();
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

    public boolean isBillOnPurchase() {
        return billOnPurchase;
    }

    public void setBillOnPurchase(boolean billOnPurchase) {
        this.billOnPurchase = billOnPurchase;
    }

    public ArrayList<PackageItemT> getPackageItems() {
        return packageItems;
    }

    public void setPackageItems(ArrayList<PackageItemT> packageItems) {
        this.packageItems = packageItems;
    }

    public ArrayList<BalanceSpecT> getBalances() {
        return balances;
    }

    public void setBalances(ArrayList<BalanceSpecT> balances) {
        this.balances = balances;
    }
    
        @Override
    public String toString() {
        String packItems="";
        for(int i=0;i<this.packageItems.size();i++){
            packItems+=this.packageItems.get(i).toString("\t")+"\n";
        }
        String balanceSpecs="";
        for(int i=0;i<this.balances.size();i++){
            balanceSpecs+=this.balances.get(i).toString("\t\t")+"\n";
        }
        return "<package xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+
                "\t<name>" + name + "</name>\n"+
                "\t<description>" + description + "</description>\n"+
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<pricingProfileName>" + pricingProfileName + "</pricingProfileName>\n"+
                "\t<priceListName>" + priceListName + "</priceListName>\n"+
                "\t<billOnPurchase>" + billOnPurchase + "</billOnPurchase>\n"+
                packItems+
                "    <balanceSpecification>\n" +
                "\t\t<name>Account Balance Group</name>\n"+
                balanceSpecs+
                "\t</balanceSpecification>\n"+
                "</package>";
    }
    
    @Override
    public int procesar(ArrayList<String> packs2, int index, String user) {
        ArrayList<String> packs= (ArrayList<String>) packs2.clone();
        int itemCount = 0;
        int itemCount2 = 0;
        boolean balanceB=false;
        for(int i=index; i<packs.size();i++) {
            
            if(packs.get(i).matches("(?s)iceUpdater: (.*)")) this.iceUpdaterCount = packs.get(i).substring(12).equals("new")?1:Integer.parseInt(packs.get(i).substring(12));
            else if(packs.get(i).matches("(?s)name: (.*)")) this.name= packs.get(i).substring(6);
            else if(packs.get(i).matches("(?s)description: (.*)")) this.description= packs.get(i).substring(13);
            else if(packs.get(i).matches("(?s)internalId: (.*)")) this.internalId= packs.get(i).substring(12);
            else if(packs.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= packs.get(i).substring(15);
            else if(packs.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= packs.get(i).substring(20);
            else if(packs.get(i).matches("(?s)billOnPurchase: (.*)")) this.billOnPurchase= Boolean.valueOf(packs.get(i).substring(16));
            else if(("productSpecPackageItem customerSpecPackageItem").contains(packs.get(i))){ 
                
                PackageItemT packItem = new PackageItemT(itemCount);
                itemCount++;
                i= packItem.procesar(packs, i+1,user);
                i--;
                this.packageItems.add(packItem);
            }else if(("balanceSpecification").contains(packs.get(i))){ 
                i++;
                balanceB=true;
            }
            else if(("balanceElementSpecification").contains(packs.get(i)) && balanceB){ 
                
                BalanceSpecT balance = new BalanceSpecT(itemCount2);
                itemCount2++;
                i= balance.procesar(packs, i+1, user);
                i--;
                this.balances.add(balance);
            }else return i;
        }
        return packs.size();
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            if(indexs.get(0)==1){
                indexs.remove(0);
                int i= indexs.get(0);
                indexs.remove(0);
                this.packageItems.get(i).procesarI(lista, index, indexs, user);}
            else{
                indexs.remove(0);
                int i= indexs.get(0);
                indexs.remove(0);
                this.balances.get(i).procesarI(lista, index, indexs, user);}
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(PackageItemT item: this.packageItems){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+billOnPurchase).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        if(index.get(0)==1){
            if(((PackageItemT)nodo).getSpecName().equals("CustomerPackage")){
                PackageItemT pack= (PackageItemT) nodo;
                pack.id=0;
                for(PackageItemT p: this.packageItems)
                    p.id++;
                this.packageItems.add(0,pack);
            }else
                this.packageItems.add((PackageItemT) nodo);
            
        }else
            this.balances.add((BalanceSpecT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {
        if(index.get(0)==1){
            this.packageItems.remove(((int) index.get(1)));
            for(int i=index.get(1); i<this.packageItems.size();i++){
                this.packageItems.get(i).id--;
            }}
        else{
            this.balances.remove(((int) index.get(1)));
            for(int i=index.get(1); i<this.balances.size();i++){
                this.balances.get(i).id--;
            }}
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.packageItems.get(i).merge(nodoI);
        }
    }
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Paquete: "+this.name, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("Nombre: ",name));
            preface.add(FirstPDF.createDescription("Descripción: ",description));
            preface.add(FirstPDF.createDescription("ID: ",internalId));
            preface.add(FirstPDF.createDescription("Nombre de lista de precio: ",priceListName));
            preface.add(FirstPDF.createDescription("Factura por compra: ",billOnPurchase+""));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Items",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            for(PackageItemT item : this.packageItems){
                if(item.visibilidad){
                    item.getPDF(preface);
                }
            }
            FirstPDF.addEmptyLine(preface, 1);            
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Especificación de Saldo",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(5);
            float[] columnWidths = new float[]{30f, 10f, 10f, 10f, 10f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Saldo en"));
            table.addCell(FirstPDF.createTableHeader("Limite de crédito"));
            table.addCell(FirstPDF.createTableHeader("Nivel mínimo de crédito"));
            table.addCell(FirstPDF.createTableHeader("Umbral"));
            table.addCell(FirstPDF.createTableHeader("Umbral"));
            for(BalanceSpecT item : this.balances){
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


    
    

