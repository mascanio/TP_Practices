package tp.pr5.mv.model.instruction.Jumps;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class BF extends Jumps {

	public BF(int n) {
		super(n);
	}

	public BF() {
		super();
	}

	@Override
	public void executeAux(CPU cpu) throws InstructionExecutionException {

		try {
			if (cpu.pop() != 0) // Si hay un true saltamos a la istrucción
								// actual
				this.jumpToPc = cpu.getProgramCounter() + 1;
			else
				this.jumpToPc = this.instructionPc;
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

	@Override
	public Instruction parse(String[] s) {
		BF out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("BF")) //$NON-NLS-1$
			out = new BF(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "BF " + this.instructionPc; //$NON-NLS-1$
	}

}
