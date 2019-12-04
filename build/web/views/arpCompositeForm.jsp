<%-- 
    Document   : arpCompositeForm
    Created on : Nov 1, 2019, 10:46:30 AM
    Author     : Joseph Ramírez
--%>
<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.alteration.AlterationConfigurationT"%>
<%@page import="datos.alteration.AlterationRatePlanT"%>
<%@page import="datos.User"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<%AlterationConfigurationT crp = (AlterationConfigurationT) request.getSession().getAttribute("composite");%>
<%AlterationRatePlanT charge = (AlterationRatePlanT) request.getSession().getAttribute("principal");%>
<form style="margin: 20px;" id="formulaire">
    <%ArrayList<ListaT> filtro= new ArrayList<ListaT>();%>
    <%filtro.add(new ListaT("pricingProfileName",charge.getPricingProfileName()));%>
    <% ArrayList<ListaT> triggers = ControlFunctions.getListaFiltro((String)ControlPath.triggerSpecClick,user, filtro);%>
    <div class="form-group row">
        <label>Triggers<select class="custom-select" id="-triggerSpecName">
        <%for(int j=0;j<triggers.size();j++){%>
        <option <%=triggers.get(j).valor.equals(crp.getTriggerSpecName())?"selected":""%> value="<%=triggers.get(j).valor%>"><%=triggers.get(j).valor%></option>
        <%}%>
        </select></label>
    </div>
    <% ArrayList<ListaT> chargers = ControlFunctions.getListaFiltro((String)ControlPath.chargeSelectorSpecClick,user, filtro);%>
    <div class="form-group row">
        <label>Charger Selector Specs<select class="custom-select" id="-chargeSelectorSpec">
        <%for(int j=0;j<chargers.size();j++){%>
        <option <%=chargers.get(j).valor.equals(crp.getChargeSelectorSpecName())?"selected":""%> value="<%=chargers.get(j).valor%>"><%=chargers.get(j).valor%></option>
        <%}%>
        </select></label>
    </div>
    <div id="-lowerBound">    
        <div class="form-group row" id="-numberTBExpression">
            <label for="-value">Lower Bound</label>
            <input class="form-control" type="number" id="-value" placeholder="Lower Bound" value="<%=crp.getArpCompositePopModel().getLowerBound().getLeftOperand().valor%>"/>
        </div>
    </div>
    <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("tierBasis");%>
    <div class="form-group row">
        <label>Tier Basis<select class="custom-select" id="-tierBasis">
        <%for(int j=0;j<constants.size();j++){%>
        <option <%=constants.get(j).unit.equals(crp.getArpCompositePopModel().getTierBasis().unit)?"selected":""%> value="<%=constants.get(j).unit%>"><%=constants.get(j).valor%></option>
        <%}%>
        </select></label>>
        <label for="-value">Value</label>
        <input class="form-control" type="text" id="-value" placeholder="Lower Bound" value="<%=crp.getArpCompositePopModel().getTierBasis().valor%>"/>
    </div>
    
</form>
