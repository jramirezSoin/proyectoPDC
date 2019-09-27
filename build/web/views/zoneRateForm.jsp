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
<script>
    var indexZRF=-1;
</script>
<form style="margin: 20px;" id="formulaire">
    <div class="row">
        <div class="col-sm-12 mb-3 mb-md-0">
            <div id="-zoneModel">
                <div id="zoneModelInfo">    
                    <div class="form-group row">
                        <% ArrayList<ListaT> zoneModels = ControlFunctions.getLista((String) ControlPath.zoneModelsClick);%>
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
                        <% ArrayList<ListaT> usc = ControlFunctions.getLista((String) ControlPath.uscMapClick);%>
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
            <%if(rel.getZoneModel()!=null) for(ResultsT result: rel.getZoneModel().getResults()){%>
            <div class="card mt-5" style="display: none;">
                <div class="card-header">
                    <a><%=result.getName()%></a>
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
                                <label>name</label>
                                <%if(result.getResult() instanceof GenericSelectorT){%>
                                    <input readonly class="form-control" type="text" id="-name" placeholder="name" value="<%=((GenericSelectorT)result.getResult()).getGenericSelectorName()%>"/>
                                <%}else if(result.getResult() instanceof TimeConfigurationT){%>
                                    <input readonly class="form-control" type="text" id="-name" placeholder="name" value="<%=((TimeConfigurationT)result.getResult()).getTimeModelName()%>"/>
                                <%}%>
                            </div>
                            <div class="form-group row col-sm-6">
                                <label>name<select class="custom-select" onclick="addResult($(this).val());" id="resultsGen">
                                </select></label>
                            </div>
                            <div id="-resultsGen">
                                <%if(result.getResult() instanceof GenericSelectorT){for(ResultsT genres : ((GenericSelectorT)result.getResult()).getResults()){%>
                                    <span class="badge badge-pill badge-primary"><%=genres.getName().get(0)%></span>
                                <%}}else if(result.getResult() instanceof TimeConfigurationT){for(TagsT genres : ((TimeConfigurationT)result.getResult()).getTags()){%>
                                    <span class="badge badge-pill badge-primary"><%=genres.getName()%></span>
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
                $("#icDesc").children().eq(indexZRF).find("#-resultsGen").append("<span class='badge badge-pill badge-primary'>"+val+"</span>")
            }
        }
        function addNew(val){
            if(!($("#ic").text()).includes(val))
            $("#ic").append("<button type='button'onclick='indexZRF=$(this).index(); changeShow();' class='list-group-item list-group-item-action'>["+val+"]</button>")
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
        function getTimes(val){
            return {
            'funcion' : 'getLista',
            'tipo' : '<%=ControlPath.attributeSpecMapClick%>',
            }
        }
        function getGenerics(val){
            return {
            'funcion' : 'getLista',
            'tipo' : '<%=ControlPath.zoneModelsClick%>',
            }
        }
        function changeShow(){
            $("#icDesc").children().hide();
            $("#icDesc").children().eq(indexZRF).show();
            var id= $("#icDesc").children().eq(indexZRF).find(".result").attr("id");
            var val= $("#icDesc").children().eq(indexZRF).find("#-name").val();
            var parameters;
            if(id!="-crpCompositePopModel"){
                if(id=="-timeConfiguration") parameters=getTimes(val);
                else parameters=getGenerics(val);
                consultaByElement($("#icDesc").children().eq(indexZRF).find("#resultsGen"),parameters);
            }
        }
        
    </script>   
    
    
</form>

