package exercise.exercise3;

import java.util.*;

/**
 * Created by Radu.Hoaghe on 04/20/2015.
 *
 * Exercise 3: Fill three Set implementations that you know (Hint: they were described during
 *             the earlier presentation) with the List<String> that is given to this class by
 *             its constructor.
 *
 *             Check out the elements that the list mentioned above contains and then, add them
 *             to your three Sets. After this check out the elements of your Sets. What do you
 *             remark? What could be the reason?
 *
 *             Finally, add to the one of the three Sets some elements
 *             that already exist in the Set (e.g add("that") and add("collection"))
 *
 *             To run your implementation, run the Exercise3Test class.
 */
public class Exercise3 {

    // List containing some elements that need to be added into the Set
    private List<String> listToAdd;

    public Exercise3(List<String> l) {
        listToAdd = l;
    }

    public void addElementsToSets(){
        HashSet<String> hset = new HashSet<String>();
        LinkedHashSet<String> lhset = new LinkedHashSet<String>();
        TreeSet<String> tset = new TreeSet<String>();

        System.out.println("The elements that will be added to the Sets: ");
        for(String s : listToAdd){
            System.out.println(s);
        }


        for(String s : listToAdd){
            hset.add(s);
            lhset.add(s);
            tset.add(s);
        }

        System.out.println("\nThe elements contained in the first Set: ");
        for(String s : hset){
            System.out.println(s);
        }

        System.out.println("\nThe elements contained in the second Set: ");
        for(String s : lhset){
            System.out.println(s);
        }
        System.out.println("\nThe elements contained in the third Set: ");
        for(String s : tset){
            System.out.println(s);
        }

        tset.add(listToAdd.get(0));
        tset.add(listToAdd.get(1));
        hset.add(listToAdd.get(0));
        hset.add(listToAdd.get(1));
        lhset.add(listToAdd.get(0));
        lhset.add(listToAdd.get(1));

        System.out.println("\nThe elements contained in the TreeSet after inserting two duplicates: ");
        for(String s : tset){
            System.out.println(s);
        }
    }
}
