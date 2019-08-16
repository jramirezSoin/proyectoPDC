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
public class TimeModelTagT extends Nodo{
    public String tagName="";
    public ArrayList<TimeSpecT> timeSpecs;

    public TimeModelTagT(int id) {
        this.id=id;
        this.timeSpecs= new ArrayList<>();
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ArrayList<TimeSpecT> getTimeSpecs() {
        return timeSpecs;
    }

    public void setTimeSpecs(ArrayList<TimeSpecT> timeSpecs) {
        this.timeSpecs = timeSpecs;
    }
    
    @Override
    public int procesar(ArrayList<String> timeSpecs1, int index) {
        this.getTimeSpecs().clear();
        int itemCount = 0;
        for(int i=index; i<timeSpecs1.size();i++) {
            if(timeSpecs1.get(i).matches("(?s)tagName: (.*)")) this.tagName= timeSpecs1.get(i).substring(9);
            else if(timeSpecs1.get(i).matches("(?s)timeSpecification")){ 
                
                TimeSpecT timespec = new TimeSpecT(itemCount);
                itemCount++;
                i= timespec.procesar(timeSpecs1, i+1);
                i--;
                this.timeSpecs.add(timespec);
            }else{return i;}
        }
        return timeSpecs1.size();
    }
    
    
    @Override
    public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
        if(indexs.size()==0)
            index= this.procesar(lista, index);
        else{
            int i= indexs.get(0);
            indexs.remove(0);
            this.timeSpecs.get(i).procesarI(lista, index, indexs);
        }
        return index;
    }
    
    @Override
    public boolean buscar(String buscar) {
        boolean estado=false;
        boolean aux=false;
        for(TimeSpecT item: this.timeSpecs){
            aux= item.buscar(buscar);
            if(!estado) estado=aux;
        }
        if((tagName).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }
    
    
    @Override
    public void agregar(Nodo nodo, ArrayList<Integer> index) {
        this.timeSpecs.add((TimeSpecT) nodo);
    }
    
    @Override   
    public void eliminar(ArrayList<Integer> index) {

        this.timeSpecs.remove(((int) index.get(0)));
        for(int i=index.get(0); i<this.timeSpecs.size();i++){
            this.timeSpecs.get(i).id--;
        }
    }
    
    public void modificarMasivo(Nodo nodoI, ArrayList<Integer> indexs) {
        for(int i: indexs){
            this.timeSpecs.get(i).merge(nodoI);
        }
    }

    @Override
    public String toString() {
        String timeSpecss="";
        for(int i=0;i<this.timeSpecs.size();i++){
            timeSpecss+=this.timeSpecs.get(i).toString()+"\n";
        }
        return "        <timeModelTag>\n            <tagName>" + tagName + "</tagName>\n"+ timeSpecss + "        </timeModelTag>";
    }
    
    
    
    
    
    
    
    
    public class TimeSpecT extends Nodo{
        private String name="";
        private String description="";
        private String timeOfDay="";
        private ArrayList<ListaT> daysOfWeek;
        private ArrayList<ListaT> monthsOfYear;
        private String holiday="";
        private String daysOfMonth="";
        
        public TimeSpecT(){
            this.daysOfWeek= new ArrayList<>();
            this.monthsOfYear= new ArrayList<>();
            
        }
        
        public TimeSpecT(int id){
            this.id=id;
            this.daysOfWeek= new ArrayList<>();
            this.monthsOfYear= new ArrayList<>();
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

        public String getTimeOfDay() {
            return timeOfDay;
        }

        public void setTimeOfDay(String timeOfDay) {
            this.timeOfDay = timeOfDay;
        }

        public ArrayList<ListaT> getDaysOfWeek() {
            return daysOfWeek;
        }

        public void setDaysOfWeek(ArrayList<ListaT> daysOfWeek) {
            this.daysOfWeek = daysOfWeek;
        }

        public ArrayList<ListaT> getMonthsOfYear() {
            return monthsOfYear;
        }

        public void setMonthsOfYear(ArrayList<ListaT> monthsOfYear) {
            this.monthsOfYear = monthsOfYear;
        }

        public String getHoliday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }

        public String getDaysOfMonth() {
            return daysOfMonth;
        }

        public void setDaysOfMonth(String daysOfMonth) {
            this.daysOfMonth = daysOfMonth;
        }

        @Override
        public String toString() {
            String days="",months="",daymon="";
            if(this.daysOfWeek.size()>0){
                days="                <daysOfWeek>\n";
                for(ListaT l: this.daysOfWeek) days+="                    <day>"+l.valor+"</day>\n";
                days+="                </daysOfWeek>\n";
            }
            if(this.monthsOfYear.size()>0){
                months="                <monthsOfYear>\n";
                for(ListaT l: this.monthsOfYear) months+="                    <month>"+l.valor+"</month>\n";
                months+="                </monthsOfYear>\n";
            }
            if(!this.daysOfMonth.equals(""))
                daymon="<daysOfMonth>"+daysOfMonth+"</daysOfMonth>\n";
            return "            <timeSpecification>\n                <name>" + name + "</name>\n                <description>" + description 
                    + "</description>\n                <timeOfDay>" + timeOfDay + "</timeOfDay>\n" + days + months + "                <holiday>" + holiday+"</holiday>\n"
                    + daymon +"            </timeSpecification>";
        }
        
        
        
        @Override
    public int procesar(ArrayList<String> timeTags, int index) {
        int dayCount = 0;
        int monthCount = 0;
        boolean meses = false;
        boolean dias = false;
        for(int i=index; i<timeTags.size();i++) {
            if(timeTags.get(i).matches("(?s)name: (.*)")) this.name= timeTags.get(i).substring(6);
            else if(timeTags.get(i).matches("(?s)description: (.*)")) this.description= timeTags.get(i).substring(13);
            else if(timeTags.get(i).matches("(?s)timeOfDay: (.*)")) this.timeOfDay= timeTags.get(i).substring(11);
            else if(timeTags.get(i).matches("(?s)holiday: (.*)")) this.holiday= timeTags.get(i).substring(9);
            else if(timeTags.get(i).matches("(?s)daysOfMonth: (.*)")) this.daysOfMonth= timeTags.get(i).substring(13);
            else if(timeTags.get(i).matches("(?s)monthsOfYear")) meses=true;
            else if(timeTags.get(i).matches("(?s)daysOfWeek")) dias=true;
            else if(timeTags.get(i).matches("(?s)day: (.*)") && dias){ 
                this.daysOfWeek.add(new ListaT(timeTags.get(i).substring(5),dayCount));
                dayCount++;
            }
            else if(timeTags.get(i).matches("(?s)month: (.*)") && meses){ 
                this.monthsOfYear.add(new ListaT(timeTags.get(i).substring(7),monthCount));
                monthCount++;
            }
            else{
            return i;}
        }
        return timeTags.size();
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
        for(ListaT item: this.daysOfWeek){
            aux= item.valor.replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase());
            if(!estado) estado=aux;
        }
        for(ListaT item: this.monthsOfYear){
            aux= item.valor.replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase());
            if(!estado) estado=aux;
        }
        if((name+"/"+description+"/"+timeOfDay+"/"+holiday+"/"+daysOfMonth).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase()) || estado){
            this.visibilidad=true;
            return true;
        }else{
            this.visibilidad=false;
            return false;
        }
    }      
        
        
    }
    
}
