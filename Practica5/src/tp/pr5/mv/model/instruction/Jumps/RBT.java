package tp.pr5.mv.model.instruction.Jumps;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class RBT extends Jumps {

	public RBT(int n) {
		super(n);
	}

	public RBT() {
	}

	@Override
	public void executeAux(CPU cpu) throws InstructionExecutionException {

		try {
			if (cpu.pop() == 0) // Si hay un false saltamos a la istrucción
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
		RBT out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("RBT")) //$NON-NLS-1$
			out = new RBT(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "RBT " + this.instructionPc; //$NON-NLS-1$
	}

}
