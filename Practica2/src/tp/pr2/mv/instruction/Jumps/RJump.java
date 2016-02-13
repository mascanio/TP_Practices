package tp.pr2.mv.instruction.Jumps;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class RJump extends Jumps {

	public RJump(int n){
		super(n);
	}
	public RJump() { }
	
	@Override
	public boolean executeAux(CPU cpu) {
		this.jumpToPc = this.instructionPc + cpu.getProgramCounter();
		return true;
	}

	@Override
	public Instruction parse(String[] s) {
		RJump out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("RJUMP"))
			out = new RJump(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "RJUMP " + this.instructionPc;
	}

}
