<%-- 
    Document   : zoneModelView
    Created on : Aug 5, 2019, 4:21:25 PM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="datos.ZoneModelT"%>
<%@page import="datos.ZoneItemT"%>
<%@page import="control.ControlPath"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ZoneModelT zoneModel = (ZoneModelT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= zoneModel.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/zoneModels', 'Zone Model')">Zone Model</a></li>
                    <li><span><%= zoneModel.getName()%></span></li>
                </ul>
            </div>
        </div>
        <%@include file="/views/userPanel.jsp" %>
    </div>
</div>
            <!-- page title area end -->
<div class="main-content-inner">
        <div class="row">
            <div class="col-xs-12 col-md-8">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Zone Model Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Model','-1');">edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/zoneModels','-4')">delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= zoneModel.getDescription()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-12 mt-5">                                        
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Zone Items</h4>
                            <div class="search-box">
                             <input type="text" name="search" id="BuscaZoneItem" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaZoneItem','Principal');}" required>
                             <i class="ti-close" onclick="$('#BuscaZoneItem').val(''); buscar('BuscaZoneItem','Principal');"></i>
                            </div>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Item','-3');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Item','-5');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                    <tr>
                                        <th></th>
                                        <th>Product Name</th>
                                        <th>Origin Prefix</th>
                                        <th>Destination Prefix</th>
                                        <th>Valid From</th>
                                        <th>Valid To</th>
                                        <th>Impact Category</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(int i=0; i<zoneModel.getZoneItems().size();i++){%>
                                    <% ZoneItemT item = zoneModel.getZoneItems().get(i);%>
                                    <%if(item.visibilidad){%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=item.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=item.getId()%>"></label>
                                            </div></td>
                                        <td><%= item.getProductName()%></td>
                                        <td><%= item.getOriginPrefix()%></td>
                                        <td><%= item.getDestinationPrefix()%></td>
                                        <td><%= ControlFunctions.getParseDate(item.getValidFrom())%></td>
                                        <td><%= ControlFunctions.getParseDate(item.getValidTo())%></td>
                                        <td><%= item.getZoneResult().getZoneName()%></td>
                                        <td>
                                            <ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/zoneModels','Zone Item',<%=item.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/zoneModels','-4,<%=item.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
                                            </ul>
                                        </td>
                                        </tr>
                                        <%}%>
                                    <%}%>
                                </tbody>
                            </table>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>  
        </div>                                                               
</div>







