package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.instruction.Instruction;

public class Push implements RestSeq {

	private int num;

	public Push() {

	}

	public Push(int n) {
		this.num = n;
	}

	@Override
	public void execute(CPU cpu) {
		cpu.push(this.num);
	}

	@Override
	public Instruction parse(String[] s) {
		Push out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH")) //$NON-NLS-1$
			out = new Push(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "PUSH " + this.num; //$NON-NLS-1$
	}

}
