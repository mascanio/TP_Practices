package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class Dup implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		int aux = 0;
		try {
			aux = cpu.pop();
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

		cpu.push(aux);
		cpu.push(aux);
	}

	@Override
	public Instruction parse(String[] s) {
		Dup out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("DUP")) //$NON-NLS-1$
			out = new Dup();
		// else return null

		return out;
	}

	public String toString() {
		return "DUP"; //$NON-NLS-1$
	}

}
