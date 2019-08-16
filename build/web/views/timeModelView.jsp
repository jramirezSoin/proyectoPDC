<%-- 
    Document   : timeModelView
    Created on : Aug 14, 2019, 8:32:27 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ListaT"%>
<%@page import="datos.TimeModelTagT.TimeSpecT"%>
<%@page import="datos.TimeModelT"%>
<%@page import="datos.TimeModelTagT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% TimeModelT timeModel = (TimeModelT) request.getSession().getAttribute("principal");%>
<h1>Time Model <small><%= timeModel.getName()%></small></h1>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Model','-1');">edit</button>
    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/timeModel','-4')">delete</button>
</div>
<hr>
<dl class="row">
  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= timeModel.getDescription()%></dd>
  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= timeModel.getInternalId()%></dd>
  <dt class="col-sm-3">PriceList Name</dt><dd class="col-sm-9"><%= timeModel.getPriceListName()%></dd>
  <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= timeModel.getPricingProfileName()%></dd>
  <dt class="col-sm-3">Holiday Calendar Name</dt><dd class="col-sm-9"><%= timeModel.getHolidayCalendarName()%></dd>
</dl>
<h1><small>Validity period</small></h1>
<hr>
<dl class="row">
  <dt class="col-sm-3">Valid From</dt><dd class="col-sm-9"><%= timeModel.getValidFrom()%></dd>
</dl>
<hr>
<h2><small>Time Model Tags</small></h2>
    <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" id="BuscaTimeItem" placeholder="Search">
        </div>
        <button type="button" onclick="buscar('BuscaTimeItem','Principal')" class="btn btn-default">Search</button>
    </form>
<div class="btn-group btn-group-sm" role="group" aria-label="..."> 
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Item','-3');">add</button>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Item','-5');">edit</button>
</div>
<table class="table">
    <thead>
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
        <td><input type="checkbox" class="form-check-input itemChecks" id="itemCheck-<%=item.getId()%>"></td>
        <td><%= item.getTagName().replaceAll("_"," ")%></td>
        <td>
            <table class="table ">
                <thead>
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
                                <%= t.valor%>
                                <%}%>
                            
                        </td>
                    </tr>
                    <%}%>
                    <%if(spec.getMonthsOfYear().size()>0){%>
                    <tr>
                        <th>Months</th>
                        <td colspan="4">
                            
                                <%for(ListaT t: spec.getMonthsOfYear()){%>
                                <%= t.valor%>
                                <%}%>
                            
                        </td>
                    </tr>
                    <%}%>
                    <tr><td colspan="5">&nbsp</td></tr>
                    
                <%}%>
                
                </tbody>
            </table>                
        </td>
        <td><a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/timeModel','Time Model Tag',<%=item.getId()%>);"><i class="glyphicon glyphicon-pencil"></i></a></td><td>
        <a data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/timeModel','-4,<%=item.getId()%>')"><i class="glyphicon glyphicon-trash"></i></a></td>
        </tr>
        <%}%>
    <%}%>
    </tbody>
</table>
