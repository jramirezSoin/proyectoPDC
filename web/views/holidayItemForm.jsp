<%-- 
    Document   : holidayItemForm
    Created on : Aug 21, 2019, 10:43:07 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.HolidayT"%>
<%@page import="datos.HolidayItemT"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form style="margin: 20px;" id="formulaire">
<script>
$(function () {
$.datepicker.setDefaults($.datepicker.regional["en"]);
$(".fecha").datepicker({
dateFormat: 'd/M/yy',
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

<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(0);%>
<% HolidayItemT item = (HolidayItemT) request.getSession().getAttribute("add");%>
<%if(item==null){item = ((HolidayT)request.getSession().getAttribute("principal")).getHolidayItems().get(index);}%>
    <div class="form-group row">
        <label for="-name">name</label>
        <input class="form-control" type="text" id="-name" placeholder="name" value="<%=item.getName()%>"/>
    </div>
    <div class="custom-control custom-checkbox">
        <input <%=((item.getYear().equals(""))?"":"checked")%> type="checkbox" class="custom-control-input" id="yearcheck" onclick="if($(this).prop('checked')==false) $('#-year').val('');">
        <label class="custom-control-label" for="yearcheck">Has year?</label>
    </div>
    <div class="form-group row">
          <label for="-day">Day</label>
          <input class="form-control fecha" type="text" id="-day" placeholder="Day" value="<%=item.getDay()%>"/>
    </div>
    <div class="form-group row">
          <label for="-month">Month</label>
          <input class="form-control fecha" type="text" id="-month" placeholder="Month" value="<%=item.getMonth()%>"/>
    </div>
    <div class="form-group row">
          <label for="-year">Year</label>
          <input class="form-control fecha" type="text" id="-year" placeholder="Year" value="<%=item.getYear()%>"/>
    </div> 
</form>
