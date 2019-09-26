<%-- 
    Document   : chargeView
    Created on : Sep 18, 2019, 11:33:35 AM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.ratePlan.ChargeRatePlanT"%>
<%@page import="datos.ratePlan.ChargeT"%>
<%@page import="datos.ratePlan.CrpCompositePopModelT"%>
<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<% CrpCompositePopModelT crp = (CrpCompositePopModelT) request.getSession().getAttribute("composite");%>
<% ChargeRatePlanT chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("principal");%>
<%ArrayList<Integer> indexs= ((ArrayList<Integer>)request.getSession().getAttribute("index"));%>
<% ChargeT charge = crp.getPriceTierRanges().get(indexs.get(2)).getCharges().get(indexs.get(3));%>
<form style="margin: 20px;" id="formulaire">
    <div class="form-group row">
        <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("priceType");%>
        <label>Price Type<select class="custom-select" id="-priceType" onclick="checkPriceValidity();">
            <%for(ListaT constante : constants){%>    
                <option <%=(charge.getPriceType().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
        </select></label>
    </div>
    <div class="form-group row">
    <label for="-price">Price</label>
    <input class="form-control" type="text" id="-price" placeholder="Price" value="<%=charge.getPrice()%>"/>
    </div>
    <%if(crp.getRumName().equals("DUR")){%>
    <div class="form-group row">
        <label>Price Type<select class="custom-select" id="-unitOfMeasure">
        <%constants = ControlFunctions.LeerConstante("unitOfMeasure");%>
        <%for(ListaT constante : constants){%>
        <option <%=(charge.getUnitOfMeasure().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>        
        </select></label>
    </div>
    <%}%>    
    <div class="form-group row">
        <% ArrayList<ListaT> glids = ControlFunctions.getLista((String)ControlPath.glidClick);%>
        <label>GL/Id<select class="custom-select" id="-glidName">
        <%for(int j=0;j<glids.size();j++){%>
        <option <%=((glids.get(j).valor.equals(charge.getGlidName()))?"selected":"")%> value="<%=glids.get(j).valor%>"><%=glids.get(j).valor%></option>
        <%}%>
        </select></label>
    </div>
    <div class="form-group row">
        <% ArrayList<ListaT> impactCategories = ControlFunctions.getListaBalance("no_currency");%>
        <label>Currency<select class="custom-select" id="-balanceElementName">
                <option value="<%=chargeRate.getSubscriberCurrency().getCurrencyName()%>"><%=chargeRate.getSubscriberCurrency().getCurrencyName()%></option>        
                <%for(int j=0;j<impactCategories.size();j++){%>
                <option <%=((impactCategories.get(j).valor.equals(charge.getBalanceElementName()))?"selected":"")%> value="<%=impactCategories.get(j).valor%>"><%=impactCategories.get(j).valor%></option>
                <%}%>
        </select></label>
    </div>
        
    <%if(!crp.getRumName().equals("Occurrence")){%>    
    <div class="custom-control custom-checkbox col-sm-6">
        <input <%=((charge.getTipo().equals("scaledCharge"))?"checked":"")%> id="scaled" onclick="checkScaled();" type="checkbox" class="custom-control-input">
        <label class="custom-control-label" for="scaled">Scaled</label>
    </div>    
    <input <%=((charge.getTipo().equals("scaledCharge"))?"true":"false")%> id="-scaled" type="text" value="false" hidden="" class="form-control"> 
    <div id="increment">    
    <div class="form-group row">
    <label for="-incrementStep">Increment</label>
    <input class="form-control" type="text" id="-incrementStep" placeholder="Increment" value="<%=charge.getIncrementStep()%>"/>
    </div>
    <div class="form-group row">
        <label>Rounding<select class="custom-select" id="-incrementRounding">
            <%constants = ControlFunctions.LeerConstante("incrementRounding");%>
            <%for(ListaT constante : constants){%>
            <option <%=(charge.getIncrementRounding().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
        </select></label>
    </div>
    <div class="form-group row">
    <label>Tax Time<select class="custom-select" onclick="checkTax();" id="-taxTime">
            <%constants = ControlFunctions.LeerConstante("taxTime");%>
            <%for(ListaT constante : constants){%>
            <option <%=(charge.getTaxTime().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
    </select></label>
    </div>
    <div class="form-group row" id="taxCode">
    <label>Tax Code<select class="custom-select" id="-taxCode">
            <%constants = ControlFunctions.LeerConstante("taxCode");%>
            <%for(ListaT constante : constants){%>
            <option <%=(charge.getTaxCode().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
    </select></label>
    </div>   
    </div>
    <%}%>
    
    <div class="custom-control custom-checkbox col-sm-6">
        <input <%=((charge.isDiscountable())?"checked":"")%> id="discountable" onclick="checks('discountable');" type="checkbox" class="custom-control-input">
        <label class="custom-control-label" for="discountable">Discountable</label>
    </div>
    <%if(charge.getTipo().equals("recurringCharge")){%>
    <div class="custom-control custom-checkbox col-sm-6">
        <input <%=((charge.isProratable())?"checked":"")%> id="proratable" onclick="checks('proratable');" type="checkbox" class="custom-control-input">
        <label class="custom-control-label" for="proratable">Proratable</label>
    </div>
    <input id="-proratable" type="text" value="<%=((charge.isProratable())?"true":"false")%>" hidden="" class="form-control"><%}%>
    <input id="-discountable" type="text" value="<%=((charge.isDiscountable())?"true":"false")%>" hidden="" class="form-control">
    <div id="-priceValidity">
       <div class="form-group row">
        <label>Start Mode<select class="custom-select" id="-startValidityMode">
            <%constants = ControlFunctions.LeerConstante("startValidityMode");%>
            <%for(ListaT constante : constants){%>
            <option <%=(charge.getPriceValidity()!=null && charge.getPriceValidity().getStartValidityMode().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
            <%}%>
        </select></label>
       </div>
       <div class="form-group row">
        <label>End Mode<select class="custom-select" id="-endValidityMode">
            <%constants = ControlFunctions.LeerConstante("endValidityMode");%>
            <%for(ListaT constante : constants){%>
            <option <%=(charge.getPriceValidity()!=null && charge.getPriceValidity().getEndValidityMode().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
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
            <%if(!crp.getRumName().equals("Occurrence")){%>checkScaled(); checks("scaled"); checkTax();<%}%>
            <%if(charge.getTipo().equals("recurringCharge")){%>checks("proratable");<%}%>
            checks("discountable");
            checkPriceValidity();
        </script>
    
</form>
