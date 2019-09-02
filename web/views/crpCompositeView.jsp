<%-- 
    Document   : crpCompositeView
    Created on : Aug 30, 2019, 11:14:17 AM
    Author     : Joseph Ram�rez
--%>
<%@page import="datos.ratePlan.ChargeT"%>
<%@page import="datos.ratePlan.PriceTierRangeT"%>
<%@page import="datos.ratePlan.CrpCompositePopModelT"%>
<%CrpCompositePopModelT rel = (CrpCompositePopModelT) request.getSession().getAttribute("composite"); %>
<div class="card mt-5">
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-5">
                            <h4 class="header-title mb-0">                         
                                <%=rel.getName().replaceAll("_"," ")%>
                            </h4>
                            <i class="ti-close" onclick="$(this).parent().parent().parent().remove();"></i>
                        </div>
                        <div>
                            <dl class="row">
                                <dt class="col-sm-3">Pop Model</dt><dd class="col-sm-9"><%= rel.getPopModelType()%></dd>
                                <dt class="col-sm-3">Distribution Method</dt><dd class="col-sm-9"><%= rel.getDistributionMethod()%></dd>
                                <dt class="col-sm-3">Rum Tier</dt><dd class="col-sm-9"><%= rel.getRumTierExpression()%></dd>
                                <dt class="col-sm-3">Enforce Credit limit</dt><dd class="col-sm-9"><%= rel.getEnforceCreditLimit()%></dd>
                                <dt class="col-sm-3">Rum Name</dt><dd class="col-sm-9"><%= rel.getRumName()%></dd>
                                <dt class="col-sm-3">Lower Bound</dt><dd class="col-sm-9"><%= rel.getLowerBound()%></dd>
                                <dt class="col-sm-3">Currency Code</dt><dd class="col-sm-9"><%= rel.getCurrencyCode()%></dd>
                                <dt class="col-sm-3">Balance Element Name</dt><dd class="col-sm-9"><%= rel.getBalanceElementName()%></dd>
                            </dl>
                            <div id="daccordion1" class="according accordion-s2">
                            <%for(PriceTierRangeT result: rel.getPriceTierRanges()){%>
                                <div class="card">
                                    <div class="card-header">
                                        <a class="card-link" data-toggle="collapse" href="#daccordion-<%=rel.getId()%>-<%=result.getId()%>"><%=result.getUpperBound()%>
                                        </a>                                        
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
                                                <th>Balance</th>
                                                <th>GL/Id</th>
                                                </tr>
                                            </thead>    
                                            <tbody>
                                            <%for(ChargeT res :result.getCharges()){%>
                                                <tr>
                                                <td><%=res.getPriceType()%></td>
                                                <td><%=res.getPrice()%></td>
                                                <td><%=res.getBalanceElementName()%></td>
                                                <td><%=res.getGlidName()%></td>
                                                </tr>
                                            <%}%>
                                            </tbody>
                                        </table>
                                        </div>
                                        </div>    
                                    </div>
                                    </div>
                                </div>
                            <%}%>
                            </div>
                        </div>
                    </div>
                </div>
