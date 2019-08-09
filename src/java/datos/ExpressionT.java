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
public class ExpressionT extends Nodo{
    private String operator="";
    private String value="";
    private String balanceElementNumCode="";
    private String binaryOperator="";
    private String tipo="";

    public ExpressionT(int id, String tipo) {
        this.id=id;
        this.tipo=tipo;
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
    
        @Override
    public int procesar(ArrayList<String> triggerSpecs, int index) {
        for(int i=index; i<triggerSpecs.size();i++) {
            System.out.println(triggerSpecs.get(i));
            if(triggerSpecs.get(i).matches("operator: (.*)")) this.operator= triggerSpecs.get(i).substring(10);
            else if(triggerSpecs.get(i).matches("value: (.*)")) this.value= triggerSpecs.get(i).substring(7);
            else if(triggerSpecs.get(i).matches("balanceElementNumCode: (.*)")) this.balanceElementNumCode= triggerSpecs.get(i).substring(22);
            else if(triggerSpecs.get(i).matches("binaryOperator: (.*)")) this.binaryOperator= triggerSpecs.get(i).substring(16);
            else if(("leftOperand rightOperand binaryExpression chargeExpression balanceExpression").contains(triggerSpecs.get(i))){
                
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
        if((tipo+"/"+operator+"/"+binaryOperator+"/"+value+"/"+balanceElementNumCode).toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
}
