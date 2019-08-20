<%-- 
    Document   : impactCategoryView
    Created on : Aug 5, 2019, 4:21:25 PM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.ImpactCategoryT"%>
<%@page import="datos.ImpactCategoryT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ImpactCategoryT impactCategory = (ImpactCategoryT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= impactCategory.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/impactCategories', 'Impact Category')">Impact Category</a></li>
                    <li><span><%= impactCategory.getName()%></span></li>
                </ul>
            </div>
        </div>
    </div>
</div>
            <!-- page title area end -->
<div class="main-content-inner">
        <div class="row">
            <div class="col-xs-12 col-md-8">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Impact Category Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/impactCategories','Impact Category',-1);">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/impactCategories','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                  <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= impactCategory.getDescription()%></dd>
                                  <dt class="col-sm-3">Internal Id</dt><dd class="col-sm-9"><%= impactCategory.getInternalId()%></dd>
                                  <dt class="col-sm-3">PriceList Name</dt><dd class="col-sm-9"><%= impactCategory.getPriceListName()%></dd>
                                  <dt class="col-sm-3">Obsolete</dt><dd class="col-sm-9"><%= impactCategory.isObsolete()%></dd>
                                  <dt class="col-sm-3">Result</dt><dd class="col-sm-9"><%= impactCategory.getResult()%></dd>
                                  <dt class="col-sm-3">Result Type</dt><dd class="col-sm-9"><%= impactCategory.getResultType()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>    
        </div>                                                               
</div>
