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
public class RumT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String priceListName="Default";
    private boolean obsolete=false;
    private String rumType="";
    private String rumRounding="";
    private String rumCode="";
    private String unit="";
    
    public RumT(int id){
        this.id= id;
    }

    public RumT(int id, String name, String description,String unit, String internalId, String priceListName, boolean obsolete, String rumType, String rumRounding, String rumCode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.internalId = internalId;
        this.priceListName = priceListName;
        this.obsolete = obsolete;
        this.rumType= rumType;
        this.rumRounding= rumRounding;
        this.rumCode= rumCode;
        this.unit= unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getPriceListName() {
        return priceListName;
    }

    public void setPriceListName(String priceListName) {
        this.priceListName = priceListName;
    }

    public boolean isObsolete() {
        return obsolete;
    }

    public void setObsolete(boolean obsolete) {
        this.obsolete = obsolete;
    }

    public String getRumType() {
        return rumType;
    }

    public void setRumType(String result) {
        this.rumType = result;
    }

    public String getRumRounding() {
        return rumRounding;
    }

    public void setRumRounding(String resultType) {
        this.rumRounding = resultType;
    }
    
        public String getRumCode() {
        return rumCode;
    }

    public void setRumCode(String resultType) {
        this.rumCode = resultType;
    }
    
    @Override
    public String toString() {
        return "<rumConfigurations xmlns:mtd=\"http://xmlns.oracle.com/communications/platform/model/Metadata\" xmlns:cim=\"http://xmlns.oracle.com/communications/platform/model/Config\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+ 
                "\t<name>" + name + "</name>\n"+
                "\t<description>" + description + "</description>\n"+
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<priceListName>" + priceListName + "</priceListName>\n"+
                "\t<obsolete>" + obsolete + "</obsolete>\n"+
                "\t<unit>" + unit + "</unit>\n"+
                "\t<rumType>" + rumType + "</rumType>\n"+
                "\t<rumRounding>"  + rumRounding + "</rumRounding>\n"+
                "\t<rumCode>"+rumCode+"</rumCode>\n"+
                "</rumConfigurations>";
    }
    

    @Override
    public int procesar(ArrayList<String> rums, int index, String user) {
        for(int i=index; i<rums.size();i++) {
            
            if(rums.get(i).matches("(?s)iceUpdater: (.*)")) this.iceUpdaterCount = rums.get(i).substring(12).equals("new")?1:Integer.parseInt(rums.get(i).substring(12));
            else if(rums.get(i).matches("(?s)name: (.*)")) this.name= rums.get(i).substring(6);
            else if(rums.get(i).matches("(?s)description: (.*)")) this.description= rums.get(i).substring(13);
            else if(rums.get(i).matches("(?s)internalId: (.*)")) this.internalId= rums.get(i).substring(12);
            else if(rums.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= rums.get(i).substring(15);
            else if(rums.get(i).matches("(?s)obsolete: (.*)")) this.obsolete= Boolean.valueOf(rums.get(i).substring(10));
            else if(rums.get(i).matches("(?s)rumType: (.*)")) this.rumType= rums.get(i).substring(9);
            else if(rums.get(i).matches("(?s)rumRounding: (.*)")) this.rumRounding= rums.get(i).substring(13);
            else if(rums.get(i).matches("(?s)rumCode: (.*)")) this.rumCode= rums.get(i).substring(9);
            else if(rums.get(i).matches("(?s)unit: (.*)")) this.unit= rums.get(i).substring(6);
            else return i;
        }
        return rums.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+obsolete+"/"+rumType+"/"+rumCode+"/"+rumRounding).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    @Override
    public void getPDF(Document document) {
        try {
            Paragraph preface = new Paragraph();
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("RUM: "+this.name, FirstPDF.h1));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(new Paragraph("Descripción",FirstPDF.h2));
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("nombre: ",name));
            preface.add(FirstPDF.createDescription("Descripción: ",description));
            preface.add(FirstPDF.createDescription("ID: ",internalId));
            preface.add(FirstPDF.createDescription("Nombre de lista de precio: ",priceListName));
            preface.add(FirstPDF.createDescription("Obsoleto: ",obsolete+""));
            preface.add(FirstPDF.createDescription("Tipo de RUM: ",rumType));
            preface.add(FirstPDF.createDescription("Redondeo de RUM: ",rumRounding));
            preface.add(FirstPDF.createDescription("Código de RUM: ",rumCode));
            preface.add(FirstPDF.createDescription("Unidad: ",unit));
            document.add(preface);
            
        } catch (DocumentException ex) {
            Logger.getLogger(ZoneModelT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}