package tp.pr2.mv.instruction.Jumps;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class RBT extends Jumps {

	public RBT(int n) {
		super(n);
	}

	public RBT() { }

	@Override
	public boolean executeAux(CPU cpu) {
		boolean out = false;

		if (cpu.getStackSize() >= 1) {
			out = true;
			if (cpu.pop() == 0) // Si hay un false saltamos a la istrucción
								// siguiente
				this.jumpToPc = cpu.getProgramCounter() + 1;
			else
				this.jumpToPc = cpu.getProgramCounter() + this.instructionPc;
		}

		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		RBT out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("RBT"))
			out = new RBT(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "RBT " + this.instructionPc;
	}

}
