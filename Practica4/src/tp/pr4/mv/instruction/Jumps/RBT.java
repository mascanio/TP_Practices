package tp.pr4.mv.instruction.Jumps;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

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
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

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
