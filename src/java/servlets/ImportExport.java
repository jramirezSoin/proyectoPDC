/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import client.ImportExportClient;
import control.ControlFunctions;
import control.ControlPath;
import datos.Cambio;
import datos.ListaT;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xml.TxtParser;

/**
 *
 * @author Joseph Ramírez
 */
@WebServlet(name = "ImportExport", urlPatterns = {"/importexport"})
public class ImportExport extends HttpServlet {

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
        String tipo = request.getParameter("tipo");
        String files = request.getParameter("files");
        ArrayList<ListaT> seleccionados= new ArrayList<>();
        ArrayList<ListaT> constants = ControlFunctions.LeerConstante("files");
        ArrayList<Cambio> cambios = TxtParser.leerCambios();
        if(files!=null && !files.equals("")){
            files= files.substring(1);
            String[] filesS = files.split(",");
            for(String s: filesS){
                seleccionados.add(constants.get(Integer.parseInt(s)));
            }
        }
        ArrayList<ListaT> reporte = new ArrayList<>();
        if(tipo.equals("export")){
            boolean estado= true;
            for(ListaT l: seleccionados){
                estado= ImportExportClient.exportPricing(l.valor, l.unit);
                if(estado==false){reporte.add(new ListaT("error","ERROR EXPORTING,"+l.valor+"\n"));}
                else{
                    reporte.add(new ListaT("success","SUCCESSFUL EXPORTING,"+l.valor+"\n"));
                    ControlFunctions.CambioContieneElimina(cambios,l.valor);
                }
            }
        }else if(tipo.equals("import")){
            boolean estado= true;
            for(ListaT l: seleccionados){
                estado= ImportExportClient.importPricing(l.valor, l.unit);
                if(estado==false){reporte.add(new ListaT("error","ERROR IMPORTING,"+l.valor+"\n"));}
                else{
                    reporte.add(new ListaT("success","SUCCESSFUL IMPORTING,"+l.valor+"\n"));
                    ControlFunctions.CambioContieneElimina(cambios,l.valor);
                }
            }
        }
        HttpSession session = request.getSession();
        TxtParser.aniadirCambios(cambios);
        session.setAttribute("cambios", cambios);
        session.setAttribute("errores", reporte);
        request.getRequestDispatcher(ControlPath.mainView).forward(request, response);
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
