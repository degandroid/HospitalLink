package com.enjoyor.hospitallink.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrator on 2016/7/2.
 */
public class BDLPoint {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String time;
    @DatabaseField
    private String errorCode;
    @DatabaseField
    private String latitude;
    @DatabaseField
    private String lontitude;
    @DatabaseField
    private String radius;
    @DatabaseField
    private String CountryCode;
    @DatabaseField
    private String Country;
    @DatabaseField
    private String citycode;
    @DatabaseField
    private String city;
    @DatabaseField
    private String District;
    @DatabaseField
    private String Street;
    @DatabaseField
    private String addr;
    @DatabaseField
    private String Describe;
    @DatabaseField
    private String DirectionValue;
    @DatabaseField
    private String Poi;
    @DatabaseField
    private String operationers;
    @DatabaseField
    private String describe;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLontitude() {
        return lontitude;
    }

    public void setLontitude(String lontitude) {
        this.lontitude = lontitude;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public String getDirectionValue() {
        return DirectionValue;
    }

    public void setDirectionValue(String directionValue) {
        DirectionValue = directionValue;
    }

    public String getPoi() {
        return Poi;
    }

    public void setPoi(String poi) {
        Poi = poi;
    }

    public String getOperationers() {
        return operationers;
    }

    public void setOperationers(String operationers) {
        this.operationers = operationers;
    }
}
