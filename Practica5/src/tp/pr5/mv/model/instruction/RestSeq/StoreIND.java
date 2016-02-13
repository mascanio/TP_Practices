package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class StoreIND implements Instruction {

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		int cima, subcima;
		try {
			cima = cpu.pop();
			
		} catch (StackException e1) {
			throw new InstructionExecutionException(e1.getMessage());
		}
		
		try {	
			subcima = cpu.pop();
			cpu.memWrite(subcima, cima);
		} catch (StackException e) {
			cpu.push(cima);
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		StoreIND out = null;

		if (s.length == 1 && s[0].equalsIgnoreCase("STOREIND")) //$NON-NLS-1$
			out = new StoreIND();
		// else return null

		return out;
	}

	public String toString() {
		return "STOREIND"; //$NON-NLS-1$
	}

}
