package tp.pr5.mv.model.instruction.NumericCond;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public abstract class NumericCond implements Instruction {

	protected abstract boolean compare(int cima, int subcima);
	
	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {

		int cima = 0, subcima = 0;
		try {
			cima = cpu.pop();
			subcima = cpu.pop();
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		} // cima y subcima

		if (compare(cima, subcima))
			cpu.push(1);
		else
			cpu.push(0);

	}
}
