<%-- 
    Document   : rolloverForm
    Created on : Aug 13, 2019, 11:30:01 AM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.RolloverT"%>
<% RolloverT rollover = (RolloverT) request.getSession().getAttribute("add");%>
<% if(rollover==null){rollover = (RolloverT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!rollover.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=rollover.getName()%>"/>
</div>
<div class="form-group row">
<label for="pricingProfileName">Pricing Profile Name</label>
<input class="form-control" type="text" id="-pricingProfileName" placeholder="Pricing Profile Name" value="<%=rollover.getPricingProfileName()%>"/>
</div>
<div id="-dateRange">
<div class="form-group row">
<label for="-startDate">Start Date</label>
<input class="form-control" type="text" id="-startDate" placeholder="Start Date" value="<%=rollover.getStartDate()%>"/>
</div>
<div class="form-group row">
<label for="-endDate">End Date</label>
<input class="form-control" type="text" id="-endDate" placeholder="End Date" value="<%=rollover.getEndDate()%>"/>
</div>
<div id="-rolloverPopModel">
    <div id="-rolloverCharge">
<div class="form-group row">
<label for="-glid">Glid</label>
<input class="form-control" type="text" id="-glid" placeholder="Glid" value="<%=rollover.getGlid()%>"/>
</div>
<div class="form-group row">
<label for="-unitOfMeasure">Unit of Measure</label>
<input class="form-control" type="text" id="-unitOfMeasure" placeholder="Unit of Measure" value="<%=rollover.getUnitOfMeasure()%>"/>
</div>
<div class="form-group row">
<% ArrayList<ListaT> impactCategories = ControlFunctions.getLista((String)ControlPath.balanceElementClick);%>
    <label>Balance Element<select class="form-control" id="-balanceElementName">
    <%for(int j=0;j<impactCategories.size();j++){%>
    <option <%=(impactCategories.get(j).valor.equals(rollover.getBalanceElementName()))?"selected":""%> value="<%=impactCategories.get(j).valor%>"><%=impactCategories.get(j).valor%></option>
    <%}%>
    </select></label>
</div>
<div class="form-group row">
<label for="-rolloverUnits">Rollover Units</label>
<input class="form-control" type="text" id="-rolloverUnits" placeholder="Rollover Units" value="<%=rollover.getRolloverUnits()%>"/>
</div>
<div class="form-group row">
<label for="-rolloverMaxUnits">Rollover Max Units</label>
<input class="form-control" type="text" id="-rolloverMaxUnits" placeholder="Rollover Max Units" value="<%=rollover.getRolloverMaxUnits()%>"/>
</div>
<div class="form-group row">
<label for="-rolloverCount">Rollover Count</label>
<input class="form-control" type="number" id="-rolloverCount" placeholder="rolloverCount" value="<%=rollover.getRolloverCount()%>"/>
</div>
    </div>    
</div>
</div>
</form>
