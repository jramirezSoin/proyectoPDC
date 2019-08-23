<%-- 
    Document   : impactCategoryForm
    Created on : Aug 7, 2019, 10:09:04 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ImpactCategoryT"%>
<% ImpactCategoryT impactCategory = (ImpactCategoryT) request.getSession().getAttribute("add");%>
<% if(impactCategory==null){impactCategory = (ImpactCategoryT) request.getSession().getAttribute("principal");}%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-name">name</label>
<input <%=(!impactCategory.getName().equals(""))?"readonly":""%> class="form-control" type="text" id="-name" placeholder="name" value="<%=impactCategory.getName()%>"/>
</div>
<div class="form-group row">
<label for="-description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=impactCategory.getDescription()%>"/>
</div>
</form>
