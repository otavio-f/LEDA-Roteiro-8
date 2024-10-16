package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.ListaEncadeadaImpl;

public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

	private ListaEncadeadaIF<Integer> listaEncadeada = new ListaEncadeadaImpl<Integer>();
	private final int tamanho;

	public MinhaPilhaEncadeada() {
		this.tamanho = 5;
	}

	public MinhaPilhaEncadeada(int tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if(this.isFull())
			throw new PilhaCheiaException();
		this.listaEncadeada.insert(item);
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if(this.isEmpty())
			throw new PilhaVaziaException();
		Integer[] lista = this.listaEncadeada.toArray(Integer.class);
		Integer resultado = lista[lista.length-1];
		this.listaEncadeada.remove(resultado);
		return resultado;
	}

	@Override
	public Integer topo() {
		if(this.isEmpty()) {
			return null;
		}

		Integer[] lista = this.listaEncadeada.toArray(Integer.class);
		return lista[lista.length-1];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.listaEncadeada.isEmpty();
	}

	public boolean isFull() {
		return this.listaEncadeada.size() == this.tamanho;
	}
}
