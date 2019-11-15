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
<script>var indiceFormTimeModelTag = 100;</script>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-tagName">Tag Name</label>
<input class="form-control" type="text" id="-tagName" placeholder="Tag Name" value="<%=timeModelTag.getTagName()%>"/>
</div>
<input class="btn btn-sm btn-primary" type="button" onclick="$('#timeEspecifications').append($('#plantilla').html().replace(/Nid/g,indiceFormTimeModelTag+'')); indiceFormTimeModelTag++;" value="Add"/>
<div id="timeEspecifications" class="according accordion-s3">
<%for(int i=0;i<timeModelTag.getTimeSpecs().size();i++){%>
    <%TimeSpecT timeSpec = timeModelTag.getTimeSpecs().get(i);%>
    <div class="card" id="-timeSpecification">
        <div class="card-header"><a class="collapsed card-link" data-toggle="collapse" href="#accordion<%=i%>">Time Specification</a>
        </div>
        <div id="accordion<%=i%>" class="collapse" data-parent="#timeEspecifications">
            <div class="card-body">
                <a onclick="$(this).parent().parent().parent().remove();"><i class="ti-close"></i></a>
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
                    <label>Holiday<select class="custom-select" id="-holiday">
                            <option <%=(timeSpec.getHoliday()) ? "Selected" : ""%> value="true">true</option>
                            <option <%=(!timeSpec.getHoliday()) ? "Selected" : ""%> value="false">false</option>
                        </select></label>
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
                            <div class="card-body">
                                <input class="form-control" type="text" onkeypress="taginputs(this,'day');">    
                            <%for(int j=0;j<timeSpec.getDaysOfWeek().size();j++){%>
                                <span id="-day"  class="badge badge-primary badge-pill"><%=timeSpec.getDaysOfWeek().get(j).valor%><i onclick="$(this).parent().remove();" class="ti-close"></i></span>
                            <%}%>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card" id="-monthsOfYear">
                        <div class="card-header">
                            Months
                        </div>
                            <div class="card-body">
                                <input class="form-control" type="text" onkeypress="taginputs(this,'month');">   
                            <%for(int j=0;j<timeSpec.getMonthsOfYear().size();j++){%>
                            <span id="-month" class="badge badge-primary badge-pill"><%=timeSpec.getMonthsOfYear().get(j).valor%><i onclick="$(this).parent().remove();" class="ti-close"></i></span>
                            <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
    </div>
<%}%>


</div>
</form>
<div style='display: none'>
<div id="plantilla">
    <div class="card" id="-timeSpecification">
        <div class="card-header"><a class="collapsed card-link" data-toggle="collapse" href="#accordionNNid">Time Specification</a>
        </div>
        <div id="accordionNNid" class="collapse" data-parent="#timeEspecifications">
            <div class="card-body">
                <a onclick="$(this).parent().parent().parent().remove();"><i class="ti-close"></i></a>
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
                                                                   onkeypress="taginputs(this,'day');"></li>    
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
                            </ul>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
    </div>
</div>
</div>
<script>
    function taginputs(id,tipo){
        if(event.keyCode === 13 && id.value!=""){
            $(id).parent().append("<span id='-"+tipo+"' class='badge badge-primary badge-pill'>"+id.value+"<i onclick='$(this).parent().remove();' class='ti-close'></i></span>");
            id.value="";
        }} 
    
</script>