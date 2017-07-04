package ro.teamnet.zerotohero.oop.runapp;

import ro.teamnet.zerotohero.canvas.Canvas;
import ro.teamnet.zerotohero.oop.exceptions.AreaTooSmall;
import ro.teamnet.zerotohero.oop.exceptions.AreaWayTooSmall;
import ro.teamnet.zerotohero.oop.graphicshape.*;
import ro.teamnet.zerotohero.oop.immutableObj.Tester;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class RunApp {

    public static void main(String[] args) {
        Circles c1 = new Circles();


        try (BufferedReader br =
                     new BufferedReader(new FileReader("Dona.txt"))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("try with res");
        }

        ArrayList<Integer> x = new ArrayList<Integer>();
        int times =0;
        int i =0;
        /*try{
            while(true){
                x.set(i,i);
                i++;
                times++;
            }
        }
        finally {
            System.out.println("the number of times is " + times);
        }*/
        try{
            Circle c01 = new Circle(1);
            c01.area();
            Circle c02 = new Circle(2);
            c02.area();
        }
        catch(AreaWayTooSmall e){
            System.out.println("one or both circles need to be made bigger");
        }
        catch (AreaTooSmall f){
            System.out.println("you should make one or both circles just a bit bigger, please!");
        }

       try {
            System.out.println("The default circle area is " + c1.getAreaPub());
        }
        catch(AreaTooSmall | IllegalArgumentException e){
            try {
                System.out.println("found a problem, wait a bit i'm trying again");
                System.out.println("The default circle area is " + c1.getAreaPub());
            }
            catch (AreaTooSmall f){
                System.out.println("nah, still not working, you should find the problem now");
                throw new AreaTooSmall();
            }
            finally {
                System.out.println(c1.toString());
            }
        }






        c1.getAreaDef();
        Shape s = new Square(2);
        System.out.println("area = " +s.area());

        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));
        if(s.area() <= 10 ) throw new AreaTooSmall();
        Tester t =new Tester(13);
        t.TestApp();

    }
}
