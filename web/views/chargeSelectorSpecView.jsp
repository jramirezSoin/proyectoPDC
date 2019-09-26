<%-- 
    Document   : chargeSelectorSpecForm
    Created on : Sep 24, 2019, 11:48:16 AM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="datos.ChargeSelectorSpecT"%>
<% ChargeSelectorSpecT chargeSelectorSpec = (ChargeSelectorSpecT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= chargeSelectorSpec.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/chargeSelectorSpec', 'ChargeSelectorSpec')">ChargeSelectorSpec</a></li>
                    <li><span><%= chargeSelectorSpec.getName()%></span></li>
                </ul>
            </div>
        </div>
    </div>
</div>
            <!-- page title area end -->
<div class="main-content-inner">
        <div class="row">
            <div class="col-xs-12 col-md-8">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Charge Selector Spec Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/chargeSelectorSpec','ChargeSelectorSpec','-1');">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/chargeSelectorSpec','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-4">Pricing Profile Name</dt><dd class="col-sm-8"><%= chargeSelectorSpec.getPricingProfileName()%></dd>
                                <dt class="col-sm-4">Description</dt><dd class="col-sm-8"><%= chargeSelectorSpec.getDescription()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-4">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Date Range</h4>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-4">Valid From</dt><dd class="col-sm-8"><%= ControlFunctions.getParseDate(chargeSelectorSpec.getValidFrom())%></dd>
                                <dt class="col-sm-4">Valid To</dt><dd class="col-sm-8"><%= ControlFunctions.getParseDate(chargeSelectorSpec.getValidTo())%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Charge Selector Spec</h4>
                        </div>
                        <div>
                            <dl class="row">
                                      <%String[] items = chargeSelectorSpec.getGlidName().split("\\|");%>
                                      <dt class="col-sm-4">Glid</dt><dd class="col-sm-8">
                                          <%for(String item: items){%>
                                          <span class="badge badge-pill badge-primary"><%=item%></span>
                                          <%}%>
                                      </dd>
                                      <dt class="col-sm-4">Balance Element</dt><dd class="col-sm-8">
                                          <%items= chargeSelectorSpec.getBalanceElementName().split("\\|");%>
                                          <%for(String item: items){%>
                                          <span class="badge badge-pill badge-success"><%=item%></span>
                                          <%}%>
                                      </dd>
                                      <dt class="col-sm-4">Pricing Name</dt><dd class="col-sm-8"><%= chargeSelectorSpec.getPricingName()%></dd>
                                      <dt class="col-sm-4">Time Model Tags</dt><dd class="col-sm-8">
                                          <%items= chargeSelectorSpec.getTimeModelTagName().split("\\|");%>
                                          <%for(String item: items){%>
                                          <span class="badge badge-pill badge-danger"><%=item%></span>
                                          <%}%>
                                      </dd>
                                      <dt class="col-sm-4">Zone Results</dt><dd class="col-sm-8"><%= chargeSelectorSpec.getZoneResult()%></dd>
                                      <dt class="col-sm-4">Event Conditions: <%=chargeSelectorSpec.getEventConditions().unit.replace("AnyEvent.","").toUpperCase()%></dt><dd class="col-sm-8"><%= chargeSelectorSpec.getEventConditions().valor.replaceAll("\\|"," ")%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>                            
        </div>                                                               
</div>
