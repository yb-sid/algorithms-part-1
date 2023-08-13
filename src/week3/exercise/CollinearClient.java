package week3.exercise;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * <a href="https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php">...</a>
 */
public class CollinearClient {
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("week3/exercise/input_null.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
        //BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);
        for (LineSegment segment : collinearPoints.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
