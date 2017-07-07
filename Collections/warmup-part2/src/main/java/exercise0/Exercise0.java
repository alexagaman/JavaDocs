package exercise0;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 *          print all its elements.
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughMap(){

        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1, new String("unu"));
        map.put(2, new String("doi"));
        map.put(3, new String("trei"));
        map.put(4, new String("patru"));
        map.put(5, new String("cinci"));


        Set<Integer> keyset = map.keySet();
        System.out.print("[");
        for(Integer key : keyset){
            System.out.print(""+key+"="+map.get(key)+", ");
        }
        System.out.println("]");
        
    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}
