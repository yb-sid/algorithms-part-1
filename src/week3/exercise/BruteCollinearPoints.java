package week3.exercise;

import java.util.Arrays;

public class BruteCollinearPoints {
   private final LineSegment[] lineSegments;
   private final int numberOfSegments;
   public BruteCollinearPoints(Point[] points) {  // finds all line segments containing 4 points
      if (points == null)
         throw new IllegalArgumentException("points array can't be null");

      for(Point p : points){
         if(p == null) throw new IllegalArgumentException("Points can't have a null point");
      }

      Point[] sortedPoints = points.clone();
      Arrays.sort(sortedPoints);
      int N = points.length;
      for(int i=0;i<N-1;i++){ // checks for duplicate
         if(sortedPoints[i].compareTo(sortedPoints[i+1])==0) throw new IllegalArgumentException("points can't have duplicate");
      }
      int numLineSegments = 0;
      lineSegments = new LineSegment[N*N];
      // points a,b,c,d
      for(int a=0;a<N-3;a++){
         for(int b=a+1;b<N-2;b++){
            for(int c=b+1;c<N-1;c++){
               for (int d = c+1;d<N;d++){
                  // check if slopes are equal
                  if(sortedPoints[a].slopeTo(sortedPoints[b])==sortedPoints[a].slopeTo(sortedPoints[c]) &&
                  sortedPoints[a].slopeTo(sortedPoints[b]) == sortedPoints[a].slopeTo(sortedPoints[d])){
                     lineSegments[numLineSegments++] = new LineSegment(sortedPoints[a],sortedPoints[d]);
                  }
               }
            }
         }
      }
      this.numberOfSegments = numLineSegments;
   }
   public int numberOfSegments(){ // the number of line segments
      return numberOfSegments;
   }
   public LineSegment[] segments(){ // the line segments
      return Arrays.copyOfRange(lineSegments,0,numberOfSegments);
   }

}