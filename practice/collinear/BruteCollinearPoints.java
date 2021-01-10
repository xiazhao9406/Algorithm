import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    LineSegment[] result;

    public BruteCollinearPoints(Point[] points) {
        List<LineSegment> list = new ArrayList<>();

        final int n = points.length;
        for (int _p = 0; _p < n; _p++) {
            final Point p = points[_p];
            if (p == null) {
                throw new IllegalArgumentException();
            }
            for (int _q = _p + 1; _q < n; _q++) {
                final Point q = points[_q];
                final double slopePQ = p.slopeTo(q);
                if (q == null || slopePQ == Double.NEGATIVE_INFINITY)
                    throw new IllegalArgumentException();
                for (int _r = _q + 1; _r < n; _r++) {
                    final Point r = points[_r];
                    final double slopePR = p.slopeTo(r);
                    if (r == null || slopePR == Double.NEGATIVE_INFINITY || q.slopeTo(r) == Double.NEGATIVE_INFINITY)
                        throw new IllegalArgumentException();
                    if (slopePQ != slopePR) continue;
                    for (int _t = _r + 1; _t < n; _t++) {
                        final Point t = points[_t];
                        final double slopePT = p.slopeTo(t);
                        if (t == null || p.slopeTo(t) == Double.NEGATIVE_INFINITY || q.slopeTo(t) == Double.NEGATIVE_INFINITY || r.slopeTo(t) == Double.NEGATIVE_INFINITY) {
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
            result = new LineSegment[list.size()];
            list.toArray(result);
        }
    }

        // finds all line segments containing 4 points
        public int numberOfSegments() {
            return result.length;

        }

        // the number of line segments
        public LineSegment[] segments() {
            return result;

        }
        // the line segments


        public static void main (String[]args){

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

