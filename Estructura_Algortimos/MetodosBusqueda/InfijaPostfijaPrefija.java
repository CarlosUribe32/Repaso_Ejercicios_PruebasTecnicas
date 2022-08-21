
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class InfijaPostfijaPrefija {
    
    static class Node {
        Double value;
        String operador;
        Node left,right;
        Node(Double value) {
            this.value = value;
        }
        Node(String operador, Node left, Node right) {
            this.operador = operador;
            this.left = left;
            this.right = right;
        }
        public String toString() {
            if (value!=null) return value.toString();
            return operador;
        }
    }

    public static void infix(Node node) {
        // TODO: Imprimir expresion en notacion infija
        if(node == null) 
            return;
        if(node.value == null)StdOut.print("(");
        infix(node.left);
        StdOut.print(node);
        infix(node.right);
        if(node.value == null)StdOut.print(")");
    }

    public static void postfijo(Node node) {
        // TODO: Imprimir expresion en notacion postfija
        if(node == null)
            return;
        postfijo(node.left);
        postfijo(node.right);
        StdOut.print(node+" ");
    }

    public static void prefijo(Node node) {
        // TODO: Imprimir expresion en notacion prefija
        if(node == null)
            return;
        StdOut.print(node+" ");
        prefijo(node.left);
        prefijo(node.right);
    }

    public static void main(final String[] args) {
        final Stack<Node> pila = new Stack<>();
        while (StdIn.hasNextLine()) {
            try {
                final String token = StdIn.readString();
                Node a, b;
                switch(token) {
                    case "+": 
                    case "-": 
                    case "*": 
                    case "/": 
                        // TODO: Implementar manejo de operadores
                        b = pila.pop();
                        a = pila.pop();
                        Node t = new Node(token, a, b);
                        pila.push(t);
                        break;
                    default: 
                        // TODO: Implementar manejo de valores
                    a = new Node(Double.parseDouble(token));
                    pila.push(a);
                }
            }
            catch(NoSuchElementException e) {}
        }
        Node root = pila.pop();
        //infix(root); StdOut.println("");
        //prefijo(root);
         postfijo(root);
    }


}
