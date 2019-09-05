<%-- 
    Document   : crpRelDateForm
    Created on : Sep 3, 2019, 3:55:28 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ratePlan.CrpRelDateRangeT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ratePlan.ChargeRatePlanT"%>
<% ChargeRatePlanT chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("principal");%>
<script>
$(function () {
$.datepicker.setDefaults($.datepicker.regional["en"]);
$(".fecha").datepicker({
dateFormat: 'dd M yy',
});
});
$(".fecha").on("change",function(){
        var begins= document.getElementsByClassName("begin");
        var ends= document.getElementsByClassName("end");
        for(var i=begins.length-1; i>=0; i--){
            console.log(begins[i].value+" "+ends[i].value)
            if(ends[i].value!="Never ends"){
                if(begins[i].value!="Now"){
                   if((new Date(begins[i].value)).getTime() > (new Date(ends[i].value)).getTime()){
                       begins[i].value= ends[i].value;
                   }
                }
            }
            if(i!=0){
                ends[i-1].value=begins[i].value;
            }
        }
});

function mychange(){
    var begins= document.getElementsByClassName("begin");
        var ends= document.getElementsByClassName("end");
        for(var i=begins.length-1; i>=0; i--){
            console.log(begins[i].value+" "+ends[i].value)
            if(ends[i].value!="Never ends"){
                if(begins[i].value!="Now"){
                   if((new Date(begins[i].value)).getTime() > (new Date(ends[i].value)).getTime()){
                       begins[i].value= ends[i].value;
                   }
                }
            }
            if(i!=0){
                ends[i-1].value=begins[i].value;
            }
        }
}
</script>
<form style="margin: 20px;" id="formulaire">
    <div class="custom-control custom-checkbox">
        <input id="begin" onclick="noTermina();" type="checkbox" class="custom-control-input">
        <label class="custom-control-label" for="begin">Never Start</label>
        </div>
    <div class="custom-control custom-checkbox">
        <input id='end' onclick="noTermina();" type="checkbox" class="custom-control-input">
        <label class="custom-control-label" for="end">Never End</label>
    </div>
    <div id='-crpDelRanges'>
    <table class="table table-responsive text-center">
        <thead class="text-uppercase">
            <tr>
                <th>Start</th>
                <th>End</th>
            </tr>  
        </thead>
        <tbody id="theBody">
            <%for(CrpRelDateRangeT rel: chargeRate.getSubscriberCurrency().getCrpRelDateRanges()){%>
            <tr>
                <%if(ControlFunctions.getParseDate(rel.getStartDate()).equals("Now")){%>
                    <script>$("#begin").prop("checked",true);</script>
                <%}%>
                <%if(ControlFunctions.getParseDate(rel.getEndDate()).equals("Never ends")){%>
                    <script>$("#end").prop("checked",true);</script>
                <%}%>
                <td><input readonly="" type='text' id="-startDate_<%=rel.getId()%>" class="form-control fecha begin"  value='<%=ControlFunctions.getParseDate(rel.getStartDate())%>'></td>
                <td><input readonly="" type='text' id="-endDate_<%=rel.getId()%>" class="form-control end" value='<%=ControlFunctions.getParseDate(rel.getEndDate())%>'></td>
                
            </tr>
            <%}%>            
        </tbody>
    </table>
        <div class="form-group row">
            <input class="form-control col-sm-3" type="text" id="new" placeholder="Add"/>
            <script>$("#new").datepicker({dateFormat: 'dd M yy'}).datepicker("setDate",new Date()); var newDate=0;</script>
            <input class="btn btn-primary btn-sm" type="button" onclick="addNew();" value="Add">
        </div>    
    </div>
</form>

<script>
    function noTermina(){
        if($("#begin").prop("checked")){
            $(".begin:eq(0)").val("Now");
            $(".begin:eq(0)").datepicker("destroy");
            $(".begin:eq(0)").removeClass('fecha hasDatepicker');
        }else{
            $(".begin:eq(0)").val("01 Jan 2010");
            $(".begin:eq(0)").datepicker({dateFormat: 'dd M yy'});
            $(".begin:eq(0)").addClass('fecha hasDatepicker');
        }
        if($("#end").prop("checked")){
            $(".end").eq(($(".end").length)-1).val("Never ends");
            $(".end").eq(($(".end").length)-1).datepicker("destroy");
            $(".end").eq(($(".end").length)-1).removeClass('fecha hasDatepicker');
        }else{
            $(".end").eq(($(".end").length)-1).datepicker({dateFormat: 'dd M yy'}).datepicker("setDate",new Date());
            $(".end").eq(($(".end").length)-1).addClass('fecha hasDatepicker');
        }
            
        
    }
    function addNew(){
        var fin= $(".end").eq(($(".end").length)-1).val();
        $("#theBody").append("<tr><td><input readonly='' type='text' id='-startDate_n"+newDate+"' class='form-control fecha begin' onChange='mychange();'  value='"+$("#new").val()+"'></td><td><input readonly='' type='text' id='-endDate_n"+newDate+"' class='form-control end' value='"+fin+"'></td></tr>");
        $(".end").eq(($(".end").length)-2).val($("#new").val());
        $(".begin").eq(($(".begin").length)-1).datepicker({dateFormat: 'dd M yy'});
        $("#new").datepicker("setDate",new Date());
        newDate++;
    }
    noTermina();
</script>    