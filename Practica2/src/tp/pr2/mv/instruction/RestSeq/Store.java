package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Store extends RestSeq {

	private int pos;
	
	public Store(int n) {
		this.pos = n;
	}

	public Store() { }

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(this.pos >= 0 && cpu.getStackSize() >= 1){
			cpu.memWrite(this.pos, cpu.pop());
			out = true;
		}
		
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Store out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("STORE"))
			out = new Store(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "STORE " + this.pos;
	}

}