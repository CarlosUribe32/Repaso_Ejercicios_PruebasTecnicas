import edu.princeton.cs.algs4.*;
import java.util.Comparator;
class PuntosComparator implements Comparator<String>
    {

        @Override
        public int compare(String o1, String o2) 
        {
            o1 = o1.replace(" ", "");
            o2 = o2.replace(" ", ""); 
            o1 = o1.replace("(", "");
            o1 = o1.replace(")", "");
            o2 = o2.replace("(", "");
            o2 = o2.replace(")", "");
            String [] split1 = o1.split(",");
            String [] split2 = o2.split(",");
            double a = Math.sqrt(Math.pow(Double.parseDouble(split1[0]), 2) + Math.pow(Double.parseDouble(split1[1]), 2) + Math.pow(Double.parseDouble(split1[2]), 2));
            double b = Math.sqrt(Math.pow(Double.parseDouble(split2[0]), 2) + Math.pow(Double.parseDouble(split2[1]), 2) + Math.pow(Double.parseDouble(split2[2]), 2));
            if(a>b)
                return -1;
            else if (a<b)
                return 1;
            else
                return 0;
        }

    }
public class PuntosCercanos 
{ 
    public static void main(String[] args) 
    {
        String [] puntos = {"(3,4,8)","(9,-1,7)","(-6,0,0)", "(24,1,-7)", "(3,5,2)", "(-1,-1,2)", "(-2,3,-5)", "(7,6,-5)", "(1,1,0)", "(0,0,1)", "(7,8,-1)", "(-1,-2,-3)", "(-4,-6,-8)", "(-5,6,7)", "(1,4,-9)"};
        puntosCercanosOrigen(puntos);
    }
    public static void puntosCercanosOrigen(String [] puntos)
    {
        Comparator<String> comparator = new PuntosComparator();
        MinPQ<String> top10 = new MinPQ<>(new PuntosComparator());
        for (int i = 0; i < puntos.length && i<10; i++) {
            top10.insert(puntos[i]);
        }
        if(puntos.length>10)
        {
            for (int i = 10; i < puntos.length; i++) {
                if(comparator.compare(puntos[i], top10.min())<0)
                    continue;
                else
                {
                    top10.delMin();
                    top10.insert(puntos[i]);
                }
            }
        }
        int n = top10.size();
        for (int i = 1; i <= n; i++) {
            StdOut.println(top10.delMin());
        }
    }   
}
