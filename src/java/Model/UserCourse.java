/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Quang
 */
public class UserCourse {

    private int id;
    private int course_id;
    private int transaction_id;

    public UserCourse() {
    }

    public UserCourse(int id, int course_id, int transaction_id) {
        this.id = id;
        this.course_id = course_id;
        this.transaction_id = transaction_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    @Override
    public String toString() {
        return "UserCourse{" + "id=" + id + ", course_id=" + course_id + ", transaction_id=" + transaction_id + '}';
    }

}
