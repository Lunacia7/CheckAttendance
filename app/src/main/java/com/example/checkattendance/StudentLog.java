package com.example.checkattendance;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class StudentLog extends LitePalSupport {

    private Integer id;

    private String name;

    private String sid;

    private int isnoclass;//是否缺席

    private int isleave;//是否请假

    private double date;//日期

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

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

    public int getIsnoclass() {
        return isnoclass;
    }

    public void setIsnoclass(int isnoclass) {
        this.isnoclass = isnoclass;
    }

    public int getIsleave() {
        return isleave;
    }

    public void setIsleave(int isleave) {
        this.isleave = isleave;
    }
}



