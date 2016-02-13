package tp.pr4.mv.instruction.NumericCond;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

public abstract class NumericCond implements Instruction {

	protected abstract boolean compare(int cima, int subcima);

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {

		int cima = 0, subcima = 0;
		try {
			cima = cpu.pop();
			subcima = cpu.pop();
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		} // cima y subcima

		if (compare(cima, subcima))
			cpu.push(1);
		else
			cpu.push(0);

	}
}
