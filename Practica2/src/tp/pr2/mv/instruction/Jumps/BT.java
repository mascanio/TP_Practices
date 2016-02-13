package tp.pr2.mv.instruction.Jumps;

import tp.pr2.mv.CPU;
import tp.pr2.mv.instruction.Instruction;

public class BT extends Jumps {

	public BT(int n) {
		super(n);
	}

	public BT() { }

	@Override
	public boolean executeAux(CPU cpu) {
		boolean out = false;
		
		if(cpu.getStackSize() >= 1){			
			out = true;
			if(cpu.pop() == 0) //Si hay un false saltamos a la istrucci�n actual
				this.jumpToPc = cpu.getProgramCounter() + 1;	
			else
				this.jumpToPc = this.instructionPc;
		}
		
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		BT out = null;
		
		if(s.length == 2 && s[0].equalsIgnoreCase("BT"))
			out = new BT(Integer.parseInt(s[1]));
		//else return null
		
		return out;
	}
	
	public String toString(){
		return "BT " + this.instructionPc;
	}

}
