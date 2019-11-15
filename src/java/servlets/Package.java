/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import control.ControlPath;
import datos.BalanceSpecT;
import datos.ListaT;
import datos.PackageItemT;
import datos.PackageT;
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
@WebServlet(name = "Package", urlPatterns = {"/package"})
public class Package extends HttpServlet {

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
        ArrayList<String> packages;
        HttpSession session = request.getSession();
        String user = ((User)session.getAttribute("user")).getUserPDC();
        if(id==null){
            packages = XmlParser.Leer2(new File(ControlPath.getPath(user,ControlPath.packagePath)) , ControlPath.packagePointer);
            ArrayList<ListaT> packageId = ControlFunctions.ListS2ListT(packages);
             session.setAttribute("click", ControlPath.packageClick);           
            session.setAttribute("lista", packageId);
            session.setAttribute("titulo", "Package");
            session.setAttribute("actual", "lista");
            session.setAttribute("actualPath", ControlPath.packagePath);
            session.setAttribute("actualPoint", ControlPath.packagePointer);
            request.getRequestDispatcher(ControlPath.listView).forward(request, response);
        }else{
            packages= XmlParser.LeerSeleccionado(new File(ControlPath.getPath(user,ControlPath.packagePath)) , Integer.parseInt(id));
            PackageT packageId = new PackageT(Integer.parseInt(id));
            packageId.procesar(packages, 1,user);
            session.setAttribute("principal", packageId);
            session.setAttribute("actual", "Package");
            session.setAttribute("actualView", ControlPath.packageView);
            request.getRequestDispatcher(ControlPath.packageView).forward(request, response);
            
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
            request.getRequestDispatcher(ControlPath.packageForm).forward(request, response);}
        else if(id.equals("-2")){
            PackageT zoneModelT= new PackageT(0);
            request.getSession().setAttribute("index",null);
            request.getSession().setAttribute("add", zoneModelT);
            request.getSession().setAttribute("addView",ControlPath.packageView);
            request.getRequestDispatcher(ControlPath.packageForm).forward(request, response);
        }
        else {
            ArrayList<Integer> index= new ArrayList<>();
            String[] arrOfStr = id.split(",");
            for (String a : arrOfStr){
                 index.add(Integer.parseInt(a));}
            if(index.get(0)>=0){
                if(index.get(0)==1){
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.packageItemForm).forward(request, response);}
                else{
                request.getSession().setAttribute("add",null);
                request.getSession().setAttribute("index", index);
                request.getRequestDispatcher(ControlPath.packageBalanceForm).forward(request, response);
                }
            }else if(index.get(0)==-3){
                PackageT packages = (PackageT) request.getSession().getAttribute("principal");
                if(index.get(1)==1){
                PackageItemT packageItemT = new PackageItemT(packages.getPackageItems().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", packageItemT);
                request.getSession().setAttribute("addView",ControlPath.packageView);
                request.getRequestDispatcher(ControlPath.packageItemForm).forward(request, response);
                }else{
                BalanceSpecT packageItemT = new BalanceSpecT(packages.getBalances().size());
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", packageItemT);
                request.getSession().setAttribute("addView",ControlPath.packageView);
                request.getRequestDispatcher(ControlPath.packageBalanceForm).forward(request, response);
                }
                
            }else if(index.get(0)==-4){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-6){
                request.getSession().setAttribute("del", index);
            }else if(index.get(0)==-5){
                if(index.get(1)==1){
                PackageItemT packageItemT = new PackageItemT(0);
                packageItemT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", packageItemT);
                request.getRequestDispatcher(ControlPath.packageItemForm).forward(request, response);
                }else{
                BalanceSpecT packageItemT = new BalanceSpecT(0);
                packageItemT.masivo();
                request.getSession().setAttribute("index", index);
                request.getSession().setAttribute("add", packageItemT);
                request.getRequestDispatcher(ControlPath.packageBalanceForm).forward(request, response);
                }
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
