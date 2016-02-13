package tp.pr2.mv.instruction.Jumps;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Jump extends Jumps {

	public Jump(int n){
		super(n);
	}
	public Jump() { }
	@Override
	public boolean executeAux(CPU cpu) {
		this.jumpToPc = this.instructionPc;
		return true;
	}

	@Override
	public Instruction parse(String[] s) {
		Jump out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("JUMP"))
			out = new Jump(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "JUMP " + this.instructionPc;
	}
}
