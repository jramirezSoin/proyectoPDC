<%-- 
    Document   : holidayView
    Created on : Aug 21, 2019, 8:50:46 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.HolidayItemT"%>
<%@page import="datos.HolidayT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HolidayT holiday = (HolidayT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= holiday.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/holiday', 'Holiday')">Holiday Calendar</a></li>
                    <li><span><%= holiday.getName()%></span></li>
                </ul>
            </div>
        </div>
    </div>
</div>
            <!-- page title area end -->
<div class="main-content-inner">
        <div class="row">
            <div class="col-xs-12 col-md-12">
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Holiday Calendar Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/holiday','Holiday Calendar',-1);">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/holiday','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= holiday.getDescription()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-12">
                <div class="card mt-5">
                    <div class="card-body ">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Holidays</h4>
                            <div class="search-box">
                                <input type="text" name="search" id="BuscaHolidayItem" placeholder="Search..." onkeypress=" if(event.keyCode===13){buscar('BuscaHolidayItem','Principal');}" required>
                                <i class="ti-close" onclick="$('#BuscaHolidayItem').val(''); buscar('BuscaHolidayItem','Principal');"></i>
                            </div>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/holiday','Holiday','-3');">Add</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/holiday','Holiday','-5');">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>                
            <% for(int i=0; i<holiday.getHolidayItems().size();i++){%>
            <% HolidayItemT item = holiday.getHolidayItems().get(i);%>
            <%if(item.visibilidad){%>                
            <div class="col-xs-12 col-md-4">
                <div class="card mt-5">
                    <div class="card-body sbg1 text-white">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0 text-white"><%= item.getName()%></h4>
                            <i class="ti-pencil-alt" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/holiday','Holiday',<%=item.getId()%>);"></i>
                            <i class="ti-close" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/holiday','-4,<%=item.getId()%>')"></i>    
                        </div>
                        <div
                            <dl class="row">
                                  <dt class="col-sm-3">Day</dt><dd class="col-sm-9"><%= item.getDay()%></dd>
                                  <dt class="col-sm-3">Month</dt><dd class="col-sm-9"><%= item.getMonth()%></dd>
                                  <%if(!item.getYear().equals("")){%>
                                  <dt class="col-sm-3">Year</dt><dd class="col-sm-9"><%= item.getYear()%></dd>
                                  <%}%>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
            <%}%>
        </div>                                                               
</div>

