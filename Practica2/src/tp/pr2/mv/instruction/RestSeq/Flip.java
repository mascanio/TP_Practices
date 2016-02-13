package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Flip extends RestSeq {

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(cpu.getStackSize() >= 2){
			int aux1 = cpu.pop();
			int aux2 = cpu.pop();
			
			cpu.push(aux1);
			cpu.push(aux2);
			
			out = true;
		}
		
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Flip out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("FLIP"))
			out = new Flip();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "FLIP";
	}

}
