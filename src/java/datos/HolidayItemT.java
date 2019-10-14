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
public class HolidayItemT extends Nodo{
        private String name="";
        private String month="";
        private String day="";
        private String year="";
        
        public HolidayItemT(int id){
            this.id=id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        @Override
        public String toString(String s) {
            return s+"<holiday>\n"+
                    s+"\t<name>" + name + "</name>\n"+
                    s+"\t<month>" + month + "</month>\n"+
                    s+"\t<day>"+ day + "</day>\n"+
                    ((this.year.equals("")?"":s+"\t<year>" + year +"</year>\n"))+
                    s+"</holiday>";
        }
        
        @Override
        public int procesar(ArrayList<String> holidays, int index) {
            for(int i=index; i<holidays.size();i++) {
                if(holidays.get(i).matches("(?s)name: (.*)")) this.name= holidays.get(i).substring(6);
                else if(holidays.get(i).matches("(?s)month: (.*)")) this.month= holidays.get(i).substring(7);
                else if(holidays.get(i).matches("(?s)day: (.*)")) this.day= holidays.get(i).substring(5);
                else if(holidays.get(i).matches("(?s)year: (.*)")) this.year= holidays.get(i).substring(6);
                else return i;
            }
            return holidays.size();
        }


        @Override
        public int procesarI(ArrayList<String> lista, int index, ArrayList<Integer> indexs) {
            if(indexs.size()==0)
                index= this.procesar(lista, index);
            return index;
        }

        @Override
        public boolean buscar(String buscar) {
            if((name+"/"+day+"/"+month+"/"+year).replaceAll(" ", "_").toLowerCase().contains(buscar.toLowerCase())){
                this.visibilidad=true;
                return true;
            }else{
                this.visibilidad=false;
                return false;
            }
        }
        
        
    }
    
