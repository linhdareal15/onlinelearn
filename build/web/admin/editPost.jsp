<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <section class="panel">
                    <header class="panel-heading" style="display: flex; justify-content: space-between">
                        <div>Edit Post</div>
                        <a href="addpost">Add Post</a>
                    </header>
                    <div class="panel-body">
                        <c:if test="${sessionScope.message != null}">
                            <div style="text-align: center">
                                <h1>${message}<i class="fa fa-check-square-o"></i></h1>
                            </div>
                        </c:if>
                        <c:set var="message" scope="session" value="${null}"></c:set>
                        <form class="form-horizontal tasi-form" method="post" action="editpost" enctype="multipart/form-data">
                            <div class="form-group">
                                <div class="col-sm-8">
                                    <input style="display: none" value="${blogDetail.id}" name ="bd_id"type="text" class="form-control">
                                </div>
                            </div>
                                <div class="form-group">
                                <div class="col-sm-8">
                                    <input style="display: none" value="${blogDetail.bloglist_id.id}" name ="bl_id"type="text" class="form-control">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Title</label>
                                <div class="col-sm-8">
                                    <input  value="${blogDetail.bloglist_id.title}" name ="title" type="text" class="form-control" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Author</label>
                                <div class="col-sm-8">
                                    <input value="${blogDetail.author.fullname}" type="text" class="form-control" disabled="disabled">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Brief_Info</label>
                                <div class="col-sm-8">
                                    <textarea  name="brief" class="form-control round-input" style="height: 100px;color: black" required="required">${blogDetail.bloglist_id.brief_info}</textarea>
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
                                        <c:forEach items="${category}" var="ca" >
                                            <option value="${ca.id}" 
                                                    <c:if test="${ca.id eq blogDetail.bloglist_id.category_id.id}">selected="selected"</c:if>  
                                            >${ca.name} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">ThumbNail</label>
                                <div class="col-sm-10">
                                    <img id="blah" onchange="PreviewImage();" src="${blogDetail.bloglist_id.thumbnail}" style="border-radius: 1rem" width="50%">
                                </div>
                                <label class="col-sm-2 col-sm-2 control-label">Upload Thumbnail</label>
                                <input type="file" accept="image/*" name="thumbnail" id="imgInp">
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Status</label>
                                <div class="col-sm-8">
                                    <input type="radio" id="status" name="status" value="Active" ${blogDetail.bloglist_id.status==true?"checked":""} required="required">
                                    <label for="html">Active</label><br>
                                    <input type="radio" id="status" name="status" value="Unactive" ${blogDetail.bloglist_id.status==false?"checked":""} >
                                    <label for="css">Un Active</label><br>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Feature</label>
                                <div class="col-sm-8">
                                    <input type="radio" id="feature" name="feature" value="On" ${blogDetail.bloglist_id.feature==true?"checked":""} required="required">
                                    <label for="html">On</label><br>
                                    <input type="radio" id="status" name="feature" value="Off" ${blogDetail.bloglist_id.feature==false?"checked":""}>
                                    <label for="css">Off</label><br>
                                </div>
                            </div>
                            <div style="text-align: center"><button type="submit" class="btn btn-secondary btn-lg ">EDIT POST</button></div>                          
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


    