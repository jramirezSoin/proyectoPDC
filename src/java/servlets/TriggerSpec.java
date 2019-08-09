/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
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
            triggerSpecs = XmlParser.Leer(new File(ControlPath.triggerSpecPath) , ControlPath.triggerSpecPointer);
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
