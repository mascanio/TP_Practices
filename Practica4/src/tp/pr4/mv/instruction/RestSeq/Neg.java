package tp.pr4.mv.instruction.RestSeq;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

public class Neg implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		try {
			cpu.push(0 - cpu.pop());
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Neg out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("NEG"))
			out = new Neg();
		// else return null

		return out;
	}

	public String toString() {
		return "NEG";
	}

}