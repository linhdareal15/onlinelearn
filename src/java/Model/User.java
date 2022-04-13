/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Array;
import java.sql.Blob;
import java.util.ArrayList;
import javax.transaction.Transaction;

/**
 *
 * @author Quang
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String fullname;
    private boolean gender;
    private String address;
    private String email;
    private String phonenumber;
    private int status;
    private Role role;
    private Blob avatar;

    private ArrayList<Transaction> transactions = new ArrayList<>();

    public User() {
    }

    public User(int id, String username, String password, String fullname, boolean gender, String address, String email, String phonenumber, int status, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phonenumber = phonenumber;
        this.status = status;
        this.role = role;
    }

    public User(int id, String username, String password, String fullname, boolean gender, String address, String email, String phonenumber, int status, Role role, Blob avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phonenumber = phonenumber;
        this.status = status;
        this.role = role;
        this.avatar = avatar;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname + ", gender=" + gender + ", address=" + address + ", email=" + email + ", phonenumber=" + phonenumber + ", status=" + status + ", role=" + role + ", transactions=" + transactions + '}';
    }
    

}
