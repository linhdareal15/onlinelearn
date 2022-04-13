<%-- 
    Document   : blog
    Created on : Feb 4, 2022, 12:30:10 PM
    Author     : Duy Hiep
--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    

    <!-- Header Start -->
    <div class="container-fluid page-header" style="margin-bottom: 90px;">
        <div class="container">
            <div class="d-flex flex-column justify-content-center" style="min-height: 300px">
                <h3 class="display-4 text-white text-uppercase">Blog</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Blog</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


    <!-- Blog Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="row">
                <div class="col-lg-8">
                    <div class="row">
                        <c:forEach items="${requestScope.blogList}" var="bl">
                        <div class="col-lg-12 mb-12">
                            <div class="blog-item position-relative overflow-hidden rounded mb-2" style="margin: 1rem">
                                <img class="img-fluid" src="${bl.thumbnail}" alt="">
                                <a class="blog-overlay text-decoration-none" href="blogdetail?id=${bl.id}">
                                    <h1 class="text-white mb-3" style="font-size: 1rem">${bl.title}</h1>
                                    <p class="text-primary m-0"><fmt:formatDate type = "date" 
                            value = "${bl.postdate}" /></p>
                                    <p class="text-primary m-0">${bl.category_id.name}</p>  
                                    <p class="text-white">${bl.brief_info}</p>
                                    
                                </a>
                            </div>
                        </div>
                        </c:forEach>
                        <div class="col-12">
                            <nav aria-label="Page navigation">
                                <ul class="pagination pagination-lg justify-content-center mb-0">
                                    <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                                         <li class="page-item"><a class="page-link" href="?index=0">First</a></li>
                                    </c:if>
                                    <c:if test="${requestScope.current_index != 0}">
                                      <li class="page-item">
                                          <a class="page-link" href="?index=${requestScope.current_index - 1}" aria-label="Previous">
                                          <span aria-hidden="true">&laquo;</span>
                                          <span class="sr-only">Previous</span>
                                        </a>
                                      </li> 
                                    </c:if>
     
                                    <c:forEach begin="${requestScope.current_index + 1}" end="${requestScope.current_index + 4}"  var="i">   
                                        <c:if test="${i < requestScope.endPage}">

                                            <li class="${requestScope.current_index == i - 1 ? "page-item active":"page-item"}"><a class="page-link" href="?index=${i-1}">${i}</a></li>                                             
                                        </c:if>             
                                    </c:forEach>
                                    <c:if test="${requestScope.current_index != requestScope.endPage - 1}">
                                      <li class="page-item">
                                      <a class="page-link" href="?index=${requestScope.current_index + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                      </a>
                                      </li>
                                    </c:if>
                                    
                                    <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                                         <li class="page-item"><a class="page-link" href="?index=${requestScope.endPage - 1}">Last</a></li>
                                    </c:if>
                                </ul>
                              </nav>
                        </div>
                    </div>
                </div>
    
                <div class="col-lg-4 mt-5 mt-lg-0">
                    <!-- Search Form -->
                    <div class="mb-5">
                        <form action="searchBlog">
                            <div class="input-group">
                                <input type="text" name="keyword" class="form-control form-control-lg" placeholder="Keyword">
                                <div class="input-group-append">
                                    <button type="submit" class="input-group-text bg-transparent text-primary"><i
                                            class="fa fa-search"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
    
                    <!-- Category List -->
                    <div class="mb-5">
                        <h3 class="text-uppercase mb-4" style="letter-spacing: 5px;">Categories</h3>
                        <ul class="list-group list-group-flush">
                            <c:forEach items="${requestScope.blogCategory}" var="bc">
                                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    <p href="" class="text-decoration-none h6 m-0">${bc.name}</p>
                                    <span class="badge badge-primary badge-pill">${bc.getTotalBlog(bc.id)}</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
    
                    <!-- Recent Post -->
                    <div class="mb-5">
                        <h3 class="text-uppercase mb-4" style="letter-spacing: 5px;">Highlight Post</h3>
                        <c:forEach items="${requestScope.highlightPost}" var="hl">
                            <a class="d-flex align-items-center text-decoration-none mb-3" href="blogdetail?id=${hl.id}">
                                <img class="img-fluid rounded" style="width: 40%" src="${hl.thumbnail}" alt="">
                            <div class="pl-3">
                                <h6 class="m-1">${hl.title}</h6>
                                <small><fmt:formatDate type = "date" 
                            value = "${hl.postdate}" /></small>
                            </div>
                        </a>
                        </c:forEach>
                    </div>
    
                    <!-- Tag Cloud -->
                    <div class="mb-5">
                        <h3 class="text-uppercase mb-4" style="letter-spacing: 5px;">Tag Cloud</h3>
                        <div class="d-flex flex-wrap m-n1">
                            <c:forEach items="${requestScope.blogCategory}" var="bc">
                                <a href="" class="btn btn-outline-primary m-1">${bc.name}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Blog End -->


 