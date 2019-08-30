<%-- 
    Document   : chargeEventForm
    Created on : Aug 27, 2019, 4:20:49 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ChargeOfferingT"%>
<%@page import="datos.ChargeEventMapT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% ChargeEventMapT chargeEvent = (ChargeEventMapT) request.getSession().getAttribute("add");%>
<%if(chargeEvent==null){chargeEvent = ((ChargeOfferingT)request.getSession().getAttribute("principal")).getChargeEvents().get(index);}%>
<form style="margin: 20px;" id="formulaire">
<%ChargeOfferingT charge= (ChargeOfferingT)request.getSession().getAttribute("principal");%>    
<%ArrayList<ListaT> filtro= new ArrayList<>(); 
    if(!charge.getProductSpecName().equals(""))filtro.add(new ListaT("productSpecName",charge.getProductSpecName()));
    else filtro.add(new ListaT("customerSpecName","Account"));%>
    <% ArrayList<ListaT> events = ControlFunctions.getListaFiltro((String)ControlPath.attributeSpecMapClick, filtro,"eventRUMSpec");%>
<div class="form-group row">
    <label>Type<select class="custom-select" onclick="getTypeOptions();" id="Type">
    <option value="ONE_TIME">ONE TIME</option>
    <%if(!charge.getOfferType().equals("ITEM")){%>
    <option value="FOLD">FOLD</option>
    <option value="ROLLOVER">ROLLOVER</option>
    <option value="RECURRING">RECURRING</option>
    <option value="REMITTANCE">REMITTANCE</option>
    <option value="BILL_TIME_DISCOUNT">BILL TIME DISCOUNT</option>
    <%}%>
    </select></label>
</div>
    
<div class="form-group row">
    <label>Event Name<select class="custom-select" id="-eventName">
    </select></label>
</div> 

    <script>
        var dic = {
        <%for(ListaT t: events){%>
            <%t.unit="name";%>
            <%t.valor=t.valor.replaceAll("_ERS", "");%>
            "<%=t.valor%>":"<%= ControlFunctions.Buscar((String)ControlPath.eventAttributeSpecClick,t,"eventType")%>",
        <%}%>
        };
        
        function getTypeOptions(){
            $("#-eventName").html("");
            for (key in dic) {
                if(dic[key]==$("#Type").val())
                $("#-eventName").append("<option value='"+key+"'>"+key+"</option>");
            }
            
        };
        
        getTypeOptions();
    </script>
    
</form>
