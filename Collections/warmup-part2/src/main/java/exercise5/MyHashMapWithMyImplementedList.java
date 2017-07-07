package exercise5;

import exercise4.MyHashMap;

import java.util.*;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyHashMapWithMyImplementedList {

    //  uncomment the following line and add your MyImplementedList implementation to the project
    private MyImplementedList<LinkedList<MyEntry>> buckets;
    private int size;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMapWithMyImplementedList() {
        //
        buckets = new MyImplementedList<LinkedList<MyEntry>>();
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
        for(int i =0 ; i< BUCKET_ARRAY_SIZE; i++){
            for(MyEntry e : buckets.get(i)){
                kset.add(e.getKey());
            }
        }
        return kset;
    }

    public Collection<String> values() {
        //
        List<String> vals = new ArrayList<String>();
        for(int i =0; i<BUCKET_ARRAY_SIZE; i++){
            for(MyEntry e : buckets.get(i)){
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
        for(int i=0; i< BUCKET_ARRAY_SIZE; i++){
            for(MyEntry e : buckets.get(i)){
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
        //  Remove all the Entry objects from the bucket list
        for(int i=0; i< BUCKET_ARRAY_SIZE; i++){
            size -= buckets.get(i).size();
            buckets.get(i).clear();
        }
    }

    public Set<MyEntry> entrySet() {
        //  Return a Set containing all the Entry objects
        Set<MyEntry> ents = new HashSet<MyEntry>();
        for(int i=0; i< BUCKET_ARRAY_SIZE; i++){
            for(MyEntry e : buckets.get(i))
                ents.add(e);
        }
        return ents;
    }

    public boolean isEmpty() {
        //
        return size==0;
    }

    public static class MyEntry {
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

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return ""+key+" = " + value;
        }
    }
}
