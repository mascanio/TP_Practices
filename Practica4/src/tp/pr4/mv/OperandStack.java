package tp.pr4.mv;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 * @param <T> tipo de los elementos a guardar
 */
public class OperandStack<T> {

	private Object[] elements;
	private int size, maxSize;

	static final int INI_SIZE_STACK = 10;

	// /////////////////////////////////////////

	public OperandStack() {
		this.elements = new Object[INI_SIZE_STACK];
		this.size = 0;
		this.maxSize = INI_SIZE_STACK;
	}

	// /////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public T peek() {
		return (T) elements[size-1];
	}
	/**
	 * Método que apila (coloca encima de la pila) el elemento pasado como
	 * parámetro
	 * 
	 * @param n
	 *            elemento a apilar
	 */
	public void push(T n) {
		checkSize();
		this.elements[this.size++] = n;
	}

	/**
	 * Método que devuelve y elimina el valor cima
	 * 
	 * @return valor cima
	 */
	@SuppressWarnings("unchecked")
	public T pop() throws OperandStackException {
		if (size <= 0)
			throw new OperandStackException(
					"Error: no hay suficientes operandos en la pila");

		return (T) this.elements[--this.size];
	}

	/**
	 * Método que comprueba si la pila ha alcanzado el tamaño máximo, en tal
	 * caso la redimensiona con incrementSize()
	 */
	private void checkSize() {
		if (this.size == this.maxSize) {
			incrementSize();
		}
	}

	/**
	 * Método que aumenta el tamaño de la pila
	 */
	private void incrementSize() {
		this.maxSize = this.maxSize * 2;
		Object[] aux = new Object[this.maxSize];

		for (int i = 0; i < this.size; i++) {
			aux[i] = this.elements[i];
		}

		this.elements = aux;
	}

	// /////////////////////////////////////////

	public String toString() {
		String aux = "";

		for (int i = 0; i < this.size; i++) {
			aux += this.elements[i] + " ";
		}

		return "Pila de operandos: " + (this.size == 0 ? "<vacia>" : aux);
	}
	
	public Object[] getElements() {
		Object[] l = new Object[size];
		
		for (int i = 0; i < size; i++) {
			l[i] = elements[i];
		}
		return l;
	}
}
