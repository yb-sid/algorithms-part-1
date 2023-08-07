package week1.union_find;

public class QuickUnionUF {

    private final int[] ids;
    public QuickUnionUF(int N) {
        ids = new int[N];
        for(int i=0;i<N;i++)
            ids[i] = i;
    }

    // depth of root - access
    private int root(int i){
        while(i!=ids[i])
            i = ids[i];
        return i;
    }

    // check if p & q have same roots
    public boolean connected(int p , int q ){
        return root(p) == root(q);
    }

    // make root of p to root of q
    public void union(int p , int q ){
        int i = root(p);
        int j = root(q);
        ids[i] = j ;
    }
}
