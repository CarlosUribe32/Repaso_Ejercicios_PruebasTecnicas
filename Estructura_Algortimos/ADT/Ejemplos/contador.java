package Ejemplos;

public class contador implements IContador
{
    // Representacion
	private String id;
	private int conteo;
	/**
	 * Construye una instancia con un id
	 */
    public contador(String id) 
    {
		this.id = id;
	}
	
	// Implementacion de la interfaz publica
	
    public void incrementar() 
    {
		conteo++;
	}

    public int getConteo() 
    {
		return conteo;
	}

    public String getId() 
    {
		return id;
    }
    public static void main(String[] args) 
    {
        contador c = new contador("Un ID generico");
        assert (c.conteo==0);
    }
}