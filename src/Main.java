import queue.EmptyQueueException;
import queue.IQueue;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz wyrażenie:");
        String str = "((4+3)-(2+1)*2+3)/2";
        str = scanner.nextLine();
        BinaryExpressionTree bet = new BinaryExpressionTree();
        Stack<Object> st = new Stack<>();
        IQueue<String> queue = RPNAnalyzer.analize(str);
        boolean doing = true;
        if (doing){
            try {
                while(!queue.isEmpty()){
                    String s = queue.dequeue();
                    if (Objects.equals(s, "+")||Objects.equals(s, "-")||Objects.equals(s, "/")||Objects.equals(s, "*")||Objects.equals(s, "%")){
                        st.push(s);
                    }else{
                        st.push(Integer.parseInt(s));
                    }
                    //System.out.println(queue.dequeue());
                }
            } catch (EmptyQueueException e) {
                e.printStackTrace();
            }
        }

//
        //}
        /*
        st.push(12);
        //st.push(13);
        //st.push("+");
        st.push(3);
        st.push(2);
        st.push("*");
        st.push(6);
        st.push("+");
        st.push("+");

         */
        ///*
        if (!doing){
            st.push(4);
            st.push(3);
            st.push("+");
            st.push(2);
            st.push(1);
            st.push("+");
            st.push(2);
            st.push("*");
            st.push("-");
            st.push(3);
            st.push("+");
            st.push(2);
            st.push("/");
        }


         //*/
        bet.load(st);
        System.out.println("\npostorder: ");
        bet.postorder();
        System.out.print(" = " + bet.result());
        System.out.println("\nNode amount: ");
        System.out.println(bet.count());
        System.out.println("Leaves amount: ");
        System.out.println(bet.leafCount());
        System.out.println("Height: ");
        System.out.println(bet.heightCount());
        System.out.println("Infix notation: ");
        System.out.println(bet.inorderInfix() + " = "+ bet.result());
        System.out.println("Expression result: ");
        System.out.println(bet.result());
        System.out.println("level counter: ");
        bet.levelCounter();

    }
}
