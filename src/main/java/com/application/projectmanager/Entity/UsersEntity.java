package com.application.projectmanager.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "projectManagerDB")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userid")
    private Integer userid;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "userpassword")
    private String userpassword;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void createUser(String username, String userpassword){
        setUsername(username);
        setUserpassword(userpassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(userid, that.userid) && Objects.equals(username, that.username) && Objects.equals(userpassword, that.userpassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, username, userpassword);
    }
}
