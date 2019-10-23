<%-- 
    Document   : alterationRateView
    Created on : Oct 17, 2019, 8:18:56 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.alteration.AlterationConfigurationT"%>
<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.alteration.AlterationRatePlanT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% AlterationRatePlanT alterationRate = (AlterationRatePlanT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= alterationRate.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/alterationRatePlan', 'Alteration Rate Plan')">Alteration Rate Plan</a></li>
                    <li><span><%= alterationRate.getName()%></span></li>
                </ul>
            </div>
        </div>
        <%@include file="/views/userPanel.jsp" %>        
    </div>
</div>
                
<div class="main-content-inner">
    <div class="row">
        <div class="col-xs-12 col-md-8">

            <div class="card mt-5">
                <div class="card-body">
                    <div class="d-flex justify-content-between mb-5">
                        <h4 class="header-title mb-0">Alteration Rate Plan Information</h4>
                        <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/alterationRatePlan','Alteration Rate Plan',-1);">Edit</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/alterationRatePlan','-4')">Delete</button>
                        </div>
                    </div>
                    <div>
                        <dl class="row">
                            <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= alterationRate.getDescription()%></dd>
                            <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= alterationRate.getPricingProfileName()%></dd>
                            <dt class="col-sm-3">Tax Code</dt><dd class="col-sm-9"><%= alterationRate.getTaxCode()%></dd>
                        </dl>
                    </div>
                </div>
            </div>
                        
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="card mt-5">
                <div class="card-body">
                    <div>
                        <dl class="row">
                            <dt class="col-sm-5">Start Date</dt><dd class="col-sm-7"><%=ControlFunctions.getParseDate(alterationRate.getArpDateRange().getStartDate())%></dd>
                            <dt class="col-sm-5">End Date</dt><dd class="col-sm-7"><%= ControlFunctions.getParseDate(alterationRate.getArpDateRange().getEndDate())%></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-md-12">                
            <div class="card mt-5">
                <div class="card-body">
                    <ul class="nav nav-tabs">
                        <%for(AlterationConfigurationT configurations: alterationRate.getArpDateRange().getAlterationConfigurations()){%>
                            <li class="nav-item">
                                <a class="nav-link <%=((configurations.getId()==0)?"active":"")%>" onclick="composite('/alterationRatePlan','arpComposite-0','<%=configurations.getId()%>'); changeNav(this);" href="#arpComposite-0">Rule <%=configurations.getId()%></a>
                            </li>    
                        <%}%>
                    </ul>
                    <div id="arpComposite-0">

                    </div>
                </div>
            </div>        
        </div>            
    </div>                
</div>
<script>
    composite('/alterationRatePlan','arpComposite-0','0');
    function changeNav(nodo){
        $(".nav-link").removeClass("active");
        $(nodo).addClass("active");
    }
</script>                    
