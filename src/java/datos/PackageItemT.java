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
public class PackageItemT extends Nodo{
    private String productSpecName="";
    private String specName="CustomerPackage";
    private String balanceSpecificationName="Account Balance Group";
    private ArrayList<ListaT> bundleProductOffering;
    
    
    public PackageItemT(int id) {
        this.id=id;
        bundleProductOffering = new ArrayList<>();
    }
    
    @Override
    public void clean(){
        bundleProductOffering.clear();
    }
    
    public PackageItemT() {
        bundleProductOffering = new ArrayList<>();
    }

    public String getProductSpecName() {
        return productSpecName;
    }

    public void setProductSpecName(String productSpecName) {
        this.productSpecName = productSpecName;
    }

    public String getBalanceSpecificationName() {
        return balanceSpecificationName;
    }

    public void setBalanceSpecificationName(String balanceSpecificationName) {
        this.balanceSpecificationName = balanceSpecificationName;
    }

    public ArrayList<ListaT> getBundleProductOffering() {
        return bundleProductOffering;
    }

    public void setBundleProductOffering(ArrayList<ListaT> bundleProductOffering) {
        this.bundleProductOffering = bundleProductOffering;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
    
    

    @Override
    public String toString(String s) {
        String bundles="";
        for(ListaT item: bundleProductOffering){
            bundles+=   s+"\t<bundledProductOfferingAssociation>\n"+
                        s+"\t\t<optional>"+((item.id>2)?"true":"false")+"</optional>\n"+
                        s+"\t\t<cancelWithService>"+((item.id==1)?"true":"false")+"</cancelWithService>\n"+
                        s+"\t\t<bundledProductOfferingName>"+item.valor+"</bundledProductOfferingName>\n"+
                        s+"\t</bundledProductOfferingAssociation>\n";
        }
        return 
        s+"<"+((productSpecName.equals(""))?"customer":"product")+"SpecPackageItem>\n"+
        ((productSpecName.equals(""))?"":s+"\t<name>"+productSpecName+"</name>\n")+
        s+"\t<balanceSpecificationName>Account Balance Group</balanceSpecificationName>\n"+
        ((productSpecName.equals(""))?"":s+"\t<productSpecName>"+productSpecName+"</productSpecName>\n")+
        bundles+
        s+"</"+((productSpecName.equals(""))?"customer":"product")+"SpecPackageItem>";
    }
    
    @Override
    public int procesar(ArrayList<String> packs2, int index) {
        bundleProductOffering.clear();
        ArrayList<String> packs= (ArrayList<String>) packs2.clone();
        for(int i=index; i<packs.size();i++) {
            
            if(packs.get(i).matches("(?s)name: (.*)")){ 
                if(!packs.get(i).substring(6).equals("Account")){
                    this.productSpecName= packs.get(i).substring(6);
                    this.specName="ProductPackage";}
                else{
                    this.specName="CustomerPackage";
                }
            }
            else if(packs.get(i).matches("(?s)productSpecName: (.*)")){ this.productSpecName= packs.get(i).substring(17);
                this.specName="ProductPackage";
            }
            else if(packs.get(i).matches("(?s)balanceSpecificationName: (.*)")) this.balanceSpecificationName= packs.get(i).substring(26);
            else if(("bundledProductOfferingAssociation").contains(packs.get(i))){
                ListaT item= new ListaT();
                if(packs.get(i+1).matches("(?s)bundledProductOfferingName: (.*)")){
                        item.valor= packs.get(i+1).substring(28);
                if(packs.get(i+2).matches("(?s)tipo: (.*)"))
                    item.unit=packs.get(i+2).substring(6);
                    item.id=((item.unit.equals("Optional"))?3:((item.unit.equals("Cancel with service"))?1:2));
                    i+=2;
                }
                else if(packs.get(i+1).matches("(?s)optional: (.*)")){
                    item.id= ((packs.get(i+1).substring(10).equals("false"))?1:3);
                    if(packs.get(i+2).matches("(?s)cancelWithService: (.*)"))
                        item.id= ((packs.get(i+2).substring(19).equals("false") && item.id==1)?2:((packs.get(i+2).substring(19).equals("false") && item.id==3)?3:1));
                        item.unit=((item.id==3)?"Optional":((item.id==1)?"Cancel with service":"Cancel without service"));
                    if(packs.get(i+3).matches("(?s)bundledProductOfferingName: (.*)"))
                        item.valor= packs.get(i+3).substring(28);
                    i+=3;
                }
                this.bundleProductOffering.add(item);
            }else return i;
        }
        return packs.size();
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
        boolean aux=false;
        for(ListaT item: this.bundleProductOffering){
            aux=item.buscar(buscar);
            if(!estado)estado=aux;
        }
        if((productSpecName+"/"+specName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    
}
