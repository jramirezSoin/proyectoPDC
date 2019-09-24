<%-- 
    Document   : chargeRateForm
    Created on : Sep 2, 2019, 4:45:27 PM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ratePlan.ChargeRatePlanT"%>
<% ChargeRatePlanT chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("add");%>
<% if(chargeRate==null){chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!chargeRate.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=chargeRate.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=chargeRate.getDescription()%>"/>
</div>
<div class="form-group row">
<label>Tax Time<select class="custom-select" id="-taxTime">
        <option <%=(chargeRate.getTaxTime().equals("EVENT_TIME"))?"Selected":""%> value="EVENT_TIME">EVENT_TIME</option>
        <option <%=(chargeRate.getTaxTime().equals("BILLING_TIME"))?"Selected":""%> value="BILLING_TIME">BILLING_TIME</option>
        <option <%=(chargeRate.getTaxTime().equals("NONE"))?"Selected":""%> value="NONE">NONE</option>
</select></label>
</div>
<script>
    $("#-taxTime").on("click", function(){
        if($(this).val()=="NONE"){
            $("#taxCode").hide();
        }else{
            $("#taxCode").show();
        }
    });
    
</script>
<div class="form-group row" id="taxCode">
<label>Tax Code<select class="custom-select" id="-taxCode">
        <option <%=(chargeRate.getTaxCode().equals("IV"))?"Selected":""%> value="IV">IV</option>
        <option <%=(chargeRate.getTaxCode().equals("CR"))?"Selected":""%> value="CR">CR</option>
        <option <%=(chargeRate.getTaxCode().equals("NU"))?"Selected":""%> value="NU">NU</option>
        <option <%=(chargeRate.getTaxCode().equals("IV-CR-NU"))?"Selected":""%> value="IV-CR-NU">IV-CR-NU</option>
        <option <%=(chargeRate.getTaxCode().equals("IV-CR"))?"Selected":""%> value="IV-CR">IV-CR</option>
        <option <%=(chargeRate.getTaxCode().equals("CR-NU"))?"Selected":""%> value="CR-NU">CR-NU</option>
        <option <%=(chargeRate.getTaxCode().equals("NORM"))?"Selected":""%> value="NORM">NORM</option>
</select></label>
</div>
<%ArrayList<ListaT> filtro= new ArrayList<>();
    filtro.add(new ListaT("name",chargeRate.getPermittedName()+"_ASM"));
    filtro.add(new ListaT("eventRUMSpec",""));
    filtro.add(new ListaT("name",chargeRate.getEventName()+"_ERS"));
    filtro.add(new ListaT("rumSpec","")); %>
<% ArrayList<ListaT> rums = ControlFunctions.getListaFiltroDeep((String)ControlPath.attributeSpecMapClick, filtro,"rumName");%>
<div class="form-group row">
<label>RUM<select class="custom-select" id="-applicableRums">
        <%for(ListaT rum: rums){%>
        <option <%=(chargeRate.getApplicableRums().equals(rum.valor))?"Selected":""%> value="<%=rum.valor%>"><%=rum.valor%></option>
        <%}%>
</select></label>
</div>
<div id="-subscriberCurrency">
<div class="form-group row">
<% ArrayList<ListaT> impactCategories = ControlFunctions.getListaBalance("currency");%>
    <label>Currency<select class="custom-select" id="-currencyName">
    <%for(int j=0;j<impactCategories.size();j++){%>
    <option <%=(impactCategories.get(j).valor.equals(chargeRate.getSubscriberCurrency().getCurrencyName()))?"selected":""%> value="<%=impactCategories.get(j).valor%>"><%=impactCategories.get(j).valor%></option>
    <%}%>
    </select></label>
</div>
</div>    
</form>
