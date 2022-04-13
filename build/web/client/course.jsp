<%-- 
    Document   : course
    Created on : Feb 10, 2022, 11:18:52 AM
    Author     : Louis
--%>
<%@page import="java.util.List"%>
<%@page import="Model.CourseRegistration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="Model.Course"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function joinCourse(id) {
        window.location.href = "course/user-register?id=" + id;
    }
</script>



<!-- Header Start -->
<div class="container-fluid page-header" style="margin-bottom: 90px;">
    <div class="container">
        <div class="d-flex flex-column justify-content-center" style="min-height: 300px">
            <h3 class="display-4 text-white text-uppercase">Courses</h3>
            <div class="d-inline-flex text-white">
                <p class="m-0 text-uppercase"><a class="text-white" href="home">Home</a></p>
                <i class="fa fa-angle-double-right pt-1 px-3"></i>
                <p class="m-0 text-uppercase">Courses</p>
            </div>
        </div>
    </div>
</div>
<!-- Header End -->
<!-- Courses Start -->
<div class="container-fluid py-5">
    <div class="container py-5 col-md-12">
        <div class="text-center mb-5">
            <h5 class="text-primary text-uppercase mb-3" style="letter-spacing: 5px;">Courses</h5>
            <h1>Our Popular Courses</h1>
        </div>
        <div class="row col-md-4 justify-content-center search-form pb-3">
            <form action="course?action=search" method="get">
                <input type="text" class="form-control" name="name" placeholder="Enter course's name">
                <input type="button" class="btn btn-primary" value="Search">
            </form>
        </div>
        <div class="row col-md-12 d-flex justify-content-center flex-column align-content-center">
            <c:choose>
                <c:when test="${ListCourse==null || ListCourse.size()==0}">
                    <center><h3>NOTHING TO SHOW</h3></center>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${requestScope.ListCourse}" var="c">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="rounded overflow-hidden mb-2">
                                <img class="img-fluid" src="/g4/loadImage?id=${c.getCid()}" alt="course">
                                <div class="bg-secondary p-4">
                                    <div class="d-flex justify-content-between mb-3">
                                    </div>
                                    <a class="h5" href="course?action=detail&id=${c.getCid()}">${c.title}</a>
                                    <div class="border-top mt-4 pt-4">
                                        <div class="d-flex justify-content-between">
                                            <h5 class="m-0">$${c.listprice}</h5>
                                            <h6 class="m-0"><!-- Button trigger modal -->
                                                <input id="joinbtn${c.cid}" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${c.cid}" value="Join"  
                                                       <c:choose>
                                                           <c:when test="${requestScope.user != null}">                                                              
                                                               onclick="joinCourse(${c.getCid()})"/>
                                                           <input type="hidden" value="${requestScope.user.id}">
                                                       </c:when>
                                                       <c:when test="${requestScope.user == null}">
                                                           <!-- Modal -->
                                                           <div class="modal fade" id="exampleModal${c.cid}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                               <div class="modal-dialog" role="document">
                                                                   <div class="modal-content">
                                                                       <div class="modal-header">
                                                                           <h5 class="modal-title" id="exampleModalLabel">Join Course: ${c.title}</h5>
                                                                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                               <span aria-hidden="true">&times;</span>
                                                                           </button>
                                                                       </div>
                                                                       <div class="modal-body">
                                                                           <form action="../g4/course/customer-register" method="POST" id="form1" onclick="checkUsernameExist('${requestScope.username}'), checkEmailExist('${requestScope.email}')">
                                                                               <div class="form-group">
                                                                                   <label>Full Name</label>
                                                                                   <input name="fullname" class="form-control" type="text" placeholder="Full Name">
                                                                               </div>
                                                                               <div class="form-group">
                                                                                   <label>Title</label>
                                                                                   <input name="title" class="form-control" type="text" id="username" placeholder="Title">
                                                                                   <i class="fas fa-check-circle success" style="margin-top: 20px"></i>
                                                                                   <i class="fas fa-exclamation-circle error" style="margin-top: 20px"></i>
                                                                                   <small class="form-message"></small>
                                                                               </div>
                                                                               <div class="form-group">
                                                                                   <label>Email</label>
                                                                                   <input name="email" class="form-control" type="text" id="email" placeholder="Email">
                                                                                   <i class="fas fa-check-circle success" style="margin-top: 20px"></i>
                                                                                   <i class="fas fa-exclamation-circle error" style="margin-top: 20px"></i>
                                                                                   <small class="form-message"></small>
                                                                                   <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                                                               </div>
                                                                               <div class="form-group">
                                                                                   <label>Mobile Phone</label>
                                                                                   <input name="phonenumber" class="form-control" type="text" placeholder="Phone number">
                                                                                   <small class="form-message"></small>
                                                                                   <small id="emailHelp" class="form-text text-muted">We'll never share your phone number with anyone else.</small>
                                                                               </div>
                                                                               <div class="form-group">
                                                                                   <label>Address</label>
                                                                                   <input name="address" class="form-control" type="text" placeholder="Address">
                                                                                   <small class="form-message"></small>
                                                                               </div>
                                                                               <small id="loginHelp" class="form-text text-muted">If you have account <a href="/g4/client/login">Login</a></small>
                                                                               <small id="registerHelp" class="form-text text-muted">If you don't have account <a href="../g4/register">Register</a></small>
                                                                               <!-- Modal Footer -->
                                                                               <input type="hidden" name="id" value="${c.cid}">
                                                                               <div class="modal-footer">
                                                                                   <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                   <input type="hidden" name="courseId" value="${c.cid}">
                                                                                   <input type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal${c.cid}" onsubmit="event.preventDefault();myValidation();" value="Join"/> 
                                                                               </div>
                                                                           </form>
                                                                       </div>
                                                                   </div>
                                                               </div>
                                                           </div></small></h6>
                                                       <!-- Modal End -->
                                                </c:when>
                                            </c:choose>
                                            <c:forEach items="${requestScope.ListStatusCourseRegisted}" var="cr">
                                                <c:if test="${cr.getC().getCid() == c.cid && cr.getStatus() ==3}">
                                                    <input disabled="" type="button" class="btn btn-primary" value="registrated">
                                                    <script>
                                                        document.getElementById("joinbtn${c.cid}").style.display = "none";
                                                        //console.log(btn);

                                                    </script>    
                                                </c:if>
                                                <c:if test="${cr.getC().getCid() == c.cid && cr.getStatus() ==1}">
                                                    <button   class="btn btn-primary" ><a href="lessonview?cid=${c.cid}" style="text-decoration: none; color: white">Start Learning</a></button>
                                                    <script>
                                                        document.getElementById("joinbtn${c.cid}").style.display = "none";
                                                    </script>  
                                                </c:if>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<!-- Courses End -->
