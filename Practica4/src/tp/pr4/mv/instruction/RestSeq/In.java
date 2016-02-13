package tp.pr4.mv.instruction.RestSeq;

import java.io.IOException;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

public class In implements RestSeq {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {

		try {
			cpu.push(cpu.performInAction());
		} catch (IOException e) {
			throw new InstructionExecutionException("Error en flujo de entrada");
		}
	}

	@Override
	public Instruction parse(String[] s) {
		In out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("IN"))
			out = new In();
		// else return null

		return out;
	}

	public String toString() {
		return "IN";
	}
}
