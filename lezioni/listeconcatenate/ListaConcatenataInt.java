package listeconcatenate;

import java.util.*;

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
	{
		if (eVuota()) 
		{	throw new EccezioneListaVuota();
		}
		
		if (lunghezza == 1) // se c'è un solo elemento nella lista
		{	svuota();
			return;
		}
	
		NodoInt penultimo = testa; // trova il penultimo nodo
		while (penultimo.getSuccessivo() != coda)
		{	penultimo = penultimo.getSuccessivo();
		}
		
		penultimo.setSuccessivo(null); // rimuovi il riferimento alla coda e impostala a null
		coda = penultimo;
		
		lunghezza--; // decrementa la lunghezza della lista
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
	
	private int minimoDa(NodoInt n)
	{	if(n.getSuccessivo() == null)
			return n.getInfo();
		return Math.min(n.getInfo(), minimoDa(n.getSuccessivo()));
	}
	
	public int minimo()
	{	if(eVuota())
			throw new EccezioneListaVuota();		
		return minimoDa(testa);		
	}
	
	private int massimoDa(NodoInt n)
	{	if(n.getSuccessivo() == null)
			return n.getInfo();
		return Math.max(n.getInfo(), massimoDa(n.getSuccessivo()));
	}
	
	public int massimo()
	{	if(eVuota())
			throw new EccezioneListaVuota();		
		return massimoDa(testa);		
	}
}
