package tad.fila;

/**
 * Fila deve ser implementada com array fixo e estratégia circular
 * de gerenciamento de apontadores de cauda e cabeça.
 * @author fabioleite
 *
 */
public class MinhaFila implements FilaIF<Integer> {
	
	private int tamanho = 10;
	
	private int cauda = 1;
	private int cabeca = 0;
	
	private Integer[] meusDados = null;

	public MinhaFila(int tamanhoInicial) {
		tamanho = tamanhoInicial;
		meusDados = new Integer[tamanho];
	}
	
	public MinhaFila() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if(this.isFull())
			throw new FilaCheiaException();
		this.meusDados[this.cauda-1] = item;
		this.cauda++;
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if(this.isEmpty())
			throw new FilaVaziaException();
		final Integer resultado = this.meusDados[this.cabeca];
		for(int i=this.cabeca+1; i<this.cauda-1; i++)
			this.meusDados[i-1] = this.meusDados[i];
		this.cauda--;
		return resultado;
	}

	@Override
	public Integer verificarCauda() {
		return this.meusDados[this.cauda-1];
	}

	@Override
	public Integer verificarCabeca() {
		return this.meusDados[this.cabeca];
	}

	@Override
	public boolean isEmpty() {
		return (this.cauda-1 == 0);
	}

	@Override
	public boolean isFull() {
		return (this.cauda > this.tamanho);
	}

}
