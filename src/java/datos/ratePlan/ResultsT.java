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
public class ResultsT extends Nodo{
    private ArrayList<String> name= new ArrayList<>();
    private ResultI result;
    
    public ResultsT(){}
    public ResultsT(int id){this.id=id;}

    public ArrayList<String> getName() {
        return name;
    }
    
    @Override
    public void clean(){
        name.clear();
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ResultI getResult() {
        return result;
    }

    public void setResult(ResultI result) {
        this.result = result;
    }
    
    @Override
    public String toString(String s) {
        String names="";
        for(String i: name) names+= s+"\t<name>"+i+"</name>\n";
        return 
        s+"<results>\n"+
        names+
        ((result instanceof GenericSelectorT)?((GenericSelectorT)result).toString(s+"\t")
        :((result instanceof CrpCompositePopModelT)?((CrpCompositePopModelT)result).toString(s+"\t")
        :((TimeConfigurationT)result).toString(s+"\t")))+"\n"+
        s+"</results>";
    }

    @Override
    public int procesar(ArrayList<String> chargeRates2, int index) {
        ArrayList<String> chargeRates=  (ArrayList<String>)chargeRates2.clone();
        boolean modified= false;
        for(int i=index; i<chargeRates.size();i++) {
            if(chargeRates.get(i).matches("(?s)name: (.*)")) this.name.add(chargeRates.get(i).substring(6));
            else if(chargeRates.get(i).matches("(?s)nameS: (.*)")){
                this.name=listToArray(chargeRates.get(i).substring(7));
                modified=true;
            }
            else if(chargeRates.get(i).matches("(?s)genericSelector")){
                GenericSelectorT resultado;
                if(modified && this.getResult()!=null)
                    resultado = (GenericSelectorT)this.getResult();
                else
                    resultado = new GenericSelectorT(0);
                i= resultado.procesar(chargeRates, i+1);
                i--;
                this.setResult(resultado);
            }else if(chargeRates.get(i).matches("(?s)crpCompositePopModel")){
                CrpCompositePopModelT resultado = new CrpCompositePopModelT(0);
                i= resultado.procesar(chargeRates, i+1);
                i--;
                this.setResult(resultado);
            }else if(chargeRates.get(i).matches("(?s)timeConfiguration")){
                TimeConfigurationT resultado;
                if(modified && this.getResult()!=null)
                    resultado = (TimeConfigurationT)this.getResult();
                else
                    resultado = new TimeConfigurationT(0);
                i= resultado.procesar(chargeRates, i+1);
                i--;
                this.setResult(resultado);
            }else return i;
        }
        return chargeRates.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        boolean estado=false;
        if(result instanceof GenericSelectorT) estado=((GenericSelectorT)result).buscar(buscar);
        else if(result instanceof TimeConfigurationT) estado=((TimeConfigurationT)result).buscar(buscar);
        else if(result instanceof CrpCompositePopModelT) estado=((CrpCompositePopModelT)result).buscar(buscar);
        String names=""; 
        for(String i: name) names+=i+" ";
        if((names).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    private ArrayList<String> listToArray(String s) {
        ArrayList<String> array = new ArrayList<>();
        s=s.replace("]","").replace("[", "");
        String[] strings = s.split(", ");
        for(String item : strings) array.add(item);
        return array;
    }
    
}
