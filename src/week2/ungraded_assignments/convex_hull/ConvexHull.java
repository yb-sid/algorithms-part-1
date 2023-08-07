package week2.ungraded_assignments.convex_hull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConvexHull {
    private Point findAnchorPoint(List<Point> points){
        Point anchor = points.get(0);
        for(Point p : points){
            if(p.y<anchor.y || (p.y==anchor.y && p.x<anchor.x)){
                anchor = p;
            }
        }
        return anchor;
    }

    private double polarAngle(Point p , Point anchor){
        return Math.atan2(p.y-anchor.y , p.x-anchor.x);
    }

    private boolean isCounteClockWise(Point p1 , Point p2 , Point p3){
        int crossProduct = (p2.x-p1.x) *(p3.y-p1.y) -  (p2.y-p1.y) * (p3.x-p1.x);
        return crossProduct>0;
    }

    public List<Point> convexHull(List<Point> points){
        if(points.size()<3)
            throw new IllegalArgumentException("need at least 3 points");

        Point anchor = findAnchorPoint(points);

        points.sort((p1,p2)->{
            double angle1 = polarAngle(p1,anchor);
            double angle2 = polarAngle(p2,anchor);
            return Double.compare(angle1,angle2);
        });

        Stack<Point> stack = new Stack<>();
        stack.push(points.get(0));
        stack.push(points.get(1));


        for(int i=2;i<points.size();i++){
            while (stack.size()>=2 && !isCounteClockWise(stack.get(stack.size()-2),stack.peek(),points.get(i))){
                stack.pop();
            }
            stack.push(points.get(i));
        }

        return new ArrayList<>(stack);
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        points.add(new Point(5, 5));
        points.add(new Point(1, 5));
        points.add(new Point(5, 1));
        ConvexHull ch = new ConvexHull();
        List<Point> convexHull = ch.convexHull(points);

        // Print the convex hull points
        for (Point p : convexHull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}

class Point{
    int x,y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}
