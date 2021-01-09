package book_1_3;

import com.zx.collection.ArrayStack;
import com.zx.collection.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex09_Expression {
    public static void main(String[] args) {
        final String expression = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
        final String[] tokens = expression.split(" ");

        final Stack<String> operands = new ArrayStack<>();
        final Stack<Character> operators = new ArrayStack<>();

        for (String token: tokens) {
//            StdOut.println(token);
            if (token.length() != 1) {
                continue;
            }
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                operands.push(token);
            }
            if ("+-*/".contains(token)) {
                operators.push(c);
            }
            if (c == ')') {
                final String b = operands.pop();
                final String a = operands.pop();
                final char op = operators.pop();
                operands.push("(" + a + op + b + ")");
            }
        }
        if (!operands.isEmpty()) {
            StdOut.println(operands.pop());
        }
    }
}
