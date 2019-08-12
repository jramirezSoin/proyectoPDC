/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ExpressionT;
import datos.ListaT;
import datos.TriggerSpecT;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "TriggerSpec", urlPatterns = {"/triggerSpec"})
public class TriggerSpec extends HttpServlet {

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
        processRequest(request, response);String id = request.getParameter("id");
        ArrayList<String> triggerSpecs;
        HttpSession session = request.getSession();
        if(id==null){
            triggerSpecs = XmlParser.Leer2(new File(ControlPath.triggerSpecPath) , ControlPath.triggerSpecPointer);
            ArrayList<ListaT> zoneModelsId = ControlFunctions.ListS2ListT(triggerSpecs);
             session.setAttribute("click", ControlPath.triggerSpecClick);           
            session.setAttribute("lista", zoneModelsId);
            session.setAttribute("titulo", "Trigger Specs");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.triggerSpecPath);
            session.setAttribute("actualPoint", ControlPath.triggerSpecPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            triggerSpecs= XmlParser.LeerSeleccionado(new File(ControlPath.triggerSpecPath) , Integer.parseInt(id));
            TriggerSpecT triggerSpec = new TriggerSpecT(Integer.parseInt(id));
            triggerSpec.procesar(triggerSpecs, 1);
            session.setAttribute("principal", triggerSpec);
            session.setAttribute("actual", "triggerSpec");
            session.setAttribute("actualView", ControlPath.triggerSpecView);
            request.getRequestDispatcher(ControlPath.triggerSpecView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.triggerSpecForm).forward(request, response);}
        else if(id.equals("-2")){
            TriggerSpecT triggerSpecT= new TriggerSpecT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", triggerSpecT);
            request.getSession().setAttribute("addView",ControlPath.triggerSpecView);
            request.getRequestDispatcher(ControlPath.triggerSpecForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.expressionForm).forward(request, response);}
            else if(index.get(0)==-3){
                TriggerSpecT triggerSpec = (TriggerSpecT) request.getSession().getAttribute("principal");
                ExpressionT expression = new ExpressionT(triggerSpec.getExpressions().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", expression);
                request.getSession().setAttribute("addView",ControlPath.triggerSpecView);
                request.getRequestDispatcher(ControlPath.expressionForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                TriggerSpecT triggerSpecT = new TriggerSpecT(0);
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", triggerSpecT);
                request.getRequestDispatcher(ControlPath.triggerSpecForm).forward(request, response);
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
