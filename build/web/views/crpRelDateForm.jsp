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
onSelect: function (date) {
var x= date.split('/');
$("#-day").val(x[0]);
$("#-month").val(x[1].toUpperCase());
if($("#yearcheck").prop("checked")==true)
	$("#-year").val(x[2]);
else
	$("#-year").val("");
},
});
});
</script>
<form style="margin: 20px;" id="formulaire">
    <table class="table table-responsive text-center">
        <div class="custom-control custom-checkbox">
        <input id="-begin" type="checkbox" class="custom-control-input">
        <label class="custom-control-label" for="-begin">Never Start</label>
        </div>
        <div class="custom-control custom-checkbox">
        <input id='-end' type="checkbox" class="custom-control-input">
        <label class="custom-control-label" for="-end">Never End</label>
        </div>
        <thead class="text-uppercase">
            <tr>
                <th>Start</th>
                <th>End</th>
            </tr>  
        </thead>
        <tbody>
            <%for(CrpRelDateRangeT rel: chargeRate.getSubscriberCurrency().getCrpRelDateRanges()){%>
            <tr>
                <%if(ControlFunctions.getParseDate(rel.getStartDate()).equals("Now")){%>
                    <script>$("#-begin").prop("checked",true);</script>
                <%}%>
                <%if(ControlFunctions.getParseDate(rel.getEndDate()).equals("Never ends")){%>
                    <script>$("#-end").prop("checked",true);</script>
                <%}%>
                <td><input readonly="" type='text' class="form-control fecha"  value='<%=ControlFunctions.getParseDate(rel.getStartDate())%>'></td>
                <td><input readonly="" type='text' class="form-control fecha" value='<%=ControlFunctions.getParseDate(rel.getEndDate())%>'></td>
                
            </tr>
            <%}%>            
        </tbody>
    </table>
</form>
