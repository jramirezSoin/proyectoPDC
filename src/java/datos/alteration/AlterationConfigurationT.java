/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.Element;
import control.FirstPDF;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class AlterationConfigurationT extends Nodo{
    private String applicableChargeAndQuantity = "";
    private String triggerSpecName = "";
    private String chargeSelectorSpecName = "";
    private ArpCompositePopModelT arpCompositePopModel;

    public AlterationConfigurationT() {
    }
    
    public AlterationConfigurationT(int id) {
        this.id=id;
    }

    public String getApplicableChargeAndQuantity() {
        return applicableChargeAndQuantity;
    }

    public void setApplicableChargeAndQuantity(String applicableChargeAndQuantity) {
        this.applicableChargeAndQuantity = applicableChargeAndQuantity;
    }

    public String getTriggerSpecName() {
        return triggerSpecName;
    }

    public void setTriggerSpecName(String triggerSpecName) {
        this.triggerSpecName = triggerSpecName;
    }

    public String getChargeSelectorSpecName() {
        return chargeSelectorSpecName;
    }

    public void setChargeSelectorSpecName(String chargeSelectorSpecName) {
        this.chargeSelectorSpecName = chargeSelectorSpecName;
    }

    public ArpCompositePopModelT getArpCompositePopModel() {
        return arpCompositePopModel;
    }

    public void setArpCompositePopModel(ArpCompositePopModelT arpCompositePopModel) {
        this.arpCompositePopModel = arpCompositePopModel;
    }
    
    @Override
    public int procesar(ArrayList<String> ratePlan2, int index, String user) {
        ArrayList<String> ratePlan= (ArrayList<String>)ratePlan2.clone();
        for(int i=index; i<ratePlan.size();i++) {
            if(ratePlan.get(i).matches("(?s)triggerSpecName: (.*)")) this.triggerSpecName= ratePlan.get(i).substring(17);
            else if(ratePlan.get(i).matches("(?s)applicableChargeAndQuantity: (.*)")) this.applicableChargeAndQuantity= ratePlan.get(i).substring(29);
            else if(ratePlan.get(i).matches("(?s)chargeSelectorSpecName: (.*)")) this.chargeSelectorSpecName= ratePlan.get(i).substring(24);
            else if(("arpCompositePopModel").contains(ratePlan.get(i))){
                if(arpCompositePopModel==null)
                    arpCompositePopModel = new ArpCompositePopModelT(0);
                i= arpCompositePopModel.procesar(ratePlan, i+1, user);
                i--;
            }else return i;
        }
        return ratePlan.size();
    }
    
    @Override
    public String toString(String s) {
        return s+"<alterationConfiguration>\n" +
            s+"\t<applicableChargeAndQuantity>"+applicableChargeAndQuantity+"</applicableChargeAndQuantity>\n" +
            s+"\t<triggerSpecName>"+triggerSpecName+"</triggerSpecName>\n" +
            s+"\t<chargeSelectorSpecName>"+chargeSelectorSpecName+"</chargeSelectorSpecName>\n"+
            this.arpCompositePopModel.toString(s+"\t")+"\n"+
            s+"</alterationConfiguration>";    
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.arpCompositePopModel.procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((triggerSpecName+"/"+applicableChargeAndQuantity+"/"+chargeSelectorSpecName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
        @Override
    public void getPDF(Element element) {
            Paragraph preface = (Paragraph) element;
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Configuración "+this.id, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("Disparador: ",triggerSpecName));
            preface.add(FirstPDF.createDescription("Filtro: ",chargeSelectorSpecName));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            this.arpCompositePopModel.getPDF(preface);
            preface.add(line);
    }
    
    
}
