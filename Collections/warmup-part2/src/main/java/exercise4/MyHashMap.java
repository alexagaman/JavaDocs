package exercise4;

import java.util.*;

/**
 * Exercise 3. Implement a HashMap from scratch. In order to pass all the tests
 * you need to implement all the methods defined below. The key-value pair will
 * be encapsulated in the MyHashMap.MyEntry object defined below.
 *
 * The buckets list in which each MyEntry object will be stored is stored in "buckets" object.
 * The hash function that you will use in order to store a pair in a specific bucket will be
 * the one presented earlier: (hashcode value) modulo (bucket array size)
 */
public class MyHashMap {

    private ArrayList<LinkedList<MyEntry>> buckets;
    private int size ;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMap() {

        //  Initialize buckets list
        buckets = new ArrayList<LinkedList<MyEntry>>();
        for(int i=0; i<BUCKET_ARRAY_SIZE; i++){
            buckets.add(new LinkedList<MyEntry>());
        }
        size = 0;
    }

    public String get(String key) {
        //
        if(key == null){
            LinkedList<MyEntry> list = buckets.get(Math.abs(0));
            for (MyEntry ent : list) {
                if (ent.getKey()==null)
                    return ent.getValue();
            }
        }
        else {
            LinkedList<MyEntry> list = buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE));
            for (MyEntry ent : list) {
                if (ent.getKey().equals(key))
                    return ent.getValue();
            }
        }
        return null;
    }

    public void put(String key, String value) {
        //
        if(key == null){
            LinkedList<MyEntry> list = buckets.get(0);
            boolean found = false;
            for (MyEntry ent : list) {
                if (ent.getKey()==null) {
                    ent.setValue(value);
                    found = true;
                    break;
                }
            }
            if (!found) {
                list.add(new MyEntry(key, value));
                size++;
            }
        }
        else {
            LinkedList<MyEntry> list = buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE));
            boolean found = false;
            for (MyEntry ent : list) {
                if (ent.getKey().equals(key)) {
                    ent.setValue(value);
                    found = true;
                    break;
                }
            }
            if (!found) {
                list.add(new MyEntry(key, value));
                size++;
            }
        }
    }

    public Set<String> keySet() {
        //
        Set<String> kset = new HashSet<String>();
        for(LinkedList<MyEntry> l : buckets){
            for(MyEntry e : l){
                kset.add(e.getKey());
            }
        }
        return kset;
    }

    public Collection<String> values() {
        //
        List<String> vals = new ArrayList<String>();
        for(LinkedList<MyEntry> l : buckets){
            for(MyEntry e : l){
                vals.add(e.getValue());
            }
        }
        return vals;
    }

    public String remove(String key) {
        //  Returns the value associated with the key removed from the map or null if the key wasn't found
        String ret = null;
        LinkedList<MyEntry> list = buckets.get(Math.abs(key.hashCode()%BUCKET_ARRAY_SIZE));
        Iterator<MyEntry> it = list.iterator();
        while(it.hasNext()){
            MyEntry aux = it.next();
            if(aux.getKey().equals(key)) {
                it.remove();
                ret = aux.getValue();
                size--;
                break;
            }
        }

        return ret;
    }

    public boolean containsKey(String key) {
        //
        if(key == null){
            LinkedList<MyEntry> list = buckets.get(Math.abs(0));
            for (MyEntry ent : list) {
                if (ent.getKey()==null)
                    return true;
            }
        }
        else {
            LinkedList<MyEntry> list = buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE));
            for (MyEntry ent : list) {
                if (ent.getKey().equals(key))
                    return true;
            }
        }
        return false;
    }

    public boolean containsValue(String value) {
        //
        for(LinkedList<MyEntry> l : buckets){
            for(MyEntry e : l){
                if(value!= null){
                    if(value.equals(e.value))
                        return true;
                }
                else  if(e.value == null)
                    return true;
            }
        }
        return false;
    }

    public int size() {
        //  Return the number of the Entry objects stored in all the buckets
        return size;
    }

    public void clear() {
        // Remove all the Entry objects from the bucket list
        for(LinkedList<MyEntry> l : buckets){
            size -= l.size();
            l.clear();
        }
    }

    public Set<MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyEntry> ents = new HashSet<MyEntry>();
        for(LinkedList<MyEntry> l : buckets){
            for(MyEntry e : l)
                ents.add(e);
        }
        return ents;
    }

    public boolean isEmpty() {
        //
        return size==0;
    }

    public static class MyEntry implements  Map.Entry<String,String>{
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public String setValue(String value) {
            this.value = value;
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null) return false;
            if(!(o instanceof Map.Entry)) return false;
            Map.Entry<String,String> p = (Map.Entry<String,String>) o;
            if(getKey().equals(p.getKey()) && getValue().equals(p.getValue()))
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            int res = 17;
            res = 31 * res + getKey().hashCode();
            res = 31 * res + getValue().hashCode();
            return res;
        }

        @Override
        public String toString() {
            return ""+key+" = " + value;
        }
    }
}
