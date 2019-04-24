package com.example.checkattendance;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport {

    private Integer id;

    private String name;

    private String sid;

    private int noclass;//缺席次数

    private int leave;//请假次数

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getNoclass() {
        return noclass;
    }

    public void setNoclass(int noclass) {
        this.noclass = noclass;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    
}



