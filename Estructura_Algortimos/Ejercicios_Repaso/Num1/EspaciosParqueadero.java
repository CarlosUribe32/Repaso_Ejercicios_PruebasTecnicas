public class EspaciosParqueadero implements IParqueadero
{
    private short espacios;
    private short limite;

    public short getEspacios()
    {
        return espacios;
    }
    public short getLimite()
    {
        return limite;
    }

    public EspaciosParqueadero (short limite)
    {
        this.limite = limite;
        espacios = 0;
    }

    @Override
    public void IncrementarEspacio() throws Exception
    {
        if(espacios==limite)throw new Exception("El espacio esta al límite");
        espacios++;
    }

    @Override
    public void DecrementarEspacio() throws Exception
    {
        if(espacios==0)throw new Exception("No hay carros");
        espacios--;
    }
}