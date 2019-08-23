<%-- 
    Document   : rumConfigView
    Created on : Aug 12, 2019, 3:32:10 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.RumT"%>
<% RumT rum = (RumT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= rum.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/rumConfig', 'RUM')">RUM</a></li>
                    <li><span><%= rum.getName()%></span></li>
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
                            <h4 class="header-title mb-0">RUM Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/rumConfig','RUM','-1');">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/rumConfig','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                  <dt class="col-sm-3">Description</dt><dd class="col-sm-9">&nbsp<%= rum.getDescription()%></dd>
                                  <dt class="col-sm-3">Unit</dt><dd class="col-sm-9">&nbsp<%= rum.getUnit()%></dd>
                                  <dt class="col-sm-3">Rum Type</dt><dd class="col-sm-9"><%= rum.getRumType()%></dd>
                                  <dt class="col-sm-3">Rum Rounding</dt><dd class="col-sm-9">&nbsp<%= rum.getRumRounding()%></dd>
                                  <dt class="col-sm-3">Rum Code</dt><dd class="col-sm-9">&nbsp<%= rum.getRumCode()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>  
        </div>                                                               
</div>







