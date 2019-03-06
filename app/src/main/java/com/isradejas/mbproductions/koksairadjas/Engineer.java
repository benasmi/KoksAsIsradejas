package com.isradejas.mbproductions.koksairadjas;

import java.io.Serializable;

public class Engineer implements Serializable {

    public String FullName;
    public String About;
    public int PhotoResource;

    public Engineer(String name, String about, int photoResource){
        FullName = name;
        About = about;
        PhotoResource = photoResource;
    }

}
