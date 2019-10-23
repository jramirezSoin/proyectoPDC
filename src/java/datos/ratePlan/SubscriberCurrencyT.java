/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.ControlFunctions;
import control.ControlPath;
import control.FirstPDF;
import datos.ListaT;
import datos.Nodo;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class SubscriberCurrencyT extends Nodo{
    private String currencyCode="";
    private String currencyName="";
    private ApplicableRumT applicableRum;
    private ArrayList<CrpRelDateRangeT> crpRelDateRanges= new ArrayList<>();
    
    public SubscriberCurrencyT(){}
    public SubscriberCurrencyT(int id){this.id=id;}

    public String getCurrencyCode() {
        return currencyCode;
    }
    
    @Override
    public void clean(){
        crpRelDateRanges.clear();
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public ApplicableRumT getApplicableRum() {
        return applicableRum;
    }

    public void setApplicableRum(ApplicableRumT applicableRum) {
        this.applicableRum = applicableRum;
    }

    public ArrayList<CrpRelDateRangeT> getCrpRelDateRanges() {
        return crpRelDateRanges;
    }

    public void setCrpRelDateRanges(ArrayList<CrpRelDateRangeT> crpRelDateRanges) {
        this.crpRelDateRanges = crpRelDateRanges;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    
    @Override
    public String toString(String s) {
        String crpRelDateRangess="";
        for(int i=0;i<this.crpRelDateRanges.size();i++){
            if(applicableRum==null)
                crpRelDateRangess+=this.crpRelDateRanges.get(i).toString(s+"\t")+"\n";
            else
                crpRelDateRangess+=this.crpRelDateRanges.get(i).toString(s+"\t\t")+"\n";
        }
        return 
            s+"<subscriberCurrency>\n"+
            s+"\t<currencyCode>"+currencyCode+"</currencyCode>\n"+
            ((applicableRum==null)?crpRelDateRangess:applicableRum.toString(s+"\t", crpRelDateRangess)+"\n")+
            s+"</subscriberCurrency>";
    }

    @Override
    public int procesar(ArrayList<String> subscribers, int index) {
        int itemCount = 0;
        for(int i=index; i<subscribers.size();i++) {
            if(subscribers.get(i).matches("(?s)currencyCode: (.*)")){ this.currencyCode= subscribers.get(i).substring(14); this.currencyName=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("code",subscribers.get(i).substring(14)),"name");}
            else if(subscribers.get(i).matches("(?s)currencyName: (.*)")){ this.currencyName= subscribers.get(i).substring(14); this.currencyCode=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name",subscribers.get(i).substring(14)),"code"); }
            else if(subscribers.get(i).matches("(?s)applicableRum")){ 
                ApplicableRumT applicableRum = new ApplicableRumT();
                i= applicableRum.procesar(subscribers, i+1);
                i--;
                this.setApplicableRum(applicableRum);
            }
            else if(subscribers.get(i).matches("(?s)crpRelDateRange")){ 
                
                CrpRelDateRangeT crpRelDateRange = new CrpRelDateRangeT(crpRelDateRanges.size());
                i= crpRelDateRange.procesar(subscribers, i+1);
                i--;
                this.crpRelDateRanges.add(crpRelDateRange);
            }else return i;
        }
        return subscribers.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.crpRelDateRanges.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(CrpRelDateRangeT item: this.crpRelDateRanges){
            item.buscar(buscar);
        }
        if((currencyCode).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
        @Override
    public void getPDF(Element element) {
            Paragraph preface = (Paragraph) element;
            preface.add(new Paragraph("Moneda: "+currencyName,FirstPDF.normalFont));
            FirstPDF.addEmptyLine(preface, 1);
            if(this.applicableRum!=null){
                this.applicableRum.getPDF(preface);  
            }
            for(CrpRelDateRangeT rel: this.crpRelDateRanges){
                rel.getPDF(preface);
            }

    }
    
    
    
}
