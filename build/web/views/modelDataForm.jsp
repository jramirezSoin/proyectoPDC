<%-- 
    Document   : modelDataForm
    Created on : Oct 3, 2019, 11:20:52 AM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.ModelDataT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.GenericSelectorT"%>
<% GenericSelectorT genericSelector = (GenericSelectorT) request.getSession().getAttribute("add");%>
<% if(genericSelector==null){genericSelector = (GenericSelectorT) request.getSession().getAttribute("principal");}%>
<form id="formulaire">
<div id="row">
    <div class="col-xs-12 col-md-12">
        <%ArrayList<ListaT> attributes = ControlFunctions.getLista(ControlPath.customRuleClick);%>
        <%ArrayList<ListaT> constants = ControlFunctions.LeerConstante("fieldName");%>
        <div class="input-group mb-5">
            <select class="custom-select" id="event" aria-describedby="basic-addon2">
                <%for(ListaT constante : attributes){%><option value="<%=constante.valor%>"><%=constante.valor%></option><%}%>
            </select>
            <div class="input-group-append">
                <span class="input-group-text" id="basic-addon2">add Custom Rule</span>
            </div>
            <select class="custom-select" id="event" aria-describedby="basic-addon2">
                <%for(ListaT constante : constants){%><option value="<%=constante.unit%>"><%=constante.valor%></option><%}%>
            </select>
            <div class="input-group-append">
                <span class="input-group-text" id="basic-addon2">add Event Item</span>
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
        <tbody>
            <% constants = ControlFunctions.LeerConstante("operation");%>
            <%ArrayList<ListaT> constants2 = ControlFunctions.LeerConstante("valueType");%>
            <% for(String s: genericSelector.getModelDatas().keySet()){%>
            <% ModelDataT model = genericSelector.getModelDatas().get(s);%>
                <tr id="-modelData">
                <td><input readonly class="form-control" type="text" id="-name" placeholder="name" value="<%= model.getName()%>"/></td>
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
                <td><i class="ti-trash" onclick="$(this).parent().parent().remove()"></i></td>
                </tr>
            <%}%>
        </tbody>
    </table>
    </div>    
</div>
</div>        
</form>

