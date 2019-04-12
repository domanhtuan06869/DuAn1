package com.cao.nang.duan.chatgroup.modelgroup;

public class All_Image_Uploads_Database {

    private String imageURL;


    public All_Image_Uploads_Database() {

    }


    public All_Image_Uploads_Database( String url) {

        this.imageURL= url;
    }


    public String getImageURL() {
        return imageURL;
    }
}
