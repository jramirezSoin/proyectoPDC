<%-- 
    Document   : packageView
    Created on : Aug 23, 2019, 4:45:45 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.PackageItemT"%>
<%@page import="datos.BalanceSpecT"%>
<%@page import="datos.PackageT"%>

<% PackageT pack = (PackageT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= pack.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/package', 'Package')">Package</a></li>
                    <li><span><%= pack.getName()%></span></li>
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
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/package','Rollover','-1');">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/package','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-4">Name</dt><dd class="col-sm-8"><%= pack.getName()%></dd>
                                <dt class="col-sm-4">Description</dt><dd class="col-sm-8"><%= pack.getDescription()%></dd>
                                <dt class="col-sm-4">Pricing Profile Name</dt><dd class="col-sm-8"><%= pack.getPricingProfileName()%></dd>
                                <dt class="col-sm-4">Bill on Purchase</dt><dd class="col-sm-8"><%= pack.isBillOnPurchase()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <%for(BalanceSpecT balance: pack.getBalances()){%>                
            <div class="col-xs-12 col-md-4">
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Balance Specification</h4>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-4">Name</dt><dd class="col-sm-8">Account Balance Group</dd>
                                <dt class="col-sm-4">Balance Element</dt><dd class="col-sm-8"><%= balance.getBalanceElementName()%></dd>
                                <dt class="col-sm-4">Start Date</dt><dd class="col-sm-8"><%= balance.getCeil()%></dd>
                                <dt class="col-sm-4">End Date</dt><dd class="col-sm-8"><%= balance.getFloor()%></dd>
                                <dt class="col-sm-4">End Date</dt><dd class="col-sm-8"><%= balance.getThreshold1()%></dd>
                                <dt class="col-sm-4">End Date</dt><dd class="col-sm-8"><%= balance.getThreshold2()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>                
            <%for(PackageItemT item: pack.getPackageItems()){%>                
            <div class="col-xs-12">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Bundle Offering</h4>
                        </div>
                        <div>
                            <dl class="row">
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>                                                               
</div>
