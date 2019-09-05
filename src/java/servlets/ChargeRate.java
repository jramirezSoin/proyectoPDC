/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ratePlan.ChargeRatePlanT;
import datos.ListaT;
import datos.ratePlan.CrpCompositePopModelT;
import datos.ratePlan.CrpRelDateRangeT;
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
@WebServlet(name = "ChargeRate", urlPatterns = {"/chargeRate"})
public class ChargeRate extends HttpServlet {

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
        String id = request.getParameter("id");
        String dir = request.getParameter("path");
        ArrayList<String> ChargeRate;
        HttpSession session = request.getSession();
        if(id==null){
            if(dir==null){
            ChargeRate = XmlParser.Leer2(new File(ControlPath.chargeRatePath) , ControlPath.chargeRatePointer);
            ArrayList<ListaT> ChargeRateId = ControlFunctions.ListS2ListT(ChargeRate);
             session.setAttribute("click", ControlPath.chargeRateClick);           
            session.setAttribute("lista", ChargeRateId);
            session.setAttribute("titulo", "Charge Rate");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.chargeRatePath);
            session.setAttribute("actualPoint", ControlPath.chargeRatePointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
            }else{
                CrpCompositePopModelT composite = ((ChargeRatePlanT)session.getAttribute("principal")).buscaPop(dir);
                session.setAttribute("composite", composite);
                request.getRequestDispatcher(ControlPath.crpCompositeView).forward(request, response);
            }
        }else{
                ChargeRate= XmlParser.LeerSeleccionado(new File(ControlPath.chargeRatePath) , Integer.parseInt(id));
                ChargeRatePlanT ChargeRateId = new ChargeRatePlanT(Integer.parseInt(id));
                ChargeRateId.procesar(ChargeRate, 1);
                session.setAttribute("principal", ChargeRateId);
                session.setAttribute("actual", "chargeRate");
                session.setAttribute("actualView", ControlPath.chargeRateView);
                request.getRequestDispatcher(ControlPath.chargeRateView).forward(request, response);  
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
            request.getRequestDispatcher(ControlPath.chargeRateForm).forward(request, response);}
        else if(id.equals("-2")){
            ChargeRatePlanT chargeRateT= new ChargeRatePlanT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", chargeRateT);
            request.getSession().setAttribute("addView",ControlPath.chargeRateView);
            request.getRequestDispatcher(ControlPath.chargeRateForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                if(index.get(1)==-1)
                    request.getRequestDispatcher(ControlPath.crpRelDateForm).forward(request, response);
                else if(index.get(1)==-2)
                    request.getRequestDispatcher(ControlPath.zoneRateForm).forward(request, response);
            }
            else if(index.get(0)==-3){
                ChargeRatePlanT chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("principal");
                CrpRelDateRangeT crpRelDateT = new CrpRelDateRangeT(chargeRate.getSubscriberCurrency().getCrpRelDateRanges().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", crpRelDateT);
                request.getSession().setAttribute("addView",ControlPath.chargeRateView);
                request.getRequestDispatcher(ControlPath.crpRelDateForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                CrpRelDateRangeT crpRelDateT = new CrpRelDateRangeT(0);
                crpRelDateT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", crpRelDateT);
                request.getRequestDispatcher(ControlPath.crpRelDateForm).forward(request, response);
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
