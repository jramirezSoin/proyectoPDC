/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.Nodo;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xml.XmlParser;

/**
 *
 * @author Joseph Ramírez
 */
public class Guardar extends HttpServlet {

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
        ArrayList<String> lista = new ArrayList<>();
        String[] arrOfStr = ((String)request.getParameter("Documento")).split("\n"); 
        for (String a : arrOfStr) 
            lista.add(XmlParser.convSpecialChar(a));
        Nodo nodo = (Nodo) request.getSession().getAttribute("add");
        nodo.procesar(lista, 0);
        String view= (String) request.getSession().getAttribute("addView");
        request.getSession().setAttribute("actualView", view);
        request.getSession().setAttribute("principal", nodo);
        request.getSession().setAttribute("add", null);
        String path=(String) request.getSession().getAttribute("actualPath");
        String pointer=(String) request.getSession().getAttribute("actualPoint");
        XmlParser.Agregar(path, path, nodo.toString(), pointer);
        ArrayList<String> impactCategories = XmlParser.Leer2(new File(path) , pointer);
        ArrayList<ListaT> zoneModelsId = ControlFunctions.ListS2ListT(impactCategories);             
        request.getSession().setAttribute("lista", zoneModelsId);
        request.getRequestDispatcher(ControlPath.listView).forward(request, response);  
        
       
        

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