<div class="d-flex justify-content-center">
    <c:if test="${totalPage>1}">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item ${page<=1?"disabled":""}">
                    <a class="page-link" href="${url}page=${page-1}" tabindex="-1" aria-disabled="true">Previous</a>
                </li>

                <c:forEach begin="1" end="${totalPage}" var="i">
                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}page=${i}">${i}</a></li>
                    </c:forEach>
                <li class="page-item ${page>=totalPage?"disabled":""}">
                    <c:if test="${urlSearch!=null}">
                        <a class=" page-link" href="${url}page=${page+1}${urlSearch}">Next</a>
                    </c:if>
                    <a class=" page-link" href="${url}page=${page+1}">Next</a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>

<script type="text/javascript">
    function checkUsernameExist(list) {
        let inputElement = document.getElementById("username");
        let errorElement = inputElement.parentElement.querySelector('.form-message');
        let success = inputElement.parentElement.querySelector('.success');
        let error = inputElement.parentElement.querySelector('.error');
        const arr = list.split(', ');
        arr[0] = arr[0].slice(1);
        arr[arr.length - 1] = arr[arr.length - 1].slice(0, arr[arr.length - 1].length - 1);
        for (let i in arr) {
            if (arr[i] === inputElement.value) {
                success.style.visibility = "hidden";
                error.style.visibility = "visible";
                let message = arr[i] + " is existed";
                inputElement.setCustomValidity(message);
                inputElement.reportValidity();
                return false;
            } else {
                inputElement.setCustomValidity("");
                inputElement.reportValidity();
            }

        }
        console.log(inputElement);
        return true;
    }

    function checkEmailExist(list) {
        let inputElement = document.getElementById("email");
        let errorElement = inputElement.parentElement.querySelector('.form-message');
        let success = inputElement.parentElement.querySelector('.success');
        let error = inputElement.parentElement.querySelector('.error');
        const arr = list.split(', ');
        arr[0] = arr[0].slice(1);
        arr[arr.length - 1] = arr[arr.length - 1].slice(0, arr[arr.length - 1].length - 1);
        for (let i in arr) {
            if (arr[i] === inputElement.value) {
                //                    errorElement.innerText = arr[i] + " is existed";
                error.style.visibility = "visible";
                success.style.visibility = "hidden";
                let message = arr[i] + " is existed";
                console.log(message);
                inputElement.setCustomValidity(message);
                inputElement.reportValidity();
                return false;
            } else {
                inputElement.setCustomValidity("");
                inputElement.reportValidity();
            }

        }
        return true;
    }

    function myValidation() {
        if (checkUsernameExist('${requestScope.username}') === false || checkEmailExist('${requestScope.email}') === false) {
            return false;
        }
        return true;
    }
</script>

