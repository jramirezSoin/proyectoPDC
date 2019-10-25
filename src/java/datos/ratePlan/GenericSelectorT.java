/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.FirstPDF;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class GenericSelectorT extends Nodo implements ResultI{
    private String genericSelectorName="";
    private ArrayList<ResultsT> results= new ArrayList<>();
    
    public GenericSelectorT(){}
    public GenericSelectorT(int id){this.id=id;}

    public String getGenericSelectorName() {
        return genericSelectorName;
    }
    
    @Override
    public void clean(){
        results.clear();
    }

    public void setGenericSelectorName(String genericSelectorName) {
        this.genericSelectorName = genericSelectorName;
    }

    public ArrayList<ResultsT> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsT> results) {
        this.results = results;
    }
    
    @Override
    public String toString(String s) {
        String gens="";
        for(int i=0;i<this.results.size();i++){
            gens+=this.results.get(i).toString(s+"\t")+"\n";
        }
        return s+"<genericSelector>\n"+
               s+"\t<genericSelectorName>"+genericSelectorName+"</genericSelectorName>\n"+gens+
               s+"</genericSelector>";
    }

    @Override
    public int procesar(ArrayList<String> generics, int index) {
        int itemCount = 0;
        boolean resultsGen= false;
        for(int i=index; i<generics.size();i++) {
            
            if(generics.get(i).matches("(?s)genericSelectorName: (.*)")) this.genericSelectorName= generics.get(i).substring(21);
            else if(generics.get(i).matches("(?s)results") && !resultsGen){ 
                if(i+2<generics.size() && generics.get(i+2).matches("(?s)timeConfiguration")) return i;
                ResultsT resul = new ResultsT(results.size());
                itemCount++;
                i= resul.procesar(generics, i+1);
                i--;
                this.results.add(resul);
            }else if(generics.get(i).matches("(?s)resultsGen")){
                i++;
                resultsGen=true;
                int contador=0;
                for(int k=i;i<generics.size();i++){
                    if(generics.get(i).matches("(?s)name_[0-9]{1,}: (.*)")){
                        int indexTag= Integer.parseInt(generics.get(i).split(": ")[0].replace("name_", ""));
                        while(contador!=indexTag){
                            this.getResults().remove(contador);
                        }
                        this.getResults().get(indexTag).getName().clear();
                        this.getResults().get(indexTag).getName().add(generics.get(i).split(": ")[1]);
                    }else if(generics.get(i).matches("(?s)nameResult: (.*)")){
                        ResultsT resul = new ResultsT(results.size());
                        resul.getName().add(generics.get(i).substring(12));
                        CrpCompositePopModelT popModel = new CrpCompositePopModelT(0);
                        popModel.setZoneCrp();
                        resul.setResult(popModel);
                        this.getResults().add(resul);
                    }else{
                        i--;
                        break;
                    }
                }
                
            }else return i;
        }
        return generics.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.results.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(ResultsT item: this.results){
            item.buscar(buscar);
        }
        if((genericSelectorName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    @Override
    public void getRumCurrency(String rum, String currency) {
        for(ResultsT crp : this.getResults()){
            ((CrpCompositePopModelT)crp.getResult()).getRumCurrency(rum,currency);
        }
    }
    
    @Override
    public void getPDF(Element element) {
            Paragraph preface = (Paragraph) element;
            preface.add(new Paragraph("Generic Selector: "+genericSelectorName,FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            for(ResultsT result: this.results){
                result.getPDF(preface);
                LineSeparator line = new LineSeparator();              
                preface.add(line);
                FirstPDF.addEmptyLine(preface, 1);
            }
    }
    
    
}
