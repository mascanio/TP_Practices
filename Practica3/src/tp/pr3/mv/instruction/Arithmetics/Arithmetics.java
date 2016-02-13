package tp.pr3.mv.instruction.Arithmetics;

import tp.pr3.mv.CPU;
import tp.pr3.mv.OperandStackException;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionExecutionException;

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
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
		try {
			subcima = cpu.pop();
		} catch (OperandStackException e) {
			cpu.push(cima); // apiler el perdido
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
