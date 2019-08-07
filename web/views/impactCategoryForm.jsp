<%-- 
    Document   : impactCategoryForm
    Created on : Aug 7, 2019, 10:09:04 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ImpactCategoryT"%>
<% ImpactCategoryT impactCategory = (ImpactCategoryT) request.getSession().getAttribute("principal");%>
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
<label for="-name">name</label>
<input readonly class="form-control" type="text" id="-name" placeholder="name" value="<%=impactCategory.getName()%>"/>
</div>
<div class="form-group row">
<label for="-description">Description</label>
<input class="form-control" type="text" id="-description" placeholder="Description" value="<%=impactCategory.getDescription()%>"/>
</div>
<div class="form-group row">
<label for="-internalId">Internal Id</label>
<input readonly class="form-control" type="text" id="-internalId" placeholder="Internal Id" value="<%=impactCategory.getInternalId()%>"/>
</div>
<div class="form-group row">
<label for="-priceListName">PriceList Name</label>
<input class="form-control" type="text" id="-priceListName" placeholder="PriceList Name" value="<%=impactCategory.getPriceListName()%>"/>
</div>
<div class="form-group row">
<label>Obsolete<select class="form-control" id="-obsolete">
        <option <%=(impactCategory.isObsolete())?"Selected":""%>>true</option>
        <option <%=(!impactCategory.isObsolete())?"Selected":""%>>false</option>
</select></label>
</div>
<div class="form-group row">
<label for="-result">Result</label>
<input readonly class="form-control" type="text" id="-result" placeholder="Result" value="<%=impactCategory.getResult()%>"/>
</div>
<div class="form-group row">
<label for="-resultType">PriceList Name</label>
<input class="form-control" type="text" id="-resultType" placeholder="ResultType" value="<%=impactCategory.getResultType()%>"/>
</div>
</form>
