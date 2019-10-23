/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.alteration.AlterationConfigurationT;
import datos.alteration.AlterationRatePlanT;
import datos.alteration.ArpCompositePopModelT;
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
@WebServlet(name = "AlterationRatePlan", urlPatterns = {"/alterationRatePlan"})
public class AlterationRatePlan extends HttpServlet {

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
        String dir = request.getParameter("path");
        ArrayList<String> ChargeRate;
        HttpSession session = request.getSession();
        if(id==null){
            if(dir==null){
            ChargeRate = XmlParser.Leer2(new File(ControlPath.alterationRatePath) , ControlPath.alterationRatePointer);
            ArrayList<ListaT> ChargeRateId = ControlFunctions.ListS2ListT(ChargeRate);
             session.setAttribute("click", ControlPath.alterationRateClick);           
            session.setAttribute("lista", ChargeRateId);
            session.setAttribute("titulo", "Charge Rate");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.alterationRatePath);
            session.setAttribute("actualPoint", ControlPath.alterationRatePointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
            }else{
                AlterationConfigurationT composite = ((AlterationRatePlanT)session.getAttribute("principal")).buscaPop(dir);
                session.setAttribute("composite", composite);
                request.getRequestDispatcher(ControlPath.arpCompositeView).forward(request, response);
            }
        }else{
                ChargeRate= XmlParser.LeerSeleccionado(new File(ControlPath.alterationRatePath) , Integer.parseInt(id));
                AlterationRatePlanT ChargeRateId = new AlterationRatePlanT(Integer.parseInt(id));
                ChargeRateId.procesar(ChargeRate, 1);
                session.setAttribute("principal", ChargeRateId);
                session.setAttribute("actual", "alterationRate");
                session.setAttribute("actualView", ControlPath.alterationRateView);
                request.getRequestDispatcher(ControlPath.alterationRateView).forward(request, response);  
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
