<%-- 
    Document   : balanceElementForm
    Created on : Aug 12, 2019, 3:32:49 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.BalanceElementT"%>
<% BalanceElementT balance = (BalanceElementT) request.getSession().getAttribute("add");%>
<% if(balance==null){balance = (BalanceElementT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!balance.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=balance.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=balance.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="internalId">Internal Id</label>
<input <%=(!balance.getInternalId().equals(""))?"readonly":""%> class="form-control" type="text" id="-internalId" placeholder="Internal Id" value="<%=balance.getInternalId()%>"/>
</div>
<div class="form-group row">
<label for="priceListName">PriceList Name</label>
<input class="form-control" type="text" id="-priceListName" placeholder="PriceList Name" value="<%=balance.getPriceListName()%>"/>
</div>
<div class="form-group row">
<label>Obsolete<select class="form-control" id="-obsolete">
        <option <%=(balance.isObsolete())?"Selected":""%> value="true">true</option>
        <option <%=(!balance.isObsolete())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label for="-code">Code</label>
<input class="form-control" type="text" id="-code" placeholder="Code" value="<%=balance.getCode()%>"/>
</div>
<div class="form-group row">
<label for="-numericCode">Numeric Code</label>
<input class="form-control" type="text" id="-numericCode" placeholder="Numeric Code" value="<%=balance.getNumCode()%>"/>
</div>
<div class="form-group row">
<label for="-symbol">Symbol</label>
<input class="form-control" type="text" id="-symbol" placeholder="Symbol" value="<%=balance.getSymbol()%>"/>
</div>
<div class="form-group row">
<label>Transient Element<select class="form-control" id="-transientElement">
        <option <%=(balance.getTransientElement())?"Selected":""%> value="true">true</option>
        <option <%=(!balance.getTransientElement())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label>Foldable<select class="form-control" id="-foldable">
        <option <%=(balance.isFoldable())?"Selected":""%> value="true">true</option>
        <option <%=(!balance.isFoldable())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label>Counter<select class="form-control" id="-counter">
        <option <%=(balance.isCounter())?"Selected":""%> value="true">true</option>
        <option <%=(!balance.isCounter())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label for="-consumptionRule">Consumption Rule</label>
<input class="form-control" type="text" id="-consumptionRule" placeholder="Consumption Rule" value="<%=balance.getConsumptionRule()%>"/>
</div>
</form>