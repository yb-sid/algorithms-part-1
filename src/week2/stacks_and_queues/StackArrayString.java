package Week2.stacks_and_queues;

public class StackArrayString {

    private final String[] stack;
    private int N = 0;

    public StackArrayString(int N){
        stack = new String[N];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void push(String item){
        stack[N++] = item;
    }

    public String pop(){
        String item = stack[--N];
        stack[N] = null;
        return item;
    }
}
