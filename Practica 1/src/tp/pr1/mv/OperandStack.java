package tp.pr1.mv;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 *
 */
public class OperandStack {
	
	/*
	 * Constructora
	 * Operaciones de pila
	 * Operaciones referentes al tamaño
	 * toString
	 */

	private int[] space;
	private int size, maxSize;

	static final int INI_SIZE_STACK = 0;
	static final int INCREMENT_SIZE = 2; //Tamaño a aumentar al llamar a incrementSize
	
	///////////////////////////////////////////
	
	public OperandStack(){
		this.space = new int[INI_SIZE_STACK];
		this.size = 0;
		this.maxSize = 0;
	}
	
	///////////////////////////////////////////
	/**
	 * Método que apila
	 * @param n número a apilar
	 */
	public void push(int n){
		checkSize();
		this.space[this.size++] = n; 
	}
	/**
	 * Método que devuelve y elimina el valor cima
	 * @return valor cima
	 */
	public int pop(){
		int aux = this.space[this.size - 1];
		size--;
		return aux;
	}
	
	///////////////////////////////////////////
	/**
	 * Método que devuelve el tamaño de la pila
	 * @return Tamaño de la pila
	 */
	public int getSize(){
		return this.size;
	}
	/**
	 * Método que comprueba si la pila ha alcanzado el tamaño máximo,
	 * en tal caso la redimensiona con incrementSize()
	 */
	private void checkSize(){
		if(this.size == this.maxSize){
			incrementSize();
		}
	}
	/**
	 * Método que aumenta el tamaño de la pila en INCREMENT_SIZE
	 */
	private void incrementSize(){
		this.maxSize += INCREMENT_SIZE;
		int[] aux = new int [this.maxSize];
		
		for(int i = 0; i < this.size; i++){
			aux[i] = this.space[i];
		}
		
		this.space = aux;
	}
	
	///////////////////////////////////////////
		
	public String toString(){
		String aux = "";
		
		for(int i = 0; i < this.size; i++){
			aux += this.space[i] + " ";
		}
		
		return "Pila de operandos: " + ( this.size == 0 ? "<vacia>" : aux);
	}
}			
