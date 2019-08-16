<%-- 
    Document   : triggerSpecView
    Created on : Aug 9, 2019, 3:44:32 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.TriggerSpecT"%>
<%@page import="datos.ExpressionT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% TriggerSpecT triggerSpec = (TriggerSpecT) request.getSession().getAttribute("principal");%>
<h1>Trigger Spec <small><%= triggerSpec.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/triggerSpec','Trigger Spec','-1');">edit</button>
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/triggerSpec','-4')">delete</button>
</div>
<hr>
<dl class="row">
  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= triggerSpec.getDescription()%></dd>
  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= triggerSpec.getInternalId()%></dd>
  <dt class="col-sm-3">Price List Name</dt><dd class="col-sm-9"><%= triggerSpec.getPriceListName()%></dd>
  <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= triggerSpec.getPricingProfileName()%></dd>

</dl>
<hr>
<h1><small>Expressions</small></h1>
    <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" id="BuscaExpression" placeholder="Search">
        </div>
        <button type="button" onclick="buscar('BuscaExpression','Principal')" class="btn btn-default">Search</button>
    </form>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/triggerSpec','Trigger Spec','-3');">add</a>
</div>
<hr>
<ul class="list-group">
    
        <% for(int i=0; i<triggerSpec.getExpressions().size();i++){%>
        <% ExpressionT item = triggerSpec.getExpressions().get(i);%>
        <%if(item.visibilidad){%>
        <li class="list-group-item">
        <table class="table">
        <thead>
            <tr>
                <td></td>
                <td>Operator</td>
                <td>Value</td>
                <% if(item.getTipo().equals("balanceTriggerExpression")){%>
                <td>Balance Element NumCode</td>
                <%}else if(item.getTipo().equals("complexTriggerExpression")){%>
                <td>Left Operand</td>
                <td>Right Operand</td>
                <td>Binary Operator</td>
                <%}%>
                <td></td>
                <td></td>
            </tr>        
        </thead>
        <tbody>
        <tr>
            <td><%= item.getTipo()%></td>
        <td><%=item.getOperator()%></td>
        <td><%=item.getValue()%></td>
        <% if(item.getTipo().equals("balanceTriggerExpression")){%>
        <td><%=item.getBalanceElementNumCode()%></td>
        <%}else if(item.getTipo().equals("complexTriggerExpression")){%>
        <td><%=item.getBalanceElementNumCode()%></td>
        <td>Charge Expression</td>
        <td><%=item.getBinaryOperator()%></td>
        <%}%>
        <td><a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/triggerSpec','Expression',<%=item.getId()%>);"><i class="glyphicon glyphicon-pencil"></i></a></td><td>
        <a data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/triggerSpec','-4,<%=item.getId()%>')"><i class="glyphicon glyphicon-trash"></i></a></td>
        <%}%>
        </tr>
        </tbody>
        </table>
        </li>
        <%}%>

</ul>