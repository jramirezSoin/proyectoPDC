/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.ControlFunctions;
import control.FirstPDF;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class TimeModelT extends Nodo{
    
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="Convergent Usage";
    private String priceListName="Default";
    private String holidayCalendarName="";
    private ArrayList<TimeModelTagT> timeModelTags;
    private String validFrom="";

    public TimeModelT(int id) {
        this.id=id;
        this.timeModelTags= new ArrayList<>();
    }
    
    @Override
    public void clean(){
        timeModelTags.clear();
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
    
    public ArrayList<TimeModelTagT> getTimeModelTags() {
        return timeModelTags;
    }

    public void setTimeModelTags(ArrayList<TimeModelTagT> timeModelTags) {
        this.timeModelTags = timeModelTags;
    }

    public String getHolidayCalendarName() {
        return holidayCalendarName;
    }

    public void setHolidayCalendarName(String holidayCalendarName) {
        this.holidayCalendarName = holidayCalendarName;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }
    
    @Override
    public String toString() {
        String timeTags="";
        for(int i=0;i<this.timeModelTags.size();i++){
            timeTags+=this.timeModelTags.get(i).toString("\t\t")+"\n";
        }
        return  "<timeModel xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+ 
                "\t<name>" + name + "</name>\n"+
                "\t<description>" + description + "</description>\n"+
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<pricingProfileName>" + pricingProfileName + "</pricingProfileName>\n"+
                "\t<priceListName>" + priceListName + "</priceListName>\n"+
                "\t<holidayCalendarName>" + holidayCalendarName + "</holidayCalendarName>\n"+
                "\t<validityPeriod>\n"+
                "\t\t<validFrom>"+ validFrom + "</validFrom>\n"+
                timeTags + 
                "\t</validityPeriod>\n"+
                "</timeModel>";
    }
    
    @Override
    public int procesar(ArrayList<String> timeModels, int index, String user) {
        int itemCount = 0;
        boolean ingresa=false;
        for(int i=index; i<timeModels.size();i++) {
            
            if(timeModels.get(i).matches("name: (.*)")) this.name= timeModels.get(i).substring(6);
            else if(timeModels.get(i).matches("(?s)description: (.*)")) this.description= timeModels.get(i).substring(13).replaceAll("/n","\n");
            else if(timeModels.get(i).matches("(?s)internalId: (.*)")) this.internalId= timeModels.get(i).substring(12);
            else if(timeModels.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= timeModels.get(i).substring(15);
            else if(timeModels.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= timeModels.get(i).substring(20);
            else if(timeModels.get(i).matches("(?s)holidayCalendarName: (.*)")) this.holidayCalendarName= timeModels.get(i).substring(21);
            else if(timeModels.get(i).matches("validityPeriod")) ingresa=true;
            else if(timeModels.get(i).matches("(?s)validFrom: (.*)") && ingresa==true) this.validFrom= timeModels.get(i).substring(11);
            else if(timeModels.get(i).matches("(?s)timeModelTag")){ 
                
                TimeModelTagT timeModelTag = new TimeModelTagT(itemCount);
                itemCount++;
                i= timeModelTag.procesar(timeModels, i+1, user);
                i--;
                this.timeModelTags.add(timeModelTag);
            }else{return i;}
        }
        
        return timeModels.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.timeModelTags.get(i).procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        boolean estado=false;
        boolean aux=false;
        for(TimeModelTagT item: this.timeModelTags){
            aux= item.buscar(buscar);
            if(!estado) estado=aux;
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+holidayCalendarName+"/"+validFrom).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.timeModelTags.add((TimeModelTagT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.timeModelTags.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.timeModelTags.size();i++){
            this.timeModelTags.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.timeModelTags.get(i).merge(nodoI);
        }
    }
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Modelo de tiempo: "+this.name, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("Nombre: ",name));
            preface.add(FirstPDF.createDescription("Descripción: ",description));
            preface.add(FirstPDF.createDescription("ID: ",internalId));
            preface.add(FirstPDF.createDescription("Nombre de lista de precio: ",priceListName));
            preface.add(FirstPDF.createDescription("Calendario de Festividades: ",holidayCalendarName));
            preface.add(FirstPDF.createDescription("Valido desde: ",ControlFunctions.getParseDate(validFrom)));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Tags",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            for(TimeModelTagT item : this.timeModelTags){
                if(item.visibilidad){
                    item.getPDF(preface);
                }
            }
            document.add(preface);
            
        } catch (DocumentException ex) {
            Logger.getLogger(ZoneModelT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TimeModelT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
