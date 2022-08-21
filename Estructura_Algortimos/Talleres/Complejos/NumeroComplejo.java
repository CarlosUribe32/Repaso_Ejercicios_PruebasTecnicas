public class NumeroComplejo
{
    private float real;
    private float imaginario;

    public NumeroComplejo(double real, double imaginario)
    {
        this.real = (float)(real);
        this.imaginario = (float)(imaginario);
    }

    public float getReal ()
    {
        return real;
    }
    public float getImaginario()
    {
        return imaginario;
    }

    public NumeroComplejo suma (NumeroComplejo b)
    {
        float r = real+ b.getReal();
        float i = imaginario+b.getImaginario();
        return new NumeroComplejo(r, i);
    }
    public NumeroComplejo resta (NumeroComplejo b)
    {
        float r = real- b.getReal();
        float i = imaginario-b.getImaginario();
        return new NumeroComplejo(r, i);
    }
    public NumeroComplejo multiplicacion (NumeroComplejo b)
    {
        float r = real*b.getReal()-imaginario*b.getImaginario();
        float i = real*b.getImaginario()+imaginario*b.getReal();
        return new NumeroComplejo(r, i);
    }
    public NumeroComplejo division (NumeroComplejo b) throws Exception
    {
        if(b.getReal()==0 && b.getImaginario()==0) throw new Exception ("No se puede dividir por 0");
        float r = (real*b.getReal()+imaginario*b.getImaginario())/(float)(Math.pow(b.getReal(), 2)+Math.pow(b.getImaginario(), 2));
        float i = (imaginario*b.getReal()-real*b.getImaginario())/(float)(Math.pow(b.getReal(), 2)+Math.pow(b.getImaginario(), 2));
        return new NumeroComplejo(r, i);
    }
    public NumeroComplejo conjugar()
    {
        imaginario = imaginario*(-1);
        return new NumeroComplejo(real, imaginario);
    }
    public NumeroComplejo raizCuadrada()
    {
        if(real<0 && imaginario == 0)
        {
            imaginario = (float)Math.sqrt(-1*real);
            real = 0;
        }
        else if (real!=0 || imaginario!=0)
        {
            float raizmodulo = (float)Math.sqrt(Math.sqrt(Math.pow(real, 2)+Math.pow(imaginario,2)));
            float angulo = (float)(Math.atan(imaginario/real))/2;

            real = raizmodulo*(float)Math.cos(angulo);
            imaginario = raizmodulo*(float)Math.sin(angulo);
        }
        return new NumeroComplejo(real, imaginario);
    }

    static NumeroComplejo parseComplejo (String s) throws Exception
    {
        try 
        {
            int pos=0;
            s = s.replace(" ","");
            s = s.replace("(","");
            s = s.replace(")","");
            s = s.replace("[","");
            s = s.replace("]","");
            if(s.charAt(0)=='+') s= s.substring(1, s.length());
            boolean oper = false;
            boolean mas = false;
            for (int i = 0; i < s.length(); i++) 
            {
                switch(s.charAt(i))
                {
                    case '+':{
                        oper = true;
                        mas = true;
                    }break;
                    case '-': {
                        if(i!=0) oper=true;
                    }break;
                    default:break;
                }
                if (oper==true) 
                {
                    pos = i;
                    break;
                }    
            }
            if(oper==true)
            {
                String [] number = new String[2];
                double r, i;
                number[0] = s.substring(0, pos);
                number[1] = s.substring(pos+1, s.length());
                if (number[0].charAt(number[0].length()-1) == 'i')
                {
                    i = Double.parseDouble(number[0].substring(0, number[0].length()-1));
                    r = Double.parseDouble(number[1]);
                    if(mas!=true) r = r*-1;
                }
                else if (number[1].charAt(number[1].length()-1) == 'i')
                {
                    i = Double.parseDouble(number[1].substring(0, number[1].length()-1));
                    r = Double.parseDouble(number[0]);
                    if(mas!=true) i = i*-1;
                }
                else throw new Exception("No se puede convertir el string a Numero Complejo");

                return new NumeroComplejo(r,i);
            }
            else
            {
                if(s.charAt(s.length()-1)=='i')
                    return new NumeroComplejo(0, Double.parseDouble(s.substring(0, s.length()-1)));
                else
                    return new NumeroComplejo(Double.parseDouble(s),0);
            }    
        } 
        catch (Exception e) 
        {
            throw new Exception("No se puede convertir el string a Numero Complejo");
        } 
    }

    @Override
    public String toString()
    {
        if(imaginario<0)
            return real+" - "+Math.abs(imaginario)+"i";
        else if (imaginario>0)
            return real+" + "+imaginario+"i";
        else
            return String.valueOf(real);
    }
    @Override
    public boolean equals(Object x)
    {
        if(x==null)
            return false;
        if(!(x instanceof NumeroComplejo))
            return false;

        NumeroComplejo b = (NumeroComplejo)x;
        if(real == b.getReal() && imaginario == b.getImaginario())
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws Exception
    {
        NumeroComplejo a = new NumeroComplejo(1, 1);
        NumeroComplejo b = new NumeroComplejo(3, 9);

        assert(a.suma(b).equals(new NumeroComplejo(4, 10)));

        assert(a.resta(b).equals(new NumeroComplejo(-2, -8)));
        assert(b.resta(a).equals(new NumeroComplejo(2,8)));

        assert(a.multiplicacion(b).equals(new NumeroComplejo(-6,12)));

        assert(a.division(b).equals(new NumeroComplejo(0.13333334, -0.06666667)));
        assert(b.division(a).equals(new NumeroComplejo(6,3)));

        NumeroComplejo c = new NumeroComplejo(a.getReal(), a.getImaginario());
        a.conjugar();
        assert(c.getReal()==a.getReal() && c.getImaginario()==a.getImaginario()*(-1));

        assert(a.raizCuadrada().equals(new NumeroComplejo(1.0986841, -0.45508987)));
    }
}