<%-- 
    Document   : alterationOfferingView
    Created on : Oct 25, 2019, 2:16:29 PM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.ListaT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.AlterationOfferingT"%>
<%@page import="datos.AlterationEventMapT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% AlterationOfferingT alterationOffer = (AlterationOfferingT) request.getSession().getAttribute("principal");%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= alterationOffer.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/alterationOffering', 'Alteration Offering')">Alteration Offering</a></li>
                    <li><span><%= alterationOffer.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Alteration Offering Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/alterationOffering','Alteration Offering','-1');">edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/alterationOffering','-4')">delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= alterationOffer.getDescription()%></dd>
                                <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= alterationOffer.getPricingProfileName()%></dd>
                                <dt class="col-sm-3">Time Range</dt><dd class="col-sm-9"><%= ControlFunctions.getParseDate(alterationOffer.getTimeRange().split("/")[0]) %>-<%= ControlFunctions.getParseDate(alterationOffer.getTimeRange().split("/")[1]) %></dd>
                                <dt class="col-sm-3"><%= ((alterationOffer.getProductSpecName().equals(""))?"Customer SpecName":"Product SpecName")%></dt><dd class="col-sm-9"><%= ((alterationOffer.getProductSpecName().equals(""))?alterationOffer.getCustomerSpecName():alterationOffer.getProductSpecName())%></dd>
                                <dt class="col-sm-3">Offer Type</dt><dd class="col-sm-9"><%= alterationOffer.getOfferType()%></dd>
                                <dt class="col-sm-3">Priority</dt><dd class="col-sm-9"><%= alterationOffer.getPriority()%></dd>
                                <dt class="col-sm-3">Purchase Max</dt><dd class="col-sm-9"><%= alterationOffer.getPurchaseMax()%></dd>
                                <dt class="col-sm-3">Own Max</dt><dd class="col-sm-9"><%= alterationOffer.getOwnMax()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-12 mt-5">                                        
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Alteration Events</h4>
                            <div class="search-box">
                             <input type="text" name="search" id="BuscaAlterationEvent" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaAlterationEvent','Principal');}" required>
                             <i class="ti-close" onclick="$('#BuscaAlterationEvent').val(''); buscar('BuscaAlterationEvent','Principal');"></i>
                            </div>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/alterationOffering','Zone Item','-3');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/alterationOffering','Zone Item','-5');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                    <tr>
                                        <th></th>
                                        <th>Event</th>
                                        <th>Alteration Rate Plan</th>
                                        <th>Valid Both</th>
                                        <th>Invalid Start</th>
                                        <th>Invalid Both</th>
                                        <th>Snowball</th>
                                        <th>Target Engine</th>
                                        <th>Alteration</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(int i=0; i<alterationOffer.getAlterationEvents().size();i++){%>
                                    <% AlterationEventMapT item = alterationOffer.getAlterationEvents().get(i);%>
                                    <% ListaT buscar= new ListaT("name",item.getAlterationRatePlanName());%>
                                    <% ListaT buscar2= new ListaT("name",item.getAlterationRatePlanSelectorName());%>
                                    <%if(item.visibilidad){%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=item.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=item.getId()%>"></label>
                                            </div></td>
                                        <td><%= item.getEventName()%></td>
                                        <%if(!item.getAlterationRatePlanName().equals("")){%>
                                        <td><a href="#" onclick="hacerClick(this,'/alterationRatePlan',<%=ControlFunctions.Buscar(ControlPath.alterationRateClick,user, buscar, "id")%>)"><%= item.getAlterationRatePlanName()%></a></td>
                                        <%}else if(!item.getAlterationRatePlanSelectorName().equals("")){%>
                                        <td><a href="#" onclick="hacerClick(this,'/alterationRateSelector',<%=ControlFunctions.Buscar(ControlPath.alterationRateClick,user, buscar2, "id")%>)"><%= item.getAlterationRatePlanSelectorName()%></a></td>
                                        <%}%>
                                        <td><%= item.getValidAtStartNotValidAtEnd()%></td>
                                        <td><%= item.getNotValidAtStartValidAtEnd()%></td>
                                        <td><%= item.getNotValidAtStartNotValidAtEnd()%></td>
                                        <td><%= item.isSnowball()%></td>
                                        <td><%= item.getTargetEngine()%></td>
                                        <td><%= ((item.getValid()==1)?"Never":(((item.getValid()==2)?"Is Cancelled":((item.getValid()==3)?"Is Inactive":"Inactive or Cancelled"))))%></td>
                                        <td>
                                            <ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/alterationOffering','Zone Item',<%=item.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/alterationOffering','-4,<%=item.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
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
