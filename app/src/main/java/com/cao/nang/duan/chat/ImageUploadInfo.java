package com.cao.nang.duan.chat;

public class ImageUploadInfo {
    private String imageURL;
    private  String title;


    public ImageUploadInfo() {

    }

    public ImageUploadInfo( String title,String url) {
        this.title=title;
        this.imageURL= url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }
}
