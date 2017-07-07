package exercise6;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Create a resizable generic HashMap. When the number of entries exceeds (load capacity * bucket array size)
 * the HashMap needs to be resized.
 *
 * Resizing (rehashing) consists in increasing the size of the bucket array with the value of
 * INCREASE_SIZE_FACTOR. After this step, all the entries that were stored in the HashMap
 * must be inserted in the new bucket array according to the insertion algorithm in a HashMap:
 * the entry must be placed in a bucket after applying the hash function (hashcode modulo (bucket array size))
 * on the key's hashcode value. The result of the hash function will return the index from the
 * bucket array where the entry will be stored. (see HashMap documentation)
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyResizableHashMap<K, V> {

    /**
     * The array of buckets.
     */
    private Node<K, V>[] buckets;

    /**
     * Default initial capacity for the bucket array.
     */
    private final int DEFAULT_BUCKET_ARRAY_SIZE = 16;

    /**
     * The maximum accepted load property of the data structure.
     */
    private static final double LOAD_FACTOR = 0.75d;

    /**
     * The factor for increasing the size of the data structure.
     */
    private static final int INCREASE_SIZE_FACTOR = 2;

    /**
     * The number of entries stored in the Map.
     *
     */
    private int capacity;
    private int size;

    public MyResizableHashMap() {

        //  Initialize buckets list
        buckets = new Node[DEFAULT_BUCKET_ARRAY_SIZE];
        capacity = DEFAULT_BUCKET_ARRAY_SIZE;
    }

    private void resize() {
        //  function that does the rehashing of the HashMap
        Set<MyEntry> entries = entrySet();
        capacity *= INCREASE_SIZE_FACTOR;
        size = 0;
        buckets = new Node[capacity];
        for(MyEntry ent : entries){
            put((K)ent.getKey(),(V)ent.getValue());
        }
    }

    public V get(K key) {
        if(key == null){
            V element = null;
            Node<K, V> node = buckets[0];
            while (node != null) {
                if (node.getEntry().getKey() == null) {
                    element = node.getEntry().getValue();
                    break;
                }
                node = node.getNextElement();
            }
            return element;
        }
        else {
            int index = Math.abs(key.hashCode() % capacity);
            V element = null;
            Node<K, V> node = buckets[index];
            while (node != null) {
                if (node.getEntry().getKey().equals(key)) {
                    element = node.getEntry().getValue();
                    break;
                }
                node = node.getNextElement();
            }
            return element;
        }
    }

    public void put(K key, V value) {



        if(key == null) {
            Node<K, V> node = buckets[0];
            boolean found = false;
            while (node != null) {
                if (node.getEntry().getKey()== null) {
                    found = true;
                    node.getEntry().setValue(value);
                    break;
                }
                node = node.getNextElement();
            }
            if (!found) {
                buckets[0] = new Node<K, V>(new MyEntry<K, V>(key, value), 0,
                        buckets[0]);
                size++;
            }
        }
        else {
            int index = Math.abs(key.hashCode() % capacity);
            Node<K, V> node = buckets[index];
            boolean found = false;
            while (node != null) {
                if (node.getEntry().getKey().equals(key)) {
                    found = true;
                    node.getEntry().setValue(value);
                    break;
                }
                node = node.getNextElement();
            }
            if (!found) {
                buckets[index] = new Node<K, V>(new MyEntry<K, V>(key, value), Math.abs(key.hashCode() % capacity),
                        buckets[index]);
                size++;
            }
        }
        if((size+1)/capacity > LOAD_FACTOR)
            resize();

    }

    public Set<K> keySet() {
        //
        Set<K> keys = new HashSet<K>();
        for(int i=0; i< capacity; i++){
            Node<K,V> node = buckets[i];
            while (node != null){
                keys.add(node.getEntry().getKey());
                node = node.getNextElement();
            }
        }
        return keys;
    }

    public Collection<V> values() {
        Set<V> vals = new HashSet<V>();
        for(int i=0; i< capacity; i++){
            Node<K,V> node = buckets[i];
            while (node != null){
                vals.add(node.getEntry().getValue());
                node = node.getNextElement();
            }
        }
        return vals;
    }

    public V remove(K key) {
        // Returns the value associated with the key removed from the map or null if the key wasn't found
        if(key == null) {
            Node<K, V> node = buckets[0];
            V el = null;
            if(node != null) {
                if (node.getNextElement() == null) {
                    if (node.getEntry().getKey() == null) {
                        el = node.getEntry().getValue();
                        buckets[0] = null;
                        size--;
                    }
                } else {
                    while (node.getNextElement() != null) {
                        if (node.getNextElement().getEntry().getKey() == null) {
                            el = node.getNextElement().getEntry().getValue();
                            node.setNextElement(node.getNextElement().getNextElement());
                            size--;
                            break;
                        }
                        node = node.getNextElement();

                    }
                }
            }
            return el;

        }
        else {
            int index = Math.abs(key.hashCode() % capacity);
            Node<K, V> node = buckets[index];
            V el = null;
            if(node != null) {
                if (node.getNextElement() == null) {
                    if (key.equals(node.getEntry().getKey())) {
                        el = node.getEntry().getValue();
                        buckets[index] = null;
                        size--;
                    }
                } else {
                    while (node.getNextElement() != null) {
                        if (key.equals(node.getNextElement().getEntry().getKey())) {
                            el = node.getNextElement().getEntry().getValue();
                            node.setNextElement(node.getNextElement().getNextElement());
                            size--;
                            break;
                        }
                        node = node.getNextElement();
                    }
                }
            }
            return el;
        }
    }

    public boolean containsKey(K key) {
        //
        if(key == null){
            Node<K, V> node = buckets[0];
            while (node != null) {
                if (node.getEntry().getKey() == null)
                    return true;
            }
        }
        else {
            Node<K, V> node = buckets[Math.abs(key.hashCode() % capacity)];
            while (node != null) {
                if (key.equals(node.getEntry().getKey()))
                    return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        //
        for(int i=0; i< capacity; i++){
            Node<K,V> node = buckets[i];
            while (node != null){
                if(value.equals(node.getEntry().getValue()))
                    return true;
                node = node.getNextElement();
            }
        }
        return false;
    }

    public int size() {
        //  Return the number of the Entry objects stored in all the buckets
        return size;
    }

    public void clear() {
        //  Remove all the Entry objects from the bucket list
        for(int i=0; i< capacity; i++) {
            if (buckets[i] != null) {
                while (buckets[i].getNextElement() != null) {
                    Node<K, V> node = buckets[i];
                    while (node.getNextElement() != null) {
                        node = node.getNextElement();
                    }
                    node.setNextElement(null);
                    size --;
                }
                buckets[i] = null;
                size--;
            }
        }
    }

    public Set<MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyEntry> entries = new HashSet<MyEntry>();
        for(int i=0; i< capacity; i++){
            Node<K,V> node = buckets[i];
            while (node != null){
                entries.add(node.getEntry());
                node = node.getNextElement();
            }
        }
        return entries;
    }

    public boolean isEmpty() {
        //
        return size==0;
    }

    public static class MyEntry<K, V> {
        private K key;
        private V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return ""+key+"="+value;
        }
    }

    static class Node<K, V> {
        private final MyEntry<K, V> entry;
        private final int hash;
        private Node<K, V> nextElement;

        public Node(MyEntry<K, V> entry, int hash, Node<K, V> nextElement) {
            this.entry = entry;
            this.hash = hash;
            this.nextElement = nextElement;
        }

        public MyEntry<K, V> getEntry() {
            return entry;
        }

        public int getHash() {
            return hash;
        }

        public Node<K, V> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<K, V> nextElement) {
            this.nextElement = nextElement;
        }

        @Override
        public String toString() {
            return ""+entry.getValue() +" "+entry.getKey();
        }
    }

}
