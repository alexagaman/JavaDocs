package ro.teamnet.zerotohero.oop.graphicshape;

import ro.teamnet.zerotohero.oop.exceptions.AreaTooSmall;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class Circles {
    public double getAreaPub(){
        Circle c = new Circle();
        if(c.area() <= 1000) {
            throw new AreaTooSmall();
        }
        return c.area();
    };
    public void getAreaDef(){
        Circle c1 = new Circle();
        c1.fillColour();
        c1.fillColour(5);
        c1.fillColour(0.6f);
    }
}
