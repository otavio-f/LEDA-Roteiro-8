package tad.listasEncadeadas;

import tad.util.Conversor;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T>{

	NodoListaEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	public ListaEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaEncadeada<T>();
		cauda = new NodoListaEncadeada<T>();
		cabeca.setProximo(cauda);
	}
	

	@Override
	public boolean isEmpty() {
		return (this.cabeca.getProximo() == this.cauda);
	}

	@Override
	public int size() {
		int resultado = 0;
		NodoListaEncadeada<T> corrente = this.cabeca.getProximo();
		while(corrente != this.cauda) {
			resultado++;
			corrente = corrente.getProximo();
		}
		return resultado;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		NodoListaEncadeada<T> corrente = this.cabeca.getProximo();
		while(corrente != this.cauda) {
			if(chave.equals(corrente.getChave())) {
				if(corrente.getProximo() == this.cauda)
					return new NodoListaEncadeada<T>(corrente.getChave());
				return corrente;
			}
			else
				corrente = corrente.getProximo();
		}

		return null;
	}

	@Override
	public void insert(T chave) {
		//1. Craiar o novo registro
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<T>(chave);
		
		//2. Inserir o novo nó na lista
		NodoListaEncadeada<T> ultimo = this.cabeca;
		while(ultimo.getProximo() != this.cauda)
			ultimo = ultimo.getProximo();

		ultimo.setProximo(novoNo);
		novoNo.setProximo(this.cauda);
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) {
		if(this.isEmpty())
			throw new ListaVaziaException();
		NodoListaEncadeada<T> anterior = this.cabeca;
		while(!chave.equals(anterior.getProximo().getChave()) && anterior.getProximo() != this.cauda)
			anterior = anterior.getProximo();

		NodoListaEncadeada<T> alvo = anterior.getProximo();
		anterior.setProximo(anterior.getProximo().getProximo());

		return alvo;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		if(this.isEmpty())
			return null;
		// Criar um array usando a classe utilitária conversor
		Conversor<T> c = new Conversor<T>();
		T[] meuArray = c.gerarArray(clazz, this.size());
		NodoListaEncadeada<T> corrente = this.cabeca.getProximo();
		for(int i=0; i<meuArray.length; i++) {
			meuArray[i] = corrente.getChave();
			corrente = corrente.getProximo();
		}

		return meuArray;
	}

	@Override
	public String imprimeEmOrdem() {
		if(this.cabeca.getProximo() == this.cauda)
			return "";

		StringBuilder texto = new StringBuilder();
		NodoListaEncadeada<T> corrente = this.cabeca.getProximo();

		while(corrente.getProximo() != this.cauda) {
			texto.append(corrente.getChave()).append(", ");
			corrente = corrente.getProximo();
		}

		texto.append(corrente.getChave());

		return texto.toString();
	}


	@Override
	public String imprimeInverso() {
		if(this.isEmpty())
			return "";

		NodoListaEncadeada<T> ultimo = this.cabeca.getProximo();

		while(ultimo.getProximo() != this.cauda) {
			ultimo = ultimo.getProximo();
		}

		StringBuilder texto = new StringBuilder();
		NodoListaEncadeada<T> corrente;

		while(ultimo != this.cabeca.getProximo()) {
			corrente = this.cabeca.getProximo();
			while(corrente.getProximo() != ultimo) {
				corrente = corrente.getProximo();
			}
			texto.append(ultimo.getChave());
			texto.append(", ");
			ultimo = corrente;
		}

		texto.append(this.cabeca.getProximo().getChave());

		return texto.toString();
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaEncadeada<T> resultado = this.cabeca;
		while(resultado.getProximo() != this.cauda) {
			if(chave.equals(resultado.getChave()))
				return resultado.getProximo();
			resultado = resultado.getProximo();
		}
		return null;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaEncadeada<T> resultado = this.cabeca;
		while(resultado.getProximo() != this.cauda) {
			if(chave.equals(resultado.getProximo().getChave()))
				return resultado;
			resultado = resultado.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave, int index) {
		if(index > this.size() || index < 0)
			throw new RuntimeException("Indice invalido!");

		NodoListaEncadeada<T> alvo = this.cabeca;
		for(int i=0; i<index; i++) {
			alvo = alvo.getProximo();
		}
		NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<T>(chave);
		novoNodo.setProximo(alvo.getProximo());
		alvo.setProximo(novoNodo);
	}
}
