package tp.pr2.mv.instruction.NumericCond;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public abstract class NumericCond extends Instruction {

	protected abstract boolean compare(int cima, int subcima);
	
	@Override
	public boolean execute(CPU cpu) {
		boolean out = false;
		
		if(cpu.getStackSize() >= 2){
			int cima = cpu.pop(), subcima = cpu.pop(); //cima y subcima
			
			if(compare(cima, subcima))
				cpu.push(1);
			else
				cpu.push(0);
			
			out = true;
		}
		
		return out;
	}
}
