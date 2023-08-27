package week3.exercise;

import java.util.Arrays;

public class FastCollinearPoints {
   private LineSegment[] lineSegments;
   private int numberOfSegments;
   public FastCollinearPoints(Point[] points){ // finds all line segments containing 4 or more points
      if (points == null)
         throw new IllegalArgumentException("Points array cannot be null.");
      for(Point p : points){
         if(p == null ) throw new IllegalArgumentException("Points can't have a null point");
      }

      int n = points.length;
      Point[] sortedPoints = Arrays.copyOf(points, n);
      Arrays.sort(sortedPoints);

      for (int i = 1; i < n; i++) {
         if (sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0)
            throw new IllegalArgumentException("Duplicate points are not allowed.");
      }

      lineSegments = new LineSegment[n * n]; // Upper bound on the number of segments
      numberOfSegments = 0;
      //System.out.println("points array sorted : "+Arrays.toString(sortedPoints));
      for (int i = 0; i < n; i++) {
         Point p = sortedPoints[i];
         //System.out.println("P = "+p+" outer index i = "+i);
         Point[] slopesOrder = Arrays.copyOf(sortedPoints, n);
         Arrays.sort(slopesOrder, p.slopeOrder());

         //System.out.println("other points sorted comparator : "+Arrays.toString(slopesOrder));
         int count = 1;
         double currentSlope = Double.NEGATIVE_INFINITY;

         for (int j = 1; j < n; j++) {
            double slope = p.slopeTo(slopesOrder[j]);
            //System.out.println("j="+j+" count = "+count + " currentSlope="+currentSlope+" slope = "+slope);
            if (slope == currentSlope) {
               count++;
            } else {
               if (count >= 3) {
                  addSegmentIfUnique(p, slopesOrder, j - count, j - 1);
               }
               count = 1;
               currentSlope = slope;
            }
         }

         if (count >= 3) {
            addSegmentIfUnique(p, slopesOrder, n - count, n - 1);
         }
      }

   }

   private void addSegmentIfUnique(Point p, Point[] slopesOrder, int start, int end) {
      Point maxPoint = p;
      Point minPoint = p;

      for (int i = start; i <= end; i++) {
         if (slopesOrder[i].compareTo(maxPoint) > 0) {
            maxPoint = slopesOrder[i];
         }
         if (slopesOrder[i].compareTo(minPoint) < 0) {
            minPoint = slopesOrder[i];
         }
      }

      LineSegment newSegment = new LineSegment(minPoint, maxPoint);
      for (int i=0;i<numberOfSegments;i++) {
         if (lineSegments[i].toString().equals(newSegment.toString())) {
            return; // Avoid duplicate segments
         }
      }

      lineSegments[numberOfSegments++] = newSegment;
   }
   public int numberOfSegments(){ // the number of line segments
      return numberOfSegments;
   }
   public LineSegment[] segments(){ // the line segments
      return Arrays.copyOfRange(lineSegments,0,numberOfSegments);
   }

   public static void main(String[] args) {
      Point[] points = new Point[]{null};
      //FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
   }
}