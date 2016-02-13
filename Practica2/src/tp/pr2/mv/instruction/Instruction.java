package tp.pr2.mv.instruction;

import tp.pr2.mv.CPU;

/**
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */

abstract public class Instruction {
	/**
	 * Se implementará la ejecucion de la instrucción instanciada sobre la CPU
	 * cpu
	 * 
	 * @param cpu
	 *            CPU sobre la que s eejecutan la instruccion
	 * @return true si la ejecución fue correcta
	 */
	abstract public boolean execute(CPU cpu);

	/**
	 * Se implementará el parseo de la instrucción s
	 * 
	 * @param s
	 *            String a parsear
	 * @return new intruccion referente a s
	 */
	abstract public Instruction parse(String[] s);
}