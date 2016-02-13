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
	 * Método que pregunta al usuario si desea detener la ejecución, y en caso
	 * afirmativo detiene la máquina y el programa
	 */
	public void quit() {
		cpu.exit();
		System.exit(0);
	}

	/**
	 * Método que ejecuta la siguiente instrucción de la cpu. Se controla
	 * internamente lso errores que puedan ocurrir (error de ejecución de la
	 * instrucción, pc erróneo...)
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
	 * Método que ejecuta step() hasta que encuentre un error, o finalice la
	 * ejecución de todas las instrucciones (halt).
	 * 
	 * Se preguntará al usuario si desea continuar desde la isntrucción actual o
	 * si se debe reiniciar, y se actuará en consecuencia
	 */
	public void run() {
		cpu.run();
	}

	/**
	 * Método que resetea la mv
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
	 * Método que ejecuta pop en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecución
	 */
	public void pop() {
		try {
			cpu.externalPop();
		} catch (StackException e) {
		}
	}

	/**
	 * Método que ejecuta push en la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecución
	 * 
	 * @param n
	 *            elemento a introducir en la pila
	 */
	public void push(int n) {
		cpu.push(n);
	}

	/**
	 * Método que escribe en la memoria de la cpu.
	 * 
	 * Se controlan los errores posibles, informando al usuario y deteniendo la
	 * ejecución
	 * 
	 * @param pos
	 *            posición en la que escribir
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
