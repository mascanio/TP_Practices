package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Halt extends RestSeq {

	@Override
	protected boolean executeAux(CPU cpu) {
		cpu.exit();
		return true;
	}

	@Override
	public Instruction parse(String[] s) {
		Halt out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("HALT"))
			out = new Halt();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "HALT";
	}

}