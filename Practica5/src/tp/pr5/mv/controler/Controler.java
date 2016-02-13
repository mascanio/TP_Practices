package tp.pr5.mv.controler;

import java.io.IOException;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.CpuIsHaltedException;
import tp.pr5.mv.model.CpuPCOverflowException;
import tp.pr5.mv.model.MemoryException;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public abstract class Controler {

	protected CPU cpu;

	public Controler(CPU cpu) {
		this.cpu = cpu;
	}

	public abstract void start();

	/**
	 * M�todo que pregunta al usuario si desea detener la ejecuci�n, y en caso
	 * afirmativo detiene la m�quina y el programa
	 */
	public void quit() {
		cpu.exit();
		System.exit(0);
	}

	/**
	 * M�todo que ejecuta la siguiente instrucci�n de la cpu. Se controla
	 * internamente lso errores que puedan ocurrir (error de ejecuci�n de la
	 * instrucci�n, pc err�neo...)
	 * 
	 * Actualiza la vista
	 */
	public void step() {
		try {
			cpu.step();
		} catch (InstructionExecutionException | CpuPCOverflowException
				| CpuIsHaltedException e) {
		}
	}

	/**
	 * M�todo que ejecuta step() hasta que encuentre un error, o finalice la
	 * ejecuci�n de todas las instrucciones (halt).
	 * 
	 * Se preguntar� al usuario si desea continuar desde la isntrucci�n actual o
	 * si se debe reiniciar, y se actuar� en consecuencia
	 */
	public void run() {
		cpu.run();
	}

	/**
	 * M�todo que resetea la mv
	 */
	public void reset() {
		try {
			cpu.reset();
		} catch (IOException e) {

		}
	}

	public void pasue() {
		cpu.pause_resume();
	}
	
	/**
	 * M�todo que ejecuta pop en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecuci�n
	 */
	public void pop() {
		try {
			cpu.externalPop();
		} catch (StackException e) {
		}
	}

	/**
	 * M�todo que ejecuta push en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecuci�n
	 * 
	 * @param n
	 *            elemento a introducir en la pila
	 */
	public void push(int n) {
		cpu.push(n);
	}

	/**
	 * M�todo que escribe en la memoria de la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecuci�n
	 * 
	 * @param pos
	 *            posici�n en la que escribir
	 * @param value
	 *            dato a escribir
	 */
	public void memorySet(int pos, int value) {
		try {
			cpu.externalMemWrite(pos, value);
		} catch (MemoryException e) {
		}
	}

}
