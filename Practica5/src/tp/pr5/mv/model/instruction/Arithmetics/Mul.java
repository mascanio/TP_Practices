package tp.pr5.mv.model.instruction.Arithmetics;

import tp.pr5.mv.model.instruction.Instruction;

public class Mul extends Arithmetics {

	@Override
	protected void execute(int cima, int subcima) {
		this.result = cima*subcima;
	}

	@Override
	public Instruction parse(String[] s) {
		Mul out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("MUL")) //$NON-NLS-1$
			out = new Mul();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "MUL"; //$NON-NLS-1$
	}

}