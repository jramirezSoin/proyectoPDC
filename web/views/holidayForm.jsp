<%-- 
    Document   : holidayForm
    Created on : Aug 21, 2019, 8:51:02 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.HolidayT"%>
<% HolidayT holiday = (HolidayT) request.getSession().getAttribute("add");%>
<% if(holiday==null){holiday = (HolidayT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-name">name</label>
<input <%=(!holiday.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=holiday.getName()%>"/>
</div>
<div class="form-group row">
<label for="-description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=holiday.getDescription()%>"/>
</div>
</form>
