/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.ratePlan;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.FirstPDF;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class PriceTierRangeT extends Nodo{
    private String tipo="tierRange";
    private String upperBound="NO_MAX";
    private ArrayList<ChargeT> charges= new ArrayList<>();
    private int priceTierValidityPeriod = -1;
    
    public PriceTierRangeT(){}
    public PriceTierRangeT(int id){this.id=id;}
    public PriceTierRangeT(int id, int validity){
        this.id=id;
        this.priceTierValidityPeriod=validity;
    }
    
    @Override
    public void clean(){
        charges.clear();
    }

    PriceTierRangeT(int id,int priceTierP, String upperBound, String tipo) {
        this.id=id;
        this.tipo=tipo;
        this.priceTierValidityPeriod=priceTierP;
        this.upperBound=upperBound;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(String upperBound) {
        this.upperBound = upperBound;
    }

    public ArrayList<ChargeT> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<ChargeT> charges) {
        this.charges = charges;
    }

    public int getPriceTierValidityPeriod() {
        return priceTierValidityPeriod;
    }

    public void setPriceTierValidityPeriod(int priceTierValidityPeriod) {
        this.priceTierValidityPeriod = priceTierValidityPeriod;
    }
    
    @Override
    public String toString(String s) {
        String gens="";
        for(int i=0;i<this.charges.size();i++){
            gens+=this.charges.get(i).toString(s+"\t")+"\n";
        }
        return s+"<"+tipo+">\n"+
               s+"\t<upperBound>"+upperBound+"</upperBound>\n"+gens+
               s+"</"+tipo+">";
    }

    @Override
    public int procesar(ArrayList<String> generics, int index, String user) {
        int itemCount = 0;
        for(int i=index; i<generics.size();i++) {
            
            if(generics.get(i).matches("(?s)upperBound: (.*)")) this.upperBound= generics.get(i).substring(12);
            else if(("fixedCharge scaledCharge recurringCharge oneTimeCharge").contains(generics.get(i))){ 
                ChargeT resul = new ChargeT(charges.size());
                resul.setTipo(generics.get(i));
                itemCount++;
                i= resul.procesar(generics, i+1,user);
                i--;
                this.charges.add(resul);
            }else return i;
        }
        return generics.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.charges.get(i).procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        for(ChargeT item: this.charges){
            item.buscar(buscar);
        }
        if((upperBound).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    public void setZoneCrp(){
        tipo="priceTierRange";
        priceTierValidityPeriod = 0;
        ChargeT charge = new ChargeT(0);
        charge.setZoneCrp();
        this.charges.add(charge);
    }
    
    @Override
    public void getPDF(Element element) {
        try {
            Paragraph preface = (Paragraph) element;
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("Límite mayor: ",upperBound));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            FirstPDF.addEmptyLine(preface, 1);
            PdfPTable table = new PdfPTable(4);
            float[] columnWidths = new float[]{20f, 20f, 30f, 10f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.addCell(FirstPDF.createTableHeader("Tipo"));
            table.addCell(FirstPDF.createTableHeader("Precio"));
            table.addCell(FirstPDF.createTableHeader("Saldo en"));
            table.addCell(FirstPDF.createTableHeader("Unidad de medida"));
            for(ChargeT tier: this.charges)
                tier.getPDF(table);
            preface.add(table);
        } catch (DocumentException ex) {
        }
    }
    
    
}
