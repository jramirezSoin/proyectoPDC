<%-- 
    Document   : rumConfigView
    Created on : Aug 12, 2019, 3:32:10 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.RumT"%>
<% RumT rum = (RumT) request.getSession().getAttribute("principal");%>
<h1>RUM <small><%= rum.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="...">
</div>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/rumConfig','Rum Configuration',-1);">edit</button>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/rumConfig','-4')">delete</button>
<hr>
<dl class="row">
  <dt class="col-sm-3">Description</dt><dd class="col-sm-9">&nbsp<%= rum.getDescription()%></dd>
  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9">&nbsp<%= rum.getInternalId()%></dd>
  <dt class="col-sm-3">Price List Name</dt><dd class="col-sm-9">&nbsp<%= rum.getPriceListName()%></dd>
  <dt class="col-sm-3">Obsolete</dt><dd class="col-sm-9">&nbsp<%= rum.isObsolete()%></dd>
  <dt class="col-sm-3">Unit</dt><dd class="col-sm-9">&nbsp<%= rum.getUnit()%></dd>
  <dt class="col-sm-3">Rum Type</dt><dd class="col-sm-9"><%= rum.getRumType()%></dd>
  <dt class="col-sm-3">Rum Rounding</dt><dd class="col-sm-9">&nbsp<%= rum.getRumRounding()%></dd>
  <dt class="col-sm-3">Rum Code</dt><dd class="col-sm-9">&nbsp<%= rum.getRumCode()%></dd>
</dl>
<hr>
