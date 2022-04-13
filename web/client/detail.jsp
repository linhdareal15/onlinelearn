<%-- 
    Document   : detail
    Created on : Feb 12, 2022, 5:53:47 PM
    Author     : Louis
--%>
<%@page import="Model.Lesson"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    .ratings i {
        color: #cecece;
        font-size: 32px
    }

    .rating-color {
        color: #fbc634 !important
    }

    .review-count {
        font-weight: 400;
        margin-bottom: 2px;
        font-size: 24px !important
    }
</style>
<body>


    <!-- Header Start -->
    <div class="container-fluid page-header" style="margin-bottom: 90px;">
        <div class="container">
            <div class="d-flex flex-column justify-content-center" style="min-height: 300px">
                <h3 class="display-4 text-white text-uppercase">${requestScope.course.getTitle()}</h3>
                <div class="d-inline-flex text-white">
                    <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase"><a class="text-white" href="course">Course</a></p>
                    <i class="fa fa-angle-double-right pt-1 px-3"></i>
                    <p class="m-0 text-uppercase">Course Detail</p>
                </div>
            </div>
        </div>
    </div>
    <!-- Header End -->


    <div class="row col-md-12">
        <!-- About Start -->
        <div class="container-fluid py-5 col-md-8">
            <div class="container py-5">
                <div class="row align-items-center">
                    <div class="col-lg-5">
                        <img class="img-fluid rounded mb-4 mb-lg-0" src="/g4/loadImage?id=${requestScope.course.getCid()}" alt="">
                    </div>
                    <div class="col-lg-7">
                        <div class="text-left mb-4">
                            <h5 class="text-primary text-uppercase mb-3" style="letter-spacing: 5px;">About Course</h5>
                            <h1>${requestScope.course.getTitle()}</h1>
                        </div>
                        <p>${requestScope.course.getIntroduction()}</p>
                        <c:if test="${requestScope.status!=null && requestScope.status == 1}">
                            <a href="/lessonview?cid=${requestScope.course.getCid()}" class="btn btn-primary py-md-2 px-md-4 font-weight-semi-bold mt-2">START LEARNING</a>
                        </c:if>
                        <c:if test="${requestScope.status!=null && requestScope.status == 3}">
                            <a  class="btn btn-primary py-md-2 px-md-4 font-weight-semi-bold mt-2">REGISTERED</a>
                        </c:if>
                        <c:if test="${requestScope.status==null}">
                            <a href="/lessonview?cid=${requestScope.course.getCid()}" class="btn btn-primary py-md-2 px-md-4 font-weight-semi-bold mt-2">ENROLL NOW</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- About End --> 
        <div class="container-fluid px-2 pt-5 col-md-2" style="margin-top: 100px">
            <div class="row py-3">
                <div class="pr-2">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-share-fill" viewBox="0 0 16 16">
                    <path d="M11 2.5a2.5 2.5 0 1 1 .603 1.628l-6.718 3.12a2.499 2.499 0 0 1 0 1.504l6.718 3.12a2.5 2.5 0 1 1-.488.876l-6.718-3.12a2.5 2.5 0 1 1 0-3.256l6.718-3.12A2.5 2.5 0 0 1 11 2.5z"/>
                    </svg>
                </div>
                <div class="pl-4">
                    <h5>Shareable Certificate</h5>
                    <small>Earn a Certificate upon completion</small>
                </div>
            </div>

            <div class="row py-3">
                <div class="pr-2">
                    <svg aria-hidden="true" class="_ufjrdd" viewBox="0 0 48 48" role="img" aria-labelledby="100%onlinecoursesdda2ebd1-3511-4f1b-838c-83fce9c0dede 100%onlinecoursesdda2ebd1-3511-4f1b-838c-83fce9c0dedeDesc" xmlns="http://www.w3.org/2000/svg" style="fill: rgb(0, 0, 0); height: 20px; width: 20px;"><title id="100%onlinecoursesdda2ebd1-3511-4f1b-838c-83fce9c0dede">100% online courses</title><path d="M29.144,2.6074 C30.868,4.5654 32.31,7.5324 33.322,11.1824 C36.407,10.4934 38.17,9.4914 39.698,8.6014 C36.855,5.7034 33.218,3.5864 29.144,2.6074 L29.144,2.6074 Z M8.239,8.6654 C9.691,9.5474 11.656,10.5824 15.059,11.2674 C16.093,7.5044 17.581,4.4624 19.364,2.4914 C15.054,3.4194 11.208,5.6144 8.239,8.6654 L8.239,8.6654 Z M17.026,11.5984 C18.939,11.8624 21.228,12.0234 24,12.0234 C27.009,12.0234 29.408,11.8414 31.364,11.5454 C29.704,5.6564 26.962,2.1054 24.321,2.0024 C24.263,2.0014 24.206,2.0014 24.148,2.0004 C21.478,2.0484 18.698,5.6304 17.026,11.5984 L17.026,11.5984 Z M2.022,23.0004 L13.482,23.0004 C13.543,19.4734 13.93,16.1644 14.579,13.2154 C10.664,12.4204 8.499,11.1834 6.891,10.1844 C4.03,13.7214 2.24,18.1604 2.022,23.0004 L2.022,23.0004 Z M15.482,23.0004 L32.923,23.0004 C32.861,19.4734 32.472,16.2744 31.855,13.4984 C29.767,13.8234 27.209,14.0234 24,14.0234 C21.035,14.0234 18.585,13.8444 16.538,13.5524 C15.928,16.3154 15.543,19.4964 15.482,23.0004 L15.482,23.0004 Z M34.923,23.0004 L45.977,23.0004 C45.758,18.1314 43.95,13.6704 41.06,10.1234 C40.978,10.1704 40.896,10.2184 40.813,10.2664 C39.129,11.2484 37.204,12.3704 33.808,13.1304 C34.467,16.1004 34.861,19.4394 34.923,23.0004 L34.923,23.0004 Z M23.915,33.4534 C27.147,33.4534 29.78,33.6774 31.956,34.0364 C32.515,31.3704 32.864,28.3324 32.923,25.0004 L15.482,25.0004 C15.54,28.2944 15.883,31.3024 16.431,33.9474 C18.471,33.6414 20.922,33.4534 23.915,33.4534 L23.915,33.4534 Z M2.022,25.0004 C2.233,29.6894 3.92,34.0034 6.627,37.4834 C6.703,37.4394 6.78,37.3944 6.858,37.3494 C8.586,36.3414 10.707,35.1064 14.475,34.3004 C13.889,31.4754 13.54,28.3354 13.482,25.0004 L2.022,25.0004 Z M33.905,34.4184 C37.597,35.2674 39.727,36.5214 41.338,37.5274 C44.065,34.0394 45.766,29.7094 45.977,25.0004 L34.923,25.0004 C34.864,28.3824 34.506,31.5634 33.905,34.4184 L33.905,34.4184 Z M33.443,36.3704 C32.426,40.2264 30.938,43.3554 29.144,45.3924 C33.373,44.3754 37.132,42.1334 40.02,39.0634 C38.571,38.1704 36.649,37.1104 33.443,36.3704 L33.443,36.3704 Z M7.947,39.0284 C10.955,42.2394 14.911,44.5494 19.364,45.5084 C17.504,43.4524 15.965,40.2294 14.927,36.2404 C11.531,36.9614 9.609,38.0614 7.947,39.0284 L7.947,39.0284 Z M16.888,35.8944 C18.541,42.1664 21.404,45.9494 24.148,45.9994 C24.206,45.9994 24.263,45.9984 24.321,45.9974 C27.031,45.8924 29.847,42.1574 31.492,35.9904 C29.446,35.6594 26.966,35.4534 23.915,35.4534 C21.091,35.4534 18.793,35.6194 16.888,35.8944 L16.888,35.8944 Z M24.203,48.0004 L24,48.0004 C10.767,48.0004 0,37.2334 0,24.0004 C0,10.7664 10.767,0.0004 24,0.0004 L24.203,0.0004 C24.26,0.0004 24.317,0.0014 24.375,0.0034 C37.436,0.2044 48,10.8914 48,24.0004 C48,37.1084 37.436,47.7954 24.375,47.9974 C24.317,47.9994 24.26,48.0004 24.203,48.0004 L24.203,48.0004 Z" role="presentation"></path></svg>
                </div>
                <div class="pl-4">
                    <h5>100% online </h5>
                    <small>Start instantly and learn </small>
                </div>
            </div>

            <div class="row py-3">
                <div class="pr-2">
                    <svg aria-hidden="true" class="_ufjrdd" viewBox="0 0 48 48" role="img" aria-labelledby="100%onlinecoursesdda2ebd1-3511-4f1b-838c-83fce9c0dede 100%onlinecoursesdda2ebd1-3511-4f1b-838c-83fce9c0dedeDesc" xmlns="http://www.w3.org/2000/svg" style="fill: rgb(0, 0, 0); height: 20px; width: 20px;"><title id="100%onlinecoursesdda2ebd1-3511-4f1b-838c-83fce9c0dede">100% online courses</title><path d="M29.144,2.6074 C30.868,4.5654 32.31,7.5324 33.322,11.1824 C36.407,10.4934 38.17,9.4914 39.698,8.6014 C36.855,5.7034 33.218,3.5864 29.144,2.6074 L29.144,2.6074 Z M8.239,8.6654 C9.691,9.5474 11.656,10.5824 15.059,11.2674 C16.093,7.5044 17.581,4.4624 19.364,2.4914 C15.054,3.4194 11.208,5.6144 8.239,8.6654 L8.239,8.6654 Z M17.026,11.5984 C18.939,11.8624 21.228,12.0234 24,12.0234 C27.009,12.0234 29.408,11.8414 31.364,11.5454 C29.704,5.6564 26.962,2.1054 24.321,2.0024 C24.263,2.0014 24.206,2.0014 24.148,2.0004 C21.478,2.0484 18.698,5.6304 17.026,11.5984 L17.026,11.5984 Z M2.022,23.0004 L13.482,23.0004 C13.543,19.4734 13.93,16.1644 14.579,13.2154 C10.664,12.4204 8.499,11.1834 6.891,10.1844 C4.03,13.7214 2.24,18.1604 2.022,23.0004 L2.022,23.0004 Z M15.482,23.0004 L32.923,23.0004 C32.861,19.4734 32.472,16.2744 31.855,13.4984 C29.767,13.8234 27.209,14.0234 24,14.0234 C21.035,14.0234 18.585,13.8444 16.538,13.5524 C15.928,16.3154 15.543,19.4964 15.482,23.0004 L15.482,23.0004 Z M34.923,23.0004 L45.977,23.0004 C45.758,18.1314 43.95,13.6704 41.06,10.1234 C40.978,10.1704 40.896,10.2184 40.813,10.2664 C39.129,11.2484 37.204,12.3704 33.808,13.1304 C34.467,16.1004 34.861,19.4394 34.923,23.0004 L34.923,23.0004 Z M23.915,33.4534 C27.147,33.4534 29.78,33.6774 31.956,34.0364 C32.515,31.3704 32.864,28.3324 32.923,25.0004 L15.482,25.0004 C15.54,28.2944 15.883,31.3024 16.431,33.9474 C18.471,33.6414 20.922,33.4534 23.915,33.4534 L23.915,33.4534 Z M2.022,25.0004 C2.233,29.6894 3.92,34.0034 6.627,37.4834 C6.703,37.4394 6.78,37.3944 6.858,37.3494 C8.586,36.3414 10.707,35.1064 14.475,34.3004 C13.889,31.4754 13.54,28.3354 13.482,25.0004 L2.022,25.0004 Z M33.905,34.4184 C37.597,35.2674 39.727,36.5214 41.338,37.5274 C44.065,34.0394 45.766,29.7094 45.977,25.0004 L34.923,25.0004 C34.864,28.3824 34.506,31.5634 33.905,34.4184 L33.905,34.4184 Z M33.443,36.3704 C32.426,40.2264 30.938,43.3554 29.144,45.3924 C33.373,44.3754 37.132,42.1334 40.02,39.0634 C38.571,38.1704 36.649,37.1104 33.443,36.3704 L33.443,36.3704 Z M7.947,39.0284 C10.955,42.2394 14.911,44.5494 19.364,45.5084 C17.504,43.4524 15.965,40.2294 14.927,36.2404 C11.531,36.9614 9.609,38.0614 7.947,39.0284 L7.947,39.0284 Z M16.888,35.8944 C18.541,42.1664 21.404,45.9494 24.148,45.9994 C24.206,45.9994 24.263,45.9984 24.321,45.9974 C27.031,45.8924 29.847,42.1574 31.492,35.9904 C29.446,35.6594 26.966,35.4534 23.915,35.4534 C21.091,35.4534 18.793,35.6194 16.888,35.8944 L16.888,35.8944 Z M24.203,48.0004 L24,48.0004 C10.767,48.0004 0,37.2334 0,24.0004 C0,10.7664 10.767,0.0004 24,0.0004 L24.203,0.0004 C24.26,0.0004 24.317,0.0014 24.375,0.0034 C37.436,0.2044 48,10.8914 48,24.0004 C48,37.1084 37.436,47.7954 24.375,47.9974 C24.317,47.9994 24.26,48.0004 24.203,48.0004 L24.203,48.0004 Z" role="presentation"></path></svg>
                </div>
                <div class="pl-4">
                    <h5>Flexible Schedule</h5>
                    <small>Set and maintain flexible deadlines.</small>
                </div>
            </div>

        </div>
    </div>

    <!--    <div class="col-md-12 my-5" style="border-top: 0.5px solid gray ">
            <center><h3 class="mt-4">Courses in this Specialization</h3></center>
            <div class="row col-md-12 justify-content-center">
                <--%
                    List<Lesson> list = (List) request.getAttribute("lesson");
                    int i = 1;
                    for (Lesson lesson : list) {
                %>
               
                    <div class="row col-md-8 justify-content-center">
    
                        <div class="col-md-2 mt-5">
    
                            <h3 style="letter-spacing: 5px" >COURSE &nbsp <--%=i++%></h3>
    
                        </div>
                        <div class="col-md-6 my-3">
                            <a style="text-decoration: none" href="lessonview?cid=""><h1 class="text-info"> <--%=lesson.getName()%> </h1></a>
                            <div class="d-flex justify-content-between align-items-center my-3">
                                <div class="ratings"> 
                                    <i class="fa fa-star rating-color"></i> 
                                    <i class="fa fa-star rating-color"></i> 
                                    <i class="fa fa-star rating-color"></i> 
                                    <i class="fa fa-star rating-color"></i> 
                                    <i class="fa fa-star"></i> </div>
                                <h5 class="review-count">12 Reviews</h5>
                            </div>
                            <p>Aliquyam accusam clita nonumy ipsum sit sea clita ipsum clita, ipsum dolores amet voluptua duo dolores et sit 
                                ipsum rebum, sadipscing et erat eirmod diam kasd labore clita est. Diam sanctus gubergren sit rebum clita amet, 
                                sea est sea vero sed et. Sadipscing labore tempor at sit dolor clita consetetur diam. Diam ut diam tempor no et, lorem dolore 
                                invidunt no nonumy stet ea labore, dolor justo et sit gubergren diam sed sed no ipsum. Sit tempor ut nonumy 
                                elitr dolores justo aliquyam ipsum stet</p>
                        </div>
    
                    </div>
                <--%}%>
            </div>
        </div>-->


