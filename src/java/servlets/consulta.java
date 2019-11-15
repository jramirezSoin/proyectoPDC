/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import control.ControlFunctions;
import datos.ListaT;
import datos.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joseph Ramírez
 */
@WebServlet(name = "consulta", urlPatterns = {"/consulta"})
public class consulta extends HttpServlet {

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
        String respuesta = getRespuesta(request);
        response.setContentType("text/plain");
	response.getWriter().write(respuesta);
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

    private String getRespuesta(HttpServletRequest request) {
        String funcion= request.getParameter("funcion");
        String tipo= request.getParameter("tipo");
        String user = ((User)request.getSession().getAttribute("user")).getUserPDC();
        ArrayList<ListaT> response= new ArrayList<>();
        switch(funcion){
            case "getLista": response= ControlFunctions.getLista(tipo,user); break;
            case "getListaFiltroDeep":
                ArrayList<ListaT> filtro= getFiltro(request.getParameter("filtro"));
                String buscar= request.getParameter("buscar");
                String conjunto=request.getParameter("conjunto");
                if(conjunto==null || conjunto.equals("")) conjunto="false";
                response= ControlFunctions.getListaFiltroDeep(tipo,user, filtro, buscar,Boolean.valueOf(conjunto));
                break;
        }
        String compara=request.getParameter("compara");
        if(compara!=null && !compara.equals("")){
            ListaT comp= new ListaT("",compara);
            int itemPos = response.indexOf(comp);
            if(itemPos!=-1){
                response.remove(itemPos);
                response.add(0, comp);}
        }
        String result=arrayListToJson(response);
        String replace=request.getParameter("replace");
        if(replace!=null && !replace.equals("")){
            try{
                result=result.replaceAll(replace.split(";")[0], replace.split(";")[1]);
            }catch(Exception e){
                result=result.replaceAll(replace.split(";")[0], "");
            }
        }
        return result;
    }

    private String arrayListToJson(ArrayList<ListaT> lista) {
        if(lista==null || lista.size()==0) return "{ \"elements\" : [ ]}";
        String response = "";
        for(ListaT t: lista)response+=",\""+t.valor+"\"";
        return "{ \"elements\" : [ "+response.substring(1)+" ]}";
    }

    private ArrayList<ListaT> getFiltro(String parameter) {
        ArrayList<ListaT> lista = new ArrayList<ListaT>();
        String[] filtros= parameter.split(",");
        for(String fil: filtros){
            try{
            lista.add(new ListaT(fil.split(";")[0],fil.split(";")[1]));}
            catch(Exception e){
            lista.add(new ListaT(fil.split(";")[0],""));
            }
        }
        return lista;
    }

}
