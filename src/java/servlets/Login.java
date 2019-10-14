/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import client.ImportExportClient;
import client.OraclePDCClient;
import com.jcraft.jsch.SftpException;
import control.ControlFunctions;
import control.ControlPath;
import datos.ListaT;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
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
public class Login extends HttpServlet {

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
            HttpSession session = request.getSession();
            session.setAttribute("index", null);
            session.setAttribute("add", null);
            session.setAttribute("addView", null);
            session.setAttribute("principal", null);
            session.setAttribute("lista", null);
            session.setAttribute("actual", null);
            session.setAttribute("actualView", null);
            session.setAttribute("actualPath", null);
            session.setAttribute("actualPoint", null);
            session.setAttribute("titulo", null);
            session.setAttribute("click", null);
            ControlPath.LoadParameters();
            //ImportExport.main();
            /*try {
            OraclePDCClient.crearPricing();
            } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        
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
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
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
        String user= request.getParameter("user");
        String password= request.getParameter("password");
        System.out.println(user+" "+password);
        if(ControlFunctions.login(user,password))
            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
        else
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
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
