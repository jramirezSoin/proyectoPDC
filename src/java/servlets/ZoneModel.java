/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.ZoneItemT;
import datos.ZoneModelT;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xml.XmlParser;

/**
 *
 * @author Joseph Ramírez
 */
public class ZoneModel extends HttpServlet {
   
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
        ArrayList<String> zoneModels;
        HttpSession session = request.getSession();
        if(id==null){
            zoneModels = XmlParser.Leer(new File(ControlPath.zoneModelsPath) , ControlPath.zoneModelsPointer);
            ArrayList<ListaT> zoneModelsId = ControlFunctions.ListS2ListT(zoneModels);
             session.setAttribute("click", ControlPath.zoneModelsClick);           
            session.setAttribute("lista", zoneModelsId);
            session.setAttribute("titulo", "Zone Models");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.zoneModelsPath);
            session.setAttribute("actualPoint", ControlPath.zoneModelsPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            zoneModels= XmlParser.LeerSeleccionado(new File(ControlPath.zoneModelsPath) , Integer.parseInt(id));
            ZoneModelT zoneModel = new ZoneModelT(Integer.parseInt(id));
            zoneModel.procesar(zoneModels, 1);
            session.setAttribute("principal", zoneModel);
            session.setAttribute("actual", "zoneModel");
            session.setAttribute("actualView", ControlPath.zoneModelsView);
            request.getRequestDispatcher(ControlPath.zoneModelsView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.zoneModelForm).forward(request, response);}
        else if(id.equals("-2")){
            ZoneModelT zoneModelT= new ZoneModelT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", zoneModelT);
            request.getSession().setAttribute("addView",ControlPath.zoneModelsView);
            request.getRequestDispatcher(ControlPath.zoneModelForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.zoneItemForm).forward(request, response);}
            else if(index.get(0)==-3){
                ZoneModelT zoneModel = (ZoneModelT) request.getSession().getAttribute("principal");
                ZoneItemT zoneItemT = new ZoneItemT(zoneModel.getZoneItems().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", zoneItemT);
                request.getSession().setAttribute("addView",ControlPath.zoneModelsView);
                request.getRequestDispatcher(ControlPath.zoneItemForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
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
