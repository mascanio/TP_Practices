package tp.pr5.mv.model.instruction.Arithmetics;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public abstract class Arithmetics implements Instruction {

	protected int result;


	abstract protected void execute(int cima, int subcima);


	/**
	 * realiza la operación aritmética referente al objeto intanciado.
	 * 
	 * @throws InstructionExecutionException
	 *             si ocurre algún error de ejecución
	 */
	public void execute(CPU cpu) throws InstructionExecutionException {
		int cima, subcima;
		try {
			cima = cpu.pop();
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
		try {
			subcima = cpu.pop();
		} catch (StackException e) {
			cpu.push(cima); // apilar el perdido
			throw new InstructionExecutionException(e.getMessage());
		}
		try {
			this.execute(cima, subcima);
			cpu.push(this.result);
		} catch (IllegalArgumentException e) {
			cpu.push(subcima);
			cpu.push(cima);
			throw new InstructionExecutionException(e.getMessage());
		}
	}
}
