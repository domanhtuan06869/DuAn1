package com.cao.nang.duan.model;

public class FindHospital {
    private String province;
    private String district;

    public FindHospital(String province, String district) {
        this.province = province;
        this.district = district;
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
}
