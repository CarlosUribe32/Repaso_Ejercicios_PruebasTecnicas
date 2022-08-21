import edu.princeton.cs.algs4.StdOut;

public class NumeroRacional implements Comparable
{
    private int p;
    private int q;

    public int getP ()
    {
        return p;
    }
    public int getQ ()
    {
        return q;
    }
    
    public NumeroRacional(int p, int q)throws Exception
    {
        if(q==0)
            throw new Exception("No se puede tener un racional cuya fracción correspondiente tiene denominador 0");
        if(p<0 && q<0)
            q = Math.abs(q);
        else if (q<0)
        {
            p = -1*p;
            q = Math.abs(q);
        }
        this.p = p;
        this.q = q;
    }

    public NumeroRacional Suma (NumeroRacional b) throws Exception
    {
        if(b.getP()==0)
            return new NumeroRacional(p, q);
        int den = q * b.getQ();
        int num = den/q*p+den/b.getQ()*b.getP();
        return new NumeroRacional(num, den);
    }
    public NumeroRacional Resta (NumeroRacional b) throws Exception
    {
        if(b.getP()==0)
            return new NumeroRacional(p, q);
        int den = q * b.getQ();
        int num = den/q*p-den/b.getQ()*b.getP();
        return new NumeroRacional(num, den);
    }
    public NumeroRacional Multiplicacion (NumeroRacional b) throws Exception
    {
        int den = q * b.getQ();
        int num = p*b.getP();
        return new NumeroRacional(num, den);
    }
    public NumeroRacional Division (NumeroRacional b) throws Exception
    {
        int den = q * b.getP();
        int num = p*b.getQ();
        return new NumeroRacional(num, den);
    }

    

    @Override
    public String toString()
    {
        if (p==0)
            return 0+"";
        return p+"/"+q;
    }

    @Override
    public boolean equals(Object b)
    {
        NumeroRacional c = (NumeroRacional)b;
        if(p==c.getP() && q==c.getQ())
            return true;
        else
            return false;
    }

    @Override
    public int compareTo(Object o)
    {
        try 
        {
            NumeroRacional b = Resta((NumeroRacional)o);
            if (b.getP()<0)
                return -1;
            else if (b.getP()>0)
                return 1;
            else
                return 0;   
        } 
        catch (Exception e) 
        {
            return -1;
        }
    }

    public static void main(String[] args) throws Exception
    {
        NumeroRacional a = new NumeroRacional(1,5);
        NumeroRacional b = new NumeroRacional(-2,5);

        StdOut.println(a.compareTo(b));
    }
}