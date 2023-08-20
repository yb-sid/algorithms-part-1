package week4;

public class MaxPQHeap<Key extends Comparable<Key>>{
    private final Key[] pq;
    private int N;

    public MaxPQHeap(int capacity){
        pq = (Key[]) new Comparable[capacity+1];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public void insert(Key x){
        pq[++N] = x;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exchange(1,N--); // exchange max with least
        pq[N+1] = null;
        sink(1); // find correct position for root
        return max;
    }

    private void swim(int k){
        while(k>1 && less(k/2,k)){ // parent is less than current
            exchange(k,k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while(2*k<=N){
            int j = 2*k;
            if(j<N && less(j,j+1))j++;
            if(!less(k,j))break;
            exchange(k,j);
            k = j;
        }
    }

    private boolean less(int i , int j){
        return pq[i].compareTo(pq[j])<0;
    }

    private void exchange(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public Key max(){
        return pq[1];
    }

    public int size(){
        return N;
    }


    public static void main(String[] args) {
        MaxPQHeap<String> maxHeap = new MaxPQHeap<>(10);
        maxHeap.insert("T");
        maxHeap.insert("Q");
        maxHeap.insert("W");
        maxHeap.insert("K");
        maxHeap.insert("U");

        System.out.println("Max of MAX-heap is : "+maxHeap.max());

        String one = maxHeap.delMax();
        String two = maxHeap.delMax();
        System.out.println("First two deletes are : "+one + " and "+two);

        maxHeap.insert("S");

        System.out.println("Size of max heap is : "+maxHeap.size());



    }
}
