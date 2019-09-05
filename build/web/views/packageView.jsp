<%-- 
    Document   : packageView
    Created on : Aug 23, 2019, 4:45:45 PM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
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
            <div class="col-xs-12 col-md-6">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Package Information</h4>
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
            
            <div class="col-xs-12 col-md-10">
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Balance Specification</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/package','Balance','-3,2');">Add</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/package','Balance','-5,2');">Edit</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-4">Name</dt><dd class="col-sm-8">Account Balance Group</dd>
                            </dl>
                            <%if(pack.getBalances().size()!=0){%>
                            <dl class="row">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                        <th>Balance</th>
                                        <th>Floor</th>
                                        <th>Ceiling</th>
                                        <th>Threshold</th>
                                        <th>Threshold</th>
                                        <th></th>
                                    </thead>
                                    <tbody>
                                <%for(BalanceSpecT balance: pack.getBalances()){%>                
                                        <tr>
                                            <td><%=balance.getBalanceElementName()%></td>
                                            <td><%=balance.getFloor()%></td>
                                            <td><%=balance.getCeil()%></td>
                                            <td><%=balance.getThreshold1()%></td>
                                            <td><%=balance.getThreshold2()%></td>
                                            <td>
                                                <ul class="d-flex justify-content-center">
                                                    <li class="mr-3"><i class="ti-pencil-alt" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/package','Balance','2,<%=balance.getId()%>');"></i></li>
                                                    <li><i class="ti-close" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/package','-4,2,<%=balance.getId()%>')"></i></li>    
                                                </ul>
                                                
                                            </td>
                                        </tr>
                                <%}%>
                                    </tbody>
                                </table>    
                            </dl>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-12">
                <div class="card mt-5">
                    <div class="card-body ">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Bundles</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/package','Package Item','-3,1');">Add</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/package','Package Item','-5,1');">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%for(PackageItemT item: pack.getPackageItems()){%>
            <div class="col-xs-12 col-md-6">

                <div class="card mt-5">
                    <div class="card-body sbg1 text-white">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0 text-white">Bundle Offering</h4>
                            <i class="ti-pencil-alt" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/package','Package','1,<%=item.getId()%>');"></i>
                            <i class="ti-close" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/package','-4,1,<%=item.getId()%>')"></i>    
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-4"><%= item.getSpecName()%></dt><dd class="col-sm-8"><%= item.getProductSpecName()%></dd>
                                <dt class="col-sm-4">Balance Spec Name</dt><dd class="col-sm-8"><%= item.getBalanceSpecificationName()%></dd>                                
                            </dl>
                            <dl class="row">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                        <tr>
                                            <th>Name</th>
                                            <th>Bundle</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%for(ListaT t: item.getBundleProductOffering()){%>
                                        <% ListaT buscar= new ListaT("name",t.valor);%>
                                        <tr>
                                            <td><a href="#" style="color: #fff;" onclick="hacerClick(this,'/bundled',<%=ControlFunctions.Buscar(ControlPath.bundledClick, buscar, "id")%>)"><%=t.valor%></a></td>
                                            <td><%=t.unit%></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>                                                               
</div>
