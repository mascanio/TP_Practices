package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.MemoryException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class Load implements RestSeq {

	private int pos;

	public Load(int n) {
		this.pos = n;
	}

	public Load() {
	}

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		try {
			cpu.push(cpu.memRead(this.pos));
		} catch (MemoryException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Load out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("LOAD")) //$NON-NLS-1$
			out = new Load(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "LOAD " + this.pos; //$NON-NLS-1$
	}

}
