package week2.exercise;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;
    private static final int INIT_CAPACITY = 8;

    // construct an empty randomized queue
    public RandomizedQueue(){
        items = (Item[]) new Object[INIT_CAPACITY];
        size = 0;

    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size==0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if(item==null){
            throw new IllegalArgumentException("Item can't be null");
        }
        if(items.length==size){
            resize(2* items.length);
        }
        items[size++] = item;
    }

    private void resize(int newLen){
        Item[] newItems = (Item[]) new Object[newLen];
        System.arraycopy(items,0,newItems,0,size);
        items = newItems;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()){
            throw new NoSuchElementException("Randomized queue is empty");
        }
        int randomIndex = StdRandom.uniformInt(size);
        Item randomItem = items[randomIndex];
        // handle loitering after removing
        items[randomIndex] = items[--size];
        items[size] = null;
        if(size>0 && size == items.length/4){
            resize(items.length/2);
        }

        return randomItem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new NoSuchElementException("Randomized queue is empty");
        }

        int randomIndex = StdRandom.uniformInt(size);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>{
        private int index;
        private final Item[] randomItems;

        private RandomizedQueueIterator() {
            index = 0;
            randomItems = (Item[]) new Object[size];
            System.arraycopy(items,0,randomItems,0,size);
            shuffleArray(); // extra memory per iterator
        }

        /**
         * generate random index , swap it with element at i
         */
        private void shuffleArray(){
            for(int i=0;i<randomItems.length;i++){
                int swapIndex = StdRandom.uniformInt(i+1);
                Item temp = randomItems[i];
                randomItems[i] = randomItems[swapIndex];
                randomItems[swapIndex] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if(!hasNext()){
                throw new NoSuchElementException("No more elements in Randomized Queue");
            }
            return randomItems[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove operation now allowed");
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        // add items
        randQueue.enqueue("AB");
        randQueue.enqueue("BH");
        randQueue.enqueue("OK");
        randQueue.enqueue("LO");
        randQueue.enqueue("OP");
        randQueue.enqueue("TY");
        randQueue.enqueue("QW");
        randQueue.enqueue("DS");

        StdOut.println("Random QUeue iterator 5 times");
        // test iterator 5 times
        for(int i=0;i<5;i++){
            for(String item : randQueue){
                StdOut.print(item + " ");
            }
            StdOut.println();
        }

        // remove 2 items
        String one = randQueue.dequeue();
        String two = randQueue.dequeue();

        StdOut.println("Dequeue one is : "+one);
        StdOut.println("Dequeue two is :"+two);

        StdOut.println("Sample output is :"+randQueue.sample());

        StdOut.println("rand queue after 2 dequeu and a sample is :");
        for(String item : randQueue){
            StdOut.print(item+" ");
        }
        StdOut.println();
        StdOut.println("is random queue empty :"+randQueue.isEmpty());
        StdOut.println("random queue size :"+randQueue.size());

    }

}