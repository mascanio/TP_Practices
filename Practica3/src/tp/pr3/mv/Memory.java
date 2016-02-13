package tp.pr3.mv;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 * 
 * @param <T> tipo de los elementos a guarda
 */
public class Memory<T> {

	/*
	 * Constructora Operaciones de memoria Operaciones referentes al tama�o
	 * toString
	 */

	private Object[] space;
	private int size;

	static final int INI_SIZE_MEM = 2;

	// /////////////////////////////////////////

	public Memory() {
		this.space = new Object[INI_SIZE_MEM];
		this.size = INI_SIZE_MEM;
	}

	// /////////////////////////////////////////
	/**
	 * M�todo para leer el elemento contenido en Pos
	 * 
	 * @param pos
	 *            posici�n de la que leer
	 * @return el elemento contenido en pos
	 * @throws MemoryException
	 *             si se intenta acceder a una posici�n err�nea (<0)
	 */
	@SuppressWarnings("unchecked")
	public T read(int pos) throws MemoryException {

		if (pos < 0)
			throw new MemoryException("Posici�n err�nea de memoria.");

		Object aleatorio = 0, out;

		if (pos < this.size) {
			// Si existe devuelvo el numero, si no, aleatorio
			out = (this.space[pos] == null ? aleatorio : this.space[pos]);
		} else {
			out = aleatorio;
		}

		return (T) out;
	}

	/**
	 * M�todo para escribir en memoria. Si la posici�n est� ocupada,
	 * sobreescribe.
	 * 
	 * @param pos
	 *            posici�n en la que escribir
	 * @param elem
	 *            elemento a escribir en memoria
	 * @throws MemoryException
	 *             si se intenta acceder a una posici�n err�nea (<0)
	 */
	public void write(int pos, T elem) throws MemoryException {

		if (pos < 0)
			throw new MemoryException("Posici�n err�nea de memoria.");

		checkSize(pos);
		this.space[pos] = elem;
	}

	// /////////////////////////////////////////
	/**
	 * Analiza el tama�o de la memoria respecto de pos, si pos es mayor que la
	 * �ltima posici�n de la memoria, aumenta su tama�o
	 * 
	 * @param pos
	 *            posici�n con la que comparar
	 */
	private void checkSize(int pos) {
		if (pos >= this.size) {
			incrementSize(pos);
		}
	}

	/**
	 * Aumenta el tama�o de la memoria al doble de pos
	 * 
	 * @param pos
	 *            posici�n respecto de la que aumentar
	 */
	private void incrementSize(int pos) {
		int newMaxSize = pos * 2;

		Object[] aux = new Object[newMaxSize];

		for (int i = 0; i < this.size; i++) {
			aux[i] = this.space[i];
		}

		this.size = newMaxSize;
		this.space = aux;
	}

	// /////////////////////////////////////////

	public String toString() {
		String out = "";

		for (int i = 0; i < this.size; i++) {
			// A�ado los que no son null
			out += (this.space[i] == null ? "" : "[" + i + "]:" + this.space[i]
					+ " ");
		}

		return "Memoria: " + (out.isEmpty() ? "<vacia>" : out);
	}
}