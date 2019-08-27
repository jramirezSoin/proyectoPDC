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
<% ArrayList<ListaT> buscar = (ArrayList<ListaT>) request.getSession().getAttribute("buscar");%>
<%if(zoneItem==null){zoneItem = ((BundledT)request.getSession().getAttribute("principal")).getBundledItems().get(index); buscar=new ArrayList<>();}%>
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
<% ArrayList<ListaT> products = ControlFunctions.getListaFiltro((String)ControlPath.chargeOfferingClick,buscar);%>
<label>Offers<select class="custom-select" onclick="$('#-chargeOfferingName').val($(this).val()); $('#-alterationOfferingName').val('');">
        <%for(int j=0;j<products.size();j++){%>
        <option <%=(products.get(j).valor.equals(zoneItem.getChargeOfferingName()))?"Selected":""%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
        <%}%>
</select></label>
</div>
<div class="form-group row">
<% ArrayList<ListaT> products2 = ControlFunctions.getListaFiltro((String)ControlPath.alterationOfferingClick, buscar);%>
<label>Discount<select class="custom-select" onclick="$('#-alterationOfferingName').val($(this).val()); $('#-chargeOfferingName').val('');">
        <%for(int j=0;j<products2.size();j++){%>
        <option <%=(products2.get(j).valor.equals(zoneItem.getAlterationOfferingName()))?"Selected":""%> value="<%=products2.get(j).valor%>"><%=products2.get(j).valor%></option>
        <%}%>
    </select></label>
</div>
<div class="according accordion-s3">
    <div class="card" id="-purchaseStart">
        <div class="card-header">
            <a class="collapsed card-link" data-toggle="collapse" href="#accordion1">Purchase Start</a>
        </div>
        <div id="accordion1" class="collapse" data-parent="#accordion2">
            <div class="card-body">
                <div class="form-group row">
                    <label >Offset</label>
                    <input class="form-control" type="number" id="-offset" placeholder="Offset" value="<%=zoneItem.getPurchaseStart().id%>"/>
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
        </div>
    </div>

    <div class="card" id="-purchaseEnd">
        <div class="card-header">
            <a class="collapsed card-link" data-toggle="collapse" href="#accordion2">Purchase End</a>
        </div>
        <div id="accordion2" class="collapse" data-parent="#accordion2">
            <div class="card-body">
                <div class="form-group row">
                    <label >Offset</label>
                    <input class="form-control" type="number" id="-offset" placeholder="Offset" value="<%=zoneItem.getPurchaseEnd().id%>"/>
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
        </div>
    </div>                                   
    <div class="card" id="-usageStart">
        <div class="card-header">
            <a class="collapsed card-link" data-toggle="collapse" href="#accordion3">Usage Start</a>
        </div>
        <div id="accordion3" class="collapse" data-parent="#accordion2">
            <div class="card-body">
                <div class="form-group row">
                    <label >Offset</label>
                    <input class="form-control" type="number" id="-offset" placeholder="Offset" value="<%=zoneItem.getUsageStart().id%>"/>
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
        </div>
    </div>


    <div class="card" id="-usageEnd">
        <div class="card-header">
            <a class="collapsed card-link" data-toggle="collapse" href="#accordion4">Usage End</a>
        </div>
        <div id="accordion4" class="collapse" data-parent="#accordion2">
            <div class="card-body">
                <div class="form-group row">
                    <label >Offset</label>
                    <input class="form-control" type="number" id="-offset" placeholder="Offset" value="<%=zoneItem.getUsageEnd().id%>"/>
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
        </div>
    </div>

    <div class="card" id="-cycleStart">
        <div class="card-header">
            <a class="collapsed card-link" data-toggle="collapse" href="#accordion5">Cycle Start</a>
        </div>
        <div id="accordion5" class="collapse" data-parent="#accordion2">
            <div class="card-body">
                <div class="form-group row">
                    <label >Offset</label>
                    <input class="form-control" type="number" id="-offset" placeholder="Offset" value="<%=zoneItem.getCycleStart().id%>"/>
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
        </div>
    </div>

    <div class="card" id="-cycleEnd">
        <div class="card-header">
            <a class="collapsed card-link" data-toggle="collapse" href="#accordion6">Cycle End</a>
        </div>
        <div id="accordion6" class="collapse" data-parent="#accordion2">
            <div class="card-body">
                 <div class="form-group row">
                <label >Offset</label>
                <input class="form-control" type="number" id="-offset" placeholder="Offset" value="<%=zoneItem.getCycleEnd().id%>"/>
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
        </div>
    </div>
</div>                                    

<div class="form-group row">    
    <label>Status</label>
    <input class="form-control" type="number" id="-status" placeholder="Status" value="<%=zoneItem.getStatus()%>"/>
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
