package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Push extends RestSeq {

	private int num;
	
	public Push(int n) {
		this.num = n;
	}

	public Push() { }

	@Override
	protected boolean executeAux(CPU cpu) {
		cpu.push(this.num);
		
		return true;
	}

	@Override
	public Instruction parse(String[] s) {
		Push out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("PUSH"))
			out = new Push(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "PUSH " + this.num;
	}

}

