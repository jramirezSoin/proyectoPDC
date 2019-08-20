<%-- 
    Document   : balanceElementView
    Created on : Aug 12, 2019, 3:31:51 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.RoundingRuleT"%>
<%@page import="datos.BalanceElementT"%>
<% BalanceElementT balance = (BalanceElementT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= balance.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/balanceElement', 'Balance Element')">Balance Element</a></li>
                    <li><span><%= balance.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Balance Element Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/balanceElement','Balance Element','-1');">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/balanceElement','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= balance.getDescription()%></dd>
                                  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= balance.getInternalId()%></dd>
                                  <dt class="col-sm-3">PriceList Name</dt><dd class="col-sm-9"><%= balance.getPriceListName()%></dd>
                                  <dt class="col-sm-3">Obsolete</dt><dd class="col-sm-9"><%= balance.isObsolete()%></dd>
                                  <dt class="col-sm-3">Code</dt><dd class="col-sm-9"><%= balance.getCode()%></dd>
                                  <dt class="col-sm-3">Num Code</dt><dd class="col-sm-9"><%= balance.getNumCode()%></dd>
                                  <dt class="col-sm-3">Symbol</dt><dd class="col-sm-9"><%= balance.getSymbol()%></dd>
                                  <dt class="col-sm-3">Transient Element</dt><dd class="col-sm-9"><%= balance.getTransientElement()%></dd>
                                  <dt class="col-sm-3">Foldable</dt><dd class="col-sm-9"><%= balance.isFoldable()%></dd>
                                  <dt class="col-sm-3">Counter</dt><dd class="col-sm-9"><%= balance.isCounter()%></dd>
                                  <dt class="col-sm-3">Consumption Rule</dt><dd class="col-sm-9"><%= balance.getConsumptionRule()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-12 mt-5">                                        
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Rounding Rules</h4>
                            <div class="search-box">
                             <input type="text" name="search" id="BuscaRounds" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaRounds','Principal');}" required>
                             <i class="ti-close" onclick="$('#BuscaRounds').val(''); buscar('BuscaRounds','Principal');"></i>
                            </div>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/balanceElement','Rounding Rule','-3');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/balanceElement','Rounding Rule','-5');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                    <tr>
                                        <th></th>
                                        <th>Precision</th>
                                        <th>Tolerance Min</th>
                                        <th>Tolerance Max</th>
                                        <th>Tolerance Percentage</th>
                                        <th>Rounding Mode</th>
                                        <th>Processing Stage</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(int i=0; i<balance.getRoundingRules().size();i++){%>
                                    <% RoundingRuleT item = balance.getRoundingRules().get(i);%>
                                    <%if(item.visibilidad){%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=item.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=item.getId()%>"></label>
                                            </div></td>
                                        <td><%= item.getPrecision()%></td>
                                        <td><%= item.getToleranceMin()%></td>
                                        <td><%= item.getToleranceMax()%></td>
                                        <td><%= item.getTolerancePercentage()%></td>
                                        <td><%= item.getRoundingMode()%></td>
                                        <td><%= item.getProcessingStage()%></td>
                                        <td>
                                            <ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/balanceElement','Rounding Rule',<%=item.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/balanceElement','-4,<%=item.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
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







