package com.cao.nang.duan.model;

public class SickList {
    private  String sick_name;
    private  String treatment;

    public SickList(String sick_name, String treatment) {
        this.sick_name = sick_name;
        this.treatment = treatment;
    }

    public String getSick_name() {
        return sick_name;
    }

    public void setSick_name(String sick_name) {
        this.sick_name = sick_name;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
