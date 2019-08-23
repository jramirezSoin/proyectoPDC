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
<label for="-code">Code</label>
<input class="form-control" type="text" id="-code" placeholder="Code" value="<%=balance.getCode()%>"/>
</div>
<div class="form-group row">
<label for="-numericCode">Numeric Code</label>
<input class="form-control" type="number" id="-numericCode" placeholder="Numeric Code" value="<%=balance.getNumCode()%>"/>
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
</form>