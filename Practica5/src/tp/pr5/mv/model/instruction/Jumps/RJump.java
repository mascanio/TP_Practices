package tp.pr5.mv.model.instruction.Jumps;

import tp.pr5.mv.model.CPU;
import tp.pr5.mv.model.instruction.Instruction;

public class RJump extends Jumps {

	public RJump(int n){
		super(n);
	}
	public RJump() { }
	
	@Override
	public void executeAux(CPU cpu) {
		this.jumpToPc = this.instructionPc + cpu.getProgramCounter();
	}

	@Override
	public Instruction parse(String[] s) {
		RJump out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("RJUMP")) //$NON-NLS-1$
			out = new RJump(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "RJUMP " + this.instructionPc; //$NON-NLS-1$
	}

}
