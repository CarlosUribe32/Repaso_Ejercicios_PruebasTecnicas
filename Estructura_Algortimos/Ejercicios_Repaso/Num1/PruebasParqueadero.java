
public class PruebasParqueadero 
{
    public static void main(String[] args) throws Exception 
    {
        try 
        {
            IParqueadero parqueadero1 = new EspaciosParqueadero((short)20);
            parqueadero1.IncrementarEspacio();
            parqueadero1.IncrementarEspacio();
            parqueadero1.IncrementarEspacio();
            
            System.out.println(parqueadero1.getEspacios());
            parqueadero1.DecrementarEspacio();
            parqueadero1.DecrementarEspacio();
            parqueadero1.DecrementarEspacio();

            System.out.println(parqueadero1.getLimite());
            System.out.println("Hola" == "Hola");
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }    
}