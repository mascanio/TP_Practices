package tp.pr5.mv.model.instruction.Jumps;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class JumpIND extends Jumps {

	public JumpIND(int n) {
		super(n);
	}

	public JumpIND() {
	}

	@Override
	public void executeAux(CPU cpu) throws InstructionExecutionException {

		try {
			this.jumpToPc = cpu.getProgramCounter() + cpu.pop();
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

	@Override
	public Instruction parse(String[] s) {
		JumpIND out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("JumpIND")) //$NON-NLS-1$
			out = new JumpIND();
		// else return null

		return out;
	}

	public String toString() {
		return "JUMPIND " + this.instructionPc; //$NON-NLS-1$
	}

}
