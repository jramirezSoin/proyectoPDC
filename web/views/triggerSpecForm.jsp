<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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
<label for="-pricingProfileName">Pricing Profile Name
    <select class="custom-select" id="-pricingProfileName" <%=(!triggerSpec.getPricingProfileName().equals(""))?"disabled":""%>>
        <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("pricingProfileName");%>
        <%for(ListaT constante : constants){%>
        <option <%=(triggerSpec.getPricingProfileName().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
    </select>
</label>
</div>
</form>