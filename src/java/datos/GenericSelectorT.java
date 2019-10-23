/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.FirstPDF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class GenericSelectorT extends Nodo{
    
    private String name="";
    private String description="";
    private String internalId="";
    private String pricingProfileName="";
    private String priceListName="Default";
    private String stereoType="";
    private String eventSpecName="";
    private String customerSpecName="";
    private String productSpecName="";
    ArrayList<String> validityPeriods = new ArrayList<>();
    ArrayList<RuleT> rules = new ArrayList<>();
    HashMap<String,ModelDataT> modelDatas = new HashMap<>();
    
    public GenericSelectorT(){
    }
    public GenericSelectorT(int id){
        this.id=id;
    }
    
    @Override
    public void clean(){
        validityPeriods.clear();
        rules.clear();
        modelDatas.clear();
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

    public String getStereoType() {
        return stereoType;
    }

    public void setStereoType(String stereoType) {
        this.stereoType = stereoType;
    }

    public String getEventSpecName() {
        return eventSpecName;
    }

    public void setEventSpecName(String eventSpecName) {
        this.eventSpecName = eventSpecName;
    }

    public String getCustomerSpecName() {
        return customerSpecName;
    }

    public void setCustomerSpecName(String customerSpecName) {
        this.customerSpecName = customerSpecName;
    }

    public String getProductSpecName() {
        return productSpecName;
    }

    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    public ArrayList<String> getValidityPeriods() {
        return validityPeriods;
    }

    public void setValidityPeriods(ArrayList<String> validityPeriods) {
        this.validityPeriods = validityPeriods;
    }

    public ArrayList<RuleT> getRules() {
        return rules;
    }

    public void setRules(ArrayList<RuleT> rules) {
        this.rules = rules;
    }

    public HashMap<String,ModelDataT> getModelDatas() {
        return modelDatas;
    }

    public void setModelDatas(HashMap<String,ModelDataT> modelDatas) {
        this.modelDatas = modelDatas;
    }
    
    @Override
    public String toString() {
        String validities="";
        for(int i=0; i<this.validityPeriods.size();i++){
            validities+="\t<validityPeriod>\n";
            validities+="\t\t<validFrom>"+validityPeriods.get(i)+"</validFrom>\n";
            for(RuleT rule: this.rules){
                if(rule.getValidityPeriod()==i){
                    validities+= rule.toString("\t\t")+"\n";
                }
            }
            validities+="\t</validityPeriod>\n";
        }
        if(this.modelDatas.size()!=0){
            String validitiesAux="";
            int c=0;
            for(String s: this.modelDatas.keySet()){
                ModelDataT m = this.modelDatas.get(s);
                if(m.getOperator().equals("EQUAL_TO")){
                    validitiesAux+=m.toString("\t", c+"")+"\n";
                    c++;
                }
            }
            if(c!=0){
                validities+="\t<modelData>\n" +
                "\t\t<key>FIELD_COUNT</key>\n" +
                "\t\t<value>"+c+"</value>\n" +
                "\t</modelData>\n";
                validities+=validitiesAux;
            }
        }
        return  "<genericSelector xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n" +
                "\t<name>"+name+"</name>\n" +
                "\t<description>"+description+"</description>\n" +
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<pricingProfileName>"+pricingProfileName+"</pricingProfileName>\n" +
                "\t<priceListName>"+priceListName+"</priceListName>\n" +
                "\t<stereoType>"+stereoType+"</stereoType>\n" +
                "\t<eventSpecName>"+eventSpecName+"</eventSpecName>\n" +
                "\t"+((customerSpecName.equals(""))?"<productSpecName>" + productSpecName + "</productSpecName>":"<customerSpecName>" + customerSpecName + "</customerSpecName>")+"\n"+    
                validities+        
                "</genericSelector>";
    }
    
    
    
    
    @Override
    public int procesar(ArrayList<String> generics, int index) {
        ArrayList<ModelDataT> models = new ArrayList<>();
        for(int i=index; i<generics.size();i++) {
            
            if(generics.get(i).matches("(?s)name: (.*)")) this.name= generics.get(i).substring(6);
            else if(generics.get(i).matches("(?s)description: (.*)")) this.description= generics.get(i).substring(13);
            else if(generics.get(i).matches("(?s)internalId: (.*)")) this.internalId= generics.get(i).substring(12);
            else if(generics.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= generics.get(i).substring(15);
            else if(generics.get(i).matches("(?s)pricingProfileName: (.*)")) this.pricingProfileName= generics.get(i).substring(20);
            else if(generics.get(i).matches("(?s)stereoType: (.*)")) this.stereoType= generics.get(i).substring(12);
            else if(generics.get(i).matches("(?s)eventSpecName: (.*)")) this.eventSpecName= generics.get(i).substring(15);
            else if(generics.get(i).matches("(?s)customerSpecName: (.*)")) this.customerSpecName= generics.get(i).substring(18);
            else if(generics.get(i).matches("(?s)productSpecName: (.*)")) this.productSpecName= generics.get(i).substring(17);
            else if(generics.get(i).matches("(?s)aplicable: (.*)")){
                if(generics.get(i).substring(11).equals("Account")){this.customerSpecName="Account"; this.productSpecName="";}
                else{this.productSpecName=generics.get(i).substring(11); this.customerSpecName="";}
            }
            else if(generics.get(i).matches("(?s)validityPeriod")){ 
                i++;
                if(generics.get(i).matches("(?s)validFrom: .*"))
                    this.validityPeriods.add(generics.get(i).substring(11));
                else return i;
            }else if(generics.get(i).matches("(?s)rule")){ 
                RuleT rule = new RuleT(rules.size(), validityPeriods.size()-1, eventSpecName);
                i= rule.procesar(generics, i+1);
                i--;
                this.rules.add(rule);
            }else if(generics.get(i).matches("(?s)modelData")){ 
                if(generics.get(i+1).matches("(?s)key: FIELD_COUNT")){for(int k=0; k<Integer.parseInt(generics.get(i+2).substring(7));k++) models.add(new ModelDataT(k));}
                else if(generics.get(i+1).matches("(?s)key: field_[0-9]+\\..*")){
                    int indexField= Integer.parseInt(generics.get(i+1).replace("key: field_","").split("\\.")[0]);
                    String type= generics.get(i+1).split("\\.")[1];
                    switch(type){
                        case "name": models.get(indexField).setName(generics.get(i+2).substring(7));
                        case "kind": models.get(indexField).setKind(generics.get(i+2).substring(7));
                        case "valueType": models.get(indexField).setValueType(generics.get(i+2).substring(7));
                        case "defaultValue": models.get(indexField).setDefaultValue(generics.get(i+2).substring(7));
                        case "operator": models.get(indexField).setOperator(generics.get(i+2).substring(7));
                    }
                }
                i+=2;
            }
            else{getModelDataRules(models); return i;}
        }
        getModelDataRules(models);
        return generics.size();
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            if(i==0 && indexs.size()>0 && indexs.get(0)==-1){
                
            }
            else{
            this.rules.get(i).procesarI(lista, index, indexs);
            }
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(RuleT item: this.rules){
            item.buscar(buscar);
        }
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+pricingProfileName+"/"+pricingProfileName+"/"+stereoType+"/"+customerSpecName+"/"+productSpecName+"/"+eventSpecName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        RuleT rule = (RuleT) nodo;
        rule.setValidityPeriod(index.get(0));
        rule.setEvent(this.eventSpecName);
        this.rules.add(rule);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.rules.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.rules.size();i++){
            this.rules.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.rules.get(i).merge(nodoI);
        }
    }
    
    public void getModelDataRules(ArrayList<ModelDataT> models){
        for(RuleT rule : rules){
            Set<String> values= rule.getModels().keySet();
            for(String value : values){
                modelDatas.put(value, new ModelDataT(value, rule.getModels().get(value).unit,((rule.getModels().get(value).id==1)?"Implicito":"Custom")));
            }
        }
        for(ModelDataT model : models){
            ModelDataT m = modelDatas.get(model.getName());
            m.setOperator(model.getOperator());
            m.setDefaultValue(model.getDefaultValue());
            m.setValueType(model.getValueType());
        }
    }
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Zone Model: "+this.name, FirstPDF.titleFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.subFont));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("nombre: "+name,FirstPDF.normalFont));
            preface.add(new Paragraph("Descripción: "+description,FirstPDF.normalFont));
            preface.add(new Paragraph("ID: "+internalId,FirstPDF.normalFont));
            preface.add(new Paragraph("Nombre de lista de precio: "+priceListName,FirstPDF.normalFont));
            if(!productSpecName.equals(""))
            preface.add(new Paragraph("Producto: "+productSpecName,FirstPDF.normalFont));
            else
            preface.add(new Paragraph("Cliente: "+customerSpecName,FirstPDF.normalFont));    
            preface.add(new Paragraph("Evento: "+eventSpecName,FirstPDF.normalFont));
            preface.add(new Paragraph("Estereotipo: "+stereoType,FirstPDF.normalFont));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            preface.add(line);
            FirstPDF.addEmptyLine(preface, 1);
            int i=0;
            for(String item : this.validityPeriods){
                preface.add(new Paragraph("Periodo: "+item,FirstPDF.subFont));
                FirstPDF.addEmptyLine(preface, 1);
                for(RuleT rule: this.rules){
                    if(rule.visibilidad && rule.getValidityPeriod()==i){
                        rule.getPDF(preface);
                    }
                }
                i++;
            }
            document.add(preface);
            
        } catch (DocumentException ex) {
            Logger.getLogger(ZoneModelT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
