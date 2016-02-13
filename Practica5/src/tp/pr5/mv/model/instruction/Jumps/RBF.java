package tp.pr5.mv.model.instruction.Jumps;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

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
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

	@Override
	public Instruction parse(String[] s) {
		RBF out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("RBF")) //$NON-NLS-1$
			out = new RBF(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "RBF " + this.instructionPc; //$NON-NLS-1$
	}

}
