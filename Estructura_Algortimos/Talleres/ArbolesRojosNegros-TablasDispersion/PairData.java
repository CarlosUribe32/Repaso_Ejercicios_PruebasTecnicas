import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PairData {
    transient SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    private GregorianCalendar date;
    private String symbol;
    private double open;
    private double high;
    private double low;
    private double close;

    public GregorianCalendar getDate() {
        return date;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public PairData(String linea) throws ParseException {
        String [] split = linea.split(",");
        date = new GregorianCalendar();
        date.setTime(formato.parse(split[1]));
        symbol = split[2];
        open = Double.parseDouble(split[3]);
        high = Double.parseDouble(split[4]);
        low = Double.parseDouble(split[5]);
        close = Double.parseDouble(split[6]);
    }
    
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
    @Override
    public String toString(){
        return date.get(Calendar.DATE)+"-"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR)+" | "+symbol+" | "+open+" | "+high+" | "+low+" | "+close;
    }
}