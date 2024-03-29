/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import control.FirstPDF;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class TriggerSpecT extends Nodo{
    
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="Default";
    private ArrayList<ExpressionT> expressions;
    
    public TriggerSpecT(int id) {
        this.id= id;
        this.expressions = new ArrayList<>();
    }
    
    public TriggerSpecT(int id, String name, String description, String internalId, String pricingProfileName, String priceListName){
        this.id = id;
        this.name = name;
        this.description = description;
        this.internalId = internalId;
        this.priceListName = priceListName;
        this.pricingProfileName = pricingProfileName;
        this.expressions = new ArrayList<>();        
    }
    
    @Override
    public void clean(){
        expressions.clear();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getPricingProfileName() {
        return pricingProfileName;
    }

    public void setPricingProfileName(String pricingProfileName) {
        this.pricingProfileName = pricingProfileName;
    }

    public String getPriceListName() {
        return priceListName;
    }

    public void setPriceListName(String priceListName) {
        this.priceListName = priceListName;
    }

    public ArrayList<ExpressionT> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<ExpressionT> expressions) {
        this.expressions = expressions;
    }
    
    @Override
    public int procesar(ArrayList<String> triggerSpecs2, int index, String user) {
        ArrayList<String> triggerSpecs= (ArrayList<String>)triggerSpecs2.clone();
        int itemCount = 0;
        for(int i=index; i<triggerSpecs.size();i++) {
            if(triggerSpecs.get(i).matches("(?s)iceUpdater: (.*)")) this.iceUpdaterCount = triggerSpecs.get(i).substring(12).equals("new")?1:Integer.parseInt(triggerSpecs.get(i).substring(12));
            else if(triggerSpecs.get(i).matches("(?s)name: (.*)")) this.name= triggerSpecs.get(i).substring(6);
            else if(triggerSpecs.get(i).matches("(?s)description: (.*)")) this.description= triggerSpecs.get(i).substring(13);
            else if(triggerSpecs.get(i).matches("(?s)internalId: (.*)")) this.internalId= triggerSpecs.get(i).substring(12);
            else if(triggerSpecs.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= triggerSpecs.get(i).substring(15);
            else if(triggerSpecs.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= triggerSpecs.get(i).substring(20);
            else if(("chargeTriggerExpression complexTriggerExpression quantityTriggerExpression balanceTriggerExpression").contains(triggerSpecs.get(i))){     
                ExpressionT expression = new ExpressionT(itemCount,triggerSpecs.get(i));
                itemCount++;
                i= expression.procesar(triggerSpecs, i+1, user);
                i--;
                this.expressions.add(expression);
            }else return i;
        }
        return triggerSpecs.size();
    }
    
    @Override
    public String toString() {
        String zoneItems="";
        for(int i=0;i<this.expressions.size();i++){
            zoneItems+=this.expressions.get(i).toString("\t")+"\n";
        }
        return  "<triggerSpec xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" +
                "\t<name>" + name + "</name>\n"+
                "\t<description>" + description + "</description>\n"+
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<pricingProfileName>" + pricingProfileName + "</pricingProfileName>\n"+
                "\t<priceListName>" + priceListName + "</priceListName>\n" + 
                zoneItems + 
                "</triggerSpec>";
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.expressions.get(i).procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(ExpressionT item: this.expressions){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.expressions.add((ExpressionT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.expressions.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.expressions.size();i++){
            this.expressions.get(i).id--;
        }
    }
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Trigger: "+this.name, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("nombre: ",name));
            preface.add(FirstPDF.createDescription("descripción: ",description));
            preface.add(FirstPDF.createDescription("ID: ",internalId));
            preface.add(FirstPDF.createDescription("Nombre de lista de precio: ",priceListName));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Expresiones",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            for(ExpressionT item : this.expressions){
                if(item.visibilidad){
                    item.getPDF(preface);
                }
            }
            document.add(preface);
            
        } catch (DocumentException ex) {
            Logger.getLogger(ZoneModelT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
