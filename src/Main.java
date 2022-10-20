import java.io.*;
import java.util.Scanner;

/**
 * Comparare metodi di ordinamento
 *
 * @author Scarsato Andrea and Balbiani Gabriele
 * @version 20/10/2022
 */

public class Main{

    public static void selectionSort(String [] array, int n) {
        for(int i = 0; i < n-1; i++) {
            int minimo = i; //Partiamo dall' i-esimo elemento

            for(int j = i+1; j < n; j++) {
                //confronto del carattere c delle due parole
                int c = 0;
                while(array[minimo].charAt(c) == array[j].charAt(c)){
                    c++;
                    if (c >= array[minimo].length()-1 || c >= array[j].length()-1)
                        break;
                }
                     //finchè sono uguali passa alla lettera dopo

                if(array[minimo].charAt(c) > array[j].charAt(c)) {
                    minimo = j;
                }
            }
            //Se minimo e diverso dall' elemento di partenza
            //allora avviene lo scambio
            if(minimo!=i) {
                String k = array[minimo];
                array[minimo]= array[i];
                array[i] = k;
            }
        }
    }


    public static int partition (String[] v, int inizio, int fine){
        int i = inizio;
        int j = fine-1;
        while (i <= j){
            while (v[i].compareTo(v[fine]) < 0)
                i++;

            while (j > inizio && v[j].compareTo(v[fine]) >= 0)
                j--;

            if (i >= j)
                break;

            String temp = v[i];
            v[i] = v[j];
            v[j] = temp;
        }
        String temp = v[i];
        v[i] = v[fine];
        v[fine] = temp;
        return i;
    }

    public static void quickSort (String[] v, int inizio, int fine){
        if (inizio>=fine)
            return;
        int i = partition(v,inizio,fine);
        quickSort(v,inizio,i-1);
        quickSort(v,i+1,fine);
    }

    public static void stampaFile (String[] v, int n, int t){
        FileWriter f;
        try{
            if (t == 0)
                f = new FileWriter("alunni_s_" + n + ".txt");
            else
                f = new FileWriter("alunni_q_" + n + ".txt");
            for (int j = 0; j < n; j++) {
                f.write(v[j]);
                f.write("\n");
            }
            f.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void leggiFile (String[] v, int dim){
        int i = 0;
        try{
            File f = new File("Nomi_" + dim + ".csv");
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String linea = s.nextLine(); //linea diventa la riga del file
                v[i] = linea;
                i++;
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void scriviTempo (double[] a, double[] b){
        FileWriter tempi;
        int dim = 20;
        try{
            tempi = new FileWriter("Risultati.csv");
            for (int i = 0; i < 5; i++) {
                tempi.write(dim + ";" + a[i] + ";" + b[i] + "\n");
                dim *= 10;
            }
            tempi.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main (String[] args){

        int dim = 20;
        String []v = new String [2000000];
        String []a = new String [2000000];
        double[] tempo_sel = new double[6];
        double[] tempo_qui = new double[6];
        int k = 0;

        for (int j = 0; j < 6; j++) {

            /**
             * selection sort
             */
            leggiFile(v, dim);
            long inizio = System.currentTimeMillis();
            selectionSort(v, dim);
            long fine = System.currentTimeMillis();
            System.out.println("con " + dim + " elementi il selection sort ci ha messo: " + (fine-inizio)/1000.0 + " secondi");
            stampaFile(v, dim, 0);

            tempo_sel[k] = (fine-inizio)/1000.0;

            /**
             * quick sort
             */
            leggiFile(a, dim);
            inizio = System.currentTimeMillis();
            quickSort(a, 0, dim-1);
            fine = System.currentTimeMillis();
            System.out.println("con " + dim + " elementi il quick sort " +
                    "ci ha messo: " + (fine - inizio)/1000.0 + " secondi");
            stampaFile(a, dim, 1);
            tempo_qui[k] = (fine - inizio)/1000.0; k++;

            dim *= 10;
        }

        scriviTempo(tempo_sel, tempo_qui);
    }
}