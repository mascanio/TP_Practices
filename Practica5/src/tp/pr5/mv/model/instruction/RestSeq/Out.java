package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;
import tp.pr5.mv.view.msg.Messages;

public class Out implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {

		try {
			cpu.performOutAction((char) cpu.pop());
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage()
					+ Messages.getString("Out.0")); //$NON-NLS-1$
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Out out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("OUT")) //$NON-NLS-1$
			out = new Out();
		// else return null

		return out;
	}

	public String toString() {
		return "OUT"; //$NON-NLS-1$
	}

}