/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Quang
 */
public class Registration {

    private int id;
    private Course course;
    private Timestamp registrationTime;
    private float totalCourse;
    private RegistrationStatus registrationStatus;
    private Timestamp updateTime;
    private User RegisBy;
    private String userAccept;

    public Registration() {
    }

    public Registration(int id, Course course, Timestamp registrationTime, float totalCourse, RegistrationStatus registrationStatus, Timestamp updateTime, User RegisBy, String userAccept) {
        this.id = id;
        this.course = course;
        this.registrationTime = registrationTime;
        this.totalCourse = totalCourse;
        this.registrationStatus = registrationStatus;
        this.updateTime = updateTime;
        this.RegisBy = RegisBy;
        this.userAccept = userAccept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public float getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(float totalCourse) {
        this.totalCourse = totalCourse;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public User getRegisBy() {
        return RegisBy;
    }

    public void setRegisBy(User RegisBy) {
        this.RegisBy = RegisBy;
    }

    public String getUserAccept() {
        return userAccept;
    }

    public void setUserAccept(String userAccept) {
        this.userAccept = userAccept;
    }

    

   
    
    

}
