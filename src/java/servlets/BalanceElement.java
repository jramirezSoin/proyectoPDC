/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.BalanceElementT;
import datos.ListaT;
import datos.RoundingRuleT;
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
@WebServlet(name = "BalanceElement", urlPatterns = {"/balanceElement"})
public class BalanceElement extends HttpServlet {

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
        ArrayList<String> balances;
        HttpSession session = request.getSession();
        String user = ((User)session.getAttribute("user")).getUserPDC();
        if(id==null){
            balances = XmlParser.Leer2(new File(ControlPath.getPath(user,ControlPath.balanceElementPath)) , ControlPath.balanceElementPointer);
            ArrayList<ListaT> balancesId = ControlFunctions.ListS2ListT(balances);
             session.setAttribute("click", ControlPath.balanceElementClick);           
            session.setAttribute("lista", balancesId);
            session.setAttribute("titulo", "Balance Elements");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.balanceElementPath);
            session.setAttribute("actualPoint", ControlPath.balanceElementPointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            balances= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,ControlPath.balanceElementPath)), ControlPath.balanceElementPointer , Integer.parseInt(id));
            BalanceElementT balance = new BalanceElementT(Integer.parseInt(id));
            balance.procesar(balances, 1, user);
            session.setAttribute("principal", balance);
            session.setAttribute("actual", "zoneModel");
            session.setAttribute("actualView", ControlPath.balanceElementView);
            request.getRequestDispatcher(ControlPath.balanceElementView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.balanceElementForm).forward(request, response);}
        else if(id.equals("-2")){
            BalanceElementT balance= new BalanceElementT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", balance);
            request.getSession().setAttribute("addView",ControlPath.balanceElementView);
            request.getRequestDispatcher(ControlPath.balanceElementForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.roundingRuleForm).forward(request, response);}
            else if(index.get(0)==-3){
                BalanceElementT balance = (BalanceElementT) request.getSession().getAttribute("principal");
                RoundingRuleT rule = new RoundingRuleT(balance.getRoundingRules().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", rule);
                request.getSession().setAttribute("addView",ControlPath.balanceElementView);
                request.getRequestDispatcher(ControlPath.roundingRuleForm).forward(request, response);
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                BalanceElementT balance = new BalanceElementT(0);
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", balance);
                request.getRequestDispatcher(ControlPath.balanceElementForm).forward(request, response);
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
