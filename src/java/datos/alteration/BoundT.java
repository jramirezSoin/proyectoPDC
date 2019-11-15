/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import datos.ListaT;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class BoundT extends Nodo{
    private String tipo="";
    private ListaT leftOperand= new ListaT();
    private ListaT rightOperand= new ListaT();
    private String binaryOperator="";

    public BoundT() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ListaT getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(ListaT leftOperand) {
        this.leftOperand = leftOperand;
    }

    public ListaT getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(ListaT rightOperand) {
        this.rightOperand = rightOperand;
    }

    public String getBinaryOperator() {
        return binaryOperator;
    }

    public void setBinaryOperator(String binaryOperator) {
        this.binaryOperator = binaryOperator;
    }
    
    @Override
    public String toString(String s) {
        String k="";
        if(tipo.equals("numberTBExpression")){
            k+=s+"\t\t<value>"+leftOperand.valor+"</value>\n";
        }
        else if(tipo.equals("balanceTBExpression")){
            k+=s+"\t\t<balanceElementNumCode>"+leftOperand.valor+"</balanceElementNumCode>\n";
        }
        else if(tipo.equals("binaryTBExpression")){
            k+=s+"\t\t<binaryOperator>"+binaryOperator+"</binaryOperator>"+
            s+"\t\t<leftOperand>\n"+
            s+"\t\t\t<"+leftOperand.unit+">\n"+
            s+"\t\t\t\t<"+(leftOperand.unit.equals("numberTBExpression")?"value":"balanceElementNumCode")+">"+leftOperand.valor+"</"+(leftOperand.unit.equals("numberTBExpression")?"value":"balanceElementNumCode")+">\n"+
            s+"\t\t\t</"+leftOperand.unit+">\n"+
            s+"\t\t</leftOperand>\n"+
            s+"\t\t<rightOperand>\n"+
            s+"\t\t\t<"+rightOperand.unit+">\n"+
            s+"\t\t\t\t<"+(rightOperand.unit.equals("numberTBExpression")?"value":"balanceElementNumCode")+">"+leftOperand.valor+"</"+(rightOperand.unit.equals("numberTBExpression")?"value":"balanceElementNumCode")+">\n"+
            s+"\t\t\t</"+rightOperand.unit+">\n"+
            s+"\t\t</rightOperand>\n";        
        }
        return
                s+"\t<"+tipo+">\n" +
                k+
                s+"\t</"+tipo+">";
    }
    
    @Override
    public String toString() {
        return leftOperand.valor+" "+binaryOperator+" "+rightOperand.valor;
    }
    
    @Override
    public int procesar(ArrayList<String> ratePlan2, int index, String user) {
        ArrayList<String> ratePlan= (ArrayList<String>)ratePlan2.clone();
        for(int i=index; i<ratePlan.size();i++) {
            if(ratePlan.get(i).matches("(?s)numberTBExpression")){
                this.tipo=ratePlan.get(i);
                i++;
                if(ratePlan.get(i).matches("(?s)value: (.*)")) this.leftOperand.valor= ratePlan.get(i).substring(7);
            }else if(ratePlan.get(i).matches("(?s)balanceTBExpression")){
                this.tipo=ratePlan.get(i);
                i++;
                if(ratePlan.get(i).matches("(?s)balanceElementNumCode: (.*)")) this.leftOperand.valor= ratePlan.get(i).substring(23);
            }else if(ratePlan.get(i).matches("(?s)binaryTBExpression")){
                this.tipo=ratePlan.get(i);
                i++;
                if(ratePlan.get(i).matches("(?s)binaryOperator: (.*)")) this.binaryOperator= ratePlan.get(i).substring(16);
                else if(ratePlan.get(i).matches("(?s)leftOperand")){
                    i++;
                    if(ratePlan.get(i).matches("(?s)numberTBExpression")){
                        this.leftOperand.unit=ratePlan.get(i);
                        i++;
                        if(ratePlan.get(i).matches("(?s)value: (.*)")) this.leftOperand.valor= ratePlan.get(i).substring(7);
                    }else if(ratePlan.get(i).matches("(?s)balanceTBExpression")){
                        this.leftOperand.unit=ratePlan.get(i);
                        i++;
                        if(ratePlan.get(i).matches("(?s)balanceElementNumCode: (.*)")) this.leftOperand.valor= ratePlan.get(i).substring(23);
                    }
                } 
                else if(ratePlan.get(i).matches("(?s)rightOperand")){
                    i++;
                    if(ratePlan.get(i).matches("(?s)numberTBExpression")){
                        this.rightOperand.unit=ratePlan.get(i);
                        i++;
                        if(ratePlan.get(i).matches("(?s)value: (.*)")) this.rightOperand.valor= ratePlan.get(i).substring(7);
                    }else if(ratePlan.get(i).matches("(?s)balanceTBExpression")){
                        this.rightOperand.unit=ratePlan.get(i);
                        i++;
                        if(ratePlan.get(i).matches("(?s)balanceElementNumCode: (.*)")) this.rightOperand.valor= ratePlan.get(i).substring(23);
                    }
                } 
            }else return i;
        }
        return ratePlan.size();
    }

    @Override
    public boolean buscar(String buscar) {
        if((binaryOperator+"/"+tipo+"/"+leftOperand.valor+"/"+rightOperand.valor).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    
}
