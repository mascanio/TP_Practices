package tp.pr5.mv.model.instruction.Jumps;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

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
