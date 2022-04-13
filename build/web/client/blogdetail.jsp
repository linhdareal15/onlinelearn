<%-- 
    Document   : blogdetail
    Created on : Feb 6, 2022, 6:05:11 PM
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
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">${requestScope.blogDetail.bloglist_id.category_id.name}</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->

    <!-- Detail Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="row">
                <div class="col-lg-8">
                    <div class="mb-5">
                        <h6 class="text-primary mb-3"><fmt:formatDate type = "date" 
                            value = "${requestScope.blogDetail.updated_date}" /></h6>
                        <h1 class="mb-5">${requestScope.blogDetail.bloglist_id.title}</h1>
                        <div style="display: flex; justify-content: space-between;">
                            <p class="text-left mb-3">Category: <b>${requestScope.blogDetail.bloglist_id.category_id.name}</b></p>
                            <p class="text-right mb-3"><b>Post by: </b><i>${requestScope.blogDetail.author.fullname}</i></p>
                        </div>
                        <img class="img-fluid rounded w-100 mb-4" src="${requestScope.blogDetail.bloglist_id.thumbnail}" alt="Image">
                        <p>${requestScope.blogDetail.post_content}</p>
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
                        <h3 class="text-uppercase mb-4" style="letter-spacing: 5px;" ><a  href="blog">Post Categories</a></h3>                     
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
    <!-- Detail End -->




  
