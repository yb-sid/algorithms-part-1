package week2.ungraded_assignments.queue_with_stacks;

import java.util.Stack;

public class QueueWithStacks<T> {
    private Stack<T> inStack;
    private Stack<T> outStack;

    public QueueWithStacks(){
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void enqueue(T item){
        // enqueue O(1)
        inStack.push(item);
    }

    public T dequeue(){
        if(outStack.isEmpty()){
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        if(outStack.isEmpty()){
            return null;
        }
        return outStack.pop();
    }

    public boolean isEmpty(){
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public int size(){
        return inStack.size()+outStack.size();
    }

    public static void main(String[] args) {
        QueueWithStacks<Integer> queue = new QueueWithStacks<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println("Dequeue: "+queue.dequeue());
        queue.enqueue(5);
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());

    }
}
