import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        final int k = Integer.parseInt(args[0]);
        final RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int count = 0;
        while (!StdIn.isEmpty()) {
            final String str = StdIn.readString();
            count++;
            if (queue.size() >= k) {
                if (StdRandom.uniform() < (1.0 * k / count)) {
                    queue.dequeue();
                    queue.enqueue(str);
                }
            } else {
                queue.enqueue(str);
            }
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }

}
