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
<label>Permitted Name<select class="custom-select" id="-permittedName">
        <%ArrayList<ListaT> attributes = ControlFunctions.getLista(ControlPath.attributeSpecMapClick);%>
        <%for(ListaT constante : attributes){
            constante.valor= constante.valor.replaceAll("_ASM","");
        %>
        <option <%=(chargeRate.getPermittedName().equals(constante.valor)?"selected":"")%> value="<%=constante.valor%>"><%=constante.valor%></option>
        <%}%>
</select></label>
</div>
<div class="form-group row">
<label>Permitted Name<select class="custom-select" id="-eventName">
</select></label>
</div>
<div class="form-group row">
<label>Tax Time<select class="custom-select" id="-taxTime">
        <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("taxTime");%>
        <%for(ListaT constante : constants){%>
        <option <%=(chargeRate.getTaxTime().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
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
        <%constants = ControlFunctions.LeerConstante("taxCode");%>
        <%for(ListaT constante : constants){%>
        <option <%=(chargeRate.getTaxCode().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
</select></label>
</div>
<div class="form-group row">
<label>RUM<select class="custom-select" id="-applicableRums">
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
    <script>
        function getRum(){
            var parameters= { 'filtro' : 'name;<%=chargeRate.getPermittedName()%>_ASM,eventRUMSpec;,name;<%=chargeRate.getEventName()%>_ERS,rumSpec;',
            'funcion' : 'getListaFiltroDeep',
            'tipo' : '<%=ControlPath.attributeSpecMapClick%>',
            'buscar' : 'rumName',
            'compara' : '<%=chargeRate.getApplicableRums()%>'}
            consulta("-applicableRums",parameters);
        }
        function getEvent(){
            var parameters= { 'filtro' : 'name;<%=chargeRate.getPermittedName()%>_ASM,eventRUMSpec;',
            'funcion' : 'getListaFiltroDeep',
            'tipo' : '<%=ControlPath.attributeSpecMapClick%>',
            'buscar' : 'name',
            'compara' : '<%=chargeRate.getEventName()%>_ERS',
            'replace' : '_ERS;'}
            consulta("-eventName",parameters);
        }
        getEvent();
        getRum();
    </script>