package tad.fila;

import tad.listasEncadeadas.ListaEncadeadaIF;
import tad.listasEncadeadas.ListaEncadeadaImpl;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	private final ListaEncadeadaIF<Integer> filaEncadeada = new ListaEncadeadaImpl<Integer>();
	private final int tamanho;

	public MinhaFilaEncadeada() {
		this.tamanho = 5;
	}

	public MinhaFilaEncadeada(int tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if(this.isFull())
			throw new FilaCheiaException();
		this.filaEncadeada.insert(item);
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if(this.isEmpty())
			throw new FilaVaziaException();

		final Integer primeiro = this.filaEncadeada.toArray(Integer.class)[0];
		this.filaEncadeada.remove(primeiro);

		return primeiro;
	}

	@Override
	public Integer verificarCauda() {
		if(this.isEmpty())
			return null;
		Integer[] lista = this.filaEncadeada.toArray(Integer.class);
		return lista[lista.length-1];
	}

	@Override
	public Integer verificarCabeca() {
		if(this.isEmpty())
			return null;
		return this.filaEncadeada.toArray(Integer.class)[0];
	}

	@Override
	public boolean isEmpty() {
		return this.filaEncadeada.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.filaEncadeada.size() == this.tamanho;
	}

}
