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
            holidays+=this.holidayItems.get(i).toString("\t")+"\n";
        }
        return "<holidayCalendar xmlns:mtd=\"http://xmlns.oracle.com/communications/platform/model/Metadata\" xmlns:cim=\"http://xmlns.oracle.com/communications/platform/model/Config\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" +
                "\t<name>" + name + "</name>\n"+
                "\t<description>" + description + "</description>\n"+
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<priceListName>" + priceListName + "</priceListName>\n"+
                "\t<obsolete>" + obsolete + "</obsolete>\n"+
                holidays+
                "</holidayCalendar>";
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
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Holiday Calendar: "+this.name, FirstPDF.titleFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("nombre: "+name,FirstPDF.normalFont));
            preface.add(new Paragraph("Descripción: "+description,FirstPDF.normalFont));
            preface.add(new Paragraph("ID: "+internalId,FirstPDF.normalFont));
            preface.add(new Paragraph("Nombre de lista de precio: "+priceListName,FirstPDF.normalFont));
            preface.add(new Paragraph("Obsoleto: "+obsolete,FirstPDF.normalFont));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Festividades",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(4);
            float[] columnWidths = new float[]{30f, 10f, 20f, 20f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Festividad"));
            table.addCell(FirstPDF.createTableHeader("Día"));
            table.addCell(FirstPDF.createTableHeader("Mes"));
            table.addCell(FirstPDF.createTableHeader("Año"));
            for(HolidayItemT item : this.holidayItems){
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
