<%-- 
    Document   : settingsList
    Created on : Jan 15, 2022, 9:58:00 AM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <script type="text/javascript">
        function searchStatus(status)
        {
            window.location.href = "slider?status=" + status;
        }
        function changeStatus(id, status)
        {
            window.location.href = "slider?idChange=" + id + "&&statusChange=" + status;
        }
        function submitForm(e){
                let form = e.parentElement;
                form.submit();
            }
    </script>

        <!-- header logo: style can be found in header.less -->



        <!-- Right side column. Contains the navbar and content of the page -->

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="panel">
                        <header class="panel-heading">
                            <div class="panel-heading" style="display: flex;">
                                <div style="margin-right: 74%;"> Sliders  List</div>
                                <div>
                                    <a href="add-slider" style="margin-left: 90px">Add Slider</a>
                                </div>
                            </div>
                        </header>
                        <!-- <div class="box-header"> -->
                        <!-- <h3 class="box-title">Responsive Hover Table</h3> -->
                        <!-- </div> -->
                        <div class="panel-body table-responsive">
                            <div class="box-tools m-b-15" style="display: flex;">
                                <form action="slider" method="GET">
                                    Status <select name="status" onchange="searchStatus(status.value);" class="input-sm">
                                        <option value="-1" ${getStatus == -1 ? "selected" : ""}>All</option>
                                        <option value="1" ${getStatus == 1 ? "selected" : ""}>Show</option>
                                        <option value="0" ${getStatus == 0 ? "selected" : ""}>Hide</option>
                                    </select>
                                    <input style="margin-left: 710px" type="text" name="keyword" value="${getKeyword}" placeholder="Enter title.."/>
                                    <input type="submit" value="Search"/>
                                </form>
                            </div>
                            <table class="table table-hover">
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Image</th>
                                    <th>Backlink</th>
                                    <th>Status</th>
                                    <th> </th>
                                </tr>
                                <c:forEach items="${requestScope.searchStatus}" var="s">
                                    <tr>
                                        <td>${s.id}</td>
                                        <td>${s.title}</td>
                                        <td><img src="${s.image}" width="200px"></td>
                                        <td><a href="${s.backlink}">${s.backlink}</a></td>
                                        <td>
                                            <button type="button" onclick="changeStatus(${s.id}, ${s.status})" class="label 
                                                    <c:if test="${s.status eq '0'}">
                                                        label-danger
                                                    </c:if>
                                                    <c:if test="${s.status eq '1'}">
                                                        label-success 
                                                    </c:if>
                                                    ">
                                                <c:if test="${s.status eq '0'}">
                                                    Hide
                                                </c:if>
                                                <c:if test="${s.status eq '1'}">
                                                    Show
                                                </c:if>
                                            </button>
                                        </td>
                                        <td><a href="edit-slider?id=${s.id}"><input type="submit" value="Edit" /></a></td>
                                    </tr>
                                </c:forEach>                                
                            </table>
                        </div><!-- /.box-body -->
                    </div><!-- /.box -->
                </div>
            </div>
            <div class="text-center">
                <ul class="pagination">
                    <li><a href="slider?index=${tag-1}">«</a></li>
                        <c:forEach begin="1" end="${endPage}" var="i">
                        <li><a href="slider">${i}</a></li>
                        </c:forEach>
                    <li><a href="slider?index=${tag+1}">»</a></li>
                </ul>
            </div>
        </section><!-- /.content -->


