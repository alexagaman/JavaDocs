package ro.teamnet.zerotohero.oop.exceptions;

/**
 * Created by Alexandra.Gaman on 7/4/2017.
 */
public class AreaWayTooSmall extends AreaTooSmall {
    @Override
    public String getMessage() {
        return "can it be any smaller?";
    }
}
