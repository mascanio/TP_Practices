package tp.pr3.mv.instruction.BooleanCond;

import tp.pr3.mv.CPU;
import tp.pr3.mv.OperandStackException;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionExecutionException;

public class Not implements Instruction {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		int n1 = 0;
		try {
			n1 = cpu.pop();
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}

		cpu.push(n1 == 0 ? 1 : 0); // n1 == 0 -> n1 = false -> result = !n1
	}

	public Instruction parse(String[] s) {
		Not out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("NOT"))
			out = new Not();
		// else return null

		return out;
	}

	public String toString() {
		return "NOT";
	}

}
