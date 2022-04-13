/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.BlogCategory;
import Model.BlogDetail;
import Model.BlogList;
import Model.User;
import context.DBContext;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duy Hiep
 */
public class BlogDao extends DBContext{
    public User getUserById(int id){
        try {
            String sql = "select * from user where id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                User u = new User();
                u.setId(id);
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public ArrayList<User> getAuthor(){
        ArrayList<User> authors = new ArrayList<>();
        try {
            String sql = "select * from user where Role_Id = 5";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                authors.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authors;
    }
    
    public ArrayList<BlogCategory> getBlogCategory(){
        ArrayList<BlogCategory> category = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.blog_category";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                BlogCategory bc = new BlogCategory();
                bc.setId(rs.getInt("id"));
                bc.setName(rs.getString("name"));
                category.add(bc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;      
    }
    
    public ArrayList<BlogList> getLastNBlog(int number) {
        ArrayList<BlogList> list = new ArrayList<>();
        try {
            String sql = "select * from onlinelearn.blog_list order by id DESC limit " + number;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BlogList bl = new BlogList();
                bl.setId(rs.getInt("id"));
                bl.setTitle(rs.getNString("title"));
                BlogCategory bc = getBlogCategoryById(rs.getInt("category_id"));
                bl.setCategory_id(bc);
                bl.setPostdate(rs.getDate("postdate"));
                bl.setBrief_info(rs.getString("brief_info"));
                bl.setThumbnail(rs.getString("thumbnail"));
                list.add(bl);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);

        }

        return null;
    }
    
    public BlogCategory getBlogCategoryById(int id){
        try {
            String sql = "SELECT * FROM onlinelearn.blog_category where id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                BlogCategory bc = new BlogCategory();
                bc.setId(rs.getInt("id"));
                bc.setName(rs.getString("name"));
                return bc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;      
    }
    
    
    public ArrayList<BlogList> getPaginationBlogList(int index){
        ArrayList<BlogList> list = new ArrayList<>();
        try {
            String sql = "select bl.*,bd.author,bd.updated_date from blog_list bl inner join blog_detail bd\n" +
                        "on bl.id = bd.bloglist_id \n" +
                        "where bl.status = 1\n" +
                        "order by bd.updated_date desc\n" +
                        "limit 4 offset ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,  index * 4);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                BlogList bl = new  BlogList();
                bl.setId(rs.getInt("id"));
                bl.setTitle(rs.getNString("title"));
                BlogCategory bc = getBlogCategoryById(rs.getInt("category_id"));
                bl.setCategory_id(bc);
                bl.setPostdate(rs.getDate("updated_date"));
                bl.setBrief_info(rs.getString("brief_info"));
                bl.setThumbnail(rs.getString("thumbnail"));
                bl.setFeature(rs.getBoolean("feature"));
                bl.setStatus(rs.getBoolean("status"));
                BlogDetail bd = new BlogDetail();
                User u = getUserById(rs.getInt("author"));
                bd.setAuthor(u);
                bl.setBlogdetail(bd);
                list.add(bl);
                        
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;      
    }
    
    public void updateStatus(int id,String property){
        try {
            String sql = "UPDATE `onlinelearn`.`blog_list`\n" +
                    "SET\n" +
                    "`status` = ?\n" +
                    "WHERE `id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            if(property.equals("1")){
                stm.setBoolean(1, false);
            }
            else{
                stm.setBoolean(1, true);
            }
            stm.setInt(2, id);
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateFeature(int id, String property) {
        try {
            String sql = "UPDATE `onlinelearn`.`blog_list`\n" +
                    "SET\n" +
                    "`feature` = ?\n" +
                    "WHERE `id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            if(property.equals("1")){
                stm.setBoolean(1, false);
            }
            else{
                stm.setBoolean(1, true);
            }
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public BlogList getBlogListById(int id){
        try {
            String sql = "SELECT * FROM onlinelearn.blog_list where id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                BlogList bl = new  BlogList();
                bl.setId(rs.getInt("id"));
                bl.setTitle(rs.getNString("title"));
                BlogCategory bc = getBlogCategoryById(rs.getInt("category_id"));
                bl.setCategory_id(bc);
                bl.setPostdate(rs.getDate("postdate"));
                bl.setBrief_info(rs.getString("brief_info"));
                bl.setThumbnail(rs.getString("thumbnail"));
                bl.setFeature(rs.getBoolean("feature"));
                bl.setStatus(rs.getBoolean("status"));
                return bl;         
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<BlogList> getHighLightBlog(){
        ArrayList<BlogList> lists = new ArrayList<>();
        try {
            String sql = "Select * from blog_list where status = 1 and feature = 1;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                BlogList bl = new  BlogList();
                bl.setId(rs.getInt("id"));
                bl.setTitle(rs.getNString("title"));
                BlogCategory bc = getBlogCategoryById(rs.getInt("category_id"));
                bl.setCategory_id(bc);
                bl.setPostdate(rs.getDate("postdate"));
                bl.setBrief_info(rs.getString("brief_info"));
                bl.setThumbnail(rs.getString("thumbnail"));
                bl.setFeature(rs.getBoolean("feature"));
                bl.setStatus(rs.getBoolean("status"));
                lists.add(bl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lists;
    }
    
    public BlogDetail getBlogDetailById(int id){
        try {
            String sql = "SELECT * FROM onlinelearn.blog_detail where bloglist_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
               BlogDetail bd = new BlogDetail();
               bd.setId(rs.getInt("id"));
               BlogList bl = getBlogListById(id);
               bd.setBloglist_id(bl);
               bd.setUpdated_date(rs.getDate("updated_date"));
               User u = getUserById(rs.getInt("author"));
               bd.setAuthor(u);
               bd.setPost_content(rs.getString("post_content"));
               return bd;          
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;      
    }
    
    public ArrayList<BlogDetail> getBlogDetail(){
        ArrayList<BlogDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM onlinelearn.blog_detail";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               BlogDetail bd = new BlogDetail();
               bd.setId(rs.getInt("id"));
               BlogList bl = getBlogListById(rs.getInt("bloglist_id"));
               bd.setBloglist_id(bl);
               bd.setUpdated_date(rs.getDate("updated_date"));
               User u = getUserById(rs.getInt("author"));
               bd.setAuthor(u);
               bd.setPost_content(rs.getString("post_content"));
               list.add(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;      
    }
    
    public ArrayList<BlogList> getPaginationPostList(int index, String status, int category,int author,String sort) {
        ArrayList<BlogList> list = new ArrayList<>();
        try {
            String sql = "SELECT bl.*,bd.author,bd.updated_date FROM onlinelearn.blog_list bl\n" +
                            "inner join blog_detail bd\n" +
                            "on bl.id = bd.bloglist_id\n";
            PreparedStatement stm = null;
            if(author == -1 && category == -1 && status.equals("-1")){
                switch(sort){
                    case "title":
                        sql += " order by title desc\n";
                        break;
                    case "category":
                        sql += " order by category_id desc\n";
                        break;
                    case "author":
                        sql += " order by author desc\n";
                        break;
                    case "status":
                        sql += " order by status desc\n";
                        break;
                    case "feature":
                        sql += " order by feature desc\n";
                        break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setInt(1,  index * 4);
            }
            
            if(author != -1 && category != -1 && !status.equals("-1")){
                sql += " where author = ? and category_id = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }
                

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
                stm.setInt(2,category);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(3, s);
                stm.setInt(4, index * 4);  
            }
            else if(author != -1 && category == -1 && status.equals("-1")){
                sql += " where author = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
                stm.setInt(2, index * 4);
            }
            else if(author == -1 && category != -1 && status.equals("-1")){
                sql += " where category_id = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setInt(1,category);
                stm.setInt(2, index * 4);
            }
            else if(author == -1 && category == -1 && !status.equals("-1")){
                sql += " where status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;     
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(1, s);
                stm.setInt(2, index * 4);
            }
            else if(author != -1 && category != -1 && status.equals("-1")){
                sql += " where author = ? and category_id = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
                stm.setInt(2,category);
                stm.setInt(3, index * 4);
            }
            else if(author != -1 && category == -1 && !status.equals("-1")){
                sql += " where author = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;   
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(2, s);
                stm.setInt(3, index * 4);
            }
            else if(author == -1 && category != -1 && !status.equals("-1")){
                sql += " where category_id = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setInt(1,category);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(2, s);
                stm.setInt(3, index * 4);
            }
 
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                BlogList bl = new  BlogList();
                bl.setId(rs.getInt("id"));
                bl.setTitle(rs.getNString("title"));
                BlogCategory bc = getBlogCategoryById(rs.getInt("category_id"));
                bl.setCategory_id(bc);
                bl.setPostdate(rs.getDate("updated_date"));
                bl.setBrief_info(rs.getString("brief_info"));
                bl.setThumbnail(rs.getString("thumbnail"));
                bl.setFeature(rs.getBoolean("feature"));
                bl.setStatus(rs.getBoolean("status"));
                BlogDetail bd = new BlogDetail();
                User u = getUserById(rs.getInt("author"));
                bd.setAuthor(u);
                bl.setBlogdetail(bd);
                list.add(bl);          
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public int countPaginationPostList(String status, int category, int author,String sort){
        try{
            String sql = "SELECT count(*) as total FROM onlinelearn.blog_list bl\n" +
                            "inner join blog_detail bd\n" +
                            "on bl.id = bd.bloglist_id\n";
            PreparedStatement stm = null;
            if(author == -1 && category == -1 && status.equals("-1")){
                switch(sort){
                    case "title":
                        sql += " order by title desc\n";
                        break;
                    case "category":
                        sql += " order by category_id desc\n";
                        break;
                    case "author":
                        sql += " order by author desc\n";
                        break;
                    case "status":
                        sql += " order by status desc\n";
                        break;
                    case "feature":
                        sql += " order by feature desc\n";
                        break;
                }
                stm = connection.prepareStatement(sql);

            }
            
            if(author != -1 && category != -1 && !status.equals("-1")){
                sql += " where author = ? and category_id = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
                stm.setInt(2,category);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(3, s);
            }
            else if(author != -1 && category == -1 && status.equals("-1")){
                sql += " where author = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
            }
            else if(author == -1 && category != -1 && status.equals("-1")){
                sql += " where category_id = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1,category);
            }
            else if(author == -1 && category == -1 && !status.equals("-1")){
                sql += " where status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                stm = connection.prepareStatement(sql);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(1, s);
            }
            else if(author != -1 && category != -1 && status.equals("-1")){
                sql += " where author = ? and category_id = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
                stm.setInt(2,category);
            }
            else if(author != -1 && category == -1 && !status.equals("-1")){
                sql += " where author = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1, author);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(2, s);
            }
            else if(author == -1 && category != -1 && !status.equals("-1")){
                sql += " where category_id = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                stm = connection.prepareStatement(sql);
                stm.setInt(1,category);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(2, s);
            }
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int countBlog(int category_id){
        try {
            String sql = "Select count(*) as totalblog from onlinelearn.blog_list \n" +
                            "where blog_list.status = 1\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            if(category_id != 0){
                sql += " and category_id = ?;";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, category_id);
            }     
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("totalblog");
            }     
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    
    public ArrayList<BlogList> getSearchBlogList(String keyword,int index){
        ArrayList<BlogList> list = new ArrayList<>();
        try {
            String sql = "SELECT blog_list.id,blog_list.title,blog_list.category_id,\n" +
                "blog_detail.updated_date,blog_detail.author,blog_list.brief_info,blog_list.thumbnail,blog_list.feature,blog_list.status \n" +
                "FROM onlinelearn.blog_list inner join blog_detail \n" +
                "on blog_list.id = blog_detail.bloglist_id\n" +
                "where (blog_list.title like concat('%',?,'%') or blog_list.brief_info like concat(?,'%')) and blog_list.status = 1\n" +
                "order by blog_detail.updated_date desc\n" +
                "limit 4 offset ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
            stm.setString(2, keyword);
            stm.setInt(3, index * 4);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                BlogList bl = new BlogList();
                bl.setId(rs.getInt("id"));
                bl.setTitle(rs.getNString("title"));
                BlogCategory bc = getBlogCategoryById(rs.getInt("category_id"));
                bl.setCategory_id(bc);
                bl.setPostdate(rs.getDate("updated_date"));
                bl.setBrief_info(rs.getString("brief_info"));
                bl.setThumbnail(rs.getString("thumbnail"));
                bl.setFeature(rs.getBoolean("feature"));
                bl.setStatus(rs.getBoolean("status"));
                BlogDetail bd = new BlogDetail();
                User u = getUserById(rs.getInt("author"));
                bd.setAuthor(u);
                bl.setBlogdetail(bd);
                list.add(bl);                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;      
    }
    
    public ArrayList<BlogList> getSearchPaginationBlogList(String keyword,int index,String status, int category, int author,String sort){
        ArrayList<BlogList> list = new ArrayList<>();
        try {
            String sql = "SELECT bl.*,bd.author,bd.updated_date\n" +
            "FROM onlinelearn.blog_list bl inner join blog_detail bd\n" +
            "on bl.id = bd.bloglist_id\n";
            PreparedStatement stm = null;
            if(author == -1 && category == -1 && status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
                switch(sort){
                    case "title":
                        sql += " order by title desc\n";
                        break;
                    case "category":
                        sql += " order by category_id desc\n";
                        break;
                    case "author":
                        sql += " order by author desc\n";
                        break;
                    case "status":
                        sql += " order by status desc\n";
                        break;
                    case "feature":
                        sql += " order by feature desc\n";
                        break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1, keyword);
                stm.setInt(2,  index * 4);
            }
            if(author != -1 && category != -1 && !status.equals("-1")){
                sql += " where (bl.title like concat('%',?,'%'))\n";
                sql += " and author = ? and category_id = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1, keyword);
                stm.setInt(2, author);
                stm.setInt(3,category);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(4, s);
                stm.setInt(5, index * 4);  
            }
            else if(author != -1 && category == -1 && status.equals("-1")){
                sql += " where (bl.title like concat('%',?,'%'))\n";
                sql += " and author = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1, keyword);
                stm.setInt(2, author);
                stm.setInt(3, index * 4);
            }
            else if(author == -1 && category != -1 && status.equals("-1")){
                sql += " where (bl.title like concat('%',?,'%'))\n";
                sql += " and category_id = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1, keyword);
                stm.setInt(2,category);
                stm.setInt(3, index * 4);
            }
            else if(author == -1 && category == -1 && !status.equals("-1")){
                sql += " where (bl.title like concat('%',?,'%'))\n";
                sql += " and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }
                

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1, keyword);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(2, s);
                stm.setInt(3, index * 4);
            }
            else if(author != -1 && category != -1 && status.equals("-1")){
                sql += " where (bl.title like concat('%',?,'%'))\n";
                sql += " and author = ? and category_id = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1,keyword);
                stm.setInt(2, author);
                stm.setInt(3,category);
                stm.setInt(4, index * 4);
            }
            else if(author != -1 && category == -1 && !status.equals("-1")){
                sql += " where (bl.title like concat('%',?,'%'))\n";
                sql += " and author = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1,keyword);
                stm.setInt(2, author);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(3, s);
                stm.setInt(4, index * 4);
            }
            else if(author == -1 && category != -1 && !status.equals("-1")){
                sql += " where (bl.title like concat('%',?,'%'))\n";
                sql += " and category_id = ? and status = ?\n";
                switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
                }

                sql += "limit 4 offset ?;";  
                stm = connection.prepareStatement(sql);
                stm.setString(1,keyword);
                stm.setInt(2,category);
                boolean s = false;
                if(status.equals("1")){
                    s = true;
                }
                stm.setBoolean(3, s);
                stm.setInt(4, index * 4);
            }
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                BlogList bl = new BlogList();
                bl.setId(rs.getInt("id"));
                bl.setTitle(rs.getNString("title"));
                BlogCategory bc = getBlogCategoryById(rs.getInt("category_id"));
                bl.setCategory_id(bc);
                bl.setPostdate(rs.getDate("updated_date"));
                bl.setBrief_info(rs.getString("brief_info"));
                bl.setThumbnail(rs.getString("thumbnail"));
                bl.setFeature(rs.getBoolean("feature"));
                bl.setStatus(rs.getBoolean("status"));
                BlogDetail bd = new BlogDetail();
                User u = getUserById(rs.getInt("author"));
                bd.setAuthor(u);
                bl.setBlogdetail(bd);
                list.add(bl);                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;      
    }
    
    public int countBlogSearch(String keyword){
        try {
            String sql = "SELECT count(*) as total FROM onlinelearn.blog_list \n" +
            "inner join blog_detail \n" +
            "on blog_list.id = blog_detail.bloglist_id\n" +
            "where (blog_list.title like concat('%',?,'%') or blog_list.brief_info like concat(?,'%')) and blog_list.status = 1;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
            stm.setString(2, keyword);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("total");
            }     
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int countPostSearch(String keyword,String status, int category, int author,String sort){
        try {
        String sql = "SELECT count(*) as total\n" +
        "FROM onlinelearn.blog_list bl inner join blog_detail bd\n" +
        "on bl.id = bd.bloglist_id\n";
        PreparedStatement stm = null;
        if(author == -1 && category == -1 && status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            switch(sort){
                case "title":
                    sql += " order by title desc\n";
                    break;
                case "category":
                    sql += " order by category_id desc\n";
                    break;
                case "author":
                    sql += " order by author desc\n";
                    break;
                case "status":
                    sql += " order by status desc\n";
                    break;
                case "feature":
                    sql += " order by feature desc\n";
                    break;
            }

 
            stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
        }
        if(author != -1 && category != -1 && !status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            sql += " and author = ? and category_id = ? and status = ?\n";
            switch(sort){
            case "title":
                sql += " order by title desc\n";
                break;
            case "category":
                sql += " order by category_id desc\n";
                break;
            case "author":
                sql += " order by author desc\n";
                break;
            case "status":
                sql += " order by status desc\n";
                break;
            case "feature":
                sql += " order by feature desc\n";
                break;
            }
            stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
            stm.setInt(2, author);
            stm.setInt(3,category);
            boolean s = false;
            if(status.equals("1")){
                s = true;
            }
            stm.setBoolean(4, s);
        }
        else if(author != -1 && category == -1 && status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            sql += " and author = ?\n";
            switch(sort){
            case "title":
                sql += " order by title desc\n";
                break;
            case "category":
                sql += " order by category_id desc\n";
                break;
            case "author":
                sql += " order by author desc\n";
                break;
            case "status":
                sql += " order by status desc\n";
                break;
            case "feature":
                sql += " order by feature desc\n";
                break;
            }

            stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
            stm.setInt(2, author);
        }
        else if(author == -1 && category != -1 && status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            sql += " and category_id = ?\n";
            switch(sort){
            case "title":
                sql += " order by title desc\n";
                break;
            case "category":
                sql += " order by category_id desc\n";
                break;
            case "author":
                sql += " order by author desc\n";
                break;
            case "status":
                sql += " order by status desc\n";
                break;
            case "feature":
                sql += " order by feature desc\n";
                break;
            }

            stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
            stm.setInt(2,category);
        }
        else if(author == -1 && category == -1 && !status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            sql += " and status = ?\n";
            switch(sort){
            case "title":
                sql += " order by title desc\n";
                break;
            case "category":
                sql += " order by category_id desc\n";
                break;
            case "author":
                sql += " order by author desc\n";
                break;
            case "status":
                sql += " order by status desc\n";
                break;
            case "feature":
                sql += " order by feature desc\n";
                break;
            }

            stm = connection.prepareStatement(sql);
            stm.setString(1, keyword);
            boolean s = false;
            if(status.equals("1")){
                s = true;
            }
            stm.setBoolean(2, s);
        }
        else if(author != -1 && category != -1 && status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            sql += " and author = ? and category_id = ?\n";
            switch(sort){
            case "title":
                sql += " order by title desc\n";
                break;
            case "category":
                sql += " order by category_id desc\n";
                break;
            case "author":
                sql += " order by author desc\n";
                break;
            case "status":
                sql += " order by status desc\n";
                break;
            case "feature":
                sql += " order by feature desc\n";
                break;
            }

            stm = connection.prepareStatement(sql);
            stm.setString(1,keyword);
            stm.setInt(2, author);
            stm.setInt(3,category);
        }
        else if(author != -1 && category == -1 && !status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            sql += " and author = ? and status = ?\n";
            switch(sort){
            case "title":
                sql += " order by title desc\n";
                break;
            case "category":
                sql += " order by category_id desc\n";
                break;
            case "author":
                sql += " order by author desc\n";
                break;
            case "status":
                sql += " order by status desc\n";
                break;
            case "feature":
                sql += " order by feature desc\n";
                break;
            }

            stm = connection.prepareStatement(sql);
            stm.setString(1,keyword);
            stm.setInt(2, author);
            boolean s = false;
            if(status.equals("1")){
                s = true;
            }
            stm.setBoolean(3, s);
        }
        else if(author == -1 && category != -1 && !status.equals("-1")){
            sql += " where (bl.title like concat('%',?,'%'))\n";
            sql += " and category_id = ? and status = ?\n";
            switch(sort){
            case "title":
                sql += " order by title desc\n";
                break;
            case "category":
                sql += " order by category_id desc\n";
                break;
            case "author":
                sql += " order by author desc\n";
                break;
            case "status":
                sql += " order by status desc\n";
                break;
            case "feature":
                sql += " order by feature desc\n";
                break;
            }
 
            stm = connection.prepareStatement(sql);
            stm.setString(1,keyword);
            stm.setInt(2,category);
            boolean s = false;
            if(status.equals("1")){
                s = true;
            }
            stm.setBoolean(3, s);
        }
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }     
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int updatePost(BlogList bl, BlogDetail bd, String thumbnail){
        int row = 0;
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE `onlinelearn`.`blog_detail`\n" +
                    "SET\n" +
                    "`updated_date` = ?,\n" +
                    "`post_content` = ?\n" +
                    "WHERE `id` = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, bd.getUpdated_date());
            stm.setString(2, bd.getPost_content());
            stm.setInt(3, bd.getId());
            stm.executeUpdate();
            
            sql = "UPDATE `onlinelearn`.`blog_list`\n" +
                    "SET\n" +
                    "`title` = ?,\n" +
                    "`category_id` = ?,\n" +
                    "`brief_info` = ?,\n" +
                    "`feature` = ?,\n" +
                    "`status` = ?\n";
            if(thumbnail != null){
                   sql += ",`thumbnail` = ?\n" ;
            }
            sql += "WHERE `id` = ?;";
            stm = connection.prepareStatement(sql);
            stm.setString(1, bl.getTitle());
            stm.setInt(2, bl.getCategory_id().getId());
            stm.setString(3, bl.getBrief_info());
            stm.setBoolean(4, bl.isFeature());
            stm.setBoolean(5, bl.isStatus());
            if( thumbnail != null){
                stm.setString(6, thumbnail);
                stm.setInt(7, bl.getId());
            }
            else{
                stm.setInt(6, bl.getId());
            }          
            row = stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        //set again the autocomit
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return row;
    }
    
     public int insertPost(BlogList bl, BlogDetail bd, String thumbnail) {
        int row = 0;
        try {
            connection.setAutoCommit(false); 
            String sql = "INSERT INTO `onlinelearn`.`blog_list`\n" +
                        "(`title`,`category_id`,`postdate`,`brief_info`,\n" +
                        "`thumbnail`,`feature`,`status`)\n" +
                        "VALUES\n" +
                        "(?,?,?,?,?,?,?);";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, bl.getTitle());
            stm.setInt(2, bl.getCategory_id().getId());
            stm.setDate(3, bl.getPostdate());
            stm.setString(4, bl.getBrief_info());
            stm.setString(5, thumbnail);
            stm.setBoolean(6, bl.isFeature());
            stm.setBoolean(7, bl.isStatus());
            stm.executeUpdate();
            
            String sql_get_id = "Select @@IDENTITY as blid"; // @@IDENTITY is a system function that returns the last-inserted identity value.
            PreparedStatement stm_get_id = connection.prepareStatement(sql_get_id);
            ResultSet rs = stm_get_id.executeQuery();
            if (rs.next()) {
                bl.setId(rs.getInt("blid"));
            }
            
            sql = "SET FOREIGN_KEY_CHECKS = 0;\n";
            stm = connection.prepareStatement(sql);
            stm.executeUpdate();
            
            sql = "INSERT INTO `onlinelearn`.`blog_detail`\n" +
                    "(`bloglist_id`,\n" +
                    "`updated_date`,\n" +
                    "`author`,\n" +
                    "`post_content`)\n" +
                    "VALUES\n" +
                    "(?,\n" +
                    "?,\n" +
                    "?,\n" +
                    "?);";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bl.getId());
            stm.setDate(2, bd.getUpdated_date());
            stm.setInt(3, bd.getAuthor().getId());
            stm.setString(4, bd.getPost_content());
            
            row = stm.executeUpdate();
            
            sql = "SET FOREIGN_KEY_CHECKS = 1;\n";
            stm = connection.prepareStatement(sql);
            stm.executeUpdate();
            
            sql_get_id = "Select @@IDENTITY as bdid"; // @@IDENTITY is a system function that returns the last-inserted identity value.
            stm_get_id = connection.prepareStatement(sql_get_id);
            rs = stm_get_id.executeQuery();
            if (rs.next()) {
                bd.setId(rs.getInt("bdid"));
            }
            
            sql = "UPDATE `onlinelearn`.`blog_detail`\n" +
                    "SET\n" +
                    "`author` = ?\n"+
                    "WHERE `id` = ?;";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, bd.getAuthor().getId());
            stm.setInt(2, bd.getId());
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        //set again the autocomit
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BlogDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return row;
    }

    public static void main(String[] args) {
        BlogDao bd = new BlogDao();
        BlogDetail blogDetailById = bd.getBlogDetailById(3);
        
        System.out.println(blogDetailById.toString());
        
    }

   

    

    
}
