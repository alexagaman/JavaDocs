package exercise.exercise5;

/**
 * Created by Alexandra.Gaman on 7/5/2017.
 */
public class MyLinkedList2<T> {
    private int size;
    private Node<T> first;

    public MyLinkedList2() {
        size = 0;
        first = null;
    }

    private class Node<T>{
        private T value;
        private Node<T> next;
        private Node<T> prev;

        Node(){
            value = null;
            next = null;
        }
        Node(T el){
            value = el;
        }
        Node(T el, Node<T> node){
            value = el;
            prev = node;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void add(T el){
        if(size == 0){
            first=new Node<T>(el);
            first.setPrev(first);
            first.setNext(first);
        }
        else{
            int i = 0;
            Node<T> node= first;
            while(i < size - 1 ){
                node = node.getNext();
                i++;
            }
            Node<T> val = new Node<T>(el);
            val.setPrev(node);
            val.setNext(first);
            first.setPrev(val);
            node.setNext(val);
        }
        size++;
    }

    public T remove(int index) throws LinkedListOutOfBoundException {
        if(index >= 0 && index < size){
            if(size - index > size / 2){
                T el;
                if(index == 0){
                    el = first.value;
                    first.getNext().setPrev(first.getPrev());
                    first.getPrev().setNext(first.getNext());
                    first = first.getNext();
                    size--;
                    return el;
                }
                int i = 0;
                Node<T> node = first;
                while (i < index ) {
                    node = node.getNext();
                    i++;
                }
                el = node.getNext().getValue();
                node.getNext().setPrev(node.getPrev());
                node.getPrev().setNext(node.getNext());
                size--;
                return el;
            }
            else{
                int i = 0;
                Node<T> node = first;
                while (i < size - index ) {
                    node = node.getPrev();
                    i++;
                }
                T el = node.getNext().getValue();
                node.getNext().setPrev(node.getPrev());
                node.getPrev().setNext(node.getNext());
                size--;
                return el;
            }
        }
        throw new LinkedListOutOfBoundException();
    }

    @Override
    public String toString() {
        String s = "";
        Node<T> node = first;
        for(int i=0; i < size; i++){
            s += node.value + ",  ";
            node = node.getNext();
        }
        return s;
    }



    public static void main(String[] args) {
        MyLinkedList2<String> list = new MyLinkedList2<String>();

        list.add("linked");
        list.add("list");
        list.add("exercise");
        list.add("java");
        list.add("colectii");

        System.out.println(list);

        list.remove(2);
        System.out.println(list);
        list.add("Marooooo");

        System.out.println(list);

        list.remove(0);
        list.remove(3);

        System.out.println(list);

    }
}
