<%@page import="java.sql.Date"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/edit.css" rel="stylesheet" type="text/css" />
<!-- Right side column. Contains the navbar and content of the page -->
<!-- Content Header (Page header) -->
<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-md-12">
            <section class="panel">
                <header class="panel-heading">
                    <c:if test="${user != null}">
                        <a href="subjectlist">
                            <span>Subject List</span>
                        </a>
                    </c:if>

                </header>
                <div class="panel-body">
                    <c:if test="${message != null}">
                        <div style="text-align: center">
                            <h1>${message}<i class="fa fa-check-square-o"></i></h1>
                        </div>
                    </c:if>
                    <c:set var="message" value="${null}"></c:set>
                        <form class="form-horizontal tasi-form" method="POST" action="add-lesson" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Title</label>
                                <div class="col-sm-10">
                                    <input name ="title" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Type</label>
                                <div class="col-sm-10">
                                    <input type="radio" id="status" checked="checked" name="type" value="1" required="required">
                                    <label for="html">Lesson</label><br>
                                    <input type="radio" id="status" name="type" value="2">
                                    <label for="html">Topic</label><br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Belongtotopic</label>
                                <div class="col-sm-10">
                                    <select name="belongtotopic" required="required">
                                    <c:forEach items="${requestScope.topics}" var="t">
                                        <option value="${t.id}">${t.topic_title}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Order</label>
                            <div class="col-sm-10">
                                <input name ="order" type="number" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                            <div class="col-sm-10">
                                <input type="radio" id="status" checked="checked" name="status" value="active" required="required">
                                <label for="html">Active</label><br>
                                <input type="radio" id="status" name="status" value="deactive">
                                <label for="html">Deactive</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Video link</label>
                            <div class="col-sm-10">
                                <input name ="videolink" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Content</label>
                            <div class="col-sm-10">
                                <textarea id="content" name="content" class="form-control round-input" style="height: 100px;color: black" required="required"></textarea>
                            </div>
                        </div>
                        <div style="margin-left: 40%; margin-top: -30px; padding-bottom: 30px">
                            <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Add</button>
                            <a href="SubjectLesson"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section><!-- /.content -->
<!-- Modal -->
<script src="//cdn.ckeditor.com/4.17.2/full/ckeditor.js"></script>

<script>
    CKEDITOR.replace('content');
</script>



