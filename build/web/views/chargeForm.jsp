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
        <label>Price Type<select class="custom-select" id="-priceType" onclick="checkPriceValidity();">
        <option <%=(charge.getPriceType().equals("CONSUMPTION")?"selected":"")%> value="CONSUMPTION">Consumption</option>
        <option <%=(charge.getPriceType().equals("GRANT")?"selected":"")%> value="GRANT">Grant</option>
        </select></label>
    </div>
    <div class="form-group row">
    <label for="-price">Price</label>
    <input class="form-control" type="text" id="-price" placeholder="Price" value="<%=charge.getPrice()%>"/>
    </div>
    <%if(crp.getRumName().equals("DUR")){%>
    <div class="form-group row">
        <label>Price Type<select class="custom-select" id="-unitOfMeasure">
        <option <%=(charge.getUnitOfMeasure().equals("SECONDS")?"selected":"")%> value="SECONDS">Seconds</option>        
        <option <%=(charge.getUnitOfMeasure().equals("MINUTES")?"selected":"")%> value="MINUTES">Minutes</option>
        <option <%=(charge.getUnitOfMeasure().equals("HOUR")?"selected":"")%> value="HOUR">Hour</option>
        <option <%=(charge.getUnitOfMeasure().equals("DAY")?"selected":"")%> value="DAY">Day</option>
        <option <%=(charge.getUnitOfMeasure().equals("BYTE")?"selected":"")%> value="BYTE">Byte</option>
        <option <%=(charge.getUnitOfMeasure().equals("KBYTE")?"selected":"")%> value="KILOBYTE">Kilobyte</option>
        <option <%=(charge.getUnitOfMeasure().equals("MBYTE")?"selected":"")%> value="MEGABYTE">Megabyte</option>
        <option <%=(charge.getUnitOfMeasure().equals("GBYTE")?"selected":"")%> value="GIGABYTE">Gigabyte</option>
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
        <option <%=(charge.getIncrementRounding().equals("NONE")?"selected":"")%> value="NONE">None</option>
        <option <%=(charge.getIncrementRounding().equals("UP")?"selected":"")%> value="UP">Up</option>
        </select></label>
    </div>
    <div class="form-group row">
    <label>Tax Time<select class="custom-select" onclick="checkTax();" id="-taxTime">
            <option <%=(charge.getTaxTime().equals("NONE"))?"Selected":""%> value="NONE">NONE</option>
            <option <%=(charge.getTaxTime().equals("EVENT_TIME"))?"Selected":""%> value="EVENT_TIME">EVENT_TIME</option>
            <option <%=(charge.getTaxTime().equals("BILLING_TIME"))?"Selected":""%> value="BILLING_TIME">BILLING_TIME</option>
    </select></label>
    </div>
    <div class="form-group row" id="taxCode">
    <label>Tax Code<select class="custom-select" id="-taxCode">
            <option <%=(charge.getTaxCode().equals("IV"))?"Selected":""%> value="IV">IV</option>
            <option <%=(charge.getTaxCode().equals("CR"))?"Selected":""%> value="CR">CR</option>
            <option <%=(charge.getTaxCode().equals("NU"))?"Selected":""%> value="NU">NU</option>
            <option <%=(charge.getTaxCode().equals("IV-CR-NU"))?"Selected":""%> value="IV-CR-NU">IV-CR-NU</option>
            <option <%=(charge.getTaxCode().equals("IV-CR"))?"Selected":""%> value="IV-CR">IV-CR</option>
            <option <%=(charge.getTaxCode().equals("CR-NU"))?"Selected":""%> value="CR-NU">CR-NU</option>
            <option <%=(charge.getTaxCode().equals("NORM"))?"Selected":""%> value="NORM">NORM</option>
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
        <option <%=(charge.getIncrementRounding().equals("FOREVER")?"selected":"")%> value="FOREVER">Forever</option>
        <option <%=(charge.getIncrementRounding().equals("INMEDIATE")?"selected":"")%> value="INMEDIATE">Inmediate</option>
        </select></label>
       </div>
       <div class="form-group row">
        <label>End Mode<select class="custom-select" id="-endValidityMode">
            <option <%=(charge.getIncrementRounding().equals("NEVER")?"selected":"")%> value="NEVER">Never</option>
            <option <%=(charge.getIncrementRounding().equals("RELATIVE_TO_START")?"selected":"")%> value="RELATIVE_TO_START">Relative to start</option>
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
