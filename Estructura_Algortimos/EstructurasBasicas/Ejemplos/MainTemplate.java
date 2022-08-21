import edu.princeton.cs.algs4.StdIn;
import  edu.princeton.cs.algs4.StdOut;
public class MainTemplate 
{
    public static void main(String[] args) 
    {
        StdOut.println("**************");

        /*String a;
        String b;

        a = StdIn.readString().trim();
        b = StdIn.readString().trim();
        StdOut.println(a==b);
        StdOut.println(a.equals(b));
        StdOut.println(a.hashCode());
        StdOut.println(b.hashCode());*/

        Integer a = Integer.parseInt("1");
        Integer b = Integer.valueOf(1);
        Double d = Double.valueOf(1.2345);

        /*StdOut.println(a ==b);
        StdOut.println(a.equals(b));
        StdOut.println(a.hashCode());
        StdOut.println(b.hashCode());
        StdOut.println(d.hashCode());*/

        StdOut.println(a.getClass().getName());
        StdOut.println(d.getClass().getName());
        StdOut.println(a.getClass()== b.getClass()); 
    }    
}