<%-- 
    Document   : RegistrationList
    Created on : Mar 17, 2022, 2:12:53 AM
    Author     : Louis
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function doChangeRegistrationListStatus(id, status) {
        var c = confirm("Change Status ?");
        if (c) {
            window.location.href = "RegistrationListChangeStatus?id=" + id + "&status=" + status;
        }
    }
</script>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel">
                <header class="panel-heading">
                    <div class="panel-heading" style="display: flex;">
                        <div style="margin-right: 74%;"> Registration List</div>
                        <!--                        <div>
                                                    <a href="addsubject">Add new subject </a>
                                                </div>-->
                    </div>
                </header>
                <!-- <div class="box-header"> -->
                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                <!-- </div> -->
                <div class="panel-body table-responsive">
                    <div class="box-tools m-b-15" style="display: flex;">
                        <form action="RegistrationsList" method="get">
                            Course &nbsp; <select name="cid" class="input-sm">
                                <option value="0">ALL</option>
                                <c:forEach items="${requestScope.ListCourse}" var="i">
                                    <option ${param.cid eq i.cid ? "selected=\"selected\"" : ""} value="${i.cid}">${i.title}</option>
                                </c:forEach>
                            </select>
                            &nbsp;
                            Status &nbsp;  <select name="status" class="input-sm" >
                                <option value="0" <c:if test="${param.status eq 0}">
                                        selected="selected"
                                    </c:if>>All</option>
                                <c:forEach items="${requestScope.ListStatus}" var="s">
                                    <option value="${s.getId()}" <c:if test="${param.status eq s.getId()}">
                                            selected="selected"
                                        </c:if>>${s.getName()}</option>
                                </c:forEach>
                            </select>
                            &nbsp;   From  &nbsp;  <input id="from" type="date" name="from">
                            &nbsp;   To  &nbsp;  <input id="to" type="date" name="to">
                               <!--<input style="margin-left: 710px" type="text" name="name" value="${param.name}"placeholder="Enter name.."/>-->
                            <input style="margin-left: 710px" type="text" name="email" value="${param.email}"placeholder="Enter email.."/>
                            <input onclick="submitForm();" type="submit" value="Search"/>

                        </form>
                    </div>
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>Course Name</th>
                            <th>Registration Time</th>
                            <th>Total Cost</th>
                            <th>Update Time</th>
                            <th>Regis By</th>
                            <th>Last Updated By</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${requestScope.ListRegistration}" var="i">
                            <tr>
                                <td>${i.id}</td>
                                <td>${i.getCourse().getTitle()}</td>
                                <fmt:parseDate value="${i.registrationTime}" var="dateObject"
                                               pattern="yyyy-MM-dd HH:mm:ss" />
                                <td><fmt:formatDate value="${dateObject}" pattern="dd/MM/yyyy" />
                                    <fmt:formatDate value="${dateObject }" pattern="hh:mm a" />
                                </td>
                                <td>${i.totalCourse}</td>
                                <fmt:parseDate value="${i.updateTime}" var="dateObject1"
                                               pattern="yyyy-MM-dd HH:mm:ss" />
                                <td><fmt:formatDate value="${dateObject1}" pattern="dd/MM/yyyy" />
                                    <fmt:formatDate value="${dateObject1}" pattern="hh:mm a" />
                                </td>
                                <td>${i.getRegisBy().getEmail()}</td>
                                <td>${i.getUserAccept()}</td>
                                <td>
                                    <c:if test="${i.getRegistrationStatus().getId() eq 3}">
                                        <button style="background-color: green;color: white" onclick="doChangeRegistrationListStatus(${i.id}, 1)">Approve</button>
                                        <button style="background-color: red;color: white" onclick="doChangeRegistrationListStatus(${i.id}, 2)">Reject</button>
                                    </c:if>
                                    <c:if test="${i.getRegistrationStatus().getId() eq 4}">
                                        <button disabled="" style="background-color: black;color: white">Canceled</button>
                                    </c:if>
                                    <c:if test="${i.getRegistrationStatus().getId() eq 1}">
                                        <button disabled="" style="background-color: blue;color: white">Approved</button>
                                    </c:if>
                                    <c:if test="${i.getRegistrationStatus().getId() eq 2}">
                                        <button disabled="" style="background-color: red;color: white">Rejected</button>
                                    </c:if>
                                </td>
                            </c:forEach> 
                    </table>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
            <center><div class="d-flex justify-content-center">
                    <c:if test="${requestScope.totalPage>1}">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-end">
                                <li class="page-item ${page<=1?"disabled":""}">
                                    <a class="page-link" href="${url}&page=${page-1}" tabindex="-1" aria-disabled="true">Previous</a>
                                </li>

                                <c:forEach begin="1" end="${totalPage}" var="i">
                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                    </c:forEach>
                                    <%
                                        String url = request.getAttribute("url").toString();
                                        int page1 = Integer.parseInt(request.getAttribute("page").toString());
                                        request.getSession().setAttribute("url", url + "&page=" + page1);
                                    %>
                                <li class="page-item ${page>=totalPage?"disabled":""}">
                                    <a class=" page-link" href="${url}&page=${page+1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                </div></center>
        </div>
    </div>
</section><!-- /.content -->

<script>
    function submitForm() {
        const to = new Date(document.getElementById('to').value.toString());
        const from = new Date(document.getElementById('from').value.toString());
        const different = (to - from) / (1000 * 3600 * 24);
        console.log(different);
        let alert = document.getElementById('alert date');
        if (different < 0) {
            window.alert("Date to must be greater than from");
            event.preventDefault();
            return false;
        }
        return true;
    }
</script>
