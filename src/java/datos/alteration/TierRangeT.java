/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.FirstPDF;
import datos.Nodo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph Ramírez
 */
public class TierRangeT extends Nodo{
    private BoundT upperBound = new BoundT();
    private ArrayList<AlterationChargeT> charges = new ArrayList<>();

    public TierRangeT() {
    }
    
    public TierRangeT(int id) {
        this.id=id;
    }

    public BoundT getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BoundT upperBound) {
        this.upperBound = upperBound;
    }

    public ArrayList<AlterationChargeT> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<AlterationChargeT> charges) {
        this.charges = charges;
    }
    
    @Override
    public int procesar(ArrayList<String> ratePlan2, int index, String user) {
        ArrayList<String> ratePlan= (ArrayList<String>)ratePlan2.clone();
        for(int i=index; i<ratePlan.size();i++) {
            if(("upperBound").contains(ratePlan.get(i))){     
                BoundT bound = new BoundT();
                i= bound.procesar(ratePlan, i+1,user);
                i--;
                this.upperBound=bound;
            }else if(("percentAlteration fixedAlteration").contains(ratePlan.get(i))){     
                AlterationChargeT charge = new AlterationChargeT(charges.size());
                charge.tipo= ratePlan.get(i);
                i= charge.procesar(ratePlan, i+1, user);
                i--;
                this.charges.add(charge);
            }else return i;
        }
        return ratePlan.size();
    }
    
    @Override
    public String toString(String s) {
        String charges="";
        for(AlterationChargeT conf : this.charges){
            charges+=conf.toString(s+"\t")+"\n";
        }
        return  s+"<tierRange>\n"+
                s+"\t<upperBound>\n" +
                this.upperBound.toString(s+"\t")+"\n"+
                s+"\t</upperBound>\n"+
                charges+
                s+"</tierRange>";
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
        if(upperBound.buscar(buscar)){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    @Override
    public void getPDF(Element element) {
        try {
            Paragraph preface = (Paragraph) element;
            FirstPDF.addEmptyLine(preface, 1);
            preface.add(FirstPDF.createDescription("Límite mayor: ",upperBound.toString()));
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
            for(AlterationChargeT tier: this.charges)
                tier.getPDF(table);
            preface.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(TierRangeT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void addCharge() {
        if(this.charges.size()==0)
            this.charges.add(new AlterationChargeT(this.charges.size()));
        else
            this.charges.add(this.charges.get(this.charges.size()-1));
    }
    
    
    
    
}
