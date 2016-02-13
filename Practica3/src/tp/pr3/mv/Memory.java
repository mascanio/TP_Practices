package tp.pr3.mv;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 * @param <T> tipo de los elementos a guarda
 */
public class Memory<T> {

	/*
	 * Constructora Operaciones de memoria Operaciones referentes al tamaño
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
	 * Método para leer el elemento contenido en Pos
	 * 
	 * @param pos
	 *            posición de la que leer
	 * @return el elemento contenido en pos
	 * @throws MemoryException
	 *             si se intenta acceder a una posición errónea (<0)
	 */
	@SuppressWarnings("unchecked")
	public T read(int pos) throws MemoryException {

		if (pos < 0)
			throw new MemoryException("Posición errónea de memoria.");

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
	 * Método para escribir en memoria. Si la posición está ocupada,
	 * sobreescribe.
	 * 
	 * @param pos
	 *            posición en la que escribir
	 * @param elem
	 *            elemento a escribir en memoria
	 * @throws MemoryException
	 *             si se intenta acceder a una posición errónea (<0)
	 */
	public void write(int pos, T elem) throws MemoryException {

		if (pos < 0)
			throw new MemoryException("Posición errónea de memoria.");

		checkSize(pos);
		this.space[pos] = elem;
	}

	// /////////////////////////////////////////
	/**
	 * Analiza el tamaño de la memoria respecto de pos, si pos es mayor que la
	 * última posición de la memoria, aumenta su tamaño
	 * 
	 * @param pos
	 *            posición con la que comparar
	 */
	private void checkSize(int pos) {
		if (pos >= this.size) {
			incrementSize(pos);
		}
	}

	/**
	 * Aumenta el tamaño de la memoria al doble de pos
	 * 
	 * @param pos
	 *            posición respecto de la que aumentar
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
			// Añado los que no son null
			out += (this.space[i] == null ? "" : "[" + i + "]:" + this.space[i]
					+ " ");
		}

		return "Memoria: " + (out.isEmpty() ? "<vacia>" : out);
	}
}