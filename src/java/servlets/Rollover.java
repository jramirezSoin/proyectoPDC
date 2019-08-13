/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.RolloverT;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xml.XmlParser;

/**
 *
 * @author Joseph Ramírez
 */
public class Rollover extends HttpServlet {

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
        ArrayList<String> rollovers;
        HttpSession session = request.getSession();
        if(id==null){
            rollovers = XmlParser.Leer2(new File(ControlPath.rolloverPath) , ControlPath.rolloverPointer);
            ArrayList<ListaT> rolloverId = ControlFunctions.ListS2ListT(rollovers);
             session.setAttribute("click", ControlPath.rolloverClick);           
            session.setAttribute("lista", rolloverId);
            session.setAttribute("titulo", "Rollover");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.rolloverPath);
            session.setAttribute("actualPoint", ControlPath.rolloverPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            rollovers= XmlParser.LeerSeleccionado(new File(ControlPath.rolloverPath) , Integer.parseInt(id));
            RolloverT rollover = new RolloverT(Integer.parseInt(id));
            rollover.procesar(rollovers, 1);
            session.setAttribute("principal", rollover);
            session.setAttribute("actual", "Rollover");
            session.setAttribute("actualView", ControlPath.rolloverView);
            request.getRequestDispatcher(ControlPath.rolloverView).forward(request, response);
            
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
        request.getSession().setAttribute("index", null);
        String id= request.getParameter("id");
        if(id==null || id.equals("-1")){
            request.getSession().setAttribute("add",null);
            request.getRequestDispatcher(ControlPath.rolloverForm).forward(request, response);}
        else if(id.equals("-2")){
            RolloverT rolloverT= new RolloverT(0);
            request.getSession().setAttribute("add", rolloverT);
            request.getSession().setAttribute("addView",ControlPath.rolloverView);
            request.getRequestDispatcher(ControlPath.rolloverForm).forward(request, response);
        }else if(id.equals("-4")){
            ArrayList<Integer> indexs = new ArrayList<>();
            indexs.add(-4);
            request.getSession().setAttribute("del", indexs);
        }else{
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
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