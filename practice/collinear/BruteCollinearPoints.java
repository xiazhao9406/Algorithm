import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] result;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();
        final List<LineSegment> list = new ArrayList<>();

        final int n = points.length;
        for (int pIndex = 0; pIndex < n; pIndex++) {
            final Point p = points[pIndex];
            if (p == null) {
                throw new IllegalArgumentException();
            }
            for (int qIndex = pIndex + 1; qIndex < n; qIndex++) {
                final Point q = points[qIndex];
                if (q == null)
                    throw new IllegalArgumentException();
                final double slopePQ = p.slopeTo(q);
                if (slopePQ == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
                for (int rIndex = qIndex + 1; rIndex < n; rIndex++) {
                    final Point r = points[rIndex];
                    if (r == null)
                        throw new IllegalArgumentException();
                    final double slopePR = p.slopeTo(r);
                    if (slopePR == Double.NEGATIVE_INFINITY || q.slopeTo(r) == Double.NEGATIVE_INFINITY) {
                        throw new IllegalArgumentException();
                    }
                    if (slopePQ != slopePR) continue;
                    for (int tIndex = rIndex + 1; tIndex < n; tIndex++) {
                        final Point t = points[tIndex];
                        if (t == null) {
                            throw new IllegalArgumentException();
                        }
                        final double slopePT = p.slopeTo(t);
                        if (slopePT == Double.NEGATIVE_INFINITY || q.slopeTo(t) == Double.NEGATIVE_INFINITY || r.slopeTo(t) == Double.NEGATIVE_INFINITY) {
                            throw new IllegalArgumentException();
                        }
                        if (slopePQ == slopePT) {
                            final Point[] segmentPoints = {p, q, r, t};
                            Arrays.sort(segmentPoints);
                            list.add(new LineSegment(segmentPoints[0], segmentPoints[3]));
                        }
                    }
                }
            }
        }
        result = new LineSegment[list.size()];
        list.toArray(result);
    }

    // finds all line segments containing 4 points
    public int numberOfSegments() {
        return result.length;

    }

    // the number of line segments
    public LineSegment[] segments() {
        return result.clone();
    }
    // the line segments


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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

