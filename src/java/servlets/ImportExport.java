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
import datos.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import xml.TxtParser;
import xml.XmlParser;

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
        User usuario = (User) request.getSession().getAttribute("user");
        ArrayList<ListaT> seleccionados= new ArrayList<>();
        ArrayList<ListaT> constants = ControlFunctions.LeerConstante("files");
        ArrayList<Cambio> cambios = TxtParser.leerCambios(usuario.getUserPDC());
        if(files!=null && !files.equals("") && tipo!=null && !tipo.equals("error")){
            files= files.substring(1);
            String[] filesS = files.split(",");
            for(String s: filesS){
                seleccionados.add(constants.get(Integer.parseInt(s)));
            }
        }
        ArrayList<ListaT> reporte = new ArrayList<>();
        if(tipo.equals("export")){
            boolean estado= true;
            String exportado = request.getParameter("exportado");
            if(exportado==null) exportado="";
            for(ListaT l: seleccionados){
                estado= ImportExportClient.exportPricing(l.valor, l.unit, usuario.getUserPDC(), usuario.getPwdPDC(), usuario.getPwdPDCIE(),exportado);
                if(estado==false){reporte.add(new ListaT("error","ERROR EXPORTING,"+l.valor+"\n"));}
                else{
                    reporte.add(new ListaT("success","SUCCESSFUL EXPORTING,"+l.valor+" "+(!exportado.equals("")?"["+exportado+"]":"")+"\n"));
                    if(!exportado.equals("")){
                        int cont= XmlParser.agregarExports(l.valor,l.unit,usuario.getUserPDC());
                        ControlFunctions.CambioContieneElimina(cambios,l.valor,cont);
                    }else{
                        ControlFunctions.CambioContieneElimina(cambios,l.valor,-1);
                    }
                }
            }
        }else if(tipo.equals("import")){
            boolean estado= true;
            for(ListaT l: seleccionados){
                int cont = XmlParser.getUpdates(l.valor,l.unit,usuario.getUser());
                if(cont!=0){
                estado= ImportExportClient.importPricing(l.valor, l.unit, usuario.getUserPDC(), usuario.getPwdPDC(), usuario.getPwdPDCIE());
                if(estado==false){reporte.add(new ListaT("error","ERROR IMPORTING,"+l.valor+"\n"));}
                else{
                    reporte.add(new ListaT("success","SUCCESSFUL IMPORTING,"+l.valor+"\n"));
                    ControlFunctions.CambioContieneElimina(cambios,l.valor, -1);
                    XmlParser.deleteIceUpdater(l.valor,l.unit,usuario.getUser());
                }}
                else{
                    reporte.add(new ListaT("info","DOESN'T EXIST CHANGES IN,"+l.valor+"\n"));
                }
            }
        }
        if(tipo!=null && !tipo.equals("error")){
        HttpSession session = request.getSession();
        TxtParser.aniadirCambios(cambios,usuario.getUserPDC(),ControlPath.changes);
        session.setAttribute("cambios", cambios);
        for(ListaT rpt :  reporte){
            TxtParser.aniadirCambio(new Cambio(rpt.unit,0,rpt.valor), usuario.getUser(), "bitacora.log");
        }
        session.setAttribute("errores", reporte);
        request.getRequestDispatcher(ControlPath.mainView).forward(request, response);}
        else if(tipo!=null && tipo.equals("error")){
        HttpSession session = request.getSession();
        session.setAttribute("log", TxtParser.readFile(files,usuario.getUser()));
        request.getRequestDispatcher(ControlPath.errorMessage).forward(request, response);
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
