package week1.ungraded_assignments.union_find_largest;

import edu.princeton.cs.algs4.StdOut;

public class WeightedUFLargest {
    private final int[] parent;
    private final int[] size;
    private final int[] largest;

    public WeightedUFLargest(int N){
        parent = new int[N];
        size = new int[N];
        largest = new int[N];

        for(int i=0;i<N;i++){
            parent[i] = i;
            size[i] = 1;
            largest[i] = i;
        }
    }

    public int findlargest(int i){
        return largest[find(i)];
    }

    public int find(int i){
        while(i!=parent[i]){
            i = parent[i];
        }
        return i;
    }

    public void union(int A,int B){
        int rootA = find(A);
        int rootB = find(B);
        if(rootA == rootB) return;

        if(size[rootA]<size[rootB]){
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
            largest[rootB] = Math.max(largest[rootB],largest[rootA]);
        }else{
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            largest[rootA] = Math.max(largest[rootB],largest[rootA]);
        }
    }

    public static void main(String[] args) {
        WeightedUFLargest ufLargest = new WeightedUFLargest(10);

        ufLargest.union(1,2);
        ufLargest.union(3,6);
        ufLargest.union(3,8);
        ufLargest.union(6,9);
        ufLargest.union(0,2);
        ufLargest.union(2,4);

        // {1,2,0,4} {3,6,8,9}

        StdOut.println("root of 0 is : "+ ufLargest.find(0));
        StdOut.println("root of 9 is : "+ufLargest.find(9));

        StdOut.println("largest in group1 : "+ufLargest.findlargest(1));
        StdOut.println("largest in group2 : "+ufLargest.findlargest(6));
    }
}
