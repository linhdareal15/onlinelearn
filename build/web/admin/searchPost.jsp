<%-- 
    Document   : settingsList
    Created on : Jan 15, 2022, 9:58:00 AM
    Author     : Quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <script type="text/javascript">
        function submitForm(e){
            let form = e.parentElement;
            form.submit();
        }
        function editPost(id)
        {
            window.location.href = "editpost?postid=" + id;
        }
        function changeStatusFeature(id, items, property,keyword)
        {
           alert("Want to change this property?");
            window.location.href = "../post/change?id=" + id + "&&items=" + items + "&&property=" + property + "&&index=" + ${requestScope.current_index} + "&&keyword=" + keyword
            +"&&status=" + ${requestScope.status} + "&&category=" + ${requestScope.category} + "&&author=" + '${requestScope.author}' + "&&sort=" + '${requestScope.sort}';
        }
    </script>
    
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="panel">
                    <header class="panel-heading">
                        <div class="panel-heading" style="display: flex; justify-content : space-between">
                            <div>Search Result With Keyword: "${keyword}"</div>
                            <div><a href="postlist">Post List</a></div>
                        </div>
                    </header>
                    <div class="panel-body table-responsive" >
                        <div class="box-tools m-b-15" style="display: flex; justify-content: space-between">

                            <form method="" id="filter" action="searchpost">
                                <input style="display: none" name="keyword" value="${keyword}">
                                Status  <select name="status" class="input-sm" >
                                <option value="-1" <c:if test="${requestScope.status eq -1}">
                                                selected="selected"
                                            </c:if>>All</option>
                                <option value="1" <c:if test="${requestScope.status eq 1}">
                                                selected="selected"
                                            </c:if>>Active</option>
                                <option value="0" <c:if test="${requestScope.status eq 0}">
                                                selected="selected"
                                            </c:if>>Inactive</option>
                                </select>
                                Category <select name="category" class="input-sm" >
                                    <option value="-1">All</option>
                                    <c:forEach items="${requestScope.blcategory}" var="bl">
                                        <option value="${bl.id}" 
                                            <c:if test="${requestScope.category eq bl.id}">
                                                selected="selected"
                                            </c:if>>${bl.name}</option>
                                    </c:forEach>                                  
                                </select>
                                Author <select name="author" class="input-sm" >
                                        <option value="-1">All</option>
                                        <c:forEach items="${requestScope.authors}" var="au">
                                             <option value="${au.id}"
                                                <c:if test="${requestScope.author eq au.id}">
                                                    selected="selected"
                                                </c:if>>${au.fullname}</option>
                                        </c:forEach>
                                </select>   

                                Sort By:<select name="sort" class="input-sm">
                                    <option value="title" <c:if test="${requestScope.sort eq 'title'}">
                                                selected="selected"
                                            </c:if>>Title</option>
                                    <option value="category" <c:if test="${requestScope.sort eq 'category'}">
                                                selected="selected"
                                            </c:if>>Category</option>
                                    <option value="author" <c:if test="${requestScope.sort eq 'author'}">
                                                selected="selected"
                                            </c:if>>Author</option>
                                    <option value="status" <c:if test="${requestScope.sort eq 'status'}">
                                                selected="selected"
                                            </c:if>>Status</option>
                                    <option value="feature" <c:if test="${requestScope.sort eq 'feature'}">
                                                    selected="selected"
                                                </c:if>>Feature</option>
                                </select>
                                <i class="fa fa-filter" onclick="submitForm(this)"></i>
                            </form>


                            <form action="" method="" >
                                <div class="search">
                                    <input type="text" name="keyword" placeholder="Enter title.."/>
                                    <input type="submit" value="Search"/>
                                </div>
                            </form>
                        </div>
                        <table class="table table-hover" style="table-layout: fixed;width: 100%">
                            <tr >
                                <th style="text-align : center">ID</th>
                                <th style="text-align : center">Thumbnail</th>
                                <th style="text-align : center">Title</th>
                                <th style="text-align : center">Category</th>
                                <th style="text-align : center">Author</th>
                                <th style="text-align : center">Status</th>
                                <th style="text-align : center">Feature</th>
                            </tr>
                            <c:forEach items="${requestScope.searchBlogList}" var="bl">
                            <tr>
                                <td style="text-align: center">${bl.id}</td>
                                <td onclick="editPost('${bl.id}')"><img src="${bl.thumbnail}" width="300px" style="border-radius: 1rem"></td>
                                <td style="text-align: center"><a onclick="editPost('${bl.id}')">${bl.title}</a></td>
                                <td style="text-align: center">${bl.category_id.name}</td>
                                <td style="text-align: center">${bl.blogdetail.author.fullname}</td>        
                                <td style="text-align : center">
                                    <c:if test="${bl.status eq true}">
                                        <button style="background-color: green;color: white" onclick="changeStatusFeature(${bl.id},'status',1,'${requestScope.keyword}')">Active</button>
                                    </c:if>
                                    <c:if test="${bl.status eq false}">
                                        <button style="background-color: red;color: white" onclick="changeStatusFeature(${bl.id},'status',0,'${requestScope.keyword}')">In Active</button>
                                    </c:if>
                                </td>
                                <td style="text-align : center">
                                    <c:if test="${bl.feature eq true}">
                                        <div onclick="changeStatusFeature(${bl.id},'feature',1,'${requestScope.keyword}')">
                                            <i class="fa fa-flag" style="font-size: 2.5rem;color: green"></i>
                                            <p>(On)</p>
                                        </div>
                                    </c:if>
                                    <c:if test="${bl.feature eq false}">
                                        <div onclick="changeStatusFeature(${bl.id},'feature',0,'${requestScope.keyword}')">
                                            <i class="fa fa-flag" style="font-size: 2.5rem"></i>
                                            <p>(Off)</p>
                                        </div>
                                    </c:if>
                                </td>

                            </tr>
                            </c:forEach> 
                        </table>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div>
        <div class="text-center">
            <ul class="pagination">
                <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                    <li class="page-item"><a class="page-link" href="?index=0&&keyword=${requestScope.keyword}&&status=${requestScope.status}&&category=${requestScope.category}&&author=${requestScope.author}&&sort=${requestScope.sort}">First</a></li>
                </c:if>
                <c:if test="${requestScope.current_index != 0}">
                  <li class="page-item">
                      <a class="page-link" href="?index=${requestScope.current_index - 1}&&keyword=${requestScope.keyword}&&status=${requestScope.status}&&category=${requestScope.category}&&author=${requestScope.author}&&sort=${requestScope.sort}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                      <span class="sr-only">Previous</span>
                    </a>
                  </li> 
                </c:if>

                <c:forEach begin="${requestScope.current_index + 1}" end="${requestScope.current_index + 4}"  var="i">   
                    <c:if test="${i < requestScope.endPage}">

                        <li class="${requestScope.current_index == i - 1 ? "page-item active":"page-item"}"><a class="page-link" href="?index=${i-1}&&keyword=${requestScope.keyword}&&status=${requestScope.status}&&category=${requestScope.category}&&author=${requestScope.author}&&sort=${requestScope.sort}">${i}</a></li>                                             
                    </c:if>             
                </c:forEach>
                <c:if test="${requestScope.current_index != requestScope.endPage - 1}">
                  <li class="page-item">
                  <a class="page-link" href="?index=${requestScope.current_index + 1}&&keyword=${requestScope.keyword}&&status=${requestScope.status}&&category=${requestScope.category}&&author=${requestScope.author}&&sort=${requestScope.sort}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                  </a>
                  </li>
                </c:if>

                <c:if test="${requestScope.current_index + 1 > 1 && requestScope.current_index + 1 < requestScope.endPage}">
                     <li class="page-item"><a class="page-link" href="?index=${requestScope.endPage - 1}&&keyword=${requestScope.keyword}&&status=${requestScope.status}&&category=${requestScope.category}&&author=${requestScope.author}&&sort=${requestScope.sort}">Last</a></li>
                </c:if>
            </ul>
        </div>
    </section><!-- /.content -->
            
