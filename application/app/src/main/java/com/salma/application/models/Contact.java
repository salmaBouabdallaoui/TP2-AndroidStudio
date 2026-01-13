package com.salma.application.models;

import androidx.annotation.NonNull;

public class Contact {

    private String name;
    private String phone;

    public Contact(String name,String phone){
        this.name=name;
        this.phone=phone;
    };

    public String getName() {
        return name;
    }

    public String getPhone(){
        return phone;
    }

    @NonNull
    @Override
    public String toString(){
        return name + " : " + phone;
    }
}
