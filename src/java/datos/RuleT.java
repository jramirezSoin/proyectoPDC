/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Joseph Ramírez
 */
public class RuleT extends Nodo{
    private int validityPeriod = -1;
    private String fieldKind="EVENT_SPEC_FIELD";
    private String seperator=";";
    private String name="";
    private String resultName="";
    private String event="";
    private HashMap<String, ListaT> models= new HashMap<>(); 
    
    public RuleT(){}
    public RuleT(int id, int validityPeriod, String event){
        this.id=id;
        this.validityPeriod=validityPeriod;
        this.event= event;
    }
    
    @Override
    public void clean(){
        models.clear();
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(int validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getFieldKind() {
        return fieldKind;
    }

    public void setFieldKind(String fieldKind) {
        this.fieldKind = fieldKind;
    }

    public String getSeperator() {
        return seperator;
    }

    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public HashMap<String, ListaT> getModels() {
        return models;
    }

    public void setModels(HashMap<String, ListaT> models) {
        this.models = models;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    
    
    
    public String toString(String s){
        String fieldExpressions="";
        for(String m : this.models.keySet()){
            ListaT l= this.models.get(m);
            if(l.id==1){
            fieldExpressions+= 
            s+"\t<fieldToValueExpression>\n" +
            s+"\t\t<operation>"+l.unit+"</operation>\n" +
            s+"\t\t<seperator>"+seperator+"</seperator>\n" +
            s+"\t\t<fieldName>"+event+"."+m+"</fieldName>\n" +
            s+"\t\t<fieldValue>"+l.valor+"</fieldValue>\n" +
            s+"\t\t<fieldKind>"+fieldKind+"</fieldKind>\n" +
            s+"\t</fieldToValueExpression>\n";}
            else if(l.id==2){
            fieldExpressions+=s+"\t<crSelectorExpression>\n" +
            s+"\t\t<rightOperand>"+m+"</rightOperand>\n" +
            s+"\t\t<operator>"+l.unit+"</operator>\n" +
            s+"\t</crSelectorExpression>\t";
            }
            
        }
        return  s+"<rule>\n" +
                s+"\t<name>"+name+"</name>\n" +
                s+"\t<result>\n" +
                s+"\t\t<resultName>"+resultName+"</resultName>\n" +
                s+"\t</result>\n" +
                fieldExpressions+
                s+"</rule>";
    }
    
    @Override
    public int procesar(ArrayList<String> generics, int index) {
        for(int i=index; i<generics.size();i++) {
            if(generics.get(i).matches("(?s)name: (.*)")) this.name= generics.get(i).substring(6);
            else if(generics.get(i).matches("(?s)result")){
                i++;
                if(generics.get(i).matches("(?s)resultName: .*")) this.resultName= generics.get(i).substring(12);
                else return i;
            }
            else if(generics.get(i).matches("(?s)fieldToValueExpression")){
                i++;
                String fieldValue="",operation="",fieldName="";
                for(int k=i;i<generics.size();i++){
                    if(generics.get(i).matches("(?s)operation: (.*)")) operation= generics.get(i).substring(11);
                    else if(generics.get(i).matches("(?s)seperator: (.*)")) this.seperator= generics.get(i).substring(11);
                    else if(generics.get(i).matches("(?s)fieldValue: (.*)")) fieldValue= generics.get(i).substring(12);
                    else if(generics.get(i).matches("(?s)fieldName: "+event+"(.*)")) fieldName= generics.get(i).substring(11+event.length()+1);
                    else if(generics.get(i).matches("(?s)fieldKind: (.*)")) this.fieldKind= generics.get(i).substring(11);
                    else{i--; break;} 
                }
                models.put(fieldName, new ListaT(1,operation,fieldValue));
            }
            else if(generics.get(i).matches("(?s)crSelectorExpression")){
                i++;
                String operation="",fieldName="";
                for(int k=i;i<generics.size();i++){
                    if(generics.get(i).matches("(?s)operator: (.*)")) operation= generics.get(i).substring(10);
                    else if(generics.get(i).matches("(?s)rightOperand: (.*)")) fieldName= generics.get(i).substring(14);
                    else{i--; break;} 
                }
                models.put(fieldName, new ListaT(2,operation,""));
            }
            else{return i;}
        }
        return generics.size();
    }
    
     @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0){
            System.out.println(lista);
            System.out.println(index);
            index= this.procesar(lista, index);}
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        String values="";
        for(String s: this.models.keySet()){
            ListaT l = this.models.get(s);
            values+=s+"/"+l.valor+"/"+l.unit+"/";
        }
        if((name+"/"+resultName+"/"+values).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    public void addModels(HashMap<String, ModelDataT> modelDatas) {
        for(String s: modelDatas.keySet()){
            ModelDataT model= modelDatas.get(s);
            this.models.put(s, new ListaT(((model.getTipo().equals("Custom"))?2:1),model.getOperator(),"")); 
        }
    }
}
