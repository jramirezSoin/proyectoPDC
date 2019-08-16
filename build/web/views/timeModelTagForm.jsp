<%-- 
    Document   : timeModelTagForm
    Created on : Aug 14, 2019, 12:47:56 PM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.TimeModelTagT.TimeSpecT"%>
<%@page import="datos.TimeModelT"%>
<%@page import="datos.TimeModelTagT"%>
<%@page import="java.util.ArrayList"%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% TimeModelTagT timeModelTag = (TimeModelTagT) request.getSession().getAttribute("add");%>
<%if(timeModelTag==null){timeModelTag = ((TimeModelT)request.getSession().getAttribute("principal")).getTimeModelTags().get(index);}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-tagName">Tag Name</label>
<input class="form-control" type="text" id="-tagName" placeholder="Tag Name" value="<%=timeModelTag.getTagName()%>"/>
</div>
<input type="button" onclick="$('#plantilla').clone().appendTo('#timeEspecifications');" value="Add"/>
<div id="timeEspecifications">
<%for(int i=0;i<timeModelTag.getTimeSpecs().size();i++){%>
    <%TimeSpecT timeSpec = timeModelTag.getTimeSpecs().get(i);%>
    <div id="-timeSpecification">
        <h2><small>Time Specification</small><span onclick="$(this).parent().parent().children('#colapse').toggle();" class="badge badge-pill badge-secondary"><i class="glyphicon glyphicon-chevron-down"></i></span><span onclick="$(this).parent().parent().remove();" class="badge badge-pill badge-danger"><i class="glyphicon glyphicon-remove"></i></span></h2>
        <div id="colapse">
        <div class="form-group row">
            <label for="-name">Name</label>
            <input class="form-control" type="text" id="-name" placeholder="Name" value="<%=timeSpec.getName()%>"/>
        </div>
        <div class="form-group row">
            <label for="-description">Description</label>
            <input class="form-control" type="text" id="-description" placeholder="Description" value="<%=timeSpec.getDescription()%>"/>
        </div>
        <div class="form-group row">
            <label for="-timeOfDay">Time Of Day</label>
            <input class="form-control" type="text" id="-timeOfDay" placeholder="Time Of Day" value="<%=timeSpec.getTimeOfDay()%>"/>
        </div>
        <div class="form-group row">
            <label for="-holiday">Holiday</label>
            <input class="form-control" type="text" id="-holiday" placeholder="Holiday" value="<%=timeSpec.getHoliday()%>"/>
        </div>
        <div class="form-group row">
            <label for="-daysOfMonth">Days Of Month</label>
            <input class="form-control" type="text" id="-daysOfMonth" placeholder="Days Of Month" value="<%=timeSpec.getDaysOfMonth()%>"/>
        </div>
        <div class="container">
            <h3><small>Days</small></h3>
        <%for(int j=0;j<timeSpec.getDaysOfWeek().size();j++){%>
            <div class="col-sm-1 panel panel-default"><%=timeSpec.getDaysOfWeek().get(j).valor%></div>
        <%}%>
        </div>
        <div class="container">
            <h3><small>Months</small></h3>
        <div class="row">
        <%for(int j=0;j<timeSpec.getMonthsOfYear().size();j++){%>
        <div class="col-sm-1 panel panel-default" style="border-style: solid;"><%=timeSpec.getMonthsOfYear().get(j).valor%></div>
        <%}%>
        </div>
        </div>
        </div>
    </div>
<%}%>
</div>
</form>
<div style='display: none'>
<div id="plantilla">
    <div id="-timeSpecification">
        <h2><small>Time Specification</small><span onclick="$(this).parent().parent().children('#colapse').toggle();" class="badge badge-pill badge-primary"><i class="glyphicon glyphicon-chevron-down"></i></span><span onclick="$(this).parent().parent().remove();" class="badge badge-pill badge-danger"><i class="glyphicon glyphicon-remove"></i></span></h2>
        <div id="colapse">
        <div class="form-group row">
            <label for="-name">Name</label>
            <input class="form-control" type="text" id="-name" placeholder="Name" value=""/>
        </div>
        <div class="form-group row">
            <label for="-description">Description</label>
            <input class="form-control" type="text" id="-description" placeholder="Description" value=""/>
        </div>
        <div class="form-group row">
            <label for="-timeOfDay">Time Of Day</label>
            <input class="form-control" type="text" id="-timeOfDay" placeholder="Time Of Day" value=""/>
        </div>
        <div class="form-group row">
            <label for="-holiday">Holiday</label>
            <input class="form-control" type="text" id="-holiday" placeholder="Holiday" value=""/>
        </div>
        <div class="form-group row">
            <label for="-daysOfMonth">Days Of Month</label>
            <input class="form-control" type="text" id="-daysOfMonth" placeholder="Days Of Month" value=""/>
        </div>
        </div>
    </div>
</div>
</div>