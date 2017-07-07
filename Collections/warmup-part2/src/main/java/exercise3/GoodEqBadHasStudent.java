package exercise3;

/**
 * Created by Alexandra.Gaman on 7/7/2017.
 */
public class GoodEqBadHasStudent extends Student {

    public GoodEqBadHasStudent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public GoodEqBadHasStudent() {
    }

    @Override
    public int hashCode() {
        return getFirstName().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != getClass()) return false;
        GoodEqBadHasStudent p = (GoodEqBadHasStudent) o;
        if(getFirstName().equals(p.getFirstName()) && getLastName().equals(((GoodEqBadHasStudent) o).getLastName()))
            return true;
        return false;
    }
}
