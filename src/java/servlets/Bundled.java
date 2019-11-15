/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.BundledItemT;
import datos.BundledT;
import datos.ListaT;
import datos.User;
import java.io.File;
import java.io.IOException;
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
@WebServlet(name = "Bundled", urlPatterns = {"/bundled"})
public class Bundled extends HttpServlet {

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
        ArrayList<String> bundled;
        HttpSession session = request.getSession();
        String user = ((User)session.getAttribute("user")).getUserPDC();
        if(id==null){
            bundled = XmlParser.Leer2(new File(ControlPath.getPath(user,ControlPath.bundledPath)) , ControlPath.bundledPointer);
            ArrayList<ListaT> bundledId = ControlFunctions.ListS2ListT(bundled);
             session.setAttribute("click", ControlPath.bundledClick);           
            session.setAttribute("lista", bundledId);
            session.setAttribute("titulo", "Bundled Product Offering");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.bundledPath);
            session.setAttribute("actualPoint", ControlPath.bundledPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            bundled= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,ControlPath.bundledPath)) , Integer.parseInt(id));
            BundledT zoneModel = new BundledT(Integer.parseInt(id));
            zoneModel.procesar(bundled, 1,user);
            session.setAttribute("principal", zoneModel);
            session.setAttribute("actual", "bundled");
            session.setAttribute("actualView", ControlPath.bundledView);
            request.getRequestDispatcher(ControlPath.bundledView).forward(request, response);
            
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
        String id = request.getParameter("id");
        
        if(id==null || id.equals("-1")){
            request.getSession().setAttribute("add",null);
            request.getSession().setAttribute("index",null);
            request.getRequestDispatcher(ControlPath.bundledForm).forward(request, response);}
        else if(id.equals("-2")){
            BundledT zoneModelT= new BundledT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", zoneModelT);
            request.getSession().setAttribute("addView",ControlPath.bundledView);
            request.getRequestDispatcher(ControlPath.bundledForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.bundledItemForm).forward(request, response);}
            else if(index.get(0)==-3){
                BundledT zoneModel = (BundledT) request.getSession().getAttribute("principal");
                BundledItemT bundledItemT = new BundledItemT(zoneModel.getBundledItems().size());
                ArrayList<ListaT> buscar= new ArrayList<>();
                if(zoneModel.getCustomerSpecName().equals(""))
                    buscar.add(new ListaT("productSpecName",zoneModel.getProductSpecName()));
                else
                    buscar.add(new ListaT("customerSpecName",zoneModel.getCustomerSpecName()));
                request.getSession().setAttribute("buscar",buscar);
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", bundledItemT);
                request.getSession().setAttribute("addView",ControlPath.bundledView);
                request.getRequestDispatcher(ControlPath.bundledItemForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                BundledItemT bundledItemT = new BundledItemT(0);
                bundledItemT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", bundledItemT);
                request.getRequestDispatcher(ControlPath.bundledItemForm).forward(request, response);
            }
        }
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
