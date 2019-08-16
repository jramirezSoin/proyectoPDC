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
public class Buscador extends HttpServlet {

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
        String buscar= request.getParameter("buscar");
        buscar= buscar.replaceAll(" ", "_");
        if(((String)request.getParameter("tipo")).equals("Lista")){
            ArrayList<Nodo> lista= buscarLista(buscar,(ArrayList<Nodo>) request.getSession().getAttribute("lista")); 
            request.getSession().setAttribute("lista", lista);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);}
        else{
            Nodo nodo = (Nodo) request.getSession().getAttribute("principal");
            nodo.buscar(buscar);
            request.getSession().setAttribute("principal", nodo);
            request.getRequestDispatcher((String) request.getSession().getAttribute("actualView")).forward(request, response);
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
    
    public ArrayList<Nodo> buscarLista(String buscar, ArrayList<Nodo> lista){
        for(Nodo nodo : lista ){
            nodo.buscar(buscar);
        }
        return lista;
    }

}
