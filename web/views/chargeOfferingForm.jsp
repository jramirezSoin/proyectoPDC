<%-- 
    Document   : chargeOfferingForm
    Created on : Aug 27, 2019, 4:20:31 PM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="datos.ChargeOfferingT"%>
<% ChargeOfferingT chargeOffering = (ChargeOfferingT) request.getSession().getAttribute("add");%>
<% if(chargeOffering==null){chargeOffering = (ChargeOfferingT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!chargeOffering.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=chargeOffering.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=chargeOffering.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="-timeRange">Time Range</label>
<input class="form-control" type="text" id="-timeRange" placeholder="Time Range" value="<%=chargeOffering.getTimeRange()%>"/>
</div>
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.attributeSpecMapClick);%>
    <div class="form-group row">
    <label><select class="custom-select" id="-specName">
            <%for(int j=0;j<products.size();j++){%>
            <%products.get(j).valor= products.get(j).valor.replaceAll("_ASM","");%>
            <option <%=(((chargeOffering.getProductSpecName()+" "+chargeOffering.getCustomerSpecName()).contains(products.get(j).valor))?"selected":"")%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
            <%}%>
    </select></label>
    </div>
    <div class="form-group row">
    <label>Offer Type<select class="custom-select" id="-offerType">
            <option <%=((chargeOffering.getOfferType().equals("ITEM"))?"selected":"")%> value="ITEM">ITEM</option>
            <option <%=((chargeOffering.getOfferType().equals("SUBSCRIPTION"))?"selected":"")%> value="SUBSCRIPTION">SUBSCRIPTION</option>
            <option <%=((chargeOffering.getOfferType().equals("SYSTEM"))?"selected":"")%> value="SYSTEM">SYSTEM</option>
    </select></label>
    </div>
<div class="form-group row">
<label for="-priority">Priority</label>
<input class="form-control" type="number" id="-priority" placeholder="Priority" value="<%=chargeOffering.getPriority()%>"/>
</div>   
<div class="form-group row">
<label>Partial<select class="custom-select" id="-partial">
        <option <%=(chargeOffering.isPartial())?"Selected":""%> value="true">true</option>
        <option <%=(!chargeOffering.isPartial())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label for="-purchaseMax">Purchase Max</label>
<input class="form-control" type="text" id="-purchaseMax" placeholder="Purchase Max" value="<%=chargeOffering.getPurchaseMax()%>"/>
</div>
<div class="form-group row">
<label for="-ownMax">Own Max</label>
<input class="form-control" type="text" id="-ownMax" placeholder="Own Max" value="<%=chargeOffering.getOwnMax()%>"/>
</div>
</form>
