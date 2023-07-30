package week1.exercise;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

/**
 * <a href="https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php">...</a>
 */
public class Percolation {

    private final int n;
    private final WeightedQuickUnionUF wQUF;
    private final int vTop;
    private final int vBottom;
    private int numOpenSites;
    private boolean[][] grid;
    public Percolation(int n){

        if(n<=0)
            throw new IllegalArgumentException("n can't be less than 0");

        // initialize n * n grid
        this.n = n;
        grid = new boolean[n][n];
        wQUF = new WeightedQuickUnionUF(n*n+2);
        vTop = n*n;
        vBottom = n*n+1;
        numOpenSites = 0;

    }

    private boolean isValidCoordinates(int row, int col) {
        return row-1 >=0 && row-1 < n && col-1 >= 0 && col-1 < n;
    }

    private int xyTo1D(int row , int col){
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Invalid coordinates!");
        }
        return (row - 1) * n + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

        if(!isValidCoordinates(row,col)) {
            throw new IllegalArgumentException("invalid coordinates");
        }

        if(!grid[row-1][col-1]){
            grid[row-1][col-1] = true;
            numOpenSites+=1;

            int siteIndex = xyTo1D(row,col);
            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};

            for(int i=0;i<4;i++){
                int newX = row + dr[i];
                int newY = col + dc[i];
                if(isValidCoordinates(newX,newY) && isOpen(newX,newY)){
                    int newIndex = xyTo1D(newX,newY);
                    wQUF.union(siteIndex,newIndex);
                }
            }
            if(row==1){
                // first row , connect to vTop
                wQUF.union(siteIndex , vTop);
            }
            if (row==n){
                // last row , connect to vBottom
                wQUF.union(siteIndex , vBottom);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(!isValidCoordinates(row,col)){
            //StdOut.println("row ="+row+" col="+col);
            throw new IllegalArgumentException("Invalid coordinates");
        }
        return grid[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(!isValidCoordinates(row,col)){
            throw new IllegalArgumentException("Invalid Coordinates");
        }
        int siteIndex = xyTo1D(row , col);
        return isOpen(row,col) && wQUF.find(vTop) == wQUF.find(siteIndex);

    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return wQUF.find(vTop) == wQUF.find(vBottom);
    }

    // test client (optional)
    public static void main(String[] args){
        int n = 1000;
        Percolation percolation = new Percolation(n);
        int openSites = 0;
        while(!percolation.percolates()){
            int row = StdRandom.uniformInt(n)+1; // random row
            int col = StdRandom.uniformInt(n)+1; // random col
            if(!percolation.isOpen(row,col)){
                percolation.open(row,col);
                openSites +=1;
            }
        }

        double percolationThreshold = (double) openSites/(n*n);
        StdOut.println("Percolation threshold: " + percolationThreshold);

    }
}
