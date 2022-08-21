import java.io.*;
import edu.princeton.cs.algs4.*;
public class EstructurasAnidadas
{
    public static void main(String[] args) throws IOException 
    {
        try {
            Pila<Character> p;
            char a=0;
            File archivo = new File ("C:\\Users\\jcugl\\Documents\\4_Semestre\\Estructura_Algortimos\\Ejercicios_Repaso\\Num2\\archivo.txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();
            while (linea!=null) 
            {
                linea = linea.replace(" ", "");
                p = new Pila<>();
                for (int i = 0; i < linea.length(); i++) {
                    if(p.getN()!=0)
                    {
                        a = p.pop();
                        if(a=='[' && linea.charAt(i)==']' || a=='(' && linea.charAt(i)==')' || a=='{' && linea.charAt(i)=='}') 
                        {
                        }
                        else
                        {
                            p.push(a);
                            p.push(linea.charAt(i));
                        }
                    }
                    else
                    {
                        a = linea.charAt(i);
                        p.push(linea.charAt(i));
                    }
                }
                if (p.getN()==0)
                    StdOut.println("Cierra correctamente");
                else
                    StdOut.println("No cierra correctamente");
                linea = br.readLine();
            }

            fr.close();
        } 
        catch (Exception e) {
            StdOut.println(e);
        }
    }
}