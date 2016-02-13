package tp.pr5.mv.model.instruction.Jumps;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.instruction.Instruction;

public class Jump extends Jumps {

	public Jump(int n) {
		super(n);
	}

	public Jump() {
	}

	@Override
	public void executeAux(CPU cpu) {
		this.jumpToPc = this.instructionPc;
	}

	@Override
	public Instruction parse(String[] s) {
		Jump out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("JUMP")) //$NON-NLS-1$
			out = new Jump(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "JUMP " + this.instructionPc; //$NON-NLS-1$
	}
}
