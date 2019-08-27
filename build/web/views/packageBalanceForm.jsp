<%-- 
    Document   : packageBalanceForm
    Created on : Aug 26, 2019, 9:48:54 AM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.PackageT"%>
<%@page import="datos.BalanceSpecT"%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(1);%>
<% BalanceSpecT balance = (BalanceSpecT) request.getSession().getAttribute("add");%>
<%if(balance==null){balance = ((PackageT)request.getSession().getAttribute("principal")).getBalances().get(index);}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<% ArrayList<ListaT> impactCategories = ControlFunctions.getLista((String)ControlPath.balanceElementClick);%>
    <label>Balance Element<select class="custom-select" id="-balanceElementName">
    <%for(int j=0;j<impactCategories.size();j++){%>
    <option <%=(impactCategories.get(j).valor.equals(balance.getBalanceElementName()))?"selected":""%> value="<%=impactCategories.get(j).valor%>"><%=impactCategories.get(j).valor%></option>
    <%}%>
    </select></label>
</div>
<div class="form-group row">
<label for="floor">Floor</label>
<input class="form-control" type="text" id="-floor" placeholder="Floor" value="<%=balance.getFloor()%>"/>
</div>
<div class="form-group row">
<label for="ceiling">Ceiling</label>
<input class="form-control" type="text" id="-ceiling" placeholder="Ceiling" value="<%=balance.getCeil()%>"/>
</div>
<div class="form-group row">
<label for="threshold1">Threshold</label>
<input class="form-control" type="text" id="-threshold1" placeholder="Threshold" value="<%=balance.getThreshold1()%>"/>
</div>
<div class="form-group row">
<label for="threshold2">Threshold</label>
<input class="form-control" type="text" id="-threshold2" placeholder="Threshold" value="<%=balance.getThreshold2()%>"/>
</div>
</form>
