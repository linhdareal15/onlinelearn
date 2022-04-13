<%@page import="java.sql.Date"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/edit.css" rel="stylesheet" type="text/css" />
<!-- Right side column. Contains the navbar and content of the page -->
<!-- Content Header (Page header) -->
<!-- Main content -->

<section class="content">
    <div class="row">
        <div class="col-md-12">
            <section class="panel">
                <header class="panel-heading">
                    <a href="subjectlist">
                        <span>Subject List</span>
                    </a>
                </header>
                <div class="panel-body">
                    <c:if test="${sessionScope.message != null}">
                        <div style="text-align: center">
                            <h1>${message}<i class="fa fa-check-square-o"></i></h1>
                        </div>
                    </c:if>
                    <c:set var="message" value="${null}"></c:set>
                        <form class="form-horizontal tasi-form" method="POST" action="editcoursesubject" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="col-sm-8">
                                    <input style="display: none" value="${course.cid}" name ="cid "type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Title</label>
                            <div class="col-sm-10">
                                <input name ="title" type="text" value="${course.title}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">ThumbNail</label>
                            <div class="col-sm-10">
                                <img src="${pageContext.servletContext.contextPath}/subject/img?id=${course.cid}" style="border-radius: 1rem" width="50%">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Upload Thumbnail</label>
                            <div class="col-sm-10">
                                <input type="file" name="thumbnail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Upload Document</label>
                            <div class="col-sm-10">
                                <input type="file" name="document" id="fileInput">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Document File</label>
                            <div class="col-sm-10">
                                <a href="${pageContext.servletContext.contextPath}/dowload/document?id=${course.cid}">${course.filename}</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Brief Info</label>
                            <div class="col-sm-10">
                                <textarea  name="brief" class="form-control round-input" style="height: 100px;color: black" required="required"> ${course.briefinfo}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Introduction</label>
                            <div class="col-sm-10">
                                <textarea id="introduction" name="introduction" class="form-control round-input" style="height: 100px;color: black" required="required">${course.introduction}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Author</label>
                            <div class="col-sm-10">
                                <select name="authors" required="required">
                                    <c:forEach items="${requestScope.authors}" var="i">
                                        <option value="${i.id}" ${i.id == course.author.id?"selected":""}>${i.fullname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Category</label>
                            <div class="col-sm-10">
                                <select name="categorys" required="required">
                                    <c:forEach items="${requestScope.categorys}" var="i">
                                        <option value="${i.id}">${i.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">List Price</label>
                            <div class="col-sm-10">
                                <input name ="listprice" type="text" class="form-control" value="${course.listprice}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Sale Price</label>
                            <div class="col-sm-10">
                                <input name ="saleprice" type="text" class="form-control" value="${course.saleprice}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                            <div class="col-sm-10">
                                <input type="radio" id="status" ${course.status ==  true?"checked":""} name="status" value="Publish" required="required">
                                <label for="html">Publish</label><br>
                                <input type="radio" id="status" ${course.status ==  false?"checked":""} name="status" value="Unpublish">
                                <label for="html">Unpublish</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Future Flag</label>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input type="checkbox" name="feature" value="ON" ${course.featureflag ==  true?"checked":""}/>
                                </div>
                            </div>
                        </div>
                        <button type="submit"  id="buu" class="btn btn-success btn-lg ">Edit Subject</button>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section><!-- /.content -->
<!-- Modal -->
<script src="//cdn.ckeditor.com/4.17.2/full/ckeditor.js"></script>

<script>
    CKEDITOR.replace('introduction');

    document.getElementById('fileInput').onchange = function () {
        alert('Selected file: ' + this.value);
    };
</script>



