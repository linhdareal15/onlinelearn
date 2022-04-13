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
                    Form Elements
                </header>
                <div class="panel-body">

                    <form class="form-horizontal tasi-form" method="post" action="add">

                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Name</label>
                            <div class="col-sm-10">
                                <input name ="name"type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Description</label>
                            <div class="col-sm-10">
                                <textarea name="description" class="form-control round-input" style="height: 300px"></textarea>
                            </div>
                        </div>
                        <!--<div class="form-group">
                           <label class="col-sm-2 col-sm-2 control-label">Type</label>
                           <div class="col-sm-10">
                           <input name ="type"type="text" class="form-control">
                           </div>
                           </div>-->
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Type</label>
                            <div class="col-sm-10">
                                <select name="type">
                                    <c:forEach items="${ltype}" var="t" >
                                        <option value="${t.getSetting_type_id()}" ${t.getSetting_type_id()==1?"selected":""} >${t.getSetting_type_name()} </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Value</label>
                            <div class="col-sm-10">
                                <input name ="value"type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-sm-2 control-label">Status</label>
                            <div class="col-sm-10">
                                <input type="radio" id="status" name="status" value="1" checked>
                                <label for="html">Active</label><br>
                                <input type="radio" id="status" name="status" value="2" >
                                <label for="css">Deactive</label><br>
                            </div>
                        </div>
                        <button type="submit"  id="buu" class="btn btn-secondary btn-lg ">ADD COMPLETE</button>
                    </form>
                </div>
            </section>
        </div>
    </div>
</section><!-- /.content -->




