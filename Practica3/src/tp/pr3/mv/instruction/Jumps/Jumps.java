package tp.pr3.mv.instruction.Jumps;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionExecutionException;

public abstract class Jumps implements Instruction {

	protected int instructionPc;
	protected int jumpToPc;

	public Jumps(int n) {
		this.instructionPc = n;
	}

	public Jumps() {
	}

	protected abstract void executeAux(CPU cpu) throws InstructionExecutionException;

	public void execute(CPU cpu) throws InstructionExecutionException {

		this.executeAux(cpu);
		cpu.setProgramCounter(this.jumpToPc - 1); // Fuera siempre se suma 1

	}
}
