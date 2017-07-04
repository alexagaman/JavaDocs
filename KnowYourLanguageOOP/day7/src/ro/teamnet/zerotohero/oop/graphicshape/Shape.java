package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class Shape extends  AbstractShape implements ShapeBehaviour  {
    protected int color;
    protected float saturation;

    public double  area(){
        return 0;
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return this.color;
    }

    public void setSaturation(float saturation){
        this.saturation = saturation;
    }

    public float getSaturation(){
        return this.saturation;
    }
}
