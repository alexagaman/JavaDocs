package com.teamnet.examples;

import java.util.Random;

/**
 * Created by Alexandra.Gaman on 7/10/2017.
 */
public class MyUnit {

    public String concatenate(String s1, String s2){
        String s;
        if(s1 == null){
            s = s2;
        }
        else if(s2 == null)
            s = s1;
        else
            s = s1 + s2;
        return s;
    }

    public boolean getBoolean(){
        return new java.util.Random().nextBoolean();
    }
}
