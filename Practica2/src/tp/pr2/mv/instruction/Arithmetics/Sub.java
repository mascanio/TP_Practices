package tp.pr2.mv.instruction.Arithmetics;

import tp.pr2.mv.instruction.Instruction;

public class Sub extends Arithmetics {

	@Override
	protected boolean execute(int cima, int subcima) {
		this.result = subcima-cima;
		return true;
	}

	@Override
	public Instruction parse(String[] s) {
		Sub out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("SUB"))
			out = new Sub();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "SUB";
	}

}
