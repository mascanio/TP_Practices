package tp.pr5.mv.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import tp.pr5.mv.Launcher;
import tp.pr5.mv.model.input.InMethod;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;
import tp.pr5.mv.model.output.OutMethod;
import tp.pr5.mv.model.programMV.ProgramMV;
import tp.pr5.mv.view.msg.Messages;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public class CPU implements Observable<CPUObserver> {

	private ArrayList<CPUObserver> observers;

	private Memory<Integer> memory;
	private Stack<Integer> stack;
	private ProgramMV program;

	private InMethod inStream;
	private OutMethod outStream;

	private boolean halt, paused;

	private int programCounter, delay;

	// //////////////////////////////
	// ************************** //
	// //////////////////////////////

	@Override
	public void addObserver(CPUObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(CPUObserver o) {
		observers.remove(o);
	}

	private void reportErrorToObservers(String error, String title) {
		for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
				.hasNext();) {
			iterator.next().onError(error, title);
		}
	}

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
		this.stack = new Stack<Integer>();
		this.program = new ProgramMV();
		this.observers = new ArrayList<>();

		this.delay = 0;
		this.paused = false;
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
		return program.toString();
	}
	
	public int getProgramSize() {
		return program.getSizeProgram();
	}
	/**
	 * Reseta la máquina, vaciando su memoria, stack, los streams y poniendo el
	 * contador de programa a 0.
	 * 
	 * @throws IOException
	 *             Si hay algún problema con los streams
	 */
	public void reset() throws IOException {
		this.memory.reset();
		this.stack.reset();

		//this.delay = 0;
		this.halt = false;
		this.paused = false;
		this.programCounter = 0;

		try {
			this.inStream.reset();
			this.outStream.reset();
		} catch (IOException e) {
			reportErrorToObservers(e.getMessage()
					+ Messages.getString("CPU.0"), Messages.getString("CPU.1")); //$NON-NLS-1$ //$NON-NLS-2$
			throw e;
		}

		for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
				.hasNext();) {
			iterator.next().onReset(program);
		}
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

		try {
			// checkIfComputationIsAborted();
			Instruction instr = getCurrentInsrtuction();

			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onStartInstrExecution(instr);
			}

			instr.execute(this);
			increaseProgramCounter();

			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onEndInstrExecution(programCounter,
						this.toString());
			}

			checkIfComputationIsAborted();

		} catch (CpuPCOverflowException e) {
			reportErrorToObservers(e.getMessage(), Messages.getString("CPU.2")); //$NON-NLS-1$
			throw e;
		} catch  (InstructionExecutionException e1){
			reportErrorToObservers(e1.getMessage(), Messages.getString("CPU.3")); //$NON-NLS-1$
			throw e1;
		} catch (CpuIsHaltedException e2) {
			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onHalt();
			}
			throw e2;
		}

	}

	/**
	 * Ejecuta las n siguientes instrucciones.
	 * 
	 * @throws InstructionExecutionException
	 *             si ocurre algún error al ejecutar una instrucción
	 * @throws CpuPCOverflowException
	 *             si el contador de programa se sale del propio programa
	 * @throws CpuIsHaltedException
	 *             si la máquina se ha detenido
	 */
	public void stepn(int n) throws InstructionExecutionException,
			CpuPCOverflowException, CpuIsHaltedException {

		for (int i = 0; i < n; i++) {
			step();
		}
	}

	/**
	 * Ejecuta todas las instrucciones.
	 * 
	 */
	public void threadRun() {
		new Thread() {
			public void run() {
				cpuRun();
			}
		}.start();
	}
	
	public void run() {
		cpuRun();
	}
	
	private void cpuRun() {		
		for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
				.hasNext();) {
			iterator.next().onStartRun();
		}
		
		resumeRun();
	}
	
	private void resumeRun() {
		paused = false;
		
		try {
			while (!isHalt() && !paused) {
				step();
				sleep();
			}
		} catch (InstructionExecutionException | CpuPCOverflowException e) {
	
		} catch (CpuIsHaltedException e1) {
			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onEndRun();
			}
	
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			//Thread.currentThread().interrupt();
		}
	}

	public void pause_resume() {
		if (paused) { //RESUME
			paused = false;
			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onResume();
			}
			new Thread() {
				public void run() {
					resumeRun();
				}
			}.start();
		} else { //PAUSE
			paused = true;
			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onPause();
			}
		}
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
		if (this.programCounter < 0) {
			throw new CpuPCOverflowException(
					Messages.getString("CPU.4")); //$NON-NLS-1$
		}
	}

	/**
	 * Ejecuta halt en la máquina y cierra los streams de entrada y salida
	 */
	public void exit() {
		this.halt = true;
		this.paused = true;
	
		try { //Esperar a que acabe el threadRun
			Thread.sleep(delay);
		} catch (InterruptedException e1) {
		}
		
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
	}

	/**
	 * Incrementa el contador de programa. Controla internamente si es correcto
	 */
	public void increaseProgramCounter() {
		this.programCounter++;
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
	 * @throws StackException
	 *             si ocurre algún problema al extaer de la pila (está vacía)
	 */
	public int externalPop() throws StackException {
		try {
			return this.pop();
		} catch (StackException e) {
			reportErrorToObservers(e.getMessage(), Messages.getString("CPU.5")); //$NON-NLS-1$
			throw e;
		}
	}
	public int pop() throws StackException {	
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
	

	public int externalMemRead(int pos) throws MemoryException {
		try {
			return this.memRead(pos);
		} catch (MemoryException e) {
			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onError(e.getMessage(), Messages.getString("CPU.6")); //$NON-NLS-1$
			}
			throw e;
		}
	}
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
	public void externalMemWrite(int pos, int n) throws MemoryException {
		try {
			this.memWrite(pos, n);
		} catch (MemoryException e) {
			for (Iterator<CPUObserver> iterator = observers.iterator(); iterator
					.hasNext();) {
				iterator.next().onError(e.getMessage(), Messages.getString("CPU.6")); //$NON-NLS-1$
			}
			throw e;
		}
	}
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

	public String toString() {
		return Messages.getString("CPU.7") //$NON-NLS-1$
				+ System.lineSeparator() + this.memory.toString()
				+ System.lineSeparator() + this.stack.toString()
				+ System.lineSeparator() + "PC: " + this.programCounter; //$NON-NLS-1$
	}
	
	public void setDelay(int ms) {
		this.delay = ms;
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

	public Observable<StackObserver<Integer>> getStackObservable() {
		return stack;
	}

	public Observable<MemoryObserver<Integer>> getMemoryObserver() {
		return memory;
	}

}
