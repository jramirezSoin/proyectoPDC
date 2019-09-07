/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

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
        for(int i=index; i<generics.size();i++) {
            
            if(generics.get(i).matches("(?s)genericSelectorName: (.*)")) this.genericSelectorName= generics.get(i).substring(21);
            else if(generics.get(i).matches("(?s)results")){ 
                
                ResultsT resul = new ResultsT(itemCount);
                itemCount++;
                i= resul.procesar(generics, i+1);
                i--;
                this.results.add(resul);
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
    
    
}
