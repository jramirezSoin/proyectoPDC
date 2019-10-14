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
public class ImpactCategoryT extends Nodo{
    private String name="";
    private String description="";
    private String internalId="";
    private String priceListName="Default";
    private boolean obsolete=false;
    private String result="";
    private String resultType="BASE_RESULT";
    
    public ImpactCategoryT(int id){
        this.id= id;
    }

    public ImpactCategoryT(int id, String name, String description, String internalId, String priceListName, boolean obsolete, String result, String resultType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.internalId = internalId;
        this.priceListName = priceListName;
        this.obsolete = obsolete;
        this.result= result;
        this.resultType= resultType;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
    @Override
    public String toString() {
        return "<zoneResultConfiguration xmlns:mtd=\"http://xmlns.oracle.com/communications/platform/model/Metadata\" xmlns:cim=\"http://xmlns.oracle.com/communications/platform/model/Config\" xmlns:pdc=\"http://xmlns.oracle.com/communications/platform/model/pricing\">\n"+
                "\t<name>" + name + "</name>\n"+
                "\t<description>" + description + "</description>\n"+
                ((internalId.equals(""))?"":"\t<internalId>"+ internalId + "</internalId>\n")+
                "\t<priceListName>" + priceListName + "</priceListName>\n"+
                "\t<obsolete>" + obsolete + "</obsolete>\n"+
                "\t<result>" + result + "</result>\n"+
                "\t<resultType>"+resultType+"</resultType>\n"+
                "</zoneResultConfiguration>";
    }
    

    @Override
    public int procesar(ArrayList<String> impactCategories, int index) {
        for(int i=index; i<impactCategories.size();i++) {
            
            if(impactCategories.get(i).matches("(?s)name: (.*)")){ this.name= impactCategories.get(i).substring(6); this.result= impactCategories.get(i).substring(6);}
            else if(impactCategories.get(i).matches("(?s)description: (.*)")) this.description= impactCategories.get(i).substring(13);
            else if(impactCategories.get(i).matches("(?s)internalId: (.*)")) this.internalId= impactCategories.get(i).substring(12);
            else if(impactCategories.get(i).matches("(?s)priceListName: (.*)")) this.priceListName= impactCategories.get(i).substring(15);
            else if(impactCategories.get(i).matches("(?s)obsolete: (.*)")) this.obsolete= Boolean.valueOf(impactCategories.get(i).substring(10));
            else if(impactCategories.get(i).matches("(?s)resultType: (.*)")) this.resultType= impactCategories.get(i).substring(12);
            else if(impactCategories.get(i).matches("(?s)result: (.*)")) this.result= impactCategories.get(i).substring(8);
            else return i;
        }
        return impactCategories.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((name+"/"+description+"/"+internalId+"/"+priceListName+"/"+obsolete+"/"+result+"/"+resultType).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
}
