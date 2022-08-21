public class bRotacion
{
    public static int rotan(String a, String b)
    {
        int r=0;
        boolean sirrotan = false;
        for (int i = 0; i < a.length(); i++) 
        {
            a = a.substring(1, a.length())+a.charAt(0);
            r++;
            if (a.equals(b))
            {
                sirrotan = true;
                break;
            }
        }
        if (sirrotan==true)
        {
            if (r>a.length()/2)
                return a.length()-r;
            else
                return r;
        }
        else
           return -1;
    }
}