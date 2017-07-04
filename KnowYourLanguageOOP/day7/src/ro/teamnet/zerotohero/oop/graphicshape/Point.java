package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class Point {
    int xPos;
    int yPos;
    public Point(int x, int y){
        xPos=x;
        yPos = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(obj instanceof  Point){
            Point anotherPoint = (Point) obj;
            if((xPos == anotherPoint.xPos) && (yPos == anotherPoint.yPos))
                return  true;
        }
        return false;
    }
}
