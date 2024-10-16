package tad.listasEncadeadas;

import tad.util.Conversor;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {
	
	//TODO: implementar o nó cabeça
	//TODO: implementar o nó cauda 
	//TODO: implementar as sentinelas
	
	NodoListaDuplamenteEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaDuplamenteEncadeada<T> cauda = null;// Estratégia usando marcação sentinela
	
	public ListaDuplamenteEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();
		cabeca.setProximo(cauda);
		
		// lista circular
		cabeca.setAnterior(cauda);
		cauda.setAnterior(cabeca);
		
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
			corrente = corrente.getProximo();
			resultado++;
		}
		return resultado;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> corrente = this.cauda.getAnterior();
		while(corrente != this.cabeca) {
			if(chave.equals(corrente.getChave())) {
				NodoListaDuplamenteEncadeada<T> resultado = new NodoListaDuplamenteEncadeada<>(corrente.getChave());
				if(corrente.getAnterior() != this.cabeca)
					resultado.setAnterior(corrente.getAnterior());
				if(corrente.getProximo() != this.cauda)
					resultado.setProximo(corrente.getProximo());
				return resultado;
			}
			else
				corrente = corrente.getAnterior();
		}

		return null;
	}

	@Override
	public void insert(T chave) {
		//1. Craiar o novo registro
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);

		//2. Inserir o novo nó na lista
		NodoListaDuplamenteEncadeada<T> ultimo = this.cauda.getAnterior();
		ultimo.setProximo(novoNo);

		novoNo.setAnterior(ultimo);
		novoNo.setProximo(this.cauda);

		this.cauda.setAnterior(novoNo);
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
		if(this.cabeca.getProximo() == this.cauda)
			return "";

		StringBuilder texto = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> corrente = this.cauda.getAnterior();

		while(corrente.getAnterior() != this.cabeca) {
			texto.append(corrente.getChave()).append(", ");
			corrente = corrente.getAnterior();
		}

		texto.append(corrente.getChave());

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
		NodoListaDuplamenteEncadeada<T> resultado = this.cauda;
		while(resultado.getAnterior() != this.cabeca) {
			if(chave.equals(resultado.getChave()))
				return resultado.getAnterior();
			resultado = resultado.getAnterior();
		}
		return null;
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
	public void inserePrimeiro(T elemento) {
		//1. Craiar o novo registro
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(elemento);

		//2. Inserir o novo nó na lista
		NodoListaDuplamenteEncadeada<T> primeiro = (NodoListaDuplamenteEncadeada<T>) this.cabeca.getProximo();
		primeiro.setAnterior(novoNo);

		novoNo.setProximo(primeiro);
		novoNo.setAnterior(this.cabeca);

		this.cabeca.setProximo(novoNo);
		
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		if(this.isEmpty())
			throw new ListaVaziaException();
		NodoListaDuplamenteEncadeada<T> ultimo = this.cauda.getAnterior();

		ultimo.getAnterior().setProximo(this.cauda);
		this.cauda.setAnterior(ultimo.getAnterior());
		return ultimo;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		if(this.isEmpty())
			throw new ListaVaziaException();

		NodoListaDuplamenteEncadeada<T> segundo = (NodoListaDuplamenteEncadeada<T>) this.cabeca.getProximo().getProximo();
		NodoListaDuplamenteEncadeada<T> primeiro = segundo.getAnterior();

		this.cabeca.setProximo(segundo);
		segundo.setAnterior(this.cabeca);

		return primeiro;
	}

	@Override
	public void insert(T chave, int index) {
		if(index > this.size() || index < 0)
			throw new RuntimeException("Indice invalido!");

		NodoListaDuplamenteEncadeada<T> alvo = this.cauda;
		for(int i=0; i<index; i++) {
			alvo = alvo.getAnterior();
		}
		NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<T>(chave);
		novoNodo.setProximo(alvo.getProximo());
		alvo.setProximo(novoNodo);
	}
}
