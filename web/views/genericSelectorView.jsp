<%-- 
    Document   : genericSelectorView
    Created on : Oct 2, 2019, 2:07:45 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ModelDataT"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.GenericSelectorT"%>
<%@page import="datos.RuleT"%>
<%@page import="control.ControlPath"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% GenericSelectorT genericSelector = (GenericSelectorT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-rules-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= genericSelector.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/genericSelector', 'Generic Selector')">Generic Selector</a></li>
                    <li><span><%= genericSelector.getName()%></span></li>
                </ul>
            </div>
        </div>
        <%@include file="/views/userPanel.jsp" %>
    </div>
</div>
            <!-- page title area end -->
<div class="main-content-inner">
        <div class="row">
            <div class="col-xs-12 col-md-6">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Generic Selector Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/genericSelector','Generic Selector','-1');">edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/genericSelector','-4')">delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= genericSelector.getDescription()%></dd>
                                <dt class="col-sm-3">StereoType</dt><dd class="col-sm-9"><%= genericSelector.getStereoType()%></dd>
                                <dt class="col-sm-3"><%= (genericSelector.getCustomerSpecName().equals(""))?"Product":"Customer"%> Spec Name</dt><dd class="col-sm-9"><%= (genericSelector.getCustomerSpecName().equals(""))?genericSelector.getProductSpecName():genericSelector.getCustomerSpecName()%></dd>
                                <dt class="col-sm-3">Event</dt><dd class="col-sm-9"><%= genericSelector.getEventSpecName()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-6">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Model Data</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                    <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/genericSelector','Model Data','0,-1');">edit</button>
                            </div>
                        </div>
                        <div>
                            <div class="single-table">
                                <div class="table-responsive">
                                    <table class="table text-center">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Operator</th>
                                            <th>Value Type</th>
                                            <th>Default Value</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for(String s: genericSelector.getModelDatas().keySet()){%>
                                        <% ModelDataT model = genericSelector.getModelDatas().get(s);%>
                                            <tr>
                                            <td><%= model.getName()%></td>
                                            <td><%= model.getOperator()%></td>
                                            <td><%= model.getValueType()%></td>
                                            <td><%= model.getDefaultValue()%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                </div>    
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 mt-5">
                <div class="card mt-5">
                    <div class="card-body sbg1 text-white">
                        <div class="d-flex justify-content-between mb-3">
                            <h4 class="header-title mb-0 text-white">Valid From</h4>
                            <select class="custom-select col-sm-6" id="periodRule" onclick="crpRel();">
                            </select>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <i class="ti-pencil" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/genericSelector','Date Range','0,-1');"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <%for(int i=0; i<genericSelector.getValidityPeriods().size();i++){%>
                <script>
                    $("#periodRule").append("<option selected value='<%=i%>'><%=ControlFunctions.getParseDate(genericSelector.getValidityPeriods().get(i))%></option>");
                </script>
                <div class="card PeriodRules" id="select-<%=i%>">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Rules</h4>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/genericSelector','Rule','-3,<%=i%>');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/genericSelector','Rule','-5,<%=i%>');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                    <tr>
                                        <th></th>
                                        <th>Name</th>
                                        <th>ResultName</th>
                                        <%for(String model: genericSelector.getModelDatas().keySet()){%>
                                        <th><%=model%>[<%=genericSelector.getModelDatas().get(model).getOperator()%>]</th>
                                        <%}%>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(int j=0; j<genericSelector.getRules().size();j++){%>
                                    <% RuleT rule = genericSelector.getRules().get(j);%>
                                    <%if(rule.visibilidad && rule.getValidityPeriod()==i){%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=rule.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=rule.getId()%>"></label>
                                            </div></td>
                                        <td><%= rule.getName()%></td>
                                        <td><%= rule.getResultName()%></td>
                                        <%for(String ruleModel: rule.getModels().keySet()){%>
                                        <td><%=rule.getModels().get(ruleModel).valor%></td>
                                        <%}%>
                                        <td>
                                            <ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/genericSelector','Rule',<%=rule.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/genericSelector','-4,<%=rule.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
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
                <%}%>
            </div>  
        </div>                                                               
</div>                                
<script>
 function crpRel(){
    $(".PeriodRules").hide();
    $("#select-"+$("#periodRule").val()).show();}
    crpRel();
</script>
