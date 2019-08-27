<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ZoneModelT"%>
<%@page import="datos.ZoneItemT"%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% ZoneItemT zoneItem = (ZoneItemT) request.getSession().getAttribute("add");%>
<%if(zoneItem==null){zoneItem = ((ZoneModelT)request.getSession().getAttribute("principal")).getZoneItems().get(index);}%>
<form style="margin: 20px;" id="formulaire">
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.attributeSpecMapClick);%>
<div class="form-group row">
<label>Product Name<select class="custom-select" id="-productName">
        <%for(int j=0;j<products.size();j++){%>
        <%products.get(j).valor= products.get(j).valor.replaceAll("_ASM","");%>
        <%if(!products.get(j).valor.equals("Account")){%>
        <option <%=(products.get(j).valor.equals(zoneItem.getProductName()))?"Selected":""%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
        <%}%>
        <%}%>
</select></label>
</div>
<div class="form-group row">
<label for="originPrefix">Origin Prefix</label>
<input class="form-control" type="number" id="-originPrefix" placeholder="Origin Prefix" value="<%=zoneItem.getOriginPrefix()%>"/>
</div>
<div class="form-group row">
<label for="destinationPrefix">Destination Prefix</label>
<input class="form-control" type="number" id="-destinationPrefix" placeholder="Destination Prefix" value="<%=zoneItem.getDestinationPrefix()%>"/>
</div>
<div class="form-group row">
<label for="validFrom">Valid From</label>
<input class="form-control" type="text" id="-validFrom" placeholder="Valid From" value="<%=zoneItem.getValidFrom()%>"/>
</div>
<div class="form-group row">
<label for="validTo">Valid To</label>
<input class="form-control" type="text" id="-validTo" placeholder="Valid To" value="<%=zoneItem.getValidTo()%>"/>
</div>
<div id="-zoneResult">
<div class="form-group row">
<% ArrayList<ListaT> impactCategories = ControlFunctions.getLista((String)ControlPath.impactCategoriesClick);%>

<label>Impact Category<select id="-zoneName">
        <%for(int j=0;j<impactCategories.size();j++){%>
        <option <%=(impactCategories.get(j).valor.equals(zoneItem.getZoneResult().getZoneName()))?"Selected":""%> value="<%=impactCategories.get(j).valor%>"><%=impactCategories.get(j).valor%></option>
        <%}%>
</select></label>

</div>
</div>
</form>
