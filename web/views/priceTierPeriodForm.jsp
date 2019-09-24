<%-- 
    Document   : crpRelDateForm
    Created on : Sep 3, 2019, 3:55:28 PM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ratePlan.PriceTierRangeT"%>
<%@page import="datos.ratePlan.PriceTierValidityPeriodT"%>
<%@page import="datos.ratePlan.CrpCompositePopModelT"%>
<%@page import="datos.ratePlan.CrpRelDateRangeT"%>
<%@page import="control.ControlPath"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ListaT"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.ratePlan.ChargeRatePlanT"%>
<% CrpCompositePopModelT crp = (CrpCompositePopModelT) request.getSession().getAttribute("composite");%>
<script>
$(function () {
$.datepicker.setDefaults($.datepicker.regional["en"]);
$(".fecha").datepicker({
dateFormat: 'dd M yy',
});
});
$(".fecha").on("change",function(){
        var begins= document.getElementsByClassName("begin");
        for(var i=begins.length-1; i>=0; i--){
            if(i!=0){
            if(begins[i-1].value!="Now"){
               if((new Date(begins[i-1].value)).getTime() > (new Date(begins[i].value)).getTime()){
                   var k= begins[i].value;
                   begins[i].value= begins[i-1].value;
                   begins[i-1].value= k;
               }else if((new Date(begins[i-1].value)).getTime() == (new Date(begins[i].value)).getTime()){
                       $(begins[i]).parent().parent().remove();
                   }
            }
            }
        }
});

function mychange(){
        var begins= document.getElementsByClassName("begin");
            for(var i=begins.length-1; i>=0; i--){
                if(i!=0){
                if(begins[i-1].value!="Now"){
                   if((new Date(begins[i-1].value)).getTime() > (new Date(begins[i].value)).getTime()){
                       var k= begins[i].value;
                       begins[i].value= begins[i-1].value;
                       begins[i-1].value= k;
                   }else if((new Date(begins[i-1].value)).getTime() == (new Date(begins[i].value)).getTime()){
                       $(begins[i]).parent().parent().remove();
                   }
                }
                }
            }
}
</script>
<form style="margin: 20px;" id="formulaire">
    <div id='-priceTierPeriods'>
    <table class="table table-responsive text-center">
        <thead class="text-uppercase">
            <tr>
                <th>Period</th>
                <th>Upper Bounds</th>
            </tr>  
        </thead>
        <tbody id="theBody">
            <%int cont=0;%>
            <%for(PriceTierValidityPeriodT rel: crp.getPriceTierValidityPeriods()){%>
            <tr>
                <td><input readonly="" type='text' id="-startDate_<%=rel.getId()%>" class="form-control fecha begin"  value='<%=ControlFunctions.getParseDate(rel.getValidFrom())%>'></td>
                <td>
                    <input type="text" class="form-control-sm col-sm-5" onkeypress="tagsinput(this,'upperBound_n')">
                    <%for(PriceTierRangeT range: crp.getPriceTierRanges()){%>
                    <%if(range.getPriceTierValidityPeriod()==cont){%>
                    <span class="badge badge-pill badge-primary" id="-upperBound_<%=range.getId()%>"><%=range.getUpperBound()%></span>
                    <%}}%>
                </td>
            </tr>
            <%cont++;%>
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
    function addNew(){
        $("#theBody").append("<tr><td><input readonly='' type='text' id='-startDate_n"+newDate+"' class='form-control fecha begin' onChange='mychange();'  value='"+$("#new").val()+"'></td>"+
        "<td><input type='text' class='form-control-sm col-sm-5' onkeypress='tagsinput(this,\"upperBound_n\")'></td></tr>");
        $(".begin").eq(($(".begin").length)-1).datepicker({dateFormat: 'dd M yy'});
        $("#new").datepicker("setDate",new Date());
        newDate++;
        mychange();
    }
    function tagsinput(id,tipo){
        if(event.keyCode === 13 && id.value!=""){
            $(id).parent().append("<span id='-"+tipo+"' class='badge badge-primary badge-pill'>"+id.value+"<i onclick='$(this).parent().remove();' class='ti-close'></i></span>");
            id.value="";
        }}
</script>    