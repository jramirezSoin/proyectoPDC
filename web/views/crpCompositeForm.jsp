<%-- 
    Document   : crpCompositeForm
    Created on : Sep 6, 2019, 10:18:22 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
<%@page import="datos.ratePlan.ChargeRatePlanT"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ratePlan.CrpCompositePopModelT"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<%CrpCompositePopModelT crp = (CrpCompositePopModelT) request.getSession().getAttribute("composite");%>
<%ChargeRatePlanT charge = (ChargeRatePlanT) request.getSession().getAttribute("principal");%>
<form style="margin: 20px;" id="formulaire">
    <div class="row">
        <div class="col-sm-12 mb-6 mb-md-0">
            <div class="form-group row">
                <label for="name">name</label>
                <input class="form-control" type="text" id="-name" placeholder="name" value="<%=crp.getName()%>"/>
            </div>
            <%if(charge.getSubscriberCurrency().getApplicableRum()==null){%>
            <div class="form-group row">
                <label for="lowerBound">Lower Bound</label>
                <input class="form-control" type="text" id="-lowerBound" placeholder="Lower Bound" value="<%=crp.getLowerBound()%>"/>
            </div>
            <%}%>
            <div class="form-group row">
            <% ArrayList<ListaT> impactCategories = ControlFunctions.getListaBalance("no_currency",user);%>
                <label>Balance of<select class="custom-select" id="-balanceElementName">
                <option value="">NO BALANCE</option>        
                <%for(int j=0;j<impactCategories.size();j++){%>
                <option <%=(impactCategories.get(j).valor.equals(crp.getBalanceElementName())?"selected":"")%> value="<%=impactCategories.get(j).valor%>"><%=impactCategories.get(j).valor%></option>
                <%}%>
                </select></label>
            </div>
        </div>
        <div class="col-sm-6 mb-3 mb-md-0"></div>
        <div class="col-sm-6 mb-3 mb-md-0"></div>
    </div>    
</form>
