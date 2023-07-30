package week1.exercise;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {

    private final double[] thresholds;
    private final int numTrials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0)
            throw new IllegalArgumentException("n and trials should both be greater then 0");

        thresholds = new double[trials];
        numTrials = trials;
        for(int i=0;i<trials;i++){
            Percolation percolation = new Percolation(n);
            while(!percolation.percolates()){
                int row = StdRandom.uniformInt(n)+1;
                int col = StdRandom.uniformInt(n)+1;
                if(!percolation.isOpen(row,col)){
                    percolation.open(row,col);
                }
            }
            double percolationThreshold = (double) percolation.numberOfOpenSites()/ (n*n);
            thresholds[i] = percolationThreshold;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        double mean = mean();
        double stdDev = stddev();
        return mean - ((1.96*stdDev)/ (Math.sqrt(numTrials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        double mean = mean();
        double stdDev = stddev();
        return mean + ((1.96*stdDev)/ (Math.sqrt(numTrials)));
    }

    // test client (see below)
    public static void main(String[] args){
        int n = 200; // Set the grid size
        int trials = 100; // Set the number of trials
//        int n = Integer.parseInt(args[0]);
//        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n , trials);
        StdOut.println("mean                    = "+percolationStats.mean());
        StdOut.println("stddev                  = "+percolationStats.stddev());
        StdOut.println("95% confidence interval = ["+percolationStats.confidenceLo()+", "+percolationStats.confidenceHi()+"]");
    }
}
