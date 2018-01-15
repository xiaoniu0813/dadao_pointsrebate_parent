package com.dadao.city.entity;

/**
 * 城市
 * Created by yangrui on 2017/7/16.
 */
public class PubCity {
    private Integer id; //int(11) NOT NULL,
    private String name; //varchar(40) DEFAULT NULL,
    private Integer pid; //int(11) DEFAULT NULL,

    public Integer getId() {
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "PubCity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                '}';
    }
}
