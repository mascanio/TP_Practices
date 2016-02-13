package tp.pr5.mv.model.instruction.RestSeq;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.instruction.Instruction;

public class Halt implements RestSeq {

	@Override
	public Instruction parse(String[] s) {
		Halt out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("HALT")) //$NON-NLS-1$
			out = new Halt();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "HALT"; //$NON-NLS-1$
	}

	@Override
	public void execute(CPU cpu) {
		cpu.exit();
	}

}