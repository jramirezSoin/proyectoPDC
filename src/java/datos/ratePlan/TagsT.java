/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import control.FirstPDF;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class TagsT extends Nodo{
    private String name="";
    private CrpCompositePopModelT crpCompositePopModel= new CrpCompositePopModelT();
    
    public TagsT(){}
    public TagsT(int id){this.id=id;}
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CrpCompositePopModelT getCrpCompositePopModel() {
        return crpCompositePopModel;
    }

    public void setCrpCompositePopModel(CrpCompositePopModelT crpCompositePopModel) {
        this.crpCompositePopModel = crpCompositePopModel;
    }
    
    @Override
    public String toString(String s) {
        return 
        s+"<tags>\n"+
        s+"\t<name>"+name+"</name>\n"+crpCompositePopModel.toString(s+"\t")+"\n"+
        s+"</tags>";
    }

    @Override
    public int procesar(ArrayList<String> chargeRates2, int index) {
        ArrayList<String> chargeRates=  (ArrayList<String>)chargeRates2.clone();
        for(int i=index; i<chargeRates.size();i++) {
            if(chargeRates.get(i).matches("(?s)name: (.*)")) this.name= chargeRates.get(i).substring(6);
            else if(chargeRates.get(i).matches("(?s)nameResult: (.*)")){ this.name=chargeRates.get(i).substring(12);
                CrpCompositePopModelT popModel = new CrpCompositePopModelT(0);
                popModel.setZoneCrp();
                this.setCrpCompositePopModel(popModel);
            }
            else if(chargeRates.get(i).matches("(?s)crpCompositePopModel")){
                CrpCompositePopModelT crpCompositePopModelado = new CrpCompositePopModelT(0);
                i= crpCompositePopModelado.procesar(chargeRates, i+1);
                i--;
                this.setCrpCompositePopModel(crpCompositePopModelado);
            }else return i;
        }
        return chargeRates.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        boolean estado=false;
        estado= crpCompositePopModel.buscar(buscar);
        if((name).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
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
            preface.add(new Paragraph("Tag: "+name,FirstPDF.normalFont));
            FirstPDF.addEmptyLine(preface, 1);
            this.crpCompositePopModel.getPDF(preface);
    }
    
}
