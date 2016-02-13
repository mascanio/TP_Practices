package tp.pr4.mv.instruction.RestSeq;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

public class Dup implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		int aux = 0;
		try {
			aux = cpu.pop();
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

		cpu.push(aux);
		cpu.push(aux);
	}

	@Override
	public Instruction parse(String[] s) {
		Dup out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("DUP"))
			out = new Dup();
		// else return null

		return out;
	}

	public String toString() {
		return "DUP";
	}

}
