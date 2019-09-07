<%-- 
    Document   : zoneRateForm
    Created on : Sep 4, 2019, 3:43:18 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ratePlan.TagsT"%>
<%@page import="datos.ratePlan.TimeConfigurationT"%>
<%@page import="datos.ratePlan.GenericSelectorT"%>
<%@page import="datos.ratePlan.ResultsT"%>
<%@page import="datos.ratePlan.CrpRelDateRangeT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ratePlan.ChargeRatePlanT"%>
<% ChargeRatePlanT chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("principal");%>
<% int index = ((ArrayList<Integer>) request.getSession().getAttribute("index")).get(2);%>
<% CrpRelDateRangeT rel = chargeRate.getSubscriberCurrency().getCrpRelDateRanges().get(index);%>
<form style="margin: 20px;" id="formulaire">
<div class="row">
<div class="col-sm-5 mb-3 mb-md-0">
<div id="-zoneModel">
<div id="zoneModelInfo">    
<div class="form-group row">
<% ArrayList<ListaT> zoneModels = ControlFunctions.getLista((String)ControlPath.zoneModelsClick);%>
<i onclick="agregarResult();" class="ti-plus"></i>
<label>Zone Model Name<select onclick="$('#zone').text($(this).val())" class="custom-select" id="-zoneModelName">
    <%for(int j=0;j<zoneModels.size();j++){%>
    <option <%=(zoneModels.get(j).valor.equals(rel.getZoneModel().getZoneModelName()))?"selected":""%> value="<%=zoneModels.get(j).valor%>"><%=zoneModels.get(j).valor%></option>
    <%}%>
    </select></label>
</div>
<div class="custom-control custom-checkbox col-sm-6">
    <input id="enhanced" onclick="change();" type="checkbox" class="custom-control-input">
    <label class="custom-control-label" for="enhanced">Enhanced</label>
</div>    
    <input id="-enhanced" type="text" value="false" hidden="" class="form-control">    
<div class="form-group row col-sm-6" id="uscModelName">
<% ArrayList<ListaT> usc = ControlFunctions.getLista((String)ControlPath.uscMapClick);%>
    <label>Usc Model<select class="custom-select" id="-uscModelName">
    <%for(int j=0;j<usc.size();j++){%>
    <option <%=(usc.get(j).valor.equals(rel.getZoneModel().getUscModelName()))?"selected":""%> value="<%=usc.get(j).valor%>"><%=usc.get(j).valor%></option>
    <%}%>
    </select></label>
</div>
</div>
    <%for(ResultsT resul : rel.getZoneModel().getResults()){%>
    <div>
    <div style="display: none" class="card res" id="-results">
        <div class="card-header">
            Result
        </div>
        <div class="card-body">
            <input class="form-control" type="text" onkeypress="taginputs(this,'name');">    
        <%for(int j=0;j<resul.getName().size();j++){%>
            <span id="-name"  class="badge badge-primary badge-pill"><%=resul.getName().get(j)%><i onclick="$(this).parent().remove();" class="ti-close"></i></span>
        <%}%>
        </div>
    </div>
        <%if(resul.getResult() instanceof TimeConfigurationT){%>
        <%TimeConfigurationT gen= (TimeConfigurationT) resul.getResult();%>
        <div id="timediv">
        <div style="display: none" class="card res time" id="-timeConfiguration">
            <div class="card-header">
                Time Configuration
                <i onclick="agregarResultDeep($(this).parent().parent());" class="ti-plus"></i>
            </div>
            <div class="card-body">
                <input class="form-control" id="-name" onkeyup="changeText(this);" type="text" value="<%=gen.getTimeModelName()%>">    
            </div>
        </div>
            <%for(TagsT rgen : gen.getTags()){%>    
            <div style="display: none" class="card res time" id="-resultsGen">
                <div class="card-header">
                    Result Generic Selector
                </div>
                <div class="card-body">
                    <input class="form-control" id="-name" onkeyup="changeText(this);" type="text" value="<%=rgen.getName()%>">    
                </div>
            </div>
            <%}%>
        </div>    
        <%} else if(resul.getResult() instanceof GenericSelectorT){%>
        <%GenericSelectorT gen= (GenericSelectorT) resul.getResult();%>
        <div id="gendiv">
        <div style="display: none" class="card res time" id="-genericSelector">
            <div class="card-header">
                Time Configuration
                <i onclick="agregarResultDeep($(this).parent().parent());" class="ti-plus"></i>
            </div>
            <div class="card-body">
                <input class="form-control" id="-name" onkeyup="changeText(this);" type="text" value="<%=gen.getGenericSelectorName()%>">    
            </div>
        </div>
            <%for(ResultsT rgen : gen.getResults()){%>    
            <div style="display: none" class="card res time" id="-resultsGen">
                <div class="card-header">
                    Result Generic Selector
                </div>
                <div class="card-body">
                    <input class="form-control" id="-name" onkeyup="changeText(this);" type="text" value="<%=rgen.getName().get(0)%>">    
                </div>
            </div>
            <%}%>
        </div>    
        <%}%>
    </div>    
    <%}%>
