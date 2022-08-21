public class Punto2DPolares implements IPunto2D 
{

	// Representacion del punto mediante coordenadas polares
	private double radio;
	private double theta;

	// Constructores
	public Punto2DPolares() {}	// Crea el punto (0,0)
	
	public Punto2DPolares(double radio, double theta) {
		this.radio = radio;
		this.theta = theta;
	}
	
	
	// Implementacion de la interfaz publica
	@Override
	public double getX() {
		return radio*Math.cos(theta);
	}

	@Override
	public double getY() {
		return radio*Math.sin(theta);
	}

	@Override
    public double distancia(IPunto2D p) 
    {
		return Math.sqrt( (getX()-p.getX())*(getX()-p.getX()) + (getY()-p.getY())*(getY()-p.getY()) );
	}
	
	//Sobreescribir los metodods heredados de la clase object
	public String toString()
	{
		return "Magnitud: "+radio+", angulo: "+Math.toDegrees(theta);
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