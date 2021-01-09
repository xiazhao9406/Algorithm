package book_1_3;


import com.zx.collection.ArrayStack;
import com.zx.collection.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex12_StackCopy {
    private static Stack<String> copy(Stack<String> stack) {
        final Stack<String> temp = new ArrayStack<>();
        final Stack<String> result = new ArrayStack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            final String item = temp.pop();
            stack.push(item);
            result.push(item);
        }
        return result;
    }

    public static void main(String[] args) {
        final Stack<String> original = new ArrayStack<>();
        original.push("1");
        original.push("2");
        original.push("3");
        original.push("4");
        original.push("5");
        original.push("6");
        original.push("7");
        original.push("8");

        final Stack<String> copy = copy(original);
        while (!copy.isEmpty()) {
            StdOut.println(copy.pop());
        }
        while (!original.isEmpty()) {
            StdOut.println(original.pop());
        }
    }
}
