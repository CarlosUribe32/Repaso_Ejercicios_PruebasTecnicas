package Ejemplos;

import  edu.princeton.cs.algs4.StdOut;

public class Pruebapuntos 
{
    public static void main(String[] args) 
    {
        IPunto2D origen = new Punto2DCartesiano();
        IPunto2D p1 = new Punto2DCartesiano(1,1);
        IPunto2D p2 = new Punto2DPolares(Math.sqrt(2), Math.toRadians(45));
        IPunto2D p3 = new Punto2DCartesiano(1,1);

        /*StdOut.println(origen.distancia(p1));
        StdOut.println(p1.distancia(p2));

        StdOut.println("");
        StdOut.println(p1);
        StdOut.println(p2);*/

        StdOut.println(p1.equals(p2));
        StdOut.println(p1.equals(p2) && p2.equals(p3) && p3.equals(p1));
    }
}