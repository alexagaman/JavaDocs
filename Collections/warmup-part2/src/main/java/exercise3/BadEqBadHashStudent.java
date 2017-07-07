package exercise3;

/**
 * Created by Alexandra.Gaman on 7/7/2017.
 */
public class BadEqBadHashStudent extends Student{

    public BadEqBadHashStudent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public BadEqBadHashStudent() {
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != getClass()) return false;
        BadEqBadHashStudent p = (BadEqBadHashStudent) o;
        if(getFirstName().equals(p.getFirstName()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return getFirstName().hashCode();
    }
}
