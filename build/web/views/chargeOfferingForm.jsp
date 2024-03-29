<%-- 
    Document   : chargeOfferingForm
    Created on : Aug 27, 2019, 4:20:31 PM
    Author     : Joseph Ram�rez
--%>

<%@page import="datos.User"%>
<%@page import="control.ControlFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="datos.ChargeOfferingT"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
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
<%if(chargeOffering.getChargeEvents().size()==0){%>
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.attributeSpecMapClick,user);%>
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
            <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("offerType");%>
            <%for(ListaT constante : constants){%>
            <option <%=(chargeOffering.getOfferType().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
    </select></label>
    </div>
<%}%>    
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
<div class="form-group row">
<label>Tax Supplier<select class="custom-select" id="-taxSupplier">
        <%ArrayList<ListaT> constants1 = ControlFunctions.LeerConstante("taxSupplier");%>
            <%for(ListaT constante : constants1){%>
            <option <%=(chargeOffering.getTaxSupplier().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
</select></label>
</div>
</form>
