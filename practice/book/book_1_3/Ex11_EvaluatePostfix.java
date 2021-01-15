package book_1_3;


import com.zx.collection.ArrayStack;
import com.zx.collection.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex11_EvaluatePostfix {
    public static void main(String[] args) {
        final String expression = "1 42 + 3 * 22 5 / + ";
        final String[] tokens = expression.split(" ");
        final Stack<Integer> result = new ArrayStack<>();

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                result.push(Integer.parseInt(token));
            } else {
                result.push(calculate(token.charAt(0), result.pop(), result.pop()));
            }
        }
        StdOut.println(result.pop());

    }

    private static int calculate(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                return b / a;
            default:
                return 0;
        }
    }
}
