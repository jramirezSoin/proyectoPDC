/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.TimeModelT;
import datos.TimeModelTagT;
import datos.User;
import java.io.File;
import java.io.IOException;
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
public class TimeModel extends HttpServlet {

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
        ArrayList<String> timeModels;
        HttpSession session = request.getSession();
        String user = ((User)session.getAttribute("user")).getUserPDC();
        if(id==null){
            timeModels = XmlParser.Leer2(new File(ControlPath.getPath(user,ControlPath.timeModelsPath)) , ControlPath.timeModelsPointer);
            ArrayList<ListaT> timeModelsId = ControlFunctions.ListS2ListT(timeModels);
             session.setAttribute("click", ControlPath.timeModelsClick);           
            session.setAttribute("lista", timeModelsId);
            session.setAttribute("titulo", "Time Models");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.timeModelsPath);
            session.setAttribute("actualPoint", ControlPath.timeModelsPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            timeModels= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,ControlPath.timeModelsPath)) , Integer.parseInt(id));
            TimeModelT timeModel = new TimeModelT(Integer.parseInt(id));
            timeModel.procesar(timeModels, 1,user);
            session.setAttribute("principal", timeModel);
            session.setAttribute("actual", "timeModel");
            session.setAttribute("actualView", ControlPath.timeModelsView);
            request.getRequestDispatcher(ControlPath.timeModelsView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.timeModelForm).forward(request, response);}
        else if(id.equals("-2")){
            TimeModelT timeModelT= new TimeModelT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", timeModelT);
            request.getSession().setAttribute("addView",ControlPath.timeModelsView);
            request.getRequestDispatcher(ControlPath.timeModelForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.timeModelTagForm).forward(request, response);}
            else if(index.get(0)==-3){
                TimeModelT timeModel = (TimeModelT) request.getSession().getAttribute("principal");
                TimeModelTagT TimeModelTagT = new TimeModelTagT(timeModel.getTimeModelTags().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", TimeModelTagT);
                request.getSession().setAttribute("addView",ControlPath.timeModelsView);
                request.getRequestDispatcher(ControlPath.timeModelTagForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                TimeModelTagT TimeModelTagT = new TimeModelTagT(0);
                TimeModelTagT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", TimeModelTagT);
                request.getRequestDispatcher(ControlPath.timeModelTagForm).forward(request, response);
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
