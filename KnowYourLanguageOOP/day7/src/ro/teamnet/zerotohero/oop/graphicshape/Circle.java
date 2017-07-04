package ro.teamnet.zerotohero.oop.graphicshape;
import ro.teamnet.zerotohero.oop.exceptions.AreaTooSmall;
import ro.teamnet.zerotohero.oop.exceptions.AreaWayTooSmall;

import static java.lang.Math.PI;
/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class Circle extends Shape{
    private int xPos;
    private int yPos;
    private int radius;
    public Circle(){
        xPos = 0;
        yPos = 0;
        radius = 1;
    }
    public Circle(int radius){
        xPos = 0;
        yPos = 0;
        this.radius = radius;
    }
    public  Circle(int xPos,int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = 1;
    }

    public Circle(int xPos, int yPos, int radius){
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    @Override
    public double area() {
        double a = 2*Math.PI*radius*radius;
        if(a < 8) throw new AreaWayTooSmall();
        if(a < 19) throw new AreaTooSmall();
        return a;
    }

    @Override
    public String toString() {
        return "center = ("+xPos+","+yPos+") and radius = "+radius;
    }

    void fillColour(){
        System.out.println(color);
    }

    void fillColour(int color){
        setColor(color);
        System.out.println("The circle color is now " + color);
    }

    void fillColour(float saturation){
        setSaturation(saturation);
        System.out.println("The circle saturation is now " + saturation);
    }
}
