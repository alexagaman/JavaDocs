package exercise.exercise5;

/**
 * Created by Alexandra.Gaman on 7/5/2017.
 */
public class MyLinkedList<T> {
    private int size;
    private Node<T> first;

    public MyLinkedList() {
        size =0;
        first = null;
    }

    private class Node<T>{
        private T value;
        private Node<T> next;

        Node(){
            value = null;
            next = null;
        }
        Node(T el){
            value = el;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    public void add(T el){
        if(first == null){
            first=new Node<T>(el);
        }
        else{
            Node<T> aux = first;
            while(aux.getNext() != null)
                aux = aux.getNext();
            aux.setNext(new Node<T>(el));
        }
        size++;
    }
    public int size(){
        return size;
    }


    public T remove(int index) throws LinkedListOutOfBoundException {
        if(index >= 0 && index < size){
            T el;
            if(index == 0){
                el = first.value;
                first = first.getNext();
                size--;
                return el;
            }
            int i = 0;
            Node<T> node = first;
            while (i < index - 1) {
                node = node.getNext();
                i++;
            }
            el = node.getNext().getValue();
            node.setNext(node.getNext().getNext());
            size--;
            return el;
        }
        throw new LinkedListOutOfBoundException();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString() {
        String s = "";
        Node<T> node = first;
        while(node != null){
            s += node.value + ",  ";
            node = node.getNext();
        }
        return s;
    }

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<String>();

        list.add("linked");
        list.add("list");
        list.add("exercise");
        list.add("java");
        list.add("colectii");

        list.remove(6);

        System.out.println(list);

    }
}
