<%-- 
    Document   : bundledForm
    Created on : Aug 19, 2019, 8:53:35 AM
    Author     : Joseph Ramírez
--%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.BundledT"%>
<% BundledT bundled = (BundledT) request.getSession().getAttribute("add");%>
<% if(bundled==null){bundled = (BundledT) request.getSession().getAttribute("principal");}%>
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
<div class="form-group row">
<label for="-timeRange">Time Range</label>
<input class="form-control" type="text" id="-timeRange" placeholder="Time Range" value="<%=bundled.getTimeRange()%>"/>
</div>
<div class="form-group row">
<label for="-customize">Customize</label>
<input class="form-control" type="text" id="-customize" placeholder="Customize" value="<%=bundled.getCustomize()%>"/>
</div>
<div class="form-group row">
<label>Bill On Purchase<select class="form-control" id="-billOnPurchase">
        <option <%=(bundled.getBillOnPurchase())?"Selected":""%> value="true">true</option>
        <option <%=(!bundled.getBillOnPurchase())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label>Group Balance Elements<select class="form-control" id="-groupBalanceElements">
        <option <%=(bundled.getGroupBalanceElements())?"Selected":""%> value="true">true</option>
        <option <%=(!bundled.getGroupBalanceElements())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<%if(bundled.getProductSpecName().equals("") && bundled.getCustomerSpecName().equals("")){%>
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.attributeSpecMapClick);%>
<div class="form-group row">
<label>Aplicable to<select class="form-control" id="-aplicable">
        <%for(int j=0;j<products.size();j++){%>
        <%products.get(j).valor= products.get(j).valor.replaceAll("_ASM","");%>
        <option <%=(products.get(j).valor.equals(bundled.getCustomerSpecName()) || products.get(j).valor.equals(bundled.getProductSpecName()))?"Selected":""%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
        <%}%>
</select></label>
</div>        
<%}%>
</form>
