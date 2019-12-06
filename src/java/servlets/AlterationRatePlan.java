/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import datos.User;
import datos.alteration.AlterationConfigurationT;
import datos.alteration.AlterationRatePlanT;
import datos.alteration.ArpDateRangeT;
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
@WebServlet(name = "AlterationRatePlan", urlPatterns = {"/alterationRatePlan"})
public class AlterationRatePlan extends HttpServlet {

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
        String dir = request.getParameter("path");
        ArrayList<String> AlterationRate;
        HttpSession session = request.getSession();
        String user = ((User)session.getAttribute("user")).getUserPDC();
        if(id==null){
            if(dir==null){
            AlterationRate = XmlParser.Leer2(new File(ControlPath.getPath(user,ControlPath.alterationRatePath)) , ControlPath.alterationRatePointer);
            ArrayList<ListaT> AlterationRateId = ControlFunctions.ListS2ListT(AlterationRate);
             session.setAttribute("click", ControlPath.alterationRateClick);           
            session.setAttribute("lista", AlterationRateId);
            session.setAttribute("titulo", "Alteration Rate");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.alterationRatePath);
            session.setAttribute("actualPoint", ControlPath.alterationRatePointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
            }else{
                AlterationConfigurationT composite = ((AlterationRatePlanT)session.getAttribute("principal")).buscaPop(dir);
                session.setAttribute("composite", composite);
                request.getRequestDispatcher(ControlPath.arpCompositeView).forward(request, response);
            }
        }else{
                AlterationRate= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,ControlPath.alterationRatePath)),ControlPath.alterationRatePointer , Integer.parseInt(id));
                AlterationRatePlanT AlterationRateId = new AlterationRatePlanT(Integer.parseInt(id));
                AlterationRateId.procesar(AlterationRate, 1, user);
                session.setAttribute("principal", AlterationRateId);
                session.setAttribute("actual", "alterationRate");
                session.setAttribute("actualView", ControlPath.alterationRateView);
                request.getRequestDispatcher(ControlPath.alterationRateView).forward(request, response);  
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
            request.getRequestDispatcher(ControlPath.alterationRateForm).forward(request, response);}
        else if(id.equals("-2")){
            AlterationRatePlanT alterationRateT= new AlterationRatePlanT(0);
            alterationRateT.setArpDateRange(new ArpDateRangeT(0));
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", alterationRateT);
            request.getSession().setAttribute("addView",ControlPath.alterationRateView);
            request.getRequestDispatcher(ControlPath.alterationRateForm).forward(request, response);
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
                    request.getRequestDispatcher(ControlPath.arpCompositeForm).forward(request, response);
                else if(index.get(1)==-2)
                    request.getRequestDispatcher(ControlPath.boundForm).forward(request, response);
                else if(index.get(1)==-3)
                    request.getRequestDispatcher(ControlPath.alterationForm).forward(request, response);
                else if(index.get(1)==-4){
                    request.getRequestDispatcher(ControlPath.chargeAddForm).forward(request, response);    
                }    
            }
            else if(index.get(0)==-3){
                AlterationRatePlanT alterationRate = (AlterationRatePlanT) request.getSession().getAttribute("principal");
                AlterationConfigurationT alterationConfigurationT = new AlterationConfigurationT(alterationRate.getArpDateRange().getAlterationConfigurations().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", alterationConfigurationT);
                request.getSession().setAttribute("addView",ControlPath.alterationRateView);
                request.getRequestDispatcher(ControlPath.arpCompositeForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                AlterationConfigurationT crpRelDateT = new AlterationConfigurationT(0);
                crpRelDateT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", crpRelDateT);
                request.getRequestDispatcher(ControlPath.arpCompositeForm).forward(request, response);
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
