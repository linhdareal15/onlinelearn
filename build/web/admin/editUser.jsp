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
                    Edit User
                </header>
                <div class="panel-body">
                    <form class="form-horizontal tasi-form" method="POST" action="edit-user" enctype="multipart/form-data">
                        <input value="${u.id}" name="id" hidden="">
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Username</label>
                            <div class="col-sm-10">
                                <input value="${u.username}" name="username" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Full name</label>
                            <div class="col-sm-10">
                                <input value="${u.fullname}" name ="fullname" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Gender</label>
                            <div class="col-sm-10">
                                <input type="radio" id="gender" name="gender" value="male"
                                       ${u.gender == true ? "checked=\"checked\"" : ""}>
                                <label for="html">Male</label><br>
                                <input type="radio" id="gender" name="gender" value="female"
                                       ${u.gender == false ? "checked=\"checked\"" : ""}>
                                <label for="css">Female</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Address</label>
                            <div class="col-sm-10">
                                <input value="${u.address}" name ="address" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Email</label>
                            <div class="col-sm-10">
                                <input value="${u.email}" name ="email" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Avatar</label>
                            <div class="col-sm-10">
                                <img src="${pageContext.servletContext.contextPath }/admin/user-avatar?id=${u.id}" style="border-radius: 1rem" width="50%">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Avatar</label>
                            <div class="col-sm-10">
                                <input name ="avatar" type="file" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Phone number</label>
                            <div class="col-sm-10">
                                <input value="${u.phonenumber}" name ="phonenumber" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                            <div class="col-sm-10">
                                <input type="radio" id="status" name="status" value="2"
                                       ${u.status == 2 ? "checked=\"checked\"" : ""}>
                                <label for="html">Active</label><br>
                                <input type="radio" id="status" name="status" value="1"
                                       ${u.status == 1 ? "checked=\"checked\"" : ""}>
                                <label for="css">Deactive</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Role</label>
                            <div class="col-sm-10">
                                <select name="role_id">
                                    <c:forEach items="${roles}" var="r" >
                                        <option
                                            <c:if test="${r.role_id eq u.role.role_id}">
                                                selected="selected"
                                            </c:if>
                                            value="${r.role_id}">${r.role_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div style="margin-left: 40%; margin-top: -30px; padding-bottom: 30px">
                            <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Update</button>
                            <a href="user"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section><!-- /.content -->
