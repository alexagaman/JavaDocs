package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandra.Gaman on 7/7/2017.
 */
public class Main {
    public static void main(String[] args) {
        Map<Student,BigDecimal> map1 = new HashMap<Student, BigDecimal>();
        Map<Student,BigDecimal> map2 = new HashMap<Student, BigDecimal>();
        Map<Student,BigDecimal> map3 = new HashMap<Student, BigDecimal>();
        Map<Student,BigDecimal> map4 = new HashMap<Student, BigDecimal>();


        map1.put(new BadEqBadHashStudent("AAAA","BBBB"), new BigDecimal(15));
        map1.put(new BadEqBadHashStudent("AAAA","CCCC"), new BigDecimal(16));
        map1.put(new BadEqBadHashStudent("DDDD","BBBB"), new BigDecimal(17));
        map1.put(new BadEqBadHashStudent("DDDD","BBBB"), new BigDecimal(19));

        System.out.println(map1);


        map2.put(new BadEqGoodHashStudent("AAAA","BBBB"), new BigDecimal(15));
        map2.put(new BadEqGoodHashStudent("AAAA","CCCC"), new BigDecimal(16));
        map2.put(new BadEqGoodHashStudent("DDDD","BBBB"), new BigDecimal(17));
        map2.put(new BadEqGoodHashStudent("DDDD","BBBB"), new BigDecimal(19));

        System.out.println(map2);


        map3.put(new GoodEqBadHasStudent("AAAA","BBBB"), new BigDecimal(15));
        map3.put(new GoodEqBadHasStudent("AAAA","CCCC"), new BigDecimal(16));
        map3.put(new GoodEqBadHasStudent("DDDD","BBBB"), new BigDecimal(17));
        map3.put(new GoodEqBadHasStudent("DDDD","BBBB"), new BigDecimal(19));

        System.out.println(map3);


        map4.put(new GoodEqGoodHashStudent("AAAA","BBBB"), new BigDecimal(15));
        map4.put(new GoodEqGoodHashStudent("AAAA","CCCC"), new BigDecimal(16));
        map4.put(new GoodEqGoodHashStudent("DDDD","BBBB"), new BigDecimal(17));
        map4.put(new GoodEqGoodHashStudent("DDDD","BBBB"), new BigDecimal(19));

        System.out.println(map4);
    }
}
