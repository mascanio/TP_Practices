package tp.pr3.mv.instruction.RestSeq;

import tp.pr3.mv.CPU;
import tp.pr3.mv.OperandStackException;
import tp.pr3.mv.instruction.Instruction;
import tp.pr3.mv.instruction.InstructionExecutionException;

public class Out implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {

		try {
			cpu.performOutAction((char) cpu.pop());
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage()
					+ "\nError: la cadena no se ha cerrado con -1");
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Out out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("OUT"))
			out = new Out();
		// else return null

		return out;
	}

	public String toString() {
		return "OUT";
	}

}