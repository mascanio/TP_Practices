package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class Neg implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		try {
			cpu.push(0 - cpu.pop());
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Neg out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("NEG")) //$NON-NLS-1$
			out = new Neg();
		// else return null

		return out;
	}

	public String toString() {
		return "NEG"; //$NON-NLS-1$
	}

}