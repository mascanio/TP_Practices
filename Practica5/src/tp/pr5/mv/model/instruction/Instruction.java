package tp.pr5.mv.model.instruction;

import tp.pr5.mv.model.CPU;

/**
 * Interfaz para las instrucciones
 * 
 * @author Miguel Ascanio Gómez
 * @author Guillermo Romero Alonso
 * 
 */
public interface Instruction {

	/**
	 * Ejecuta la instrucción sobre la CPU cpu
	 * 
	 * @param cpu
	 *            CPU sobre la que se ejecuta la instruccion
	 * @throws InstructionExecutionException
	 *             si ocurre error de ejecución
	 */
	void execute(CPU cpu) throws InstructionExecutionException;

	/**
	 * Parsea la instrucción referente a s
	 * 
	 * @param s
	 *            String a parsear
	 * @return new intruccion referente a s
	 */
	Instruction parse(String[] s);
}