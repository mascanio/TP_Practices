package tp.pr2.mv.instruction.Jumps;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public abstract class Jumps extends Instruction {

	protected int instructionPc;
	protected int jumpToPc;
	
	public Jumps(int n) {
		this.instructionPc = n;
	}

	public Jumps() { }

	protected abstract boolean executeAux(CPU cpu);

	public boolean execute(CPU cpu) {

		boolean out = false;

		if (this.executeAux(cpu)) {
			cpu.setProgramCounter(this.jumpToPc - 1); // Fuera siempre se suma 1
			out = true;
		}

		return out;
	}
}
