<%-- 
    Document   : chargeOfferingView
    Created on : Aug 27, 2019, 2:54:22 PM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.ListaT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ChargeOfferingT"%>
<%@page import="datos.ChargeEventMapT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ChargeOfferingT chargeOffer = (ChargeOfferingT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= chargeOffer.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/charge', 'Charge Offering')">Charge Offering</a></li>
                    <li><span><%= chargeOffer.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Charge Offering Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/charge','Charge Offering','-1');">edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/charge','-4')">delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= chargeOffer.getDescription()%></dd>
                                <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= chargeOffer.getPricingProfileName()%></dd>
                                <dt class="col-sm-3">Time Range</dt><dd class="col-sm-9"><%= ControlFunctions.getParseDate(chargeOffer.getTimeRange().split("/")[0]) %>-<%= ControlFunctions.getParseDate(chargeOffer.getTimeRange().split("/")[1]) %></dd>
                                <dt class="col-sm-3"><%= ((chargeOffer.getProductSpecName().equals(""))?"Customer SpecName":"Product SpecName")%></dt><dd class="col-sm-9"><%= ((chargeOffer.getProductSpecName().equals(""))?chargeOffer.getCustomerSpecName():chargeOffer.getProductSpecName())%></dd>
                                <dt class="col-sm-3">Offer Type</dt><dd class="col-sm-9"><%= chargeOffer.getOfferType()%></dd>
                                <dt class="col-sm-3">Priority</dt><dd class="col-sm-9"><%= chargeOffer.getPriority()%></dd>
                                <dt class="col-sm-3">Purchase Max</dt><dd class="col-sm-9"><%= chargeOffer.getPurchaseMax()%></dd>
                                <dt class="col-sm-3">Own Max</dt><dd class="col-sm-9"><%= chargeOffer.getOwnMax()%></dd>
                                <dt class="col-sm-3">Tax Supplier</dt><dd class="col-sm-9"><%= chargeOffer.getTaxSupplier()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-12 mt-5">                                        
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Charge Events</h4>
                            <div class="search-box">
                             <input type="text" name="search" id="BuscaChargeEvent" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaChargeEvent','Principal');}" required>
                             <i class="ti-close" onclick="$('#BuscaChargeEvent').val(''); buscar('BuscaChargeEvent','Principal');"></i>
                            </div>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/charge','Zone Item','-3');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/charge','Zone Item','-5');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                    <tr>
                                        <th></th>
                                        <th>Event</th>
                                        <th>Charge Rate Plan</th>
                                        <th>Timezone Mode</th>
                                        <th>prorate First</th>
                                        <th>prorate Last</th>
                                        <th>Min Quantity</th>
                                        <th>Target Engine</th>
                                        <th>Charge</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(int i=0; i<chargeOffer.getChargeEvents().size();i++){%>
                                    <% ChargeEventMapT item = chargeOffer.getChargeEvents().get(i);%>
                                    <% ListaT buscar= new ListaT("name",item.getChargeRatePlanName());%>
                                    <% ListaT buscar2= new ListaT("name",item.getRolloverRatePlanName());%>
                                    <%if(item.visibilidad){%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=item.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=item.getId()%>"></label>
                                            </div></td>
                                        <td><%= item.getEventName()%></td>
                                        <%if(!item.getChargeRatePlanName().equals("")){%>
                                        <td><a href="#" onclick="hacerClick(this,'/chargeRate',<%=ControlFunctions.Buscar(ControlPath.chargeRateClick, buscar, "id")%>)"><%= item.getChargeRatePlanName()%></a></td>
                                        <%}else if(!item.getRolloverRatePlanName().equals("")){%>
                                        <td><a href="#" onclick="hacerClick(this,'/rollover',<%=ControlFunctions.Buscar(ControlPath.rolloverClick, buscar2, "id")%>)"><%= item.getRolloverRatePlanName()%></a></td>
                                        <%}%>
                                        <td><%= item.getTimezoneMode()%></td>
                                        <td><%= item.getProrateFirst()%></td>
                                        <td><%= item.getProrateLast()%></td>
                                        <td><%= item.getMinQuantity()%></td>
                                        <td><%= item.getTargetEngine()%></td>
                                        <td><%= ((item.getValid()==1)?"Never":(((item.getValid()==2)?"Is Cancelled":((item.getValid()==3)?"Is Inactive":"Inactive or Cancelled"))))%></td>
                                        <td>
                                            <ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/charge','Zone Item',<%=item.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/charge','-4,<%=item.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
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


