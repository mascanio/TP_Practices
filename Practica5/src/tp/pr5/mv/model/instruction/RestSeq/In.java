package tp.pr5.mv.model.instruction.RestSeq;

import java.io.IOException;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;
import tp.pr5.mv.view.msg.Messages;

public class In implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {

		try {
			cpu.push(cpu.performInAction());
		} catch (IOException e) {
			throw new InstructionExecutionException(Messages.getString("In.0")); //$NON-NLS-1$
		}
	}

	@Override
	public Instruction parse(String[] s) {
		In out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("IN")) //$NON-NLS-1$
			out = new In();
		// else return null

		return out;
	}

	public String toString() {
		return "IN"; //$NON-NLS-1$
	}
}
