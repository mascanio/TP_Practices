package tp.pr5.mv.model.instruction.Arithmetics;

import tp.pr5.mv.model.instruction.Instruction;

public class Sub extends Arithmetics {

	@Override
	protected void execute(int cima, int subcima) {
		this.result = subcima-cima;
	}

	@Override
	public Instruction parse(String[] s) {
		Sub out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("SUB")) //$NON-NLS-1$
			out = new Sub();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "SUB"; //$NON-NLS-1$
	}

}
