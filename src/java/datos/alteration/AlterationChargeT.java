/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.alteration;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import control.ControlFunctions;
import control.ControlPath;
import control.FirstPDF;
import datos.ListaT;
import datos.Nodo;
import datos.ratePlan.PriceValidityT;
import java.util.ArrayList;

/**
 *
 * @author Joseph Ramírez
 */
public class AlterationChargeT extends Nodo{
    
    public String tipo = "fixedAlteration";
    private String price = "0.0";
    private String unitOfMeasure = "";
    private String balanceElementNumCode = "";
    private String balanceElementName = "";
    private String alterationAppliesTo = "USER";
    private ListaT alterationBasedOn = new ListaT("","");
    private String priceType = "CONSUMPTION";
    private String incrementStep = "1";
    private String prorateLastIncrementStep = "true";
    private String glid = "";
    private String glidName = "";
    private PriceValidityT priceValidity;
    
    public AlterationChargeT() {
    }
    
    public AlterationChargeT(int id) {
        this.id= id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getBalanceElementNumCode() {
        return balanceElementNumCode;
    }

    public void setBalanceElementNumCode(String balanceElementNumCode) {
        this.balanceElementNumCode = balanceElementNumCode;
    }

    public String getAlterationAppliesTo() {
        return alterationAppliesTo;
    }

    public void setAlterationAppliesTo(String alterationAppliesTo) {
        this.alterationAppliesTo = alterationAppliesTo;
    }

    public ListaT getAlterationBasedOn() {
        return alterationBasedOn;
    }

    public void setAlterationBasedOn(ListaT alterationBasedOn) {
        this.alterationBasedOn = alterationBasedOn;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getIncrementStep() {
        return incrementStep;
    }

    public void setIncrementStep(String incrementStep) {
        this.incrementStep = incrementStep;
    }

    public String getProrateLastIncrementStep() {
        return prorateLastIncrementStep;
    }

    public void setProrateLastIncrementStep(String prorateLastIncrementStep) {
        this.prorateLastIncrementStep = prorateLastIncrementStep;
    }

    public String getGlid() {
        return glid;
    }

    public void setGlid(String glid) {
        this.glid = glid;
    }

    public PriceValidityT getPriceValidity() {
        return priceValidity;
    }

    public void setPriceValidity(PriceValidityT priceValidity) {
        this.priceValidity = priceValidity;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBalanceElementName() {
        return balanceElementName;
    }

    public void setBalanceElementName(String balanceElementName) {
        this.balanceElementName = balanceElementName;
    }

    public String getGlidName() {
        return glidName;
    }

    public void setGlidName(String glidName) {
        this.glidName = glidName;
    }
    
    
    
    
    @Override
    public int procesar(ArrayList<String> ratePlan2, int index, String user) {
        ArrayList<String> ratePlan= (ArrayList<String>)ratePlan2.clone();
        System.out.println(ratePlan2);
        for(int i=index; i<ratePlan.size();i++) {
            if(ratePlan.get(i).matches("(?s)price: (.*)")) this.price= ratePlan.get(i).substring(7);
            else if(ratePlan.get(i).matches("(?s)unitOfMeasure: (.*)")) this.unitOfMeasure= ratePlan.get(i).substring(15);
            else if(ratePlan.get(i).matches("(?s)alterationAppliesTo: (.*)")) this.alterationAppliesTo= ratePlan.get(i).substring(21);
            else if(ratePlan.get(i).matches("(?s)balanceElementNumCode: (.*)")){ this.balanceElementNumCode= ratePlan.get(i).substring(23); this.balanceElementName=ControlFunctions.Buscar(ControlPath.balanceElementClick,user, new ListaT("numericCode",ratePlan.get(i).substring(23)),"name");}
            else if(ratePlan.get(i).matches("(?s)balanceElementName: (.*)")){ this.balanceElementName= ratePlan.get(i).substring(20); this.balanceElementNumCode=ControlFunctions.Buscar(ControlPath.balanceElementClick,user, new ListaT("name",ratePlan.get(i).substring(20)),"numericCode");}
            else if(ratePlan.get(i).matches("(?s)glid: (.*)")){ this.glid= ratePlan.get(i).substring(6); this.glidName=ControlFunctions.Buscar(ControlPath.glidClick,user, new ListaT("code",ratePlan.get(i).substring(6)),"name");}
            else if(ratePlan.get(i).matches("(?s)glidName: (.*)")){ this.glidName= ratePlan.get(i).substring(10); if(!glidName.equals(""))this.glid=ControlFunctions.Buscar(ControlPath.glidClick,user, new ListaT("name",ratePlan.get(i).substring(10)),"code"); else glid="";}
            else if(ratePlan.get(i).matches("(?s)incrementStep: (.*)")) this.incrementStep= ratePlan.get(i).substring(15);
            else if(ratePlan.get(i).matches("(?s)priceType: (.*)")) this.priceType= ratePlan.get(i).substring(11);
            else if(ratePlan.get(i).matches("(?s)prorateLastIncrementStep: (.*)")) this.prorateLastIncrementStep= ratePlan.get(i).substring(26);
            else if(ratePlan.get(i).matches("(?s)alterationBasedOn")){
                i++;
                if(ratePlan.get(i).matches("(?s)quantityBasisExpression")){
                    alterationBasedOn.unit=ratePlan.get(i);
                    i++;
                    if(ratePlan.get(i).matches("(?s)useTierComponent: (.*)")){alterationBasedOn.valor= ratePlan.get(i).substring(18);}
                    if(ratePlan.get(i+1).matches("(?s)preRated: (.*)")){i++;}
                }
                else if(ratePlan.get(i).matches("(?s)chargeBasisExpression")){
                    alterationBasedOn.unit=ratePlan.get(i);
                    i++;
                    if(ratePlan.get(i).matches("(?s)useTierComponent: (.*)")){alterationBasedOn.valor= ratePlan.get(i).substring(18);}
                    if(ratePlan.get(i+1).matches("(?s)preRated: (.*)")){i++;}
                }
            }
            else if(("priceValidity").contains(ratePlan.get(i))){     
                PriceValidityT priceValidityT = new PriceValidityT(0);
                i= priceValidityT.procesar(ratePlan, i+1, user);
                i--;
                this.priceValidity=priceValidityT;
            }else return i;
        }
        return ratePlan.size();
    }
    
    @Override
    public String toString(String s) {
        return  s+"<"+tipo+">\n" +
                s+"\t<price>"+price+"</price>\n" +
                s+"\t<unitOfMeasure>"+unitOfMeasure+"</unitOfMeasure>\n" +
                s+"\t<balanceElementNumCode>"+balanceElementNumCode+"</balanceElementNumCode>\n" +
                s+"\t<alterationAppliesTo>"+alterationAppliesTo+"</alterationAppliesTo>\n" +
                s+"\t<alterationBasedOn>\n" +
                s+"\t\t<"+alterationBasedOn.unit+">\n" +
                s+"\t\t\t<useTierComponent>"+alterationBasedOn.valor+"</useTierComponent>\n" +
                ((alterationBasedOn.unit.equals("chargeBasisExpression"))?s+"\t\t\t<preRated>false</preRated>\n":"") +
                s+"\t\t</"+alterationBasedOn.unit+">\n" +
                s+"\t</alterationBasedOn>\n" +
                s+"\t<priceType>"+priceType+"</priceType>\n" +
                s+"\t<glid>"+glid+"</glid>\n" +
                s+"\t<incrementStep>"+incrementStep+"</incrementStep>\n" +
                s+"\t<prorateLastIncrementStep>"+prorateLastIncrementStep+"</prorateLastIncrementStep>\n"+
                ((this.priceValidity==null)?"":this.priceValidity.toString(s+"\t")+"\n")+
                s+"</"+tipo+">";
    }
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs, String user) {
        if(indexs.size()==0)
            index= this.procesar(lista, index, user);
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        if((tipo+"/"+price+"/"+unitOfMeasure+"/"+balanceElementNumCode+"/"+alterationAppliesTo+"/"+alterationBasedOn.valor+"/"+priceType
                +"/"+incrementStep+"/"+prorateLastIncrementStep+"/"+glid).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    @Override
    public void getPDF(Element element) {
            PdfPTable table = (PdfPTable) element;
            table.addCell(FirstPDF.createTableCell(priceType));
            table.addCell(FirstPDF.createTableCell(price));
            table.addCell(FirstPDF.createTableCell(balanceElementName));
            table.addCell(FirstPDF.createTableCell(unitOfMeasure));
            
    }
    
    
}
