package exercise3;

/**
 * Created by Alexandra.Gaman on 7/7/2017.
 */
public class GoodEqGoodHashStudent extends Student{


    public GoodEqGoodHashStudent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public GoodEqGoodHashStudent() {
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = result*31 + getLastName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(o.getClass() != getClass()) return false;
        GoodEqGoodHashStudent p = (GoodEqGoodHashStudent) o;
        if(getFirstName().equals(p.getFirstName()) && getLastName().equals( p.getLastName()))
            return true;
        return false;
    }
}
