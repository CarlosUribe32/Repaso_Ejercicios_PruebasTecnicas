package Ejemplos;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import  edu.princeton.cs.algs4.StdOut;

public class ejemplocontador 
{
    public static void main(String[] args) 
    {
        IContador c1 = new contador("Zapatos");
        IContador c2 = new contador("Cinturones");

        while(StdIn.hasNextLine())
        {
            try
            {
                int v = StdIn.readInt();
                switch(v)
                {
                    case 1: c1.incrementar(); break;
                    case 2: c2.incrementar(); break;
                    default: StdOut.println("Voto no valido");
                }
            }
            catch(NoSuchElementException e)
            {
                StdOut.println("");
            }
        }

        StdOut.println(c1.getId()+" "+c1.getConteo());
        StdOut.println(c2.getId()+" "+c2.getConteo());
    }
}