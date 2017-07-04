package ro.teamnet.zerotohero.oop.immutableObj;

import ro.teamnet.zerotohero.oop.graphicshape.Circle;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public final class Tester {
    private final int x;
    private Circle a;
    public Tester(int x){
        this.x = x;
    }

    public void TestApp(){
        System.out.println("Look at this  -->  " + x);
    }
    public int getVal(){
        return x;
    }

}
