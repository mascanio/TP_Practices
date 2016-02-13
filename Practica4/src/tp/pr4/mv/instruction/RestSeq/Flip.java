package tp.pr4.mv.instruction.RestSeq;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

public class Flip implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {

		int aux1 = 0;
		int aux2 = 0;
		try {
			aux1 = cpu.pop();
			aux2 = cpu.pop();
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
		cpu.push(aux1);
		cpu.push(aux2);
	}

	@Override
	public Instruction parse(String[] s) {
		Flip out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("FLIP"))
			out = new Flip();
		// else return null

		return out;
	}

	public String toString() {
		return "FLIP";
	}

}
