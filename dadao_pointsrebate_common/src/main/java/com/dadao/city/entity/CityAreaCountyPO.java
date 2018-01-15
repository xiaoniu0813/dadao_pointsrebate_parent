package com.dadao.city.entity;

/**
 * 城市分类表（带经纬度）
 *
 * @auther NFY niufuyang
 * @create 2017-08-04
 */
public class CityAreaCountyPO {
    private Long id;//int(10) NOT NULL COMMENT 'ID',
    private String areaname;//varchar(50) NOT NULL COMMENT '栏目名',
    private Long parentid;//int(10) NOT NULL COMMENT '父栏目',
    private String shortname;//varchar(50) DEFAULT NULL,
    private String lng;//varchar(20) DEFAULT NULL,纬度
    private String lat;//varchar(20) DEFAULT NULL,经度

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
