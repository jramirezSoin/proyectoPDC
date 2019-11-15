/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import control.ControlFunctions;
import control.FirstPDF;
import datos.Nodo;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class PriceTierValidityPeriodT extends Nodo{
    private String lowerBound="";
    private String validFrom="";
    
    public PriceTierValidityPeriodT(){}
    public PriceTierValidityPeriodT(int id){this.id=id;}
    public PriceTierValidityPeriodT(int id,String lowerBound, String validFrom){
        this.id=id;
        this.lowerBound=lowerBound;
        this.validFrom=validFrom;
    }
    
    public String getLowerBound() {
        return lowerBound;
    }
    

    public void setLowerBound(String lowerBound) {
        this.lowerBound = lowerBound;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }
    
    @Override
    public String toString(String s, String aux) {
        return 
        s+"<priceTierValidityPeriod>\n" +
            s+"\t<lowerBound>"+lowerBound+"</lowerBound>\n" +
            s+"\t<validFrom>"+validFrom+"</validFrom>\n" +
                aux+s+"</priceTierValidityPeriod>";
                
    }

    @Override
    public int procesar(ArrayList<String> applicable, int index, String user) {
        for(int i=index; i<applicable.size();i++) {
            if(applicable.get(i).matches("(?s)lowerBound: (.*)")) this.lowerBound= applicable.get(i).substring(12);
            else if(applicable.get(i).matches("(?s)validFrom: (.*)")) this.validFrom= applicable.get(i).substring(11);
            else return i;
        }
        return applicable.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((lowerBound+"/"+validFrom).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    public void setZoneCrp(){
        lowerBound="NO_MIN";
        validFrom="0";
    }
    
    @Override
    public void getPDF(Element element) {
        try {
            Paragraph preface = (Paragraph) element;
            preface.add(FirstPDF.createDescription("Límite mínimo: ",lowerBound));
            preface.add(FirstPDF.createDescription("válido desde: ",ControlFunctions.getParseDate(validFrom)));
            FirstPDF.addEmptyLine(preface, 1);
        } catch (ParseException ex) {
            Logger.getLogger(PriceTierValidityPeriodT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
