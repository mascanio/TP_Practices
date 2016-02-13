package tp.pr2.mv.instruction;

import tp.pr2.mv.CPU;

/**
 * 
 * @author Miguel Ascanio G�mez
 * @author Guillermo Romero Alonso
 * 
 */

abstract public class Instruction {
	/**
	 * Se implementar� la ejecucion de la instrucci�n instanciada sobre la CPU
	 * cpu
	 * 
	 * @param cpu
	 *            CPU sobre la que s eejecutan la instruccion
	 * @return true si la ejecuci�n fue correcta
	 */
	abstract public boolean execute(CPU cpu);

	/**
	 * Se implementar� el parseo de la instrucci�n s
	 * 
	 * @param s
	 *            String a parsear
	 * @return new intruccion referente a s
	 */
	abstract public Instruction parse(String[] s);
}