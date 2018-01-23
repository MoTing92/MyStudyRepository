package com.moting.applyaccount.bean;

import java.util.Date;

public class History {
    private Integer id;

    private String realname;

    private String username;

    private String handelPerson;

    private Date handelTime;

    private Date openTime;

    private Integer statue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getHandelPerson() {
        return handelPerson;
    }

    public void setHandelPerson(String handelPerson) {
        this.handelPerson = handelPerson == null ? null : handelPerson.trim();
    }

    public Date getHandelTime() {
        return handelTime;
    }

    public void setHandelTime(Date handelTime) {
        this.handelTime = handelTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }
}