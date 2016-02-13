package tp.pr5.mv.model.instruction.BooleanCond;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public abstract class BooleanCond implements Instruction {

	protected boolean result;
	abstract protected void execute(int n1, int n2);
	
	/**
	 * realiza la operación booleana referente al objeto instanciado.
	 * 
	 * @throws InstructionExecutionException
	 *             si ocurre algún error de ejecución
	 */
	public void execute(CPU cpu) throws InstructionExecutionException {

		int n1 = 0, n2 = 0;
		try {
			n1 = cpu.pop();
			n2 = cpu.pop();
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

		this.execute(n1, n2);
		cpu.push(this.result ? 1 : 0);

	}

}
