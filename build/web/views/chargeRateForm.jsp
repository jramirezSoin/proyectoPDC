<%-- 
    Document   : chargeRateForm
    Created on : Sep 2, 2019, 4:45:27 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ZoneModelT"%>
<% ZoneModelT zoneModel = (ZoneModelT) request.getSession().getAttribute("add");%>
<% if(zoneModel==null){zoneModel = (ZoneModelT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!zoneModel.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=zoneModel.getName()%>"/>
</div>
<div class="form-group row">
<label for="description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=zoneModel.getDescription()%>"/>
</div>
</form>
