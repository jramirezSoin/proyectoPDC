/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ModelDataT extends Nodo{
    
    private String name="";
    private String kind="EVENT_SPEC_FIELD";
    private String valueType="SINGLE_VALUE";
    private String defaultValue="*";
    private String operator="EQUAL_TO";
    private String tipo="Explicito";
    
    public ModelDataT(){}
    public ModelDataT(int id){this.id=id;}
    public ModelDataT(String name, String operator, String tipo){
        this.name=name;
        this.operator=operator;
        this.tipo=tipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString(String s, String aux){
        return 
            s+"<modelData>\n" +
            s+"\t<key>field_"+aux+".name</key>\n" +
            s+"\t<value>"+name+"</value>\n" +
            s+"</modelData>\n" +
            s+"<modelData>\n" +
            s+"\t<key>field_"+aux+".kind</key>\n" +
            s+"\t<value>"+kind+"</value>\n" +
            s+"</modelData>\n" +
            s+"<modelData>\n" +
            s+"\t<key>field_"+aux+".valueType</key>\n" +
            s+"\t<value>"+valueType+"</value>\n" +
            s+"</modelData>\n" +
            s+"<modelData>\n" +
            s+"\t<key>field_"+aux+".defaultValue</key>\n" +
            s+"\t<value>"+defaultValue+"</value>\n" +
            s+"</modelData>\n" +
            s+"<modelData>\n" +
            s+"\t<key>field_"+aux+".operator</key>\n" +
            s+"\t<value>"+operator+"</value>\n" +
            s+"</modelData>";
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    
}
