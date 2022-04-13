/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author Duy Hiep
 */
public class Token {
    private int id;
    private User user_id;
    private String code;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp expired;

    public Token() {
    }

    public Token(int id, User user_id, String code, Timestamp createdAt, Timestamp updatedAt, Timestamp expired) {
        this.id = id;
        this.user_id = user_id;
        this.code = code;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expired = expired;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    
    
    
}
