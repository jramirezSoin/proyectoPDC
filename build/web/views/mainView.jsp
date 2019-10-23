<%-- 
    Document   : mainView
    Created on : Oct 8, 2019, 2:07:02 PM
    Author     : Joseph Ramírez
--%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="datos.Cambio"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<Cambio> cambios = (ArrayList<Cambio>)request.getSession().getAttribute("cambios");%>
<%ArrayList<ListaT> errores = (ArrayList<ListaT>)request.getSession().getAttribute("errores");%>
<%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("files");%>

<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1">Import/Export</h4>
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
                            <h4 class="header-title mb-0"></h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" onclick="importar();">Import</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" onclick="exportar()">Export</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <div id="alertaIE" class="alert alert-info" style="display: none" role="alert">
                                    <strong>Loading... </strong>Waiting the Import/Export
                                </div>
                                <table class="table text-center">
                                    <thead class="text-uppercase">
                                        <th></th>
                                        <th>File</th>
                                        <th></th>
                                    </thead>
                                    <tbody>
                                <%int cont=0;%>
                                <%for(ListaT constante: constants){%>
                                        <tr>
                                            <td><div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input listFileChecks" id="customCheck-<%=cont%>">
                                                <label class="custom-control-label" for="customCheck-<%=cont%>"></label>
                                            </div></td>
                                            <td><%=constante.valor%></td>
                                            <td>
                                                <%if(ControlFunctions.CambioContiene(cambios,constante.valor)){%>
                                                <span class="badge badge-pill badge-primary">Changed</span>
                                                <%}%>
                                            </td>
                                        </tr>
                                <%cont++;%>        
                                <%}%>
                                    </tbody>
                                </table>    
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-md-6">
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Report</h4>
                        </div>
                        <div>
                            <div class="alert-items">
                                <%if(errores!=null && errores.size()!=0){
                                for(ListaT error : errores){
                                    if(error.unit.equals("error")){
                                %>
                                <div class="alert alert-danger" role="alert">
                                    <strong><%=error.valor.split(",")[0]%>: </strong> <%=error.valor.split(",")[1]%>
                                </div>
                                <%}else if(error.unit.equals("info")){%>
                                <div class="alert alert-info" role="alert">
                                    <strong><%=error.valor.split(",")[0]%>: </strong> <%=error.valor.split(",")[1]%>
                                </div>
                                <%}else{%>
                                <div class="alert alert-success" role="alert">
                                    <strong><%=error.valor.split(",")[0]%>: </strong> <%=error.valor.split(",")[1]%>
                                </div>
                                <%}}}%>
                            </div>
                        </div>
                    </div>    
                </div>
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Changes</h4>
                        </div>
                        <div>
                            <%if(cambios!=null & cambios.size()!=0){%>
                            <div class="single-table">
                                <div class="table-responsive">
                                    <table class="table text-center">
                                        <thead class="text-uppercase">
                                        <tr>
                                            <th>Change</th>
                                            <th>Date</th>
                                            <th>File</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for(Cambio cambio: cambios){%>
                                            <tr>
                                            <td><%= cambio.getCambio()%></td>
                                            <td><%= cambio.getFecha()%></td>
                                            <td><%= cambio.getArchivo()%></td>
                                            </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                                </div>    
                            </div>
                            <%}%>        
                        </div>
                    </div>    
                </div>            
            </div>    
        </div>                                                               
</div>

