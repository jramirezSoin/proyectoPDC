<%-- 
    Document   : alterationRateForm
    Created on : Oct 17, 2019, 8:19:34 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.alteration.AlterationRatePlanT"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<% AlterationRatePlanT bundled = (AlterationRatePlanT) request.getSession().getAttribute("add");%>
<% if(bundled==null){bundled = (AlterationRatePlanT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!bundled.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=bundled.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=bundled.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="-pricingProfileName">Pricing Profile Name</label>
<input class="form-control" type="text" id="-pricingProfileName" placeholder="Pricing Profile Name" value="<%=bundled.getPricingProfileName()%>"/>
</div>
<div id="-arpDateRange">
<div class="form-group row">
<label for="-timeRange">Start Date</label>
<input class="form-control" type="text" id="-startDate" placeholder="Time Range" value="<%=bundled.getArpDateRange().getStartDate()%>"/>
</div>
<div class="form-group row">
<label for="-timeRange">Time Range</label>
<input class="form-control" type="text" id="-endDate" placeholder="Time Range" value="<%=bundled.getArpDateRange().getEndDate()%>"/>
</div>
</div>
</form>
