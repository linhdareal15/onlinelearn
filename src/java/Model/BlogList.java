/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author Duy Hiep
 */
public class BlogList {
    private int id;
    private String title;
    private BlogCategory category_id;
    private Date postdate;
    private String brief_info;
    private String thumbnail;
    private boolean feature;
    private boolean  status;
    private BlogDetail blogdetail;


    public BlogDetail getBlogdetail() {
        return blogdetail;
    }

    public void setBlogdetail(BlogDetail blogdetail) {
        this.blogdetail = blogdetail;
    }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogCategory getCategory_id() {
        return category_id;
    }

    public void setCategory_id(BlogCategory category_id) {
        this.category_id = category_id;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public String getBrief_info() {
        return brief_info;
    }

    public void setBrief_info(String brief_info) {
        this.brief_info = brief_info;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isFeature() {
        return feature;
    }

    public void setFeature(boolean feature) {
        this.feature = feature;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

    @Override
    public String toString() {
        return "BlogList{" + "id=" + id + ", title=" + title + ", category_id=" + category_id + ", postdate=" + postdate + ", brief_info=" + brief_info + ", thumbnail=" + thumbnail + ", feature=" + feature + ", status=" + status + '}';
    }


    
}
