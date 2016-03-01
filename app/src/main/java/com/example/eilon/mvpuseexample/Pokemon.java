package com.example.eilon.mvpuseexample;

/**
 * Created by eilon on 01/03/16.
 */


public class Pokemon {

    private String name, type;

    public Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " " + type + "\n";
    }

}
