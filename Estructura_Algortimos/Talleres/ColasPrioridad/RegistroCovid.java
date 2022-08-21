import java.util.*;
public class RegistroCovid implements Comparable <RegistroCovid>
{
    private Date fecha;
    private String codigoPais;
    private String pais;
    private String regionOrg;
    private int casosNuevos;
    private long casosAcumulados;
    private int muertesNuevas;
    private long muertesAcumuladas;

    public Date getFecha() {
        return fecha;
    }
    public String getCodigoPais() {
        return codigoPais;
    }
    public String getPais() {
        return pais;
    }
    public String getRegionOrg() {
        return regionOrg;
    }
    public int getCasosNuevos() {
        return casosNuevos;
    }
    public long getCasosAcumulados() {
        return casosAcumulados;
    }
    public int getMuertesNuevas() {
        return muertesNuevas;
    }
    public long getMuertesAcumuladas() {
        return muertesAcumuladas;
    }

    public RegistroCovid(Date fecha, String codigoPais, String pais, String regionOrg, int casosNuevos, long casosAcumulados, int muertesNuevas, long muertesAcumuladas) {
        this.fecha = fecha;
        this.codigoPais = codigoPais;
        this.pais = pais;
        this.regionOrg = regionOrg;
        this.casosNuevos = casosNuevos;
        this.casosAcumulados = casosAcumulados;
        this.muertesNuevas = muertesNuevas;
        this.muertesAcumuladas = muertesAcumuladas;
    }
    
    @Override
    public int compareTo(RegistroCovid o) 
    {
        if(o == null) 
            throw new NoSuchElementException("El ADT a comparar es nulo");
        if (casosNuevos<o.getCasosNuevos())
            return -1;
        else if (casosNuevos>o.getCasosNuevos())
            return 1;
        else
            return 0;
    }
    @Override
    public String toString()
    {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        return cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)+" | "+codigoPais+" | "+pais+" | "+regionOrg+" | "+casosNuevos+" | "+casosAcumulados+" | "+muertesNuevas+" | "+muertesAcumuladas;
    }
}