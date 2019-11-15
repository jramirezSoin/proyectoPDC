<%-- 
    Document   : alterationForm
    Created on : Nov 1, 2019, 10:45:54 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
<%@page import="datos.alteration.AlterationRatePlanT"%>
<%@page import="datos.alteration.AlterationChargeT"%>
<%@page import="datos.alteration.AlterationConfigurationT"%>
<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<% AlterationConfigurationT crp = (AlterationConfigurationT) request.getSession().getAttribute("composite");%>
<% AlterationRatePlanT alterationRate = (AlterationRatePlanT) request.getSession().getAttribute("principal");%>
<%ArrayList<Integer> indexs= ((ArrayList<Integer>)request.getSession().getAttribute("index"));%>
<% AlterationChargeT alteration = crp.getArpCompositePopModel().getTierRange().get(indexs.get(2)).getCharges().get(indexs.get(3));%>
<form style="margin: 20px;" id="formulaire">
    <div class="form-group row">
        <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("priceType");%>
        <label>Price Type<select class="custom-select" id="-priceType" onclick="checkPriceValidity();">
            <%for(ListaT constante : constants){%>    
                <option <%=(alteration.getPriceType().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
        </select></label>
    </div>
    <div class="form-group row">
    <label for="-price">Price</label>
    <input class="form-control" type="text" id="-price" placeholder="Price" value="<%=alteration.getPrice()%>"/>
    </div>
    <div class="form-group row">
        <label>Price Type<select class="custom-select" id="-unitOfMeasure">
        <option value="NONE">none</option>                
        <%constants = ControlFunctions.LeerConstante("unitOfMeasure");%>
        <%for(ListaT constante : constants){%>
        <option <%=(alteration.getUnitOfMeasure().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>        
        </select></label>
    </div>
    <div class="form-group row">
        <% ArrayList<ListaT> glids = ControlFunctions.getLista((String)ControlPath.glidClick,user);%>
        <label>GL/Id<select class="custom-select" id="-glidName">
        <option value=""></option>        
        <%for(int j=0;j<glids.size();j++){%>
        <option <%=((glids.get(j).valor.equals(alteration.getGlidName()))?"selected":"")%> value="<%=glids.get(j).valor%>"><%=glids.get(j).valor%></option>
        <%}%>
        </select></label>
    </div>
    <div class="form-group row">
        <% ArrayList<ListaT> impactCategories = ControlFunctions.getListaBalance("all",user);%>
        <label>Currency<select class="custom-select" id="-balanceElementName">
                <%for(int j=0;j<impactCategories.size();j++){%>
                <option <%=((impactCategories.get(j).valor.equals(alteration.getBalanceElementName()))?"selected":"")%> value="<%=impactCategories.get(j).valor%>"><%=impactCategories.get(j).valor%></option>
                <%}%>
        </select></label>
    </div>
    <div id="-priceValidity">
       <div class="form-group row">
        <label>Start Mode<select class="custom-select" id="-startValidityMode">
            <%constants = ControlFunctions.LeerConstante("startValidityMode");%>
            <%for(ListaT constante : constants){%>
            <option <%=(alteration.getPriceValidity()!=null && alteration.getPriceValidity().getStartValidityMode().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
        </select></label>
       </div>
       <div class="form-group row">
        <label>End Mode<select class="custom-select" id="-endValidityMode">
            <%constants = ControlFunctions.LeerConstante("endValidityMode");%>
            <%for(ListaT constante : constants){%>
            <option <%=(alteration.getPriceValidity()!=null && alteration.getPriceValidity().getEndValidityMode().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
        </select></label>
       </div> 
    </div>
        <script>
            function checks(id){
                $("#-"+id).val($("#"+id).prop("checked").toString()+"");
            }
            function checkScaled(){
                if($("#scaled").prop("checked")){
                    $("#increment").show();
                }else{
                    $("#increment").hide();
                }
                checks("scaled");
            }
            function checkTax(){
                if($("#-taxTime").val()=="NONE"){
                    $("#taxCode").hide();
                }else{
                    $("#taxCode").show();
                }
            }
            function checkPriceValidity(){
                if($("#-priceType").val()=="GRANT"){
                    $("#-priceValidity").show();
                }else{
                    $("#-priceValidity").hide();
                }
            }
            checkPriceValidity();
        </script>
    
</form>
