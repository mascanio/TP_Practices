package tp.pr2.mv.instruction.Jumps;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class RBF extends Jumps {

	public RBF(int n) {
		super(n);
	}

	public RBF() { }

	@Override
	public boolean executeAux(CPU cpu) {
		boolean out = false;

		if (cpu.getStackSize() >= 1) {
			out = true;
			if (cpu.pop() != 0) // Si hay un true saltamos a la istrucción
								// siguiente
				this.jumpToPc = cpu.getProgramCounter() + 1;
			else
				this.jumpToPc = cpu.getProgramCounter() + this.instructionPc;
		}

		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		RBF out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("RBF"))
			out = new RBF(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "RBF " + this.instructionPc;
	}

}
