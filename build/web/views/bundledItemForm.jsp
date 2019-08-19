<%-- 
    Document   : bundledItemForm
    Created on : Aug 19, 2019, 8:54:05 AM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.BundledT"%>
<%@page import="datos.BundledItemT"%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% BundledItemT zoneItem = (BundledItemT) request.getSession().getAttribute("add");%>
<%if(zoneItem==null){zoneItem = ((BundledT)request.getSession().getAttribute("principal")).getBundledItems().get(index);}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label >Offer</label>
<input readonly="" class="form-control" type="text" id="-chargeOfferingName" placeholder="Charge Offering" value="<%=zoneItem.getChargeOfferingName()%>"/>
</div>
<div class="form-group row">
<label >Discount</label>
<input readonly="" class="form-control" type="text" id="-alterationOfferingName" placeholder="Alteration Offering" value="<%=zoneItem.getAlterationOfferingName()%>"/>
</div>
<div class="form-group row">
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.chargeOfferingClick);%>
<label>Offers<select class="form-control" onclick="$('#-chargeOfferingName').val($(this).val()); $('#-alterationOfferingName').val('');">
        <%for(int j=0;j<products.size();j++){%>
        <option <%=(products.get(j).valor.equals(zoneItem.getChargeOfferingName()))?"Selected":""%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
        <%}%>
</select></label>
</div>
<div class="form-group row">
<% ArrayList<ListaT> products2 = ControlFunctions.getLista((String)ControlPath.alterationOfferingClick);%>
<label>Discount<select class="form-control" onclick="$('#-alterationOfferingName').val($(this).val()); $('#-chargeOfferingName').val('');">
        <%for(int j=0;j<products2.size();j++){%>
        <option <%=(products2.get(j).valor.equals(zoneItem.getAlterationOfferingName()))?"Selected":""%> value="<%=products2.get(j).valor%>"><%=products2.get(j).valor%></option>
        <%}%>
</select></label>
</div>
<div id="-purchaseStart">
<h2><small>Purchase Start</small></h2>
<div class="form-group row">
<label >Offset</label>
<input class="form-control" type="text" id="-offset" placeholder="Offset" value="<%=zoneItem.getPurchaseStart().id%>"/>
</div>
<div class="form-group row">
<label>Unit</label>
<input class="form-control" type="text" id="-unit" placeholder="Unit" value="<%=zoneItem.getPurchaseStart().unit%>"/>
</div>
<div class="form-group row">
<label>Mode</label>
<input class="form-control" type="text" id="-mode" placeholder="Mode" value="<%=zoneItem.getPurchaseStart().valor%>"/>
</div>
</div>
<hr>
<div id="-purchaseEnd">    
<h2><small>Purchase End</small></h2>    
<div class="form-group row">
<label >Offset</label>
<input class="form-control" type="text" id="-offset" placeholder="Offset" value="<%=zoneItem.getPurchaseEnd().id%>"/>
</div>
<div class="form-group row">
<label>Unit</label>
<input class="form-control" type="text" id="-unit" placeholder="Unit" value="<%=zoneItem.getPurchaseEnd().unit%>"/>
</div>
<div class="form-group row">
<label>Mode</label>
<input class="form-control" type="text" id="-mode" placeholder="Mode" value="<%=zoneItem.getPurchaseEnd().valor%>"/>
</div>
</div>
<hr>
<div id="-usageStart">
<h2><small>Usage Start</small></h2>
<div class="form-group row">
<label >Offset</label>
<input class="form-control" type="text" id="-offset" placeholder="Offset" value="<%=zoneItem.getUsageStart().id%>"/>
</div>
<div class="form-group row">
<label>Unit</label>
<input class="form-control" type="text" id="-unit" placeholder="Unit" value="<%=zoneItem.getUsageStart().unit%>"/>
</div>
<div class="form-group row">
<label>Mode</label>
<input class="form-control" type="text" id="-mode" placeholder="Mode" value="<%=zoneItem.getUsageStart().valor%>"/>
</div>
</div>
<hr>
<div id="-usageEnd">    
<h2><small>Usage End</small></h2>    
<div class="form-group row">
<label >Offset</label>
<input class="form-control" type="text" id="-offset" placeholder="Offset" value="<%=zoneItem.getUsageEnd().id%>"/>
</div>
<div class="form-group row">
<label>Unit</label>
<input class="form-control" type="text" id="-unit" placeholder="Unit" value="<%=zoneItem.getUsageEnd().unit%>"/>
</div>
<div class="form-group row">
<label>Mode</label>
<input class="form-control" type="text" id="-mode" placeholder="Mode" value="<%=zoneItem.getUsageEnd().valor%>"/>
</div>
</div>
<hr>
<div id="-cycleStart">
<h2><small>Cycle Start</small></h2>    
<div class="form-group row">
<label >Offset</label>
<input class="form-control" type="text" id="-offset" placeholder="Offset" value="<%=zoneItem.getCycleStart().id%>"/>
</div>
<div class="form-group row">
<label>Unit</label>
<input class="form-control" type="text" id="-unit" placeholder="Unit" value="<%=zoneItem.getCycleStart().unit%>"/>
</div>
<div class="form-group row">
<label>Mode</label>
<input class="form-control" type="text" id="-mode" placeholder="Mode" value="<%=zoneItem.getCycleStart().valor%>"/>
</div>
</div>
<hr>
<div id="-cycleEnd">
<h2><small>Cycle End</small></h2>    
<div class="form-group row">
<label >Offset</label>
<input class="form-control" type="text" id="-offset" placeholder="Offset" value="<%=zoneItem.getCycleEnd().id%>"/>
</div>
<div class="form-group row">
<label>Unit</label>
<input class="form-control" type="text" id="-unit" placeholder="Unit" value="<%=zoneItem.getCycleEnd().unit%>"/>
</div>
<div class="form-group row">
<label>Mode</label>
<input class="form-control" type="text" id="-mode" placeholder="Mode" value="<%=zoneItem.getCycleEnd().valor%>"/>
</div>
</div>
<hr>
<div class="form-group row">    
<label>Status</label>
<input class="form-control" type="text" id="-status" placeholder="Status" value="<%=zoneItem.getStatus()%>"/>
</div>
<div class="form-group row">    
<label>Status Code</label>
<input class="form-control" type="text" id="-statusCode" placeholder="Status Code" value="<%=zoneItem.getStatusCode()%>"/>
</div>
<div class="form-group row">    
<label>Quantity</label>
<input class="form-control" type="text" id="-quantity" placeholder="Quantity" value="<%=zoneItem.getQuantity()%>"/>
</div>
<div class="form-group row">    
<label>Purchase Adjustment</label>
<input class="form-control" type="text" id="-purchaseChargeAdjustment" placeholder="Purchase Adjustment" value="<%=zoneItem.getPurchaseChargeAdjustment()%>"/>
</div>
<div class="form-group row">    
<label>Usage Adjustment</label>
<input class="form-control" type="text" id="-usageChargeAdjustment" placeholder="Usage Adjustment" value="<%=zoneItem.getUsageChargeAdjustment()%>"/>
</div>
<div class="form-group row">    
<label>Cycle Adjustment</label>
<input class="form-control" type="text" id="-cycleChargeAdjustment" placeholder="Cycle Adjustment" value="<%=zoneItem.getCycleChargeAdjustment()%>"/>
</div>



</form>
