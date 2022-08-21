/**
* Ejemplos de uso de la tabla de símbolos ordenada
* 
* @author Jorge Londoño
* Dataset: https://www.kaggle.com/jealousleopard/goodreadsbooks
* 
*/

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
//import jdk.internal.jshell.tool.StopDetectingInputStream;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.MaxPQ;

class Book {
   private int bookID;
   private String title;
   private String authors;
   private float average_rating;
   private String isbn;
   private String isbn13;
   private String language_code;
   private int num_pages;
   private int ratings_count;
   private int text_reviews_count;
   private Date publication_date;
   private String publisher;
   
   Book(String line) throws ParseException {
       String[] tmp = line.split(",");
       bookID = Integer.valueOf(tmp[0]);
       title = tmp[1];
       authors = tmp[2];
       average_rating = Float.parseFloat(tmp[3]);
       isbn = tmp[4];
       isbn13 = tmp[5];
       language_code = tmp[6];
       num_pages = Integer.valueOf(tmp[7]);
       ratings_count = Integer.valueOf(tmp[8]);
       text_reviews_count = Integer.valueOf(tmp[9]);
       publication_date = sdf.parse(tmp[10]);
       publisher = tmp[11];
   }

   public int    getBookID()             { return bookID; }
   public String getTitle()              { return title; }
   public String getAuthors()            { return authors; }
   public float  getAverage_rating()     { return average_rating; }
   public String getIsbn()               { return isbn; }
   public String getIsbn13()             { return isbn13; }
   public String getLanguage_code()      { return language_code; }
   public int    getNum_pages()          { return num_pages; }
   public int    getRatings_count()      { return ratings_count; }
   public int    getText_reviews_count() { return text_reviews_count; }
   public Date   getPublication_date()   { return publication_date; }
   public String getPublisher()          { return publisher; }

   static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

}

//Permite comparar teniendo en cuenta multiples factores
class BookComparator implements Comparator<Book> {

   public int compare(Book a, Book b) {
        if(a.getAverage_rating()<b.getAverage_rating())
            return -1;
        else if (a.getAverage_rating()>b.getAverage_rating())
            return 1;
        else
            return 0;
   }

}
class AnoComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        float a = Float.parseFloat(o1.split("_")[1]);
        float b = Float.parseFloat(o2.split("_")[1]);
        if(a<b)
            return -1;
        else if(a>b)
            return 1;
        else
            return 0;
    }

}


public class Goodreads {
    static BST<String, Bag<Book>> librosPorAutor = new BST<>();
    static BST<Integer, Integer> cantidadPorAno = new BST<>();
    static BST<Integer, Bag<Book>> librosPorAno = new BST<>();
    static BST<String, Integer> cantidadPorIdioma = new BST<>();
    static BST<String, Bag<Book>> librosPorEditorial = new BST<>();

   public static void readFile(String file) {
       In in = new In(file);
       in.readLine(); // ignorar primera linea
       while(! in.isEmpty()) {
           String l = in.readLine();
           try {
               Book b = new Book(l);
                if(! librosPorAutor.contains(b.getAuthors()))
                    librosPorAutor.put(b.getAuthors(), new Bag<Book>());
                librosPorAutor.get(b.getAuthors()).add(b);
                
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(b.getPublication_date());
                int ano = cal.get(Calendar.YEAR);
                if(!cantidadPorAno.contains(ano))
                {
                    cantidadPorAno.put(ano, 0);
                    librosPorAno.put(ano, new Bag<Book>());
                }
                cantidadPorAno.put(ano, cantidadPorAno.get(ano)+1);
                librosPorAno.get(ano).add(b);

                if(!cantidadPorIdioma.contains(b.getLanguage_code()))
                {
                    cantidadPorIdioma.put(b.getLanguage_code(), 0);
                }
                cantidadPorIdioma.put(b.getLanguage_code(), cantidadPorIdioma.get(b.getLanguage_code())+1);
                
                if(!librosPorEditorial.contains(b.getPublisher()))
                    librosPorEditorial.put(b.getPublisher(), new Bag<Book>());
                librosPorEditorial.get(b.getPublisher()).add(b);
            }
           catch(ParseException e) { 
               StdOut.println("Fecha no valida: "+l);
           }
           catch(NumberFormatException e) {
               StdOut.println("Numero no valido: "+l);
           }
       }
   }


