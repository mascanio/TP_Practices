package tp.pr4.mv.instruction.RestSeq;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

public class StoreIND implements Instruction {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		int cima, subcima;
		try {
			cima = cpu.pop();
			
		} catch (OperandStackException e1) {
			throw new InstructionExecutionException(e1.getMessage());
		}
		
		try {	
			subcima = cpu.pop();
			cpu.memWrite(subcima, cima);
		} catch (OperandStackException e) {
			cpu.push(cima);
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		StoreIND out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("STOREIND"))
			out = new StoreIND();
		// else return null

		return out;
	}

	public String toString() {
		return "STOREIND";
	}

}
