public class Punto2DCartesiano implements IPunto2D
{
    // Representacion del ADT
	private double x;
	private double y;
	
	// Constructores
	public Punto2DCartesiano() {}	// Crea el punto (0,0)
	
    public Punto2DCartesiano(final double x, final double y) 
    {
		this.x = x;
		this.y = y;
	}
	
    // API del ADT
    @Override
    public double getX() 
    {
		return x;
	}
    
    @Override
    public double getY() 
    {
		return y;
    }
    
    @Override
	public double distancia(IPunto2D p) {
		return Math.sqrt( (x-p.getX())*(x-p.getX()) + (y-p.getY())*(y-p.getY()) );
    }
    
    //Sobreescribir metodos heredados de la clase object
    public String toString()
    {
        return "("+x+", "+y+")";
    }
    public boolean equals(Object x)
    {
        if(x==null)
            return false;
        if(!(x instanceof IPunto2D))
            return false;

        IPunto2D p = (IPunto2D)x;
        if(this.distancia(p)<tolerancia)
            return true;
        return false;
    }
    public static final double tolerancia = 1E-14;
}