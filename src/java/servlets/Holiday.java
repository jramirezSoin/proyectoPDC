/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.HolidayT;
import datos.HolidayItemT;
import datos.ListaT;
import datos.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xml.XmlParser;

/**
 *
 * @author Joseph Ramírez
 */
@WebServlet(name = "Holiday", urlPatterns = {"/holiday"})
public class Holiday extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String id = request.getParameter("id");
        ArrayList<String> holidays;
        HttpSession session = request.getSession();
        String user = ((User)session.getAttribute("user")).getUserPDC();
        if(id==null){
            holidays = XmlParser.Leer2(new File(ControlPath.getPath(user,ControlPath.holidayPath)) , ControlPath.holidayPointer);
            ArrayList<ListaT> holidayId = ControlFunctions.ListS2ListT(holidays);
             session.setAttribute("click", ControlPath.holidayClick);           
            session.setAttribute("lista", holidayId);
            session.setAttribute("titulo", "Holiday Calendars");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.holidayPath);
            session.setAttribute("actualPoint", ControlPath.holidayPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            holidays= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,ControlPath.holidayPath)) , Integer.parseInt(id));
            HolidayT holiday = new HolidayT(Integer.parseInt(id));
            holiday.procesar(holidays, 1, user);
            session.setAttribute("principal", holiday);
            session.setAttribute("actual", "holidayCalendar");
            session.setAttribute("actualView", ControlPath.holidayView);
            request.getRequestDispatcher(ControlPath.holidayView).forward(request, response);
            
        }
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String id = request.getParameter("id");
        
        if(id==null || id.equals("-1")){
            request.getSession().setAttribute("add",null);
            request.getSession().setAttribute("index",null);
            request.getRequestDispatcher(ControlPath.holidayForm).forward(request, response);}
        else if(id.equals("-2")){
            HolidayT holidayT= new HolidayT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", holidayT);
            request.getSession().setAttribute("addView",ControlPath.holidayView);
            request.getRequestDispatcher(ControlPath.holidayForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.holidayItemForm).forward(request, response);}
            else if(index.get(0)==-3){
                HolidayT holiday = (HolidayT) request.getSession().getAttribute("principal");
                HolidayItemT holidayItemT = new HolidayItemT(holiday.getHolidayItems().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", holidayItemT);
                request.getSession().setAttribute("addView",ControlPath.holidayView);
                request.getRequestDispatcher(ControlPath.holidayItemForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                HolidayItemT holidayItemT = new HolidayItemT(0);
                holidayItemT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", holidayItemT);
                request.getRequestDispatcher(ControlPath.holidayItemForm).forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
