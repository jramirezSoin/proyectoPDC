/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ImpactCategoryT;
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
@WebServlet(name = "ImpactCategory", urlPatterns = {"/impactCategories"})
public class ImpactCategory extends HttpServlet {

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
        ArrayList<String> impactCategories;
        HttpSession session = request.getSession();
        if(id==null){
            impactCategories = XmlParser.Leer(new File(ControlPath.impactCategoriesPath) , ControlPath.impactCategoriesPointer);
            ArrayList<ListaT> zoneModelsId = ControlFunctions.ListS2ListT(impactCategories);
             session.setAttribute("click", ControlPath.impactCategoriesClick);           
            session.setAttribute("lista", zoneModelsId);
            session.setAttribute("titulo", "Impact Categories");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.impactCategoriesPath);
            session.setAttribute("actualPoint", ControlPath.impactCategoriesPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            impactCategories= XmlParser.LeerSeleccionado(new File(ControlPath.impactCategoriesPath) , Integer.parseInt(id));
            ImpactCategoryT impactCategory = new ImpactCategoryT(Integer.parseInt(id));
            impactCategory.procesar(impactCategories, 1);
            session.setAttribute("principal", impactCategory);
            session.setAttribute("actual", "impactCategory");
            session.setAttribute("actualView", ControlPath.impactCategoriesView);
            request.getRequestDispatcher(ControlPath.impactCategoriesView).forward(request, response);
            
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
        request.getRequestDispatcher(ControlPath.impactCategoriesForm).forward(request, response);
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
