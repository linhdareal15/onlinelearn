/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Duy Hiep
 */
public class BlogDetail {
   private int id;
   private BlogList bloglist_id;
   private Date updated_date;
   private User author;
   private String post_content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BlogList getBloglist_id() {
        return bloglist_id;
    }

    public void setBloglist_id(BlogList bloglist_id) {
        this.bloglist_id = bloglist_id;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    @Override
    public String toString() {
        return "BlogDetail{" + "id=" + id + ", bloglist_id=" + bloglist_id + ", updated_date=" + updated_date + ", author=" + author + ", post_content=" + post_content + '}';
    }
   
    
   
}
