package tad.conjuntoDinamico;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

	private Integer[] meusDados = null;
	private int posInsercao = 0;

	public MeuConjuntoDinamico() {
		this.meusDados = new Integer[1];
	}

	@Override
	public void inserir(Integer item) {
		if (arrayEstaCheio()) {
			aumentarArray();
		}
		meusDados[posInsercao] = item;
		posInsercao++;
	}

	private boolean arrayEstaCheio() {
        return (this.posInsercao == this.meusDados.length);
    }

	private Integer[] aumentarArray() {
		// criar um array maior (arrayMaior)
		// Qual é a taxa de aumento desse array?
		Integer arrayMaior[] = new Integer[this.meusDados.length*2];

		// copiar os dados de meusDados (array cheio)
		// colar os dados para o novo array (arrayMaior)
		for(int i=0; i<this.meusDados.length; i++)
			arrayMaior[i] = this.meusDados[i];
		this.meusDados = arrayMaior;

		return this.meusDados;
	}

	@Override
	public Integer remover(Integer item) throws ConjuntoVazioException {
		final int tam = this.tamanho();
		if(tam == 0)
			throw new ConjuntoVazioException("Conjunto vazio!");

		for(int i=0; i<tam; i++)
			if(item.equals(this.meusDados[i])) {
				// 'puxa' todos itens pra tras
				for(int k=i+1; k<this.posInsercao; k++)
					this.meusDados[k-1] = this.meusDados[k];

				// corrige a posicao de insercao
				this.posInsercao--;

				return item;
			}

		return null;
	}

	@Override
	public Integer predecessor(Integer item) {
		final int tam = this.tamanho();
		if(tam == 0)
			throw new RuntimeException("Conjunto vazio!");

		if(item.equals(this.meusDados[0]))
			return null;

		for(int i=1; i<tam; i++) {
			if(item.equals(this.meusDados[i]))
				return this.meusDados[i-1];
		}

		throw new RuntimeException("Nao encontrado!");
	}

	@Override
	public Integer sucessor(Integer item) {
		final int tam = this.tamanho();
		if(tam == 0)
			throw new RuntimeException("Conjunto vazio!");

		if(item.equals(this.meusDados[tam-1]))
			return null;

		for(int i=0; i<tam-1; i++) {
			if(item.equals(this.meusDados[i]))
				return this.meusDados[i+1];
		}

		throw new RuntimeException("Nao encontrado!");
	}

	@Override
	public int tamanho() {
		return this.posInsercao;

//		int resultado = 0;
//		for(Integer i: this.meusDados)
//			if(i != null)
//				resultado++;
//
//		return resultado;
	}

	@Override
	public Integer buscar(Integer item) {
		for(Integer i: this.meusDados)
			if(item.equals(i))
				return i;
		return null;
	}

	@Override
	public Integer minimum() {
		final int tam = this.tamanho();
		if(tam == 0)
			throw new RuntimeException("Conjunto vazio!");

		Integer resultado = this.meusDados[0];
		for(int i=1; i<tam; i++) {
			if(this.meusDados[i].compareTo(resultado) < 0)
				resultado = this.meusDados[i];
		}
		return resultado;
	}

	@Override
	public Integer maximum() {final int tam = this.tamanho();
		if(tam == 0)
			throw new RuntimeException("Conjunto vazio!");

		Integer resultado = this.meusDados[0];
		for(int i=1; i<tam; i++) {
			if(this.meusDados[i].compareTo(resultado) > 0)
				resultado = this.meusDados[i];
		}
		return resultado;
	}

}
