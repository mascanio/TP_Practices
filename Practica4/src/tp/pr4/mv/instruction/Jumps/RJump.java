package tp.pr4.mv.instruction.Jumps;

import tp.pr4.mv.CPU;
import tp.pr4.mv.instruction.Instruction;

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
		
		if(s.length == 2 && s[0].equalsIgnoreCase("RJUMP"))
			out = new RJump(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "RJUMP " + this.instructionPc;
	}

}
