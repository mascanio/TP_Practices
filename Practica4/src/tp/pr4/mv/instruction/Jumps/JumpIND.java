package tp.pr4.mv.instruction.Jumps;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

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
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

	@Override
	public Instruction parse(String[] s) {
		JumpIND out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("JumpIND"))
			out = new JumpIND();
		// else return null

		return out;
	}

	public String toString() {
		return "JUMPIND " + this.instructionPc;
	}

}
