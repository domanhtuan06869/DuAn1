package com.cao.nang.duan.chatgroup.modelgroup;

public class ImageUploadInfo {
    private String imageURL;
    private  String title;
    private String email;
    private String date;
    private String content;


    public ImageUploadInfo() {

    }

    public ImageUploadInfo( String title,String content,String url,String email,String date) {
        this.title=title;
        this.imageURL= url;
        this.email=email;
        this.date=date;
        this.content=content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
