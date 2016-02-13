package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class Pop implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		try {
			cpu.pop();
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Pop out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("POP")) //$NON-NLS-1$
			out = new Pop();
		// else return null

		return out;
	}

	public String toString() {
		return "POP"; //$NON-NLS-1$
	}

}