package tp.pr3.mv.instruction.NumericCond;

import tp.pr3.mv.instruction.Instruction;

public class LessOrEqual extends NumericCond {

	@Override
	protected boolean compare(int cima, int subcima) {
		return subcima <= cima;
	}

	@Override
	public Instruction parse(String[] s) {
		LessOrEqual out = null;
		
		if(s.length == 1 && s[0].equalsIgnoreCase("LE"))
			out = new LessOrEqual();
		//else return null
		
		return out;
	}
	public String toString(){
		return "LE";
	}

}
