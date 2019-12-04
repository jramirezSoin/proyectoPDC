<%-- 
    Document   : errorMessage
    Created on : Nov 19, 2019, 9:25:34 AM
    Author     : Joseph Ramírez
--%>

<% String log = (String) request.getSession().getAttribute("log");%>
<p><%=log.replaceAll("\n","<br>").replaceAll("Validation error ([0-9]+) is:","<strong>Validation error $1 is:</strong>")%></p>
