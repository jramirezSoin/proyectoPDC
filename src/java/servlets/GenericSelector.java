/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.RuleT;
import datos.GenericSelectorT;
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
@WebServlet(name = "GenericSelector", urlPatterns = {"/genericSelector"})
public class GenericSelector extends HttpServlet {

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
        ArrayList<String> genericSelectors;
        HttpSession session = request.getSession();
        if(id==null){
            genericSelectors = XmlParser.Leer2(new File(ControlPath.genericSelectorPath) , ControlPath.genericSelectorPointer);
            ArrayList<ListaT> genericSelectorsId = ControlFunctions.ListS2ListT(genericSelectors);
             session.setAttribute("click", ControlPath.genericSelectorClick);           
            session.setAttribute("lista", genericSelectorsId);
            session.setAttribute("titulo", "Zone Models");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.genericSelectorPath);
            session.setAttribute("actualPoint", ControlPath.genericSelectorPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            genericSelectors= XmlParser.LeerSeleccionado(new File(ControlPath.genericSelectorPath) , Integer.parseInt(id));
            GenericSelectorT genericSelector = new GenericSelectorT(Integer.parseInt(id));
            genericSelector.procesar(genericSelectors, 1);
            session.setAttribute("principal", genericSelector);
            session.setAttribute("actual", "genericSelector");
            session.setAttribute("actualView", ControlPath.genericSelectorView);
            request.getRequestDispatcher( ControlPath.genericSelectorView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.genericSelectorForm).forward(request, response);}
        else if(id.equals("-2")){
            GenericSelectorT genericSelectorT= new GenericSelectorT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", genericSelectorT);
            request.getSession().setAttribute("addView",ControlPath.genericSelectorView);
            request.getRequestDispatcher(ControlPath.genericSelectorForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                if(index.size()>1 && index.get(1)==-1){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.modelDataForm).forward(request, response);    
                }
                else{
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.ruleForm).forward(request, response);}
            }
            else if(index.get(0)==-3){
                GenericSelectorT genericSelector = (GenericSelectorT) request.getSession().getAttribute("principal");
                RuleT ruleT = new RuleT();
                ruleT.addModels(genericSelector.getModelDatas());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", ruleT);
                request.getSession().setAttribute("addView",ControlPath.genericSelectorView);
                request.getRequestDispatcher(ControlPath.ruleForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                RuleT ruleT = new RuleT();
                ruleT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", ruleT);
                request.getRequestDispatcher(ControlPath.ruleForm).forward(request, response);
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
