package tp.pr2.mv.instruction.BooleanCond;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public abstract class BooleanCond extends Instruction {
	
	protected boolean result;
	
	abstract protected void execute(int n1, int n2);
	
	/**
	 * realiza la operaci�n booleana referente al objeto instanciado.
	 * @return true si se puede ejecutar
	 */
	public boolean execute(CPU cpu){
		boolean out = false;
		
		if(cpu.getStackSize() >= 2){ //dos o m�s operandos
			int n1 = cpu.pop(), n2 = cpu.pop();
			
			this.execute(n1, n2);
			cpu.push(this.result ? 1 : 0);
			
			out = true;
		}
		
		return out;
	}

}
