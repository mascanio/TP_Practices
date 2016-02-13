package tp.pr2.mv.instruction.RestSeq;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Out extends RestSeq {

	@Override
	protected boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(cpu.getStackSize() >= 1){
			int num = cpu.pop();
			System.out.println((char)num);
			out = true;
		}
				
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Out out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("OUT"))
			out = new Out();
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "OUT";
	}

}