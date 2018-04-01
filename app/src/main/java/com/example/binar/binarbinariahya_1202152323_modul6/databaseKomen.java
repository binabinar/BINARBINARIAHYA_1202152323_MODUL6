package com.example.binar.binarbinariahya_1202152323_modul6;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by telkom on 31/03/2018.
 */
//encapsulation data comment
@IgnoreExtraProperties
public class databaseKomen {
    String sikomen, komen, fotokomen;

    //kosong untuk membaca data
    public databaseKomen(){

    }
    //constructor
    public databaseKomen(String sikomen, String komen, String fotokomen){
        this.sikomen = sikomen;
        this.komen = komen;
        this.fotokomen = fotokomen;
    }

    //getter untuk variabel
    public String getSikomen() {
        return sikomen;
    }

    public String getKomen() {
        return komen;
    }

    public String getFotokomen() {
        return fotokomen;
    }
}
