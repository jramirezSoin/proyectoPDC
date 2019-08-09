/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import datos.Nodo;
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
public class Modificador extends HttpServlet {

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
        ArrayList<String> lista = new ArrayList<>();
        String[] arrOfStr = ((String)request.getParameter("Documento")).split("\n"); 
        for (String a : arrOfStr) 
            lista.add(a);
        ArrayList<Integer> indexs= (ArrayList<Integer>) request.getSession().getAttribute("index");
        Nodo nodo = (Nodo) request.getSession().getAttribute("principal");
        if(indexs==null || indexs.size()==0)
            nodo.procesar(lista, 0);
        else if(indexs.get(0)>=0)
            nodo.procesarI(lista,0,indexs);
        else if(indexs.get(0)==-3){
            Nodo nodoI = (Nodo) request.getSession().getAttribute("add");
            nodoI.procesar(lista, 0);
            nodo.agregar(nodoI, indexs);
        }
        request.getSession().setAttribute("principal", nodo);
        String path=(String) request.getSession().getAttribute("actualPath");
        String pointer=(String) request.getSession().getAttribute("actualPoint");
        XmlParser.Modificar(path, path, nodo.toString(), pointer, nodo.id);
        request.getRequestDispatcher((String) request.getSession().getAttribute("actualView")).forward(request, response);
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
