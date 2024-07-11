package traccia20200703;

import java.util.*;

import listeconcatenate.EccezioneIndiceNonValido;
import listeconcatenate.EccezioneListaVuota;
import terminale.Terminale;

class NodoInt
{	private int info;
	private NodoInt successivo;
	
	public NodoInt(int info, NodoInt successivo)
	{	this.info = info;
		this.successivo = successivo;		
	}
	
	public NodoInt(int info)
	{	this.info = info;
		successivo = null;		
	}
	
	public int getInfo()
	{	return info;		
	}
	
	public NodoInt getSuccessivo()
	{	return successivo;		
	}
	
	public void setInfo(int info)
	{	this.info = info;		
	}
	
	public void setSuccessivo(NodoInt successivo)
	{	this.successivo = successivo;		
	}
	
	public String toString()
	{	return ""+info;		
	}
	
	public boolean haInfo(int info)
	{	return this.info == info;
	}
}

public class ListaConcatenataInt 
{	private NodoInt testa;
	private NodoInt coda;
	private int lunghezza;
	
	public ListaConcatenataInt()
	{	inizializza();
	}
	
	public ListaConcatenataInt(ListaConcatenataInt l)
	{	inizializza();
		for(NodoInt corrente = l.testa; corrente != null;
				corrente = corrente.getSuccessivo())
			aggiungiInCoda(corrente.getInfo());
	}
	
	public ListaConcatenataInt(int[] a)
	{	inizializza();
		for(int v : a)
			aggiungiInCoda(v);
	}
	
	public ListaConcatenataInt(ArrayList<Integer> a)
	{	inizializza();
		for(int v : a)
			aggiungiInCoda(v);
	}
	
	public ListaConcatenataInt(LinkedList<Integer> a)
	{	inizializza();
		for(int v : a)
			aggiungiInCoda(v);
	}
	
	public boolean equals(Object o)
	{	if(o == null)
			return false;
		if(o == this)
			return true;
		if(!(o instanceof ListaConcatenataInt))
			return false;
		ListaConcatenataInt l = (ListaConcatenataInt)o;
		if(lunghezza != l.lunghezza)
			return false;
		NodoInt corrente = testa;
		NodoInt correnteL = l.testa;
		while(corrente != null)
		{	if(corrente.getInfo() != correnteL.getInfo())
				return false;
			corrente = corrente.getSuccessivo();
			correnteL = correnteL.getSuccessivo();
		}
		return true;
	}
	
	public ArrayList<Integer> adArrayList()
	{	ArrayList<Integer> ret = new ArrayList<>();
		for(NodoInt corrente = testa; corrente != null; 
				corrente = corrente.getSuccessivo())
			ret.add(corrente.getInfo());
		return ret;
	}
	
	public LinkedList<Integer> aLinkedList()
	{	LinkedList<Integer> ret = new LinkedList<>();
		for(NodoInt corrente = testa; corrente != null; 
				corrente = corrente.getSuccessivo())
			ret.add(corrente.getInfo());
		return ret;
	}
	
	public int[] adArray()
	{	if(eVuota())
			throw new EccezioneListaVuota();
		int[] ret = new int[lunghezza];
		int posLibera = 0;
		for(NodoInt corrente = testa; corrente != null; 
				corrente = corrente.getSuccessivo())
		{	ret[posLibera] = corrente.getInfo();
			posLibera++;			
		}
		return ret;
	}
	
	public ListaConcatenataInt listaInvertita()
	{	ListaConcatenataInt ret = new ListaConcatenataInt();
		for(NodoInt corrente = testa; corrente != null; 
				corrente = corrente.getSuccessivo())
			ret.aggiungiInTesta(corrente.getInfo());
		return ret;
	}
	
	public int get(int i)
	{	if(i < 0 || i >= lunghezza)
			throw new EccezioneIndiceNonValido();
		NodoInt corrente = testa;
		for(int k = 1; k <= i; k++)
			corrente = corrente.getSuccessivo();
		return corrente.getInfo();		
	}
	
	public int indiceDi(int valore)
	{	int i = 0;
		for(NodoInt corrente = testa; corrente != null; 
			corrente = corrente.getSuccessivo())
		{	if(corrente.haInfo(valore))
				return i;
			i++;
		}
		return -1;
	}
	
	public boolean contiene(int valore)
	{	return indiceDi(valore) != -1;
	}
	
	private void inizializza()
	{	testa = null;
		coda = null;
		lunghezza = 0;
	}
	
	public void svuota()
	{	inizializza();		
	}
	
	public boolean eVuota()
	{	return lunghezza == 0;		
	}
	
	public int getLunghezza()
	{	return lunghezza;		
	}

	public void aggiungiInCoda(int valore)
	{	NodoInt nuovo = new NodoInt(valore);
		if(eVuota())
			testa = nuovo;
		else
			coda.setSuccessivo(nuovo);
		coda = nuovo;
		lunghezza++;
	}
	
	public void aggiungiInTesta(int valore)
	{	NodoInt nuovo = new NodoInt(valore,testa);
		testa = nuovo;
		if(eVuota())
			coda = nuovo;
		lunghezza++;
	}
	
