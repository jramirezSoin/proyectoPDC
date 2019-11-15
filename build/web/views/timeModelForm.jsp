<%-- 
    Document   : timeModelForm
    Created on : Aug 14, 2019, 11:55:11 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.TimeModelT"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<% TimeModelT timeModel = (TimeModelT) request.getSession().getAttribute("add");%>
<% if(timeModel==null){timeModel = (TimeModelT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!timeModel.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=timeModel.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<textarea class="form-control noresize" id="-description"><%=timeModel.getDescription()%></textarea>
</div>
<div class="form-group row">
<label for="pricingProfileName">Pricing Profile Name</label>
<input class="form-control" type="text" id="-pricingProfileName" placeholder="Pricing Profile Name" value="<%=timeModel.getPricingProfileName()%>"/>
</div>
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.holidayClick, user);%>
<div class="form-group row">
<label>Holiday Calendar<select class="custom-select" id="-holidayCalendarName">
        <%for(int j=0;j<products.size();j++){%>
        <option <%=(products.get(j).valor.equals(timeModel.getHolidayCalendarName()))?"Selected":""%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
        <%}%>
</select></label>
</div>  
<div id="-validityPeriod">
<div class="form-group row">
<label for="-validFrom">Valid From</label>
<input class="form-control" type="text" id="-validFrom" placeholder="Valid From" value="<%=timeModel.getValidFrom()%>"/>
</div>
</div>
</form>
