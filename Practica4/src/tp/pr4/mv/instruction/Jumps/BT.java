package tp.pr4.mv.instruction.Jumps;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

public class BT extends Jumps {

	public BT(int n) {
		super(n);
	}

	public BT() {
	}

	@Override
	public void executeAux(CPU cpu) throws InstructionExecutionException {

		try {
			if (cpu.pop() == 0) // Si hay un false saltamos a la istrucción actual
				this.jumpToPc = cpu.getProgramCounter() + 1;
			else
				this.jumpToPc = this.instructionPc;
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

	}

	@Override
	public Instruction parse(String[] s) {
		BT out = null;

		if (s.length == 2 && s[0].equalsIgnoreCase("BT"))
			out = new BT(Integer.parseInt(s[1]));
		// else return null

		return out;
	}

	public String toString() {
		return "BT " + this.instructionPc;
	}

}
