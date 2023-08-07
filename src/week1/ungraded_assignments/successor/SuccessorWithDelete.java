package week1.ungraded_assignments.successor;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class SuccessorWithDelete {
    private final int[] parent;
    private final int[] size;
    private final int N;

    public SuccessorWithDelete(int N){
        this.N = N;
        parent = new int[N];
        size = new int[N];
        for(int i=0;i<N;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int find(int i){
        while(i!=parent[i]){
            i = parent[parent[i]];
        }
        return i;
    }

    private void union(int A, int B){
        int rootA  = find(A);
        int rootB = find(B);

        if(rootA!=rootB){
            if(size[rootA]<=size[rootB]){
                parent[rootA] = rootB;
                size[rootB] += rootA;
            }else{
                parent[rootB] = rootA;
                size[rootA] = rootA;
            }
        }
    }

    public void remove(int x){
        if(x<0 || x>=N ){
            throw new IllegalArgumentException("Illegal value to remove "+x);
        }

        union(x,x+1);
    }

    public int successor(int x){
        if(x<0 || x>=N ){
            throw new IllegalArgumentException("Illegal value to remove "+x);
        }
        int successor = find(x);
        if(successor>=N){
            return -1;
        }
        return successor;
    }

    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(8);
        StdOut.println("Set initialized {0...."+7+"}");
        swd.remove(2);
        swd.remove(4);
        StdOut.println("removed 2 and 4 from set");

        StdOut.println("Successor of 2 is :"+swd.successor(2));
        StdOut.println("Successor of 3 is :"+swd.successor(3));
        StdOut.println("Successor of 4 is :"+swd.successor(4));


    }
}
