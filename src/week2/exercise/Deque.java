package week2.exercise;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node{
        Item value;
        Node next;
        Node prev;
    }
    private int size;
    private Node first;
    private Node last;

    // construct an empty deque
    public Deque(){
        first = last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item==null){
            throw new IllegalArgumentException("item can't be null");
        }
        Node oldFirst = first;
        first = new Node();
        first.value = item;
        first.next = oldFirst;
        first.prev = null;
        if(isEmpty()){
            last = first;
        }else{
            oldFirst.prev = first;
        }
        size+=1;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null){
            throw new IllegalArgumentException("item can't be null");
        }
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.next = null;
        last.prev = oldLast;
        if(isEmpty()){
            first = last;
        }else{
            oldLast.next = last;
        }
        size+=1;

    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException("Week2.exercise.Deque is empty");
        }
        Item value = first.value;
        first = first.next;
        if(first!=null){
            first.prev = null;
        }else{
            last = null; // if first null , then last null
        }
        size-=1;
        return value;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()){
            throw new NoSuchElementException("Week2.exercise.Deque is empty");
        }
        Item value = last.value;
        last = last.prev;
        if(last!=null){
            last.next = null;
        }else{
            first = null; // last becomes null, so does first
        }
        size-=1;
        return value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            if(!hasNext()){
                throw new NoSuchElementException("no next element in iterator");
            }
            Item value = current.value;
            current = current.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        // initialize dequeue
        Deque<String> deque = new Deque<>();

        // add elements
        deque.addFirst("one");
        deque.addFirst("two");
        deque.addLast("four");
        deque.addLast("three");

        // print the queue using iterator
        StdOut.println("Week2.exercise.Deque init status :");
        for(String item : deque){
            StdOut.print(item+" ");
        }
        StdOut.println();
        // remove elements
        String removedFirst = deque.removeFirst();
        String removedLast = deque.removeLast();

        StdOut.println("Removed first element :"+removedFirst);
        StdOut.println("Removed last element :"+removedLast);

        StdOut.println("Week2.exercise.Deque status after removing :");
        for(String item : deque){
            StdOut.print(item+" ");
        }
        StdOut.println();

        StdOut.println("is deque empty ? "+ deque.isEmpty());
        StdOut.println("deque current size :"+deque.size);
    }

}