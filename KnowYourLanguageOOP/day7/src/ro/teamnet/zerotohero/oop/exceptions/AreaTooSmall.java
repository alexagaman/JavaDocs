package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class AreaTooSmall extends RuntimeException {
    @Override
    public String getMessage() {
        return "the area of the shape is to small to represent";
    }
}
