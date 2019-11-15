/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


import control.ControlPath;
import datos.Cambio;
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
            lista.add(XmlParser.convSpecialChar(a));
        ArrayList<Integer> indexs= (ArrayList<Integer>) request.getSession().getAttribute("index");
        Nodo nodo = (Nodo) request.getSession().getAttribute("principal");
        String user = ((User)request.getSession().getAttribute("user")).getUserPDC();
        if(indexs==null || indexs.size()==0)
            nodo.procesar(lista, 0,user);
        else if(indexs.get(0)>=0)
            nodo.procesarI(lista,0,indexs,user);
        else if(indexs.get(0)==-3){
            Nodo nodoI = (Nodo) request.getSession().getAttribute("add");
            indexs.remove(0);
            nodoI.procesar(lista, 0,user);
            nodo.agregar(nodoI, indexs);
        }
        else if(indexs.get(0)==-5){
            Nodo nodoI = (Nodo) request.getSession().getAttribute("add");
            nodoI.procesar(lista, 0,user);
            indexs.remove(0);
            nodo.modificarMasivo(nodoI, indexs);
        }
        request.getSession().setAttribute("principal", nodo);
        String path=(String) request.getSession().getAttribute("actualPath");
        String pointer=(String) request.getSession().getAttribute("actualPoint");
        Date date= new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TxtParser.aniadirCambio(new Cambio("Update", simpleDateFormat.format(new Date()),path.replace(ControlPath.path, "")),user);
        XmlParser.Modificar(ControlPath.getPath(user,path),ControlPath.getPath(user,path), nodo.toString(), pointer, nodo.id);
        ArrayList<String> nodos= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,path)) , nodo.id);
        nodo.clean();
        nodo.procesar(nodos, 1,user);
        request.getSession().setAttribute("principal", nodo);
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
