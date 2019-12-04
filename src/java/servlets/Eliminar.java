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
import java.text.SimpleDateFormat;
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
public class Eliminar extends HttpServlet {

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
        ArrayList<Integer> index= (ArrayList<Integer>) request.getSession().getAttribute("del");
        String path=(String) request.getSession().getAttribute("actualPath");
        String pointer=(String) request.getSession().getAttribute("actualPoint");
        request.getSession().setAttribute("del",null);
        String user = ((User)request.getSession().getAttribute("user")).getUserPDC();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(index==null || (index.size()==1 && index.get(0)==-4)){
            Nodo nodo = (Nodo) request.getSession().getAttribute("principal");
            //TxtParser.aniadirCambio(new Cambio("Delete", simpleDateFormat.format(new Date()),path.replace(ControlPath.path, "")),user);
            XmlParser.Eliminar(ControlPath.getPath(user,path), ControlPath.getPath(user,path), pointer, nodo.id);
            request.getSession().setAttribute("principal",null);
            ArrayList<String> impactCategories = XmlParser.Leer2(new File(ControlPath.getPath(user,path)) , pointer);
            ArrayList<ListaT> lista = ControlFunctions.ListS2ListT(impactCategories);             
            request.getSession().setAttribute("lista", lista);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            if(index.get(0)==-4){
                Nodo nodo = (Nodo) request.getSession().getAttribute("principal");
                index.remove(0);
                nodo.eliminar(index);
                XmlParser.Modificar(ControlPath.getPath(user,path),ControlPath.getPath(user,path), nodo.toString(),nodo.iceUpdaterCount, pointer, nodo.id);
                TxtParser.aniadirCambio(new Cambio("Update", 0,path.replace(ControlPath.path, "")),user,ControlPath.changes);
                ArrayList<String> nodos= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,path)),pointer , nodo.id);
                nodo.clean();
                nodo.procesar(nodos, 1, user);
                request.getSession().setAttribute("principal", nodo);
                request.getRequestDispatcher((String) request.getSession().getAttribute("actualView")).forward(request, response);}
            else if(index.get(0)==-6){
                index.remove(0);
                //TxtParser.aniadirCambio(new Cambio("Delete", simpleDateFormat.format(new Date()),path.replace(ControlPath.path, "")),user);
                XmlParser.EliminarMasivo(path, path, pointer, index);
                ArrayList<String> impactCategories = XmlParser.Leer2(new File(ControlPath.getPath(user,path)) , pointer);
                ArrayList<ListaT> lista = ControlFunctions.ListS2ListT(impactCategories);             
                request.getSession().setAttribute("lista", lista);
                request.getRequestDispatcher(ControlPath.listView).forward(request, response);
            }
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
