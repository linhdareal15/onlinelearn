<%-- 
    Document   : register
    Created on : Jan 14, 2022, 8:01:42 PM
    Author     : Duy Hiep
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Register</title>

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="client/css/style.css" rel="stylesheet">
    </head>
    <body>

        <!--      Topbar Start 
        -->    <div class="container-fluid d-none d-lg-block">
            <div class="row align-items-center py-4 px-xl-5">
                <div class="col-lg-3">
                    <a href="home" class="text-decoration-none">
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
        </div><!--
         Topbar End -->
        <!-- Registration Start -->
        <div class="container-fluid bg-registration" style="height: max-content" onclick="checkUsernameExist('${requestScope.username}'), checkEmailExist('${requestScope.email}')">
            <div class="container py-5">
                <div class="row align-items-center">
                    <div class="col-lg-7 mb-5 mb-lg-0">
                        <div class="mb-4">
                            <h5 class="text-primary text-uppercase mb-3" style="letter-spacing: 5px;">Need Any Courses</h5>
                            <h1 class="text-white">30% Off For New Students</h1>
                        </div>
                        <p class="text-white">Ecourse is the best way for you to learn everything.</p>
                        <ul class="list-inline text-white m-0">
                            <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Đa dạng khóa học</li>
                            <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Uy tín</li>
                            <li class="py-2"><i class="fa fa-check text-primary mr-3"></i>Chất lượng</li>
                        </ul>
                    </div>
                    <div class="col-lg-5">
                        <div class="card border-0">
                            <div class="card-header bg-light text-center p-4">
                                <h1 class="m-0">Sign Up Now</h1>
                            </div>
                            <div class="card-body rounded-bottom bg-primary p-4">
                                <form id="form" action="register" method="Post">
                                    <div class="form-group ">
                                        <input id="name" type="text" name="name" class="form-control border-0 p-4" placeholder="Full name" required="required" />
                                        <i class="fas fa-check-circle success"></i>
                                        <i class="fas fa-exclamation-circle error"></i>
                                        <small class="form-message"></small> 
                                    </div>
                                    <div class="form-group">
                                        <input id="email" type="email" name="email" class="form-control border-0 p-4" placeholder="abcd@gmail.com" required="required" />
                                        <i class="fas fa-check-circle success"></i>
                                        <i class="fas fa-exclamation-circle error"></i>
                                        <small class="form-message"></small> 

                                    </div>
                                    <div class="form-group">
                                        <input id="username" type="text" name="username" class="form-control border-0 p-4" placeholder="Username" required="required"/>
                                        <i class="fas fa-check-circle success "></i>
                                        <i class="fas fa-exclamation-circle error" style="top: 20%"></i>
                                        <small class="form-message" style="font-size: 0.8rem "></small>
                                    </div>
                                    <div class="form-group">
                                        <input id="password" type="password" name="password" class="form-control border-0 p-4" placeholder="Password" required="required" />
                                        <i class="fas fa-check-circle success"></i>
                                        <i class="fas fa-exclamation-circle error" style="top: 20%"></i>
                                        <small class="form-message" style="font-size: 0.8rem "></small>
                                    </div>
                                    <div class="form-group">
                                        <input id="password-two" type="password" class="form-control border-0 p-4" placeholder="Confirm Password" required="required" />
                                        <i class="fas fa-check-circle success"></i>
                                        <i class="fas fa-exclamation-circle error"></i>
                                        <small class="form-message"></small>
                                    </div>
                                    <div class="form-group">
                                        <div class="py-3" style="color: white;">Gender:   
                                            Male<input type="radio" style="margin-left: 0.2em" name="gender" value="male" required="required" />
                                            Female<input type="radio" style="margin-left: 0.2em" name="gender" value="female"/>
                                        </div>  
                                    </div>
                                    <div class="form-group">
                                        <input id="address" type="text" name="address" class="form-control border-0 p-4" placeholder="Your Adress" required="required" />
                                        <i class="fas fa-check-circle success"></i>
                                        <i class="fas fa-exclamation-circle error"></i>
                                        <small class="form-message"></small>
                                    </div>
                                    <div class="form-group">
                                        <input id="phone" type="text" name="phone" class="form-control border-0 p-4" placeholder="Your Phone" required="required" />
                                        <i class="fas fa-check-circle success"></i>
                                        <i class="fas fa-exclamation-circle error"></i>
                                        <small class="form-message"></small>
                                    </div>
                                    <div>
                                        <button class="btn btn-dark btn-block border-0 py-3" type="submit" onsubmit="event.preventDefault();myValidation();">Sign Up Now</button>
                                    </div>
                                    <div class="py-3 text-center" style="margin-top: 0.5em"><a style="color: #000;" href="client/login">You have an account? Log in</a></div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--     Footer Start -->
        <div class="container-fluid bg-dark text-white py-5 px-sm-3 px-lg-5">
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
                                <a class="text-white mb-2" href="#"><i class="fa fa-angle-right mr-2"></i>Research</a>
                                <a class="text-white" href="#"><i class="fa fa-angle-right mr-2"></i>SEO</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-md-12 mb-5">
                    <h5 class="text-primary text-uppercase mb-4" style="letter-spacing: 5px;">Newsletter</h5>
                    <p></p>
                    <div class="w-100">
                        <div class="input-group">
                            <input type="text" class="form-control border-light" style="padding: 30px;" placeholder="Your Email Address">
                            <div class="input-group-append">
                                <button class="btn btn-primary px-4"><a href="register">Sign Up</a></button>
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
        <!--     Footer End -->
        <!-- Registration End -->

        <script src="client/js/Register1.js"></script>
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
                                                        error.style.visibility = "visible";
                                                        success.style.visibility = "hidden";
                                                        let message = arr[i] + " is existed";
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


                                            Validator({
                                                form: '#form',
                                                rules: [
                                                    Validator.isName('#name'),
                                                    Validator.isEmail('#email'),
                                                    Validator.isUserName("#username"),
                                                    Validator.isPassWord('#password'),
                                                    Validator.confirmPassword('#password-two'),
                                                    Validator.isAddress('#address'),
                                                    Validator.isPhone('#phone')
                                                ]
                                            });
        </script>
    </body>
</html>
