//Anexar bibliotecas que estan en la carpeta comprimida
import edu.princeton.cs.algs4.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Taller3 {

    static DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    private static class Ventana extends JFrame {
        JPanel panel;

        public Ventana() {
            setTitle("Graficos top10 paises por intervalos");
            setSize(900,600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            init();
        }

        private void init() {
            panel = new JPanel();
            getContentPane().add(panel);
            // Creando el Grafico
            JFreeChart chart = ChartFactory.createBarChart("Casos por fecha","Fecha", "Casos nuevos",dataset, PlotOrientation.VERTICAL, true,false, true);
            chart.setBackgroundPaint(ChartColor.cyan);
            chart.getTitle().setPaint(ChartColor.black);
            CategoryPlot p = chart.getCategoryPlot(); 
            p.setRangeGridlinePaint(ChartColor.red);
            // Mostrar Grafico
            ChartPanel chartPanel = new ChartPanel(chart);
            panel.add(chartPanel);
        }
    }
    public static void main(String[] args) 
    {
        File file = null;
        try {
            file = new File("C:\\Users\\jcugl\\Documents\\4_Semestre\\Estructura_Algortimos\\Talleres\\ColasPrioridad\\WHO-COVID-19-global-data.csv");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            RegistroCovid[] registros = cargarDatos(file);

            /*for (int i = 0; i < registros.length; i++) {
                StdOut.println(registros[i]);
            } 
            StdOut.println("");*/
            //StdOut.println(registros[39432]);

            
            /*RegistroCovid[] porFecha =  filtrarPorFecha(registros, formato.parse("2020-02-24"));
            for (int i = 0; i < porFecha.length; i++) {
                StdOut.println(porFecha[i]);
            }
            StdOut.println("");*/

           /*  RegistroCovid[] porFechatop10 =  top10fecha(registros, formato.parse("2020-03-03"));
            for (int i = 0; i < porFechatop10.length; i++) {
                StdOut.println(porFechatop10[i]);
            }
            StdOut.println(""); */
            
            comparativoPorFechas(registros, formato.parse("2020-02-24"), formato.parse("2020-03-03"));
            comparativoPorFechasVisual(registros, formato.parse("2020-02-24"), formato.parse("2020-03-03"));
            new Ventana().setVisible(true);

        } 
        catch (Exception e) 
        {
            StdOut.println("Excepcion: " + e);
        }

    }

    //Punto 2
    public static RegistroCovid[] cargarDatos(File file)throws IOException, ParseException
    {
        FileReader abrir = new FileReader(file);
        BufferedReader archivoCovid = new BufferedReader(abrir);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        int n = cantidadRegistros(archivoCovid);
        abrir = new FileReader(file);
        archivoCovid = new BufferedReader(abrir);

        String linea =  archivoCovid.readLine();
        String [] split;
        RegistroCovid[] listaRegistros= new RegistroCovid[n];
        int i=0; 
        linea = archivoCovid.readLine();
        while (linea != null)
        {
            split = linea.split(",");
            if(split[2].charAt(0)=='"')
            {
                split[2] = split[2]+split[3];
                split[3] = split[4];
                split[4] = split[5];
                split[5] = split[6];
                split[6] = split[7];
                split[7] = split[8];
            }
            RegistroCovid registro = new RegistroCovid(formato.parse(split[0]), split[1], split[2], split[3],Integer.parseInt(split[4]), Long.parseLong(split[5]), Integer.parseInt(split[6]), Long.parseLong(split[7]));
            listaRegistros[i++] = registro;
            linea = archivoCovid.readLine();
        }
        archivoCovid.close();
        return listaRegistros;
    }
    private static int cantidadRegistros(BufferedReader archivoCovid)throws IOException
    {
        String linea = archivoCovid.readLine();
        int n = 0;
        while (linea != null) {
            n++;
            linea = archivoCovid.readLine();
        }
        archivoCovid.close();
        if (n == 0) 
            return 0;
        return n-1;
    }

    //Punto 3
    public static RegistroCovid[] filtrarPorFecha (RegistroCovid[] listaRegistros, Date fecha)
    {
        int n = 0;
        for (int i = 0; i < listaRegistros.length; i++) 
        {
            if(listaRegistros[i].getFecha().equals(fecha))
                n++;
        }
        RegistroCovid[] listaPorFecha = new RegistroCovid[n];
        n = 0;
        for (int i = 0; i < listaRegistros.length; i++) 
        {
            if(listaRegistros[i].getFecha().equals(fecha))
                listaPorFecha[n++] = listaRegistros[i];
        }
        return listaPorFecha;
    }

    //Punto 4
    public static RegistroCovid [] top10fecha (RegistroCovid[] listaRegistros, Date fecha)
    {
        RegistroCovid[] listaPorFecha = filtrarPorFecha(listaRegistros, fecha);
        MinPQ<RegistroCovid> top10 = new MinPQ<>();
        if(listaPorFecha.length<=10)
        {
            Quick.sort(listaPorFecha);
            return listaPorFecha;
        }
        else
        {
            for (int i = 0; i < 10; i++) {
                top10.insert(listaPorFecha[i]);
            }
            for (int i = 10; i < listaPorFecha.length; i++) {
                if (listaPorFecha[i].compareTo(top10.min())<0)
                    continue;
                else
                {
                    top10.delMin();
                    top10.insert(listaPorFecha[i]);
                }
            }
            RegistroCovid[] top10Array = new RegistroCovid[10];
            for (int i = 0; i < 10; i++) {
                top10Array[i] = top10.delMin();
            }
            return top10Array;
        }
    }

    //Punto 5
    public static void comparativoPorFechas(RegistroCovid[] listaRegistros, Date fechainicio, Date fechafin)
    {
        if(fechainicio.compareTo(fechafin)>0)
            StdOut.println("La fecha de inicio es mayor a la fecha fin");
        else
        {
            Calendar a = Calendar.getInstance();
            Calendar b = Calendar.getInstance();
            a.setTime(fechainicio);
            b.setTime(fechafin);
            while(a.getTime().compareTo(b.getTime())<=0)
            {
                RegistroCovid[] porFecha = top10fecha(listaRegistros, a.getTime());
                imprime(porFecha, a);
                a.add(Calendar.DAY_OF_YEAR, 1);
            }
        }  
    }
    private static void imprime(RegistroCovid[] top10, Calendar fecha)
    {
        StdOut.println(fecha.getTime());
        for (int i = 0; i < top10.length; i++) {
            StdOut.println(top10[i]);
        }
        StdOut.println("");
    }

    //Punto 6 Opcional
    public static void comparativoPorFechasVisual(RegistroCovid[] listaRegistros, Date fechainicio, Date fechafin)
    {
        if(fechainicio.compareTo(fechafin)>0)
            StdOut.println("La fecha de inicio es mayor a la fecha fin");
        else
        {
            Calendar a = Calendar.getInstance();
            Calendar b = Calendar.getInstance();
            a.setTime(fechainicio);
            b.setTime(fechafin);
            while(a.getTime().compareTo(b.getTime())<=0)
            {
                RegistroCovid[] porFecha = top10fecha(listaRegistros, a.getTime());
                MandarAVisual(porFecha, a);
                a.add(Calendar.DAY_OF_YEAR, 1);
            }
        }  
    }
    private static void MandarAVisual(RegistroCovid[] top10, Calendar fecha)
    {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(fecha.getTime());
        for (int i = 0; i < top10.length; i++) {
           dataset.addValue(top10[i].getCasosNuevos(), top10[i].getPais(), cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
        }
        
    }    
    
}
