package tp.pr2.mv.instruction.BooleanCond;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class Not extends Instruction {

	public Instruction parse(String[] s) {
		Not out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("NOT"))
			out = new Not();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "NOT";
	}

	@Override
	public boolean execute(CPU cpu) {
		boolean out = false;
		
		if (cpu.getStackSize() >= 1) {
			int n1 = cpu.pop();
			cpu.push(n1 == 0 ? 1 : 0); //n1 == 0 -> n1 = false -> result = !n1
			out = true;
		}
		
		return out;
	}


}
