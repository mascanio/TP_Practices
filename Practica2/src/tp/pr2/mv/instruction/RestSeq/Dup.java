package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Dup extends RestSeq {

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(cpu.getStackSize() >= 1){
			int aux = cpu.pop();
			
			cpu.push(aux);
			cpu.push(aux);
			
			out = true;
		}

		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Dup out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("DUP"))
			out = new Dup();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "DUP";
	}

}
