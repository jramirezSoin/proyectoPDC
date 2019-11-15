/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
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
public class TimeModelTagT extends Nodo{
    public String tagName="";
    public ArrayList<TimeSpecT> timeSpecs;

    public TimeModelTagT(int id) {
        this.id=id;
        this.timeSpecs= new ArrayList<>();
    }

    public String getTagName() {
        return tagName;
    }
    
    @Override
    public void clean(){
        timeSpecs.clear();
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ArrayList<TimeSpecT> getTimeSpecs() {
        return timeSpecs;
    }

    public void setTimeSpecs(ArrayList<TimeSpecT> timeSpecs) {
        this.timeSpecs = timeSpecs;
    }
    
    @Override
    public int procesar(ArrayList<String> timeSpecs1, int index, String user) {
        this.getTimeSpecs().clear();
        int itemCount = 0;
        for(int i=index; i<timeSpecs1.size();i++) {
            if(timeSpecs1.get(i).matches("(?s)tagName: (.*)")) this.tagName= timeSpecs1.get(i).substring(9);
            else if(timeSpecs1.get(i).matches("(?s)timeSpecification")){ 
                
                TimeSpecT timespec = new TimeSpecT(itemCount);
                itemCount++;
                i= timespec.procesar(timeSpecs1, i+1, user);
                i--;
                this.timeSpecs.add(timespec);
            }else{return i;}
        }
        return timeSpecs1.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.timeSpecs.get(i).procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        boolean estado=false;
        boolean aux=false;
        for(TimeSpecT item: this.timeSpecs){
            aux= item.buscar(buscar);
            if(!estado) estado=aux;
        }
        if((tagName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.timeSpecs.add((TimeSpecT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.timeSpecs.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.timeSpecs.size();i++){
            this.timeSpecs.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.timeSpecs.get(i).merge(nodoI);
        }
    }

    @Override
    public String toString(String s) {
        String timeSpecss="";
        for(int i=0;i<this.timeSpecs.size();i++){
            timeSpecss+=this.timeSpecs.get(i).toString(s+"\t")+"\n";
        }
        return s+"<timeModelTag>\n"+
                s+"\t<tagName>" + tagName + "</tagName>\n"+
                timeSpecss +
                s+"</timeModelTag>";
    }
    
    @Override
    public void getPDF(Element element) {
        try {
            Paragraph preface = (Paragraph) element;
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Tag: "+this.tagName, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Especificación",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            for(TimeSpecT item : this.timeSpecs){
                PdfPTable table = new PdfPTable(4);
                    float[] columnWidths = new float[]{30f, 20f, 20f, 20f};
                    table.setWidths(columnWidths);
                    table.setWidthPercentage(100);
                    table.addCell(FirstPDF.createTableHeader("Nombre"));
                    table.addCell(FirstPDF.createTableHeader("Descripción"));
                    table.addCell(FirstPDF.createTableHeader("Tiempo"));
                    table.addCell(FirstPDF.createTableHeader("Festividad"));
                    preface.add(table);
                if(item.visibilidad){
                    table = new PdfPTable(4);
                    columnWidths = new float[]{30f, 20f, 20f, 20f};
                    table.setWidths(columnWidths);
                    table.setWidthPercentage(100);
                    table.addCell(FirstPDF.createTableCell(item.getName()));
                    table.addCell(FirstPDF.createTableCell(item.getDescription()));
                    table.addCell(FirstPDF.createTableCell(ControlFunctions.getParseHour(item.getTimeOfDay())));
                    table.addCell(FirstPDF.createTableCell(item.getHoliday()+""));
                    preface.add(table);
                    table = new PdfPTable(2);
                    columnWidths = new float[]{10f, 30f};
                    table.setWidths(columnWidths);
                    String dias="";
                    for(ListaT t: item.daysOfWeek) dias+=", "+t.valor;
                    String meses="";
                    for(ListaT t: item.monthsOfYear) meses+=", "+t.valor;
                    table.setWidthPercentage(100);
                    table.addCell(FirstPDF.createTableCell("Dias"));
                    table.addCell(FirstPDF.createTableCell(dias.length()>0?dias.substring(2):dias));
                    table.addCell(FirstPDF.createTableCell("Meses"));
                    table.addCell(FirstPDF.createTableCell(meses.length()>0?meses.substring(2):meses));
                    table.addCell(FirstPDF.createTableCell("Dias del mes"));
                    table.addCell(FirstPDF.createTableCell(item.daysOfMonth));
                    table.addCell(FirstPDF.createTableCell(""));
                    table.addCell(FirstPDF.createTableCell(""));
                    preface.add(table);
                }
            }
            
        } catch (DocumentException ex) {
            Logger.getLogger(ZoneModelT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TimeModelTagT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    public class TimeSpecT extends Nodo{
        private String name="";
        private String description="";
        private String timeOfDay="";
        private ArrayList<ListaT> daysOfWeek;
        private ArrayList<ListaT> monthsOfYear;
        private boolean holiday;
        private String daysOfMonth="";
        
        public TimeSpecT(){
            this.daysOfWeek= new ArrayList<>();
            this.monthsOfYear= new ArrayList<>();
            
        }
        
        public TimeSpecT(int id){
            this.id=id;
            this.daysOfWeek= new ArrayList<>();
            this.monthsOfYear= new ArrayList<>();
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

        public String getTimeOfDay() {
            return timeOfDay;
        }

        public void setTimeOfDay(String timeOfDay) {
            this.timeOfDay = timeOfDay;
        }

        public ArrayList<ListaT> getDaysOfWeek() {
            return daysOfWeek;
        }

        public void setDaysOfWeek(ArrayList<ListaT> daysOfWeek) {
            this.daysOfWeek = daysOfWeek;
        }

        public ArrayList<ListaT> getMonthsOfYear() {
            return monthsOfYear;
        }

        public void setMonthsOfYear(ArrayList<ListaT> monthsOfYear) {
            this.monthsOfYear = monthsOfYear;
        }

        public boolean getHoliday() {
            return holiday;
        }

        public void setHoliday(boolean holiday) {
            this.holiday = holiday;
        }

        public String getDaysOfMonth() {
            return daysOfMonth;
        }

        public void setDaysOfMonth(String daysOfMonth) {
            this.daysOfMonth = daysOfMonth;
        }

        @Override
        public String toString(String s) {
            String days="",months="",daymon="";
            if(this.daysOfWeek.size()>0){
                days=s+"\t<daysOfWeek>\n";
                for(ListaT l: this.daysOfWeek) 
                    days+=s+"\t\t<day>"+l.valor+"</day>\n";
                days+=s+"\t</daysOfWeek>\n";
            }
            if(this.monthsOfYear.size()>0){
                months=s+"\t<monthsOfYear>\n";
                for(ListaT l: this.monthsOfYear) 
                    months+=s+"\t\t<month>"+l.valor+"</month>\n";
                months+=s+"\t</monthsOfYear>\n";
            }
            if(!this.daysOfMonth.equals(""))
                daymon=s+"\t<daysOfMonth>"+daysOfMonth+"</daysOfMonth>\n";
            return  s+"<timeSpecification>\n"+
                    s+"\t<name>" + name + "</name>\n"+
                    s+"\t<description>" + description + "</description>\n"+
                    s+"\t<timeOfDay>" + timeOfDay + "</timeOfDay>\n" + 
                    days + 
                    months + 
                    s+"\t<holiday>" + holiday+"</holiday>\n"+
                    daymon +
                    s+"</timeSpecification>";
        }
        
        
        
        @Override
    public int procesar(ArrayList<String> timeTags, int index, String user) {
        int dayCount = 0;
        int monthCount = 0;
        boolean meses = false;
        boolean dias = false;
        for(int i=index; i<timeTags.size();i++) {
            if(timeTags.get(i).matches("(?s)name: (.*)")) this.name= timeTags.get(i).substring(6);
            else if(timeTags.get(i).matches("(?s)description: (.*)")) this.description= timeTags.get(i).substring(13);
            else if(timeTags.get(i).matches("(?s)timeOfDay: (.*)")) this.timeOfDay= timeTags.get(i).substring(11);
            else if(timeTags.get(i).matches("(?s)holiday: (.*)")) this.holiday= Boolean.valueOf(timeTags.get(i).substring(9));
            else if(timeTags.get(i).matches("(?s)daysOfMonth: (.*)")) this.daysOfMonth= timeTags.get(i).substring(13);
            else if(timeTags.get(i).matches("(?s)monthsOfYear")) meses=true;
            else if(timeTags.get(i).matches("(?s)daysOfWeek")) dias=true;
            else if(timeTags.get(i).matches("(?s)day: (.*)") && dias){ 
                this.daysOfWeek.add(new ListaT(timeTags.get(i).substring(5),dayCount));
                dayCount++;
            }
            else if(timeTags.get(i).matches("(?s)month: (.*)") && meses){ 
                this.monthsOfYear.add(new ListaT(timeTags.get(i).substring(7),monthCount));
                monthCount++;
            }
            else{
            return i;}
        }
        return timeTags.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        boolean estado=false;
        boolean aux=false;
        for(ListaT item: this.daysOfWeek){
            aux= item.valor.replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase());
            if(!estado) estado=aux;
        }
        for(ListaT item: this.monthsOfYear){
            aux= item.valor.replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase());
            if(!estado) estado=aux;
        }
        if((name+"/"+description+"/"+timeOfDay+"/"+holiday+"/"+daysOfMonth).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }      
        
        
    }
    
}
