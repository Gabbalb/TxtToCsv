import java.io.*;
import java.util.Scanner;

public class Main{
    /*public static Studente strutturaNomi (String s, Studente a){
        int pos = s.indexOf(';');
        a.nome = s.substring(0, pos);

        //String nuova = s.substring(pos+1, s.length()-1);
        a.cognome = s.substring(pos+1, s.length());
        //System.out.println(a.nome);
        //System.out.println(a.cognome);

        return a;
    }*/

    public static double timeInizio(){
        return System.currentTimeMillis();
    }

    public static int tempo (double inizio){
        double fine = System.currentTimeMillis();
        return (int) (fine - inizio / 1000.0);
    }

    public static void selectionSort(String [] array, int n) {
        for(int i = 0; i < n-1; i++) {
            int minimo = i; //Partiamo dall' i-esimo elemento

            for(int j = i+1; j < n; j++) {
                //confronto del carattere c delle due parole
                int c = 0;

                while(array[minimo].charAt(c) == array[j].charAt(c))
                    c++; //finchè sono uguali passa alla lettera dopo

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

    public static void stampaFile (String[] v){
        try{
            FileWriter f = new FileWriter("alunni_alfabetico.txt");
            for (int j = 0; j < 4; j++) {
                f.write(v[j]);
                f.write("\n");
            }
            f.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main (String[] args){

        int dim = 20;

        //selection sort
        for (int j = 0; j < 6; j++) {

            String []v = new String [dim];

        /*for (int i = 0; i < 10; i++) {
            v[i] = new Studente();
        }*/
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

            double inizio = timeInizio();
            selectionSort(v, dim);
            int fine = tempo(inizio);
            System.out.println("con" + dim + "elementi ci ha messo: " + fine);
            stampaFile(v);

            dim *= 10;
        }


        //TODO:quick sort

        /*for (int j = 0; j < v.length; j++) {
            System.out.println(v[j]);
        }*/

        //TODO: aggumgere funzione tempo

    }
}