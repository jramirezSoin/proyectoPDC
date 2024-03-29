/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import control.FirstPDF;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ZoneModelT extends Nodo{
    private boolean enhanced=false;
    private String zoneModelName="";
    private String uscModelName="";
    private ArrayList<ResultsT> results= new ArrayList<>();
    
    public ZoneModelT(){}
    public ZoneModelT(String enhance){
        if(enhance.equals("enhancedZoneModel")) enhanced=true;
    }
    public ZoneModelT(int id){this.id=id;}

    public boolean isEnhanced() {
        return enhanced;
    }
    
    @Override
    public void clean(){
        results.clear();
    }

    public void setEnhanced(boolean enhanced) {
        this.enhanced = enhanced;
    }

    public String getZoneModelName() {
        return zoneModelName;
    }

    public void setZoneModelName(String zoneModelName) {
        this.zoneModelName = zoneModelName;
    }

    public String getUscModelName() {
        return uscModelName;
    }

    public void setUscModelName(String uscModelName) {
        this.uscModelName = uscModelName;
    }

    public ArrayList<ResultsT> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsT> results) {
        this.results = results;
    }
    
    @Override
    public String toString(String s){
        String zoneItems="";
        for(int i=0;i<this.results.size();i++){
            zoneItems+=this.results.get(i).toString(s+"\t")+"\n";
        }
        return s+"<"+((enhanced)?"enhancedZ":"z")+"oneModel>\n" +
            s+"\t<zoneModelName>"+zoneModelName+"</zoneModelName>\n" +
            ((!enhanced)?"":s+"\t<uscModelName>"+uscModelName+"</uscModelName>\n") +zoneItems+
            s+"</"+((enhanced)?"enhancedZ":"z")+"oneModel>";
    }
    
        @Override
    public int procesar(ArrayList<String> subscribers, int index, String user) {
        int itemCount = results.size();
        Boolean isZoneModeled= false;
        for(int i=index; i<subscribers.size();i++) {
            if(subscribers.get(i).matches("(?s)zoneModelName: (.*)"))this.zoneModelName= subscribers.get(i).substring(15);
            else if(subscribers.get(i).matches("(?s)enhanced: (.*)"))this.enhanced= Boolean.valueOf(subscribers.get(i).substring(10));
            else if(subscribers.get(i).matches("(?s)uscModelName: (.*)"))this.uscModelName= subscribers.get(i).substring(14);
            else if(subscribers.get(i).matches("(?s)results_n[0-9]{1,}") && isZoneModeled){ 
                int resultIndex= Integer.parseInt(subscribers.get(i).replace("results_n",""));
                i= this.getResults().get(resultIndex).procesar(subscribers, i+1, user);
                i--;
            }else if(subscribers.get(i).matches("(?s)results")){ 
                ResultsT result = new ResultsT(results.size());
                i= result.procesar(subscribers, i+1, user);
                i--;
                this.results.add(result);
            }
            else if(subscribers.get(i).matches("(?s)zoneModel")){
                isZoneModeled= true;
            }
            else return i;
        }
        return subscribers.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.results.get(i).procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(ResultsT item: this.results){
            item.buscar(buscar);
        }
        if((zoneModelName+"/"+uscModelName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    CrpCompositePopModelT buscaPop(ArrayList<Integer> index) {
        if(index.size()==1) return (CrpCompositePopModelT)this.results.get(index.get(0)).getResult();
        ResultI resul= this.results.get(index.get(0)).getResult();
        if(resul instanceof GenericSelectorT) return (CrpCompositePopModelT)(((GenericSelectorT)resul).getResults().get(index.get(1)).getResult());
        else if(resul instanceof TimeConfigurationT) return (CrpCompositePopModelT)(((TimeConfigurationT)resul).getTags().get(index.get(1)).getCrpCompositePopModel());       
        else return null;
    }
    
    @Override
    public void getPDF(Element element) {
            Paragraph preface = (Paragraph) element;
            preface.add(new Paragraph("Zone Model: "+zoneModelName,FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            for(ResultsT result:this.results){
                result.getPDF(preface);
                
            }
    }
    
    
    
}
