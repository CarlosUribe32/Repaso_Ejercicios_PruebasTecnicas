import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class Matriz 
{
    private float[][] matriz;
    private int filas;
    private int columnas;

    public Matriz(int filas, int columnas, float[][]matriz) throws Exception 
    {
        if (filas<=0||columnas<=0||filas!=matriz.length||columnas!=matriz[0].length) 
            throw new Exception("Numero de filas o columnas invalido");

        this.filas = filas;
        this.columnas = columnas;
        this.matriz = matriz;
    }
    public float[][] getMatriz()
    {
        return matriz;
    }
    public int getFilas()
    {
        return filas;
    }
    public int getColumnas()
    {
        return columnas;
    }

    public Matriz suma(Matriz b) throws Exception
    {
        if(this.filas!=b.getFilas()||this.columnas!=b.getColumnas())
            throw new Exception("Numero de filas o columnas distinto");

        float[][] sum = new float[filas][columnas]; 
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz[i].length; j++) 
            {
                sum[i][j]= this.matriz[i][j] + b.getMatriz()[i][j];
            }
        }
        Matriz resultado = new Matriz(this.filas, this.columnas, sum);        
        return resultado;
    }
    public Matriz multiplicacion(Matriz b) throws Exception
    {
        if(columnas!=b.getFilas())
            throw new Exception("El numero de columnas de la matriz A no coincide con el numero de filas de B");
        
        float[][] mult = new float[filas][b.getColumnas()];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < b.getColumnas(); j++) {
                for (int k = 0; k < columnas; k++) {
                    mult[i][j] += matriz[i][k] * b.getMatriz()[k][j];
                }
            }
        }
        Matriz resultado = new Matriz(filas, b.getColumnas(), mult);
        return resultado;
    }

    @Override
    public String toString()
    {
        String retorno ="";
        for (int i = 0; i < matriz.length; i++) 
        {
            for (int j = 0; j < matriz[i].length; j++) 
            {
                retorno += matriz[i][j]+"    ";    
            }
            retorno += "\n";
        }
        return retorno;
    }
    
    @Override
    public boolean equals(Object obj) 
    {
        if(obj==null)
            return false;
        if(!(obj instanceof Matriz))
            return false;

        Matriz recibido = ((Matriz)obj);
        if (matriz.length!=recibido.getMatriz().length||matriz[0].length!=recibido.getMatriz()[0].length)
            return false;
        for (int i = 0; i < matriz.length; i++) 
        {
            for (int j = 0; j < matriz[i].length; j++) 
            {
                if(matriz[i][j]!=recibido.getMatriz()[i][j])
                    return false;
            }
        }
        return true;
    }

    public Matriz inversaPorGaussJordan() throws Exception
    {
        if(filas!=columnas)
            throw new Exception("Solo las matrices cuadradas tienen inversa");
        
        float[][] original = matriz;
        float[][] identidad = new float[filas][filas];
        for (int i = 0; i < identidad.length; i++) 
        {
            for (int j = 0; j < identidad.length; j++) 
            {
                if(i==j)
                    identidad[i][j]=1;
            }
        }

        for (int i = 0; i < original.length; i++) 
        {
            float div = original[i][i];
            for (int j = 0; j < identidad.length; j++) 
            {
                original[i][j]= original[i][j]/div;
                identidad[i][j] = identidad[i][j]/div;
            }
            for (int f = 0; f < original.length; f++) 
            {
                if(f!=i)
                {
                    div = -1*original[f][i];
                    for (int c = 0; c < original.length; c++) 
                    {
                        original[f][c] = original[f][c]+original[i][c]*div;
                        identidad[f][c] = identidad[f][c]+identidad[i][c]*div;
                    }
                }
            }
        }

        for (int i = 0; i < original.length; i++) 
        {
            for (int j = 0; j < original.length; j++) 
            {
                if(i==j && original[i][j]!=1)
                    throw new Exception("Esta matriz no tiene inversa");
                else if (i!=j && original[i][j]!=0)
                    throw new Exception("Esta matriz no tiene inversa");
            }
        }
        Matriz inversa = new Matriz(filas, filas, identidad);
        return inversa;
    }

    public static void main(String[] args) throws Exception 
    {
        Matriz matricita = new Matriz(2,2, new float[][]{{8,9},{10,3}});
        Matriz matricitaDos = new Matriz(2,3, new float[][]{{8,9,1},{10,3,0}});
        Matriz matricitaTres = new Matriz(2,2, new float[][]{{1,2},{4,5}});
        Matriz matricitaCuatro = new Matriz(2,2, new float[][]{{8,9},{10,3}});

        assert(matricita.suma(matricitaTres).equals(new Matriz(2,2,new float[][]{{9,11},{14,8}})));
        assert(matricita.equals(matricitaCuatro));
        assert(matricita.multiplicacion(matricitaDos).equals(new Matriz(2,3,new float[][]{{154,99,8},{110,99,10}})));

        Matriz matricitaCinco = new Matriz(3,3,new float[][]{{2,3,1},{3,3,1},{2,4,1}});
        assert(matricitaCinco.inversaPorGaussJordan().equals(new Matriz(3,3,new float[][]{{-1,1,0},{-1,0,1},{6,-2,-3}})));

        //Pruebas Para el modelo empírico
        Matriz a = new Matriz(9,9, new float[][]{{3,4,6,31,23,10,43,54,34},{1,5,7,89,10,11,12,45,10},{10,11,23,1,34,12,89,1,45},{12,34,5,8,6,13,6,45,1},{12,34,56,6,0,14,12,56,3},{45,10,2,3,4,12,11,45,0},{12,34,4,5,6,7,89,23,32},{12,34,5,6,7,12,34,9,12},{12,3,4,2,56,76,7,0,9}});
        Stopwatch timer = new Stopwatch();
        StdOut.println(a.inversaPorGaussJordan());
        StdOut.println("elapsed time = " + timer.elapsedTime());
        
    }

}