	public void rimuoviTesta()
	{	if(eVuota())
			throw new EccezioneListaVuota();
		if(lunghezza == 1)
			svuota();
		else
		{	testa = testa.getSuccessivo();
			lunghezza--;
		}
	}
	
	public void rimuoviCoda()
	{	if(eVuota())
			throw new EccezioneListaVuota();
		if(lunghezza == 1)
			svuota();
		else
		{	for(NodoInt corrente = testa; corrente != null;
					corrente = corrente.getSuccessivo())
				if(corrente.getSuccessivo() == coda)
				{	corrente.setSuccessivo(null);
					coda = corrente;
				}
			lunghezza--;
		}	
	}
	
	public void rimuovi(int indice)
	{	if(indice < 0 || indice >= lunghezza)
			throw new EccezioneIndiceNonValido();
		if(indice == 0)
		{	rimuoviTesta();
			return;
		}
		if(indice == lunghezza - 1)
		{	rimuoviCoda();
			return;
		}
		NodoInt corrente = testa;
		for(int i = 1; i < indice; i++)
			corrente = corrente.getSuccessivo();
		corrente.setSuccessivo(corrente.getSuccessivo().getSuccessivo());
		lunghezza--;
	}
	
	public boolean rimuoviPrimo(int valore)
	{	// ALTERNATIVA:
		//rimuovi(indiceDi(valore));
		if(eVuota())
			return false;
		if(testa.haInfo(valore))
		{	rimuoviTesta();
			return true;
		}
		for(NodoInt corrente = testa; corrente != null;
					corrente = corrente.getSuccessivo())
		{	NodoInt successivo = corrente.getSuccessivo();
			if(successivo != null && successivo.haInfo(valore))
			{	corrente.setSuccessivo(successivo.getSuccessivo());
				if(successivo == coda)
					coda = corrente;
				lunghezza--;
				return true;
			}
		}
		return false;
	}
	
	public String toString()
	{	String ret = "[";
		for(NodoInt corrente = testa; corrente != null;
				corrente = corrente.getSuccessivo())
		{	ret += corrente;
			if(corrente.getSuccessivo() != null)
				ret += ", ";
		}
		ret += "]";
		return ret;
	}
	
	private int sommaDa(NodoInt n)
	{	if(n == null)
			return 0;
		return n.getInfo() + sommaDa(n.getSuccessivo());
	}
	
	public int somma()
	{	return sommaDa(testa);		
	}
	
	private int contaDa(NodoInt n, int valore)
	{	if(n == null)
			return 0;
		if(n.haInfo(valore))
			return 1 + contaDa(n.getSuccessivo(),valore);
		return contaDa(n.getSuccessivo(),valore);
		// ALTERNATIVA DAL SECONDO IF IN POI:
		// return (n.haInfo(valore) ? 1 : 0) + contaDa(n.getSuccessivo(),valore);
	}
	
	public int conta(int valore)
	{	return contaDa(testa,valore);		
	}
	
	public int minimo()
	{	if(eVuota())
			throw new EccezioneListaVuota();		
		return minimoDa(testa);		
	}
	private int minimoDa(NodoInt n)
	{	if(n.getSuccessivo() == null)
			return n.getInfo();
		return Math.min(n.getInfo(), minimoDa(n.getSuccessivo()));
	}
	
	

	public int massimo()
	{	if(eVuota())
			throw new EccezioneListaVuota();		
		return massimoDa(testa);		
	}

	private int massimoDa(NodoInt n)
	{	if(n.getSuccessivo() == null)
			return n.getInfo();
		return Math.max(n.getInfo(), massimoDa(n.getSuccessivo()));
	}
	
	/*
	 * Si arricchisca la classe ListaConcatenataInt sviluppata durante il corso con un metodo
	 * alternati che restituisca true se e solo se, scorrendo la lista dall’inizio alla fine, si incontrano valori
	 * alternati positivi e negativi. In altri termini, per ogni nodo deve valere che: se esso contiene un valore positivo,
	 * allora il successivo (se esiste) contiene un valore negativo, e viceversa. Si assuma inoltre che una lista vuota
	 * soddisfi la proprietà verificata da alternati. Il metodo alternati dovrà essere ricorsivo o invocare un opportuno metodo
	 * ricorsivo sulla classe NodoInt.
	 */

	public boolean alternati( )
	{
		if ( eVuota() )
			return true; 
		return alternati(testa); 
	}

	private boolean alternati( NodoInt corrente)
	{	
		Terminale.stampa(corrente.getInfo());
		if ( corrente.getSuccessivo() == null ) return true; 

		if ( corrente.getSuccessivo().getInfo() == 0 ){
			if ( corrente.getSuccessivo().getSuccessivo() == null ) // se lo zero è l'ultimo elemento della lista restutuisci true 
				return true;
			return alternati(corrente.getSuccessivo().getSuccessivo()); // altrimenti lo salti e vai al valore successivo 
		}

		if ( corrente.getInfo() * corrente.getSuccessivo().getInfo() < 0 )
			return alternati(corrente.getSuccessivo());
		return false;
	}

	public static void main(String[] args) {
		ListaConcatenataInt lista = new ListaConcatenataInt(new int[] {2, -3, 7, -1,  3, -2}); 
		Terminale.stampa(lista.alternati());
	}


}