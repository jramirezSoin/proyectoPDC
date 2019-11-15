<%-- 
    Document   : modelDataForm
    Created on : Oct 3, 2019, 11:20:52 AM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.User"%>
<%@page import="datos.ModelDataT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.GenericSelectorT"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<% GenericSelectorT genericSelector = (GenericSelectorT) request.getSession().getAttribute("add");%>
<% if(genericSelector==null){genericSelector = (GenericSelectorT) request.getSession().getAttribute("principal");}%>
<form id="formulaire">
<div id="row">
    <div class="col-xs-12 col-md-12">
        <%ArrayList<ListaT> attributes = ControlFunctions.getLista(ControlPath.customRuleClick,user);%>
        <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("fieldName");%>
        <div class="input-group mb-5">
            <select class="custom-select" id="customRule1" aria-describedby="basic-addon2">
                <%for(ListaT constante : attributes){%><option value="<%=constante.valor%>"><%=constante.valor%></option><%}%>
            </select>
            <div class="input-group-append">
                <span class="input-group-text" id="customRule" onclick="getDataForm(this);">add Custom Rule</span>
            </div>
            <select class="custom-select" id="event1" aria-describedby="basic-addon2">
                <%for(ListaT constante : constants){%><option value="<%=constante.unit%>"><%=constante.valor%></option><%}%>
            </select>
            <div class="input-group-append">
                <span class="input-group-text" id="event" onclick="getDataForm(this);">add Event Item</span>
            </div>
        </div>
        <div class="input-group mb-5">
            
        </div>
    </div>            
</div>
<div id="row">    
<div class="single-table">
    <div class="table-responsive">
        <table class="table text-center">
        <thead>
            <tr>
                <th>Name</th>
                <th>Operator</th>
                <th>Value Type</th>
                <th>Default Value</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody id="modelDatas">
            <% constants = ControlFunctions.LeerConstante("operation");%>
            <%ArrayList<ListaT> constants2 = ControlFunctions.LeerConstante("valueType");%>
            <% for(String s: genericSelector.getModelDatas().keySet()){%>
            <% ModelDataT model = genericSelector.getModelDatas().get(s);%>
                <tr id="-modelDat">
                <td><input readonly class="form-control" type="text" id="-name" placeholder="name" value="<%= model.getName()%>"/></td>
                <%if(!model.getTipo().equals("Custom")){%>
                <input class="form-control" type="hidden" id="-tipo" placeholder="name" value="1"/>
                <td><select class="custom-select" id="-operator">
                    <%for(ListaT constante : constants){%>
                    <option <%=((model.getOperator().equals(constante.unit))?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
                    <%}%>
                    </select>
                </td>
                <td><select class="custom-select" id="-valueType">
                    <%for(ListaT constante : constants2){%>
                    <option <%=((model.getValueType().equals(constante.unit))?"selected":"")%> value="<%=constante.unit%>"><%=constante.valor%></option>
                    <%}%>
                    </select>
                </td>
                <td><input class="form-control" type="text" id="-defaultValue" placeholder="name" value="<%= model.getDefaultValue()%>"/></td>
                <td><i class="ti-trash" onclick="$(this).parent().parent().children('#-tipo').val('3'); $(this).parent().parent().toggle();"></i></td>
                <%}else{%>
                <input class="form-control" type="hidden" id="-tipo" placeholder="name" value="2"/>
                <td><input class="form-control" type="text" disabled id="-operator" placeholder="name" value="Equal to"/></td>
                <td><input class="form-control" type="text" disabled id="-valueType" placeholder="name" value="Single Value"/></td>
                <td><input class="form-control" type="text" disabled id="-defaultValue" placeholder="name" value=""/></td>
                <td><i class="ti-trash" onclick="$(this).parent().parent().children('#-tipo').val('3'); $(this).parent().parent().toggle();"></i></td>
                </tr>
                <%}%>
            <%}%>
        </tbody>
    </table>
    </div>    
</div>
</div>        
</form>        
    <table style="display: none;">
    <tbody id="customRuleEventDiv">    
    <tr id="-modelDat">
    <td><input readonly class="form-control" type="text" id="-name" placeholder="name" value="$NAME"/></td>
    <input class="form-control" type="hidden" id="-tipo" placeholder="name" value="2"/>
    <td><input class="form-control" type="text" disabled id="-operator" placeholder="name" value="Equal to"/></td>
    <td><input class="form-control" type="text" disabled id="-valueType" placeholder="name" value="Single Value"/></td>
    <td><input class="form-control" type="text" disabled id="-defaultValue" placeholder="name" value=""/></td>
    <td><i class="ti-trash" onclick="$(this).parent().parent().children('#-tipo').val('3'); $(this).parent().parent().toggle();"></i></td>
    </tr>
    <tr id="-modelDat">
    <td><input readonly class="form-control" type="text" id="-name" placeholder="name" value="$NAME"/></td>
    <input class="form-control" type="hidden" id="-tipo" placeholder="name" value="1"/>
    <td><select class="custom-select" id="-operator">
        <%for(ListaT constante : constants){%>
        <option value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
        </select>
    </td>
    <td><select class="custom-select" id="-valueType">
        <%for(ListaT constante : constants2){%>
        <option value="<%=constante.unit%>"><%=constante.valor%></option>
        <%}%>
        </select>
    </td>
    <td><input class="form-control" type="text" id="-defaultValue" placeholder="name" value=""/></td>
    <td><i class="ti-trash" onclick="$(this).parent().parent().children('#-tipo').val('3'); $(this).parent().parent().toggle();"></i></td>
    </tr>
    </tbody>
    </table>
<script>
    function getDataForm(nodo){
        var val= $("#"+nodo.id+"1").val();
        var id= nodo.id;
        if(id==="event"){
            $("#modelDatas").append("<tr id='-modelDat'>"+$("#customRuleEventDiv").children().eq(1).html().replace("$NAME",val)+"</tr>");
        }else{
            $("#modelDatas").append("<tr id='-modelDat'>"+$("#customRuleEventDiv").children().eq(0).html().replace("$NAME",val)+"</tr>");
        }
    }
</script>        

