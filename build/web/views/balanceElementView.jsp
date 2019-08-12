<%-- 
    Document   : balanceElementView
    Created on : Aug 12, 2019, 3:31:51 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.RoundingRuleT"%>
<%@page import="datos.BalanceElementT"%>
<% BalanceElementT balance = (BalanceElementT) request.getSession().getAttribute("principal");%>
<h1>Balance Element <small><%= balance.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/balanceElement','Balance Element','-1');">edit</button>
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/balanceElement','-4')">delete</button>
</div>
<hr>
<dl class="row">
  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= balance.getDescription()%></dd>
  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= balance.getInternalId()%></dd>
  <dt class="col-sm-3">PriceList Name</dt><dd class="col-sm-9"><%= balance.getPriceListName()%></dd>
  <dt class="col-sm-3">Obsolete</dt><dd class="col-sm-9"><%= balance.isObsolete()%></dd>
  <dt class="col-sm-3">Code</dt><dd class="col-sm-9"><%= balance.getCode()%></dd>
  <dt class="col-sm-3">Num Code</dt><dd class="col-sm-9"><%= balance.getNumCode()%></dd>
  <dt class="col-sm-3">Symbol</dt><dd class="col-sm-9"><%= balance.getSymbol()%></dd>
  <dt class="col-sm-3">Transient Element</dt><dd class="col-sm-9"><%= balance.getTransientElement()%></dd>
  <dt class="col-sm-3">Foldable</dt><dd class="col-sm-9"><%= balance.isFoldable()%></dd>
  <dt class="col-sm-3">Counter</dt><dd class="col-sm-9"><%= balance.isCounter()%></dd>
  <dt class="col-sm-3">Consumption Rule</dt><dd class="col-sm-9"><%= balance.getConsumptionRule()%></dd>
</dl>
<hr>
<h1><small>Rounding Rules</small></h1>
    <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" id="BuscaRounds" placeholder="Search">
        </div>
        <button type="button" onclick="buscar('BuscaRounds','Principal')" class="btn btn-default">Search</button>
    </form>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/balanceElement','Rounding Rule','-3');">add</a>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Precision</th>
        <th>Tolerance Min</th>
        <th>Tolerance Max</th>
        <th>Tolerance Percentage</th>
        <th>Rounding Mode</th>
        <th>Processing Stage</th>
       
    </tr>
    </thead>
    <tbody>
    <% for(int i=0; i<balance.getRoundingRules().size();i++){%>
        <% RoundingRuleT item = balance.getRoundingRules().get(i);%>
        <%if(item.visibilidad){%>
        <tr>
        <td><%= item.getPrecision()%></td>
        <td><%= item.getToleranceMin()%></td>
        <td><%= item.getToleranceMax()%></td>
        <td><%= item.getTolerancePercentage()%></td>
        <td><%= item.getRoundingMode()%></td>
        <td><%= item.getProcessingStage()%></td>
        <td><a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/balanceElement','Balance Element',<%=item.getId()%>);"><i class="glyphicon glyphicon-pencil"></i></a></td><td>
        <a data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/balanceElement','-4,<%=item.getId()%>')"><i class="glyphicon glyphicon-trash"></i></a></td>
        </tr>
        <%}%>
    <%}%>
    </tbody>
</table>
