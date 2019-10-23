/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;


import com.itextpdf.text.Element;
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
public class ArpDateRangeT extends Nodo{
    private String startDate = "";
    private String endDate = "";
    private ArrayList<AlterationConfigurationT> alterationConfigurations = new ArrayList<>();

    public ArpDateRangeT() {
    }
    
    public ArpDateRangeT(int id) {
        this.id=id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<AlterationConfigurationT> getAlterationConfigurations() {
        return alterationConfigurations;
    }

    public void setAlterationConfigurations(ArrayList<AlterationConfigurationT> alterationConfigurations) {
        this.alterationConfigurations = alterationConfigurations;
    }
    
    @Override
    public int procesar(ArrayList<String> ratePlan2, int index) {
        ArrayList<String> ratePlan= (ArrayList<String>)ratePlan2.clone();
        for(int i=index; i<ratePlan.size();i++) {
            if(ratePlan.get(i).matches("(?s)startDate: (.*)")) this.startDate= ratePlan.get(i).substring(11);
            else if(ratePlan.get(i).matches("(?s)endDate: (.*)")) this.endDate= ratePlan.get(i).substring(9);
            else if(("alterationConfiguration").contains(ratePlan.get(i))){     
                AlterationConfigurationT alterationConfiguration = new AlterationConfigurationT(alterationConfigurations.size());
                i= alterationConfiguration.procesar(ratePlan, i+1);
                i--;
                this.alterationConfigurations.add(alterationConfiguration);
            }else return i;
        }
        return ratePlan.size();
    }
    
    @Override
    public String toString(String s) {
        String configurations="";
        for(AlterationConfigurationT conf : this.alterationConfigurations){
            configurations+=conf.toString(s+"\t")+"\n";
        }
        return s+"<arpDateRange>\n" +
                s+"\t<startDate>"+startDate+"</startDate>\n" +
                s+"\t<endDate>"+endDate+"</endDate>\n"+
                configurations+
                s+"</arpDateRange>";
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.getAlterationConfigurations().get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((startDate+"/"+endDate).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
        @Override
    public void getPDF(Element element) {
        try {
            Paragraph preface = (Paragraph) element;
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("fecha inicio: "+ControlFunctions.getParseDate(startDate),FirstPDF.normalFont));
            preface.add(new Paragraph("fecha fin: "+ControlFunctions.getParseDate(endDate),FirstPDF.normalFont));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            for(AlterationConfigurationT item: this.alterationConfigurations)
                    item.getPDF(preface);
            
        } catch (ParseException ex) {
            Logger.getLogger(ArpDateRangeT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
