package tp.pr2.mv.instruction.Jumps;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class BF extends Jumps {

	public BF(int n) {
		super(n);
	}

	public BF() {
		super();
	}

	@Override
	public boolean executeAux(CPU cpu) {
		boolean out = false;

		if (cpu.getStackSize() >= 1) {
			out = true;
			if (cpu.pop() != 0) // Si hay un true saltamos a la istrucción
								// actual
				this.jumpToPc = cpu.getProgramCounter() + 1;
			else
				this.jumpToPc = this.instructionPc;
		}

		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		BF out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("BF"))
			out = new BF(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "BF " + this.instructionPc;
	}

}
