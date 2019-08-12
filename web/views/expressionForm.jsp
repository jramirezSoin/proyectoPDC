<%-- 
    Document   : expressionForm
    Created on : Aug 12, 2019, 9:22:04 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.TriggerSpecT"%>
<%@page import="datos.ExpressionT"%>
<%@page import="java.util.ArrayList"%>

<form style="margin: 20px;" id="formulaire">
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% ExpressionT expression = (ExpressionT) request.getSession().getAttribute("add");%>
<%if(expression==null){expression = ((TriggerSpecT)request.getSession().getAttribute("principal")).getExpressions().get(index);%>
<%}else{%>
<label class="radio-inline"><input type="radio" name="optradio" checked value="chargeTriggerExpression">Charge Trigger Expression</label>
<label class="radio-inline"><input type="radio" name="optradio" value="quantityTriggerExpression">Quantity Trigger Expression</label>
<label class="radio-inline"><input type="radio" name="optradio" value="balanceTriggerExpression">Balance Trigger Expression</label>
<label class="radio-inline"><input type="radio" name="optradio" value="complexTriggerExpression">Complex Trigger Expression</label>
<script type="text/javascript">
        $(document).ready(function(){
    $("input[name='optradio']").click(function(){
            var radioValue = $("input[name='optradio']:checked").val();
            $("#-type").val(radioValue);
            if(radioValue=="quantityTriggerExpression" || radioValue=="chargeTriggerExpression"){
                $("#balance").hide();
                $("#binary").hide();
            }else if(radioValue=="balanceTriggerExpression"){
                $("#balance").show();
                $("#binary").hide();
            }else{
                $("#balance").show();
                $("#binary").show();
            }
        });
        $("#-type").val("chargeTriggerExpression");
    });
</script>
<%}%>
<div class="form-group row">
<label for="-type">Type</label>
<input readonly class="form-control" type="text" id="-type" placeholder="Type" value="<%=expression.getTipo()%>"/>
</div>
<div class="form-group row">
<label for="-operator">Operator</label>
<input class="form-control" type="text" id="-operator" placeholder="Operator" value="<%=expression.getOperator()%>"/>
</div>
<div class="form-group row">
<label for="-value">Value</label>
<input class="form-control" type="text" id="-value" placeholder="Value" value="<%=expression.getValue()%>"/>
</div>
<%if(expression.getTipo().equals("balanceTriggerExpression")){%>
<div class="form-group row">
<label for="-balanceElementNumCode">Balance Element Num Code</label>
<input class="form-control" type="text" id="-balanceElementNumCode" placeholder="Balance Element Num Code" value="<%=expression.getBalanceElementNumCode()%>"/>
</div>
<%} else if(expression.getTipo().equals("complexTriggerExpression")){%>
<div id="-binaryExpression">
    <div id="-leftOperand">
        <div id="-balanceExpression">
            <div class="form-group row">
            <label for="-balanceElementNumCode">Balance Element Num Code</label>
            <input class="form-control" type="text" id="-balanceElementNumCode" placeholder="Balance Element Num Code" value="<%=expression.getBalanceElementNumCode()%>"/>
            </div>
        </div>
    </div>
<div class="form-group row">
<label for="-binaryOperator">Binary Operator</label>
<input class="form-control" type="text" id="-binaryOperator" placeholder="Binary Operator" value="<%=expression.getBinaryOperator()%>"/>
</div>
</div>
<%}else{%>
<div>
<div id="balance" style="display: none;">
    <div id="-leftOperand">
        <div id="-balanceExpression">
            <div class="form-group row">
            <label for="-balanceElementNumCode">Balance Element Num Code</label>
            <input class="form-control" type="text" id="-balanceElementNumCode" placeholder="Balance Element Num Code" value="<%=expression.getBalanceElementNumCode()%>"/>
            </div>
        </div>
    </div>
</div>
<div id="binary" style="display: none;">
<div class="form-group row">
<label for="-binaryOperator">Binary Operator</label>
<input class="form-control" type="text" id="-binaryOperator" placeholder="Binary Operator" value="<%=expression.getBinaryOperator()%>"/>
</div>
</div>
</div>
<%}%>

</form>
