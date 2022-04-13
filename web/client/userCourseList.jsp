<%-- 
    Document   : course
    Created on : Feb 10, 2022, 11:18:52 AM
    Author     : Louis
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Header Start -->
<div class="container-fluid page-header" style="margin-bottom: 90px;">
    <div class="container">
        <div class="d-flex flex-column justify-content-center" style="min-height: 300px">
            <h3 class="display-4 text-white text-uppercase">My Learning</h3>
            <div class="d-inline-flex text-white">
                <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                <i class="fa fa-angle-double-right pt-1 px-3"></i>
                <p class="m-0 text-uppercase">My Courses</p>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="nav-container">
            <ul role="navigation" class="nav-slide nav nav-tabs">
                <li role="presentation" class="" style="margin-right: 20px"><a href="mylearning" style="color: white;text-decoration: underline;">My Courses</a></li>
                <li role="presentation" class="" style="margin-right: 20px"><a href="wishlist" style="color: white;">Wishlist</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- Header End -->

<!-- Courses Start -->
<div class="container-fluid py-5">
    <div class="container py-5">
        <div class="row">
            <c:forEach items="${requestScope.list}" var="i">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="rounded overflow-hidden mb-2">
                        <img class="img-fluid" src="/g4/loadImage?id=${i.course.cid}" alt="course">
                        <div class="bg-secondary p-4">
                            <div class="d-flex justify-content-between mb-3">
                                <small class="m-0"><i class="far fa-clock text-primary mr-2"></i>${i.registrationTime}</small>
                            </div>
                            <a class="h5" href="course?action=detail&id=${i.course.cid}">${i.course.title}</a>
                            <div class="border-top mt-4 pt-4">
                                <div class="d-flex justify-content-between">
                                    <!--                                    <h5 class="m-0">$</h5>-->
                                    <h5 class="m-0">
                                        <button type="button" class="btn btn-primary" value="">
                                            <a href="lessonview?cid=${i.course.cid}" style="text-decoration: none;color: white">Start Learning</a>
                                        </button></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
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

