<%-- 
    Document   : alterationEventForm
    Created on : Oct 25, 2019, 2:17:24 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
<%@page import="datos.AlterationOfferingT"%>
<%@page import="datos.AlterationEventMapT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% AlterationEventMapT alterationEvent = (AlterationEventMapT) request.getSession().getAttribute("add");%>
<%if(alterationEvent==null){alterationEvent = ((AlterationOfferingT)request.getSession().getAttribute("principal")).getAlterationEvents().get(index);}%>
<form style="margin: 20px;" id="formulaire">
    <%AlterationOfferingT alteration= (AlterationOfferingT)request.getSession().getAttribute("principal");%>    
    
    <%ArrayList<ListaT> filtro= new ArrayList<>(); 
    if(!alteration.getProductSpecName().equals(""))filtro.add(new ListaT("productSpecName",alteration.getProductSpecName()));
    else filtro.add(new ListaT("customerSpecName","Account"));%>
    <% ArrayList<ListaT> events = ControlFunctions.getListaFiltro((String)ControlPath.attributeSpecMapClick,user, filtro,new ListaT("eventRUMSpec","name"));%>
    <% ArrayList<ListaT> rates = ControlFunctions.getLista((String)ControlPath.alterationRateClick,user);%>
    <% ArrayList<ListaT> rollovers = ControlFunctions.getLista((String)ControlPath.alterationSelectorClick,user);%>
    <% ArrayList<ListaT> rateEvents = ControlFunctions.getLista((String)ControlPath.alterationSelectorClick,user,"","eventSpecName",false);%>

<div class="form-group row">
    <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("eventType");%>
    <label>Type<select class="custom-select" onclick="getTypeOptions(dic,'-eventName','Type'); getTypeOptions(dic2,'-alterationRatePlanName','-eventName');" id="Type">
    <%for(ListaT constante : constants){%>
        <%if(!alteration.getOfferType().equals("ITEM") || (alteration.getOfferType().equals("ITEM") && constante.unit.equals("ITEM"))){%>
            <option value="<%=constante.valor%>"><%=constante.valor%></option>
        <%}%>
    <%}%>
    </select></label>
    <input id="-eventName" onclick="checkScaled();" type="hidden" class="">
</div>
    
<div class="form-group row">
    <label>Event Name<select class="custom-select" id="-eventName">
    </select></label>
</div> 
    
<div class="form-group row">
    <label>Alteration Rate Plan<select class="custom-select" id="-alterationRatePlanName">
    </select></label>
</div>
    
<div class="form-group row">
    <%constants = ControlFunctions.LeerConstante("valid");%>
    <label>Made Alteration if<select class="custom-select"  id="-valid">
    <%for(ListaT constante : constants){%>
    <option <%=(alterationEvent.getValid()==Integer.parseInt(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
    <%}%>
    </select></label>
</div>
    
</form>

<script>
var dic = {
<%
    for(ListaT t: events){%>
    <%t.valor=t.valor.replaceAll("_ERS", "");
        t.unit="name";%>
    "<%=t.valor%>":"<%= ControlFunctions.Buscar(ControlPath.eventAttributeSpecClick,user,t,"eventType")%>",
<%}%>
};

var dic2 = {
<%for(int i=0;i<rates.size();i++){%>
    <%rates.get(i).unit="name";%>
    "<%=rates.get(i).valor%>":"",
<%}%>
<%for(int i=0;i<rollovers.size();i++){%>
    "<%=rollovers.get(i).valor%>":"<%=rateEvents.get(i).valor%>",
<%}%>    
};

function getTypeOptions(map,select1,select2){
    $("#"+select1).html("");
    for (key in map) {
        $("#"+select1).append("<option value='"+key+"'>"+key+"</option>");
    }

};
</script>
<script>
$('#Type option[value="'+dic["<%=alterationEvent.getEventName()%>"]+'"]').prop('selected', true);
getTypeOptions(dic,"-eventName","Type");
$('#-eventName option[value="<%=alterationEvent.getEventName()%>"]').prop('selected', true);
getTypeOptions(dic2,'-alterationRatePlanName','-eventName');
$('#-alterationRatePlanName option[value="<%=alterationEvent.getAlterationRatePlanName()%><%=alterationEvent.getAlterationRatePlanSelectorName()%>"]').prop('selected', true);

</script>
