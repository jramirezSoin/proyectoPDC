/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;
import control.FirstPDF;
import datos.ListaT;
import datos.Nodo;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class ArpCompositePopModelT extends Nodo{
    
    private String name="";
    private String distributionMethod = "";
    private ListaT tierBasis= new ListaT("","");
    private BoundT lowerBound= new BoundT();
    private ArrayList<TierRangeT> tierRange= new ArrayList<>();

    public ArpCompositePopModelT() {
    }
    
    public ArpCompositePopModelT(int id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistributionMethod() {
        return distributionMethod;
    }

    public void setDistributionMethod(String distributionMethod) {
        this.distributionMethod = distributionMethod;
    }

    public ListaT getTierBasis() {
        return tierBasis;
    }

    public void setTierBasis(ListaT tierBasis) {
        this.tierBasis = tierBasis;
    }

    public BoundT getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BoundT lowerBound) {
        this.lowerBound = lowerBound;
    }

    public ArrayList<TierRangeT> getTierRange() {
        return tierRange;
    }

    public void setTierRange(ArrayList<TierRangeT> tierRange) {
        this.tierRange = tierRange;
    }
    
    @Override
    public int procesar(ArrayList<String> ratePlan2, int index, String user) {
        ArrayList<String> ratePlan= (ArrayList<String>)ratePlan2.clone();
        boolean alterationPopModel=false, priceTier= false;
        for(int i=index; i<ratePlan.size();i++) {
            if(ratePlan.get(i).matches("(?s)name: (.*)")) this.name= ratePlan.get(i).substring(6);
            else if(("alterationPopModel").contains(ratePlan.get(i))){alterationPopModel=true;}
            else if(("priceTier").contains(ratePlan.get(i)) && alterationPopModel){priceTier=true;}
            else if(ratePlan.get(i).matches("(?s)distributionMethod: (.*)") && priceTier) this.distributionMethod= ratePlan.get(i).substring(20);
            else if(ratePlan.get(i).matches("(?s)tierBasis") && priceTier){
                i++;
                if(ratePlan.get(i).matches("(?s)quantityTierExpression")){
                    tierBasis.unit=ratePlan.get(i);
                    i++;
                    if(ratePlan.get(i).matches("(?s)useTierComponent: (.*)")){tierBasis.valor= ratePlan.get(i).substring(18);}
                }
                else if(ratePlan.get(i).matches("(?s)chargeTierExpression")){
                    tierBasis.unit=ratePlan.get(i);
                    i++;
                    if(ratePlan.get(i).matches("(?s)useTierComponent: (.*)")){tierBasis.valor= ratePlan.get(i).substring(18);}
                    if(ratePlan.get(i+1).matches("(?s)preRated: (.*)")){i++;}
                }
                else if(ratePlan.get(i).matches("(?s)numberTierExpression")){
                    tierBasis.unit=ratePlan.get(i);
                    i++;
                    if(ratePlan.get(i).matches("(?s)value: (.*)")){tierBasis.valor= ratePlan.get(i).substring(7);}
                }
            }
            else if(("lowerBound").contains(ratePlan.get(i)) && priceTier){     
                BoundT bound = new BoundT();
                i= bound.procesar(ratePlan, i+1, user);
                i--;
                this.lowerBound=bound;
            }else if(("tierRange").contains(ratePlan.get(i)) && priceTier){     
                TierRangeT tierRange = new TierRangeT();
                i= tierRange.procesar(ratePlan, i+1, user);
                i--;
                this.tierRange.add(tierRange);
            }else return i;
        }
        return ratePlan.size();
    }
    
    @Override
    public String toString(String s) {
        String k="";
        if(tierBasis.unit.equals("numberTierExpression")){
            k+=s+"\t\t\t\t\t<value>"+tierBasis.valor+"</value>\n";
        }
        else if(tierBasis.unit.equals("chargeTierExpression")){
            k+=s+"\t\t\t\t\t<useTierComponent>"+tierBasis.valor+"</useTierComponent>\n";
            k+=s+"\t\t\t\t\t<preRated>false</preRated>\n";
        }
        else if(tierBasis.unit.equals("quantityTierExpression")){
            k+=s+"\t\t\t\t\t<useTierComponent>"+tierBasis.valor+"</useTierComponent>\n";        
        }
        String priceTiers="";
        for(TierRangeT conf : this.tierRange){
            priceTiers+=conf.toString(s+"\t\t\t")+"\n";
        }
        return s+"<arpCompositePopModel>\n" +
                s+"\t<alterationPopModel>\n" +
                s+"\t\t<priceTier>\n" +
                s+"\t\t\t<distributionMethod>"+distributionMethod+"</distributionMethod>\n" +
                s+"\t\t\t<tierBasis>\n" +
                s+"\t\t\t\t<"+tierBasis.unit+">\n" +
                k+
                s+"\t\t\t\t</"+tierBasis.unit+">\n" +
                s+"\t\t\t</tierBasis>\n" +
                s+"\t\t\t<lowerBound>\n" +
                this.lowerBound.toString(s+"\t\t\t")+"\n"+
                s+"\t\t\t</lowerBound>\n" +
                priceTiers+
                s+"\t\t</priceTier>\n"+
                s+"\t<alterationPopModel>\n"+
                s+"</arpCompositePopModel>";
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.tierRange.get(i).procesarI(lista, index, indexs, user);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if(("").replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
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
            preface.add(FirstPDF.createDescription("Límite menor: ",lowerBound.toString()));
            FirstPDF.addEmptyLine(preface, 1);
            LineSeparator line = new LineSeparator();              
            FirstPDF.addEmptyLine(preface, 1);
            for(TierRangeT tier: this.tierRange)
                tier.getPDF(preface);
            preface.add(line);
    }
    
    
    
}
