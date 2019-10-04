<%-- 
    Document   : ruleForm
    Created on : Oct 2, 2019, 2:08:16 PM
    Author     : Joseph Ramírez
--%>

<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.GenericSelectorT"%>
<%@page import="datos.RuleT"%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% RuleT rule = (RuleT) request.getSession().getAttribute("add");%>
<%if(rule==null){rule = ((GenericSelectorT)request.getSession().getAttribute("principal")).getRules().get(index);}%>
<form style="margin: 20px;" id="formulaire">

<div class="form-group row">
<label for="name">Name</label>
<input class="form-control" type="text" id="-name" placeholder="Name" value="<%=rule.getName()%>"/>
</div>
<div id="-result">
<div class="form-group row">
<label for="resultName">Result Name</label>
<input class="form-control" type="text" id="-resultName" placeholder="Result Name" value="<%=rule.getResultName()%>"/>
</div>
</div>
<%for(String s: rule.getModels().keySet()){%>
    <%ListaT l = rule.getModels().get(s);%>
        <%if(l.id==1){%>
        <div id="-fieldToValueExpression">
            <div class="form-group row">
                <label for="fieldValue"><%=s%>[<%=l.unit%>]</label>
                <input type="hidden" id="-fieldName" value="<%=rule.getEvent()%>.<%=s%>">
                <input type="hidden" id="-operation" value="<%=l.unit%>">
                <input class="form-control" type="text" id="-fieldValue" placeholder="Field Value" value="<%=l.valor%>"/>
            </div>
        <div>
        <%}else if(l.id==2){%>
        <div id="-crSelectorExpression">
            <div class="form-group row">
                <label for="fieldValue"></label>
                <input type="hidden" id="-rightOperand" value="<%=s%>">
                <input type="hidden" id="-operator" value="NONE">
            </div>
        <div>
        <%}%>
<%}%>


</form>
