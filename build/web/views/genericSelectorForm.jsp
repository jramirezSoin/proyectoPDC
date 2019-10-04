<%-- 
    Document   : genericSelectorForm
    Created on : Oct 2, 2019, 2:08:05 PM
    Author     : Joseph Ramírez
--%>

<%-- 
    Document   : genericSelectorForm
    Created on : Aug 19, 2019, 8:53:35 AM
    Author     : Joseph Ramírez
--%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.GenericSelectorT"%>
<% GenericSelectorT genericSelector = (GenericSelectorT) request.getSession().getAttribute("add");%>
<% if(genericSelector==null){genericSelector = (GenericSelectorT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-name">name</label>
<input <%=(!genericSelector.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=genericSelector.getName()%>"/>
</div>
<div class="form-group row">
<label for="-description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=genericSelector.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="-stereoType">StereoType</label>
<input class="form-control" type="text" id="-stereoType" placeholder="StereoType" value="<%=genericSelector.getStereoType()%>"/>
</div>
<div class="form-group row">
<label for="-pricingProfileName">Pricing Profile Name</label>
<input class="form-control" type="text" id="-pricingProfileName" placeholder="Pricing Profile Name" value="<%=genericSelector.getPricingProfileName()%>"/>
</div>
<%if(genericSelector.getRules().size()==0){%>
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.attributeSpecMapClick);%>
<div class="form-group row">
    <label>Aplicable to<select class="custom-select" id="-aplicable" onclick="getEvent();">
        <%for(int j=0;j<products.size();j++){%>
        <%products.get(j).valor= products.get(j).valor.replaceAll("_ASM","");%>
        <option <%=(products.get(j).valor.equals(genericSelector.getCustomerSpecName()) || products.get(j).valor.equals(genericSelector.getProductSpecName()))?"Selected":""%> value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
        <%}%>
</select></label>
</div>        
<%}else{%>
<div class="form-group row">
    <label>Aplicable to<select class="custom-select" id="-aplicable" disabled onclick="getEvent();">
            <option><%=genericSelector.getCustomerSpecName()+genericSelector.getProductSpecName()%></option>>
    </select></label>
</div> 
<%}%>
<div class="form-group row">
<label>Event<select <%=((genericSelector.getRules().size()==0)?"":"disabled")%> class="custom-select" id="-eventSpecName">
</select></label>
</div>

<script>
    function getEvent(){
        var parameters= { 'filtro' : 'name;'+$("#-aplicable").val()+'_ASM,eventRUMSpec;',
        'funcion' : 'getListaFiltroDeep',
        'tipo' : '<%=ControlPath.attributeSpecMapClick%>',
        'buscar' : 'name',
        'compara' : '<%=genericSelector.getEventSpecName()%>_ERS',
        'replace' : '_ERS;'}
        consulta("-eventSpecName",parameters,false);
    }
    getEvent();
</script>
    
</form>

