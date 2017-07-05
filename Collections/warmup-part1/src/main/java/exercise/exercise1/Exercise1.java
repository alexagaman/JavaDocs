package exercise.exercise1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 1: Compute the sum, the minimum and the maximum element from a given list (givenList) using three
 *             different ways to iterate over a List:
 *             a) ListIterator (implement it in the iterateUsingListIterator() method)
 *             b) for loop (implement it in the iterateUsingForLoop() method)
 *             c) foreach loop (implement it in the iterateUsingForEachLoop() method)
 *
 *             In order to test your implementations you need to run the Exercise1Test from the test/java package
 *             (right-click on Exercise1Test class then click Run 'Exercise1Test' )
 */
public class Exercise1{

    // The list that contains some Integer values
    private List<Integer> givenList;

    public Exercise1(List<Integer> l) {
       this.givenList = l;
    }

    // TODO Exercise #1 a) Compute sum and get the min and the max from givenList, iterating through it using ListIterator
    public List<Integer> iterateUsingListIterator(){

        // This List is used only for testing so you don't have to modify it
        List<Integer> testValues = new ArrayList<Integer>();


        Iterator<Integer> it = givenList.iterator();
        Integer sum = 0;
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
        while(it.hasNext()){
            Integer x = it.next();
            sum += x;
            if(x < min)
                min =x;
            if(x > max)
                max =x;
        }


       testValues.add(sum);
       testValues.add(min);
       testValues.add(max);

        return testValues;
    }

    // TODO Exercise #1 b) Compute the sum and get the min and the max from the even (RO: pare) positions in the list,
    // TODO Exercise #1 b) iterating through it using classic for loop
    public List<Integer> iterateUsingForLoop(){

        // This List is used only for testing so you don't need to modify it
        List<Integer> testValues = new ArrayList<Integer>();
        Integer sum = 0;
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;

        for(int i=0; i < givenList.size(); i++){

            if(i %2 == 0){
                sum += givenList.get(i);
                if(givenList.get(i) > max){
                    max = givenList.get(i);
                }
                if(givenList.get(i) < min){
                    min = givenList.get(i);
                }
            }
        }


        testValues.add(sum);
        testValues.add(min);
        testValues.add(max);

        return testValues;
    }

    // TODO Exercise #1 c) Compute the sum and get the min and the max from the odd (RO: impare) elements of the list
    // TODO Exercise #1 c) iterating through it using foreach loop
    public List<Integer> iterateUsingForEachLoop(){

        // This List is used only for testing so you don't need to modify it
        List<Integer> testValues = new ArrayList<Integer>();

        Integer sum = 0;
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;

        for(Integer i : givenList){

            if(i % 2 == 1){
                sum += i;
                if(i > max){
                    max = i;
                }
                if(i < min){
                    min = i;
                }
            }
        }



        testValues.add(sum);
        testValues.add(min);
        testValues.add(max);

        return testValues;
    }
}
