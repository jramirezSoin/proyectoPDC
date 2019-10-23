/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import control.ControlFunctions;
import control.ControlPath;
import java.util.ArrayList;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import control.FirstPDF;

/**
 *
 * @author Joseph Ramírez
 */
public class ExpressionT extends Nodo{
    private String operator="";
    private String value="";
    private String balanceElementNumCode="";
    private String balanceElementName="";
    private String binaryOperator="";
    private String tipo="";

    public ExpressionT(int id, String tipo) {
        this.id=id;
        this.tipo=tipo;
    }
    
    public ExpressionT(int id) {
        this.id=id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBalanceElementNumCode() {
        return balanceElementNumCode;
    }

    public void setBalanceElementNumCode(String balanceElementNumCode) {
        this.balanceElementNumCode = balanceElementNumCode;
    }

    public String getBinaryOperator() {
        return binaryOperator;
    }

    public void setBinaryOperator(String binaryOperator) {
        this.binaryOperator = binaryOperator;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBalanceElementName() {
        return balanceElementName;
    }

    public void setBalanceElementName(String balanceElementName) {
        this.balanceElementName = balanceElementName;
    }
    
    
    
        @Override
    public int procesar(ArrayList<String> triggerSpecs2, int index) {
        ArrayList<String> triggerSpecs= (ArrayList<String>) triggerSpecs2.clone();
        for(int i=index; i<triggerSpecs.size();i++) {
            
            if(triggerSpecs.get(i).matches("(?s)operator: (.*)")) this.operator= triggerSpecs.get(i).substring(10);
            else if(triggerSpecs.get(i).matches("(?s)type: (.*)")) this.tipo= triggerSpecs.get(i).substring(6);
            else if(triggerSpecs.get(i).matches("(?s)value: (.*)")) this.value= triggerSpecs.get(i).substring(7);
            else if(triggerSpecs.get(i).matches("(?s)binaryOperator: (.*)")) this.binaryOperator= triggerSpecs.get(i).substring(16);
            else if(triggerSpecs.get(i).matches("(?s)balanceElementNumCode: (.*)")){ this.balanceElementNumCode= triggerSpecs.get(i).substring(23); this.balanceElementName=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("numericCode",triggerSpecs.get(i).substring(23)),"name");}
            else if(triggerSpecs.get(i).matches("(?s)balanceElementName: (.*)")){ this.balanceElementName= triggerSpecs.get(i).substring(20); this.balanceElementNumCode=ControlFunctions.Buscar(ControlPath.balanceElementClick, new ListaT("name",triggerSpecs.get(i).substring(20)),"numericCode");}
            else if(("leftOperand rightOperand binaryExpression chargeExpression: balanceExpression").contains(triggerSpecs.get(i))){
                
            }
            else return i;
        }
        return triggerSpecs.size();
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
            @Override
    public boolean buscar(String buscar) {
        if((tipo+"/"+operator+"/"+binaryOperator+"/"+value+"/"+balanceElementName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }

    @Override
    public String toString(String s) {
        String k=s+"<"+tipo+">\n"+
                s+"\t<operator>"+operator+"</operator>\n"+
                s+"\t<value>"+value+"</value>\n";
        
        if(tipo.equals("balanceTriggerExpression")){
            k+=s+"\t<balanceElementNumCode>"+balanceElementNumCode+"</balanceElementNumCode>\n";
        }
        if(tipo.equals("complexTriggerExpression")){
            k+= s+"\t<binaryExpression>\n"+
                s+"\t\t<leftOperand>\n"+
                s+"\t\t\t<balanceExpression>\n"+
                s+"\t\t\t\t<balanceElementNumCode>"+balanceElementNumCode+"</balanceElementNumCode>\n"+
                s+"\t\t\t</balanceExpression>\n"+
                s+"\t\t</leftOperand>\n"+
                s+"\t\t<rightOperand>\n"+
                s+"\t\t\t<chargeExpression/>\n"+
                s+"\t\t</rightOperand>\n"+
                s+"\t\t<binaryOperator>"+binaryOperator+"</binaryOperator>\n"+
                s+"\t</binaryExpression>\n";
                    
        }
        return k+s+"</"+tipo+">";
    }
    
    @Override
    public void getPDF(Element element) {
        Paragraph preface = (Paragraph) element;
        if(tipo.equals("balanceTriggerExpression")){
            preface.add(new Paragraph("Saldo",FirstPDF.normalBold));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Operador: "+operator,FirstPDF.normalFont));
            preface.add(new Paragraph("Valor: "+value,FirstPDF.normalFont));
            preface.add(new Paragraph("Saldo en: "+balanceElementName,FirstPDF.normalFont));
        }
        if(tipo.equals("complexTriggerExpression")){
            preface.add(new Paragraph("Compleja",FirstPDF.normalBold));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Operador: "+operator,FirstPDF.normalFont));
            preface.add(new Paragraph("Valor: "+value,FirstPDF.normalFont));
            preface.add(new Paragraph("Operador Izquierdo: "+balanceElementName,FirstPDF.normalFont));
            preface.add(new Paragraph("Operador Derecho: Cargo",FirstPDF.normalFont));
            preface.add(new Paragraph("Operador Binario: "+operator,FirstPDF.normalFont));
            
        }else if(tipo.equals("chargeTriggerExpression")){
            preface.add(new Paragraph("Cargo",FirstPDF.normalBold));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Operador: "+operator,FirstPDF.normalFont));
            preface.add(new Paragraph("Value: "+value,FirstPDF.normalFont));
        }
        FirstPDF.addEmptyLine(preface, 2);
    
    }
    
    
}
