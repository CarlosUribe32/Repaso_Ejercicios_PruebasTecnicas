import edu.princeton.cs.algs4.StdOut;
public class PruebasNumeroRacional 
{
    public static void main(String[] args) throws Exception 
    {
        try 
        {
            NumeroRacional a = new NumeroRacional(4,3);
            NumeroRacional b = new NumeroRacional(1,2);
            NumeroRacional c = new NumeroRacional(4,3);

            StdOut.println(a.compareTo(c));
            StdOut.println(a.compareTo(b));
            StdOut.println(b.compareTo(c));

            StdOut.println(a+"\n"+b);
            StdOut.println(a.equals(b));

            StdOut.println(a.Suma(b));
            StdOut.println(a.Resta(b));
            StdOut.println(a.Multiplicacion(b));
            StdOut.println(a.Division(b));

        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }  
    }    
}