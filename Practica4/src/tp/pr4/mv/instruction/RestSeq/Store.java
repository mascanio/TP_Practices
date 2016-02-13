package tp.pr4.mv.instruction.RestSeq;

import tp.pr4.mv.CPU;
import tp.pr4.mv.OperandStackException;
import tp.pr4.mv.instruction.Instruction;
import tp.pr4.mv.instruction.InstructionExecutionException;

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
		} catch (OperandStackException e) {
			throw new InstructionExecutionException(e.getMessage());
		}
	}

	@Override
	public Instruction parse(String[] s) {
		Store out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("STORE"))
			out = new Store(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "STORE " + this.pos;
	}

}