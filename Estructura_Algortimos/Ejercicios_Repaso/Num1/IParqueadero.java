public interface IParqueadero 
{
    void IncrementarEspacio() throws Exception;
    
    void DecrementarEspacio() throws Exception;

    short getEspacios();

    short getLimite();
}