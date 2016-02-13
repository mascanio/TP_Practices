package tp.pr5.mv.model.instruction.BooleanCond;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class Not implements Instruction {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		int n1 = 0;
		try {
			n1 = cpu.pop();
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

		cpu.push(n1 == 0 ? 1 : 0); // n1 == 0 -> n1 = false -> result = !n1
	}

	public Instruction parse(String[] s) {
		Not out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("NOT")) //$NON-NLS-1$
			out = new Not();
		// else return null

		return out;
	}

	public String toString() {
		return "NOT"; //$NON-NLS-1$
	}

}
