/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlPath;
import control.FirstPDF;
import datos.ListaT;
import datos.Nodo;
import datos.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joseph Ramírez
 */
@WebServlet(name = "DownloadPdf", urlPatterns = {"/downloadPdf"})
public class DownloadPdf extends HttpServlet {
    private final int ARBITARY_SIZE = 4096;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
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
        Nodo nodo = (Nodo)request.getSession().getAttribute("principal");
        String name = "PDC_"+simpleDateFormat.format(new Date())+".pdf";
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename="+name);
        String user = ((User)request.getSession().getAttribute("user")).getUserPDC();
        FirstPDF.crearPDF(name, nodo,user);
        File file = new File(ControlPath.path+user+"/PDF/"+name);
        FileInputStream fileIn = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        byte[] outputByte = new byte[ARBITARY_SIZE];
        //copy binary contect to output stream
        while(fileIn.read(outputByte, 0, ARBITARY_SIZE) != -1)
        {
               out.write(outputByte, 0, ARBITARY_SIZE);
        }
        fileIn.close();
        out.flush();
        out.close();
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
        
        ArrayList<Integer> checks= getChecks(request.getParameter("pdfChecks"));
        String cantidad= request.getParameter("customRadioD");
        String calidad= request.getParameter("customRadio");
        
        
        ArrayList<ListaT> nodo = (ArrayList<ListaT>)request.getSession().getAttribute("lista");
        String name = "PDC_"+simpleDateFormat.format(new Date())+".pdf";
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename="+name);
        String user = ((User)request.getSession().getAttribute("user")).getUserPDC();
        if(calidad.equals("General")){
            if(cantidad.equals("Todos"))
                FirstPDF.crearListaPDF(name, nodo,user);
            else if(cantidad.equals("Seleccionados")){
                FirstPDF.crearListaPDF(name, nodo, checks,user);
            }
        }else if(calidad.equals("Detallado")){
            if(cantidad.equals("Todos"))
                FirstPDF.crearListaDPDF(name, nodo, (String)request.getSession().getAttribute("actualPath"),user);
            else if(cantidad.equals("Seleccionados")){
                FirstPDF.crearListaDPDF(name, nodo, checks, (String)request.getSession().getAttribute("actualPath"),user);
            }
        }
        File file = new File(ControlPath.path+user+"/PDF/"+name);
        FileInputStream fileIn = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        byte[] outputByte = new byte[ARBITARY_SIZE];
        //copy binary contect to output stream
        while(fileIn.read(outputByte, 0, ARBITARY_SIZE) != -1)
        {
               out.write(outputByte, 0, ARBITARY_SIZE);
        }
        fileIn.close();
        out.flush();
        out.close();
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

    private ArrayList<Integer> getChecks(String parameter) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        if(parameter!=null && !parameter.equals("")){
            parameter= parameter.substring(1);
            for(String s: parameter.split(",")){
                lista.add(Integer.parseInt(s));
            }
        }
        return lista;
    }

}
