package week2.stacks_and_queues;


// TODO : implement generic LL and array stack
//  and queue , write a working code for 2 stack expression evaluator
//  , queue with 2 stacks , stack with max ,
public class QueueLLString {

    private class Node{
        String value;
        Node next;
    }

    private Node first , last ;
    public boolean isEmpty(){
        return first == null;
    }

    public String dequeue(){
        String item = first.value;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }

    public void enqueue(String item){
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.next = null ;
        if(isEmpty()) first = last;
        oldLast.next = last;
    }
}
