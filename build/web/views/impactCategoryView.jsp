<%-- 
    Document   : impactCategoryView
    Created on : Aug 7, 2019, 10:08:47 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ImpactCategoryT"%>
<%@page import="datos.ImpactCategoryT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ImpactCategoryT impactCategory = (ImpactCategoryT) request.getSession().getAttribute("principal");%>
<h1>Impact Category <small><%= impactCategory.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="...">
</div>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/impactCategories','Impact Category',-1);">edit</button>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/impactCategories','-4')">delete</button>
<hr>
<dl class="row">
  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= impactCategory.getDescription()%></dd>
  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= impactCategory.getInternalId()%></dd>
  <dt class="col-sm-3">PriceList Name</dt><dd class="col-sm-9"><%= impactCategory.getPriceListName()%></dd>
  <dt class="col-sm-3">Obsolete</dt><dd class="col-sm-9"><%= impactCategory.isObsolete()%></dd>
  <dt class="col-sm-3">Result</dt><dd class="col-sm-9"><%= impactCategory.getResult()%></dd>
  <dt class="col-sm-3">Result Type</dt><dd class="col-sm-9"><%= impactCategory.getResultType()%></dd>
</dl>
<hr>
