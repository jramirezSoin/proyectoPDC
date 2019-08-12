<%-- 
    Document   : rumConfigForm
    Created on : Aug 12, 2019, 3:33:06 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.RumT"%>
<% RumT rum = (RumT) request.getSession().getAttribute("add");%>
<% if(rum==null){rum = (RumT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-name">name</label>
<input <%=(!rum.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=rum.getName()%>"/>
</div>
<div class="form-group row">
<label for="-description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=rum.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="-internalId">Internal Id</label>
<input <%=(!rum.getInternalId().equals(""))?"readonly":""%> class="form-control" type="text" id="-internalId" placeholder="Internal Id" value="<%=rum.getInternalId()%>"/>
</div>
<div class="form-group row">
<label for="-priceListName">PriceList Name</label>
<input class="form-control" type="text" id="-priceListName" placeholder="PriceList Name" value="<%=rum.getPriceListName()%>"/>
</div>
<div class="form-group row">
<label>Obsolete<select class="form-control" id="-obsolete">
        <option <%=(rum.isObsolete())?"Selected":""%> value="true">true</option>
        <option <%=(!rum.isObsolete())?"Selected":""%> value="false">false</option>
</select></label>
</div>
<div class="form-group row">
<label for="-unit">Unit</label>
<input class="form-control" type="text" id="-unit" placeholder="Unit" value="<%=rum.getUnit()%>"/>
</div>
<div class="form-group row">
<label for="-rumType">Rum Type</label>
<input class="form-control" type="text" id="-rumType" placeholder="Rum Type" value="<%=rum.getRumType()%>"/>
</div>
<div class="form-group row">
<label for="-rumRounding">Rum Rounding</label>
<input class="form-control" type="text" id="-rumRounding" placeholder="Rum Rounding" value="<%=rum.getRumRounding()%>"/>
</div>
<div class="form-group row">
<label for="-rumCode">Rum Code</label>
<input class="form-control" type="text" id="-rumCode" placeholder="Rum Code" value="<%=rum.getRumCode()%>"/>
</div>
</form>
