/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.Cambio;
import datos.ListaT;
import datos.Nodo;
import datos.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xml.TxtParser;
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
        String user = ((User)request.getSession().getAttribute("user")).getUserPDC();
        nodo.procesar(lista, 0, user);
        String view= (String) request.getSession().getAttribute("addView");
        request.getSession().setAttribute("actualView", view);
        request.getSession().setAttribute("principal", nodo);
        request.getSession().setAttribute("add", null);
        String path=(String) request.getSession().getAttribute("actualPath");
        String pointer=(String) request.getSession().getAttribute("actualPoint");
        TxtParser.aniadirCambio(new Cambio("Add", 0,path.replace(ControlPath.path, "")),user,ControlPath.changes);
        XmlParser.Agregar(ControlPath.getPath(user,path), ControlPath.getPath(user,path), nodo.toString(), pointer);
        ArrayList<String> impactCategories = XmlParser.Leer2(new File(ControlPath.getPath(user,path)) , pointer);
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
