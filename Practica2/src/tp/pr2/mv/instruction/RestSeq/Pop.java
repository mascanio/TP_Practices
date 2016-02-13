package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Pop extends RestSeq {

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(cpu.getStackSize() >= 1){
			cpu.pop();
			out = true;
		}
		
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Pop out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("POP"))
			out = new Pop();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "POP";
	}

}