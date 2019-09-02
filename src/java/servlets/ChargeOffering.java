/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.ChargeEventMapT;
import datos.ChargeOfferingT;
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
@WebServlet(name = "ChargeOffering", urlPatterns = {"/charge"})
public class ChargeOffering extends HttpServlet {

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
        ArrayList<String> chargeOffering;
        HttpSession session = request.getSession();
        if(id==null){
            chargeOffering = XmlParser.Leer2(new File(ControlPath.chargeOfferingPath) , ControlPath.chargeOfferingPointer);
            ArrayList<ListaT> chargeOfferingId = ControlFunctions.ListS2ListT(chargeOffering);
             session.setAttribute("click", ControlPath.chargeOfferingClick);           
            session.setAttribute("lista", chargeOfferingId);
            session.setAttribute("titulo", "Charge Offering");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.chargeOfferingPath);
            session.setAttribute("actualPoint", ControlPath.chargeOfferingPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            chargeOffering= XmlParser.LeerSeleccionado(new File(ControlPath.chargeOfferingPath) , Integer.parseInt(id));
            ChargeOfferingT chargeOfferingId = new ChargeOfferingT(Integer.parseInt(id));
            chargeOfferingId.procesar(chargeOffering, 1);
            session.setAttribute("principal", chargeOfferingId);
            session.setAttribute("actual", "chargeOffer");
            session.setAttribute("actualView", ControlPath.chargeOfferingView);
            request.getRequestDispatcher(ControlPath.chargeOfferingView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.chargeOfferingForm).forward(request, response);}
        else if(id.equals("-2")){
            ChargeOfferingT chargeOfferingT= new ChargeOfferingT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", chargeOfferingT);
            request.getSession().setAttribute("addView",ControlPath.chargeOfferingView);
            request.getRequestDispatcher(ControlPath.chargeOfferingForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.chargeEventForm).forward(request, response);}
            else if(index.get(0)==-3){
                ChargeOfferingT chargeOffering = (ChargeOfferingT) request.getSession().getAttribute("principal");
                ChargeEventMapT chargeEventT = new ChargeEventMapT(chargeOffering.getChargeEvents().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", chargeEventT);
                request.getSession().setAttribute("addView",ControlPath.chargeOfferingView);
                request.getRequestDispatcher(ControlPath.chargeEventForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                ChargeEventMapT chargeEventT = new ChargeEventMapT(0);
                chargeEventT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", chargeEventT);
                request.getRequestDispatcher(ControlPath.chargeEventForm).forward(request, response);
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