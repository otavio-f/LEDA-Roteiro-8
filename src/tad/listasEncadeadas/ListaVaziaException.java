package tad.listasEncadeadas;

// BUG: Nao pode lançar essa exceção sem mudar a assinatura
// public class ListaVaziaException extends Exception {

// FIX: Mudar pra subclasse de RuntimeException
public class ListaVaziaException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2035554480259121771L;

	public ListaVaziaException() {
		super("lista vazia!");
	}
	
	public ListaVaziaException(String message) {
		super(message);
	}

}
