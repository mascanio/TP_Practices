package tp.pr3.mv.instruction.RestSeq;

import tp.pr3.mv.CPU;
import tp.pr3.mv.instruction.Instruction;

public class Halt implements RestSeq {

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

	@Override
	public void execute(CPU cpu) {
		cpu.exit();
	}

}