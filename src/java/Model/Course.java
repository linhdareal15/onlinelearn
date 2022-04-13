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
 * @author Louis
 */
public class Course {

    private int cid;
    private String title;
    private Blob thumbnail;
    private String briefinfo;
    private User author;
    private String introduction;
    private double listprice;
    private double saleprice;
    private boolean status;
    private boolean featureflag;
    private CourseCategory category;
    private String filename;
    private Blob document;
    //day add/update new course(subject)
    private Date updatedate;

    public Course() {
    }

    public Course(int cid, String title, Blob thumbnail, String briefinfo, User author, String introduction, double listprice, double saleprice, boolean status, boolean featureflag, CourseCategory category, String filename, Blob document, Date updatedate) {
        this.cid = cid;
        this.title = title;
        this.thumbnail = thumbnail;
        this.briefinfo = briefinfo;
        this.author = author;
        this.introduction = introduction;
        this.listprice = listprice;
        this.saleprice = saleprice;
        this.status = status;
        this.featureflag = featureflag;
        this.category = category;
        this.filename = filename;
        this.document = document;
        this.updatedate = updatedate;
    }

    

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blob getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Blob thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBriefinfo() {
        return briefinfo;
    }

    public void setBriefinfo(String briefinfo) {
        this.briefinfo = briefinfo;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getListprice() {
        return listprice;
    }

    public void setListprice(double listprice) {
        this.listprice = listprice;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFeatureflag() {
        return featureflag;
    }

    public void setFeatureflag(boolean featureflag) {
        this.featureflag = featureflag;
    }

    public CourseCategory getCategory() {
        return category;
    }

    public void setCategory(CourseCategory category) {
        this.category = category;
    }

    public Blob getDocument() {
        return document;
    }

    public void setDocument(Blob document) {
        this.document = document;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
