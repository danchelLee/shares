package com.dc.sharesdemo.entity;

import javax.persistence.*;

/**
 * @Auther: D&C
 * @Descirption:
 * @Time: 2019-03-04
 */
@Entity
@Table(name = "user", schema = "hotel", catalog = "")
public class UserEntity {
    private int id;
    private String username;
    private String pwd;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        return result;
    }
}
