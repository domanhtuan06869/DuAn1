package com.cao.nang.duan.model;

public class Hospital {
    private  String name_hospital;
    private  String province;
    private String district;
    private  String address;
    private  double longitude;
    private  double latitude ;
public Hospital(){}
    public Hospital(String name_hospital, String province, String district, String address, double longitude, double latitude) {
        this.name_hospital = name_hospital;
        this.province = province;
        this.district = district;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName_hospital() {
        return name_hospital;
    }

    public void setName_hospital(String name_hospital) {
        this.name_hospital = name_hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
