package tad.conjuntoDinamico;

import tad.listasEncadeadas.ListaDuplamenteEncadeadaImpl;
import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.NodoListaDuplamenteEncadeada;
import tad.listasEncadeadas.NodoListaEncadeada;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {

	//lista encadeada
	ListaEncadeadaIF<Integer> meusDados = new ListaDuplamenteEncadeadaImpl<>();

	@Override
	public void inserir(Integer item) {
		meusDados.insert(item);
		
	}

	@Override
	public Integer remover(Integer item) throws ConjuntoVazioException {
		if(this.tamanho() == 0)
			throw new ConjuntoVazioException("Conjunto vazio!");
		return this.meusDados.remove(item).getChave();
	}

	@Override
	public Integer predecessor(Integer item) {
		if(this.tamanho() == 0)
			throw new RuntimeException("Conjunto Vazio!");
		final NodoListaDuplamenteEncadeada<Integer> alvo = (NodoListaDuplamenteEncadeada<Integer>) this.meusDados.search(item);
		if(alvo == null)
			throw new RuntimeException("Nao encontrado!");
		if(alvo.getAnterior() == null)
			return null;
		return alvo.getAnterior().getChave();
	}

	@Override
	public Integer sucessor(Integer item) {
		if(this.tamanho() == 0)
			throw new RuntimeException("Conjunto Vazio!");
		final NodoListaEncadeada<Integer> alvo = this.meusDados.search(item);
		if(alvo == null)
			throw new RuntimeException("Nao encontrado!");
		if(alvo.getProximo() == null)
			return null;
		return alvo.getProximo().getChave();
	}

	@Override
	public int tamanho() {
		return this.meusDados.size();
	}

	@Override
	public Integer buscar(Integer item) {
		final NodoListaEncadeada<Integer> resultado = this.meusDados.search(item);
		if(resultado == null)
			return null;
		return resultado.getChave();
	}

	@Override
	public Integer minimum() {
		Integer[] lista = this.meusDados.toArray(Integer.class);
		Integer resultado = lista[0];
		for(int i=1; i<lista.length; i++) {
			if(lista[i].compareTo(resultado) < 0)
				resultado = lista[i];
		}
		return resultado;
	}

	@Override
	public Integer maximum() {
		Integer[] lista = this.meusDados.toArray(Integer.class);
		Integer resultado = lista[0];
		for(int i=1; i<lista.length; i++) {
			if(lista[i].compareTo(resultado) > 0)
				resultado = lista[i];
		}
		return resultado;
	}
}
