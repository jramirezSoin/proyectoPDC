<%-- 
    Document   : arpCompositeView
    Created on : Oct 17, 2019, 8:19:54 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.alteration.AlterationConfigurationT"%>
<%@page import="control.ControlFunctions"%>
<%@page import="datos.alteration.AlterationChargeT"%>
<%@page import="datos.alteration.TierRangeT"%>
<%@page import="datos.alteration.ArpCompositePopModelT"%>
<%AlterationConfigurationT conf = (AlterationConfigurationT) request.getSession().getAttribute("composite"); %>
<%ArpCompositePopModelT rel = conf.getArpCompositePopModel();%>
<div class="card mt-5">
    <div class="card-body">
        <dl class="row">
                <dt class="col-sm-5">Applicable Charge And Quantity</dt><dd class="col-sm-7"><%= conf.getApplicableChargeAndQuantity()%></dd>
                <dt class="col-sm-5">Trigger Spec Name</dt><dd class="col-sm-7"><%= conf.getTriggerSpecName()%></dd>
                <dt class="col-sm-5">Charge Selector Spec Name</dt><dd class="col-sm-7"><%= conf.getChargeSelectorSpecName()%></dd>
        </dl>        
        <div>
            <dl class="row">
                <dt class="col-sm-5">Distribution Method</dt><dd class="col-sm-7"><%= rel.getDistributionMethod()%></dd>
                <dt class="col-sm-5">Lower Bound</dt><dd class="col-sm-7"><%= rel.getLowerBound().getTipo()%></dd>
                <%if(rel.getLowerBound().getTipo().equals("numberTBExpression")){%>
                    <dt class="col-sm-5">Value</dt><dd class="col-sm-7"><%= rel.getLowerBound().getLeftOperand().valor%></dd>
                <%}else if(rel.getLowerBound().getTipo().equals("balanceTBExpression")){%>
                    <dt class="col-sm-5">Balance Element Num Code</dt><dd class="col-sm-7"><%= rel.getLowerBound().getLeftOperand().valor%></dd>
                <%}else if(rel.getLowerBound().getTipo().equals("binaryTBExpression")){%>
                    <dl>
                        <dt class="col-sm-5">Left Operand</dt><dd class="col-sm-9"><%= rel.getLowerBound().getLeftOperand().unit%></dd>
                            <dt class="col-sm-5">Balance Element Num Code</dt><dd class="col-sm-7"><%= rel.getLowerBound().getLeftOperand().valor%></dd>
                        <dt class="col-sm-5">Right Operand</dt><dd class="col-sm-9"><%= rel.getLowerBound().getRightOperand().unit%></dd>
                            <dt class="col-sm-5">Balance Element Num Code</dt><dd class="col-sm-7"><%= rel.getLowerBound().getRightOperand().valor%></dd>
                    </dl>
                <%}%>
                <dt class="col-sm-5">Tier Basis</dt><dd class="col-sm-7"><%= rel.getTierBasis().unit%></dd>
                <dt class="col-sm-5">Use Tier Component</dt><dd class="col-sm-7"><%= rel.getTierBasis().valor%></dd>
            </dl>
            <div id="daccordion1" class="according">
            <%for(TierRangeT result: rel.getTierRange()){%>
                <div class="card period period-<%=result.getId()%>">
                    <div class="card-header">
                        <a class="card-link">Upper Bound</a>
                        <dt class="col-sm-7"><%= result.getUpperBound().getTipo()%></dt>
                        <%if(result.getUpperBound().getTipo().equals("numberTBExpression")){%>
                            <dt class="col-sm-5">Value</dt><dd class="col-sm-7"><%= result.getUpperBound().getLeftOperand().valor%></dd>
                        <%}else if(result.getUpperBound().getTipo().equals("balanceTBExpression")){%>
                            <dt class="col-sm-5">Balance Element Num Code</dt><dd class="col-sm-7"><%= result.getUpperBound().getLeftOperand().valor%></dd>
                        <%}else if(result.getUpperBound().getTipo().equals("binaryTBExpression")){%>
                            <dl>
                                <dt class="col-sm-5">Left Operand</dt><dd class="col-sm-9"><%= result.getUpperBound().getLeftOperand().unit%></dd>
                                    <dt class="col-sm-5">Balance Element Num Code</dt><dd class="col-sm-7"><%= result.getUpperBound().getLeftOperand().valor%></dd>
                                <dt class="col-sm-5">Right Operand</dt><dd class="col-sm-9"><%= result.getUpperBound().getRightOperand().unit%></dd>
                                    <dt class="col-sm-5">Balance Element Num Code</dt><dd class="col-sm-7"><%= result.getUpperBound().getRightOperand().valor%></dd>
                            </dl>
                        <%}%>
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
                                <th>Mesure</th>
                                <th>Balance</th>
                                <th>GL/Id</th>
                                <th></th>
                                <th></th>
                                </tr>
                            </thead>    
                            <tbody>
                            <%for(AlterationChargeT res :result.getCharges()){%>
                                <tr>
                                <td><%=res.getPriceType()%></td>
                                <td><%=res.getPrice()%></td>
                                <td><%=res.getUnitOfMeasure()%></td>
                                <td><%=res.getBalanceElementName()%></td>
                                <td><%=res.getGlidName()%></td>
                                <td><button tabindex="0" type="button" data-toggle="modal" data-target="#exampleModal" onclick="modificar('/alterationRatePlan','Periods','0,-5,<%=result.getId()%>,<%=res.getId()%>');" class="btn btn-rounded btn-light btn-sm"><i class="ti-pencil"></i></button></td>
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
            </div>
        </div>
    </div>
</div>

