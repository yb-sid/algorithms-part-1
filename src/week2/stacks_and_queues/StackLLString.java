package week2.stacks_and_queues;

public class StackLLString {

    private class Node{
        String value;
        Node next;
    }

    private Node first = null;

    public boolean isEmpty(){
        return first == null;
    }

    public void push(String item){
        Node oldFirst = first;
        first = new Node();
        first.value = item;
        first.next = oldFirst;
    }

    public String pop(){
        String top = first.value;
        first = first.next;
        return top;
    }
}
