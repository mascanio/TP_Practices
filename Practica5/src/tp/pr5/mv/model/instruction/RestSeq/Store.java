package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.StackException;
import tp.pr5.mv.model.instruction.Instruction;
import tp.pr5.mv.model.instruction.InstructionExecutionException;

public class Store implements RestSeq {

	private int pos;
	
	public Store(int n) {
		this.pos = n;
	}

	public Store() { }

	@Override
	public void execute(CPU cpu) throws InstructionExecutionException {
		try {
			cpu.memWrite(this.pos, cpu.pop());
		} catch (StackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Store out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("STORE")) //$NON-NLS-1$
			out = new Store(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "STORE " + this.pos; //$NON-NLS-1$
	}

}