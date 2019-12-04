package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import control.ControlFunctions;
import control.ControlPath;
import datos.AlterationEventMapT;
import datos.AlterationOfferingT;
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
@WebServlet(urlPatterns = {"/alterationOffering"})
public class AlterationOffering extends HttpServlet {

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
        ArrayList<String> alterationOffering;
        HttpSession session = request.getSession();
        String user = ((User)session.getAttribute("user")).getUserPDC();
        if(id==null){
            alterationOffering = XmlParser.Leer2(new File(ControlPath.getPath(user,ControlPath.alterationOfferingPath)) , ControlPath.alterationOfferingPointer);
            ArrayList<ListaT> alterationOfferingId = ControlFunctions.ListS2ListT(alterationOffering);
             session.setAttribute("click", ControlPath.alterationOfferingClick);           
            session.setAttribute("lista", alterationOfferingId);
            session.setAttribute("titulo", "Alteration Offering");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.alterationOfferingPath);
            session.setAttribute("actualPoint", ControlPath.alterationOfferingPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            alterationOffering= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,ControlPath.alterationOfferingPath)),ControlPath.alterationOfferingPointer , Integer.parseInt(id));
            AlterationOfferingT alterationOfferingId = new AlterationOfferingT(Integer.parseInt(id));
            alterationOfferingId.procesar(alterationOffering, 1, user);
            session.setAttribute("principal", alterationOfferingId);
            session.setAttribute("actual", "alterationOffer");
            session.setAttribute("actualView", ControlPath.alterationOfferingView);
            request.getRequestDispatcher(ControlPath.alterationOfferingView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.alterationOfferingForm).forward(request, response);}
        else if(id.equals("-2")){
            AlterationOfferingT alterationOfferingT= new AlterationOfferingT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", alterationOfferingT);
            request.getSession().setAttribute("addView",ControlPath.alterationOfferingView);
            request.getRequestDispatcher(ControlPath.alterationOfferingForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.alterationEventForm).forward(request, response);}
            else if(index.get(0)==-3){
                AlterationOfferingT alterationOffering = (AlterationOfferingT) request.getSession().getAttribute("principal");
                AlterationEventMapT alterationEventT = new AlterationEventMapT(alterationOffering.getAlterationEvents().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", alterationEventT);
                request.getSession().setAttribute("addView",ControlPath.alterationOfferingView);
                request.getRequestDispatcher(ControlPath.alterationEventForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                AlterationEventMapT alterationEventT = new AlterationEventMapT(0);
                alterationEventT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", alterationEventT);
                request.getRequestDispatcher(ControlPath.alterationEventForm).forward(request, response);
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
