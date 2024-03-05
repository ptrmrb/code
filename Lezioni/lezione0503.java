package Lezioni;

import terminale.*; // * vuol dire importa tutte le classi presenti nel package termiale 

public class lezione0503 
{
    public static void main(String[] args)
    {
        String nome  = Terminale.richiediStringa("Come ti chiami?");
        String risposta = " Ciao, " + nome + "!";
        Terminale.stampa(risposta);

        double valore = Math.PI;
        Terminale.stampa(valore);
        Math.sqrt(valore);

        int[] a = new int[] {1,0,-2, 3, 5, -1, 3};
        int positivi = selezionaPositivi1(a);

    }
    
    public static int[] selezionaPositivi1( int[] arr ) 
    {
        int numeroPositivi = 0 ;
        for ( int n : arr)
            if (n > 0)
                numeroPositivi++;
        
        int[] ret = new int[numeroPositivi];

        int posizioneLibera = 0; // tiene a mente la prima posizione libera in cui possiamo posizionare il valore e tiene anche a mente il numero di elementi già inserito
        for ( int i = 0; i < arr.length; i++ )
            if (arr[i] > 0)
            {
                ret[posizioneLibera] = arr[i];
                posizioneLibera++;

                /*if (posizioneLibera == numeroPositivi)
                    break;*/
            }

        return ret;
    
    public static int[] selezionaPositivi2( int[] arr2 )
    { // quando creo una struttura d'appoggio, il suo nome è buffer

        int[] buf = new int[arr2.length];
        int posizioneLibera1 = 0;

        for(int n : arr2 )
            if (n > 0)
            {
                buf[posizioneLibera1] = n;
                posizioneLibera1++;
            }

        int [] ret = new int[posizioneLibera1];
        for( int i = 0; i < posizioneLibera1; i++)
            ret[i] = buf[i];

        return ret;

    }
    }
}

