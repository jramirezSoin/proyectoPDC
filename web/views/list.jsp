<%-- 
    Document   : zoneModelsList
    Created on : Aug 5, 2019, 12:13:44 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.Nodo"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <form>
            <input type="search" id="Buscador" placeholder="Buscar"/>
            <input type="button" onclick="buscar('Buscador','Lista')" value="Buscar">
        </form>
        <a data-toggle="modal" data-target="#addModal" onclick="agregar(<%=request.getSession().getAttribute("click")%>);">add</a>
        <% ArrayList<Nodo> zoneModels = (ArrayList<Nodo>) request.getSession().getAttribute("lista"); %>
        <h1><%= request.getSession().getAttribute("titulo")%></h1>
        <ul>
        <% for(int i=0; i<zoneModels.size(); i++){%>
        <%if(zoneModels.get(i).visibilidad){%>
        <li onclick="hacerClick('<%=request.getSession().getAttribute("click")%>',<%= ((ListaT)zoneModels.get(i)).id%>)"><%= ((ListaT)zoneModels.get(i)).valor%></li>
        <%}}%>
        </ul>