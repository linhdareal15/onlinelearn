<%-- 
    Document   : settingsList
    Created on : Jan 15, 2022, 9:58:00 AM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Right side column. Contains the navbar and content of the page -->
<script src="myJS/myjavascript.js" type="text/javascript"></script>
<script>
    function doEdit(id) {
        window.location.href = "edit?sid=" + id;
    }

</script>
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel">
                <header class="panel-heading">
                    <div class="panel-heading" style="display: flex;">
                        <div style="margin-right: 74%;"> Settings  List</div>
                        <div>
                            <a href="add">Add new setting </a>
                        </div>
                    </div>
                </header>
                <!-- <div class="box-header"> -->
                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                <!-- </div> -->
                <div class="panel-body table-responsive">
                    <div class="box-tools m-b-15" style="display: flex;">
                        <form action="settingslist" method="POST">
                            Type <select name="type" class="input-sm">
                                <option value="-1">ALL</option>
                                <c:forEach items="${requestScope.settingTypesList}" var="i">
                                    <option ${requestScope.type_id eq i.setting_type_id ? "selected=\"selected\"" : ""} value="${i.setting_type_id}">${i.setting_type_name}</option>
                                </c:forEach>
                            </select>
                            Status <select name="status" class="input-sm">
                                <option value="-1">ALL</option>
                                <c:forEach items="${requestScope.settingStatusList}" var="i">
                                    <option ${requestScope.status_id eq i.setting_status_id ? "selected=\"selected\"" : ""} value="${i.setting_status_id}">${i.setting_status_name}</option>
                                </c:forEach>
                            </select>
                            <input style="margin-left: 710px" type="text" name="setting_value" value="${requestScope.setting_value}"placeholder="Enter title.."/>
                            <input type="submit" value="Search"/>
                        </form>
                    </div>
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>Settings Name</th>
                            <th>Description</th>
                            <th>Type</th>
                            <th>Value</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${requestScope.listBySearch}" var="i">
                            <tr>
                                <td onclick="doEdit(${i.settingslist_id})">${i.settingslist_id}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.settingsName}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.description}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.type.setting_type_name}</td>
                                <td onclick="doEdit(${i.settingslist_id})">${i.value}</td>
                                <td><button onclick="doChangeStatus(${i.settingslist_id}, '${i.status.setting_status_id}')" type="button" class="label 
                                            <c:if test="${i.status.setting_status_id eq '1'}">
                                                label-danger
                                            </c:if>
                                            <c:if test="${i.status.setting_status_id eq '2'}">
                                                label-success 
                                            </c:if>
                                            ">
                                        <c:if test="${i.status.setting_status_id eq '1'}">
                                            Active
                                        </c:if>
                                        <c:if test="${i.status.setting_status_id eq '2'}">
                                            Deactive 
                                        </c:if>
                                    </button></td>
                            </tr>
                        </c:forEach>                                
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>
    <div class="text-center">
        <ul class="pagination">
            <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                <li class="page-item"><a class="page-link" href="?index=0&&status=${requestScope.status_id}&&type=${requestScope.type_id}">First</a></li>
                </c:if>
                <c:if test="${requestScope.current_index != 0}">
                <li class="page-item">
                    <a class="page-link" href="?index=${requestScope.current_index - 1}&&status=${requestScope.status_id}&&type=${requestScope.type_id}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                    </a>
                </li> 
            </c:if>

            <c:forEach begin="${requestScope.current_index + 1}" end="${requestScope.current_index + 4}"  var="i">   
                <c:if test="${i < requestScope.endPage}">

                    <li class="${requestScope.current_index == i - 1 ? "page-item active":"page-item"}"><a class="page-link" href="?index=${i-1}&&status=${requestScope.status_id}&&type=${requestScope.type_id}&&setting_value=${requestScope.setting_value}">${i}</a></li>                                             
                    </c:if>             
                </c:forEach>
                <c:if test="${requestScope.current_index != requestScope.endPage - 1}">
                <li class="page-item">
                    <a class="page-link" href="?index=${requestScope.current_index + 1}&&status=${requestScope.status_id}&&type=${requestScope.type_id}&&setting_value=${requestScope.setting_value}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                        <span class="sr-only">Next</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                <li class="page-item"><a class="page-link" href="?index=${requestScope.endPage - 1}&&status=${requestScope.status_id}&&type=${requestScope.type_id}&&setting_value=${requestScope.setting_value}">Last</a></li>
                </c:if>
        </ul>
    </div>
</section><!-- /.content -->



