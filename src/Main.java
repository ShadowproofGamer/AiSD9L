import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        BinaryExpressionTree bet = new BinaryExpressionTree();
        Stack<Object> st = new Stack<>();
        ///*
        st.push(12);
        //st.push(13);
        //st.push("+");
        st.push(3);
        st.push(2);
        st.push("*");
        st.push(6);
        st.push("+");
        st.push("+");

         //*/
        //4.0, 3.0, +, 2.0, 1.0, +, 2.0, *, -, 3.0, +, 2.0, /
        /*
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

         */
        bet.load(st);
        //bet.insert("+");
        //System.out.println("preorder: ");
        //bet.preorder();
        //System.out.println("\ninorder: ");
        //bet.inorder();
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

    }
}
