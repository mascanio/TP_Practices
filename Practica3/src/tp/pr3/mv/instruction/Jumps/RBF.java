package tp.pr3.mv.instruction.Jumps;

import tp.pr3.mv.CPU;
import tp.pr3.mv.OperandStackException;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionExecutionException;

public class RBF extends Jumps {

	public RBF(int n) {
		super(n);
	}

	public RBF() {
	}

	@Override
	public void executeAux(CPU cpu) throws InstructionExecutionException {

		try {
			if (cpu.pop() != 0) // Si hay un true saltamos a la istrucción
								// siguiente
				this.jumpToPc = cpu.getProgramCounter() + 1;
			else
				this.jumpToPc = cpu.getProgramCounter() + this.instructionPc;
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

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
