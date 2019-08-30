/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ratePlan.ChargeRatePlanT;
import datos.ListaT;
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
@WebServlet(name = "ChargeRate", urlPatterns = {"/chargeRate"})
public class ChargeRate extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
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
        ArrayList<String> ChargeRate;
        HttpSession session = request.getSession();
        if(id==null){
            ChargeRate = XmlParser.Leer2(new File(ControlPath.ChargeRatePath) , ControlPath.ChargeRatePointer);
            ArrayList<ListaT> ChargeRateId = ControlFunctions.ListS2ListT(ChargeRate);
             session.setAttribute("click", ControlPath.ChargeRateClick);           
            session.setAttribute("lista", ChargeRateId);
            session.setAttribute("titulo", "Charge Rate");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.ChargeRatePath);
            session.setAttribute("actualPoint", ControlPath.ChargeRatePointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            ChargeRate= XmlParser.LeerSeleccionado(new File(ControlPath.ChargeRatePath) , Integer.parseInt(id));
            ChargeRatePlanT ChargeRateId = new ChargeRatePlanT(Integer.parseInt(id));
            ChargeRateId.procesar(ChargeRate, 1);
            session.setAttribute("principal", ChargeRateId);
            session.setAttribute("actual", "chargeRate");
            session.setAttribute("actualView", ControlPath.ChargeRateView);
            request.getRequestDispatcher(ControlPath.ChargeRateView).forward(request, response);
            
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
