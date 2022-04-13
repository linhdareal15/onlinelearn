<%-- 
    Document   : subjectList
    Created on : Feb 25, 2022, 9:59:46 PM
    Author     : Quang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function doChangeStatusSubject(course_id, course_status) {
        var c = confirm("Change Subject Status ?");
        if (c) {
            window.location.href = "changecoursesubjectstatus?id=" + course_id + "&status=" + course_status;
        }
    }
    function editCourseSubject(id)
    {
        window.location.href = "editcoursesubject?coursesubjectid=" + id;
    }
</script>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel">
                <header class="panel-heading">
                    <div class="panel-heading" style="display: flex;">
                        <div style="margin-right: 74%;"> Subject List</div>
                        <div>
                            <a href="addsubject">Add new subject </a>
                        </div>
                    </div>
                </header>
                <!-- <div class="box-header"> -->
                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                <!-- </div> -->
                <div class="panel-body table-responsive">
                    <div class="box-tools m-b-15" style="display: flex;">
                        <form action="subjectlist" method="">
                            Author <select name="author" class="input-sm">
                                <option value="-1">ALL</option>
                                <c:forEach items="${requestScope.authors}" var="i">
                                    <option ${requestScope.author_id eq i.id ? "selected=\"selected\"" : ""} value="${i.id}">${i.fullname}</option>
                                </c:forEach>
                            </select>
                            Status  <select name="status" class="input-sm" >
                                <option value="-1" <c:if test="${requestScope.subject_status eq -1}">
                                        selected="selected"
                                    </c:if>>All</option>
                                <option value="1" <c:if test="${requestScope.subject_status eq 1}">
                                        selected="selected"
                                    </c:if>>Publish</option>
                                <option value="0" <c:if test="${requestScope.subject_status eq 0}">
                                        selected="selected"
                                    </c:if>>Unpublish</option>
                            </select>
                            <input style="margin-left: 710px" type="text" name="subject_title" value="${requestScope.subject_title}"placeholder="Enter title.."/>
                            <input type="submit" value="Search"/>
                        </form>
                    </div>
                    <table class="table table-hover">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th style="text-align: center">Thumbnail</th>
                            <th>Author</th>
                            <th>List Price</th>
                            <th>Sale Price</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${requestScope.courses}" var="i">
                            <tr>
                                <td onclick="editCourseSubject('${i.cid}')">${i.cid}</td>
                                <td onclick="editCourseSubject('${i.cid}')">${i.title}</td>
                                <td onclick="editCourseSubject('${i.cid}')" style="text-align: center"><img src="${pageContext.servletContext.contextPath }/subject/img?id=${i.cid}" width="300px" style="border-radius: 1rem"></td>
                                <td onclick="editCourseSubject('${i.cid}')">${i.author.fullname}</td>
                                <td onclick="editCourseSubject('${i.cid}')">${i.listprice}$</td>
                                <td onclick="editCourseSubject('${i.cid}')">${i.saleprice}$</td>
                                <td>
                                    <c:if test="${i.status eq true}">
                                        <button style="background-color: green;color: white" onclick="doChangeStatusSubject(${i.cid}, 1)">Publish</button>
                                    </c:if>
                                    <c:if test="${i.status eq false}">
                                        <button style="background-color: red;color: white" onclick="doChangeStatusSubject(${i.cid}, 0)">Unpublish</button>
                                    </c:if>
                                </td>
                            </c:forEach> 
                    </table>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
            <div class="text-center">
                <ul class="pagination">
                    <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                        <li class="page-item"><a class="page-link" href="?index=0&&author=${requestScope.status_id}&&status=${requestScope.subject_status}&&author=${requestScope.author_id}&&subject_title=${requestScope.subject_title}">First</a></li>
                    </c:if>
                        
                    <c:if test="${requestScope.current_index != 0}">
                        <li class="page-item">
                            <a class="page-link" href="?index=${requestScope.current_index - 1}&&status=${requestScope.subject_status}&&author=${requestScope.author_id}&&subject_title=${requestScope.subject_title}"" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li> 
                    </c:if>
                        
                    <c:forEach begin="${requestScope.current_index + 1}" end="${requestScope.current_index + 4}"  var="i">   
                        <c:if test="${i < requestScope.endPage}">

                            <li class="${requestScope.current_index == i - 1 ? "page-item active":"page-item"}"><a class="page-link" href="?index=${i-1}&&status=${requestScope.subject_status}&&author=${requestScope.author_id}&&subject_title=${requestScope.subject_title}"">${i}</a></li>                                             
                        </c:if>             
                    </c:forEach>
                            
                    <c:if test="${requestScope.current_index != requestScope.endPage - 1}">
                        <li class="page-item">
                            <a class="page-link" href="?index=${requestScope.current_index + 1}&&status=${requestScope.subject_status}&&author=${requestScope.author_id}&&subject_title=${requestScope.subject_title}"" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </c:if>
                        
                    <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                        <li class="page-item"><a class="page-link" href="?index=${requestScope.endPage - 1}&&status=${requestScope.status}&&category=${requestScope.category}&&author=${requestScope.author}&&sort=${requestScope.sort}">Last</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</section><!-- /.content -->
