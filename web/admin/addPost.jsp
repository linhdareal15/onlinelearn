<%-- 
    Document   : addPost
    Created on : Feb 27, 2022, 11:29:58 AM
    Author     : Duy Hiep
--%>

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <section class="panel">
                    <header class="panel-heading" style="display: flex; justify-content: space-between">
                        <div>ADD POST</div>
                        <a href="postlist">Post List</a>
                    </header>
                    <div class="panel-body">
                        <c:if test="${sessionScope.message != null}">
                            <div style="text-align: center">
                                <h1>${message}<i class="fa fa-check-square-o"></i></h1>
                            </div>
                        </c:if>
                        <c:set var="message" scope="session" value="${null}"></c:set>
                        <form class="form-horizontal tasi-form" method="Post" action="addpost" enctype="multipart/form-data">
                             <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Title(*)</label>
                                <div class="col-sm-8">
                                    <input  name ="title" type="text" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Author</label>
                                <div class="col-sm-8">
                                    <input  type="text" value="${sessionScope.user.fullname}" class="form-control" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Brief_Info(*)</label>
                                <div class="col-sm-8">
                                    <textarea  name="brief" class="form-control round-input" style="height: 100px;color: black" required="required"></textarea>
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Post Content</label>
                                <div class="col-sm-8">
                                    <textarea id="editor" name="content" class="form-control round-input" style="height: 200px;color: black" required="required">
                                        ${blogDetail.post_content}
                                    </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Category</label>
                                <div class="col-sm-8">
                                    <select name="category" required="required">
                                        <c:forEach items="${requestScope.category}" var="ca" >
                                            <option value="${ca.id}">${ca.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group" >
                                <label class="col-sm-2 col-sm-2 control-label">Upload Thumbnail </label>
                                <input type="file" class="col-sm-2" accept="image/*" name="thumbnail" id="imgInp">
                                <div class="col-sm-8">
                                    <img id="blah" onchange="PreviewImage();" src="" style="border-radius: 1rem" width="70%">
                                </div>
                                
                                    
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                <div class="col-sm-8">
                                    <input type="radio" id="status" name="status" value="Active" required="required">
                                    <label for="status">Active</label><br>
                                    <input type="radio" id="status" name="status" value="Unactive">
                                    <label for="status">Un Active</label><br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Feature</label>
                                <div class="col-sm-8">
                                    <input type="radio" id="feature" name="feature" value="On" required="required">
                                    <label for="html">On</label><br>
                                    <input type="radio" id="status" name="feature" value="Off">
                                    <label for="css">Off</label><br>
                                </div>
                            </div>
                            <div style="text-align: center"><button type="submit"  id="buu" class="btn btn-secondary btn-lg ">ADD POST</button></div>
                        </form>
                    </div>
                </section>
            </div>
        </div>
    </section><!-- /.content -->
    <script src="//cdn.ckeditor.com/4.17.2/full/ckeditor.js"></script>    
    <script>

        CKEDITOR.replace('editor');  
        let i = document.getElementById("editor");
        console.log(typeof(i.value));
    </script>
    <script>
        imgInp.onchange = evt => {
            const [file] = imgInp.files;
            if (file) {
              blah.src = URL.createObjectURL(file);
            }
          };

    </script>


           

