<%@page import="datos.ZoneModelT"%>
<% ZoneModelT zoneModel = (ZoneModelT) request.getSession().getAttribute("principal");%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input readonly class="form-control" type="text" id="-name" placeholder="name" value="<%=zoneModel.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=zoneModel.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="internalId">Internal Id</label>
<input readonly class="form-control" type="text" id="-internalId" placeholder="Internal Id" value="<%=zoneModel.getInternalId()%>"/>
</div>
<div class="form-group row">
<label for="priceListName">PriceList Name</label>
<input class="form-control" type="text" id="-priceListName" placeholder="PriceList Name" value="<%=zoneModel.getPriceListName()%>"/>
</div>
<div class="form-group row">
<label>Obsolete<select class="form-control" id="-obsolete">
        <option <%=(zoneModel.isObsolete())?"Selected":""%>>true</option>
        <option <%=(!zoneModel.isObsolete())?"Selected":""%>>false</option>
</select></label>
</div>
</form>
