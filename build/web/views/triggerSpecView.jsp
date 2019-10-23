<%-- 
    Document   : triggerSpecView
    Created on : Aug 9, 2019, 3:44:32 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.TriggerSpecT"%>
<%@page import="datos.ExpressionT"%>
<%@page import="control.ControlPath"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% TriggerSpecT triggerSpec = (TriggerSpecT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= triggerSpec.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/triggerSpec', 'Trigger')">Trigger</a></li>
                    <li><span><%= triggerSpec.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Trigger Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/triggerSpec','Trigger','-1');">edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/triggerSpec','-4')">delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= triggerSpec.getDescription()%></dd>
                                  <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= triggerSpec.getPricingProfileName()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-12 mt-5">                                        
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Expressions</h4>
                            <div class="search-box">
                             <input type="text" name="search" id="BuscaExpression" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaExpression','Principal');}" required>
                             <i class="ti-close" onclick="$('#BuscaZoneItem').val(''); buscar('BuscaZoneItem','Principal');"></i>
                            </div>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/triggerSpec','Expression','-3');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/triggerSpec','Expression','-5');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                    <tr>
                                        <th></th>
                                        <th>Tipo</th>
                                        <th>Operator</th>
                                        <th>Value</th>
                                        <th>Balance Element</th>
                                        <th>Right Operand</th>
                                        <th>Binary Operator</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                        <% for(int i=0; i<triggerSpec.getExpressions().size();i++){%>
                                        <% ExpressionT item = triggerSpec.getExpressions().get(i);%>
                                        <%if(item.visibilidad){%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=item.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=item.getId()%>"></label>
                                            </div></td>
                                        <td><%= item.getTipo()%></td>
                                        <td><%=item.getOperator()%></td>
                                        <td><%=item.getValue()%></td>
                                        <td><%=item.getBalanceElementName()%></td>                                        
                                        <td><%=((item.getTipo().equals("complexTriggerExpression"))?"Charge Expression":"")%></td>
                                        <td><%=item.getBinaryOperator()%></td>
                                        <td><ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/triggerSpec','Expression',<%=item.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/triggerSpec','-4,<%=item.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
                                            </ul></td>
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







