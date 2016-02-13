package tp.pr4.mv.instruction.Jumps;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

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
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

	@Override
	public Instruction parse(String[] s) {
		BF out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("BF"))
			out = new BF(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "BF " + this.instructionPc;
	}

}
