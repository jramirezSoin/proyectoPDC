<%-- 
    Document   : rolloverView
    Created on : Aug 13, 2019, 11:30:18 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.RolloverT"%>
<% RolloverT rollover = (RolloverT) request.getSession().getAttribute("principal");%>
<h1>Rollover <small><%= rollover.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="...">
</div>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/rollover','Rollover',-1);">edit</button>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/rollover','-4')">delete</button>
<hr>
<dl class="row">
  <dt class="col-sm-4">Internal Id</dt><dd class="col-sm-8"><%= rollover.getInternalId()%></dd>
  <dt class="col-sm-4">PriceList Name</dt><dd class="col-sm-8"><%= rollover.getPriceListName()%></dd>
  <dt class="col-sm-4">Pricing Profile Name</dt><dd class="col-sm-8"><%= rollover.getPricingProfileName()%></dd>
</dl>
<hr>
<h3>Date Range</h3>
<dl class="row">
  <dt class="col-sm-4">Start Date</dt><dd class="col-sm-8"><%= rollover.getStartDate()%></dd>
  <dt class="col-sm-4">End Date</dt><dd class="col-sm-8"><%= rollover.getEndDate()%></dd>
</dl>
  <hr>
  <h4>Rollover Pop Model <small>Rollover Charge</small></h3>
  <dl class="row">
      <dt class="col-sm-4">Glid</dt><dd class="col-sm-8"><%= rollover.getGlid()%></dd>
      <dt class="col-sm-4">Unit Of Measure</dt><dd class="col-sm-8"><%= rollover.getUnitOfMeasure()%></dd>
      <dt class="col-sm-4">Balance Element Num Code</dt><dd class="col-sm-8"><%= rollover.getBalanceElementNumCode()%></dd>
      <dt class="col-sm-4">Rollover Units</dt><dd class="col-sm-8"><%= rollover.getRolloverUnits()%></dd>
      <dt class="col-sm-4">Rollover Max Units</dt><dd class="col-sm-8"><%= rollover.getRolloverMaxUnits()%></dd>
      <dt class="col-sm-4">Rollover Count</dt><dd class="col-sm-8"><%= rollover.getRolloverCount()%></dd>
  </dl>