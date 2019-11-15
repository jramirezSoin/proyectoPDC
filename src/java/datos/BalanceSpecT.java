/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import control.ControlFunctions;
import control.ControlPath;
import control.FirstPDF;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class BalanceSpecT extends Nodo{
    
    String balanceElementNumCode="";
    String balanceElementName="";
    String consumptionRule="NONE";           
    String floor="";
    String ceil="";
    String threshold1="";
    String threshold2="";
        
    public BalanceSpecT(int id) {
        this.id=id;
    }
    
    public BalanceSpecT() {
       
    }

    public String getBalanceElementNumCode() {
        return balanceElementNumCode;
    }

    public void setBalanceElementNumCode(String balanceElementNumCode) {
        this.balanceElementNumCode = balanceElementNumCode;
    }

    public String getBalanceElementName() {
        return balanceElementName;
    }

    public void setBalanceElementName(String balanceElementName) {
        this.balanceElementName = balanceElementName;
    }

    public String getConsumptionRule() {
        return consumptionRule;
    }

    public void setConsumptionRule(String consumptionRule) {
        this.consumptionRule = consumptionRule;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getCeil() {
        return ceil;
    }

    public void setCeil(String ceil) {
        this.ceil = ceil;
    }

    public String getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(String threshold1) {
        this.threshold1 = threshold1;
    }

    public String getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(String threshold2) {
        this.threshold2 = threshold2;
    }

    @Override
    public String toString(String s) {
        return
        s+"<balanceElementSpecification>\n" +
        s+"\t<balanceElementNumCode>"+balanceElementNumCode+"</balanceElementNumCode>\n" +
        s+"\t<consumptionRule>"+consumptionRule+"</consumptionRule>\n" +
        s+"\t<balanceCreditProfile>\n" +
        s+"\t\t<floor>"+floor+"</floor>\n" +
        s+"\t\t<ceiling>"+ceil+"</ceiling>\n" +
        ((threshold1.equals(""))?"":s+"\t\t<thresholdLevel>\n" +
        s+"\t\t\t<threshold>"+threshold1+"</threshold>\n" +
        s+"\t\t\t<type>ABSOLUTE</type>\n" +
        s+"\t\t</thresholdLevel>\n") +
        ((threshold2.equals(""))?"":s+"\t\t<thresholdLevel>\n" +
        s+"\t\t\t<threshold>"+threshold2+"</threshold>\n" +
        s+"\t\t\t<type>ABSOLUTE</type>\n" +
        s+"\t\t</thresholdLevel>\n") +
        s+"\t</balanceCreditProfile>\n" +
        s+"</balanceElementSpecification>";
    }
    
    @Override
    public int procesar(ArrayList<String> balances2, int index, String user) {
        ArrayList<String> balances = (ArrayList<String>) balances2.clone();
        
        for(int i=index; i<balances.size();i++) {
            
            if(balances.get(i).matches("(?s)threshold: (.*)")){ 
                if(threshold1.equals(""))
                    this.threshold1= balances.get(i).substring(11);
                else
                    this.threshold2= balances.get(i).substring(11);
                i++;
            }
            else if(balances.get(i).matches("(?s)threshold1: (.*)")) this.threshold1= balances.get(i).substring(11);
            else if(balances.get(i).matches("(?s)threshold2: (.*)")) this.threshold2= balances.get(i).substring(11);
            else if(balances.get(i).matches("(?s)floor: (.*)")) this.floor= balances.get(i).substring(7);
            else if(balances.get(i).matches("(?s)ceiling: (.*)")) this.ceil= balances.get(i).substring(9);
            else if(balances.get(i).matches("(?s)consumptionRule: (.*)")) this.consumptionRule= balances.get(i).substring(17);
            else if(balances.get(i).matches("(?s)balanceElementNumCode: (.*)")){ this.balanceElementNumCode= balances.get(i).substring(23); this.balanceElementName=ControlFunctions.Buscar(ControlPath.balanceElementClick,user, new ListaT("numericCode",balances.get(i).substring(23)),"name");}
            else if(balances.get(i).matches("(?s)balanceElementName: (.*)")){ this.balanceElementName= balances.get(i).substring(20); this.balanceElementNumCode=ControlFunctions.Buscar(ControlPath.balanceElementClick,user, new ListaT("name",balances.get(i).substring(20)),"numericCode");}
            else if(("balanceCreditProfile thresholdLevel").contains(balances.get(i))){}
            else return i;
        }
        return balances.size();
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((threshold1+"/"+threshold2+"/"+floor+"/"+ceil+"/"+balanceElementName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    public void getPDF(Element element){
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.balanceElementName));
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.ceil));
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.floor));
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.threshold1));
        ((PdfPTable)element).addCell(FirstPDF.createTableCell(this.threshold2));
    }
    
    
    
    
}
