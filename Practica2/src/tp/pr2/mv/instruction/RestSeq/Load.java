package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Load extends RestSeq {
	
	private int pos;
	
	public Load(int n){
		this.pos = n;
	}
	
	public Load() { }

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(this.pos >= 0){
			cpu.push(cpu.memRead(this.pos));
			out = true;
		}
		
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Load out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("LOAD"))
			out = new Load(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "LOAD "+ this.pos;
	}

}
