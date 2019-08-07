<%-- 
    Document   : zoneModelView
    Created on : Aug 5, 2019, 4:21:25 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ZoneModelT"%>
<%@page import="datos.ZoneItemT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ZoneModelT zoneModel = (ZoneModelT) request.getSession().getAttribute("principal");%>
<h1>Zone Model <%= zoneModel.getName()%></h1>
<a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Model',-1);">edit</a>
<a data-toggle="modal" data-target="#eliminarModal">delete</a>
<p>Description: <%= zoneModel.getDescription()%></p>
<p>Internal Id: <%= zoneModel.getInternalId()%></p>
<p>PriceList Name: <%= zoneModel.getPriceListName()%></p>
<p>Obsolete: <%= zoneModel.isObsolete()%></p>
<h2>Zone Items</h2>
        <form>
            <input type="search" id="BuscaZoneItem" placeholder="Buscar"/>
            <input type="button" onclick="buscar('BuscaZoneItem','Principal')" value="Buscar">
        </form>
<table>
    <tr>
        <th>Product Name</th>
        <th>Origin Prefix</th>
        <th>Destination Prefix</th>
        <th>Valid From</th>
        <th>Valid To</th>
        <th>Zone Result</th>
        <th></th>
    </tr>
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
        <td><a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Item',<%=item.getId()%>);">edit</a></td>
        </tr>
        <%}%>
    <%}%>
</table>


