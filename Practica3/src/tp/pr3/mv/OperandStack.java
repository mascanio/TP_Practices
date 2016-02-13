package tp.pr3.mv;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 * 
 * @param <T> tipo de los elementos a guardar
 */
public class OperandStack<T> {

	private Object[] space;
	private int size, maxSize;

	static final int INI_SIZE_STACK = 10;

	// /////////////////////////////////////////

	public OperandStack() {
		this.space = new Object[INI_SIZE_STACK];
		this.size = 0;
		this.maxSize = INI_SIZE_STACK;
	}

	// /////////////////////////////////////////
	/**
	 * M�todo que apila (coloca encima de la pila) el elemento pasado como
	 * par�metro
	 * 
	 * @param n
	 *            elemento a apilar
	 */
	public void push(T n) {
		checkSize();
		this.space[this.size++] = n;
	}

	/**
	 * M�todo que devuelve y elimina el valor cima
	 * 
	 * @return valor cima
	 */
	@SuppressWarnings("unchecked")
	public T pop() throws OperandStackException {
		if (size <= 0)
			throw new OperandStackException(
					"Error: no hay suficientes operandos en la pila");

		return (T) this.space[--this.size];
	}

	/**
	 * M�todo que comprueba si la pila ha alcanzado el tama�o m�ximo, en tal
	 * caso la redimensiona con incrementSize()
	 */
	private void checkSize() {
		if (this.size == this.maxSize) {
			incrementSize();
		}
	}

	/**
	 * M�todo que aumenta el tama�o de la pila
	 */
	private void incrementSize() {
		this.maxSize = this.maxSize * 2;
		Object[] aux = new Object[this.maxSize];

		for (int i = 0; i < this.size; i++) {
			aux[i] = this.space[i];
		}

		this.space = aux;
	}

	// /////////////////////////////////////////

	public String toString() {
		String aux = "";

		for (int i = 0; i < this.size; i++) {
			aux += this.space[i] + " ";
		}

		return "Pila de operandos: " + (this.size == 0 ? "<vacia>" : aux);
	}
}
