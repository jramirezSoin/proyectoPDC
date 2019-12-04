/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.FirstPDF;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class AlterationRatePlanT extends Nodo{
    private String name = "";
    private String description = "";
    private String internalId = "";
    private String pricingProfileName = "";
    private String priceListName = "";
    private String taxCode="";
    private ArpDateRangeT arpDateRange = new ArpDateRangeT();
    private String dir= "";

    public AlterationRatePlanT() {
    }
    
    public AlterationRatePlanT(int id) {
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

    public ArpDateRangeT getArpDateRange() {
        return arpDateRange;
    }

    public void setArpDateRange(ArpDateRangeT arpDateRange) {
        this.arpDateRange = arpDateRange;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
    
    @Override
    public int procesar(ArrayList<String> ratePlan2, int index, String user) {
        ArrayList<String> ratePlan= (ArrayList<String>)ratePlan2.clone();
        for(int i=index; i<ratePlan.size();i++) {
            if(ratePlan.get(i).matches("(?s)iceUpdater: (.*)")) this.iceUpdaterCount = ratePlan.get(i).substring(12).equals("new")?1:Integer.parseInt(ratePlan.get(i).substring(12));
            else if(ratePlan.get(i).matches("(?s)name: (.*)")) this.name= ratePlan.get(i).substring(6);
            else if(ratePlan.get(i).matches("(?s)description: (.*)")) this.description= ratePlan.get(i).substring(13);
            else if(ratePlan.get(i).matches("(?s)internalId: (.*)")) this.internalId= ratePlan.get(i).substring(12);
            else if(ratePlan.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= ratePlan.get(i).substring(15);
            else if(ratePlan.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= ratePlan.get(i).substring(20);
            else if(ratePlan.get(i).matches("(?s)taxCode: (.*)")) this.taxCode= ratePlan.get(i).substring(9);
            else if(("arpDateRange").contains(ratePlan.get(i))){     
                ArpDateRangeT arpDateRange = new ArpDateRangeT(0);
                i= arpDateRange.procesar(ratePlan, i+1, user);
                i--;
                this.arpDateRange=arpDateRange;
            }else return i;
        }
        return ratePlan.size();
    }
    
    @Override
    public String toString() {
        return "<alterationRatePlan xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+
            "\t<name>"+name+"</name>\n" +
            "\t<description>"+description+"</description>\n" +
            "\t<internalId>"+internalId+"</internalId>\n" +
            "\t<pricingProfileName>"+pricingProfileName+"</pricingProfileName>\n" +
            "\t<priceListName>"+priceListName+"</priceListName>\n"+
            "\t<taxCode>"+taxCode+"</taxCode>\n"+
            this.arpDateRange.toString("\t")+"\n"+
            "</alterationRatePlan>";    
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.arpDateRange.procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    public AlterationConfigurationT buscaPop(String dir) {
        this.dir=dir;
        int key = Integer.parseInt(dir);
        return this.getArpDateRange().getAlterationConfigurations().get(key);
            
    }
    
        @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Plan de Tarifa de Descuento: "+this.name, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("nombre: ",name));
            preface.add(FirstPDF.createDescription("Descripción: ",description));
            preface.add(FirstPDF.createDescription("ID: ",internalId));
            preface.add(FirstPDF.createDescription("Nombre de lista de precio: ",priceListName));
            preface.add(FirstPDF.createDescription("Código de impuesto: ",taxCode));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            this.arpDateRange.getPDF(preface);
            document.add(preface);
            
        } catch (DocumentException ex) {
            
        }
    }
    
    
    
    
    
}
