package book_1_3;

import com.zx.collection.ArrayQueue;
import com.zx.collection.ArrayStack;
import com.zx.collection.Queue;
import com.zx.collection.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex10_InfixToPostfix {
//    public static String InfixToPostfix(String s) {
//
//        Stack<Character> stack = new ArrayStack<>();
//        Queue<Character> queue = new ArrayQueue<>();
//
//        Character s1 = ' ';
//        char peeks = '#'; // 栈顶元素
//
//        for (int i = 0; i < s.length(); i++) {
//
//            // 如果是操作数，则送入操作数队列
//            if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
//                queue.enqueue(s.charAt(i) );
//                continue;
//            }
//
//            // 如果碰到右括号则遍历栈，并送入队列，直到碰到"("
//            if (s.charAt(i) == ')') {
//                s1 = stack.pop();
//                while (s1 != '(' ) {
//                    queue.enqueue(s1);
//                    s1 = stack.pop();
//                }
//                continue;
//            }
//
//
//            // 如果出现同优先级的运算符, 则将栈顶元素送入队列中 同时新运算符送入栈内
//
//            if ( (s.charAt(i) == '/' || s.charAt(i) == '*') &&
//                    (peeks == '*' || peeks == '/') ) {
//
//                queue.enqueue(stack.pop());
//                stack.push(s.charAt(i));
//                peeks=s.charAt(i);
//            }
//
//            // 剩下的普通情况下的操作符，送入操作符栈
//            {
//                stack.push(s.charAt(i) );
//                peeks = s.charAt(i);
//            }
//
//        }
//
//        s = " ";// 初始化s来接收表达式
//
//        //接收队列的值
//        do {
//            s += queue.dequeue() +" ";
//        } while (!queue.isEmpty());
//
//        //接受栈的值
//        do {
//            s += stack.pop() + "  ";
//        } while (!stack.isEmpty());
//
//        return s;
//    }

    public static void main(String[] args) {
        String expression = "(1 + 42) * 3 + 22 / 5 #";
        expression = expression.replace("(", "( ");
        expression = expression.replace(")", " )");
        final String[] tokens = expression.split(" ");

        final Stack<Character> operators = new ArrayStack<>();
        final Queue<String> result = new ArrayQueue<>();

        operators.push('#');

        for (String token : tokens) {
            if (isOperand(token)) {
                result.enqueue(extractNumber(token));
            } else {
                final char op = token.charAt(0);
                switch (op) {
                    case '(':
                        operators.push('(');
                        break;
                    case ')':
                        while (operators.peek() != '(') {
                            result.enqueue(Character.toString(operators.pop()));
                        }
                        operators.pop();
                        break;
                    default:
                        while (isHigherOrEqualPriority(op, operators.peek())) {
                            result.enqueue(Character.toString(operators.pop()));
                        }
                        operators.push(op);
                        break;
                }
            }
        }

        final StringBuilder stringBuilder = new StringBuilder();
        while (!result.isEmpty()) {
            stringBuilder.append(result.dequeue());
            stringBuilder.append(" ");
        }
        StdOut.println(stringBuilder.toString());

    }

    private static boolean isOperand(String token) {
        return Character.isDigit(token.charAt(0));
    }

    private static String extractNumber(String token) {
        return token;
    }

    private static boolean isHigherOrEqualPriority(char thiz, char that) {
        if (thiz == '*' || thiz == '/') {
            return that == '*' || that == '/';
        } else if (thiz == '+' || thiz == '-') {
            return that == '*' || that == '/' || that == '+' || that == '-';
        } else if (thiz == '#') {
            return that != '#';
        }
        return false;
    }
}
