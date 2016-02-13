package tp.pr5.mv.model;

import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.view.msg.Messages;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 * @param <T>
 *            tipo de los elementos a guardar
 */
public class Stack<T> implements Observable<StackObserver<T>> {

	private ArrayList<StackObserver<T>> observers;

	private Object[] elements;
	private int size, maxSize;

	static final int INI_SIZE_STACK = 10;

	// /////////////////////////////////////////

	public Stack() {
		this.observers = new ArrayList<>();
		this.elements = new Object[INI_SIZE_STACK];
		this.size = 0;
		this.maxSize = INI_SIZE_STACK;
	}

	public void reset() {
		this.elements = new Object[INI_SIZE_STACK];
		this.size = 0;
		this.maxSize = INI_SIZE_STACK;
	}
	
	// /////////////////////////////////////////

	@Override
	public void addObserver(StackObserver<T> o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(StackObserver<T> o) {
		observers.remove(o);
	}

	// ////////////////////////////////////////
	/*
	 * @SuppressWarnings("unchecked") public T peek() { return (T)
	 * elements[size-1]; }
	 */
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
		for (Iterator<StackObserver<T>> iterator = observers.iterator(); iterator
				.hasNext();) {
			iterator.next().onPush(n);
		}
	}

	/**
	 * Método que devuelve y elimina el valor cima
	 * 
	 * @return valor cima
	 */
	@SuppressWarnings("unchecked")
	public T pop() throws StackException {
		if (size <= 0)
			throw new StackException(
					Messages.getString("Stack.0")); //$NON-NLS-1$
		
		T r = (T) this.elements[--this.size];
		for (Iterator<StackObserver<T>> iterator = observers.iterator(); iterator
				.hasNext();) {
			iterator.next().onPop(r);
		}
		
		return r;
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
		String aux = ""; //$NON-NLS-1$

		for (int i = 0; i < this.size; i++) {
			aux += this.elements[i] + " "; //$NON-NLS-1$
		}

		return Messages.getString("Stack.1") + (this.size == 0 ? Messages.getString("Stack.2") : aux); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
