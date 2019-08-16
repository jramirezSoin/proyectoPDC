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
    <div class="panel panel-default" id="-timeSpecification">
        <div class="panel-heading">Time Specification<span onclick="$(this).parent().parent().children('#colapse').toggle();" class="badge badge-pill badge-secondary"><i class="glyphicon glyphicon-chevron-down"></i></span><span onclick="$(this).parent().parent().remove();" class="badge badge-pill badge-danger"><i class="glyphicon glyphicon-remove"></i></span></div>
        <div class="panel-body" id="colapse">
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
        <div class="row">
        <div class="col-sm-6 mb-3 mb-md-0">    
        <div class="card" id="-daysOfWeek">
            <div class="card-header">
                Days
            </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><input class="form-control" type="text" 
                                               onkeypress="taginputs(this,'day');"></li>    
        <%for(int j=0;j<timeSpec.getDaysOfWeek().size();j++){%>
            <li class="list-group-item" id="-day"><%=timeSpec.getDaysOfWeek().get(j).valor%><span onclick="$(this).parent().remove();" class="badge badge-primary badge-pill"><i class="glyphicon glyphicon-remove"></i></span></li>
        <%}%>
        </ul>
        </div>
        </div>
        <div class="col-sm-6">
            <div class="card" id="-monthsOfYear">
            <div class="card-header">
                Months
            </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><input class="form-control" type="text" 
                                               onkeypress="taginputs(this,'month');"></li>   
        <%for(int j=0;j<timeSpec.getMonthsOfYear().size();j++){%>
        <li class="list-group-item" id="-month"><%=timeSpec.getMonthsOfYear().get(j).valor%><span onclick="$(this).parent().remove();" class="badge badge-primary badge-pill"><i class="glyphicon glyphicon-remove"></i></span></li>
        <%}%>
        </ul></div>
        </div>
        </div>
        </div>
    </div>
<%}%>
</div>
</form>
<div style='display: none'>
<div id="plantilla">
        <div class="panel panel-default" id="-timeSpecification">
        <div class="panel-heading">Time Specification<span onclick="$(this).parent().parent().children('#colapse').toggle();" class="badge badge-pill badge-secondary"><i class="glyphicon glyphicon-chevron-down"></i></span><span onclick="$(this).parent().parent().remove();" class="badge badge-pill badge-danger"><i class="glyphicon glyphicon-remove"></i></span></div>
        <div class="panel-body" id="colapse">
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
        <div class="row">
        <div class="col-sm-6 mb-3 mb-md-0">    
        <div class="card" id="-daysOfWeek">
            <div class="card-header">
                Days
            </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><input class="form-control" type="text" 
                                               onkeypress="taginputs(this, 'day');"></li>    
        </ul>
        </div>
        </div>
        <div class="col-sm-6">
        <div class="card" id="-monthsOfYear">
            <div class="card-header">
                Months
            </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item"><input class="form-control" type="text" 
                                               onkeypress="taginputs(this,'month');"></li>   
        </ul></div>
        </div>
        </div>
        </div>
    </div>
</div>
</div>
<script>
    function taginputs(id,tipo){
        if(event.keyCode === 13 && id.value!=""){
            $(id).parent().parent().append("<li class='list-group-item' id='-"+tipo+"'>"+id.value+"<span onclick='$(this).parent().remove();' class='badge badge-primary badge-pill'><i class='glyphicon glyphicon-remove'></i></span></li>");
            id.value="";
        }} 
    
</script>