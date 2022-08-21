import edu.princeton.cs.algs4.*;
public class ejemplo
{
    public static void main(String[] args) 
    {
        int distinct=0, words=0;
        int minlen = Integer.valueOf(2);
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        while (!StdIn.isEmpty())
        {
            String key = StdIn.readString();
            if(key.length() < minlen)
                continue;
            words++;
            if(st.contains(key))
                st.put(key, st.get(key)+1);
            else
            {
                st.put(key, 1);
                distinct++;
            }
        } 
        StdOut.println("Palabras: "+words);
        StdOut.println("Distintas: "+distinct);

        for (int i = 0; i < st.size(); i++) {
            StdOut.println("Rank: "+i+" palabra: "+st.select(i)+" frecuencia: "+st.get(st.select(i)));            
        }

        //Para sacar las 10 palabras mas repetidas (Usaremos el monticulo MaxQP)
        MinPQ<Integer> pq = new MinPQ<>(10);
        int[] posllaves = new int[10];
        short palabras = 0;
        for (int i = 0; i < 10 && i < st.size(); i++) {
            posllaves [i] = i;
            pq.insert(st.get(st.select(i)));
            palabras++;
        }
        int pos=posllaves.length, c=0;
        for (int i = 10; i < st.size(); i++) {
            
            int a = st.get(st.select(i));
            int word=i;
            if (a<= pq.min())
            {

            }
            else
            {
                int b = pq.delMin();
                if(c!=b){
                    c=b;
                    pos=posllaves.length;
                }
                for (int j = posllaves.length-1; j >=0; j--) {
                    pos--;
                    if(st.get(st.select(j))==b)
                        break;
                }
                
                for (int j = 0; j < posllaves.length; j++) {
                    if(posllaves[j]==pos)
                    {
                        posllaves[j] = word;
                        break; 
                    }
                }
                pq.insert(a);
            }
        }
        StdOut.println("\n");
        for (int i = 0; i < palabras; i++) {
            StdOut.println("Palabra: "+st.select(posllaves[i])+" frecuencia: "+st.get(st.select(posllaves[i])));
        }
    }
}