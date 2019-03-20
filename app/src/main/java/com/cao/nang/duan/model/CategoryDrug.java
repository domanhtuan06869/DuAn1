package com.cao.nang.duan.model;

public class CategoryDrug {
    private   String category_drug;
    private   String listdug;

    public CategoryDrug(String category_drug, String listdug) {
        this.category_drug = category_drug;
        this.listdug = listdug;
    }

    public String getCategory_drug() {
        return category_drug;
    }

    public void setCategory_drug(String category_drug) {
        this.category_drug = category_drug;
    }

    public String getListdug() {
        return listdug;
    }

    public void setListdug(String listdug) {
        this.listdug = listdug;
    }
}
