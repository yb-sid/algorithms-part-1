package week2.ungraded_assignments.stack_with_max;

import java.util.Stack;

public class StackWithMax {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    public StackWithMax(){
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int item){
        stack.push(item);

        if(maxStack.isEmpty()|| item > maxStack.peek()){
            maxStack.push(item);
        }
    }

    public int pop(){
        if(stack.isEmpty())
            throw new IllegalArgumentException("Stack is empty to pop");

        int popped = stack.pop();
        if(popped==maxStack.peek()){
            maxStack.pop();
        }

        return popped;
    }

    public int max(){
        return maxStack.peek();
    }

    public static void main(String[] args) {
        StackWithMax stackWithMax = new StackWithMax();

        stackWithMax.push(1);
        stackWithMax.push(7);
        stackWithMax.push(5);
        stackWithMax.push(0);

        System.out.println("max value is : "+stackWithMax.max());
    }
}
