<%-- 
    Document   : roundingRuleForm
    Created on : Aug 12, 2019, 3:33:25 PM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="datos.BalanceElementT"%>
<%@page import="datos.RoundingRuleT"%>
<%@page import="java.util.ArrayList"%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% RoundingRuleT rule = (RoundingRuleT) request.getSession().getAttribute("add");%>
<%if(rule==null){rule = ((BalanceElementT)request.getSession().getAttribute("principal")).getRoundingRules().get(index);}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-precision">Precision</label>
<input class="form-control" type="number" id="-precision" placeholder="Precision" value="<%=rule.getPrecision()%>"/>
</div>
<div class="form-group row">
<label for="toleranceMin">Tolerance Min</label>
<input class="form-control" type="text" id="-toleranceMin" placeholder="Tolerance Min" value="<%=rule.getToleranceMin()%>"/>
</div>
<div class="form-group row">
<label for="toleranceMax">Tolerance Max</label>
<input class="form-control" type="text" id="-toleranceMax" placeholder="Tolerance Max" value="<%=rule.getToleranceMax()%>"/>
</div>
<div class="form-group row">
<label for="tolerancePercentage">Tolerance Percentage</label>
<input class="form-control" type="text" id="-tolerancePercentage" placeholder="Tolerance Percentage" value="<%=rule.getTolerancePercentage()%>"/>
</div>
<div class="form-group row">
<label for="-roundingMode">Rounding Mode</label>
<input class="form-control" type="text" id="-roundingMode" placeholder="Rounding Mode" value="<%=rule.getRoundingMode()%>"/>
</div>
<div class="form-group row">
    <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("roundingRules");%>
    <label>Processing Stage<select class="custom-select" id="-processingStage" onclick="checkPriceValidity();">
        <%for(ListaT constante : constants){%>    
            <option <%=(rule.getProcessingStage().equals(constante.unit)?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
    </select></label>
</div>
</form>
