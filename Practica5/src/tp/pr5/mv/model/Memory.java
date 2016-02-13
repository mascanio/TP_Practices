package tp.pr5.mv.model;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.view.msg.Messages;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 * 
 * @param <T>
 *            tipo de los elementos a guarda
 */
public class Memory<T> implements Observable<MemoryObserver<T>> {

	/*
	 * Constructora Operaciones de memoria Operaciones referentes al tama�o
	 * toString
	 */

	private ArrayList<MemoryObserver<T>> observers;

	private Object[] elements;
	private int size, numElems;

	static final int INI_SIZE_MEM = 2;

	// /////////////////////////////////////////

	public Memory() {
		this.observers = new ArrayList<>();
		this.elements = new Object[INI_SIZE_MEM];
		this.size = INI_SIZE_MEM;
	}
	
	public void reset() {
		this.elements = new Object[INI_SIZE_MEM];
		this.size = INI_SIZE_MEM;

	}

	// /////////////////////////////////////////

	@Override
	public void addObserver(MemoryObserver<T> o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(MemoryObserver<T> o) {
		observers.remove(o);
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
			throw new MemoryException(Messages.getString("Memory.0")); //$NON-NLS-1$

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
			throw new MemoryException(Messages.getString("Memory.0")); //$NON-NLS-1$

		checkSize(pos);
		if (this.elements[pos] == null)
			numElems++;
		this.elements[pos] = elem;
		
		for (Iterator<MemoryObserver<T>> iterator = observers.iterator(); iterator.hasNext();) {
			iterator.next().onWrite(pos, elem);			
		}

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
			aux[i] = this.elements[i];
		}

		this.size = newMaxSize;
		this.elements = aux;
	}

	// /////////////////////////////////////////

	public String toString() {
		String out = ""; //$NON-NLS-1$

		for (int i = 0; i < this.size; i++) {
			// A�ado los que no son null
			out += (this.elements[i] == null ? "" : "[" + i + "]:" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					+ this.elements[i] + " "); //$NON-NLS-1$
		}

		return Messages.getString("Memory.1") + (out.isEmpty() ? Messages.getString("Memory.2") : out); //$NON-NLS-1$ //$NON-NLS-2$
	}

}