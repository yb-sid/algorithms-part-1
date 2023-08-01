package week1.union_find;


// TODO : social network connectivity
//  , union-find with max  , successor with delete
public class QuickFindUF {

    private int[] ids;

    public QuickFindUF(int N){
        ids = new int[N];
        for(int i=0;i<N;i++){
            ids[i] = i;
        }
    }

    // find operation
    public boolean connected(int p , int q ){
        return ids[p] == ids[q];
    }

    // merge two components
    // N union operation - takes N^2
    public void union(int p , int q ){
        int pid = ids[p];
        int qid = ids[q];

        for(int i=0;i<ids.length;i++){
            if(ids[i]==pid){
                ids[i] = qid;
            }
        }
    }
}
