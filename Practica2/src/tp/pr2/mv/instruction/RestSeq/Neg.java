package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Neg extends RestSeq {

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(cpu.getStackSize() >= 1){
			cpu.push(0 - cpu.pop());
			out = true;
		}
		
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Neg out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("NEG"))
			out = new Neg();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "NEG";
	}

}