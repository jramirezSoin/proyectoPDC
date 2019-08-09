<%-- 
    Document   : zoneModelView
    Created on : Aug 5, 2019, 4:21:25 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ZoneModelT"%>
<%@page import="datos.ZoneItemT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ZoneModelT zoneModel = (ZoneModelT) request.getSession().getAttribute("principal");%>
<h1>Zone Model <small><%= zoneModel.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Model','-1');">edit</button>
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/zoneModels','-4')">delete</button>
</div>
<hr>
<dl class="row">
  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= zoneModel.getDescription()%></dd><dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= zoneModel.getInternalId()%></dd><dt class="col-sm-3">PriceList Name</dt><dd class="col-sm-9"><%= zoneModel.getPriceListName()%></dd><dt class="col-sm-3">Obsolete</dt><dd class="col-sm-9"><%= zoneModel.isObsolete()%></dd>

</dl>
<hr>
<h1><small>Zone Items</small></h1>
    <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" id="BuscaZoneItem" placeholder="Search">
        </div>
        <button type="button" onclick="buscar('BuscaZoneItem','Principal')" class="btn btn-default">Search</button>
    </form>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Item','-3');">add</a>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Product Name</th>
        <th>Origin Prefix</th>
        <th>Destination Prefix</th>
        <th>Valid From</th>
        <th>Valid To</th>
        <th>Zone Result</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <% for(int i=0; i<zoneModel.getZoneItems().size();i++){%>
        <% ZoneItemT item = zoneModel.getZoneItems().get(i);%>
        <%if(item.visibilidad){%>
        <tr>
        <td><%= item.getProductName()%></td>
        <td><%= item.getOriginPrefix()%></td>
        <td><%= item.getDestinationPrefix()%></td>
        <td><%= item.getValidFrom()%></td>
        <td><%= item.getValidTo()%></td>
        <td><table>
                <tr><th>Zone Item Name</th></tr>
                <tr><td><%= item.getZoneResult().getZoneName()%></td></tr>
            </table></td>
        <td><a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Item',<%=item.getId()%>);"><i class="glyphicon glyphicon-pencil"></i></a>
        <a data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/zoneModels','-4,<%=item.getId()%>')"><i class="glyphicon glyphicon-trash"></i></a></td>
        </tr>
        <%}%>
    <%}%>
    </tbody>
</table>


