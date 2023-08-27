package week5.exercise;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;
import java.util.List;

public class PointSET {
   private final SET<Point2D> points;
   public PointSET()  {// construct an empty set of points
      this.points = new SET<>();
   }
   public boolean isEmpty(){  // is the set empty?
      return points.isEmpty();
   }
   public int size(){ // number of points in the set
      return points.size();
   }
   public void insert(Point2D p){
      // add the point to the set (if it is not already in the set)
      if(p==null) throw new IllegalArgumentException("null point can't be inserted");
      if(!points.contains(p)) points.add(p);
    }
   public boolean contains(Point2D p){// does the set contain point p?
      if (p==null) throw new IllegalArgumentException(" cant't check for null points");
      return points.contains(p);
   }
   public void draw(){// draw all points to standard draw
      for(Point2D point : points){
         point.draw();
      }
   }
   public Iterable<Point2D> range(RectHV rect){// all points that are inside the rectangle (or on the boundary)
      if (rect == null){
         throw new IllegalArgumentException("Rectangle can't be null");
      }

      List<Point2D> pointList = new ArrayList<>();
      for(Point2D point : points){
         if (rect.contains(point)){
            pointList.add(point);
         }
      }

      return pointList;

   }
   public Point2D nearest(Point2D p){// a nearest neighbor in the set to point p; null if the set is empty
      if(p == null){
         throw new IllegalArgumentException("p can't be null");
      }
      Point2D nearest = null;
      double minDistance = Double.POSITIVE_INFINITY;
      for(Point2D point : points){
         double distanceToPoint = p.distanceTo(point);
         if(distanceToPoint < minDistance){
            minDistance = distanceToPoint;
            nearest = point;
         }
      }
      return nearest;
   }

   public static void main(String[] args){
      // unit testing of the methods (optional)
   }
}