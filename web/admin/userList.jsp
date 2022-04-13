<%-- 
    Document   : settingsList
    Created on : Jan 15, 2022, 9:58:00 AM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
    <style type="text/css">
    </style>
    <script>
        function doEdit(id)
        {
            window.location.href = "edit-user?id=" + id;
        }
        function submitForm(e) {
            let form = e.parentElement;
            form.submit();
        }
    </script>
    <body class="skin-black">
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="panel">
                        <header class="panel-heading">
                            <div class="panel-heading" style="display: flex;">
                                <div style="margin-right: 74%;"> User  List</div>
                                <div>
                                    <a href="add-user" style="margin-left: 90px">Add user</a>
                                </div>
                            </div>
                        </header>
                        <!-- <div class="box-header"> -->
                        <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                        <!-- </div> -->
                        <div class="panel-body table-responsive">
                            <div class="box-tools m-b-15" style="display: flex;">
                                <form action="user" method="">
                                    Role <select name="role" class="input-sm">
                                        <option value="-1">All</option>
                                        <c:forEach items="${requestScope.roles}" var="i">
                                            <option value="${i.role_id}"  ${requestScope.role eq i.role_id ? "selected=\"selected\"" : ""}>${i.role_name}</option>
                                        </c:forEach>
                                    </select>
                                    Status <select name="status" class="input-sm">
                                        <option value="-1">All</option>
                                        <c:forEach items="${requestScope.statuses}" var="i">
                                            <option value="${i.status_id}" ${requestScope.status eq i.status_id ? "selected=\"selected\"" : ""}>${i.status_name}</option>
                                        </c:forEach>
                                    </select>
                                    Sort by: <select name="sort" class="input-sm">
                                        <option value="id" ${requestScope.sort eq 'id' ? "selected=\"selected\"" : ""}>Id</option>
                                        <option value="name" ${requestScope.sort eq 'name' ? "selected=\"selected\"" : ""}>Name</option>
                                        <option value="gender" ${requestScope.sort eq 'gender' ? "selected=\"selected\"" : ""}>Gender</option>
                                        <option value="email" ${requestScope.sort eq 'email' ? "selected=\"selected\"" : ""}>Email</option>
                                        <option value="moblie" ${requestScope.sort eq 'moblie' ? "selected=\"selected\"" : ""}>Moblie</option>
                                        <option value="role" ${requestScope.sort eq 'role' ? "selected=\"selected\"" : ""}>Role</option>
                                        <option value="status" ${requestScope.sort eq 'status' ? "selected=\"selected\"" : ""}>Status</option>
                                    </select>
                                    <i class="fa fa-filter" onclick="submitForm(this)"></i>
                                </form>
                                <form action="search-user" method="">
                                    <input style="margin-left: 710px" type="text" name="keyword" value="${keyword}" placeholder="Enter name, email.."/>
                                    <input type="submit" value="Search"/>
                                </form>
                            </div>
                            <table class="table table-hover">
                                <tr>
                                    <th>ID</th>
                                    <th>Full Name</th>
                                    <th>Gender</th>
                                    <th>Email</th>
                                    <th>Moblie</th>
                                    <th>Role</th>
                                    <th>Status</th>
                                </tr>
                                <c:forEach items="${requestScope.users}" var="i">
                                    <tr>
                                        <td onclick="doEdit(${i.id})">${i.id}</td>
                                        <td onclick="doEdit(${i.id})">${i.fullname}</td>
                                        <td onclick="doEdit(${i.id})"> 
                                            <c:if test="${i.gender == true}">
                                                Male
                                            </c:if>
                                            <c:if test="${i.gender == false}">
                                                Female 
                                            </c:if>
                                        </td>
                                        <td onclick="doEdit(${i.id})">${i.email}</td>
                                        <td onclick="doEdit(${i.id})">${i.phonenumber}</td>
                                        <td onclick="doEdit(${i.id})">${i.role.role_name}</td>
                                        <td>
                                            <button type="button" class="label 
                                                    <c:if test="${i.status eq '1'}">
                                                        label-danger
                                                    </c:if>
                                                    <c:if test="${i.status eq '2'}">
                                                        label-success 
                                                    </c:if>
                                                    <c:if test="${i.status eq '3'}">
                                                        label-primary
                                                    </c:if>
                                                    ">
                                                <c:if test="${i.status eq '1'}">
                                                    Deactive
                                                </c:if>
                                                <c:if test="${i.status eq '2'}">
                                                    Active
                                                </c:if>
                                                <c:if test="${i.status eq '3'}">
                                                    Denied
                                                </c:if>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>  
                            </table>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div>
            </div>
            <div class="text-center">
                <ul class="pagination">
                    <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                        <li class="page-item"><a class="page-link" href="?index=0&&status=${requestScope.status}&&role=${requestScope.role}&&sort=${requestScope.sort}">First</a></li>
                        </c:if>
                        <c:if test="${requestScope.current_index != 0}">
                        <li class="page-item">
                            <a class="page-link" href="?index=${requestScope.current_index - 1}&&status=${requestScope.status}&&role=${requestScope.role}&&sort=${requestScope.sort}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li> 
                    </c:if>
                    <c:forEach begin="${requestScope.current_index + 1}" end="${requestScope.current_index + 4}"  var="i">   
                        <c:if test="${i < requestScope.endPage}">

                            <li class="${requestScope.current_index == i - 1 ? "page-item active":"page-item"}"><a class="page-link" href="?index=${i-1}&&status=${requestScope.status}&&role=${requestScope.role}&&sort=${requestScope.sort}">${i}</a></li>                                             
                            </c:if>             
                        </c:forEach>
                        <c:if test="${requestScope.current_index != requestScope.endPage - 1}">
                        <li class="page-item">
                            <a class="page-link" href="?index=${requestScope.current_index + 1}&&status=${requestScope.status}&&role=${requestScope.role}&&sort=${requestScope.sort}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                        <li class="page-item"><a class="page-link" href="?index=${requestScope.endPage - 1}&&status=${requestScope.status}&&role=${requestScope.role}&&sort=${requestScope.sort}">Last</a></li>
                        </c:if>
                </ul>
            </div>
        </section><!-- /.content -->


