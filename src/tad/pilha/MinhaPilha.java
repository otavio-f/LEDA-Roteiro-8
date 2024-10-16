package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {
	
	private int tamanho = 5;
	private int cabeca = 0;
	private Integer[] meusDados = null;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		meusDados = new Integer[tamanho];
	}
	
	public MinhaPilha() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if(this.isFull())
			throw new PilhaCheiaException();
		this.meusDados[this.cabeca] = item;
		this.cabeca++;
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if(this.isEmpty())
			throw new PilhaVaziaException();
		Integer resultado = this.meusDados[this.cabeca-1];
		this.cabeca--;
		return resultado;
	}

	@Override
	public Integer topo() {
		if(this.isEmpty())
			return null;
		return this.meusDados[this.cabeca-1];
	}

	@Override
	public PilhaIF<Integer> multitop(int k) {
		throw new UnsupportedOperationException("Implementar");
	}

	@Override
	public boolean isEmpty() {
		return this.cabeca == 0;
	}

	private boolean isFull() {
		return this.cabeca == this.tamanho;
	}
}
