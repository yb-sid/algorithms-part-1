package week1.union_find;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class QuickUnionWeighted {

    private int[] parent;
    private int[] size;

    public QuickUnionWeighted(int n){
        parent = new int[n];
        size = new int[n];

        for(int i=0;i<n;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int root(int i){
        while(i!=parent[i]){
            i = parent[i];
        }
        return i;
    }

    public boolean connected(int p , int q ){
        return root(p) == root(q);
    }

    public void union(int p , int q ){
        int rootP = root(p);
        int rootQ = root(q);
        if(rootP == rootQ){ // both components are already connected
            return;
        }

        if(size[rootP]<size[rootQ]){
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }else{
            parent[rootQ] = rootP;
            size[rootP] += rootQ;
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
        int i = 1;
        while (i!=n) {
            i++;
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
