<%-- 
    Document   : impactCategoryView
    Created on : Aug 7, 2019, 10:08:47 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.ImpactCategoryT"%>
<%@page import="datos.ImpactCategoryT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ImpactCategoryT impactCategory = (ImpactCategoryT) request.getSession().getAttribute("principal");%>
<h1>Impact Category <%= impactCategory.getName()%></h1>
<a data-toggle="modal" data-target="#exampleModal" onclick="modificar('/impactCategories','Impact Category',-1);">edit</a>
<a data-toggle="modal" data-target="#eliminarModal">delete</a>
<p>Description: <%= impactCategory.getDescription()%></p>
<p>Internal Id: <%= impactCategory.getInternalId()%></p>
<p>PriceList Name: <%= impactCategory.getPriceListName()%></p>
<p>Obsolete: <%= impactCategory.isObsolete()%></p>
<p>Result: <%= impactCategory.getResult()%></p>
<p>Result Type: <%= impactCategory.getResultType()%></p>
