import edu.princeton.cs.algs4.*;
public class Taller2 
{
    public static void main(String[] args) 
    {
        int opcion=0;
        try 
        {
            do 
            {
                StdOut.println("Escoge una opcion:\n1.Sumar dos matrices\n2.Multiplicar dos matrices\n3.Hallar Inversa de una matriz\n4.Salir del programa");
                opcion = StdIn.readInt();
                switch (opcion) 
                {
                    case 1:
                        {
                            StdOut.println("Ingrese el numero de filas y de columnas de la matriz A");
                            int filas1 = StdIn.readInt();
                            int columnas1 = StdIn.readInt();
                            StdOut.println("Ingrese los valores de la matriz");
                            float[][] mata = new float[filas1][columnas1];
                            for (int i = 0; i < filas1; i++) 
                            {
                                for (int j = 0; j < columnas1; j++) 
                                {
                                    mata[i][j] = StdIn.readFloat();
                                }   
                            }
                            Matriz a = new Matriz(filas1, columnas1, mata);

                            StdOut.println("Ingrese el numero de filas y de columnas de la matriz B");
                            int filas2 = StdIn.readInt();
                            int columnas2 = StdIn.readInt();
                            StdOut.println("Ingrese los valores de la matriz");
                            float[][] matb = new float[filas2][columnas2];
                            for (int i = 0; i < filas2; i++) 
                            {
                                for (int j = 0; j < columnas2; j++) 
                                {
                                    matb[i][j] = StdIn.readFloat();
                                }   
                            }
                            Matriz b = new Matriz(filas2, columnas2, matb);

                            StdOut.println("El resultado de la suma es:\n"+a.suma(b));

                        }
                        break;
                    case 2:
                        {
                            StdOut.println("Ingrese el numero de filas y de columnas de la matriz A");
                            int filas1 = StdIn.readInt();
                            int columnas1 = StdIn.readInt();
                            StdOut.println("Ingrese los valores de la matriz");
                            float[][] mata = new float[filas1][columnas1];
                            for (int i = 0; i < filas1; i++) 
                            {
                                for (int j = 0; j < columnas1; j++) 
                                {
                                    mata[i][j] = StdIn.readFloat();
                                }   
                            }
                            Matriz a = new Matriz(filas1, columnas1, mata);

                            StdOut.println("Ingrese el numero de filas y de columnas de la matriz B");
                            int filas2 = StdIn.readInt();
                            int columnas2 = StdIn.readInt();
                            StdOut.println("Ingrese los valores de la matriz");
                            float[][] matb = new float[filas2][columnas2];
                            for (int i = 0; i < filas2; i++) 
                            {
                                for (int j = 0; j < columnas2; j++) 
                                {
                                    matb[i][j] = StdIn.readFloat();
                                }   
                            }
                            Matriz b = new Matriz(filas2, columnas2, matb);

                            StdOut.println("El resultado de la multiplicacion es:\n"+a.multiplicacion(b));
                        }
                        break;
                    case 3:
                        {
                            StdOut.println("Ingrese el numero de filas y de columnas de la matriz A");
                            int filas1 = StdIn.readInt();
                            int columnas1 = StdIn.readInt();
                            StdOut.println("Ingrese los valores de la matriz");
                            float[][] mata = new float[filas1][columnas1];
                            for (int i = 0; i < filas1; i++) 
                            {
                                for (int j = 0; j < columnas1; j++) 
                                {
                                    mata[i][j] = StdIn.readFloat();
                                }   
                            }
                            Matriz a = new Matriz(filas1, columnas1, mata);
                            StdOut.println("La inversa de la matriz es:\n"+a.inversaPorGaussJordan());
                        }
                        break;

                    default:
                        break;
                }
            } while (opcion!=4);     
        } 
        catch (Exception e) 
        {
            StdOut.println(e);
        }   
    }    
}