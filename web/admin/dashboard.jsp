<%-- 
    Document   : dashboard
    Created on : Mar 9, 2022, 5:22:54 PM
    Author     : Duy Hiep
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*,java.sql.*" %>


<section class="content">
    <div class="row" style="margin-bottom:5px;">
        <div class="col-md-4">
            <div class="sm-st clearfix">
                <span class="sm-st-icon st-red"><i class="fa fa-users"></i></span>
                <div class="sm-st-info">
                    <span>${requestScope.totalCustomers}</span>
                    <b>Total Customers</b>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="sm-st clearfix">
                <span class="sm-st-icon st-violet"><i class="fa fa-book"></i></span>
                <div class="sm-st-info">
                    <span>${requestScope.totalCourses}</span>
                    <b>Total Courses</b>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="sm-st clearfix">
                <span class="sm-st-icon st-blue"><i class="fa fa-dollar"></i></span>
                <div class="sm-st-info">
                    <span>${requestScope.totalRevenues}</span>
                    <b>Total Revenues</b>
                </div>
            </div>
        </div>
    </div>

    <!-- Main row -->
    <div class="row">
        <div class="col-md-12">
            <div id="alert date" class="alert alert-block alert-danger" style="display: none">
                <button data-dismiss="alert" class="close close-sm" type="button">
                    <i class="fa fa-times"></i>
                </button>
                <div id="error message">
                    <strong>Error!</strong> Date is longer than now
                </div>
            </div>
            <form class="panel-heading" action="dashboard" method="" style="display: flex;justify-content: space-evenly;">
                <div>
                    Start Date: <input type="date" id="from" name="from" value="${from}" style="margin-right: 34rem">
                </div>
                <div>
                    End Date: <input type="date" id="to" name="to" value="${to}"/>
                    <i class="fa fa-filter" onclick="submitForm(this)"></i>
                </div>        
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <!--earning graph start-->
            <section class="panel"> 
                <header class="panel-heading">
                    Subject Chart - Type Area
                </header>
                <div class="panel-body">
                    <canvas id="subjectchart"  width="300" height="150"></canvas>
                </div>
            </section>
                        <!--earning graph end-->
        </div>
        <div class="col-md-6">
         <!--chat start-->
            <section class="panel">
                <header class="panel-heading">
                    New Registraions Chart - Type Pie
                </header>
                 <div class="panel-body">
                    <canvas id="registrationchart"  width="100" height="150"></canvas>
                </div>
            </section>
        </div>
     </div>
    <div class="row">
        <div class="col-md-6">
            <section class="panel">
                <header class="panel-heading">
                    Revenues Chart - Type Bar
                </header>
                 <div class="panel-body">
                    <canvas id="revenues chart"  width="400" height="200"></canvas>
                </div>
            </section>
        </div><!--end col-6 -->
        <div class="col-md-6">
            <section class="panel">
                <header class="panel-heading">
                    Customer Register Chart - Type Bar
                </header>
                 <div class="panel-body">
                    <canvas id="customers registration chart"  width="400" height="200"></canvas>
                </div>
            </section>

        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
           <section class="panel">
                <header class="panel-heading">
                    Order Trend By Day - Type Line
                </header>
                 <div class="panel-body">
                    <canvas id="order trend"  width="400" height="200"></canvas>
                </div>
             </section> 

        </div>
    </div>
    <!-- row end -->
    </section><!-- /.content -->
    <div class="footer-main">
        Copyright &copy DuyHiep, 2022
    </div>
    
    <script>
            //draw subject pie chart//
            let labels = [
                'New Subject',
                'All Subject'
              ];
              
            let data = {
            labels: labels,
            datasets: [{
              label: 'New Subject Chart',
              backgroundColor: ['#84DE02','#72A0C1'],
              borderColor: '#C9FFE5',
              data: ['${requestScope.newsubject}','${requestScope.totalCourses}']
            }]
            };
            
            let config = {
                type: 'polarArea',
                data: data,
                options: {  
                }
            };
        
            let myChart = new Chart(
                document.getElementById('subjectchart'),
                config
            );
            
            //draw registration pie chart//
            labels = [
              <c:forEach var="registration" items="${requestScope.newRegistration}">
                '${registration.key}',
                </c:forEach>
              ];
             
            data = {
            labels: labels,
            datasets: [{
              label: 'New Registration Chart',
              backgroundColor: ['#AF002A','#041A00','#F0F8FF','#84DE02','#EFDECD'],
              borderColor: '#C9FFE5',
              data: [<c:forEach var="registration" items="${requestScope.newRegistration}">
                        '${registration.value}',
                    </c:forEach>]
            }]
            };
            
            config = {
                type: 'pie',
                data: data
            };
            
            myChart = new Chart(
                document.getElementById('registrationchart'),
                config
            );
                    
            //draw customers registration chart
            labels = [
              <c:forEach var="c" items="${requestScope.newCustomerRegistration}">
                '${c.key}',
                </c:forEach>
              ];
             
            data = {
            labels: labels,
            datasets: [{
              label: 'Customers Registration',
              backgroundColor: ['#003f5c','#58508d','#bc5090','#ff6361','#ffa600'],
              borderColor: '#C9FFE5',
              data: [<c:forEach var="c" items="${requestScope.newCustomerRegistration}">
                        '${c.value}',
                    </c:forEach>]
            }]
            };
            
            config = {
                type: 'bar',
                data: data
            };
            
            myChart = new Chart(
                document.getElementById('customers registration chart'),
                config
            );
            
            //draw order trend
            
            
    </script>
    <script>
        
        function submitForm(e){
            const to = new Date(document.getElementById('to').value.toString());
            const from = new Date(document.getElementById('from').value.toString());
            const different = (to - from)/(1000 *3600*24);
            console.log(different);
            let alert = document.getElementById('alert date');
            if(different >= 0 && different <= 31){
                alert.style.display = "none";
                let form = e.parentElement.parentElement;
                form.submit();
                return true;
            }
            else if(different > 31){
                alert.style.display = "block";
                let message = document.getElementById('error message');
                message.innerHTML = "Data is valid for range in 31 days";
                return false;
            }
            else{
                alert.style.display = "block";
                console.log(different);
                return false;
            }
            return true;
        }
        
        var xValues = [<c:forEach var="a" items="${requestScope.allOrder}">
                '${a.key}',
              </c:forEach> ];

        new Chart(document.getElementById("order trend"), {
            type: "line",
            data: {
                labels: xValues,
                datasets: [
                    {
                        fill: false,
                        label: 'Success Order',
                        lineTension: 0,
                        backgroundColor: '#84DE02',
                        borderColor: '#84DE02',
                        data: [<c:forEach var="s" items="${requestScope.successOrder}">
                            '${s.value}',
                        </c:forEach>]
                        },
                    {
                        fill: false,
                        label: 'All Order',
                        lineTension: 0,
                        backgroundColor: '#AF002A',
                        borderColor: '#AF002A', 
                        data: [<c:forEach var="a" items="${requestScope.allOrder}">
                            '${a.value}',
                          </c:forEach>]
                    }
                ]
            },
            options: {
                plugins: {
                    title: {
                        display: true,
                        text: 'Order Trend By Days',
                        beginAtZero: true
                    }
                },
                scales: {
                    xAxes: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Orders Count',
                            beginAtZero: true
                        },
                        ticks: {
                            beginAtZero: true
                        }
                    },
                    yAxes: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Days',
                        },
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
    <script>
            const ctx = document.getElementById('revenues chart');

            const chart = new Chart(ctx, {
              type: 'bar',
              data: {
                labels: [
                   <c:forEach var="revenues" items="${requestScope.revenuesByCourseCategory}">
                    '${revenues.key}',
                   </c:forEach>
                ],
                datasets: [{
                    label: 'Devlopment',
                    data: [
                        <c:forEach var="d" items="${requestScope.Development}">
                        '${d}',
                    </c:forEach>    
                    ],
                    borderWidth: 1,
                    backgroundColor: [<c:forEach var="d" items="${requestScope.Development}">
                        '#003f5c',
                    </c:forEach>]
                  },
                  {
                    label: 'Marketing',
                     data: [<c:forEach var="m" items="${requestScope.Marketing}">
                        '${m}',
                    </c:forEach>],
                    borderWidth: 1,
                    backgroundColor: [<c:forEach var="m" items="${requestScope.Marketing}">
                        "#58508d",
                    </c:forEach>]
                  },
                  {
                    label: 'Programing Language',
                     data: [<c:forEach var="p" items="${requestScope.Program}">
                        '${p}',
                    </c:forEach>],
                    borderWidth: 1,
                    backgroundColor: [<c:forEach var="m" items="${requestScope.Program}">
                        "#bc5090",
                    </c:forEach>]
                  },
                  {
                    label: 'Web Design',
                     data: [<c:forEach var="m" items="${requestScope.Desgin}">
                        '${m}',
                    </c:forEach>],
                    borderWidth: 1,
                    backgroundColor: [<c:forEach var="m" items="${requestScope.Desgin}">
                        "#ffa600",
                    </c:forEach>]
                  },
                  {
                    label: 'SEO',
                     data: [<c:forEach var="seo" items="${requestScope.Seo}">
                        '${seo}',
                    </c:forEach>],
                    borderWidth: 1,
                    backgroundColor: [<c:forEach var="m" items="${requestScope.Seo}">
                        "#ff6361",
                    </c:forEach>]
                  }
                ]
              },
              options: {
                scales: {
                  yAxes: [{
                    ticks: {
                      beginAtZero: true
                    }
                  }]
                }
              }
            });                
    </script>
    

    