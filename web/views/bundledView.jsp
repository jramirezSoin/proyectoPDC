<%-- 
    Document   : bundledView
    Created on : Aug 16, 2019, 3:09:25 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.BundledItemT"%>
<%@page import="datos.BundledT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% BundledT bundled = (BundledT) request.getSession().getAttribute("principal");%>
<h1>Bundle product Offering<small><%= bundled.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle','-1');">edit</button>
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/bundled','-4')">delete</button>
</div>
<hr>
<dl class="row">
  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= bundled.getDescription()%></dd>
  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= bundled.getInternalId()%></dd>
  <dt class="col-sm-3">PriceList Name</dt><dd class="col-sm-9"><%= bundled.getPriceListName()%></dd>
  <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= bundled.getPricingProfileName()%></dd>
  <dt class="col-sm-3">Time Range</dt><dd class="col-sm-9"><%= bundled.getTimeRange()%></dd>
  <dt class="col-sm-3"><%= (bundled.getCustomerSpecName().equals(""))?"Product":"Customer"%> Spec Name</dt><dd class="col-sm-9"><%= (bundled.getCustomerSpecName().equals(""))?bundled.getProductSpecName():bundled.getCustomerSpecName()%></dd>
  <dt class="col-sm-3">Bill on Purchase</dt><dd class="col-sm-9"><%= bundled.getBillOnPurchase()%></dd>
  <dt class="col-sm-3">Customize</dt><dd class="col-sm-9"><%= bundled.getCustomize()%></dd>
  <dt class="col-sm-3">Group Balance Elements</dt><dd class="col-sm-9"><%= bundled.getGroupBalanceElements()%></dd>

</dl>
<hr>
<h1><small>Bundle Items</small></h1>
    <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" id="BuscaBundleItem" placeholder="Search">
        </div>
        <button type="button" onclick="buscar('BuscaBundleItem','Principal')" class="btn btn-default">Search</button>
    </form>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle Item','-3');">add</button>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle Item','-5');">edit</button>
</div>
<table class="table">
    <thead>
    <tr>
        <th></th>
        <th>Offer/Discount</th>
        <th>status</th>
        <th>status Code</th>
        <th>Quantity</th>
        <th>Purchase</th>
        <th>Usage</th>
        <th>Cycle</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for(int i=0; i<bundled.getBundledItems().size();i++){%>
        <% BundledItemT item = bundled.getBundledItems().get(i);%>
        <%if(item.visibilidad){%>
        <tr>
        <td><input type="checkbox" class="form-check-input itemChecks" id="itemCheck-<%=item.getId()%>"></td>
        <td><%= (item.getChargeOfferingName().equals(""))?item.getAlterationOfferingName().replaceAll("_", " "):item.getChargeOfferingName().replaceAll("_", " ")%></td>
        <td><%= item.getStatus()%></td>
        <td><%= item.getStatusCode()%></td>
        <td><%= item.getQuantity()%></td>
        <td>
            <table class="table">
            <tr><th colspan="2">Start</th></tr>
            <tr><th>Offset</th><td><%=item.getPurchaseStart().id%></td></tr>
            <tr><th>Mode</th><td><%=item.getPurchaseStart().valor%></td></tr>
            <%if(!item.getPurchaseStart().unit.equals("")){%>
            <tr><th>Unit</th><td><%=item.getPurchaseStart().unit%></td></tr>
            <%}%>
            <tr><th colspan="2">End</th></tr>
            <tr><th>Offset</th><td><%=item.getPurchaseEnd().id%></td></tr>
            <tr><th>Mode</th><td><%=item.getPurchaseEnd().valor%></td></tr>
            <%if(!item.getPurchaseEnd().unit.equals("")){%>
            <tr><th>Unit</th><td><%=item.getPurchaseEnd().unit%></td></tr>
            <%}%>
            <tr><th colspan="2">Adjustment</th></tr>
            <tr><td colspan="2"><%= item.getPurchaseChargeAdjustment()%></td></tr>
            </table>
        </td>
        <td>
            <table class="table">
            <tr><th colspan="2">Start</th></tr>
            <tr><th>Offset</th><td><%=item.getUsageStart().id%></td></tr>
            <tr><th>Mode</th><td><%=item.getUsageStart().valor%></td></tr>
            <%if(!item.getUsageStart().unit.equals("")){%>
            <tr><th>Unit</th><td><%=item.getUsageStart().unit%></td></tr>
            <%}%>
            <tr><th colspan="2">End</th></tr>
            <tr><th>Offset</th><td><%=item.getUsageEnd().id%></td></tr>
            <tr><th>Mode</th><td><%=item.getUsageEnd().valor%></td></tr>
            <%if(!item.getUsageEnd().unit.equals("")){%>
            <tr><th>Unit</th><td><%=item.getUsageEnd().unit%></td></tr>
            <%}%>
            <tr><th colspan="2">Adjustment</th></tr>
            <tr><td colspan="2"><%= item.getUsageChargeAdjustment()%></td></tr>
            </table>
        </td>
        <td>
            <table class="table">
            <tr><th colspan="2">Start</th></tr>
            <tr><th>Offset</th><td><%=item.getCycleStart().id%></td></tr>
            <tr><th>Mode</th><td><%=item.getCycleStart().valor%></td></tr>
            <%if(!item.getCycleStart().unit.equals("")){%>
            <tr><th>Unit</th><td><%=item.getCycleStart().unit%></td></tr>
            <%}%>
            <tr><th colspan="2">End</th></tr>
            <tr><th>Offset</th><td><%=item.getCycleEnd().id%></td></tr>
            <tr><th>Mode</th><td><%=item.getCycleEnd().valor%></td></tr>
            <%if(!item.getCycleEnd().unit.equals("")){%>
            <tr><th>Unit</th><td><%=item.getCycleEnd().unit%></td></tr>
            <%}%>
            <tr><th colspan="2">Adjustment</th></tr>
            <tr><td colspan="2"><%= item.getCycleChargeAdjustment()%></td></tr>
            </table>
        </td>
        <td><a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle Item',<%=item.getId()%>);"><i class="glyphicon glyphicon-pencil"></i></a></td><td>
        <a data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/bundled','-4,<%=item.getId()%>')"><i class="glyphicon glyphicon-trash"></i></a></td>
        </tr>
        <%}%>
    <%}%>
    </tbody>
</table>
