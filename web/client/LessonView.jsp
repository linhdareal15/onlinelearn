<%@page import="Dao.LessonDAO"%>
<%@page import="java.util.Map"%>
<%@page import="Model.Topic"%>
<%@page import="Model.Lesson"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <title> ${requestScope.title_value} </title>
        <!-- Boxiocns CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <!--<div style="display: flex">-->
            <div class="sidebar open">
                <div class="logo-details">
                    <i class='bx bxs-book-content' ></i>
                    <span class="logo_name">Course Content</span>
                </div>
                <ul class="nav-links">
                    <li>
                        <ul class="sub-menu blank">
                            <li><a class="link_name" href="#">Category</a></li>
                        </ul>
                    </li>
                    <%
                        List<Topic> listTopic = (List) request.getAttribute("ListTopic");
                        LessonDAO dao = new LessonDAO();
                        for (Topic topic : listTopic) {
                            List<Lesson> val = dao.getLessonByTopicId(topic.getId());
                    %>
                    <li>
                        <div class="iocn-link">
                            <a href="#">
                                <i class='bx bx-book-alt' ></i>
                                <span class="link_name"><%=topic.getTopic_title()%></span>
                            </a>
                            <i class='bx bxs-chevron-down arrow' ></i>
                        </div>
                        <ul class="sub-menu">
                            <%
                                for (Lesson lesson : val) {
                            %>
                            <script>
                            var param_<%=lesson.getLesson_id()%> = "Lesson-<%=lesson.getLesson_id()%>"
                            </script>    
                            <li><a href="#" onclick="loadcontent(param_<%=lesson.getLesson_id()%>)" ><%=lesson.getTitle()%></a></li>
                                <%}%>
                            <%
                                if(topic.getQuiz_id() != 0){
                            %>
                                <li><a href="quiz?id=<%=topic.getQuiz_id()%>">Quiz</a></li>
                            <%}%>
                        </ul>
                    </li>    
                    <%}%>
                    <li>
                        <div class="profile-details">
                            <div class="profile-content">

                            </div>
                            <div class="name-job">
                                <div class="profile_name"><a href="course?action=detail&id=${requestScope.course_id}" style="text-decoration: none; color: white">Return</a></div>
                            </div>
                            <a href="course?action=detail&id=${requestScope.course_id}" style="text-decoration: none; color: white">  <i class='bx bx-log-out' ></i></a>
                        </div>
                    </li>
                </ul>
            </div>
            <section class="home-section">
                <div class="home-content">
                    <i class='bx bx-menu' ></i>
                    <span class="text"></span>
                </div>
                <div class="">
                    <div class="pt-5" id="content">
                        <center>
                            <iframe width="860" height="515" src="https://www.youtube.com/embed/dLQe4qEfVJw" 
                                    title="YouTube video player" frameborder="4" 
                                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
                                    allowfullscreen>
                            </iframe>
                        </center>
                    </div>
                    <br>
                    <hr>
                    <!--<center><h3>Content</h3></center>-->
                    <div class="">
                        <nav class="navbar navbar-expand-lg navbar-light">
                            <div class="collapse navbar-collapse" id="navbarNav">
                                <ul class="navbar-nav">
                                    <li class="nav-item active">
                                        <a class="nav-link active" href="#">Overview</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Features</a>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                        <div class="overview" style="padding-left: 2%;width: 50%"> 
                            <p class="h2 py-3">About this course</p>
                            <p>Learn to program using the Java programming language</p>
                        </div>
                        <hr>
                        <dl class="row" style="padding-left: 2%;width: 90%">
                            <dt class="col-sm-2">Description</dt>
                            <dd class="col-sm-9">Learn to program in the Java programming language. This course assumes no prior programming knowledge, just a desire to learn to program.
                            </dd>
                        </dl>
                        <dl class="row" style="padding-left: 2%;width: 90%">
                            <dt class="col-sm-2">Instructor</dt>
                            <dd class="col-sm-9">Learn to program in the Java programming language. This course assumes no prior programming knowledge, just a desire to learn to program.
                            </dd>
                        </dl>
                    </div>
                </div>
            </section>
        <!--</div>-->
    </body>
    <script>
        let arrow = document.querySelectorAll(".arrow");
        for (var i = 0; i < arrow.length; i++) {
            arrow[i].addEventListener("click", (e) => {
                let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
                arrowParent.classList.toggle("showMenu");
            });
        }

        let sidebar = document.querySelector(".sidebar");
        let sidebarBtn = document.querySelector(".bx-menu");
        console.log(sidebarBtn);
        sidebarBtn.addEventListener("click", () => {
            sidebar.classList.toggle("close");
        });
    </script>
    <script>
        function loadcontent(param1) {
            var row = document.getElementById("content");
            row.innerHTML = "";
            console.log(param1)
            $.ajax({
                url: "/g4/loadlesson",
                type: "GET",
                data: {
                    type: param1
                },
                //contentType: 'application/json; charset=utf-8',
                success: function (data) {
                    row.innerHTML += data;
                },
                error: function () {
                    alert("error");
                }
            });
        }



    </script>
    <style>
        /* Google Fonts Import Link */
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }
        .sidebar{
            position: fixed;
            top: 0;
            right: 0;
            height: 100%;
            width: 260px;
            background: #11101d;
            z-index: 100;
            transition: all 0.5s ease;
        }
        .sidebar.close{
            width: 78px;
        }
        .sidebar .logo-details{
            height: 60px;
            width: 100%;
            display: flex;
            align-items: center;
        }
        .sidebar .logo-details i{
            font-size: 30px;
            color: #fff;
            height: 50px;
            min-width: 78px;
            text-align: center;
            line-height: 50px;
        }
        .sidebar .logo-details .logo_name{
            font-size: 22px;
            color: #fff;
            font-weight: 600;
            transition: 0.3s ease;
            transition-delay: 0.1s;
        }
        .sidebar.close .logo-details .logo_name{
            transition-delay: 0s;
            opacity: 0;
            pointer-events: none;
        }
        .sidebar .nav-links{
            height: 100%;
            padding: 30px 0 150px 0;
            overflow: auto;
        }
        .sidebar.close .nav-links{
            overflow: visible;
        }
        .sidebar .nav-links::-webkit-scrollbar{
            display: none;
        }
        .sidebar .nav-links li{
            position: relative;
            list-style: none;
            transition: all 0.4s ease;
        }
        .sidebar .nav-links li:hover{
            background: #1d1b31;
        }
        .sidebar .nav-links li .iocn-link{
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .sidebar.close .nav-links li .iocn-link{
            display: block
        }
        .sidebar .nav-links li i{
            height: 50px;
            min-width: 78px;
            text-align: center;
            line-height: 50px;
            color: #fff;
            font-size: 20px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        .sidebar .nav-links li.showMenu i.arrow{
            transform: rotate(-180deg);
        }
        .sidebar.close .nav-links i.arrow{
            display: none;
        }
        .sidebar .nav-links li a{
            display: flex;
            align-items: center;
            text-decoration: none;
        }
        .sidebar .nav-links li a .link_name{
            font-size: 13px;
            font-weight: 400;
            color: #fff;
            transition: all 0.4s ease;
        }
        .sidebar.close .nav-links li a .link_name{
            opacity: 0;
            pointer-events: none;
        }
        .sidebar .nav-links li .sub-menu{
            padding: 6px 6px 14px 80px;
            margin-top: -10px;
            background: #1d1b31;
            display: none;
        }
        .sidebar .nav-links li.showMenu .sub-menu{
            display: block;
        }
        .sidebar .nav-links li .sub-menu a{
            color: #fff;
            font-size: 15px;
            padding: 5px 0;
            white-space: nowrap;
            opacity: 0.6;
            transition: all 0.3s ease;
        }
        .sidebar .nav-links li .sub-menu a:hover{
            opacity: 1;
        }
        .sidebar.close .nav-links li .sub-menu{
            position: absolute;
            left: 100%;
            top: -10px;
            margin-top: 0;
            padding: 10px 20px;
            border-radius: 0 6px 6px 0;
            opacity: 0;
            display: block;
            pointer-events: none;
            transition: 0s;
        }
        .sidebar.close .nav-links li:hover .sub-menu{
            top: 0;
            opacity: 1;
            pointer-events: auto;
            transition: all 0.4s ease;
        }
        .sidebar .nav-links li .sub-menu .link_name{
            display: none;
        }
        .sidebar.close .nav-links li .sub-menu .link_name{
            font-size: 18px;
            opacity: 1;
            display: block;
        }
        .sidebar .nav-links li .sub-menu.blank{
            opacity: 1;
            pointer-events: auto;
            padding: 3px 20px 6px 16px;
            opacity: 0;
            pointer-events: none;
        }
        .sidebar .nav-links li:hover .sub-menu.blank{
            top: 50%;
            transform: translateY(-50%);
        }
        .sidebar .profile-details{
            position: fixed;
            bottom: 0;
            width: 260px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            background: #1d1b31;
            padding: 12px 0;
            transition: all 0.5s ease;
        }
        .sidebar.close .profile-details{
            background: none;
        }
        .sidebar.close .profile-details{
            width: 78px;
        }
        .sidebar .profile-details .profile-content{
            display: flex;
            align-items: center;
        }
        .sidebar .profile-details img{
            height: 52px;
            width: 52px;
            object-fit: cover;
            border-radius: 16px;
            margin: 0 14px 0 12px;
            background: #1d1b31;
            transition: all 0.5s ease;
        }
        .sidebar.close .profile-details img{
            padding: 10px;
        }
        .sidebar .profile-details .profile_name,
        .sidebar .profile-details .job{
            color: #fff;
            font-size: 18px;
            font-weight: 500;
            white-space: nowrap;
        }
        .sidebar.close .profile-details i,
        .sidebar.close .profile-details .profile_name,
        .sidebar.close .profile-details .job{
            display: none;
        }
        .sidebar .profile-details .job{
            font-size: 12px;
        }
        .home-section{
            position: relative;
            background: #E4E9F7;
            min-height: 120vh;
            right: 0px;
            width: calc(100% - 100px);
            transition: all 0.5s ease;
        }
        .navbar{
            background-color: : #E4E9F7;
            left: 20px;
            width: calc(100% - 700px);
        }
        .sidebar.close ~ .home-section{
            left: 70px;
            width: calc(100% - 70px);
        }
        .home-section .home-content{
            height: 60px;
            display: flex;
            align-items: center;
        }
        .home-section .home-content .bx-menu,
        .home-section .home-content .text{
            color: #11101d;
            font-size: 35px;
        }
        .home-section .home-content .bx-menu{
            margin: 0 15px;
            cursor: pointer;
        }
        .home-section .home-content .text{
            font-size: 26px;
            font-weight: 600;
        }
        @media (max-width: 420px) {
            .sidebar.close .nav-links li .sub-menu{
                display: none;
            }
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</html>
