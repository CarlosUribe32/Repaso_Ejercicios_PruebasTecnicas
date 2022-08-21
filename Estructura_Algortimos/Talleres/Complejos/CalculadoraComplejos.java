import edu.princeton.cs.algs4.*;

public class CalculadoraComplejos 
{
    public static void main(String[] args) throws Exception 
    {
       try 
       {
            int opcion = 0;
            do 
            {
                StdOut.println("Ingrese:\n1 si quiere sumar dos números complejos\n2 si quiere restar dos numeros complejos\n3 si quiere multiplicar dos numeros complejos\n4 si quiere dividir dos numeros complejos\n5 si quiere sacar el conjugado a un numero complejo\n6 si quiere sacar la raiz de un numero complejo\n7 si quiere salir del menu");
                opcion = StdIn.readInt();
                StdIn.readLine();
                switch(opcion)
                {
                    case 1:{
                        StdOut.println("Ingrese los dos numeros");
                        NumeroComplejo a = NumeroComplejo.parseComplejo(StdIn.readLine());
                        NumeroComplejo b = NumeroComplejo.parseComplejo(StdIn.readLine());
                        StdOut.println("El resultado es: "+a.suma(b));
                    }break;
                    case 2:{
                        StdOut.println("Ingrese los dos numeros");
                        NumeroComplejo a = NumeroComplejo.parseComplejo(StdIn.readLine());
                        NumeroComplejo b = NumeroComplejo.parseComplejo(StdIn.readLine());
                        StdOut.println("El resultado es: "+a.resta(b));
                    }break;
                    case 3:{
                        StdOut.println("Ingrese los dos numeros");
                        NumeroComplejo a = NumeroComplejo.parseComplejo(StdIn.readLine());
                        NumeroComplejo b = NumeroComplejo.parseComplejo(StdIn.readLine());
                        StdOut.println("El resultado es: "+a.multiplicacion(b));
                    }break;
                    case 4:{
                        StdOut.println("Ingrese los dos numeros");
                        NumeroComplejo a = NumeroComplejo.parseComplejo(StdIn.readLine());
                        NumeroComplejo b = NumeroComplejo.parseComplejo(StdIn.readLine());
                        StdOut.println("El resultado es: "+a.division(b));
                    }break;
                    case 5:{
                        StdOut.println("Ingrese el numero");
                        NumeroComplejo a = NumeroComplejo.parseComplejo(StdIn.readLine());
                        StdOut.println("El resultado es: "+a.conjugar());
                    }break;
                    case 6:{
                        StdOut.println("Ingrese el numero");
                        NumeroComplejo a = NumeroComplejo.parseComplejo(StdIn.readLine());
                        StdOut.println("El resultado es: "+a.raizCuadrada());
                    }break;
                    default:break;
                }
                StdOut.println("");
            }
            while (opcion!=7);    
       } 
       catch (Exception e) 
       {
           StdOut.println(e);
       }
    }    
}