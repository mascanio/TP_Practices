package tp.pr2.mv.instruction.Arithmetics;

import tp.pr2.mv.instruction.Instruction;

public class Mul extends Arithmetics {

	@Override
	protected boolean execute(int cima, int subcima) {
		this.result = cima*subcima;
		return true;
	}

	@Override
	public Instruction parse(String[] s) {
		Mul out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("MULT"))
			out = new Mul();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "MULT";
	}

}