package com.alireza.model;

import java.sql.Date;

public class User {
    private int id;
    private String username;
    private String nationalCode;
    private Date birthday;
    private String password;

    public User(int id, String username, String nationalCode, Date birthday, String password) {
        this.id = id;
        this.username = username;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
        this.password = password;
    }

    public User(int id, String username, String nationalCode, Date birthday) {
        this.id = id;
        this.username = username;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
    }

    public User(String username, String nationalCode, Date birthday) {
        this.username = username;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
