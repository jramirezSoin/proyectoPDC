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
public class TimeConfigurationT extends Nodo implements ResultI{
    private String timeModelName="";
    private ArrayList<TagsT> tags= new ArrayList<>();
    
    public TimeConfigurationT(){}
    public TimeConfigurationT(int id){this.id=id;}

    public String getTimeModelName() {
        return timeModelName;
    }
    
    @Override
    public void clean(){
        tags.clear();
    }
    
    public void setTimeModelName(String timeModelName) {
        this.timeModelName = timeModelName;
    }

    public ArrayList<TagsT> getTags() {
        return tags;
    }

    public void setTags(ArrayList<TagsT> tags) {
        this.tags = tags;
    }
    
    @Override
    public String toString(String s) {
        String gens="";
        for(int i=0;i<this.tags.size();i++){
            gens+=this.tags.get(i).toString(s+"\t")+"\n";
        }
        return s+"<timeConfiguration>\n"+
               s+"\t<timeModelName>"+timeModelName+"</timeModelName>\n"+gens+
               s+"</timeConfiguration>";
    }

    @Override
    public int procesar(ArrayList<String> generics, int index) {
        int itemCount = 0;
        for(int i=index; i<generics.size();i++) {
            if(generics.get(i).matches("(?s)timeModelName: (.*)")) this.timeModelName= generics.get(i).substring(15);
            else if(generics.get(i).matches("(?s)tags")){ 
                
                TagsT resul = new TagsT(tags.size());
                itemCount++;
                i= resul.procesar(generics, i+1);
                i--;
                this.tags.add(resul);
            }else if(generics.get(i).matches("(?s)resultsGen")){
                i++;
                for(int k=i;i<generics.size();i++){
                    if(generics.get(i).matches("(?s)name_[0-9]{1,}: (.*)")){
                        int indexTag= Integer.parseInt(generics.get(i).split(": ")[0].replace("name_", ""));
                        this.getTags().get(indexTag).setName(generics.get(i).split(": ")[1]);
                    }else if(generics.get(i).matches("(?s)nameResult: (.*)")){
                        TagsT resul = new TagsT(tags.size());
                        resul.setName(generics.get(i).substring(12));
                        CrpCompositePopModelT popModel = new CrpCompositePopModelT(0);
                        popModel.setZoneCrp();
                        resul.setCrpCompositePopModel(popModel);
                        this.getTags().add(resul);
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
            this.tags.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(TagsT item: this.tags){
            item.buscar(buscar);
        }
        if((timeModelName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    @Override
    public void getRumCurrency(String rum, String currency) {
        for(TagsT crp : this.getTags()){
            crp.getCrpCompositePopModel().getRumCurrency(rum,currency);
        }
    }
    
    
}
