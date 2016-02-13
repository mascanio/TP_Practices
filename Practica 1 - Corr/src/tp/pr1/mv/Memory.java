package tp.pr1.mv;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 *
 */
public class Memory {
	
	/*
	 * Constructora
	 * Operaciones de memoria
	 * Operaciones referentes al tama�o
	 * toString
	 */
	
	private Integer[] space;
	private int size;

	static final int INI_SIZE_MEM = 2;
	
	///////////////////////////////////////////
	
	public Memory(){
		this.space = new Integer[INI_SIZE_MEM];
		this.size = INI_SIZE_MEM;
	}
	
	///////////////////////////////////////////
	/**
	 * Funci�n que lee de memoria
	 * @param pos posici�n a leer
	 * @return lo almacenado en la posici�n, num aleatorio si en la posici�n no hay nada
	 */
	public int read(int pos){		
		int aleatorio = (int) Math.floor(Math.random() * 1000), out;
		
		if(pos < this.size){
			//Si existe devuelvo el numero, si no, aleatorio
			out = (this.space[pos] == null ?  aleatorio : this.space[pos]);
		}
		else{
			out = aleatorio;
		}
		
		return out;
	}
	/**
	 * Funci�n que escribe en la memoria
	 * @param pos posici�n en la que escribir
	 * @param num dato a escribir
	 */
	public void write(int pos, int num){
		checkSize(pos);
		this.space[pos] = num;
	}
	
	///////////////////////////////////////////
	/**
	 * Analiza el tama�o de la memoria respecto de pos, si pos es mayor que la �ltima posici�n de la memoria, aumenta su tama�o
	 * @param pos posici�n con la que comparar
	 */
	private void checkSize(int pos){
		if(pos >= this.size){
			incrementSize(pos);
		}
	}
	/**
	 * Aumenta el tama�o de la memoria al doble de pos
	 * @param pos posici�n respecto de la que aumentar
	 */
	private void incrementSize(int pos){
		int newMaxSize = pos * 2;
		
		Integer[] aux = new Integer [newMaxSize];
		
		for(int i = 0; i < this.size; i++){
			aux[i] = this.space[i];
		}

		this.size = newMaxSize;
		this.space = aux;
	}
	
	///////////////////////////////////////////
	
	public String toString(){
		String out = "";
		
		for(int i = 0; i < this.size; i++){
			//A�ado los que no son null
			out += (this.space[i] == null ? "" : "[" + i + "]:" + this.space[i] + " ");
		}
		
		return "Memoria: " + (out.isEmpty() ? "<vacia>" : out);
	}
}