   public static void librosPorAutor(String autor) {
        Bag<Book> libros = librosPorAutor.get(autor);
        StdOut.println("Cantidad de libros: "+libros.size());
        double[] a = new double[libros.size()];
        int i = 0;
        for (Book b: libros)
        {
            a[i++] = b.getAverage_rating();
        }
        StdOut.println("Calificacion promedio: "+StdStats.mean(a));

   }

   public static void librosPorAnno() {
        for(Integer ano:cantidadPorAno.keys())
        {
            StdOut.println(ano+" : "+cantidadPorAno.get(ano));
        }
   }

   public static void topPorAutor() {
        for(String autor: librosPorAutor.keys())
        {
            Bag<Book> libros = librosPorAutor.get(autor);
            MaxPQ<Book> cola = new MaxPQ<>(new BookComparator());
            for(Book b: libros)
                cola.insert(b);
            StdOut.println("Top del autor: "+autor+" es el libro "+cola.max().getTitle());
        }

   }
   public static void dosMejoresPorAutor()
   {
       for (String autor : librosPorAutor.keys()) {
           Bag<Book> libros = librosPorAutor.get(autor);
           MaxPQ<Book> cola = new MaxPQ<>(new BookComparator());
           for(Book b: libros)
                cola.insert(b);
            if(cola.size()>=2)
                StdOut.println("Top 2 del autor: "+autor+" son el libro "+cola.delMax().getTitle()+" y el libro "+cola.delMax().getTitle());
            else
            StdOut.println("Top del autor: "+autor+" es el libro "+cola.max().getTitle());
         }
   }
   public static void mejoresAnos()
   {
        MaxPQ<String> cola = new MaxPQ<>(new AnoComparator());
       for ( Integer anno : librosPorAno.keys()) {
            Bag<Book> libros = librosPorAno.get(anno);
            float prom = 0;
            for (Book book : libros) {
                prom += book.getAverage_rating();
            }
            prom /= libros.size();
            String a = anno+"_"+prom;
            cola.insert(a);
       }
       StdOut.println("Los dos años con mejores calificaciones fueron: "+cola.delMax().split("-")[0]+" y "+cola.delMax().split("-")[0]);
   }
   public static void cantidadPorIdioma()
   {
       for (String lenguage : cantidadPorIdioma.keys()) {
            StdOut.println(lenguage+" : "+cantidadPorIdioma.get(lenguage));
       }
   }
   public static void mejores10Libros()
   {
        MaxPQ<Book> cola = new MaxPQ<>(new BookComparator());
        for (String autor: librosPorAutor.keys()) {
            for (Book book : librosPorAutor.get(autor)) {
                cola.insert(book);
            }
        }
        StdOut.println("Los mejores libros son: ");
        for (int i = 1; i <= 10 && cola.size()!=0; i++) {
            StdOut.println(cola.delMax().getTitle());
        }
   }
   public static void mejorEditor()
   {
       MaxPQ<String> cola = new MaxPQ<>(new AnoComparator());
       for (String publisher : librosPorEditorial.keys()) {
            Bag<Book> libros = librosPorEditorial.get(publisher);
            float prom = 0;
            for (Book book : libros) {
                prom += book.getAverage_rating();
            }
            prom /= libros.size();
            String a = publisher+"_"+prom;
            cola.insert(a);
        }
        StdOut.println("La mejor editorial que tiene el mejor promedio en calificaciones es: "+cola.delMax().split("_")[0]);

   }

   public static void main(String[] args) {
       String ruta = "C:\\Users\\jcugl\\Documents\\4_Semestre\\Estructura_Algortimos\\MetodosBusqueda\\books.csv";
       readFile(ruta);
       librosPorAutor("J.K. Rowling/Mary GrandPré");
       //librosPorAutor("J.R.R. Tolkien");
       //librosPorAutor("Gabriel García Márquez");
       librosPorAnno();
        //topPorAutor();
        //dosMejoresPorAutor();
        //mejoresAnos();
        cantidadPorIdioma();
        mejores10Libros();
        mejorEditor();
   }
}
