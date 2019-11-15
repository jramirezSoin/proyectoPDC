<%-- 
    Document   : alterationOfferingForm
    Created on : Oct 25, 2019, 2:16:55 PM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.User"%>
<%@page import="control.ControlFunctions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="datos.AlterationOfferingT"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<% AlterationOfferingT alterationOffering = (AlterationOfferingT) request.getSession().getAttribute("add");%>
<% if(alterationOffering==null){alterationOffering = (AlterationOfferingT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!alterationOffering.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=alterationOffering.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=alterationOffering.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="-timeRange">Time Range</label>
<input class="form-control" type="text" id="-timeRange" placeholder="Time Range" value="<%=alterationOffering.getTimeRange()%>"/>
</div>
<%if(alterationOffering.getAlterationEvents().size()==0){%>
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.attributeSpecMapClick,user);%>
    <div class="form-group row">
    <label><select class="custom-select" id="-specName">
            <%for(int j=0;j<products.size();j++){%>
            <%products.get(j).valor= products.get(j).valor.replaceAll("_ASM","");%>
            <option <%=(((alterationOffering.getProductSpecName()+" "+alterationOffering.getCustomerSpecName()).contains(products.get(j).valor))?"selected":"")%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
            <%}%>
    </select></label>
    </div>
    <div class="form-group row">
    <label>Offer Type<select class="custom-select" id="-offerType">
            <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("offerType");%>
            <%for(ListaT constante : constants){%>
            <option <%=(alterationOffering.getOfferType().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
    </select></label>
    </div>
<%}%>    
<div class="form-group row">
<label for="-priority">Priority</label>
<input class="form-control" type="number" id="-priority" placeholder="Priority" value="<%=alterationOffering.getPriority()%>"/>
</div>   
<div class="form-group row">
<label>Partial<select class="custom-select" id="-partial">
        <option <%=(alterationOffering.isPartial())?"Selected":""%> value="true">true</option>
        <option <%=(!alterationOffering.isPartial())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label for="-purchaseMax">Purchase Max</label>
<input class="form-control" type="text" id="-purchaseMax" placeholder="Purchase Max" value="<%=alterationOffering.getPurchaseMax()%>"/>
</div>
<div class="form-group row">
<label for="-ownMax">Own Max</label>
<input class="form-control" type="text" id="-ownMax" placeholder="Own Max" value="<%=alterationOffering.getOwnMax()%>"/>
</div>
</form>
