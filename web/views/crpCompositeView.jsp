<%-- 
    Document   : crpCompositeView
    Created on : Aug 30, 2019, 11:14:17 AM
    Author     : Joseph Ramírez
--%>
<%@page import="datos.ratePlan.PriceTierValidityPeriodT"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.ratePlan.ChargeT"%>
<%@page import="datos.ratePlan.PriceTierRangeT"%>
<%@page import="datos.ratePlan.CrpCompositePopModelT"%>
<%@page import="control.ControlPath"%>
<%CrpCompositePopModelT rel = (CrpCompositePopModelT) request.getSession().getAttribute("composite"); %>
<div class="card mt-5">
    <div class="card-body">
        <div class="d-flex justify-content-between mb-5">
            <h4 class="header-title mb-0">                         
                <%=rel.getName().replaceAll("_"," ")%>
            </h4>
                <i class="ti-pencil" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/chargeRate','Crp Composite Pop Model','0,-3');"></i>
                <i class="ti-close" onclick="$(this).parent().parent().parent().remove();"></i>
        </div>
        <div>
            <dl class="row">
                <dt class="col-sm-3">Distribution Method</dt><dd class="col-sm-9"><%= rel.getDistributionMethod()%></dd>
                <dt class="col-sm-3">Rum Tier</dt><dd class="col-sm-9"><%= rel.getRumTierExpression()%></dd>
                <dt class="col-sm-3">Enforce Credit limit</dt><dd class="col-sm-9"><%= rel.getEnforceCreditLimit()%></dd>
                <dt class="col-sm-3">Rum Name</dt><dd class="col-sm-9"><%= rel.getRumName()%></dd>
                <dt class="col-sm-3">Lower Bound</dt><dd class="col-sm-9"><%= rel.getLowerBound()%></dd>
                <dt class="col-sm-3">Currency Code</dt><dd class="col-sm-9"><%= rel.getCurrencyCode()%></dd>
                <dt class="col-sm-3">Balance Element Name</dt><dd class="col-sm-9"><%= rel.getBalanceElementName()%></dd>
            </dl>
                <%if(rel.getPopModelType().equals("usageChargePopModel")){%>
                    <div class="card mt-5">
                        <div class="card-body sbg1 text-white">
                            <div class="d-flex justify-content-between mb-3">
                                <h4 class="header-title mb-0 text-white">Periods</h4>
                                <select class="custom-select col-sm-6" onclick="getPeriods();" id="priceTierValidityPeriods">
                                    <%for(int k=0; k<rel.getPriceTierValidityPeriods().size();k++){%>
                                    <option value="<%=k%>"><%=ControlFunctions.getParseDate(rel.getPriceTierValidityPeriods().get(k).getValidFrom())%></option>
                                    <%}%>
                                </select>
                                <div class="btn-group mb-xl-3" role="group" aria-label="Basic example"> 
                                    <i class="ti-pencil" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/chargeRate','Periods','0,-4');"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                <%}%>
            <div id="daccordion1" class="according">
            <%for(PriceTierRangeT result: rel.getPriceTierRanges()){%>
                <div class="card period period-<%=result.getPriceTierValidityPeriod()%>">
                    <div class="card-header">
                        <a class="card-link">Upper Bound: <%=result.getUpperBound()%></a>
                    </div>
                    <div id="daccordion-<%=rel.getId()%>-<%=result.getId()%>" class="collapse show" data-parent="#daccordion1">
                    <div class="card-body">
                        <div class="single-table">
                        <div class="table-responsive">
                        <table class="table text-center">
                            <thead class="text-uppercase">
                                <tr>
                                <th>Charge</th>
                                <th>Price</th>
                                <th>Measure</th>
                                <th>Balance</th>
                                <th>GL/Id</th>
                                <th></th>
                                <th></th>
                                </tr>
                            </thead>    
                            <tbody>
                            <%for(ChargeT res :result.getCharges()){%>
                                <tr>
                                <td><%=res.getPriceType()%></td>
                                <td><%=res.getPrice()%></td>
                                <td><%=res.getUnitOfMeasure()%></td>
                                <td><%=res.getBalanceElementName()%></td>
                                <td><%=res.getGlidName()%></td>
                                <td><button tabindex="0" type="button" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/chargeRate','Periods','0,-5,<%=result.getId()%>,<%=res.getId()%>');" class="btn btn-rounded btn-light btn-sm"><i class="ti-pencil"></i></button></td>
                                <%if(res.getPriceValidity()!=null){%>
                                <td><button tabindex="0" type="button" class="btn btn-rounded btn-light btn-sm" data-popover-content="#res-<%=res.getId()%>" data-toggle="popover" title data-placement="right" aria-describedby="popover300430"><i class="ti-eye"></i></button></td>
                                <td id="res-<%=res.getId()%>" style="display: none;">
                                    <div class="popover-body">
                                    <table class="table table-responsive text-center">
                                        <thead>
                                            <tr>
                                                <th>Start Mode</th>
                                                <th>End Mode</th>
                                                <th>Range</th>
                                                <th>Start Offset</th>
                                                <th>End Offset</th>
                                                <th>End Offset Unit</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><%=res.getPriceValidity().getStartValidityMode()%></td>
                                                <td><%=res.getPriceValidity().getEndValidityMode()%></td>
                                                <td><%=ControlFunctions.getParseDate(res.getPriceValidity().getValidityRange().split("/")[0])%>/<%=ControlFunctions.getParseDate(res.getPriceValidity().getValidityRange().split("/")[1])%></td>
                                                <td><%=res.getPriceValidity().getRelativeStartOffset()%></td>
                                                <td><%=res.getPriceValidity().getRelativeEndOffset()%></td>
                                                <td><%=res.getPriceValidity().getRelativeEndOffsetUnit()%></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>        
                                </td>
                                <%}%>    
                                </tr>
                            <%}%>
                            </tbody>
                        </table>
                        <script>
                            $("[data-toggle=popover]").popover({
                                html : true,
                                content: function() {
                                    var content = $(this).attr("data-popover-content");
                                    return $(content).children(".popover-body").html();
                                }
                            });
                        </script>    
                        </div>
                        </div>    
                    </div>
                    </div>
                </div>
            <%}%>
            <%if(rel.getPopModelType().equals("usageChargePopModel")){%>
                <script>
                function getPeriods(){
                    $(".period").hide();
                    $(".period-"+$("#priceTierValidityPeriods").val()).show();
                }
                getPeriods();
                </script>
            <%}%>
            </div>
        </div>
    </div>
</div>
