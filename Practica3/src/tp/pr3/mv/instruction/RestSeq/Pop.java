package tp.pr3.mv.instruction.RestSeq;

import tp.pr3.mv.CPU;
import tp.pr3.mv.OperandStackException;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionExecutionException;

public class Pop implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		try {
			cpu.pop();
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Pop out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("POP"))
			out = new Pop();
		// else return null

		return out;
	}

	public String toString() {
		return "POP";
	}

}