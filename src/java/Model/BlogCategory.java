/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Dao.BlogDao;

/**
 *
 * @author Duy Hiep
 */
public class BlogCategory {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
    
    public int getTotalBlog(int category_id){
        BlogDao bd = new BlogDao();
        return bd.countBlog(category_id);
    }

    @Override
    public String toString() {
        return "BlogCategory{" + "id=" + id + ", name=" + name + '}';
    }

    
 
}
