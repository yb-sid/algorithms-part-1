import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if(args.length<1){
            throw new IllegalArgumentException("there should be one argument available");
        }

        int k = Integer.parseInt(args[0]);
        if(k<0){
            throw new IllegalArgumentException("value of k can't be less than 0");
        }

        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        while(!StdIn.isEmpty()){
            String current = StdIn.readString();
            randQueue.enqueue(current);
        }
        int i = 0;
        while(!randQueue.isEmpty() && i<k){
            String randOut = randQueue.dequeue();
            i++;
            StdOut.println(randOut);
        }
    }
}
