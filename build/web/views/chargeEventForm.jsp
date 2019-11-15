<%-- 
    Document   : chargeEventForm
    Created on : Aug 27, 2019, 4:20:49 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
<%@page import="datos.ChargeOfferingT"%>
<%@page import="datos.ChargeEventMapT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% ChargeEventMapT chargeEvent = (ChargeEventMapT) request.getSession().getAttribute("add");%>
<%if(chargeEvent==null){chargeEvent = ((ChargeOfferingT)request.getSession().getAttribute("principal")).getChargeEvents().get(index);}%>
<form style="margin: 20px;" id="formulaire">
    <%ChargeOfferingT charge= (ChargeOfferingT)request.getSession().getAttribute("principal");%>    
    
    <%ArrayList<ListaT> filtro= new ArrayList<>(); 
    if(!charge.getProductSpecName().equals(""))filtro.add(new ListaT("productSpecName",charge.getProductSpecName()));
    else filtro.add(new ListaT("customerSpecName","Account"));%>
    <% ArrayList<ListaT> events = ControlFunctions.getListaFiltro((String)ControlPath.attributeSpecMapClick,user, filtro,new ListaT("eventRUMSpec","name"));%>
    <% ArrayList<ListaT> rates = ControlFunctions.getLista((String)ControlPath.chargeRateClick,user);%>
    <% ArrayList<ListaT> rateEvents = ControlFunctions.getLista((String)ControlPath.chargeRateClick,user,"","eventName",false);%>
    <% ArrayList<ListaT> rollovers = ControlFunctions.getLista((String)ControlPath.rolloverClick,user);%>

<div class="form-group row">
    <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("eventType");%>
    <label>Type<select class="custom-select" onclick="getTypeOptions(dic,'-eventName','Type'); getTypeOptions(dic2,'-chargeRatePlanName','-eventName');" id="Type">
    <%for(ListaT constante : constants){%>
        <%if(!charge.getOfferType().equals("ITEM") || (charge.getOfferType().equals("ITEM") && constante.unit.equals("ITEM"))){%>
            <option value="<%=constante.valor%>"><%=constante.valor%></option>
        <%}%>
    <%}%>
    </select></label>
</div>
    
<div class="form-group row">
    <label>Event Name<select class="custom-select" onclick="getTypeOptions(dic2,'-chargeRatePlanName','-eventName');" id="-eventName">
    </select></label>
</div> 
    
<div class="form-group row">
    <label>Charge Rate Plan<select class="custom-select" id="-chargeRatePlanName">
    </select></label>
</div>
    
<div class="form-group row">
    <%constants = ControlFunctions.LeerConstante("valid");%>
    <label>Made Charge if<select class="custom-select"  id="-valid">
    <%for(ListaT constante : constants){%>
    <option <%=(chargeEvent.getValid()==Integer.parseInt(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
    <%}%>
    </select></label>
</div>
    
</form>

<script>
var dic = {
<%
    rateEvents.add(new ListaT("","EventBillingCycleRolloverMonthly"));
    for(ListaT t: events){%>
    <%t.valor=t.valor.replaceAll("_ERS", "");
       if(rateEvents.contains(t)){
        t.unit="name";
    %>
    "<%=t.valor%>":"<%= ControlFunctions.Buscar(ControlPath.eventAttributeSpecClick,user,t,"eventType")%>",
    <%}%>        
<%}%>
};

var dic2 = {
<%for(int i=0;i<rates.size();i++){%>
    <%rates.get(i).unit="name";%>
    "<%=rates.get(i).valor%>":"<%=rateEvents.get(i).valor%>",
<%}%>
<%for(int i=0;i<rollovers.size();i++){%>
    "<%=rollovers.get(i).valor%>":"EventBillingCycleRolloverMonthly",
<%}%>    
};

function getTypeOptions(map,select1,select2){
    $("#"+select1).html("");
    for (key in map) {
        if(map[key]==$("#"+select2).val())
        $("#"+select1).append("<option value='"+key+"'>"+key+"</option>");
    }

};
</script>
<script>
$('#Type option[value="'+dic["<%=chargeEvent.getEventName()%>"]+'"]').prop('selected', true);
getTypeOptions(dic,"-eventName","Type");
$('#-eventName option[value="<%=chargeEvent.getEventName()%>"]').prop('selected', true);
getTypeOptions(dic2,'-chargeRatePlanName','-eventName');
$('#-chargeRatePlanName option[value="<%=chargeEvent.getChargeRatePlanName()%><%=chargeEvent.getRolloverRatePlanName()%>"]').prop('selected', true);

</script>