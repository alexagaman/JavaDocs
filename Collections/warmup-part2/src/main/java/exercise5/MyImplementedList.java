package exercise5;


import java.util.Comparator;
import java.util.Iterator;

/**
 * You should implement from zero a data structure that acts as an ArrayList.
 * We have a default capacity of {@link MyImplementedList#DEFAULT_CAPACITY} elements of type <code>E</code>.
 * We have a {@link MyImplementedList#size} property that stores the number of elements of type <code>E</code> from the data structure.
 * We have an array property, {@link MyImplementedList#elementData}, that keeps the elements from the data structure.
 * We have a {@link MyImplementedList#LOAD_FACTOR} property that specify the maximum accepted load of the data structure.
 * We have a {@link MyImplementedList#INCREASE_SIZE_FACTOR} property to use it when we must increase the capacity of the data structure.
 * We have a {@link MyImplementedList#capacityAfterExtending} property to use it to retain the new capacity after increasing it.
 * <p>
 * Starting with this properties we must implement a data structure that acts ~ as an ArrayList for some objects of type <code>E</code>.
 * <p>
 * @author Cristian.Dumitru
 * @since 7/3/2017.
 */
public class MyImplementedList<E> implements  Iterable{

    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * The array buffer into which the elements of the {@link MyImplementedList} are stored.
     */
    private Object[] elementData;

    /**
     * Property to keep the extended capacity.
     */
    private int capacityAfterExtending;

    public MyImplementedList() {
        elementData = new Object[DEFAULT_CAPACITY] ;
        capacityAfterExtending = DEFAULT_CAPACITY;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean add(E e){
        if((size+1)/capacityAfterExtending > LOAD_FACTOR){
            Object[] aux = elementData;
            capacityAfterExtending *= INCREASE_SIZE_FACTOR;
            elementData = new Object[capacityAfterExtending];
            for(int i = 0; i< size; i++){
                elementData[i] = aux[i];
            }
        }
        elementData[size++] = e;
        return true;
    }

    public boolean isEmpty(){

        if(size == 0)
            return true;
        else return false;
    }

    public boolean contains(Object o){
        for(int i=0 ;i < size; i++){
            if(elementData[i].equals(o))
                return true;
        }
        return false;
    }

    public int indexOf(Object o){
        for(int i=0 ;i < size; i++){
            if(elementData[i].equals(o))
                return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o){
        for(int i=size-1 ;i >= 0; i--){
            if(elementData[i].equals(o))
                return i;
        }
        return -1;
    }


    public E get(int index){
        if(index < size && index >=0){
            return (E)elementData[index];
        }
        throw new IndexOutOfBoundsException();

    }

    public E set(int index, E element){
        if(index < size && index >=0){
            E el = (E) elementData[index];
            elementData[index] = element;
            return el;
        }
        throw new IndexOutOfBoundsException();
    }

    public  E remove(int index){
        if(index < size && index >=0){
            E el = (E) elementData[index];
            for(int i = index; i<size-1; i++){
                elementData[i]=elementData[i+1];
            }
            size--;
            return el;
        }
        throw new IndexOutOfBoundsException();
    }

    public void extendCapacity(int capacity){
        if(capacity > capacityAfterExtending) {
            if ((size + 1) / capacityAfterExtending > LOAD_FACTOR) {
                Object[] aux = elementData;
                capacityAfterExtending = capacity;
                elementData = new Object[capacityAfterExtending];
                System.arraycopy(aux, 0, elementData, 0, size);
            }
        }
    }

    class MyIterator implements Iterator{
        private int current;
        private MyImplementedList list;

        public MyIterator(MyImplementedList list) {
            this.list = list;
            current = -1;
        }

        public boolean hasNext() {
            return list.size > current + 1;
        }

        public Object next() {
            return list.get(++current);
        }

        public void remove() {
            list.remove(current--);
        }
    }

    public Iterator iterator() {
        return new MyIterator(this);
    }

    public void sort(Comparator<? super E> c){
        boolean sem = true;
        while(sem){
            sem = false;
            for(int i = 0; i < size-1; i++){
                for(int j = i+1; j < size; j++){
                    if(c.compare((E)elementData[i], (E)elementData[j]) < 0){
                        Object aux = elementData[i];
                        elementData[i] = elementData[j];
                        elementData[j] = aux;
                        sem = true;
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(int i =0; i< size; i++){
            s += elementData[i] + "   ";
        }
        return s;
    }
}
