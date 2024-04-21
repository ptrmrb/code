/*
 * Progettare e implementare una classe Concessionaria tenendo conto delle seguenti
 * specifiche:
 * 		-	La concessionaria contiene un magazzino (cioè una lista di automobili).
 * 		-	La classe deve contenere metodi per:
 * 				
 * 				-	trovare la casa produttrice più ricorrente;
 * 				-	trovare l'auto con la cilindrata pi� bassa tra quelle con un prezzo
 * 					inferiore a 10.000 euro;
 * 				-	trovare l'auto con la cilindrata pi� alta tra quelle con un prezzo
 * 					superiore alla media dei prezzi delle auto;
 * 				-	trovare le case produttrici che producono solo auto con un prezzo
 * 					inferiore alla media dei prezzi delle auto;
 * 				-	trovare le case produttrici che producono solo auto con la stessa
 * 					cilindrata.
 */

package esercizi_casa.myConcessionaria;

import java.util.*;
import terminale.*;

public class MyConcessionaria {

    private ArrayList<MyAutomobile> magazzino;

    //costruttore

    public MyConcessionaria() 
     { 	magazzino = new ArrayList<>(); 
     }

    // aggiungere un'auto al magazzino;
    public void aggiungiAuto( MyAutomobile a )
    {   magazzino.add(a);    
    }

    // rimuovere un'auto dal magazzino;
    public void rimuoviAuto ( MyAutomobile a )
    {   magazzino.remove(a);
    }
    
    // stampare tutte le auto 
    public void stampaAll()
    {   for( MyAutomobile a: magazzino )
            Terminale.stampa(a);
    }

    // calcolare la somma dei prezzi di tutte le auto;
    public float sommaPrezziAuto()
    {
        float somma = 0;
        for( MyAutomobile a : magazzino)
        {   somma += a.getPrezzo(); 
        }
        return somma;
    }
    

            


    }

