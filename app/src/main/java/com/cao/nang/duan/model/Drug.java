package com.cao.nang.duan.model;

public class Drug {
    private String name_drug;
    private String category_drug;
    private String price_drug;
    private String ingredient_drug;
    private String assign_drug;
    private String contraindicated_drug;
    private String user_drug;
    private String side_efects;
    private String attention;

    public Drug(String name_drug,String category_drug, String price_drug, String ingredient_drug, String assign_drug, String contraindicated_drug, String user_drug, String side_efects, String attention) {
        this.name_drug = name_drug;
        this.price_drug = price_drug;
        this.ingredient_drug = ingredient_drug;
        this.assign_drug = assign_drug;
        this.contraindicated_drug = contraindicated_drug;
        this.user_drug = user_drug;
        this.side_efects = side_efects;
        this.attention = attention;
    }

    public String getCategory_drug() {
        return category_drug;
    }

    public void setCategory_drug(String category_drug) {
        this.category_drug = category_drug;
    }

    public String getName_drug() {
        return name_drug;
    }

    public void setName_drug(String name_drug) {
        this.name_drug = name_drug;
    }

    public String getPrice_drug() {
        return price_drug;
    }

    public void setPrice_drug(String price_drug) {
        this.price_drug = price_drug;
    }

    public String getIngredient_drug() {
        return ingredient_drug;
    }

    public void setIngredient_drug(String ingredient_drug) {
        this.ingredient_drug = ingredient_drug;
    }

    public String getAssign_drug() {
        return assign_drug;
    }

    public void setAssign_drug(String assign_drug) {
        this.assign_drug = assign_drug;
    }

    public String getContraindicated_drug() {
        return contraindicated_drug;
    }

    public void setContraindicated_drug(String contraindicated_drug) {
        this.contraindicated_drug = contraindicated_drug;
    }

    public String getUser_drug() {
        return user_drug;
    }

    public void setUser_drug(String user_drug) {
        this.user_drug = user_drug;
    }

    public String getSide_efects() {
        return side_efects;
    }

    public void setSide_efects(String side_efects) {
        this.side_efects = side_efects;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }
}
