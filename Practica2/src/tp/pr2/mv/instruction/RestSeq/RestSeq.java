package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public abstract class RestSeq extends Instruction {

	abstract protected boolean executeAux(CPU cpu);

	@Override
	public boolean execute(CPU cpu) {
		boolean out = false;
		
		if(this.executeAux(cpu)){
			out = true;
		}
		// else return false;
		return out;
	}

}
