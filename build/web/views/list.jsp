<%-- 
    Document   : zoneModelsList
    Created on : Aug 5, 2019, 12:13:44 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.Nodo"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="padding: 20px;">
              <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" id="Buscador" placeholder="Search">
        </div>
        <button type="button" onclick="buscar('Buscador','Lista')" class="btn btn-default">Search</button>
      </form>
        <div class="btn-group btn-group-sm" role="group" aria-label="...">   
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addModal" onclick="agregar('<%=request.getSession().getAttribute("click")%>', '-2');">add</button>
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addModal" onclick="agregar('<%=request.getSession().getAttribute("click")%>','-5');">edit</button>
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('<%=request.getSession().getAttribute("click")%>', '-6');">delete</button>
        </div>
        <div style=" height: 70vh; overflow-y: auto;">
        <% ArrayList<Nodo> zoneModels = (ArrayList<Nodo>) request.getSession().getAttribute("lista"); %>
        <h1><small><%= request.getSession().getAttribute("titulo")%></small></h1>
        <ul class="list-group">
        <% for(int i=0; i<zoneModels.size(); i++){%>
        <%if(zoneModels.get(i).visibilidad){%>
        <a class="list-group-item" onclick="hacerClick(this,'<%=request.getSession().getAttribute("click")%>',<%= ((ListaT)zoneModels.get(i)).id%>)"><input type="checkbox" class="form-check-input listChecks" id="exampleCheck-<%= ((ListaT)zoneModels.get(i)).id%>"> <%= ((ListaT)zoneModels.get(i)).valor.replaceAll("_"," ")%></a>
        <%}}%>
        </ul>
        </div>

        <script type="text/javascript">
            $(".form-check-input").on("click",function(ev){
                if(ev.target == this)clicking=false;
                else clicking=true;
                return true;
            });
        </script>
</div>