</div>
</div>
<div class="col-sm-7 mb-3 mb-md-0">
    
    <ul id="myUL" class="list-group">
    <li class="list-group-item">
      <span id="zone" class="caret caret-down"><%=rel.getZoneModel().getZoneModelName()%></span>
      <span class="badge badge-pill badge-info">Zone</span>
      <ul class="nested active">
        <%for(ResultsT resul : rel.getZoneModel().getResults()){%>  
          <%if(resul.getResult() instanceof GenericSelectorT){%>
            <div>
            <li class="result"><span class="caret list-group-item"><%= resul.getName().toString().replaceAll(","," ").replace("[","").replace("]","")%></span></li>
            <%GenericSelectorT gen= (GenericSelectorT) resul.getResult();%>
            <ul class="nested active">
                <li class="list-group-item result"><span class="caret caret-down"><%=gen.getGenericSelectorName()%></span><span class="badge badge-pill badge-danger">Gen</span></li>  
                <ul class="nested active">
                  <%for(ResultsT genres : gen.getResults()){%>  
                  <li class="list-group-item result"><span><%=genres.getName().get(0)%></span></li>
                  <%}%>
                </ul>
            </ul>
            </div>
          <%}else if(resul.getResult() instanceof TimeConfigurationT){%>
            <div>
            <li class="result"><span class="caret list-group-item"><%= resul.getName().toString().replaceAll(","," ").replace("[","").replace("]","")%></span></li>
            <%TimeConfigurationT gen= (TimeConfigurationT) resul.getResult();%>
            <ul class="nested active">
                <li class="list-group-item result"><span class="caret caret-down"><%=gen.getTimeModelName()%></span><span class="badge badge-pill badge-primary">Time</span></li>  
                <ul class="nested active">
                  <%for(TagsT genres : gen.getTags()){%>  
                    <li class="list-group-item result"><span><%=genres.getName()%></span></li>
                  <%}%>
                </ul>
            </ul>
            </div>
          <%}else{%>
            <li class="result"><span class="caret list-group-item"><%= resul.getName().toString().replaceAll(","," ").replace("[","").replace("]","")%><span class="badge badge-pill badge-success">Pop</span></span></li>
          <%}%>
        <%}%>
      </ul>
    </li>
  </ul>
</div>
</div>
<script>
    $("#uscModelName").toggle();
    function change(){
        $("#-enhanced").val($("#enhanced").prop("checked").toString()+"");
        $("#uscModelName").toggle();
    }
    <%if(rel.getZoneModel().isEnhanced()){%>
        $("#enhanced").prop("checked",true);
        change();
    <%}%>
    function tagTree(){
        var badges= $(this).children(".card-body:eq(0)").children(".badge");
        var index= $(".res").index($(this));
        console.log(index);
        for(var i = 0; i < badges; i++) {
            console.log(badges[i].value);
        }
    }
    function taginputs(id,tipo){
        if(event.keyCode === 13 && id.value!=""){
            $(id).parent().append("<span id='-"+tipo+"' class='badge badge-primary badge-pill'>"+id.value+"<i onclick='$(this).parent().remove();' class='ti-close'></i></span>");
            id.value="";
        }} 
    $(".res").on("click", tagTree());
    
    $(".result").on("click", function(){
        $(".res").hide();
        $(".res").eq($(".result").index($(this))).toggle();
    });
    function changeText(id){
        $(".result").eq($(".res").index($(id).parent().parent())).children("span:eq(0)").text(id.value);
    }
    function agregarResultDeep(id){
        var li= $(".result").eq($(".res").index($(id))).next().children().last();
        $(li).after($(li).clone());
        $(id).parent().append($(id).parent().children().last().clone());
        
        $(".result").on("click", function(){
        $(".res").hide();
        $(".res").eq($(".result").index($(this))).toggle();
        });      
    }
    
    function agregarResult(){
        $("#-zoneModel").append($("#-zoneModel").children().last().clone());
        $("#zone").next().next().append($("#zone").next().next().children().last().clone());
        
        $(".result").on("click", function(){
        $(".res").hide();
        $(".res").eq($(".result").index($(this))).toggle();
        });
    }
    
</script>    
</form>

