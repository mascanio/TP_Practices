package tp.pr2.mv;

import tp.pr2.mv.instruction.Instruction;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class CPU {

	/*
	 * Constructora
	 */

	private Memory memory;
	private OperandStack stack;
	private ProgramMv program;

	private boolean halt;
	private boolean correctPc;

	private int programCounter;

	// ///////////////////////////////////////////

	public CPU() {
		this.memory = new Memory();
		this.stack = new OperandStack();
		this.program = new ProgramMv();

		this.halt = false;
		this.correctPc = true;
		this.programCounter = 0;
	}
	
	public boolean step() {
		boolean out = false;
		
		if (!abortComputation()) {
			System.out.println("Comienza la ejecución de "
					+ getCurrentInsrtuction());
			if (getCurrentInsrtuction().execute(this)) {
				// La instrucción se ha podido ejecutar, y además correctamente
				increaseProgramCounter();
				out = true;
			} else
				System.out.println("** Error de ejecución **");

			System.out.println(toString());
		}
		return out;

	}

	// /////////////////////
	// GETTERS & SETTERS //
	// /////////////////////

	/**
	 * 
	 * @return boolean: true si se ha ejecutado halt en la máquina
	 */
	public boolean isHalt() {
		return this.halt;
	}

	/**
	 * Ejecuta halt en la máquina
	 */
	public void exit() {
		this.halt = true;
	}

	/**
	 * 
	 * @return int: el contadore de programa de la máquina
	 */
	public int getProgramCounter() {
		return this.programCounter;
	}

	/**
	 * Pone el contador de programa de la máquina al valor seleccionado
	 * 
	 * @param n
	 *            : valor del pc
	 */
	public void setProgramCounter(int n) {
		this.programCounter = n;
	}

	// ///////////////////
	// PROGRAM COUNTER //
	// ///////////////////

	/**
	 * Incrementa el contador de programa. Controla internamente si es correcto
	 */
	private void increaseProgramCounter() {
		this.programCounter++;
		if (this.programCounter > this.program.getSizeProgram()) {
			this.correctPc = false;
		}
	}

	/**
	 * 
	 * @return Boolean: true si el pc es correcto
	 */
	public boolean isProgramCounterOk() {
		return this.correctPc;
	}

	// ///////////////////////////////////////////

	// Stack
	/**
	 * Extra el último elemento de la pila
	 * 
	 * @return int: el elemento extraido
	 */
	public int pop() {
		return this.stack.pop();
	}

	/**
	 * Apila el elemento
	 * 
	 * @param n
	 *            : elemento a apilar
	 */
	public void push(int n) {
		this.stack.push(n);
	}

	/**
	 * 
	 * @return tamaño de la pila
	 */
	public int getStackSize() {
		return this.stack.getSize();
	}

	// Memory
	/**
	 * 
	 * @param pos
	 *            : posición de lectura
	 * @return int: el elemento almacenado en pos. Aleatorio si pos no existe
	 */
	public int memRead(int pos) {
		return this.memory.read(pos);
	}

	/**
	 * 
	 * @param pos
	 *            : posición de escritura
	 * @param n
	 *            : elemento a escribir
	 */
	public void memWrite(int pos, int n) {
		this.memory.write(pos, n);
	}

	// ///////////////////////////////////////////

	/**
	 * 
	 * @return true si se ha violado el PC, o se se ha ejecutado un halt
	 */
	public boolean abortComputation() {
		return !this.correctPc || this.halt;
	}

	/**
	 * Reseta la máquina, vaciando su memoria, stack y poniendo PC a 0
	 */
	public void reset() {
		this.memory = new Memory();
		this.stack = new OperandStack();

		this.halt = false;
		this.correctPc = true;
		this.programCounter = 0;
	}

	/**
	 * 
	 * @return la instrucción a la que apunta el PC
	 */
	private Instruction getCurrentInsrtuction() {
		return this.program.get(this.programCounter);
	}

	/**
	 * Carga el programa pasado por parámetro
	 * 
	 * @param pr
	 *            : programa a cargar
	 */
	public void loadProgram(ProgramMv pr) {
		this.program = pr;
	}

	public String toString() {
		return "El estado de la maquina tras ejecutar la instrucción es:"
				+ System.lineSeparator() + this.memory.toString()
				+ System.lineSeparator() + this.stack.toString()
				+ System.lineSeparator() + "PC: " + this.programCounter;
	}
}
