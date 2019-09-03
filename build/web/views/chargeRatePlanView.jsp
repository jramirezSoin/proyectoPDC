<%-- 
    Document   : chargeRatePlanView
    Created on : Aug 29, 2019, 10:12:54 AM
    Author     : Joseph Ramírez
--%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ratePlan.ChargeT"%>
<%@page import="datos.ratePlan.PriceTierRangeT"%>
<%@page import="datos.ratePlan.CrpCompositePopModelT"%>
<%@page import="datos.ratePlan.TagsT"%>
<%@page import="datos.ratePlan.TimeConfigurationT"%>
<%@page import="datos.ratePlan.GenericSelectorT"%>
<%@page import="datos.ratePlan.ResultsT"%>
<%@page import="datos.ratePlan.CrpRelDateRangeT"%>
<%@page import="datos.ratePlan.ChargeRatePlanT"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ChargeRatePlanT chargeRate = (ChargeRatePlanT) request.getSession().getAttribute("principal");%>
<div class="page-title-area">
    <div class="row align-items-center">
        <div class="col-sm-6">
            <div class="breadcrumbs-area clearfix">
                <h4 class="page-title pull-left" id="bread1"><%= chargeRate.getName()%></h4>
                <ul class="breadcrumbs pull-left">
                    <li><a href="/">Home</a></li>
                    <li><a href="#" onclick="hacerlist('/chargeRate', 'Charge Rate Plan')">Charge Rate Plan</a></li>
                    <li><span><%= chargeRate.getName()%></span></li>
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
                            <h4 class="header-title mb-0">Charge Rate Plan Information</h4>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/chargeRate','Charge Rate Plan',-1);">Edit</button>
                                <button type="button" class="btn btn-xs btn-primary bg1" data-toggle="modal" data-target="#eliminarModal" onclick="eliminar('/chargeRate','-4')">Delete</button>
                            </div>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-3">Description</dt><dd class="col-sm-9"><%= chargeRate.getDescription()%></dd>
                                <dt class="col-sm-3">Pricing Profile Name</dt><dd class="col-sm-9"><%= chargeRate.getPricingProfileName()%></dd>
                                <dt class="col-sm-3">Permitted Name</dt><dd class="col-sm-9"><%= chargeRate.getPermittedName()%></dd>
                                <dt class="col-sm-3">Permitted Type</dt><dd class="col-sm-9"><%= chargeRate.getPermittedType()%></dd>
                                <dt class="col-sm-3">Event Name</dt><dd class="col-sm-9"><%= chargeRate.getEventName()%></dd>
                                <dt class="col-sm-3">Currency</dt><dd class="col-sm-9"><%= chargeRate.getSubscriberCurrency().getCurrencyName()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <div class="card mt-5">
                    <div class="card-body sbg1 text-white">
                        <div class="d-flex justify-content-between mb-3">
                            <h4 class="header-title mb-0 text-white">Date Range</h4>
                            <select class="custom-select col-sm-6" id="crpRelDate" onselect="crpRel();">
                            </select>
                            <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                <i class="ti-pencil" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/chargeRate','Charge Rate Plan','0,-1');"></i>
                            </div>
                        </div>
                    </div>
                </div>            
                <%for(CrpRelDateRangeT rel: chargeRate.getSubscriberCurrency().getCrpRelDateRanges()){%>
                <script>
                $("#crpRelDate").append("<option selected value='<%=rel.getId()%>'><%=ControlFunctions.getParseDate(rel.getStartDate())%>-<%=ControlFunctions.getParseDate(rel.getEndDate())%></option>");
                </script>    
                <div class="crpRelDates" id="select-<%=rel.getId()%>">
                <%if(rel.getZoneModel()!=null){%>
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">
                                
                                <%=rel.getZoneModel().getZoneModelName().replaceAll("_"," ")%>
                                <%if(rel.getZoneModel().isEnhanced()){%>
                                    <span class="badge badge-pill badge-primary">Enhanced</span>
                                <%}%>
                            </h4>
                            <i class="ti-close" onclick="$(this).parent().parent().parent().remove();"></i>
                        </div>
                        <div>
                            <%if(rel.getZoneModel().isEnhanced()){%>
                            <dl class="row">
                                <dt class="col-sm-3">Usc Model</dt><dd class="col-sm-9"><%= rel.getZoneModel().getUscModelName()%></dd>
                            </dl>
                            <%}%>
                            <div id="accordion1" class="according accordion-s2">
                            <%for(ResultsT result: rel.getZoneModel().getResults()){%>
                                <div class="card">
                                    <div class="card-header">
                                        <a class="card-link collapsed" data-toggle="collapse" href="#accordion-<%=rel.getId()%>-<%=result.getId()%>">
                                        <%for(String name: result.getName()){%>
                                        <span class="badge badge-pill badge-secondary"><%=name%></span>
                                        <%}%>
                                        <span class="badge badge-pill badge-<%=((result.getResult() instanceof TimeConfigurationT)?"primary":((result.getResult() instanceof GenericSelectorT)?"danger":"success"))%>"><%=((result.getResult() instanceof TimeConfigurationT)?"Time Model":((result.getResult() instanceof GenericSelectorT)?"Generic Selector":"Pop Model"))%></span>
                                        </a>
                                    </div>
                                    <div id="accordion-<%=rel.getId()%>-<%=result.getId()%>" class="collapse" data-parent="#accordion1">
                                        <div class="card-body">
                                        <%if(result.getResult() instanceof GenericSelectorT){%>
                                         <%GenericSelectorT selector = (GenericSelectorT)result.getResult();%>
                                            <p><%= selector.getGenericSelectorName()%></p>
                                            <div class="list-group">
                                              <%for(ResultsT res :selector.getResults()){%>
                                              <a href="#Principal-<%=rel.getId()%>" class="list-group-item list-group-item-action" onclick="composite('/chargeRate','Principal-<%=rel.getId()%>','<%=rel.getId()%>,<%=result.getId()%>,<%=res.getId()%>')"><%=res.getName().get(0)%></a>
                                              <%}%>
                                             </div>    
                                        <%}else if(result.getResult() instanceof TimeConfigurationT){%>
                                         <%TimeConfigurationT time = (TimeConfigurationT)result.getResult();%>
                                             <p><%= time.getTimeModelName()%></p>
                                            <div class="list-group">
                                            <%for(TagsT tag: time.getTags()){%>
                                                 <a href="#Principal-<%=rel.getId()%>" class="list-group-item list-group-item-action"  onclick="composite('/chargeRate','Principal-<%=rel.getId()%>','<%=rel.getId()%>,<%=result.getId()%>,<%=tag.getId()%>')"><%=tag.getName()%></a>
                                             <%}%>
                                            </div>    
                            
                                        <%}else{%>
                                       <%CrpCompositePopModelT time = (CrpCompositePopModelT)result.getResult();%>
                                          <div class="list-group">
                                             <a href="#Principal-<%=rel.getId()%>" class="list-group-item list-group-item-action" onclick="composite('/chargeRate','Principal-<%=rel.getId()%>','<%=rel.getId()%>,<%=result.getId()%>')"><%= time.getName()%></a>
                                          </div>
                                       <%}%>
                                        </div>
                                    </div>
                                </div>
                            <%}%>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="Principal-<%=rel.getId()%>">
                </div>
                <%}else{%>
                <div class="card mt-5">
                    <div class="card-body">
                        <div>
                            <div id="accordion1" class="according accordion-s2">
                                <div class="card">
                                    <div class="card-header">
                                        <a  class="card-link collapsed" data-toggle="collapse" onclick="composite('/chargeRate','Principal--<%=rel.getId()%>','<%=rel.getId()%>')" href="#Principal--<%=rel.getId()%>"><%=rel.getCrpCompositePopModel().getName()%>
                                            <span class="badge badge-pill badge-success">Pop</span>
                                        </a>    
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="Principal--<%=rel.getId()%>">
                </div>
                <%}%>
                </div>
                <%}%>
                <script>
                 function crpRel(){
                    $(".crpRelDates").hide();
                    $("#select-"+$("#crpRelDate").val()).show();}
                    crpRel();
                </script>    
            </div> 
            <div class="col-xs-12 col-md-4">

                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Additional Information</h4>
                            <i class="ti-close" onclick="$(this).parent().parent().parent().remove();"></i>
                        </div>
                        <div>
                            <dl class="row">
                                <%if(!chargeRate.getTaxTime().equals("NONE")){%>
                                <dt class="col-sm-6">Tax Code</dt><dd class="col-sm-6"><%= chargeRate.getTaxCode()%></dd>
                                <%}%>
                                <dt class="col-sm-6">Applicable Rums</dt><dd class="col-sm-6"><%= chargeRate.getApplicableRums()%></dd>
                                <dt class="col-sm-6">Applicable Quantity</dt><dd class="col-sm-6"><%= chargeRate.getApplicableQuantity()%></dd>
                                <dt class="col-sm-6">Tax Time</dt><dd class="col-sm-6"><%= chargeRate.getTaxTime()%></dd>
                                <dt class="col-sm-6">Tod Mode</dt><dd class="col-sm-6"><%= chargeRate.getTodMode()%></dd>
                                <!--dt class="col-sm-6">Applicable Qty Treatment</dt><dd class="col-sm-6"><%= chargeRate.getApplicableQtyTreatment()%></dd-->     
                                <!--dt class="col-sm-6">Cycle Fee Flag</dt><dd class="col-sm-6"><%= chargeRate.getCycleFeeFlag()%></dd-->
                                <!--dt class="col-sm-6">Bill Offset</dt><dd class="col-sm-6"><%= chargeRate.getBillOffset()%></dd-->
                            </dl>
                        </div>
                    </div>
                </div>
                <%if(chargeRate.getSubscriberCurrency().getApplicableRum()!=null){%>            
                <div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">Applicable Rum</h4>
                            <i class="ti-close" onclick="$(this).parent().parent().parent().remove();"></i>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-6">applicable Rum Name</dt><dd class="col-sm-6"><%= chargeRate.getSubscriberCurrency().getApplicableRum().getApplicableRumName()%></dd>
                                <dt class="col-sm-6">min Quantity</dt><dd class="col-sm-6"><%= chargeRate.getSubscriberCurrency().getApplicableRum().getMinQuantity()%></dd>
                                <!--dt class="col-sm-6">min Quantity Unit</dt><dd class="col-sm-6"><%= chargeRate.getSubscriberCurrency().getApplicableRum().getMinQuantityUnit()%></dd-->
                                <dt class="col-sm-6">increment Quantity</dt><dd class="col-sm-6"><%= chargeRate.getSubscriberCurrency().getApplicableRum().getIncrementQuantity()%></dd>
                                <!--dt class="col-sm-6">increment Quantity Unit</dt><dd class="col-sm-6"><%= chargeRate.getSubscriberCurrency().getApplicableRum().getIncrementQuantityUnit()%></dd-->
                                <dt class="col-sm-6">rounding Mode</dt><dd class="col-sm-6"><%= chargeRate.getSubscriberCurrency().getApplicableRum().getRoundingMode()%></dd>
                            </dl>
                        </div>
                    </div>
                </div>
                <%}%> 
            </div>                                        
        </div>
                            
</div>
