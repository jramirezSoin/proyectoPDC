<%-- 
    Document   : timeModelView
    Created on : Aug 14, 2019, 8:32:27 AM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="datos.TimeModelTagT.TimeSpecT"%>
<%@page import="datos.TimeModelT"%>
<%@page import="datos.TimeModelTagT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% TimeModelT timeModel = (TimeModelT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= timeModel.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/timeModel', 'Time Model')">Time Model</a></li>
                    <li><span><%= timeModel.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Time Model Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Model','-1');">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/timeModel','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= timeModel.getDescription()%></dd>
                                  <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= timeModel.getPricingProfileName()%></dd>
                                  <dt class="col-sm-3">Holiday Calendar Name</dt><dd class="col-sm-9"><%= timeModel.getHolidayCalendarName()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-4">
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Validity Period</h4>
                        </div>
                        <div>
                            <dl class="row">
                                  <dt class="col-sm-3">Valid From</dt><dd class="col-sm-9"><%= ControlFunctions.getParseDate(timeModel.getValidFrom())%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>   
            <div class="col-12 mt-5">                                        
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Time Model Tags</h4>
                            <div class="search-box">
                             <input type="text" name="search" id="BuscaTimeItem" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaTimeItem','Principal');}" required>
                             <i class="ti-close" onclick="$('#BuscaTimeItem').val(''); buscar('BuscaTimeItem','Principal');"></i>
                            </div>  
                        </div>    
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Model Tag','-3');">Add</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Model Tag','-5');">Edit</button>
                        </div>
                        <div class="single-table">
                            <div class="table-responsive">
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                    <tr>
                                        <th></th>
                                        <th>Tag Name</th>
                                        <th>Time Specifications</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <% for(int i=0; i<timeModel.getTimeModelTags().size();i++){%>
                                    <% TimeModelTagT item = timeModel.getTimeModelTags().get(i);%>
                                    <%if(item.visibilidad){%>
                                        <tr>
                                        <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="customCheck-<%=item.getId()%>">
                                                <label class="custom-control-label" for="customCheck-<%=item.getId()%>"></label>
                                            </div></td>
                                        <td><%= item.getTagName().replaceAll("_"," ")%></td>
                                        <td>
                                            <table class="table text-center">
                                                <thead class="text-uppercase">
                                                    <tr>
                                                    <th>Name</th>
                                                    <th>Description</th>
                                                    <th>Time of Day</th>
                                                    <th>Holiday</th>
                                                    <th>Days Of Month</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <% for(int j=0; j<item.getTimeSpecs().size();j++){%>
                                                    <% TimeSpecT spec = item.getTimeSpecs().get(j);%>
                                                    <tr>
                                                        <td><%=spec.getName().replaceAll("_"," ")%></td>
                                                        <td><%=spec.getDescription().replaceAll("_"," ")%></td>  
                                                        <td><%=spec.getTimeOfDay().replaceAll("_"," ")%></td>  
                                                        <td><%=spec.getHoliday().replaceAll("_"," ")%></td>
                                                        <td><%=spec.getDaysOfMonth().replaceAll("_"," ")%></td>
                                                    </tr>
                                                    <%if(spec.getDaysOfWeek().size()>0){%>
                                                    <tr>
                                                        <th>Days</th>
                                                        <td colspan="4">
                                                            
                                                                <%for(ListaT t: spec.getDaysOfWeek()){%>
                                                                <span class="badge badge-pill badge-primary bg1"><%= t.valor%></span>
                                                                <%}%>
                                                            
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                    <%if(spec.getMonthsOfYear().size()>0){%>
                                                    <tr>
                                                        <th>Months</th>
                                                        <td colspan="4">
                                                            
                                                                <%for(ListaT t: spec.getMonthsOfYear()){%>
                                                                <span class="badge badge-pill badge-primary bg2"><%= t.valor%></span>
                                                                <%}%>
                                                            
                                                        </td>
                                                    </tr>
                                                    <%}%>
                                                    <tr><td colspan="5">&nbsp</td></tr>
                                                    
                                                <%}%>
                                                
                                                </tbody>
                                            </table>                
                                        </td>
                                        <td>
                                            <ul class="d-flex justify-content-center">
                                                <li class="mr-3"><a href="#" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Model Tag',<%=item.getId()%>);" class="text-secondary"><i class="fa fa-edit"></i></a></li>
                                                <li><a href="#" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/timeModel','-4,<%=item.getId()%>')" class="text-danger"><i class="ti-trash"></i></a></li>
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







