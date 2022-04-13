<%--     Document   : index.jsp
    Created on : Jan 16, 2022, 12:18:20 PM
    Author     : Louis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>${requestScope.title_value}</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- CSS Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="client/css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>

        <!-- Topbar Start -->
        <div class="container-fluid d-none d-lg-block">
            <div class="row align-items-center py-4 px-xl-5">
                <div class="col-lg-3">
                    <a href="" class="text-decoration-none">
                        <h1 class="m-0"><span class="text-primary">ONLINE</span>LEARN</h1>
                    </a>
                </div>
                <div class="col-lg-3 text-right">
                    <div class="d-inline-flex align-items-center">
                        <i class="fa fa-2x fa-map-marker-alt text-primary mr-3"></i>
                        <div class="text-left">
                            <h6 class="font-weight-semi-bold mb-1">Our Office</h6>
                            <small>FPT University, Hoa Lac, Thach That, Ha Noi</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-right">
                    <div class="d-inline-flex align-items-center">
                        <i class="fa fa-2x fa-envelope text-primary mr-3"></i>
                        <div class="text-left">
                            <h6 class="font-weight-semi-bold mb-1">Email Us</h6>
                            <small>G4@fpt.edu.vn</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 text-right">
                    <div class="d-inline-flex align-items-center">
                        <i class="fa fa-2x fa-phone text-primary mr-3"></i>
                        <div class="text-left">
                            <h6 class="font-weight-semi-bold mb-1">Call Us</h6>
                            <small>+012 345 6789</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->

        <!-- Navbar Start -->
        <div class="container-fluid">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="d-flex align-items-center justify-content-between bg-secondary w-100 text-decoration-none" data-toggle="collapse" href="#navbar-vertical" style="height: 67px; padding: 0 30px;">
                        <h5 class="text-primary m-0"><i class="fa fa-book-open mr-2"></i>Subjects</h5>
                        <i class="fa fa-angle-down text-primary"></i>
                    </a>
                    <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 9;">
                        <div class="navbar-nav w-100">
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link" data-toggle="dropdown">Web Design <i class="fa fa-angle-down float-right mt-1"></i></a>
                                <div class="dropdown-menu position-absolute bg-secondary border-0 rounded-0 w-100 m-0">
                                    <a href="" class="dropdown-item">HTML</a>
                                    <a href="" class="dropdown-item">CSS</a>
                                    <a href="" class="dropdown-item">jQuery</a>
                                </div>
                            </div>
                            <a href="" class="nav-item nav-link">Apps Design</a>
                            <a href="" class="nav-item nav-link">Marketing</a>
                            <!--                        <a href="" class="nav-item nav-link">Research</a>
                                                    <a href="" class="nav-item nav-link">SEO</a>-->
                        </div>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0"><span class="text-primary">ONLINE</span>LEARN</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav py-0">
                                <a href="home" class="nav-item nav-link">Home</a>
                                <a href="about" class="nav-item nav-link">About</a>
                                <a href="course" class="nav-item nav-link">Courses</a>
                                <a href="blog" class="nav-item nav-link">Blog</a>
                                <c:if test="${sessionScope.user.role.role_id == 4}">
                                    <a href="mylearning" class="nav-item nav-link">My Learning</a>
                                </c:if>
                            </div>
                            <!--<a class="btn btn-primary py-2 px-4 ml-auto d-none d-lg-block" href="../changepass">Change Password</a>-->
                            <c:choose>
                                <c:when test="${sessionScope.user!=null}">
                                    <div class="dropdown">
                                        <button class="btn btn-primary py-2 px-4 ml-auto d-none d-lg-block dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            Hello, ${requestScope.login_href_value}
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item" href="changepass">Change password</a>
                                            <a href="" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                Edit your profile
                                            </a>
                                            <a class="dropdown-item" href="logout">Logout</a>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-primary py-2 px-4 ml-auto d-none d-lg-block" href="register">Join Now</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar End -->

        <!-- Button trigger modal -->
        <style>
            .form-label{
                font-weight: 700;
            }
            .mb-3 img {
                left: 50%;
                transform: translateX(-50%);
                background: white;
                position: absolute;
                padding: 5px;
                padding-bottom: 30px;
                border-radius: 50%;
                /*                display: block;*/
                align-items: center;
                margin-bottom: 100px;

            }
            .oke{
                margin-bottom: 250px;
            }
            .profile .pic img {
                border-radius: 50%;
            }
        </style>
        <!-- Modal -->
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Change profile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form class="form-horizontal tasi-form" method="post" enctype="multipart/form-data" id="form" action="userprofile">
                        <div class="modal-body mb-3">

                            <input value="${sessionScope.user.id}" type="text" hidden="" name="id"class="form-control" id="id" aria-describedby="emailHelp">

                            <div class=" oke">
                                <label for="exampleInputEmail1" class="form-label">AVATAR</label>
                                
                            </div>
                            <input type="file" name="avatar" >
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">USERNAME</label>
                                <input id="username" value="${sessionScope.user.username}" type="text" name="username"class="form-control" required="" aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">FULL NAME</label>
                                <input  id="fullname" type="text" value="${sessionScope.user.getFullname()}" name="fullname"class="form-control" required=""aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">GENDER</label><br>
                                <c:if test="${sessionScope.user.gender==true}">
                                    Male <input type="radio" id="gender" name="gender" value="1" checked="">
                                    Female <input type="radio" id="gender" name="gender" value="0">
                                </c:if>
                                <c:if test="${sessionScope.user.gender==false}">
                                    Male <input type="radio" id="gender" name="gender" value="1">
                                    Female <input type="radio" id="gender" name="gender" value="0"checked="">
                                </c:if>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">ADDRESS</label>
                                <input id="address" type="text" value="${sessionScope.user.address}"  name="address" class="form-control" required=""aria-describedby="emailHelp">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">PHONE NUMBER</label>
                                <input id="phone" type="text" name="phone" value="${sessionScope.user.phonenumber}" class="form-control" required="" aria-describedby="emailHelp">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <c:if test="${requestScope.pageInclude != null}">
            <jsp:include page="${requestScope.pageInclude}"></jsp:include>
        </c:if>


        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-white py-5 px-sm-3 px-lg-5" style="margin-top: 90px;">
            <div class="row pt-5">
                <div class="col-lg-7 col-md-12">
                    <div class="row">
                        <div class="col-md-6 mb-5">
                            <h5 class="text-primary text-uppercase mb-4" style="letter-spacing: 5px;">Get In Touch</h5>
                            <p><i class="fa fa-map-marker-alt mr-2"></i>FPT University, Hoa Lac, Thach That, Ha Noi</p>
                            <p><i class="fa fa-phone-alt mr-2"></i>+012 345 67890</p>
                            <p><i class="fa fa-envelope mr-2"></i>G4@fpt.edu.vn</p>
                            <div class="d-flex justify-content-start mt-4">
                                <a class="btn btn-outline-light btn-square mr-2" href="#"><i class="fab fa-twitter"></i></a>
                                <a class="btn btn-outline-light btn-square mr-2" href="#"><i class="fab fa-facebook-f"></i></a>
                                <a class="btn btn-outline-light btn-square mr-2" href="#"><i class="fab fa-linkedin-in"></i></a>
                                <a class="btn btn-outline-light btn-square" href="#"><i class="fab fa-instagram"></i></a>
                            </div>
                        </div>
                        <div class="col-md-6 mb-5">
                            <h5 class="text-primary text-uppercase mb-4" style="letter-spacing: 5px;">Our Courses</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Web Design</a>
                                <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Apps Design</a>
                                <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Marketing</a>
                                <!--                            <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Research</a>
                                                            <a class="text-white" href="#"><i class="fa fa-angle-right mr-2"></i>SEO</a>-->
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-md-12 mb-5">
                    <h5 class="text-primary text-uppercase mb-4" style="letter-spacing: 5px;">Newsletter</h5>
                    <p></p>
                    <div class="w-100">
                        <div class="input-group">
                            <div class="input-group">
                                <input type="text" class="form-control border-light" style="padding: 30px;" placeholder="Your Email Address">
                                <div class="input-group-append">
                                    <button class="btn btn-primary px-4"><a href="../register">Sign Up</a></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid bg-dark text-white border-top py-4 px-sm-3 px-md-5" style="border-color: rgba(256, 256, 256, .1) !important;">
            <div class="row">
                <div class="col-lg-6 text-center text-md-left mb-3 mb-md-0">
                    <p class="m-0 text-white">&copy; <a href="#">OnlineLearn.com</a>. All Rights Reserved. Designed by <a href="https://www.facebook.com/groups/4861692537285006/">GROUP 4</a>
                    </p>
                </div>
                <div class="col-lg-6 text-center text-md-right">
                    <ul class="nav d-inline-flex">
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">Privacy</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">Terms</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">FAQs</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white py-0" href="#">Help</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Footer End -->

        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="fa fa-angle-double-up"></i></a>

        <!-- JavaScript Libraries -->
        <script src="client/js/Register1.js"></script>
        <script  type="text/javascript">
            Validator({
                form: '#form',
                rules: [
                    Validator.isName('#name'),
                    Validator.isUserName("#username"),
                    Validator.isAddress('#address'),
                    Validator.isPhone('#phone')
                ]
            });
            let i = document.getElementById("avatar");
        console.log(typeof(i.value));
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="client/lib/easing/easing.min.js"></script>
        <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <!-- Contact Javascript File -->
        <script src="client/mail/jqBootstrapValidation.min.js"></script>
        <script src="client/mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="client/js/main.js"></script>
    </body>
</html>
