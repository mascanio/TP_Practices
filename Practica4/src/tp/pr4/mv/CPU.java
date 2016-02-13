package tp.pr4.mv;

import java.io.IOException;

import tp.pr4.mv.input.InMethod;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;
import tp.pr4.mv.output.OutMethod;
import tp.pr4.mv.programMV.ProgramMV;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class CPU {

	private Memory<Integer> memory;
	private OperandStack<Integer> stack;
	private ProgramMV program;

	private InMethod inStream;
	private OutMethod outStream;

	private boolean halt;

	private int programCounter;

	// //////////////////////////////
	// ************************** //
	// //////////////////////////////

	/**
	 * Constructora de la CPU
	 * 
	 * @param launcher
	 *            opciones de lanzamiento de la máquina, respecto a las cuales
	 *            la cpu va a ser configurada
	 * @throws IOException
	 *             si ocurre un error de IO al configurar lso streams de
	 *             entrada/salida
	 */
	public CPU(Launcher launcher) throws IOException {
		this.memory = new Memory<Integer>();
		this.stack = new OperandStack<Integer>();
		this.program = new ProgramMV();

		this.halt = false;
		this.programCounter = 0;

		this.inStream = launcher.configureIN();
		this.inStream.open();
		this.outStream = launcher.configureOUT();
		this.outStream.open();
	}

	/**
	 * Carga el programa pasado por parámetro
	 * 
	 * @param pr
	 *            : programa a cargar
	 */
	public void loadProgram(ProgramMV pr) {
		this.program = pr;
	}

	public String getStringProgram() {
		return this.program.toString();
	}

	/**
	 * Reseta la máquina, vaciando su memoria, stack, los streams y poniendo el
	 * contador de programa a 0.
	 * 
	 * @throws IOException
	 *             Si hay algún problema con los streams
	 */
	public void reset() throws IOException {
		this.memory = new Memory<Integer>();
		this.stack = new OperandStack<Integer>();

		this.halt = false;
		this.programCounter = 0;

		this.inStream.reset();
		this.outStream.reset();
	}

	/**
	 * Ejecuta la instrucción a la que apunta el contador de programa. Si la
	 * instrucción se ejecuta correctamente, se incrementa el contador de
	 * programa.
	 * 
	 * @throws InstructionExecutionException
	 *             si ocurre algún error al ejecutar la instrucción
	 * @throws CpuPCOverflowException
	 *             si el contador de programa se sale del propio programa
	 * @throws CpuIsHaltedException
	 *             si la máquina se ha detenido previamente
	 */
	public void step() throws InstructionExecutionException,
			CpuPCOverflowException, CpuIsHaltedException {

		checkIfComputationIsAborted();

		getCurrentInsrtuction().execute(this); // Throw exception err

		increaseProgramCounter();

		checkIfComputationIsAborted();
	}

	// //////////////////////////////
	// *** MÉTODOS DE CONTROL *** //
	// //////////////////////////////

	/**
	 * Método para comprobar si la máquina ha terminado la ejecución de
	 * instrucciones
	 * 
	 * @return boolean: true si se ha ejecutado halt en la máquina
	 */
	public boolean isHalt() {
		return this.halt;
	}

	/**
	 * Método que comprueba que la ejecución de la máquina puede o no continuar.
	 * En el caso de no poder continuar, lanza excepciones deacuerdo al
	 * problema.
	 * 
	 * @throws CpuPCOverflowException
	 *             si el contador de programa ha excedido el final de programa
	 * @throws CpuIsHaltedException
	 *             si se ha ejecutado un halt previamente
	 */
	public void checkIfComputationIsAborted() throws CpuPCOverflowException,
			CpuIsHaltedException {

		if (this.halt || this.programCounter > this.program.getSizeProgram()) {
			exit();
			throw new CpuIsHaltedException();
		}
		if (this.programCounter < -1) {
			throw new CpuPCOverflowException(
					"Se ha sobrepasado el contador de programa");
		}
	}

	/**
	 * Ejecuta halt en la máquina y cierra los streams de entrada y salida
	 */
	public void exit() {
		this.halt = true;

		try {
			this.inStream.close();
			this.outStream.close();
		} catch (IOException e) {
		}
	}

	// ////////////////////////////////
	// *** CONTADOR DE PROGRAMA *** //
	// ////////////////////////////////

	/**
	 * Devuelve el contador de programa, que apunta a la última instrucción
	 * ejecutada
	 * 
	 * @return int: el contador de programa de la máquina
	 */
	public int getProgramCounter() {
		return this.programCounter;
	}

	/**
	 * Pone el contador de programa de la máquina al valor seleccionado. No se
	 * comprueba si es valor válido.
	 * 
	 * @param n
	 *            valor del pc
	 */
	public void setProgramCounter(int n) {
		this.programCounter = n;
		// if (this.programCounter > this.program.getSizeProgram() ||
		// this.programCounter < -1) { // Si es -1 pasara a 0
		// this.correctPc = false;
		// }
	}

	/**
	 * Incrementa el contador de programa. Controla internamente si es correcto
	 */
	public void increaseProgramCounter() {
		this.programCounter++;
		// if (this.programCounter > this.program.getSizeProgram() ||
		// this.programCounter < 0) {
		// this.correctPc = false;
		// }
	}

	/**
	 * Método para obtener la instrucción a ejecutar
	 * 
	 * @return la instrucción a la que apunta el PC
	 */
	public Instruction getCurrentInsrtuction() {
		return this.program.get(this.programCounter);
	}

	public boolean isPcCorrect() {
		return this.programCounter <= this.program.getSizeProgram()
				&& this.programCounter > -1;
	}

	// ////////////////////////////////////////
	// *** MÉTODOS DE SISTEMAS INTERNOS *** //
	// ////////////////////////////////////////

	// Stack
	/**
	 * Extrae el último elemento de la pila
	 * 
	 * @return int: el elemento extraido
	 * @throws OperandStackException
	 *             si ocurre algún problema al extaer de la pila (está vacía)
	 */
	public int pop() throws OperandStackException {
		return this.stack.pop();
	}

	/**
	 * Apila el elemento pasado como parámetro
	 * 
	 * @param n
	 *            elemento a apilar
	 */
	public void push(int n) {
		this.stack.push(n);
	}

	// Memory
	/**
	 * Método para acceder leer de la memoria de la CPU. Se devuelve el elemento
	 * almacenado en la posición pos.
	 * 
	 * @param pos
	 *            posición de lectura
	 * @return el elemento almacenado en pos. 0 si pos no existe
	 * @throws MemoryException
	 *             si no se puede leer de la memoria (posición errónea)
	 */
	public int memRead(int pos) throws MemoryException {
		return this.memory.read(pos);
	}

	/**
	 * Método para escribir en la memoria de la CPU. Se escribe el elemento n en
	 * la posición pos indicada
	 * 
	 * @param pos
	 *            : posición de escritura
	 * @param n
	 *            : elemento a escribir
	 * @throws MemoryException
	 */
	public void memWrite(int pos, int n) throws MemoryException {
		this.memory.write(pos, n);
	}

	// Streams
	/**
	 * Método para leer desde el flujo de entrada de la máquina
	 * 
	 * @return el siguiente entero en el flujo
	 * @throws IOException
	 *             si ocurre algún fallo en lecutra
	 */
	public int performInAction() throws IOException {
		return this.inStream.readChar();
	}

	/**
	 * Método para escribir en el flujo de salida
	 * 
	 * @param c
	 *            elemento a escribir
	 */
	public void performOutAction(char c) {
		this.outStream.writeChar(c);
	}

	// ///////////////////////////////////////////

	public Object[] getStackElements() {
		return stack.getElements();
	}

	public Object[] getMemoryCells() {
		return memory.getMemoryElements();
	}

	public String toString() {
		return "El estado de la maquina tras ejecutar la instrucción es:"
				+ System.lineSeparator() + this.memory.toString()
				+ System.lineSeparator() + this.stack.toString()
				+ System.lineSeparator() + "PC: " + this.programCounter;
	}

	public InMethod getInput() {
		return inStream;
	}

	public void setInput(InMethod in) {
		inStream = in;
	}

	public OutMethod getOutput() {
		return outStream;
	}

	public void setOutput(OutMethod o) {
		this.outStream = o;

	}

}
