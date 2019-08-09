<%@page import="datos.TriggerSpecT"%>
<% TriggerSpecT triggerSpec = (TriggerSpecT) request.getSession().getAttribute("add");%>
<% if(triggerSpec==null){triggerSpec = (TriggerSpecT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!triggerSpec.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=triggerSpec.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=triggerSpec.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="internalId">Internal Id</label>
<input <%=(!triggerSpec.getInternalId().equals(""))?"readonly":""%> class="form-control" type="text" id="-internalId" placeholder="Internal Id" value="<%=triggerSpec.getInternalId()%>"/>
</div>
<div class="form-group row">
<label for="priceListName">PriceList Name</label>
<input class="form-control" type="text" id="-priceListName" placeholder="PriceList Name" value="<%=triggerSpec.getPriceListName()%>"/>
</div>
<div class="form-group row">
<label for="pricingProfileName">Pricing Profile Name</label>
<input class="form-control" type="text" id="-pricingProfileName" placeholder="Pricing Profile Name" value="<%=triggerSpec.getPricingProfileName()%>"/>
</div>
</form>