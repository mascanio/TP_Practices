package tp.pr4.mv.instruction.Arithmetics;

import tp.pr4.mv.instruction.Instruction;

public class Add extends Arithmetics {

	@Override
	protected void execute(int cima, int subcima) {
		this.result = cima+subcima;
	}

	@Override
	public Instruction parse(String[] s) {
		Add out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("ADD"))
			out = new Add();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "ADD";
	}

}
