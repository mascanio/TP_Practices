package tp.pr3.mv.programMV;

import tp.pr3.mv.instruction.Instruction;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class ProgramMV {

	static private final int INI_SIZE = 10;

	private Instruction[] program;
	private int cont, contMax;

	public ProgramMV() {
		this.program = new Instruction[INI_SIZE];

		this.cont = 0;
		this.contMax = INI_SIZE;
	}

	/**
	 * Añade la expresión pasada al programa
	 * 
	 * @param instr
	 *            : instrucción a añadir
	 */
	public void push(Instruction instr) {
		if (this.cont == this.contMax)
			redimensiona();

		this.program[this.cont++] = instr;
	}

	/**
	 * Redimensiona el array dinámico que contiene las instrucciones
	 */
	private void redimensiona() {
		int maxAux = ((this.contMax * 3) / 2) + 1;
		Instruction[] programAux = new Instruction[maxAux];

		for (int i = 0; i < this.contMax; i++)
			programAux[i] = this.program[i];

		this.program = programAux;
		this.contMax = maxAux;
	}

	/**
	 * Devuelve la instrucción situada en la posución pasada PRECONDICIÓN: la
	 * pos es válida
	 * 
	 * @param pos
	 *            : posición
	 * @return Instruction: instrucción en pos
	 */
	public Instruction get(int pos) {
		return this.program[pos];
	}

	/**
	 * 
	 * @return tamaño (nº de instrucciones) del programa, sin el end final
	 */
	public int getSizeProgram() {
		return this.cont - 1; // No contar el end
	}

	public String toString() {
		String aux = "";

		for (int i = 0; i < this.cont; i++)
			aux = aux + i + ": " + this.program[i].toString()
					+ System.lineSeparator();

		return aux;
	}

}
