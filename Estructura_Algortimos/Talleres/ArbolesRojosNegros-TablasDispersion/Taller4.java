import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import edu.princeton.cs.algs4.*;
public class Taller4 
{
    static SeparateChainingHashST<String, Bag<PairData>> porSimbolos = new SeparateChainingHashST<>();
    static SeparateChainingHashST<GregorianCalendar, Bag<PairData>> porFechas = new SeparateChainingHashST<>();
   
    public static void cargarDatos(String nombreArchivo) throws ParseException {
        In in = new In(nombreArchivo);
        in.readLine(); // ignorar primera linea
        in.readLine(); // ignorar segunda linea
        while (!in.isEmpty()) {
            String linea = in.readLine();
            PairData pd = new PairData(linea);

            if (!porSimbolos.contains(pd.getSymbol()))
                porSimbolos.put(pd.getSymbol(), new Bag<PairData>());
            porSimbolos.get(pd.getSymbol()).add(pd);
            if (!porFechas.contains(pd.getDate()))
                porFechas.put(pd.getDate(), new Bag<PairData>());
            porFechas.get(pd.getDate()).add(pd);
        }
    }
    public static void comparativo(String simbolo0, String simbolo1, Date fecha0, Date fecha1) throws Exception
    {
        if(!porSimbolos.contains(simbolo0) || !porSimbolos.contains(simbolo1))
            throw new Exception("Uno de los simbolos no se encuentra en la estructura");
        
        Date inicio=null, fin=null;
        if(fecha0.compareTo(fecha1)>0){
            inicio = fecha1;
            fin = fecha0;
        }
        else{
            inicio = fecha0;
            fin = fecha1;
        }
        boolean cont = false;
        for (GregorianCalendar d : porFechas.keys()) {
            if(d.getTime().compareTo(inicio)>=0 && d.getTime().compareTo(fin)<=0)
            {
                Bag<PairData> bolsaPd = porFechas.get(d);
                PairData a=null, b=null;
                for (PairData pd : bolsaPd) {
                    if(pd.getSymbol().equals(simbolo0))
                        a = pd;
                    else if(pd.getSymbol().equals(simbolo1))
                        b = pd;
                }
                if(a!=null && b!=null)
                {
                    if(!cont)
                    {
                        cont = true;
                        StdOut.println("Fecha       Valor "+simbolo0+"        Valor "+simbolo1);
                    }
                    StdOut.println(d.get(Calendar.DATE)+"-"+(d.get(Calendar.MONTH)+1)+"-"+d.get(Calendar.YEAR)+"       "+a.getClose()+"         "+b.getClose());
                }
            }
        }
    }
    public static double correlation(String simbolo0, String simbolo1, Date fecha0, Date fecha1) throws Exception {
        if(!porSimbolos.contains(simbolo0) || !porSimbolos.contains(simbolo1))
            throw new Exception("Uno de los simbolos no se encuentra en la estructura");
            Date inicio=null, fin=null;
        if(fecha0.compareTo(fecha1)>0){
            inicio = fecha1;
            fin = fecha0;
        }
        else{
            inicio = fecha0;
            fin = fecha1;
        }
        GregorianCalendar tmp = new GregorianCalendar();
        tmp.setTime(fin);
        tmp.add(GregorianCalendar.DAY_OF_MONTH, 1);
        fin = tmp.getTime();
        double x = 0;
        double y = 0;
        double xy = 0;
        double xpow = 0;
        double ypow = 0;
        int n = 0;
        while (inicio.before(fin)) {
            GregorianCalendar temp = new GregorianCalendar();
            temp.setTime(inicio);
            if (!porFechas.contains(temp)) {
                throw new Exception("-----------------------Una fecha no esta presente-------------------");
            }
            PairData uno = null;
            PairData dos = null;
            for (PairData pd : porFechas.get(temp)) {
                if (pd.getSymbol().equals(simbolo0)) {
                    uno = pd;
                } 
                else if (pd.getSymbol().equals(simbolo1)) {
                    dos = pd;
                }
            }
            if (uno == null || dos == null) {
                throw new Exception("----------------------Un par no se encuentra en la estructura-------------------");
            }

            x = x + uno.getClose();
            y = y + dos.getClose();
            xy = xy + uno.getClose() * dos.getClose();
            xpow = xpow + Math.pow(uno.getClose(), 2);
            ypow = ypow + Math.pow(dos.getClose(), 2);

            temp.add(GregorianCalendar.DAY_OF_MONTH, 1);
            n++;
            inicio = temp.getTime();
        }
        return (n * xy - x * y) / (Math.sqrt((n * xpow - Math.pow(x, 2)) * (n * ypow - Math.pow(y, 2))));
    }

    public static void main(String[] args) throws ParseException {
        try 
        {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            int opcion=0;
            do 
            {
                StdOut.println("Ingrese:\n 1 Para indicar el archivo a cargar\n 2 Si quiere llamar a comparativo\n 3 Si quiere llamar correlation\n 4 Para salir");
                opcion = StdIn.readInt();
                StdOut.println("");
                switch(opcion)
                {
                    case 1:{
                        StdOut.println("Ingrese la ruta del archivo a cargar y el nombre");
                        String ruta = StdIn.readString();
                        cargarDatos(ruta);
                    }break;
                    case 2:{
                        StdOut.println("Ingrese los dos simbolos y luego el rango de fechas (En formato yyyy-mm-dd)");
                        String simbolo0 = StdIn.readString();
                        String simbolo1 = StdIn.readString();
                        Date fecha0 = formato.parse(StdIn.readString());
                        Date fecha1 = formato.parse(StdIn.readString());
                        comparativo(simbolo0, simbolo1, fecha0, fecha1);
                    }break;
                    case 3:{
                        StdOut.println("Ingrese los dos simbolos y luego el rango de fechas (En formato yyyy-mm-dd)");
                        String simbolo0 = StdIn.readString();
                        String simbolo1 = StdIn.readString();
                        Date fecha0 = formato.parse(StdIn.readString());
                        Date fecha1 = formato.parse(StdIn.readString());
                        double p = correlation(simbolo0, simbolo1, fecha0, fecha1);
                        StdOut.println("El coeficiente de correlacion de pearson de los anteriores datos es: "+p);
                    }break;
                    default:break;
                }
            } 
            while (opcion!=4);
        } 
        catch (Exception e) 
        {
            StdOut.println(e);
        }
    }
}
