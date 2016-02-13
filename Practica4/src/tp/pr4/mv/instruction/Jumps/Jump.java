package tp.pr4.mv.instruction.Jumps;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.Instruction;

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

		if (s.length == 2 && s[0].equalsIgnoreCase("JUMP"))
			out = new Jump(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "JUMP " + this.instructionPc;
	}
}
