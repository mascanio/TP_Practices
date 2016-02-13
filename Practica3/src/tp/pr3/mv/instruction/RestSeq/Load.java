package tp.pr3.mv.instruction.RestSeq;

import tp.pr3.mv.CPU;
import tp.pr3.mv.MemoryException;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionExecutionException;

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

		if (s.length == 2 && s[0].equalsIgnoreCase("LOAD"))
			out = new Load(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "LOAD " + this.pos;
	}

}
