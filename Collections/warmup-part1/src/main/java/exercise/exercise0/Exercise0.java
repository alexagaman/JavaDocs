package exercise.exercise0;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughList(){

        ArrayList<Integer> lista = new ArrayList<Integer>();
        for(int i = 0; i < 10 ; i++){
            lista.add(i);
        }
        Iterator<Integer> it = lista.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        for(int i = 0; i < 10 ; i++){
            System.out.println(lista.get(i));
        }
        for(Integer i : lista){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        Exercise0 ex0 = new Exercise0();
        ex0.iterateThroughList();
    }
}
