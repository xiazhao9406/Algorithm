import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] lineSegment;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        final int n = points.length;

        final List<LineSegment> list = new ArrayList<>();
        final Point[] pts = new Point[n];
        final Point[] tempPoints = new Point[n];

        for (int i = 0; i < n; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            pts[i] = points[i];
            tempPoints[i] = points[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Double.NEGATIVE_INFINITY == pts[i].slopeTo(pts[j]))
                    throw new IllegalArgumentException();
            }
        }

        Arrays.sort(pts);

        if (pts.length < 4) {
            lineSegment = new LineSegment[0];
            return;
        }

        for (final Point point : pts) {
            Arrays.sort(tempPoints, point.slopeOrder());
            int s = 1, e = s + 1;
            while (s < n) {
                if (e == n || point.slopeTo(tempPoints[s]) != point.slopeTo(tempPoints[e])) {
                    if (e - s >= 3) {
                        final int count = e - s;
                        final Point[] lineSegmentPoints = new Point[count + 1];
                        for (int k = 0; k < count; k++) {
                            lineSegmentPoints[k] = tempPoints[s + k];
                        }
                        lineSegmentPoints[count] = point;
                        Arrays.sort(lineSegmentPoints);
                        if (Double.NEGATIVE_INFINITY == point.slopeTo(lineSegmentPoints[0])) {
                            list.add(new LineSegment(point, lineSegmentPoints[count]));
                        }
                    }
                    s = e;
                }
                e++;
            }
        }

        lineSegment = new LineSegment[list.size()];
        list.toArray(lineSegment);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegment == null ? 0 : lineSegment.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegment.clone();
    }
}
