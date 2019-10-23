<%-- 
    Document   : bundledView
    Created on : Aug 16, 2019, 3:09:25 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ListaT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.BundledItemT"%>
<%@page import="datos.BundledT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% BundledT bundled = (BundledT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= bundled.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/bundled', 'Bundle')">Bundle</a></li>
                    <li><span><%= bundled.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Bundle Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle','-1');">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/bundled','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                              <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= bundled.getDescription()%></dd>
                              <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= bundled.getPricingProfileName()%></dd>
                              <dt class="col-sm-3">Time Range</dt><dd class="col-sm-9"><%= ControlFunctions.getParseDate(bundled.getTimeRange().split("/")[0]) %>-<%= ControlFunctions.getParseDate(bundled.getTimeRange().split("/")[1]) %></dd>
                              <dt class="col-sm-3"><%= (bundled.getCustomerSpecName().equals(""))?"Product":"Customer"%> Spec Name</dt><dd class="col-sm-9"><%= (bundled.getCustomerSpecName().equals(""))?bundled.getProductSpecName():bundled.getCustomerSpecName()%></dd>
                              <dt class="col-sm-3">Bill on Purchase</dt><dd class="col-sm-9"><%= bundled.getBillOnPurchase()%></dd>
                              <dt class="col-sm-3">Customize</dt><dd class="col-sm-9"><%= bundled.getCustomize()%></dd>
                              <dt class="col-sm-3">Group Balance Elements</dt><dd class="col-sm-9"><%= bundled.getGroupBalanceElements()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-12 mt-5">                                        
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Bundle Items</h4>
                            <div class="search-box">
                             <input type="text" name="search" id="BuscaBundleItem" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaBundleItem','Principal');}" required>
                             <i class="ti-close" onclick="$('#BuscaBundleItem').val(''); buscar('BuscaBundleItem','Principal');"></i>
                            </div>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle Item','-3');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle Item','-5');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
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
                                    <%String s= ((item.getChargeOfferingName().equals(""))?"/alteration":"/charge");%>
                                    <%ListaT buscar= new ListaT("name",((item.getChargeOfferingName().equals(""))?item.getAlterationOfferingName():item.getChargeOfferingName()));%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=item.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=item.getId()%>"></label>
                                            </div></td>
                                        <td><a href="#" onclick="hacerClick(this,'<%=s%>','<%=ControlFunctions.Buscar(ControlPath.chargeOfferingClick, buscar, "id")%>')"><%= (item.getChargeOfferingName().equals(""))?item.getAlterationOfferingName().replaceAll("_", " "):item.getChargeOfferingName().replaceAll("_", " ")%></a></td>
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
                                        <td>
                                            <ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/bundled','Bundle Item',<%=item.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/bundled','-4,<%=item.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
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







