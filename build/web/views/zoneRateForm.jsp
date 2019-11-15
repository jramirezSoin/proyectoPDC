<%-- 
    Document   : zoneRateForm
    Created on : Sep 4, 2019, 3:43:18 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
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
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<% ChargeRatePlanT chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("principal");%>
<% int index = ((ArrayList<Integer>) request.getSession().getAttribute("index")).get(2);%>
<% CrpRelDateRangeT rel = chargeRate.getSubscriberCurrency().getCrpRelDateRanges().get(index);%>
<script>
    var indexZRF=-1;
</script>
<form style="margin: 20px;" id="formulaire">
    <div class="row">
        <div class="col-sm-12 mb-3 mb-md-0">
            <div id="-zoneModel">
                <div id="zoneModelInfo">    
                    <div class="form-group row">
                        <% ArrayList<ListaT> zoneModels = ControlFunctions.getLista((String) ControlPath.zoneModelsClick,user);%>
                        <label>Zone Model Name<select onchange="getIC(); $('#ic').html(''); indexZRF=-1;" class="custom-select" id="-zoneModelName">
                                <%for (int j = 0; j < zoneModels.size(); j++) {%>
                                <option <%=(rel.getZoneModel()!=null && zoneModels.get(j).valor.equals(rel.getZoneModel().getZoneModelName())) ? "selected" : ""%> value="<%=zoneModels.get(j).valor%>"><%=zoneModels.get(j).valor%></option>
                                <%}%>
                            </select></label>
                    </div>
                    <div class="custom-control custom-checkbox col-sm-6">
                        <input id="enhanced" onclick="change();" type="checkbox" class="custom-control-input">
                        <label class="custom-control-label" for="enhanced">Enhanced</label>
                    </div>    
                    <input id="-enhanced" type="text" value="false" hidden="" class="form-control">    
                    <div class="form-group row col-sm-6" id="uscModelName">
                        <% ArrayList<ListaT> usc = ControlFunctions.getLista((String) ControlPath.uscMapClick,user);%>
                        <label>Usc Model<select class="custom-select" id="-uscModelName">
                                <%for (int j = 0; j < usc.size(); j++) {%>
                                <option <%=(rel.getZoneModel()!=null && usc.get(j).valor.equals(rel.getZoneModel().getUscModelName())) ? "selected" : ""%> value="<%=usc.get(j).valor%>"><%=usc.get(j).valor%></option>
                                <%}%>
                            </select></label>
                    </div>
                    <div class="form-group row col-sm-6">
                        <label>Impact Categories<select class="custom-select" id="impacts">
                            </select></label>
                    </div>
                    <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                        <button type="button" class="btn btn-xs btn-primary bg1" onclick="addNew($('#impacts').val());">Add New</button>
                        <button type="button" class="btn btn-xs btn-primary bg1" onclick="addToActual($('#impacts').val());">Add to Actual</button>
                    </div>        
                </div>
            </div>
        </div>
                                                
    </div>                        
    <div class="row">
        <div class="col-sm-7 mb-3 mb-md-0" id="icDesc">
            <%if(rel.getZoneModel()!=null) for(int i=0;i<rel.getZoneModel().getResults().size();i++){
                ResultsT result= rel.getZoneModel().getResults().get(i);
            %>
            <div class="card mt-5" style="display: none;" id='-results_n<%=i%>'>
                <div class="card-header">
                    <a id='-nameS'><%=result.getName()%></a>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div style="display: none;" class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                            <button type="button" class="btn btn-xs btn-primary bg1" onclick="addNew($('#impacts').val());">Generic Selector</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" onclick="addToActual($('#impacts').val());">Time Model</button>
                            <button type="button" class="btn btn-xs btn-primary bg1" onclick="addToActual($('#impacts').val());">Pricing</button>
                        </div>    
                    </div>
                    <div class="row">
                        <div class="result" id="<%=((result.getResult() instanceof GenericSelectorT)?"-genericSelector":((result.getResult() instanceof TimeConfigurationT)?"-timeConfiguration":"-crpCompositePopModel"))%>">
                            <div class="form-group row col-sm-12">
                                <label>Time Model</label>
                                <%if(result.getResult() instanceof GenericSelectorT){%>
                                    <input readonly class="form-control" type="text" id="-genericSelectorName" placeholder="name" value="<%=((GenericSelectorT)result.getResult()).getGenericSelectorName()%>"/>
                                <%}else if(result.getResult() instanceof TimeConfigurationT){%>
                                    <input readonly class="form-control" type="text" id="-timeModelName" placeholder="name" value="<%=((TimeConfigurationT)result.getResult()).getTimeModelName()%>"/>
                                <%}%>
                            </div>
                            <div class="form-group row col-sm-12">
                                <label>Tags<select class="custom-select" onclick="addResult($(this).val());" id="resultsGen">
                                </select></label>
                            </div>
                            <div id="-resultsGen">
                                <%if(result.getResult() instanceof GenericSelectorT){for(int gens=0;gens<((GenericSelectorT)result.getResult()).getResults().size();gens++){
                                    ResultsT genres = ((GenericSelectorT)result.getResult()).getResults().get(gens);
                                %>
                                    <span id='-name_<%=gens%>' class="badge badge-pill badge-primary"><%=genres.getName().get(0)%><i class="ti-close" onclick="$(this).parent().remove();"></i></span>
                                <%}}else if(result.getResult() instanceof TimeConfigurationT){for(int gens=0;gens<((TimeConfigurationT)result.getResult()).getTags().size();gens++){
                                    TagsT genres = ((TimeConfigurationT)result.getResult()).getTags().get(gens);
                                %>
                                    <span id='-name_<%=gens%>' class="badge badge-pill badge-primary"><%=genres.getName()%><i class="ti-close" onclick="$(this).parent().remove();"></i></span>
                                <%}}%>
                            </div>    
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
        <div class="col-sm-5 mb-3 mb-md-0">
            <div class="list-group" id="ic">
                <%if(rel.getZoneModel()!=null) for(ResultsT result: rel.getZoneModel().getResults()){%>
                    <button type="button" onclick="indexZRF=$(this).index(); changeShow();" class="list-group-item list-group-item-action"><%=result.getName()%></button>
                <%}%>
            </div>    
        </div>     
    </div>
                    
                    
                    
                    
    <script>
        $("#uscModelName").toggle();
        function change() {
            $("#-enhanced").val($("#enhanced").prop("checked").toString() + "");
            $("#uscModelName").toggle();
        }
        <%if (rel.getZoneModel()!=null && rel.getZoneModel().isEnhanced()) {%>
        $("#enhanced").prop("checked", true);
        change();
        <%}%>
        function getIC(){
            var parameters= { 'filtro' : 'name;'+$("#-zoneModelName").val()+',zoneItem;',
            'funcion' : 'getListaFiltroDeep',
            'tipo' : '<%=ControlPath.zoneModelsClick%>',
            'conjunto' : 'true',
            'buscar' : 'zoneName'}
            consulta("impacts",parameters,true);
        }
        getIC();
        function addResult(val){
            if(!($("#icDesc").children().eq(indexZRF).find("#-resultsGen").text()).includes(val)){
                $("#icDesc").children().eq(indexZRF).find("#-resultsGen").append("<span id='-nameResult' class='badge badge-pill badge-primary'>"+val+"<i class='ti-close' onclick='$(this).parent().remove();'></i></span>")
            }
        }
        function addNew(val){
            if(!($("#ic").text()).includes(val))
            $("#ic").append("<button type='button'onclick='indexZRF=$(this).index(); changeShow();' class='list-group-item list-group-item-action'>["+val+"]</button>")
            $("#icDesc").append($("#clonable").html().replace("Nombre",val));
        }
        function addToActual(val){
            if(indexZRF!=-1){
            var ics= $("#ic").children().eq(indexZRF).text();
            console.log(ics);
            if(!($("#ic").text()).includes(val)){
                ics= ics.replace("]",", "+val+"]");
                $("#ic").children().eq(indexZRF).text(ics);
                $("#icDesc").children().eq(indexZRF).find('a').text(ics);
            }
            }
        }
        function getTimeModel(){
            return { 
            'funcion' : 'getLista',
            'tipo' : '<%=ControlPath.timeModelsClick%>'}
        }
        function getGenericSelector(){
            return { 
            'funcion' : 'getLista',
            'tipo' : '<%=ControlPath.genericSelectorClick%>'}
        }
        function getTimes(val){
            return { 'filtro' : 'name;'+val+',timeModelTag;',
            'funcion' : 'getListaFiltroDeep',
            'tipo' : '<%=ControlPath.timeModelsClick%>',
            'buscar' : 'tagName'}
        }
        function getGenerics(val){
            return { 'filtro' : 'name;'+val+',rule;',
            'funcion' : 'getListaFiltroDeep',
            'tipo' : '<%=ControlPath.genericSelectorClick%>',
            'buscar' : 'name'}
        }
        function changeShow(){
            $("#icDesc").children().hide();
            $("#icDesc").children().eq(indexZRF).show();
            var id= $("#icDesc").children().eq(indexZRF).find(".result").attr("id");
            var val="";
            var parameters;
            if(id!="-crpCompositePopModel"){
                if(id=="-timeConfiguration"){
                    val=$("#icDesc").children().eq(indexZRF).find("#-timeModelName").val();
                    console.log("VAL"+val);
                    parameters=getTimes(val);
                }else{
                    console.log("VAL"+val);
                    val=$("#icDesc").children().eq(indexZRF).find("#-genericSelectorName").val();
                    parameters=getGenerics(val);
                }
                consultaByElement($("#icDesc").children().eq(indexZRF).find("#resultsGen"),parameters);
            }
        }
        
        function addClonable(tipo,actual){
            if(tipo!='pricing'){
                $(actual).parent().parent().parent().append($('#'+tipo+'Clonable').html());
                changeShow();
            }
            $(actual).parent().parent().remove();
        }
        
        function changeTags(actual,tipo){
            if(tipo=='generic')
                consultaByElement($('#icDesc').children().eq(indexZRF).find('#resultsGen'),getGenerics($(actual).val()));
            else if(tipo=='time')
                consultaByElement($('#icDesc').children().eq(indexZRF).find('#resultsGen'),getTimes($(actual).val()));
            $('#icDesc').children().eq(indexZRF).find('#-resultsGen').html("");
        }
        
    </script>   
    
    
</form>

<div id="clonable">
<div class="card mt-5" style="display: none;" id='-results'>
    <div class="card-header">
        <a id='-nameS'>Nombre</a>
    </div>
    <div class="card-body">
        <div class="row">
            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                <button type="button" class="btn btn-xs btn-primary bg1" onclick="addClonable('generic',this)">Generic Selector</button>
                <button type="button" class="btn btn-xs btn-primary bg1" onclick="addClonable('time',this)">Time Model</button>
                <button type="button" class="btn btn-xs btn-primary bg1" onclick="addClonable('pricing',this)">Pricing</button>
            </div>    
        </div>
    </div>
</div>
</div>
<div id="timeClonable" style="display: none;">
    <div class="row">
        <div class="result" id="-timeConfiguration">
            <div class="form-group row col-sm-12">
                <label>Time Model<select class="custom-select" onchange="changeTags(this,'time');" id="-timeModelName">
                </select></label>

            </div>
            <div class="form-group row col-sm-12">
                <label>Results<select class="custom-select" onclick="addResult($(this).val());" id="resultsGen">
                </select></label>
            </div>
            <div id="-resultsGen">

            </div>    
        </div>
    </div>
</div>
<div id="genericClonable" style="display: none;">
    <div class="row">
        <div class="result" id="-genericSelector">
            <div class="form-group row col-sm-12">
                <label>Time Model<select class="custom-select" onclick="changeTags(this,'generic');" id="-genericSelectorName">
                </select></label>

            </div>
            <div class="form-group row col-sm-12">
                <label>Results<select class="custom-select" onclick="addResult($(this).val());" id="resultsGen">
                </select></label>
            </div>
            <div id="-resultsGen">

            </div>    
        </div>
    </div>
</div>

<script>
    consultaByElement($("#genericClonable").find("#-genericSelectorName"),getGenericSelector());
    consultaByElement($("#timeClonable").find("#-timeModelName"),getTimeModel());
</script>