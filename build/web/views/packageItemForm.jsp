<%-- 
    Document   : packageItemForm
    Created on : Aug 26, 2019, 9:49:11 AM
    Author     : Joseph Ramírez
--%>

<%@page import="datos.User"%>
<%@page import="control.ControlFunctions"%>
<%@page import="control.ControlPath"%>
<%@page import="datos.ListaT"%>
<%@page import="datos.PackageT"%>
<%@page import="datos.PackageItemT"%>
<%@page import="java.util.ArrayList"%>
<%String user= ((User)request.getSession().getAttribute("user")).getUserPDC();%>
<%int index= ((ArrayList<Integer>)request.getSession().getAttribute("index")).get(1);%>
<% PackageItemT packageItem = (PackageItemT) request.getSession().getAttribute("add");%>
<%if(packageItem==null){packageItem = ((PackageT)request.getSession().getAttribute("principal")).getPackageItems().get(index);%>
    
<form style="margin: 20px;" id="formulaire">
<div class="form-group row">
    <%ArrayList<ListaT> filtro= new ArrayList<>(); 
    if(packageItem.getSpecName().equals("ProductPackage"))filtro.add(new ListaT("productSpecName",packageItem.getProductSpecName()));
    else filtro.add(new ListaT("customerSpecName","Account"));%>    
<% ArrayList<ListaT> bundles = ControlFunctions.getListaFiltro((String)ControlPath.bundledClick,user, filtro);%>
    <label>Bundles<select class="custom-select" id="bundles">
    <%for(int j=0;j<bundles.size();j++){%>
    <option value="<%=bundles.get(j).valor%>"><%=bundles.get(j).valor%></option>
    <%}%>
    </select></label>
</div>
<div class="form-group row">    
    <input type="button" class="btn btn-sm btn-primary" onclick="addBundleTr();" value="Add"> 
</div>
<table class="table text-center"> 
    <thead>
        <th>Product</th>
        <th>Bundle</th>
        <th>Delete</th>
    </thead>
    <tbody id="bodytable">
<%for(ListaT item: packageItem.getBundleProductOffering()){%>
    <tr id="-bundledProductOfferingAssociation">
        <td id="-bundledProductOfferingName"><%=item.valor%></td>
        <td><select class="custom-select" id="-tipo">
                <option <%=((item.id==3)?"selected":"")%> value="Optional">Optional</option>
                <option <%=((item.id==1)?"selected":"")%> value="Cancel with Service" >Cancel with Service</option>
                <option <%=((item.id==2)?"selected":"")%> value="Cancel without Service">Cancel without Service</option>
            </select>
        </td>
        <td><i onclick="deleteBundleTr(this);" class="ti-close"></i></td>
    </tr>
    <script>
        $("#bundles option[value='<%=item.valor%>']").remove();
    </script> 
<%}%>
    </tbody>
</table>   
</form>
    <table style="display: none;">
    <tbody id="plantilla">
        <tr id="-bundledProductOfferingAssociation">
            <td id="-bundledProductOfferingName"></td>
            <td><select class="custom-select" id="-tipo">
                    <option value="Optional">Optional</option>
                    <option value="Cancel with Service" >Cancel with Service</option>
                    <option value="Cancel without Service">Cancel without Service</option>
                </select>
            </td>
            <td><i onclick=" deleteBundleTr(this);" class="ti-close"></i></td>
        </tr>
    </div>
    </tbody>    
    <script>
        function deleteBundleTr(id){
            var i= $(id).parent().parent().children("#-bundledProductOfferingName").text();
            $("#bundles").append("<option value='" + i + "'>" + i + "</option>");
            $(id).parent().parent().remove();
        }
        function addBundleTr(){
            $("#plantilla").children("#-bundledProductOfferingAssociation").children("#-bundledProductOfferingName").html($("#bundles").val());
            $("#plantilla").children("#-bundledProductOfferingAssociation").clone().appendTo("#bodytable");
            $("#bundles option:selected").remove();
        }
    </script> 
<%}else{ ArrayList<PackageItemT> packs = ((PackageT)request.getSession().getAttribute("principal")).getPackageItems();%>
<form style="margin: 20px;" id="formulaire">
<% ArrayList<ListaT> products = ControlFunctions.getLista((String)ControlPath.attributeSpecMapClick,user);%>
    <div class="form-group row">
    <label>Aplicable to<select class="custom-select" id="-name">
            <%for(int j=0;j<products.size();j++){%>
            <%products.get(j).valor= products.get(j).valor.replaceAll("_ASM","");%>
            <option value="<%=products.get(j).valor%>"><%=products.get(j).valor%></option>
            <%}%>
    </select></label>
    </div>
    <script>
        <%for(PackageItemT item : packs){%>
            $("#-name option[value='<%=item.getProductSpecName()%>']").remove();
            <%if(item.getSpecName().equals("CustomerPackage")){%>
            $("#-name option[value='Account']").remove();
            <%}%>
        <%}%>
    </script>
</form>    
<%}%>