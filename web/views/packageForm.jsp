<%-- 
    Document   : packageForm
    Created on : Aug 26, 2019, 11:44:05 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.PackageT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<% PackageT packageT = (PackageT) request.getSession().getAttribute("add");%>
<% if(packageT==null){packageT = (PackageT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!packageT.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=packageT.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=packageT.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="-pricingProfileName">Pricing Profile Name</label>
<input class="form-control" type="text" id="-pricingProfileName" placeholder="Pricing Profile Name" value="<%=packageT.getPricingProfileName()%>"/>
</div>
<div class="form-group row">
<label>Bill On Purchase<select class="custom-select" id="-billOnPurchase">
        <option <%=(packageT.isBillOnPurchase())?"Selected":""%> value="true">true</option>
        <option <%=(!packageT.isBillOnPurchase())?"Selected":""%> value="false">false</option>
</select></label>
</div>
</form>
