package tp.pr2.mv.instruction.Arithmetics;

import tp.pr2.mv.instruction.Instruction;

public class Div extends Arithmetics {

	@Override
	protected boolean execute(int cima, int subcima) {
		boolean out = false;
		
		if(cima != 0){
			this.result = subcima/cima;
			out = true;
		}
		
		return out;
	}

	@Override
	public Instruction parse(String[] s) {
		Div out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("DIV"))
			out = new Div();
		//else return null
		
		return out;
	}
	
	public String toString() {
		return "DIV";
	}

}
