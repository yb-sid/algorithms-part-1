package week5.exercise;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://coursera.cs.princeton.edu/algs4/assignments/kdtree/specification.php">...</a>
 */
public class KdTree {
    private static class Node{
        private Point2D point;
        private Node left;
        private Node right;
        public Node(Point2D point){
            this.point = point;
        }
    }

    private Node root;
    private int size;

    public KdTree(){
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void insert(Point2D p){
        if(p==null){
            throw new IllegalArgumentException("null point can't be inserted");
        }
        root = insert(root  , p , true); // root splits vertically
    }

    private Node insert(Node root , Point2D p , boolean vertical){
        if(root == null){
            size+=1;
            return new Node(p);
        }
        if(root.point.equals(p))return root;
        int comp = vertical ? Double.compare(p.x() , root.point.x()) : Double.compare(p.y(),root.point.y());
        if(comp<0) root.left = insert(root.left , p , !vertical);
        else root.right = insert(root.right,p,!vertical);

        return root;
    }

    public boolean contains(Point2D p){
        if(p==null)throw new IllegalArgumentException("null point can't be checked");

        return contains(root,p,true);
    }

    private boolean contains(Node root, Point2D p , boolean vertical ){
        if(root==null)return false;

        if(root.point.equals(p)) return true;

        int comp = vertical? Double.compare(p.x(),root.point.x()) : Double.compare(p.y(),root.point.y());

        if(comp<0) return contains(root.left , p , !vertical);
        else return contains(root.right,p,!vertical);
    }

    public Iterable<Point2D> range(RectHV rect){
        if(rect==null) throw new IllegalArgumentException("Rectangle can't be null");
        List<Point2D> pointInRange = new ArrayList<>();
        range(root,rect , new RectHV(0,0,1,1),pointInRange,true);
        return pointInRange;
    }

    private void range(Node root , RectHV queryRect , RectHV boundaryRect , List<Point2D> pointsInRange , boolean vertical){
        if(root==null)return;

        if(!boundaryRect.intersects(queryRect))return;

        //check if point is inside rectangle
        if(queryRect.contains(root.point)){
            pointsInRange.add(root.point);
        }// don't return , need to process subtrees

        double xmin = boundaryRect.xmin();
        double ymin = boundaryRect.ymin();
        double xmax = boundaryRect.xmax();
        double ymax = boundaryRect.ymax();
        double x = root.point.x();
        double y = root.point.y();
        if(vertical){
            RectHV leftRect = new RectHV(xmin,ymin,x,ymax);
            RectHV rightRect = new RectHV(x,ymin,xmax,ymax);
            // search left and right subtree
            range(root.left , queryRect,leftRect, pointsInRange,!vertical);
            range(root.right,queryRect,rightRect,pointsInRange,!vertical);
        }else{
            RectHV lowerRect = new RectHV(xmin, ymin , xmax,y);
            RectHV upperRect = new RectHV(xmin,y,xmax,ymax);
            // search lower and upper rectangles
            range(root.left , queryRect , lowerRect , pointsInRange , !vertical);
            range(root.right,queryRect,upperRect, pointsInRange,!vertical);
        }
    }

    public void draw(){
        draw(root , new RectHV(0,0,1,1),true);
    }

    private void draw(Node root , RectHV boundaryRect , boolean vertical){
        if(root == null){
            return;
        }

        // draw the point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        root.point.draw();

        double xmin = boundaryRect.xmin();
        double xmax = boundaryRect.xmax();
        double ymax = boundaryRect.ymax();
        double ymin = boundaryRect.ymin();
        double x = root.point.x();
        double y = root.point.y();
        //Draw line splitting rectangle
        StdDraw.setPenRadius();
        if(vertical){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x,ymin,x,ymax);
        }else{
            // draw horizontal
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(xmin,y,xmax,y);
        }

        // recursive for left and right
        if (vertical){
            RectHV leftRect = new RectHV(xmin,ymin,x,ymax);
            RectHV rightRect = new RectHV(x,ymin,xmax,ymax);

            draw(root.left,leftRect,!vertical);
            draw(root.right,rightRect,!vertical);
        }else{
            RectHV lowerRect = new RectHV(xmin,ymin,xmax,y);
            RectHV upperRect = new RectHV(xmin,y,xmax,ymax);

            draw(root.left,lowerRect,!vertical);
            draw(root.right,upperRect,!vertical);
        }
    }

    public Point2D nearest(Point2D p){
        if(p==null)throw new IllegalArgumentException("Null point can't be checked");

        if(isEmpty()) return null;

        return nearest(root,p, root.point , new RectHV(0,0,1,1),true);
    }

    private Point2D nearest(Node root, Point2D queryPoint , Point2D nearestPoint, RectHV boundaryRect,boolean vertical){
        if(root==null) return nearestPoint;

        double distQueryToNearest = nearestPoint.distanceSquaredTo(queryPoint);
        double distQueryToNode = root.point.distanceSquaredTo(queryPoint);

        if(distQueryToNode < distQueryToNearest){
            nearestPoint = root.point;
        }

        double xmin = boundaryRect.xmin();
        double xmax = boundaryRect.xmax();
        double ymin = boundaryRect.ymin();
        double ymax = boundaryRect.ymax();
        double x = root.point.x();
        double y = root.point.y();
        RectHV firstRect, secondRect;

        if (vertical) {
            if (queryPoint.x() < root.point.x()) { // query point on left of vertical line
                firstRect = new RectHV(xmin, ymin ,  root.point.x(), ymax);
                secondRect = new RectHV(root.point.x(), ymin, xmax,ymax);
            } else { // reverse previous values
                firstRect = new RectHV(root.point.x(), ymin,xmax,ymax);
                secondRect = new RectHV(xmin, ymin, root.point.x(),ymax);
            }
        } else {
            if (queryPoint.y() < root.point.y()) { // query point below horizontal line
                firstRect = new RectHV(xmin,ymin, xmax, root.point.y());
                secondRect = new RectHV(xmin, root.point.y(),xmax, ymax);
            } else { // reverse values
                firstRect = new RectHV(xmin, root.point.y(),xmax, ymax);
                secondRect = new RectHV(xmin,ymin, xmax, root.point.y());
            }
        }
        // find closer side
        Node first , second;
        if((vertical && queryPoint.x() < root.point.x())|| (!vertical && queryPoint.y()<root.point.y())){
            first = root.left;
            second = root.right;
        }else{
            first = root.right;
            second = root.left;
        }
        // find the nearest point in closer side
        nearestPoint = nearest(first , queryPoint , nearestPoint , firstRect,!vertical);

        if(secondRect.distanceSquaredTo(queryPoint) < nearestPoint.distanceSquaredTo(queryPoint)){
            nearestPoint = nearest(second,queryPoint,nearestPoint,secondRect,!vertical);
        }

        return nearestPoint;
    }


}
