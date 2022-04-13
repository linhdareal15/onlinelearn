<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<link href="css/edit.css" rel="stylesheet" type="text/css" />
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <!-- Main content -->
    <section class="content">

        <div class="row">
            <div class="col-md-12">
                <section class="panel">
                    <header class="panel-heading">
                        Add Slider
                    </header>
                    <div class="panel-body">

                        <form class="form-horizontal tasi-form" method="post" action="add-slider">

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Title</label>
                                <div class="col-sm-10">
                                    <input name ="title"type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Image</label>
                                <div class="col-sm-10">
                                    <input name ="image"type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Backlink</label>
                                <div class="col-sm-10">
                                    <input name ="backlink"type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                <div class="col-sm-10">
                                    <input type="radio" id="status" name="status" value="1" checked>
                                    <label for="html">Show</label><br>
                                    <input type="radio" id="status" name="status" value="0" >
                                    <label for="css">Hide</label><br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Note</label>
                                <div class="col-sm-10">
                                    <input name ="note"type="text" class="form-control">
                                </div>
                            </div>
                            <div style="margin-left: 40%; margin-top: -30px; padding-bottom: 30px">
                                <button style="float: left" type="submit"  id="buu" class="btn btn-success btn-lg ">Add</button>
                                <a href="slider"><div style="float: left; margin-left: 5px" id="buu" class="btn btn-danger btn-lg ">Cancel</div></a>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section><!-- /.content -->
</aside><!-- /.right-side -->



