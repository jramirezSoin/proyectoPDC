<%-- 
    Document   : zoneModelsList
    Created on : Aug 5, 2019, 12:13:44 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.Nodo"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <div class="timeline-task">
        <div class="search-box">
         <input type="text" name="search" id="Buscador" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('Buscador','Lista');}" required>
         <i class="ti-close" onclick="$('#Buscador').val(''); buscar('Buscador','Lista');"></i>
        </div>
        </div>
        <br>
        <div class="timeline-task">
            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example">
                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#addModal" onclick="agregar('<%=request.getSession().getAttribute("click")%>', '-2');">Add</button>
                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#pdfModal" onclick="getPDFSelects();">Download PDF</button>
            </div>
        </div>    
        <br>
        
        <% ArrayList<Nodo> zoneModels = (ArrayList<Nodo>) request.getSession().getAttribute("lista"); %>
        <% for(int i=0; i<zoneModels.size(); i++){%>
        <%if(zoneModels.get(i).visibilidad){%>
        <div class="timeline-task" onclick="hacerClick(this,'<%=request.getSession().getAttribute("click")%>',<%= ((ListaT)zoneModels.get(i)).id%>)">
            <div class="icon bg1">
                <i class="fa"></i>
                <input hidden type="checkbox" class="form-check-input listChecks" id="exampleCheck-<%= ((ListaT)zoneModels.get(i)).id%>">
            </div>
            <div class="tm-title">
                <h4><%= ((ListaT)zoneModels.get(i)).valor.replaceAll("_"," ")%></h4>
            </div>
        </div>
        <%}}%>

        <script type="text/javascript">
            $(".icon").on("click",function(ev){
                if(ev.target == this || $(this).children(ev.target)){clicking=false;
                $(this).toggleClass('bg1 bg2');
                $(this).children('.fa').toggleClass('fa-check');
                if($(this).children(".listChecks").prop('checked')==true){
                    $(this).children(".listChecks").prop('checked',false);}
                else{
                    $(this).children(".listChecks").prop('checked',true);}
                }
                else clicking=true;
                return true;
            });
        </script>