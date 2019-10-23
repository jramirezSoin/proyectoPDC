<%-- 
    Document   : userPanel
    Created on : Oct 18, 2019, 9:42:55 AM
    Author     : Joseph Ramírez
--%>

<div class="col-sm-6 clearfix">
    <div class="user-profile pull-right">
        <!--img class="avatar user-thumb" src="assets/images/author/avatar.png" alt="avatar"-->
        <h4 class="user-name dropdown-toggle" data-toggle="dropdown"><%=ControlPath.pinUser%><i class="fa fa-angle-down"></i></h4>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Log Out</a>
            <%if(request.getSession().getAttribute("principal")!=null){%>
            <a class="dropdown-item" href="/downloadPdf">Download PDF</a>    
            <%}%>
        </div>
    </div>
</div>
