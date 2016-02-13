package tp.pr4.mv;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 * @param <T>
 *            tipo de los elementos a guarda
 */
public class Memory<T> {

	/*
	 * Constructora Operaciones de memoria Operaciones referentes al tamaño
	 * toString
	 */

	private Object[] elements;
	private int size, numElems;

	static final int INI_SIZE_MEM = 2;

	// /////////////////////////////////////////

	public Memory() {
		this.elements = new Object[INI_SIZE_MEM];
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
			out = (this.elements[pos] == null ? aleatorio : this.elements[pos]);
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
		if (this.elements[pos] == null)
			numElems++;
		this.elements[pos] = elem;
		
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
			aux[i] = this.elements[i];
		}

		this.size = newMaxSize;
		this.elements = aux;
	}

	// /////////////////////////////////////////

	public String toString() {
		String out = "";

		for (int i = 0; i < this.size; i++) {
			// Añado los que no son null
			out += (this.elements[i] == null ? "" : "[" + i + "]:"
					+ this.elements[i] + " ");
		}

		return "Memoria: " + (out.isEmpty() ? "<vacia>" : out);
	}

	/**
	 * Clase interna para las celdas de memoria
	 * 
	 * @author usuario_local
	 * 
	 * @param <T>
	 */
	public static class MemCell<T> {
		private int pos;
		private T value;

		protected MemCell(int pos, T value) {
			this.pos = pos;
			this.value = value;
		}

		public T getValue() {
			return value;
		}

		public int getPos() {
			return pos;
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @return el array de tamaño minmo con las celdas de memoria ocupadas, con su valor y posicion
	 */
	public Object[] getMemoryElements() {

		Object[] cells = new Object[numElems];
		int k = 0;
		for (int i = 0; i < size; i++) {
			if (elements[i] != null) {
				cells[k++] = new MemCell<T>(i, (T) elements[i]);
			}
		}

		return  cells;
	}
}