package com.example.binar.binarbinariahya_1202152323_modul6;

import com.google.firebase.database.IgnoreExtraProperties;

//encapsulation data post
@IgnoreExtraProperties
public class databasePost {
    String image, judul, caption, user, key;

    //dibutuhkan untuk membaca data
    public databasePost(){

    }
    //constructor
    public databasePost(String caption, String image, String judul, String user ){
        this.image = image;
        this.judul = judul;
        this.caption = caption;
        this.user = user;
    }
    //menentukan key dari firebase
    public void setKey(String key) {
        this.key = key;
    }

    //methid getter dari variabel lainnya
    public String getImage() {
        return image;
    }

    public String getJudul() {
        return judul;
    }

    public String getCaption() {
        return caption;
    }

    public String getUser() {
        return user;
    }

    //mendapatkan key dari firebase
    public String getKey() {
        return key;
    }
}
