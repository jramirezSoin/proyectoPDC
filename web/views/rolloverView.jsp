<%-- 
    Document   : rolloverView
    Created on : Aug 13, 2019, 11:30:18 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.RolloverT"%>
<% RolloverT rollover = (RolloverT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= rollover.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/rollover', 'Rollover')">Rollover</a></li>
                    <li><span><%= rollover.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Rollover Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/rollover','Rollover','-1');">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/rollover','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-4">Pricing Profile Name</dt><dd class="col-sm-8"><%= rollover.getPricingProfileName()%></dd>
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
                                <dt class="col-sm-4">Start Date</dt><dd class="col-sm-8"><%= rollover.getStartDate()%></dd>
                                <dt class="col-sm-4">End Date</dt><dd class="col-sm-8"><%= rollover.getEndDate()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Rollover Pop Model <small>Rollover Charge</small></h4>
                        </div>
                        <div>
                            <dl class="row">
                                      <dt class="col-sm-4">Glid</dt><dd class="col-sm-8"><%= rollover.getGlid()%></dd>
                                      <dt class="col-sm-4">Unit Of Measure</dt><dd class="col-sm-8"><%= rollover.getUnitOfMeasure()%></dd>
                                      <dt class="col-sm-4">Balance Element</dt><dd class="col-sm-8"><%= rollover.getBalanceElementName()%></dd>
                                      <dt class="col-sm-4">Rollover Units</dt><dd class="col-sm-8"><%= rollover.getRolloverUnits()%></dd>
                                      <dt class="col-sm-4">Rollover Max Units</dt><dd class="col-sm-8"><%= rollover.getRolloverMaxUnits()%></dd>
                                      <dt class="col-sm-4">Rollover Count</dt><dd class="col-sm-8"><%= rollover.getRolloverCount()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>                            
        </div>                                                               
</div>







