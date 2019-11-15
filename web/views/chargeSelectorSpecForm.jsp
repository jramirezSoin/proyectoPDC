<%-- 
    Document   : chargeSelectorSpecForm
    Created on : Sep 24, 2019, 11:48:16 AM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.User"%>
<%-- 
    Document   : chargeSelectorSpecForm
    Created on : Aug 13, 2019, 11:30:01 AM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ChargeSelectorSpecT"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<% ChargeSelectorSpecT chargeSelectorSpec = (ChargeSelectorSpecT) request.getSession().getAttribute("add");%>
<% if(chargeSelectorSpec==null){chargeSelectorSpec = (ChargeSelectorSpecT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="name">name</label>
<input <%=(!chargeSelectorSpec.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=chargeSelectorSpec.getName()%>"/>
</div>
<div class="form-group row">
<label for="-pricingProfileName">Pricing Profile Name
    <select class="custom-select" id="-pricingProfileName" <%=(!chargeSelectorSpec.getPricingProfileName().equals(""))?"disabled":""%>>
        <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("pricingProfileName");%>
        <%for(ListaT constante : constants){%>
        <option <%=(chargeSelectorSpec.getPricingProfileName().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
    </select>
</label>
</div>
<div class="form-group row">
<label for="-validFrom">Valid From</label>
<input class="form-control" type="text" id="-validFrom" placeholder="Valid From" value="<%=chargeSelectorSpec.getValidFrom()%>"/>
</div>
<div class="form-group row">
<label for="-validTo">Valid To</label>
<input class="form-control" type="text" id="-validTo" placeholder="Valid To" value="<%=chargeSelectorSpec.getValidTo()%>"/>
</div>


<div class="form-group row" id='-Nglids'>
<% ArrayList<ListaT> glids = ControlFunctions.getLista((String)ControlPath.glidClick,user);%>
    <label>GL/Id</label>
    <select class="custom-select" onchange="tagSelect(this,'nt')" id="glidName">
    <%for(ListaT glid: glids){%>
    <%if(!chargeSelectorSpec.getGlidName().contains(glid.valor)){%>
    <option value="<%=glid.valor%>"><%=glid.valor%></option>
    <%}}%>
    </select>
    <%String[] chargeGlids = chargeSelectorSpec.getGlidName().split("\\|");%>
    <%if(!chargeSelectorSpec.getGlidName().equals("")){for(String chargeglid: chargeGlids){%>
        <span id="-nt"  class="badge badge-primary badge-pill"><%=chargeglid%><i onclick="addOption(this); $(this).parent().remove();" class="ti-close"></i></span>
    <%}}%>
</div>

<div class="form-group row" id='-Nbalances'>
    <%glids = ControlFunctions.getListaBalance("currency",user);%>
    <label>Balance</label>
    <select class="custom-select" onchange="tagSelect(this,'nt')" id="balanceElementName">
    <%for(ListaT glid: glids){%>
    <%if(!chargeSelectorSpec.getBalanceElementName().contains(glid.valor)){%>
    <option value="<%=glid.valor%>"><%=glid.valor%></option>
    <%}}%>
    </select>
    <%chargeGlids = chargeSelectorSpec.getBalanceElementName().split("\\|");%>
    <%if(!chargeSelectorSpec.getBalanceElementName().equals("")){for(String chargeglid: chargeGlids){%>
        <span id="-nt"  class="badge badge-primary badge-pill"><%=chargeglid%><i onclick="addOption(this); $(this).parent().remove();" class="ti-close"></i></span>
    <%}}%>
</div>

<div class="form-group row" id='-Ntimes'>
    <%glids = ControlFunctions.getLista(ControlPath.timeModelsClick,user,"timeModelTag","tagName",true);%>
    <label>Time Model Tags</label>
    <select class="custom-select" onchange="tagSelect(this,'nt')" id="balanceElementName">
    <%for(ListaT glid: glids){%>
    <%if(!(("|"+chargeSelectorSpec.getTimeModelTagName()+"|").contains("|"+glid.valor+"|"))){%>
    <option value="<%=glid.valor%>"><%=glid.valor%></option>
    <%}}%>
    </select>
    <%chargeGlids = chargeSelectorSpec.getTimeModelTagName().split("\\|");%>
    <%if(!chargeSelectorSpec.getTimeModelTagName().equals("")){for(String chargeglid: chargeGlids){%>
        <span id="-nt"  class="badge badge-primary badge-pill"><%=chargeglid%><i onclick="addOption(this); $(this).parent().remove();" class="ti-close"></i></span>
    <%}}%>
</div>

<div class="form-group row">
<label for="-pricingName">Pricing Name</label>
<input class="form-control" type="text" id="-pricingName" placeholder="Pricing Name" value="<%=chargeSelectorSpec.getPricingName()%>"/>
</div>

<div class="form-group row">
<label for="-zoneResult">Zone Result</label>
<input class="form-control" type="text" id="-zoneResult" placeholder="Zone Result" value="<%=chargeSelectorSpec.getZoneResult()%>"/>
</div>

<div class="form-group row">
<label for="-eventField">Event Type
    <select class="custom-select" id="-eventField">
        <option <%=(chargeSelectorSpec.getEventConditions().unit.contains(""))?"selected":""%> value=""></option>
        <option <%=(chargeSelectorSpec.getEventConditions().unit.contains("USAGE_CLASS"))?"selected":""%> value="AnyEvent.USAGE_CLASS">Usage Class</option>
        <option <%=(chargeSelectorSpec.getEventConditions().unit.contains("SERVICE_CLASS"))?"selected":""%> value="AnyEvent.SERVICE_CLASS">Service Class</option>
        <option <%=(chargeSelectorSpec.getEventConditions().unit.contains("USAGE_TYPE"))?"selected":""%> value="AnyEvent.USAGE_TYPE">Usage Type</option>
    </select>
</label>    
</div>
    
<div class="form-group row">
<label for="-eventValue">Event Value</label>
<input class="form-control" type="text" id="-eventValue" placeholder="Event Value" value="<%=chargeSelectorSpec.getEventConditions().valor%>"/>
</div>

</form>
<script>
    function tagSelect(id,tipo){    
            $(id).parent().append("<span id='-"+tipo+"' class='badge badge-primary badge-pill'>"+id.value+"<i onclick='addOption(this); $(this).parent().remove();' class='ti-close'></i></span>");
            $('#'+$(id).attr('id')+' option[value="'+id.value+'"]').remove();
            id.value="";
        }
    function addOption(valor){
        console.log($(valor).text());
        $(valor).parent().parent().children(".custom-select").first().append('<option value="'+$(valor).parent().text()+'">'+$(valor).parent().text()+'</option>');
        $(valor).parent().parent().children(".custom-select").first().val("");
    }
    
</script>
