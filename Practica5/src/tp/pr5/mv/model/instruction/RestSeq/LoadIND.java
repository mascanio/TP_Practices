package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class LoadIND implements Instruction {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		try {
			cpu.push(cpu.memRead(cpu.pop()));
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		LoadIND out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("LOADIND")) //$NON-NLS-1$
			out = new LoadIND();
		// else return null

		return out;
	}

	public String toString() {
		return "LOADIND"; //$NON-NLS-1$
	}

}
